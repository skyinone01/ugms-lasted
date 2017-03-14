package com.ugms.backend.service.repository.rdbsupport;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by Roy on 2017/3/29.
 */
@NoRepositoryBean
public interface RDBRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, MybatisSupport {

}
