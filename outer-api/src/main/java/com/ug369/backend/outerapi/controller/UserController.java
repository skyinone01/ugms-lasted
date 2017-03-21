package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.UserRequest;
import com.ug369.backend.bean.bean.response.UserResponse;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 用户列表 for role
     */
    @RequestMapping(value = "/users/{role_id}", method = RequestMethod.GET)
    public DataResponse<User> userList4Role(@PathVariable("role_id") long roleId) {
         List<User> users = userService.findByRole(roleId);

        return new DataResponse(users);
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
    @RequestMapping(value = "/users/{uid}", method = RequestMethod.DELETE)
    public BasicResponse userDelete( @PathVariable("uid") long uid) {
        userService.delete(uid);
        return BasicResponse.success();
    }
}
