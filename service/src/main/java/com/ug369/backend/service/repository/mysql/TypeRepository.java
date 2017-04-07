package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Type;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roy on 2017/3/27.
 */
@Repository
public interface TypeRepository extends RDBRepository<Type, Integer> {


    List<Type> findByIsdel(Boolean b);
}
