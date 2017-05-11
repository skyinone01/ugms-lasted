package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Basic;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepository extends RDBRepository<Basic, Long> {


}
