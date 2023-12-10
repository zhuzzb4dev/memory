package com.zhuzimo.account.aggregate;

import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;
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

    public Account(Long id, AccountName accountName, AccountPassword accountPassword) {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    private Account() {

    }

    /**
     * build4 新
     *
     * @param name     名字
     * @param password 密码
     * @return {@link Account}
     */
    public static Account build4New(AccountName name, AccountPassword password) {
        Account account = new Account();
        account.setAccountName(name);
        account.setAccountPassword(password);
        return account;
    }
}
