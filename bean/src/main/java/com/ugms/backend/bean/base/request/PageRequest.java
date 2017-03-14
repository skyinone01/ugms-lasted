package com.ugms.backend.bean.base.request;

import com.ugms.backend.bean.exception.UgmsStatus;
import com.ugms.backend.bean.exception.UserException;

/**
 * 分页请求消息体
 * Created by Roy on 2017/3/9.
 */
public class PageRequest {

    private int page;
    private int size;

    private PageRequest(int page, int size) {

        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public static PageRequest of(int page, int size) throws UserException {
        if (page < 1 || size < 0 || size > 100) {
            throw new UserException(UgmsStatus.BAD_REQUEST, "invalid page params");
        }
        return new PageRequest(page, size);
    }
}
