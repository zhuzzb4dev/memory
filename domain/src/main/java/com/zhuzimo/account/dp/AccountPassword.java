package com.zhuzimo.account.dp;

import cn.hutool.crypto.SecureUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

    public AccountPassword(String password, String salt) {
        if (StringUtils.isBlank(salt)) {
            this.password = password;
        } else {
            this.password = SecureUtil.sha1(SecureUtil.sha1(password) + salt);
        }
    }


}
