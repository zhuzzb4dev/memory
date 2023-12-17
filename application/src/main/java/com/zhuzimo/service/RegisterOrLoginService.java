package com.zhuzimo.service;

import com.zhuzimo.dto.LoginCacheDto;
import com.zhuzimo.dto.RegisterOrLoginDto;
import com.zhuzimo.command.LoginCmd;
import com.zhuzimo.common.CommonResp;
import com.zhuzimo.command.RegisterCmd;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 注册或登录服务
 *
 * @author t3
 * @date 2023/11/28
 */
public interface RegisterOrLoginService {


    /**
     * 注册
     *
     * @param req 要求
     * @return {@link CommonResp}<{@link RegisterOrLoginDto}>
     */
    CommonResp<RegisterOrLoginDto> register(@Valid RegisterCmd req);

    /**
     * 登录
     *
     * @param req 要求
     * @return {@link CommonResp}<{@link RegisterOrLoginDto}>
     */
    CommonResp<RegisterOrLoginDto> login(LoginCmd req);

    /**
     * 令牌交换用户 ID
     *
     * @param token 令 牌
     * @return {@link CommonResp}<{@link Long}>
     */
    CommonResp<LoginCacheDto> tokenExchangeUserId(@NotBlank String token);



}
