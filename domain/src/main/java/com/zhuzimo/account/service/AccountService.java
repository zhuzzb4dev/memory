package com.zhuzimo.account.service;

import com.zhuzimo.account.entity.Account;
import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;

/**
 * 用户服务
 *
 * @author t3
 * @date 2023/11/28
 */
public interface AccountService {

    /**
     * 按名称查询
     *
     * @param accountName 帐户名称
     * @return {@link Account}
     */
    Account queryByName(AccountName accountName);

    /**
     * 按名称和密码查询
     *
     * @param accountPassword 帐户密码
     * @param accountName     帐户名称
     * @return {@link Account}
     */
    Account queryByNameAndPassword(AccountName accountName, AccountPassword accountPassword);

    /**
     * 救
     *
     * @param accountPassword 使用 salt 帐户密码
     * @param accountName     帐户名称
     * @return {@link Account}
     */
    Account save(AccountName accountName, AccountPassword accountPassword);
}
