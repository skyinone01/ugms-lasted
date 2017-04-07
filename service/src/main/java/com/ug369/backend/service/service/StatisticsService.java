package com.ug369.backend.service.service;

import com.ug369.backend.service.entity.mysql.Statistics;
import com.ug369.backend.service.entity.mysql.TotalStatistics;
import com.ug369.backend.service.entity.mysql.UserAgeStatistics;
import com.ug369.backend.service.entity.mysql.UserCountStatistics;
import com.ug369.backend.service.entity.mysql.UserDeviceStatistics;
import com.ug369.backend.service.entity.mysql.UserModuleStatistics;
import com.ug369.backend.service.entity.mysql.UserSexStatistics;
import com.ug369.backend.service.repository.mysql.StatisticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    /*public List<Statistics> selectGroup() {
        return statisticsRepository.selectGroup();
    }*/

    public List<TotalStatistics> selectTotal() {
        return statisticsRepository.getData("Statistics.selectTotal", null);
    }

    public List<UserAgeStatistics> selectAge(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectAge", dateMap);
    }

    public List<UserCountStatistics> selectCountYear(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectUserCountYear", dateMap);
    }

    public List<UserCountStatistics> selectCountMonth(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectUserCountMonth", dateMap);
    }

    public List<UserCountStatistics> selectCountDay(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectUserCountDay", dateMap);
    }

    public List<UserDeviceStatistics> selectDevice() {
        return statisticsRepository.getData("Statistics.selectDevice", null);
    }

    public List<UserModuleStatistics> selectModule() {
        return statisticsRepository.getData("Statistics.selectModule", null);
    }

    public List<UserSexStatistics> selectSex() {
        return statisticsRepository.getData("Statistics.selectSex", null);
    }

    public List<UserCountStatistics> selectActiveUser() {
        return statisticsRepository.getData("Statistics.selectActiveUser", null);
    }

    public List<UserCountStatistics> selectActiveModule() {
        return statisticsRepository.getData("Statistics.selectActiveModule", null);
    }

    public List<UserCountStatistics> selectActiveDevice() {
        return statisticsRepository.getData("Statistics.selectActiveDevice", null);
    }

    public List<UserCountStatistics> selectUv(){
        return statisticsRepository.getData("Statistics.selectUv", null);
    }
}
