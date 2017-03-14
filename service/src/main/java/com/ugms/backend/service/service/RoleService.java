package com.ugms.backend.service.service;

import com.ugms.backend.service.repository.mysql.RoleRepository;
import com.ugms.backend.service.entity.mysql.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roy on 2017/3/20.
 */
@Service
public class RoleService {

    public enum Type {UNKNOWN, ADMIN, DEVELOPER, MAINTAINER, MONITOR}

    public enum Status {TRUE, FALSE, ALL}

    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
