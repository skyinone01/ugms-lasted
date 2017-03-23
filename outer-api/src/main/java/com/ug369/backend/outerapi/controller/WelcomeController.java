package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.response.WelcomeEntry;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    /**
     * 用户列表
     */
    @RequestMapping(value = "/welcomes", method = RequestMethod.GET)
    public PagedDataResponse<WelcomeEntry> userList(@PageDefault PageRequest pageRequest) {
        PagedResult<WelcomeEntry> users = welcomeService.getAll(pageRequest);

        return PagedDataResponse.of(users);
    }
}
