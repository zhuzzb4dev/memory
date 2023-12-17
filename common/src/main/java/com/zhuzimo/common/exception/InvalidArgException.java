package com.zhuzimo.common.exception;

/**
 * 无效 arg 异常
 *
 * @author t3
 * @date 2023/12/12
 */
public class InvalidArgException extends RuntimeException {
    /**
     * 无效 arg 异常
     *
     * @param message 消息
     */
    public InvalidArgException(String message) {
        super(message);
    }
}
