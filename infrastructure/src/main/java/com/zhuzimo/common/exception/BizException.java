package com.zhuzimo.common.exception;

/**
 * biz 异常
 *
 * @author t3
 * @date 2023/11/28
 */
public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }
}
