package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.UserRequest;
import com.ug369.backend.bean.bean.response.UserResponse;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roy on 2017/3/10.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public PagedDataResponse<UserResponse> userList(@PageDefault PageRequest pageRequest) {
        PagedResult<UserResponse> users = userService.getAll(pageRequest);

        return PagedDataResponse.of(users);
    }

    /**
     * 新增or修改用户
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public BasicResponse userAdd(@RequestBody  UserRequest user) {
        userService.createOrUpdate(user);
        return BasicResponse.success();
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public BasicResponse userDelete(UserRequest user) {
        userService.delete(user.getId());
        return BasicResponse.success();
    }
}
