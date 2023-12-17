package com.zhuzimo.controller;

import com.zhuzimo.command.RegisterCmd;
import com.zhuzimo.dto.RegisterOrLoginDto;
import com.zhuzimo.command.LoginCmd;
import com.zhuzimo.common.CommonResp;
import com.zhuzimo.req.LoginReq;
import com.zhuzimo.req.RegisterReq;
import com.zhuzimo.service.RegisterOrLoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author t3
 * @date 2023/11/28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private RegisterOrLoginService registerService;


    /**
     * 注册
     *
     * @param req 要求
     * @return {@link CommonResp}<{@link RegisterOrLoginDto}>
     */
    @PostMapping("register")
    public CommonResp<RegisterOrLoginDto> register(@RequestBody @Validated RegisterReq req) {
        RegisterCmd registerCmd = new RegisterCmd();
        BeanUtils.copyProperties(req, registerCmd);
        return registerService.register(registerCmd);
    }

    /**
     * 登录
     *
     * @param req 要求
     * @return {@link CommonResp}<{@link RegisterOrLoginDto}>
     */
    @PostMapping("login")
    public CommonResp<RegisterOrLoginDto> login(@RequestBody @Validated LoginReq req) {
        LoginCmd loginCmd = new LoginCmd();
        BeanUtils.copyProperties(req, loginCmd);
        return registerService.login(loginCmd);
    }
}
