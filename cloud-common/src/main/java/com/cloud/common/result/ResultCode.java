package com.cloud.common.result;

public enum ResultCode {
    COMMON_SUCCESS(200, "success"),
    COMMON_SERVER_ERROR(500, "Server Failed!"),
    COMMON_SERVER_NOT_FOUND(404, "Server not found"),
    UNAUTHORIZED(401, "login fail"),
    FORBIDDEN(403, "please login first"),
    REGISTER_ERROR(201, "register fail");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int code) {
        for (ResultCode r : ResultCode.values()) {
            if (r.getCode() == code) {
                return r.getMsg();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean success() {
        return getCode() == COMMON_SUCCESS.getCode();
    }
}
