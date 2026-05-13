package com.aion.common.result;

import lombok.Data;

/**
 * 统一结果返回类
 * 遵循阿里开发规范，封装统一的响应格式
 *
 * @param <T> 数据类型
 */
@Data
public class Result<T> {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功返回
     *
     * @param data 数据
     * @param <T>  类型
     * @return 结果对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败返回
     *
     * @param message 错误消息
     * @param <T>     类型
     * @return 结果对象
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }
}
