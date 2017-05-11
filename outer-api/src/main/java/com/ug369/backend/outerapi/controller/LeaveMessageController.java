package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.LeaveMessageRequest;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.service.BasicService;
import com.ug369.backend.service.service.LeaveMessageService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

        String startDateTime = "";
        String endDateTime = "";
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

            if(!StringUtils.isEmpty(startDate)){

                startDateTime = sdf.format(sdf2.parse(startDate));
            }
            if(!StringUtils.isEmpty(endDate)){

                Date end =sdf2.parse(endDate);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(end);
                calendar.add(calendar.DATE, 1);
                endDateTime = sdf.format(calendar.getTime());
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PagedResult<LeaveMessageRequest> leaveMessage = leaveMessageService.getAll(pageRequest, userName, startDateTime, endDateTime);

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
