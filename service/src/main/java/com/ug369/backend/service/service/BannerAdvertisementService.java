package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.BannerRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Banner;
import com.ug369.backend.service.repository.mysql.BannerRepository;
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
public class BannerAdvertisementService {

    @Autowired
    private BannerRepository bannerRepository;

    public PagedResult<BannerRequest> getAll(PageRequest pageRequest) {

        PagedResult<BannerRequest> welcomes = bannerRepository.getDataPageBatch("Banner.getAll", "Banner.getCount", new HashMap<>(),pageRequest);
        return welcomes;
    }

    @Transactional
    public void delete(long id) {
        bannerRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(BannerRequest request) {
        Banner one;
        if (request.getId()!=null && request.getId() != 0){
            one = bannerRepository.findOne(request.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND,"更新对象不存在");
            }
        }else {
            one = new Banner();
            one.setCreateDate(new Date());
        }

        if (request.getStatus()!=null && request.getStatus()!=0){
            one.setStatus(request.getStatus());
        }else {
            one.setStatus(1);
        }
        if (!StringUtils.isEmpty(request.getContactName())){
            one.setContactName(request.getContactName());
        }
        if (!StringUtils.isEmpty(request.getContactPhone())){
            one.setContactPhone(request.getContactPhone());
        }
        if (!StringUtils.isEmpty(request.getContent())){
            one.setContent(request.getContent());
        }
        if (!StringUtils.isEmpty(request.getTitle())){
            one.setTitle(request.getTitle());
        }
        if (!StringUtils.isEmpty(request.getLink())){
            one.setLink(request.getLink());
        }
        if (!StringUtils.isEmpty(request.getName())){
            one.setName(request.getName());
        }
        if (!StringUtils.isEmpty(request.getPicture())){
            one.setPicture(request.getPicture());
        }
        if (request.getBeginDate() != null){
            one.setBeginDate(request.getBeginDate());
        }
        if (request.getEndDate() != null){
            one.setEndDate(request.getEndDate());
        }
        if (request.getIsBanner() != null){
            one.setIsBanner(request.getIsBanner());
        }
        if (request.getIsdefault() != null){
            one.setIsdefault(request.getIsdefault());
        }
        if (request.getOrderId() != null){
            one.setOrderId(request.getOrderId());
        }
        if (request.getType()!=null){
            one.setType(request.getType());
        }
        if (request.getWeight()!=null){
            one.setWeight(request.getWeight());
        }
        bannerRepository.save(one);
    }

    public Banner findOne(long id) {
        Banner one = bannerRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND,"Banner页不存在");
        }
        return one;
    }


    @Transactional
    public void changeStatus(long id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Banner one = bannerRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND,"Banner页不在了");
        }
        one.setStatus(op);
        bannerRepository.save(one);
    }
}
