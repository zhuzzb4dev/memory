package com.zhuzimo.aop;

import com.zhuzimo.MemoryConstant;
import com.zhuzimo.dto.CommonResp;
import com.zhuzimo.account.exception.LoginException;
import com.zhuzimo.service.RegisterOrLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author t3
 * @date 2023/11/28
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RegisterOrLoginService registerOrLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new LoginException("token缺失");
        }
        CommonResp<Long> longCommonResp = registerOrLoginService.tokenExchangeUserId(token);
        if (longCommonResp.isSuccess()) {
            request.setAttribute(MemoryConstant.LOGIN_USER_ID, longCommonResp.getData());
        } else {
            throw new LoginException(longCommonResp.getMsg());
        }

        return true;
    }



}
