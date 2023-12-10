package com.zhuzimo.common.exception;

/**
 * 无效 arg 异常
 *
 * @author t3
 * @date 2023/11/28
 */
public class InvalidArgException extends RuntimeException {


    public InvalidArgException(String message) {
        super(message);
    }
}
