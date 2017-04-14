package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.DescriptionRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Explain;
import com.ug369.backend.service.repository.mysql.DescriptionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class DescriptionService {

    @Autowired
    private DescriptionRepository descriptionRepository;

    public PagedResult<Explain> getAll(PageRequest pageRequest) {

        PagedResult<Explain> explainPagedResult = descriptionRepository.getDataPageBatch("Description.getAll", "Description.getCount", new HashMap<>(),pageRequest);

        List<Map<String,String>> layout = descriptionRepository.getData("Description.getLayout", new HashMap<>());

        explainPagedResult.getItems().forEach(o->{
            layout.forEach(t->{
                if (t.get("code").equals(o.getLayoutCode())){
                    o.setLayoutCode(t.get("name"));
                }
            });
        });

        return explainPagedResult;
    }
    public List<Map<String,String>> getLayout(){
        return descriptionRepository.getData("Description.getLayout", new HashMap<>());
    }

    @Transactional
    public void delete(long id) {
        descriptionRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(DescriptionRequest request) {
        Explain one;
        if (request.getId()!=null && request.getId() != 0){
            one = descriptionRepository.findOne(request.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            one = new Explain();
        }

        if (request.getStatus()!=null &&(request.getStatus()==2 || request.getStatus()==3)){
            one.setStatus(request.getStatus());
            one.setApplydetail(request.getApplyDetail());
            one.setApplypeople(request.getApplyPeople());
        }else{
            one.setStatus(1);
        }
        if (!StringUtils.isEmpty(request.getContent())){
            one.setContent(request.getContent());
        }
        if (!StringUtils.isEmpty(request.getTitle())){
            one.setTitle(request.getTitle());
        }
        if (request.getType()!=null){
            one.setType(request.getType());
        }
        if (request.getUseable()==null && request.getUseable()==false){
            one.setUseable(false);
        }else {
            one.setUseable(true);
        }
        if (!StringUtils.isEmpty(request.getPictures())){
            one.setPictures(request.getPictures());
        }
        if (!StringUtils.isEmpty(request.getLayoutCode())){
            one.setLayoutCode(request.getLayoutCode());
        }

        descriptionRepository.save(one);
    }

    public Explain findOne(long id) {
        Explain one = descriptionRepository.findOne(id);
        if (one == null){
            throw new UserException(UgmsStatus.NOT_FOUND);
        }
        return one;
    }


    @Transactional
    public void changeStatus(long id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Explain one = descriptionRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND);
        }
        one.setStatus(op);
        descriptionRepository.save(one);
    }
}
