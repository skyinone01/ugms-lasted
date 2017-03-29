package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.response.WelcomeEntry;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Welcome;
import com.ug369.backend.service.repository.mysql.WelcomeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public void createOrUpdate(Long id, String picture, String title, Integer useable, Date begin_date, Integer orders,Integer status) {
        Welcome one;
        if (id!=null && id.longValue() != 0){
            one = welcomeRepository.findOne(id);
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND,"更新对象不存在");
            }
        }else {
            one = new Welcome();
            one.setCreate_date(new Date());
        }

        if (status!=null){
            one.setStatus(status);
        }
        if (!StringUtils.isEmpty(title)){
            one.setTitle(title);
        }
        if (!StringUtils.isEmpty(picture)){
            one.setPicture(picture);
        }
        if(orders!=null){
            one.setOrders(orders);
        }
        if(begin_date!=null){
            one.setBegin_date(begin_date);
        }
        if (useable!=null){
            one.setUseable(useable);
        }

        welcomeRepository.save(one);
    }

    public Welcome findOne(long id) {

        Welcome one = welcomeRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND,"欢迎页不存在");
        }


        return one;
//        WelcomeResponse response = new WelcomeResponse();
//        response.setId(one.getId());
//        response.setContent(one.getContent());
//        response.setPicture(one.getPicture());
//        response.setTitle(one.getTitle());
//        response.setOrders(one.getOrders());

//        return response;
    }


}
