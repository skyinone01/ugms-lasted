package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.response.WelcomeEntry;
import com.ug369.backend.bean.bean.response.WelcomeResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Welcome;
import com.ug369.backend.service.repository.mysql.WelcomeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class WelcomeService {

    @Autowired
    private WelcomeRepository welcomeRepository;

    public PagedResult<WelcomeEntry> getAll(PageRequest pageRequest) {

        PagedResult<WelcomeEntry> welcomes = welcomeRepository.getDataPageBatch("Welcome.getAll", "Welcome.getCount", new HashMap<>(),pageRequest);
        return welcomes;
    }

    @Transactional
    public void delete(long id) {
        welcomeRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(WelcomeEntry welcomeEntry) {
        Welcome one;
        if (welcomeEntry!=null && welcomeEntry.getId()!=0){
            one = welcomeRepository.findOne(welcomeEntry.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND,"更新对象不存在");
            }
        }else {
            one = new Welcome();
        }

        if (!StringUtils.isEmpty(welcomeEntry.getContent())){
            one.setContent(welcomeEntry.getContent());
        }
        if (!StringUtils.isEmpty(welcomeEntry.getTitle())){
            one.setTitle(welcomeEntry.getTitle());
        }
        if (!StringUtils.isEmpty(welcomeEntry.getPicture())){
            one.setPicture(welcomeEntry.getPicture());
        }
        if(welcomeEntry.getOrders()!=0){
            one.setOrder(welcomeEntry.getOrders());
        }
        if(welcomeEntry.getStatus()!=0){
            one.setStatus(welcomeEntry.getStatus());
        }
        if (welcomeEntry.getUseable()!=0){
            one.setUseable(welcomeEntry.getUseable());
        }

        welcomeRepository.save(one);
    }

    public WelcomeResponse findOne(long id) {

        Welcome one = welcomeRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND,"欢迎页不存在");
        }

        WelcomeResponse response = new WelcomeResponse();
        response.setId(one.getId());
        response.setContent(one.getContent());
        response.setPicture(one.getPicture());
        response.setTitle(one.getTitle());
        response.setOrders(one.getOrder());

        return response;
    }
}
