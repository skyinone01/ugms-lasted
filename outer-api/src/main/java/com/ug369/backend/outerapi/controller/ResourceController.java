package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by roy on 2017/3/13.
 */
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源列表
     */
    @RequestMapping(value = "/user/resources", method = RequestMethod.GET)
    public DataResponse resources(@UserInjected User user) {
        return new DataResponse(resourceService.findByRole(user.getRole()));
    }
}
