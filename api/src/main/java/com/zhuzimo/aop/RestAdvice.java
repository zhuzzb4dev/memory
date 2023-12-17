package com.zhuzimo.aop;

import com.zhuzimo.common.CommonResp;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 休息建议
 *
 * @author t3
 * @date 2023/11/28
 */
@RestControllerAdvice
public class RestAdvice {

    /**
     * 处理异常
     *
     * @param e e
     * @return {@link CommonResp}
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResp<?> handleException(RuntimeException e) {
        e.printStackTrace();
        return CommonResp.buildError(e.getMessage());
    }

    /**
     * 处理异常
     *
     * @param e e
     * @return {@link CommonResp}
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResp<?> handleException(IllegalArgumentException e) {
        return CommonResp.buildError(e.getMessage());
    }


    /**
     * 处理异常
     *
     * @param e e
     * @return {@link CommonResp}<{@link ?}>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResp<?> handleException(MethodArgumentNotValidException e) {
        return CommonResp.buildError(
                e.getBindingResult().getAllErrors().stream().findFirst().orElse(new ObjectError("", ERROR_MSG)).getDefaultMessage());
    }
    /**
     * 处理异常
     *
     * @param e e
     * @return {@link CommonResp}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResp<?> handleException(ConstraintViolationException e) {
        return CommonResp.buildError(e.getMessage());
    }

    private static final String ERROR_MSG = "参数校验异常";
}
