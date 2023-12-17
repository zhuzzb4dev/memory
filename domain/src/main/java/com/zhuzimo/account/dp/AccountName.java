package com.zhuzimo.account.dp;

import com.zhuzimo.common.util.AssertUtil;
import lombok.Getter;
import lombok.Setter;

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
        AssertUtil.assertNotBlank(name, "name 不能为空");
        AssertUtil.assertLengthLessThan(name, MAX_NAME_LENGTH, "name 长度不能超过" + MAX_NAME_LENGTH);
        this.name = name;
    }

}
