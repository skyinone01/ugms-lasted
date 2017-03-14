package com.ugms.backend.service.repository.mysql;

import com.ugms.backend.service.entity.mysql.RoleResource;
import com.ugms.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roy on 2017/3/10.
 */
@Repository
public interface RoleResourceRepository extends RDBRepository<RoleResource, Long> {

}
