package com.ug369.backend.outerapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.service.entity.mysql.Statistics;
import com.ug369.backend.service.entity.mysql.TotalStatistics;
import com.ug369.backend.service.entity.mysql.UserAgeStatistics;
import com.ug369.backend.service.entity.mysql.UserCountStatistics;
import com.ug369.backend.service.entity.mysql.UserDeviceStatistics;
import com.ug369.backend.service.entity.mysql.UserModuleStatistics;
import com.ug369.backend.service.entity.mysql.UserSexStatistics;
import com.ug369.backend.service.service.StatisticsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService service;

    /*@RequestMapping()
    public List<Statistics> selectGroup() {
        List<Statistics> countryList = service.selectGroup();
        return countryList;
    }*/

    @RequestMapping(value = "/statistic/total")
    public DataResponse selectTotal() {
        Map result = new HashMap();
        List<TotalStatistics> countryList = service.selectTotal();
        result.put("other", countryList.get(0).getCount());
        result.put("ios", countryList.get(1).getCount());
        result.put("android", countryList.get(2).getCount());
        result.put("newUser", countryList.get(3).getCount());
        result.put("totalUser", countryList.get(4).getCount());
        result.put("sex1", countryList.get(5).getCount());
        result.put("sex2", countryList.get(6).getCount());
        result.put("qq", countryList.get(7).getCount());
        result.put("weChat", countryList.get(8).getCount());
        return new DataResponse(result);
    }

    @RequestMapping("/statistic/age")
    public DataResponse selectAge(String startDate,String endDate) {
        Map result = new HashMap();
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserAgeStatistics> countryList = service.selectAge(dateMap);
        if(countryList.size()>0){
        result.put("0", countryList.get(0).getCount());
        result.put("1", countryList.get(1).getCount());
        result.put("2", countryList.get(2).getCount());
        result.put("3", countryList.get(3).getCount());
        result.put("4", countryList.get(4).getCount());
        result.put("5", countryList.get(5).getCount());
        result.put("6", countryList.get(6).getCount());
        }else {
            result.put("0", 0);
            result.put("1", 0);
            result.put("2", 0);
            result.put("3", 0);
            result.put("4", 0);
            result.put("5", 0);
            result.put("6", 0);
        }
        return new DataResponse(result);
    }

    @RequestMapping("/statistic/count-year")
    public DataResponse<UserCountStatistics> selectCountYear(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectCountYear(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/count-month")
    public DataResponse<UserCountStatistics> selectCountMonth(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectCountMonth(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/count-day")
    public DataResponse<UserCountStatistics> selectCountDay(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectCountDay(dateMap);
        return new DataResponse(countryList);
    }
    
    @RequestMapping("/statistic/pv-count-year")
    public DataResponse<UserCountStatistics> selectPvCountYear(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectPvCountYear(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/pv-count-month")
    public DataResponse<UserCountStatistics> selectPvCountMonth(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectPvCountMonth(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/pv-count-day")
    public DataResponse<UserCountStatistics> selectPvCountDay(String startDate,String endDate) {
        Map dateMap = new HashMap();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<UserCountStatistics> countryList = service.selectPvCountDay(dateMap);
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/device")
    public DataResponse<UserDeviceStatistics> selectDevice() {
        List<UserDeviceStatistics> countryList = service.selectDevice();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/module")
    public DataResponse<UserModuleStatistics> selectModule() {
        List<UserModuleStatistics> countryList = service.selectModule();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/sex")
    public DataResponse<UserSexStatistics> selectSex() {
        List<UserSexStatistics> countryList = service.selectSex();
        return new DataResponse(countryList);
    }


    @RequestMapping("/statistic/active-user")
    public DataResponse<UserCountStatistics> selectActiveUser() {
        List<UserCountStatistics> countryList = service.selectActiveUser();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/active-module")
    public DataResponse<UserCountStatistics> selectActiveModule() {
        List<UserCountStatistics> countryList = service.selectActiveModule();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/active-device")
    public DataResponse<UserCountStatistics> selectActiveDevice() {
        List<UserCountStatistics> countryList = service.selectActiveDevice();
        return new DataResponse(countryList);
    }

    @RequestMapping("/statistic/uv")
    public DataResponse<UserCountStatistics> selectUv() {
        List<UserCountStatistics> countryList = service.selectUv();
        return new DataResponse(countryList);
    }

}
