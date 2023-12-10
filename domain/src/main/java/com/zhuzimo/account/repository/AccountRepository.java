package com.zhuzimo.account.repository;

import com.zhuzimo.account.aggregate.Account;
import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;

/**
 * 帐户存储库
 *
 * @author t3
 * @date 2023/11/28
 */
public interface AccountRepository {

    /**
     * 按名称查询
     *
     * @param accountName 帐户名称
     * @return {@link Account}
     */
    Account queryByName(AccountName accountName);

    /**
     * 救
     *
     * @param account 帐户
     * @return {@link Account}
     */
    Account save(Account account);

    /**
     * 按名称和密码查询
     *
     * @param accountName     帐户名称
     * @param accountPassword 帐户密码
     * @return {@link Account}
     */
    Account queryByNameAndPassword(AccountName accountName, AccountPassword accountPassword);
}
