package com.zhuzimo.common;


/**
 * 通用 RESP
 *
 * @param <T> t
 * @author t3
 * @date 2023/11/22
 */
public class CommonResp <T> {

    private static final Integer DEFAULT_SUCCESS_CODE = 200;

    private static final Integer DEFAULT_ERROR_CODE = 500;

    private Integer code;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
        return DEFAULT_SUCCESS_CODE.equals(this.code);
    }
}
