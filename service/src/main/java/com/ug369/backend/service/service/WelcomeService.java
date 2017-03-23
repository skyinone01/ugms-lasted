package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.response.WelcomeEntry;
import com.ug369.backend.bean.result.PagedResult;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class WelcomeService {

    public PagedResult<WelcomeEntry> getAll(PageRequest pageRequest) {
        return null;
    }
}
