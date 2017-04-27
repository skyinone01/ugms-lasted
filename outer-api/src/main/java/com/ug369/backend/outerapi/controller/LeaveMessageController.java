package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.BannerRequest;
import com.ug369.backend.bean.bean.request.BasicRequest;
import com.ug369.backend.bean.bean.request.LeaveMessageRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.Banner;
import com.ug369.backend.service.service.BannerAdvertisementService;
import com.ug369.backend.service.service.BasicService;
import com.ug369.backend.service.service.LeaveMessageService;
import com.ug369.backend.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class LeaveMessageController {

    @Autowired
    private BasicService basicService;
    
    @Autowired
    private LeaveMessageService leaveMessageService;
    
    /**
     * basic页列表列表
     */
    @RequestMapping(value = "/leaveMessage/selectList")
    public PagedDataResponse<LeaveMessageRequest> leaveMessageList(@PageDefault PageRequest pageRequest, String userName, String startDate, String endDate) {

        PagedResult<LeaveMessageRequest> leaveMessage = leaveMessageService.getAll(pageRequest, userName, startDate, endDate);

        return PagedDataResponse.of(leaveMessage);
    }
    
    /**
     * 详情
     */
    @RequestMapping(value = "/leaveMessage/{id}", method = RequestMethod.GET)
    public DataResponse<LeaveMessageRequest> leaveMessageOne(@PathVariable("id") long id) {

    	LeaveMessageRequest response = leaveMessageService.findOne(id);
        return new DataResponse<>(response);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/leaveMessage", method = RequestMethod.POST)
    public BasicResponse update( @RequestParam(value = "id") long id,
                                  @RequestParam(value = "replayContent") String replayContent) throws NoSuchAlgorithmException, IOException, ParseException {

        LeaveMessageRequest leaveMessageRequest = new LeaveMessageRequest();
        leaveMessageRequest.setId(id);
        leaveMessageRequest.setReplayContent(replayContent);
        leaveMessageRequest.setIsreplay(true);
        
        leaveMessageService.update(leaveMessageRequest);
        return BasicResponse.success();
    }
}
