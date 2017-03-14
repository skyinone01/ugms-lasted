package com.ugms.backend.bean.base.response;

import com.ugms.backend.bean.result.PagedResult;

/**
 * 请求请求回复分页数据消息体
 * Created by Roy on 2017/3/10.
 */
public class PagedDataResponse<T> extends DataResponse {

    public PagedDataResponse(PagedResult<T> pagedResult) {
        this.data = pagedResult;
    }

    public static <T> PagedDataResponse of(PagedResult<T> result) {
        return new PagedDataResponse(result);
    }
}
