package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Banner;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roy on 2017/3/27.
 */
@Repository
public interface BannerRepository extends RDBRepository<Banner, Integer> {


}
