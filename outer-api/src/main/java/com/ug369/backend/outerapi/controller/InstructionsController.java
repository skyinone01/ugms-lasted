package com.ug369.backend.outerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.Instructions;
import com.ug369.backend.service.entity.mysql.InstructionsXH;
import com.ug369.backend.service.service.InstructionsService;

import java.util.List;


@RestController
public class InstructionsController {
    @Autowired
    private InstructionsService instructionsService;

    @RequestMapping(value = "/instructions", method = RequestMethod.GET)
    public PagedDataResponse<InstructionsXH> instructions(@PageDefault PageRequest pageRequest) {
        PagedResult<InstructionsXH> users = instructionsService.selectXH(pageRequest);

        return PagedDataResponse.of(users);
    }
    
    /*@RequestMapping(value = "/instructions/{offset}/{limit}")
    public List<InstructionsXH> getAll(@PathVariable Integer offset, @PathVariable Integer limit) {
        //PageHelper.offsetPage(offset, limit,true);
        List<InstructionsXH> list = instructionsService.selectXH();
        return list;
    }

    @RequestMapping(value = "/instructions")
    public List<InstructionsXH>  getXHAll() {
        List<InstructionsXH> list = instructionsService.selectXH();
        return list;
    }*/

    @RequestMapping(value = "/instructions/{id}", method = {RequestMethod.GET})
    public Instructions getOne(@PathVariable Integer id) {
        Instructions instructions = instructionsService.findOne(id);
        return instructions;
    }

    @RequestMapping(value = "/instructions/{id}", method = {RequestMethod.POST}, produces = "application/json")
    public BasicResponse update(@PathVariable Integer id, @RequestBody Instructions instructions) {
        instructionsService.update(instructions);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/instructions/save", method = {RequestMethod.POST}, produces = "application/json")
    public BasicResponse save(@RequestBody Instructions instructions) {
        instructionsService.save(instructions);
        return BasicResponse.success();
    }
    

    @RequestMapping(value = "/instructions/{id}", method = {RequestMethod.DELETE})
    public BasicResponse delete(@PathVariable Integer id) {
        instructionsService.delete(id);
        return BasicResponse.success();
    }
    
    
}
