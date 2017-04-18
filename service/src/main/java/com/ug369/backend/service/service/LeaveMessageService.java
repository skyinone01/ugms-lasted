package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.BannerRequest;
import com.ug369.backend.bean.bean.request.BasicRequest;
import com.ug369.backend.bean.bean.request.LeaveMessageRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Banner;
import com.ug369.backend.service.repository.mysql.BannerRepository;
import com.ug369.backend.service.repository.mysql.BasicRepository;
import com.ug369.backend.service.repository.mysql.LeaveMessageRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

@Service
public class LeaveMessageService {

    @Autowired
    private LeaveMessageRepository leaveMessageRepository;
    

    public PagedResult<LeaveMessageRequest> getAll(PageRequest pageRequest, String userName) {
    	HashMap<String, String> param = new HashMap<String, String>();
    	if(!StringUtils.isEmpty(userName)&&!userName.equals("null")){
    		param.put("userName", userName);
    	}
        PagedResult<LeaveMessageRequest> leaveMessage = leaveMessageRepository.getDataPageBatch("LeaveMessage.getAll", "LeaveMessage.getCount", param,pageRequest);
        return leaveMessage;
    }
    
    public LeaveMessageRequest findOne(long userId) {
    	LeaveMessageRequest one = leaveMessageRepository.getObject("LeaveMessage.findOne", userId);
        return one;
    }
    
    
    @Transactional
    public void update(LeaveMessageRequest request) {
    	leaveMessageRepository.updateData("LeaveMessage.update", request);
    }

}
