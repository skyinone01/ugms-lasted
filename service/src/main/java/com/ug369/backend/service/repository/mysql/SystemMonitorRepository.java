package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.SystemMonitor;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMonitorRepository extends RDBRepository<SystemMonitor, Long> {

}
