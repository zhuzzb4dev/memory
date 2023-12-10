package com.zhuzimo.service;

import cn.hutool.core.util.IdUtil;
import com.zhuzimo.account.aggregate.Account;
import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;
import com.zhuzimo.account.service.AccountService;
import com.zhuzimo.dto.RegisterOrLoginDto;
import com.zhuzimo.account.repository.AccountRepository;
import com.zhuzimo.command.LoginCommand;
import com.zhuzimo.dto.CommonResp;
import com.zhuzimo.dto.LoginCacheDto;
import com.zhuzimo.common.exception.BizException;
import com.zhuzimo.command.RegisterCommand;
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
    private AccountRepository accountRepository;

    @Resource
    private CacheFacade cacheFacade;

    private static final Long TIMEOUT = 24 * 60 * 60L;

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
    public CommonResp<RegisterOrLoginDto> register(@Valid RegisterCommand req) {
        boolean locked = cacheFacade.lock(buildLockKey(req.getName()), LOCK_TIMEOUT);
        if (!locked) {
            return CommonResp.buildError("请求处理中，请勿重复请求");
        }
        AccountName accountName = new AccountName(req.getName());
        Account accountExist = accountService.queryByName(accountName);
        if (Objects.nonNull(accountExist)) {
            throw new BizException("用户名已经存在");
        }
        Account savedAccount  = accountService.save(accountName, new AccountPassword(req.getPassword(), salt));
        String token = IdUtil.fastSimpleUUID();
        String refreshToken = IdUtil.fastSimpleUUID();
        LoginCacheDto cacheDto = new LoginCacheDto();
        cacheDto.setId(savedAccount.getId());
        cacheFacade.saveCache(buildTokenKey(token), cacheDto, TIMEOUT);
        cacheFacade.unLock(buildLockKey(req.getName()));
        return CommonResp.buildSuccess(new RegisterOrLoginDto(token, refreshToken));
    }

    @Override
    public CommonResp<RegisterOrLoginDto> login(LoginCommand req) {
        AccountName accountName = new AccountName(req.getName());
        Account accountExist = accountRepository.queryByName(accountName);
        if (Objects.isNull(accountExist)) {
            throw new BizException("用户名不存在");
        }
        accountExist = accountRepository.queryByNameAndPassword(accountName, new AccountPassword(req.getPassword(), salt));
        if (Objects.isNull(accountExist)) {
            throw new BizException("密码不正确");
        }
        String token = IdUtil.fastSimpleUUID();
        String refreshToken = IdUtil.fastSimpleUUID();
        LoginCacheDto cacheDto = new LoginCacheDto();
        cacheDto.setId(accountExist.getId());
        cacheFacade.saveCache(buildTokenKey(token), cacheDto, TIMEOUT);
        return CommonResp.buildSuccess(new RegisterOrLoginDto(token, refreshToken));
    }

    @Override
    public CommonResp<Long> tokenExchangeUserId(String token) {
        LoginCacheDto cacheDto = cacheFacade.getCache(buildTokenKey(token), LoginCacheDto.class);
        if (Objects.nonNull(cacheDto)) {
            return CommonResp.buildSuccess(cacheDto.getId());
        }
        return CommonResp.buildError("token 无效");
    }
}
