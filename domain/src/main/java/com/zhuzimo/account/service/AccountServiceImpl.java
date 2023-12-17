package com.zhuzimo.account.service;

import com.zhuzimo.account.entity.Account;
import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;
import com.zhuzimo.account.repository.AccountRepository;

import javax.annotation.Resource;

/**
 * 用户服务实现
 *
 * @author t3
 * @date 2023/11/30
 */
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public Account queryByName(AccountName name) {
        return accountRepository.queryByName(name);
    }

    @Override
    public Account save(AccountName name, AccountPassword accountPassword) {
        return accountRepository.save(new Account(name, accountPassword));
    }

    @Override
    public Account queryByNameAndPassword(AccountName accountName, AccountPassword accountPassword) {
        return accountRepository.queryByNameAndPassword(accountName, accountPassword);
    }
}
