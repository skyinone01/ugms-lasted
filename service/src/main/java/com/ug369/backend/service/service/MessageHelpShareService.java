package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.ContentRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Content;
import com.ug369.backend.service.repository.mysql.MessageHelpShareRepository;
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
public class MessageHelpShareService {

    @Autowired
    private MessageHelpShareRepository messageHelpShareRepository;

    public PagedResult<Content> getAll(PageRequest pageRequest) {

        PagedResult<Content> welcomes = messageHelpShareRepository.getDataPageBatch("Message.getAll", "Message.getCount", new HashMap<>(),pageRequest);
        return welcomes;
    }

    @Transactional
    public void delete(long id) {
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
        if (!StringUtils.isEmpty(request.getTypeStr())){
            one.setTypeStr(request.getTypeStr());
        }
        if (request.getTypeId() != null){
            one.setTypeId(request.getTypeId());
        }
        if (!StringUtils.isEmpty(request.getPictures())){
            one.setPictures(request.getPictures());
        }
        if (!StringUtils.isEmpty(request.getIcon())){
            one.setIcon(request.getIcon());
        }
        if (request.getOrderId() != null){
            one.setOrderId(request.getOrderId());
        }

        messageHelpShareRepository.save(one);
    }

    public Content findOne(long id) {
        Content one = messageHelpShareRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND);
        }
        return one;
    }


    @Transactional
    public void changeStatus(long id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Content one = messageHelpShareRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND,"修改项不在了");
        }
        one.setStatus(op);
        messageHelpShareRepository.save(one);
    }
}
