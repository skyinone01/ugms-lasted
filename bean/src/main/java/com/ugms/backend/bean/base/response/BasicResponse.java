package com.ugms.backend.bean.base.response;

import com.ugms.backend.bean.exception.UgmsStatus;

/**
 * 接口请求回复基本消息体
 * Created by roy on 2017/3/8.
 */

public class BasicResponse {

    protected int errno;
    protected String error;

    private final static BasicResponse instance = new BasicResponse();

    public BasicResponse() {
        this.errno = 0;
        this.error = "succ";
    }

    public BasicResponse(int errno, String error) {
        this.errno = errno;
        this.error = error;
    }

    public BasicResponse(UgmsStatus status) {
        this.errno = status.getErrorNo();
        this.error = status.getError();
    }

    public BasicResponse(UgmsStatus status, String message) {
        this.errno = status.getErrorNo();
        this.error = status.getError() + ": " + message;
    }

    //getter不能缺少，jackson需要

    public int getErrno() {
        return errno;
    }

    public String getError() {
        return error;
    }

    public static BasicResponse success() {
        return instance;
    }

    @Override
    public String toString() {
        return "{\"errno:\"" + errno + ",\"error\":\"" + error + "\"}";
    }
}
