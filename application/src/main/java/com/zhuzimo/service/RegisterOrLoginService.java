package com.zhuzimo.service;

import com.zhuzimo.dto.RegisterOrLoginDto;
import com.zhuzimo.command.LoginCommand;
import com.zhuzimo.dto.CommonResp;
import com.zhuzimo.command.RegisterCommand;

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
    CommonResp<RegisterOrLoginDto> register(@Valid RegisterCommand req);

    /**
     * 登录
     *
     * @param req 要求
     * @return {@link CommonResp}<{@link RegisterOrLoginDto}>
     */
    CommonResp<RegisterOrLoginDto> login(LoginCommand req);

    /**
     * 令牌交换用户 ID
     *
     * @param token 令 牌
     * @return {@link CommonResp}<{@link Long}>
     */
    CommonResp<Long> tokenExchangeUserId(@NotBlank String token);



}
