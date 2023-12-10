package com.zhuzimo.converter;

import com.zhuzimo.account.aggregate.Account;
import com.zhuzimo.po.AccountPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountConverter {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "accountName.name"),
            @Mapping(target = "password", source = "accountPassword.password"),
    })
    AccountPo do2Po(Account account);

}
