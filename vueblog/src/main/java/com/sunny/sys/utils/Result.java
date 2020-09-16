package com.sunny.sys.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Sunny
 * @Date: 2020/6/15
 * @Description:
 */
@Data
public class Result<T> implements Serializable {
    /**
     * @Fields code : 返回状态码
     */
    private String code;

    /**
     * @Fields msg : 返回消息描述
     */
    private String msg;

    /**
     * @Fields data : 返回实体数据
     */
    private T data;

    /**
     * <p>Title: </p>
     * <p>Description: 异常返回时的返回构造描述</p>
     *
     * @param status
     * @param msg
     */
    public Result(String status, String msg) {
        this.code = status;
        this.msg = msg;
    }

}
