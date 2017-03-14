package com.ugms.backend.service.repository.mysql;

import com.ugms.backend.service.entity.mysql.Resource;
import com.ugms.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roy on 2017/3/10.
 */
@Repository
public interface ResourceRepository extends RDBRepository<Resource, Long> {

	Resource findById(long id);
}
