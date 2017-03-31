package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.bean.response.CommonRoleEntry;
import com.ug369.backend.bean.bean.response.RoleEntry;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/18.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表 for select option
     */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public DataResponse<RoleEntry> roleList(@PageDefault PageRequest pageRequest) {

        return new DataResponse(roleService.getRoleList());
    }

    /**
     * 角色列表 for common list
     */
    @RequestMapping(value = "/common/roles", method = RequestMethod.GET)
    public DataResponse<CommonRoleEntry> commonRoleList(@PageDefault PageRequest pageRequest) {

        return new DataResponse(roleService.getAll());
    }

    /**
     * 新增or修改角色
     */
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public BasicResponse roleAdd(@RequestBody CommonRoleEntry entry) {
        roleService.updateOrCreate(entry);
        return BasicResponse.success();
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/roles/{rid}", method = RequestMethod.DELETE)
    public BasicResponse roleDelete( @PathVariable("rid") Long rid) {
        roleService.deleteOne(rid);
        return BasicResponse.success();
    }
}
