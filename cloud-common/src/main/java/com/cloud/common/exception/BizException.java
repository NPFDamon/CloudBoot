package com.cloud.common.exception;

/**
 * Copyright (C),Damon
 *
 * @Description:
 * @Author: Damon(npf)
 * @Date: 2020-06-29:16:40
 */
public class BizException extends RuntimeException{
    public BizException(String errorMsg, Object... args) {
        super(String.format(errorMsg, args));
    }

    public BizException(String errorMessage, Exception cause, Object... args) {
        super(String.format(errorMessage, args), cause);
    }

    public BizException(Exception cause) {
        super(cause.getMessage(), cause);
    }
}
