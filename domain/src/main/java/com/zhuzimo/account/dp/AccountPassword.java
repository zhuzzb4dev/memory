package com.zhuzimo.account.dp;

import cn.hutool.crypto.SecureUtil;
import com.zhuzimo.common.util.AssertUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 帐户密码
 *
 * @author t3
 * @date 2023/11/28
 */
@Getter
@Setter
public class AccountPassword {

    private String password;

    public static final int MAX_NAME_LENGTH = 32;

    public AccountPassword(String password, String salt) {
        AssertUtil.assertNotBlank(password, "password 不能为空");
        AssertUtil.assertNotBlank(salt, "salt 不能为空");
        AssertUtil.assertLengthLessThan(password, MAX_NAME_LENGTH, "password 长度不能超过" + MAX_NAME_LENGTH);
        this.password = SecureUtil.sha1(SecureUtil.sha1(password) + salt);
    }


}
