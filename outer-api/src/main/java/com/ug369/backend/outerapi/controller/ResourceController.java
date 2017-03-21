package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.response.ResourceEntryUGMS;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by roy on 2017/3/13.
 */
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源列表 for user
     */
    @RequestMapping(value = "/user/resources", method = RequestMethod.GET)
    public DataResponse resources4User(@UserInjected User user) {
        return new DataResponse(resourceService.getPermission(user.getRole()));
    }

    /**
     * 资源列表 for all
     */
    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public PagedDataResponse<ResourceEntryUGMS> resourcesAll(@PageDefault PageRequest pageRequest) {

        PagedResult<ResourceEntryUGMS> all = resourceService.getAll(pageRequest);
        return PagedDataResponse.of(all);
    }

    /**
     * 资源列表 for role
     */
    @RequestMapping(value = "role/resources", method = RequestMethod.GET)
    public PagedDataResponse<ResourceEntryUGMS> resources4Role(@RequestParam("rid") long rid, @PageDefault PageRequest pageRequest) {
        PagedResult<ResourceEntryUGMS> all = resourceService.getRoleResource(rid,pageRequest);
        return PagedDataResponse.of(all);
    }

    /**
     * 资源列表保存 for role
     */
    @RequestMapping(value = "role/resources", method = RequestMethod.PUT)
    public BasicResponse resources4Role(@RequestParam("rid") long rid, @RequestBody ResourceEntryUGMS resourceEntryUGMS) {
        resourceService.updateRoleResource(rid,resourceEntryUGMS);
        return BasicResponse.success();
    }

    /**
     * 新增or修改资源
     */
    @RequestMapping(value = "/resources", method = RequestMethod.POST)
    public BasicResponse resourceAdd(@RequestBody ResourceEntryUGMS entry) {
        resourceService.updateOrCreate(entry);
        return BasicResponse.success();
    }

    /**
     * 删除资源
     */
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.DELETE)
    public BasicResponse resourceDelete(@PathVariable("id") long id) {
        resourceService.deleteOne(id);
        return BasicResponse.success();
    }
}
