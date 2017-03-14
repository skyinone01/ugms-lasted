package com.ugms.backend.bean.base.response;

import com.ugms.backend.bean.exception.UgmsStatus;

/**
 *
 * 请求请求回复数据消息体
 * Created by Roy on 2017/3/8.
 */
public class DataResponse<T> extends BasicResponse {

    protected T data;

    protected <T> DataResponse() {
    }

    public DataResponse(T data) {
        this.errno = UgmsStatus.SUCCESS.getErrorNo();
        this.error = UgmsStatus.SUCCESS.getError();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    private static <T> DataResponse of() {
        return null;
    }
}
