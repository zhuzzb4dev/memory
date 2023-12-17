package com.zhuzimo.account.entity;

import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;
import com.zhuzimo.account.dp.AccountPasswordSalted;
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
public class Account {

    private Long id;

    private AccountName accountName;

    private AccountPassword accountPassword;

    private AccountPasswordSalted accountPasswordSalted;

    public Account(Long id, AccountName accountName, AccountPassword accountPassword) {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    public Account(Long id, AccountName accountName, AccountPasswordSalted accountPasswordSalted) {
        this.id = id;
        this.accountName = accountName;
        this.accountPasswordSalted = accountPasswordSalted;
    }

    private Account() {

    }

    /**
     * 帐户
     *
     * @param accountName     帐户名称
     * @param accountPassword 帐户密码
     * @return {@link Account}
     */
    public Account(AccountName accountName, AccountPassword accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }
}
