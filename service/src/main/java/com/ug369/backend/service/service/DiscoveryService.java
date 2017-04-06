package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.DiscoveryRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Discovery;
import com.ug369.backend.service.repository.mysql.DiscoveryRepository;
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
public class DiscoveryService {

    @Autowired
    private DiscoveryRepository discoveryRepository;

    public PagedResult<Discovery> getAll(PageRequest pageRequest) {

        PagedResult<Discovery> discovery = discoveryRepository.getDataPageBatch("Discovery.getAll", "Discovery.getCount", new HashMap<>(),pageRequest);
        return discovery;
    }

    @Transactional
    public void delete(long id) {
        discoveryRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(DiscoveryRequest request) {
        Discovery one;
        if (request.getId()!=null && request.getId() != 0){
            one = discoveryRepository.findOne(request.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            one = new Discovery();
            one.setCreateDate(new Date());
        }

        if (request.getStatus()!=null){
            one.setStatus(request.getStatus());
        }
        if (!StringUtils.isEmpty(request.getTitle())){
            one.setTitle(request.getTitle());
        }
        if (!StringUtils.isEmpty(request.getContext())){
            one.setContext(request.getContext());
        }
        if (!StringUtils.isEmpty(request.getSummary())){
            one.setSummary(request.getSummary());
        }
        if (!StringUtils.isEmpty(request.getIcon())){
            one.setIcon(request.getIcon());
        }
        if (request.getTypeId()!=null){
            one.setTypeId(request.getTypeId());
        }
        if (request.getIsLink()!=null){
            one.setIsLink(request.getIsLink());
        }
        if (!StringUtils.isEmpty(request.getLinkUrl())){
            one.setLinkUrl(request.getLinkUrl());
        }
        if (request.getOrderId()!=null){
            one.setOrderId(request.getOrderId());
        }

        discoveryRepository.save(one);
    }

    public Discovery findOne(long id) {
        Discovery one = discoveryRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND);
        }
        return one;
    }


    @Transactional
    public void changeStatus(long id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Discovery one = discoveryRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND);
        }
        one.setStatus(op);
        discoveryRepository.save(one);
    }
}
