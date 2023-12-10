package com.zhuzimo.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * 通用 RESP
 *
 * @param <T> t
 * @author t3
 * @date 2023/11/22
 */
@Getter
@Setter
public class CommonResp <T> {

    private static final Integer DEFAULT_SUCCESS_CODE = 200;

    private static final Integer DEFAULT_ERROR_CODE = 500;

    private Integer code;

    private String msg;

    private T data;

    /**
     * 构建成功
     * @param <T> t
     * @param data 数据
     * @return {@link CommonResp}<{@link T}>
     */
    public static <T> CommonResp<T> buildSuccess(T data) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setCode(DEFAULT_SUCCESS_CODE);
        resp.setData(data);
        return resp;
    }

    /**
     * 构建成功
     * @param <T> t
     * @return {@link CommonResp}
     */
    public static <T> CommonResp<T> buildSuccess() {
        return buildSuccess(null);
    }

    /**
     * 生成错误
     *
     * @param msg 味精
     * @param <T> t
     * @return {@link CommonResp}
     */
    public static <T> CommonResp<T> buildError(String msg) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setCode(DEFAULT_ERROR_CODE);
        resp.setMsg(msg);
        return resp;
    }

    public boolean isSuccess() {
        return this.code == DEFAULT_SUCCESS_CODE;
    }
}
