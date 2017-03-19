package com.ug369.backend.service.service;

import com.ug369.backend.bean.bean.response.CommonRoleEntry;
import com.ug369.backend.bean.bean.response.RoleEntry;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.service.entity.mysql.Role;
import com.ug369.backend.service.repository.mysql.RoleRepository;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 2017/3/20.
 */
@Service
public class RoleService {

//    public enum Type {UNKNOWN, ADMIN, DEVELOPER, MAINTAINER, MONITOR}
//
//    public enum Status {TRUE, FALSE, ALL}

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }


    public List<RoleEntry> getRoleList() {
        Iterable<Role> all = roleRepository.findAll();
        if (all != null){
            ArrayList roles = new ArrayList();
            RoleEntry entry;
            Object[] objects = IteratorUtils.toArray(all.iterator());
            if (objects!= null && objects.length >0){
                for(Object o : objects){
                    entry = new RoleEntry();
                    entry.setText(((Role)o).getName());
                    entry.setValue(((Role)o).getId());
                    roles.add(entry);
                }
            }
            return roles;
        }
        return null;
    }

    public List<CommonRoleEntry> getAll() {
        Iterable<Role> all = roleRepository.findAll();
        if (all != null){
            ArrayList roles = new ArrayList();
            CommonRoleEntry entry;
            Object[] objects = IteratorUtils.toArray(all.iterator());
            if (objects!= null && objects.length >0){
                for(Object o : objects){
                    entry = new CommonRoleEntry();
                    entry.setId(((Role)o).getId());
                    entry.setName(((Role)o).getName());
                    entry.setCode(((Role)o).getCode());
                    entry.setDescription(((Role)o).getDescription());
                    roles.add(entry);
                }
            }
            return roles;
        }
        return null;
    }

    public void deleteOne(long rid){
        roleRepository.delete(rid);
    }

    public void updateOrCreate(CommonRoleEntry entry){
        if (entry.getId() == 0L){
            Role role = new Role();
            role.setDescription(entry.getDescription());
            role.setName(entry.getName());
            role.setCode(entry.getCode());
            roleRepository.save(role);
        }else {
            Role role = roleRepository.findOne(entry.getId());
            if (role == null){
                throw new UserException(UgmsStatus.NOT_FOUND," 角色"+entry.getName());
            }
            if (!StringUtils.isEmpty(entry.getName())){
                role.setName(entry.getName());
            }
            if (!StringUtils.isEmpty(entry.getDescription())){
                role.setDescription(entry.getDescription());
            }
            if (!StringUtils.isEmpty(entry.getCode())){
                role.setCode(entry.getCode());
            }
            roleRepository.save(role);
        }
    }
}
