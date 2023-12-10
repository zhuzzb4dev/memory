package com.zhuzimo.account.dp;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 帐户名称
 *
 * @author t3
 * @date 2023/11/28
 */
@Getter
@Setter
public class AccountName {

    public static final int MAX_NAME_LENGTH = 10;
    private String name;

    public AccountName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name 不能为空");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("name 长度不能超过10");
        }

        this.name = name;
    }

}
