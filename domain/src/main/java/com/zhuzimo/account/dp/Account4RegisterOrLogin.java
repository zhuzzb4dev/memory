package com.zhuzimo.account.dp;

import lombok.Getter;
import lombok.Setter;

/**
 * 帐户
 *
 * @author t3
 * @date 2023/11/28
 */
@Getter
@Setter
public class Account4RegisterOrLogin {

    private AccountName accountName;

    private AccountPassword accountPassword;

    /**
     * 帐户
     *
     * @param accountName     帐户名称
     * @param accountPassword 帐户密码
     * @return {@link Account4RegisterOrLogin}
     */
    public Account4RegisterOrLogin(AccountName accountName, AccountPassword accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }
}
