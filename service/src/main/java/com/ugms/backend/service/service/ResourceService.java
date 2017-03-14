package com.ugms.backend.service.service;

import com.ugms.backend.service.entity.mysql.Resource;
import com.ugms.backend.service.repository.mysql.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */
@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> findByRole(long rid){

        List<Resource> data = resourceRepository.getData("Resource.getByUser", rid);

        return data;
    }
}
