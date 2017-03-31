package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.UserRole;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roy on 2017/3/23.
 */
@Repository
public interface UserRoleRepository extends RDBRepository<UserRole, Long> {

    UserRole findByUser(long userId);

    List<UserRole> findByRole(long rid);
}
