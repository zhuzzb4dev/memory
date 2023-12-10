package com.zhuzimo.mapper;


import com.zhuzimo.po.AccountPo;

/**
 * 帐户 PO 映射器
 *
 * @author t3
 * @date 2023/11/28
 */
public interface AccountPoMapper {

    /**
     * 插入
     *
     * @param record 记录
     * @return {@link Long}
     */
    Long insert(AccountPo record);

    /**
     * 按主键选择
     *
     * @param id 编号
     * @return {@link AccountPo}
     */
    AccountPo selectByPrimaryKey(Long id);

    /**
     * 按名称选择
     *
     * @param name 名字
     * @return {@link AccountPo}
     */
    AccountPo selectByName(String name);

    /**
     * 按名称和密码选择
     *
     * @param name     名字
     * @param password 密码
     * @return {@link AccountPo}
     */
    AccountPo selectByNameAndPassword(String name, String password);
}
