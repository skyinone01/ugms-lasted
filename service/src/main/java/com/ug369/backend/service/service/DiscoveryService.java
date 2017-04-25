package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.DiscoveryRequest;
import com.ug369.backend.bean.bean.request.DiscoveryTypeRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Discovery;
import com.ug369.backend.service.entity.mysql.Type;
import com.ug369.backend.service.repository.mysql.DiscoveryRepository;
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
public class DiscoveryService {

    @Autowired
    private DiscoveryRepository discoveryRepository;

    @Autowired
    private TypeRepository typeRepository;

    public PagedResult<DiscoveryRequest> getAll(PageRequest pageRequest) {

        PagedResult<DiscoveryRequest> discovery = discoveryRepository.getDataPageBatch("Discovery.getAll", "Discovery.getCount", new HashMap<>(),pageRequest);


        List<Map<String,Object>> data = discoveryRepository.getData("Discovery.getType", new HashMap<>());
        discovery.getItems().forEach(o-> data.forEach(t->{
            if(t.get("id") ==o.getTypeId()){
                o.setTypeStr((String)t.get("name"));
            }
        }));

        return discovery;
    }

    public List<Map<String,String>> getTypes(){
        return discoveryRepository.getData("Discovery.getType", new HashMap<>());
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

        if (request.getStatus()!=null && (request.getStatus()==2 || request.getStatus()==3)){
            one.setStatus(request.getStatus());
            one.setApplydetail(request.getApplyDetail());
            one.setApplypeople(request.getApplyPeople());
        }else {
            one.setStatus(0);
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
        if (request.getLink()!=null){
            one.setLink(request.getLink());
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
        if (op ==11){
            Discovery newone = new Discovery();
            newone.setStatus(0);
            newone.setTitle(one.getTitle());
            newone.setContext(one.getContext());
            newone.setCreateDate(new Date());
            newone.setIcon(one.getIcon());
            newone.setLink(one.getLink());
            newone.setLinkUrl(one.getLinkUrl());
            newone.setMark(one.getMark());
            newone.setOrderId(one.getOrderId());
            newone.setSummary(one.getSummary());
            newone.setTypeId(one.getTypeId());
            discoveryRepository.save(newone);
        }else {
            one.setStatus(op);
            discoveryRepository.save(one);
        }
    }

    public void deleteType(Integer id) {
        typeRepository.delete(id);
    }

    public void createOrUpdateType(DiscoveryTypeRequest request) {
        Type type = new Type();
        if (request.getId() == null || request.getId() ==0){
            type.setOrderId(request.getOrderId());
            type.setTypeName(request.getName());
            type.setIsdel(false);
            type.setBelongType("0");
        }else {
            type = typeRepository.findOne(request.getId());
            if (!StringUtils.isEmpty(request.getName())){
                type.setTypeName(request.getName());
            }
            if (request.getOrderId() != null){
                type.setOrderId(request.getOrderId());
            }
        }
        typeRepository.save(type);
    }
}
