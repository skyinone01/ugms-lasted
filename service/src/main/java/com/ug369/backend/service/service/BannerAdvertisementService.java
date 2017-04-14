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
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class BannerAdvertisementService {

    @Autowired
    private BannerRepository bannerRepository;

    public PagedResult<BannerRequest> getAll(PageRequest pageRequest,int type) {

        Map param = new HashMap<>();
        param.put("type",type);
        PagedResult<BannerRequest> welcomes = bannerRepository.getDataPageBatch("Banner.getAll", "Banner.getCount", param,pageRequest);
        return welcomes;
    }

    @Transactional
    public void delete(Integer id) {
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

        if (request.getStatus()!=null && (request.getStatus()==2 || request.getStatus()==3)){
            one.setStatus(request.getStatus());
            one.setApplydetail(request.getApplyDetail());
            one.setApplypeople(request.getName());
        }else {
            one.setStatus(1);
        }
        if (request.getIsBanner() != null &&request.getIsBanner()==1){
            one.setBanner(true);
        }else {
            one.setBanner(false);
        }

        if (request.getIsdefault() != null){
            one.setIsdefault(request.getIsdefault());
        }else {
            one.setIsdefault(false);
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
            one.setName(request.getTitle());
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

        if (request.getOrderId() != null){
            one.setOrderId(request.getOrderId());
        }else {
            one.setOrderId(0);
        }
        if (request.getType()!=null){
            one.setType(request.getType());
        }
        if (request.getWeight()!=null){
            one.setWeight(request.getWeight());
        }
        bannerRepository.save(one);
    }

    public Banner findOne(Integer id) {
        Banner one = bannerRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND,"Banner页不存在");
        }
        return one;
    }


    @Transactional
    public void changeStatus(Integer id, int op) {
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
