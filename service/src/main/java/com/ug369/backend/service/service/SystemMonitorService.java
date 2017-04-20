package com.ug369.backend.service.service;

import com.ug369.backend.service.entity.mysql.SystemMonitor;
import com.ug369.backend.service.repository.mysql.SystemMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Roy on 2017/4/20.
 */
@Service
public class SystemMonitorService {

    @Autowired
    private SystemMonitorRepository systemMonitorRepository;

    public Map<String,List> getMonitor(Date date){

        Map param = new HashMap<>();
        Calendar c1 = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
        if (date != null){
            cal.setTime(date);
            c1.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH));
        }
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        param.put("start",c1.getTime());
        if (date !=null){
            cal.set(Calendar.HOUR_OF_DAY,23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            param.put("end",cal.getTime());
        }else {
            param.put("end",new Date());
        }
        List<SystemMonitor> data = systemMonitorRepository.getData("Monitor.getData", param);

        List cpu = new ArrayList<>();
        List memory = new ArrayList<>();
        List network = new ArrayList<>();
        List disk = new ArrayList<>();
        data.forEach(o->{
            switch (o.getType()){
                case 1:
                    cpu.add(o);
                    break;
                case 2:
                    memory.add(o);
                    break;
                case 3:
                    network.add(o);
                    break;
                case 4:
                    disk.add(o);
                    break;
            }
        });
        Map result = new HashMap<>();
        result.put("cpu",cpu);
        result.put("memory",memory);
        result.put("network",network);
        result.put("disk",disk);
        return result;
    }
}
