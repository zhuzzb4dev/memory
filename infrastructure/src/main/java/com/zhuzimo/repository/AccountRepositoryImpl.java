package com.zhuzimo.repository;

import com.zhuzimo.account.dp.AccountPasswordSalted;
import com.zhuzimo.account.entity.Account;
import com.zhuzimo.account.dp.AccountName;
import com.zhuzimo.account.dp.AccountPassword;
import com.zhuzimo.account.repository.AccountRepository;
import com.zhuzimo.converter.AccountConverter;
import com.zhuzimo.mapper.AccountPoMapper;
import com.zhuzimo.po.AccountPo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 帐户存储库 IMPL
 *
 * @author t3
 * @date 2023/11/28
 */
@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Resource
    private AccountPoMapper accountPoMapper;

    @Resource
    private AccountConverter accountConverter;

    @Override
    public Account queryByName(AccountName accountName) {
        AccountPo accountPo = accountPoMapper.selectByName(accountName.getName());
        return po2do(accountPo);
    }

    @Override
    public Account save(Account account) {
        AccountPo po = accountConverter.do2Po(account);
        accountPoMapper.insert(po);
        AccountPo accountPo = accountPoMapper.selectByPrimaryKey(po.getId());
        return po2do(accountPo);
    }

    @Override
    public Account queryByNameAndPassword(AccountName accountName, AccountPassword accountPassword) {
        AccountPo accountPo = accountPoMapper.selectByNameAndPassword(accountName.getName(), accountPassword.getPassword());
        return po2do(accountPo);
    }

    /**
     * PO2DO
     *
     * @param po 邮政
     * @return {@link Account}
     */
    private Account po2do(AccountPo po) {
        if (Objects.isNull(po)) {
            return null;
        }
        return new Account(po.getId(), new AccountName(po.getName()), new AccountPasswordSalted(po.getPassword()));
    }
}
