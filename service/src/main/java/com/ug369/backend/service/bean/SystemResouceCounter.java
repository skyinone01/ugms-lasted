package com.ug369.backend.service.bean;

import com.ug369.backend.service.entity.mysql.SystemMonitor;
import com.ug369.backend.service.repository.mysql.SystemMonitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Roy on 2017/4/18.
 */
@Component
@EnableAsync
@EnableScheduling
public class SystemResouceCounter {

    private static Logger loger = LoggerFactory.getLogger(SystemResouceCounter.class);

    @Autowired
    private SystemMonitorRepository monitorRepository;

//    @Scheduled(fixedDelay = 900000)
    private void monitor(){

        //memory
        List<String> processList = execute("free -h");
        String[] splits = processList.get(1).trim().split("\\s+");
        SystemMonitor memory = new SystemMonitor();
        memory.setDatepoint(new Date());
        double t1 = Double.parseDouble(splits[1].substring(0, splits[1].length() - 1));
        double v1 = Double.parseDouble(splits[2].substring(0, splits[2].length() - 1));
        memory.setTotal(BigDecimal.valueOf(t1).setScale(2,BigDecimal.ROUND_UP).doubleValue());
        memory.setUseage(BigDecimal.valueOf(v1).setScale(2,BigDecimal.ROUND_UP).doubleValue());
        memory.setType(2);
        monitorRepository.save(memory);

        //cpu
        processList = execute("sar -u 1 1");
        String[] cpus = processList.get(0).trim().split("\\s+");
        String[] cpuuse = processList.get(processList.size()-1).trim().split("\\s+");
        SystemMonitor cpu = new SystemMonitor();
        cpu.setDatepoint(new Date());
        double v2 = Double.parseDouble(cpus[cpus.length - 2].substring(1));
        cpu.setTotal(BigDecimal.valueOf(v2).setScale(2,BigDecimal.ROUND_UP).doubleValue());
        Double v = Double.parseDouble(cpuuse[cpuuse.length - 1]);
        Double t = Double.parseDouble("100");
        double sub = BigDecimal.valueOf(t).setScale(2,BigDecimal.ROUND_UP).subtract(BigDecimal.valueOf(v).setScale(2,BigDecimal.ROUND_UP)).doubleValue();
        cpu.setUseage(sub);
        cpu.setType(1);
        monitorRepository.save(cpu);


        //network
        processList = execute("sar -n DEV 1 1");
        String[] networks = processList.get(processList.size()-2).trim().split("\\s+");
        Double recv = Double.parseDouble(networks[4]);
        Double tran = Double.parseDouble(networks[5]);
        SystemMonitor network = new SystemMonitor();
        network.setDatepoint(new Date());
        network.setTotal(4);
        double add = BigDecimal.valueOf(recv).setScale(2,BigDecimal.ROUND_UP).add(BigDecimal.valueOf(tran)).setScale(2,BigDecimal.ROUND_UP).doubleValue();
        network.setUseage(add);
        network.setType(3);
        monitorRepository.save(network);

        //disk
        processList = execute("df -h");
        String[] disks = processList.get(1).trim().split("\\s+");
        SystemMonitor disk = new SystemMonitor();
        disk.setDatepoint(new Date());
        double t4 = BigDecimal.valueOf(Double.parseDouble(disks[1].substring(0,disks[1].length()-1))).setScale(2,BigDecimal.ROUND_UP).doubleValue();
        double u4 = BigDecimal.valueOf(Double.parseDouble(disks[2].substring(0,disks[2].length()-1))).setScale(2,BigDecimal.ROUND_UP).doubleValue();
        disk.setTotal(t4);
        disk.setUseage(u4);
        disk.setType(4);
        monitorRepository.save(disk);

    }

    private List<String> execute(String cmd){
        Process process;
        List<String> processList = new ArrayList<>();
        try {
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            loger.error("execute cmd error ", e);
        }
        return processList;
    }


}
