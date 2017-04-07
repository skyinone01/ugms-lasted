package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.bean.request.ContentRequest;
import com.ug369.backend.bean.bean.request.TypeRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Content;
import com.ug369.backend.service.repository.mysql.MessageHelpShareRepository;
import com.ug369.backend.service.repository.mysql.TypeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class MessageHelpShareService {

    @Autowired
    private MessageHelpShareRepository messageHelpShareRepository;

    @Autowired
    private TypeRepository typeRepository;

    public PagedResult<Content> getAll(PageRequest pageRequest, WebUser user, String category) {

        Map param = new HashMap<>();
        param.put("category",category);
        param.put("userId",user.getId());
        PagedResult<Content> welcomes = messageHelpShareRepository.getDataPageBatch("Message.getAll", "Message.getCount", param,pageRequest);
        return welcomes;
    }

    @Transactional
    public void delete(int id) {
        messageHelpShareRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(ContentRequest request) {
        Content one;
        if (request.getId()!=null && request.getId() != 0){
            one = messageHelpShareRepository.findOne(request.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            one = new Content();
            one.setCreateDate(new Date());
            one.setUserId(request.getUserId());
        }
        if (request.getStatus()!=null){
            one.setStatus(request.getStatus());
        }

        if (!StringUtils.isEmpty(request.getContext())){
            one.setContext(request.getContext());
        }
        if (!StringUtils.isEmpty(request.getTitle())){
            one.setTitle(request.getTitle());
        }
        if (!StringUtils.isEmpty(request.getSummary())){
            one.setSummary(request.getSummary());
        }
        if (!StringUtils.isEmpty(request.getCategory())){
            one.setCategory(request.getCategory());
        }
        if (!StringUtils.isEmpty(request.getTypeStr())){
            one.setTypeStr(request.getTypeStr());
        }
        if (request.getTypeId() != null){
            one.setTypeId(request.getTypeId());
        }
        if (!StringUtils.isEmpty(request.getPictures())){
            one.setPictures(request.getPictures());
        }
        if (!StringUtils.isEmpty(request.getLinkUrl())){
            one.setLinkUrl(request.getLinkUrl());
            one.setLink(true);
        }
        if (!StringUtils.isEmpty(request.getIcon())){
            one.setIcon(request.getIcon());
        }
        if (request.getOrderId() != null){
            one.setOrderId(request.getOrderId());
        }

        messageHelpShareRepository.save(one);
    }

    public ContentRequest findOne(int id) {
        ContentRequest one = messageHelpShareRepository.getObject("Message.getOne", id);
//        Content one = messageHelpShareRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND);
        }
        return one;
    }


    @Transactional
    public void changeStatus(int id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Content one = messageHelpShareRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND,"修改项不在了");
        }
        one.setStatus(op);
        messageHelpShareRepository.save(one);
    }

    public List<TypeRequest> getTypes() {
        List<TypeRequest> data = messageHelpShareRepository.getData("Message.getTypes", new HashMap<>());
        return data;
    }
}
