package com.zhuzimo.service;

import cn.hutool.core.util.IdUtil;
import com.zhuzimo.account.entity.Account;
import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;
import com.zhuzimo.account.service.AccountService;
import com.zhuzimo.command.LoginCmd;
import com.zhuzimo.command.RegisterCmd;
import com.zhuzimo.common.CommonResp;
import com.zhuzimo.common.exception.BizException;
import com.zhuzimo.dto.LoginCacheDto;
import com.zhuzimo.dto.RegisterOrLoginDto;
import com.zhuzimo.util.CacheFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;


/**
 * 注册或登录服务
 *
 * @author t3
 * @date 2023/11/28
 */
@Component
@Validated
public class RegisterOrLoginServiceImpl implements RegisterOrLoginService {


    @Resource
    private CacheFacade cacheFacade;

    private static final Long TOKEN_TIMEOUT = 24 * 60 * 60L;

    private static final Long REFRESH_TOKEN_TIMEOUT = 30 * 24 * 60 * 60L;

    private static final Long LOCK_TIMEOUT = 60L;

    @Resource
    private AccountService accountService;

    @Value("${password.salt:salt}")
    private String salt;

    /**
     * 生成令牌密钥
     *
     * @param token 令 牌
     * @return {@link String}
     */
    private String buildTokenKey(String token) {
        return "LOGIN:CACHE:TOKEN:" + token;
    }

    /**
     * 生成刷新令牌密钥
     *
     * @param token 令 牌
     * @return {@link String}
     */
    private String buildRefreshTokenKey(String token) {
        return "LOGIN:CACHE:REFRESH:TOKEN:" + token;
    }

    /**
     * 构建锁定键
     *
     * @param lockKey 锁键
     * @return {@link String}
     */
    private String buildLockKey(String lockKey) {
        return "LOGIN:REGISTER:LOCK:" + lockKey;
    }

    @Override
    @Transactional
    public CommonResp<RegisterOrLoginDto> register(@Valid RegisterCmd registerCmd) {
        boolean locked = cacheFacade.lock(buildLockKey(registerCmd.getName()), LOCK_TIMEOUT);
        if (!locked) {
            return CommonResp.buildError("请求处理中，请勿重复请求");
        }
        AccountName accountName = new AccountName(registerCmd.getName());
        Account accountExist = accountService.queryByName(accountName);
        if (Objects.nonNull(accountExist)) {
            throw new BizException("用户名已经存在");
        }
        AccountPassword accountPassword = new AccountPassword(registerCmd.getPassword(), salt);
        Account savedAccount  = accountService.save(accountName, accountPassword);
        LoginCacheDto cacheDto = save2cache(savedAccount);
        return CommonResp.buildSuccess(new RegisterOrLoginDto(cacheDto.getToken(), cacheDto.getRefreshToken()));
    }

    @Override
    public CommonResp<RegisterOrLoginDto> login(LoginCmd loginCmd) {
        AccountName accountName = new AccountName(loginCmd.getName());
        Account accountExist = accountService.queryByName(accountName);
        if (Objects.isNull(accountExist)) {
            throw new BizException("用户名不存在");
        }
        accountExist = accountService.queryByNameAndPassword(accountName, new AccountPassword(loginCmd.getPassword(), salt));
        if (Objects.isNull(accountExist)) {
            throw new BizException("密码不正确");
        }
        LoginCacheDto cacheDto = save2cache(accountExist);
        return CommonResp.buildSuccess(new RegisterOrLoginDto(cacheDto.getToken(), cacheDto.getRefreshToken()));
    }

    /**
     * save2cache
     *
     * @param accountExist 帐户存在
     * @return {@link LoginCacheDto}
     */
    private LoginCacheDto save2cache(Account accountExist) {
        String token = IdUtil.fastSimpleUUID();
        String refreshToken = IdUtil.fastSimpleUUID();
        LoginCacheDto cacheDto = new LoginCacheDto();
        cacheDto.setId(accountExist.getId());
        cacheDto.setAccountName(accountExist.getAccountName().getName());
        cacheDto.setToken(token);
        cacheDto.setRefreshToken(refreshToken);
        cacheFacade.saveCache(buildTokenKey(token), cacheDto, TOKEN_TIMEOUT);
        cacheFacade.saveCache(buildRefreshTokenKey(refreshToken), cacheDto, REFRESH_TOKEN_TIMEOUT);
        return cacheDto;
    }

    @Override
    public CommonResp<LoginCacheDto> tokenExchangeUserId(String token) {
        LoginCacheDto cacheDto = cacheFacade.getCache(buildTokenKey(token), LoginCacheDto.class);
        if (Objects.nonNull(cacheDto)) {
            return CommonResp.buildSuccess(cacheDto);
        }
        return CommonResp.buildError("token 无效");
    }
}
