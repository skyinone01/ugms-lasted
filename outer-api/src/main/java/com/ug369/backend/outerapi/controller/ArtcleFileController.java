package com.ug369.backend.outerapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Roy on 2017/5/8.
 */
@Controller
public class ArtcleFileController {
    //从application中读取配置，如取不到默认值为hello jack
    @Value("${application.hello:hello jack}")
    private String hello;


    @RequestMapping(value = "/jsp/controller", method = RequestMethod.GET)
    public String controller(Map<String, Object> map){
        System.out.println("HelloController.helloJsp().hello=");
//        map.put("hello", hello);
        return "controller";
    }



    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String, Object> map){
        System.out.println("HelloController.helloJsp().hello="+hello);
        map.put("hello", hello);
        return "helloJsp";
    }
}
