package com.zhuzimo.converter;

import com.zhuzimo.account.entity.Account;
import com.zhuzimo.po.AccountPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 帐户转换器
 *
 * @author t3
 * @date 2023/12/11
 */
@Mapper(componentModel = "spring")
public interface AccountConverter {

    /**
     * DO2 PO
     *
     * @param account 帐户
     * @return {@link AccountPo}
     */
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "accountName.name"),
            @Mapping(target = "password", source = "accountPassword.password"),
    })
    AccountPo do2Po(Account account);

}
