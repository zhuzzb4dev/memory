package com.zhuzimo.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * login 命令
 *
 * @author t3
 * @date 2023/11/28
 */
@Getter
@Setter
public class LoginCommand {

    @NotBlank(message = "name 不能为空")
    private String name;

    @NotBlank(message = "password 不能为空")
    private String password;
}
