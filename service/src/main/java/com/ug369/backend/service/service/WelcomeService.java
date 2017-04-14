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
    public void delete(Integer id) {
        welcomeRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(Integer id, String picture, String title, Boolean useable,
                               Date begin_date, Integer orders,Integer status,String applyDetail,String applyPeople) {
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

        if (status!=null && (status==2 || status==3)){
            one.setStatus(status);
            one.setApplydetail(applyDetail);
            one.setApplypeople(applyPeople);
        }else {
            one.setStatus(1);
        }
        if (!StringUtils.isEmpty(title)){
            one.setContent(title);
        }
        if (!StringUtils.isEmpty(picture)){
            one.setPicture(picture);
        }
        if(orders!=null){
            one.setA_order(orders);
        }
        if(begin_date!=null){
            one.setBegin_date(begin_date);
        }
        if (useable!=null){
            one.setUseable(useable);
        }

        welcomeRepository.save(one);
    }

    public Welcome findOne(Integer id) {

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


    @Transactional
    public void changeStatus(Integer id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Welcome one = welcomeRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND,"此欢迎页不在了");
        }
        one.setStatus(op);
        welcomeRepository.save(one);
    }
}
