package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.service.service.SystemMonitorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roy on 2017/4/20.
 */
@RestController
public class SystemtMonitorController {

    @Autowired
    private SystemMonitorService systemMonitorService;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public DataResponse monitor(@RequestParam(value = "date",required = false) String  datestr) throws ParseException {

        Date date = null;
        if (!StringUtils.isEmpty(datestr)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
             date = simpleDateFormat.parse(datestr);
        }

        return new DataResponse(systemMonitorService.getMonitor(date));
    }
}
