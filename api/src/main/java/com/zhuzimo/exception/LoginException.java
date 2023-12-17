package com.zhuzimo.exception;

/**
 * 登录异常
 *
 * @author t3
 * @date 2023/11/28
 */
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
