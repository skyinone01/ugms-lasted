package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.response.PermissionResponse;
import com.ug369.backend.bean.bean.response.ResourceEntryUGMS;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Resource;
import com.ug369.backend.service.entity.mysql.RoleResource;
import com.ug369.backend.service.repository.mysql.ResourceRepository;
import com.ug369.backend.service.repository.mysql.RoleResourceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleResourceRepository roleResourceRepository;

    public List<PermissionResponse> getPermission(long rid){

        List<PermissionResponse> data = resourceRepository.getData("Resource.getByRole", rid);

        return data;
    }

    public PagedResult<ResourceEntryUGMS> getAll(PageRequest pageRequest) {
        Map map = new HashMap();
        return resourceRepository.getDataPageBatch("Resource.getAll", "Resource.getCount", map, pageRequest);
    }


    public void deleteOne(long rid){
        resourceRepository.delete(rid);
    }


    public void updateOrCreate(ResourceEntryUGMS entry) {
        if (entry.getId() == 0L){
            Resource resource = new Resource();
            resource.setDescription(entry.getDescription());
            resource.setName(entry.getName());
            resource.setUrl(entry.getUrl());
            resource.setState(entry.getState());
            resourceRepository.save(resource);
        }else {
            Resource resource = resourceRepository.findOne(entry.getId());
            if (resource == null){
                throw new UserException(UgmsStatus.NOT_FOUND," 角色"+entry.getName());
            }
            if (!StringUtils.isEmpty(entry.getName())){
                resource.setName(entry.getName());
            }
            if (!StringUtils.isEmpty(entry.getDescription())){
                resource.setDescription(entry.getDescription());
            }
            if (!StringUtils.isEmpty(entry.getUrl())){
                resource.setUrl(entry.getUrl());
            }
            if (!StringUtils.isEmpty(entry.getState())){
                resource.setState(entry.getState());
            }
            resourceRepository.save(resource);
        }
    }

    public PagedResult<ResourceEntryUGMS> getRoleResource(long roleId, PageRequest pageRequest) {


        List<RoleResource> byRole = roleResourceRepository.findByRole(roleId);

        Map map = new HashMap();
        PagedResult<ResourceEntryUGMS> dataPageBatch = resourceRepository.getDataPageBatch("Resource.getAll", "Resource.getCount", map, pageRequest);
        dataPageBatch.getItems().forEach( o -> byRole.forEach(or ->{
            if (or.getResource() == o.getId()){
                o.setVisible(true);
                o.setDelete(or.getDeleteable());
                o.setEdit(or.getEditable());
                o.setOperate(or.getOperateable());
                return;
            }
        }));

        return dataPageBatch;

    }

    @Transactional
    public void updateRoleResource(Long roleId,List<ResourceEntryUGMS> resourceEntryUGMS) {


        List<ResourceEntryUGMS> add = new ArrayList<>();
        resourceEntryUGMS.forEach(o->{
            if(o.isVisible()){
                add.add(o);
            }
        });

        roleResourceRepository.deleteByRole(roleId);
        add.forEach(o->{
            RoleResource rr = new RoleResource();
            rr.setResource(o.getId());
            rr.setRole(roleId);
            rr.setDeleteable(o.isDelete());
            rr.setEditable(o.isEdit());
            rr.setOperateable(o.isOperate());
            roleResourceRepository.save(rr);
        });

    }
}
