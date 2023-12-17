package com.zhuzimo.account.dp;

import com.zhuzimo.common.util.AssertUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 帐户密码已加盐
 *
 * @author t3
 * @date 2023/11/28
 */
@Getter
@Setter
public class AccountPasswordSalted {

    private String password;

    public AccountPasswordSalted(String password) {
        AssertUtil.assertNotBlank(password, "password 不能为空");
        this.password = password;
    }


}
