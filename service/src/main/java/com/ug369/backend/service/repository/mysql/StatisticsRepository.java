package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.*;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 2017/3/23.
 */
@Repository
public interface StatisticsRepository extends RDBRepository<Statistics, Long> {
//	List<Statistics> selectGroup();
	/*List<TotalStatistics> selectTotal();
    List<UserAgeStatistics> selectAge(Map dateMap);
    List<UserCountStatistics> selectUserCountMonth(Map dateMap);
    List<UserCountStatistics> selectUserCountYear(Map dateMap);
    List<UserCountStatistics> selectUserCountDay(Map dateMap);

    List<UserDeviceStatistics> selectDevice();
    List<UserModuleStatistics> selectModule();
    List<UserSexStatistics> selectSex();

    List<UserCountStatistics> selectActiveUser();
    List<UserCountStatistics> selectActiveModule();
    List<UserCountStatistics> selectActiveDevice();

    List<UserCountStatistics> selectUv();*/
}
