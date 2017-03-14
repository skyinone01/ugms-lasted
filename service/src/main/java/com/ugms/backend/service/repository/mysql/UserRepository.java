package com.ugms.backend.service.repository.mysql;

import com.ugms.backend.service.repository.rdbsupport.RDBRepository;
import com.ugms.backend.service.entity.mysql.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by roy on 2017/3/10.
 */
@Repository
public interface UserRepository extends RDBRepository<User, Long> {
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
	boolean existsByUsername(@Param("username")String username);

	User findByUsernameAndPassword(String username,String password);
}
