package com.zhuzimo.common.util;

import com.zhuzimo.common.exception.InvalidArgException;
import org.apache.commons.lang3.StringUtils;

/**
 * 断言实用程序
 *
 * @author t3
 * @date 2023/12/12
 */
public class AssertUtil {

    /**
     * 断言不为空
     *
     * @param str     str
     * @param message 消息
     */
    public static void assertNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new InvalidArgException(message);
        }
    }

    /**
     * 断言长度小于
     * 断言长度不超过阈值
     *
     * @param str     str
     * @param length  长度
     * @param message 消息
     */
    public static void assertLengthLessThan(String str, int length, String message) {
        if (str.length() > length) {
            throw new InvalidArgException(message);
        }
    }
}
