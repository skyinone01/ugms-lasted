package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.BannerRequest;
import com.ug369.backend.bean.bean.request.BasicRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Banner;
import com.ug369.backend.service.entity.mysql.Basic;
import com.ug369.backend.service.entity.mysql.Instructions;
import com.ug369.backend.service.repository.mysql.BannerRepository;
import com.ug369.backend.service.repository.mysql.BasicRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/12.
 */
@Service
public class BasicService {

    @Autowired
    private BasicRepository basicRepository;

    public PagedResult<BasicRequest> getAll(PageRequest pageRequest, String userName) {
    	HashMap<String, String> param = new HashMap<String, String>();
    	if(!StringUtils.isEmpty(userName)&&!userName.equals("null")){
    		param.put("userName", userName);
    	}
    	
        PagedResult<BasicRequest> basic = basicRepository.getDataPageBatch("Basic.getAll", "Basic.getCount", param, pageRequest);
        return basic;
    }

    public BasicRequest findOne(long userId) {
    	BasicRequest one = basicRepository.getObject("Basic.findOne", userId);
        return one;
    }
    
    @Transactional
    public void update(BasicRequest request) {
    	basicRepository.updateData("Basic.updateU", request);
    	basicRepository.updateData("Basic.updateUb", request);
    	basicRepository.updateData("Basic.updateUh", request);
    }
    
    @Transactional
    public void updateStatus(BasicRequest request) {
    	basicRepository.updateData("Basic.updateStatus", request);
    }
    
    public List<Map<String, Object>> statsExportUsers(String userName, String startDate, String endDate) {
    	HashMap<String, String> param = new HashMap<String, String>();
        
        if(!StringUtils.isEmpty(userName)&& !userName.equals("null")){
    		param.put("userName", userName);
    	}
        if(!StringUtils.isEmpty(startDate) && !startDate.equals("null")){
        	param.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate) && !endDate.equals("null")){
        	param.put("endDate", endDate);
        }
        
        return basicRepository.getData("Basic.statsExportUsers", param);
    }
    
}
