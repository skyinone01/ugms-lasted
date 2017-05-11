package com.ug369.backend.service.service;

import com.alibaba.druid.util.StringUtils;
import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.TotalStatistics;
import com.ug369.backend.service.entity.mysql.UserAgeStatistics;
import com.ug369.backend.service.entity.mysql.UserCountStatistics;
import com.ug369.backend.service.entity.mysql.UserDeviceStatistics;
import com.ug369.backend.service.entity.mysql.UserModuleStatistics;
import com.ug369.backend.service.entity.mysql.UserSexStatistics;
import com.ug369.backend.service.repository.mysql.StatisticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    
    public List<UserCountStatistics> selectPvCountYear(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectPvCountYear", dateMap);
    }

    public List<UserCountStatistics> selectPvCountMonth(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectPvCountMonth", dateMap);
    }

    public List<UserCountStatistics> selectPvCountDay(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectPvCountDay", dateMap);
    }
    
    /**
     * 获取模块年月日统计信息
     * @param data
     * @param dateMap
     * @return
     */
    public List<UserCountStatistics> selectPvCount(String data,Map dateMap) {
        return statisticsRepository.getData(data, dateMap);
    }

    public List<UserDeviceStatistics> selectDevice() {
        return statisticsRepository.getData("Statistics.selectDevice", null);
    }

    public List<UserModuleStatistics> selectModule() {
        return statisticsRepository.getData("Statistics.selectModule", null);
    }
    
    /**
     * 查询pv相关数据
     * @param dateMap
     * @return
     */
    public List<UserModuleStatistics> selectPvModule(Map dateMap) {
        return statisticsRepository.getData("Statistics.selectPvModule", dateMap);
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
    
    public Map<String, Object> statsExportUserAge(String start, String end) {
        Map<String, Object> clos = new HashMap<>();
        Map<String, String> params = new HashMap<String, String>();
        if(!StringUtils.isEmpty(start)){
        	params.put("startDate", start);
        }
        if(!StringUtils.isEmpty(end)){
        	params.put("endDate", end);
        }
        
        Integer totalUsers = 0;
        List<Map<String, Object>> list = statisticsRepository.getData("Statistics.userSexSts", params);
        for(Map<String, Object> item : list){
            String users = null==item.get("users") ? "0" : item.get("users").toString();
            clos.put(item.get("userSex").toString(), users);
            totalUsers += Integer.valueOf(users);
        }
        clos.put("用户总数", totalUsers);
        List<Map<String, Object>> ageList = statisticsRepository.getData("Statistics.userAgeSts", params);
        for(Map<String, Object> item : ageList){
            clos.put(null==item.get("userAgeRange") ? "未知" : item.get("userAgeRange").toString(), null==item.get("users") ? 0 : item.get("users").toString());
        }
        return clos;
    }
    
    public Map<String, Object> statsExportUserAdd(String start, String end) {
        Map<String, Object> cols = new HashMap<>();
        Integer totalUsers = 0;
        Map<String, String> params = new HashMap<String, String>();
        if(!StringUtils.isEmpty(start)){
        	params.put("startDate", start);
        }
        if(!StringUtils.isEmpty(end)){
        	params.put("endDate", end);
        }
        List<Map<String, Object>> list = statisticsRepository.getData("Statistics.userAddSts", params);
        for(Map<String, Object> item : list){
            String users = null==item.get("users") ? "0" : item.get("users").toString();
            cols.put(item.get("createTime").toString(), item.get("users").toString());
            totalUsers += Integer.valueOf(users);
        }
        cols.put("用户总数", totalUsers);
        return cols;
    }
    
    public Map<String, Object> statsExportModuleAcc(String start, String end) {
        Map<String, Object> cols = new HashMap<>();
        Map<String, String> params = new HashMap<String, String>();
        if(!StringUtils.isEmpty(start)){
        	params.put("startDate", start);
        }
        if(!StringUtils.isEmpty(end)){
        	params.put("endDate", end);
        }
        List<Map<String, Object>> list = statisticsRepository.getData("Statistics.moduleAccSts", params);
        for(Map<String, Object> item : list){
            cols.put(item.get("moduleName").toString(), item.get("accCounts").toString());
        }
        return cols;
    }
    
    public Map<String, Object> statsExportTerminalRang(String start, String end) {
        Map<String, Object> cols = new HashMap<>();
        Integer totalUsers = 0;
        Map<String, String> params = new HashMap<String, String>();
        if(!StringUtils.isEmpty(start)){
        	params.put("startDate", start);
        }
        if(!StringUtils.isEmpty(end)){
        	params.put("endDate", end);
        }
        List<Map<String, Object>> list = statisticsRepository.getData("Statistics.terminalRangSts", params);
        for(Map<String, Object> item : list){
            String users = null==item.get("users") ? "0" : item.get("users").toString();
            cols.put(item.get("modelNumber").toString(), users);
            totalUsers += Integer.valueOf(users);
        }
        cols.put("用户总数", totalUsers);
        return cols;
    }
    
    public PagedResult<UserCountStatistics> getActiveUserList(PageRequest pageRequest) {
    	
        PagedResult<UserCountStatistics> user = statisticsRepository.getDataPageBatch("Statistics.statsActiveUsers", "Statistics.getActiveUsersCount", new HashMap<>(), pageRequest);
        return user;
    }
    
    public PagedResult<UserCountStatistics> getActiveModuleList(PageRequest pageRequest) {
    	
        PagedResult<UserCountStatistics> user = statisticsRepository.getDataPageBatch("Statistics.statsActiveModules", "Statistics.getActiveModulesCount", new HashMap<>(), pageRequest);
        return user;
    }
    
    public PagedResult<UserCountStatistics> getActiveDeviceList(PageRequest pageRequest) {
    	
        PagedResult<UserCountStatistics> user = statisticsRepository.getDataPageBatch("Statistics.statsActiveDevices", "Statistics.getActiveDevicesCount", new HashMap<>(), pageRequest);
        return user;
    }
}
