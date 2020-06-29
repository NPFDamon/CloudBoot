package com.cloud.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:15:54
 */
@Data
public class Result implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultCode.COMMON_SUCCESS.getCode());
        result.setMsg(ResultCode.COMMON_SUCCESS.getMsg());
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCode.COMMON_SUCCESS.getCode());
        result.setMsg(ResultCode.COMMON_SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        return result;
    }

    public static Result failure(ResultCode resultCode, String msg) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMsg(msg);
        return result;
    }

}
