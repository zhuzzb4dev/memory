package com.zhuzimo.account.service;

import com.zhuzimo.account.aggregate.Account;
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
     * @param name 名字
     * @return {@link Account}
     */
    Account queryByName(AccountName name);

    /**
     * 救
     *
     * @param name                    名字
     * @param accountPassword 使用 salt 帐户密码
     * @return {@link Account}
     */
    Account save(AccountName name, AccountPassword accountPassword);
}
