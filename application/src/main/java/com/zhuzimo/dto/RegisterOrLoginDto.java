package com.zhuzimo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 注册或登录 DTO
 *
 * @author t3
 * @date 2023/11/28
 */
@Getter
@Setter
public class RegisterOrLoginDto {
    private String token;
    private String refreshToken;

    public RegisterOrLoginDto(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
