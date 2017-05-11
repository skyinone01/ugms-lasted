package com.ug369.backend.outerapi.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by hc on 2017/3/1.
 */
@RestController
@CrossOrigin
@RequestMapping("/upload")
public class UploadFileController {
    private final static int    DEFAULT_WIDTH = 640;
    private final static String BASE_IMG       = "/../uploadImg/";

    @RequestMapping(value = "/pictrues/{type}", method = RequestMethod.POST)
    @ResponseBody
    public Map upload(HttpServletRequest request,@PathVariable String type) throws IOException {
        Map<String, Object> data = new HashMap<>();
        try {
            if (!(request instanceof MultipartHttpServletRequest))
                return null;

            Map<String, MultipartFile> multipartFileMap = ((MultipartHttpServletRequest) request).getFileMap();
            if (multipartFileMap != null) {
                Map<String, String> urls = new HashMap<>();
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {

                    MultipartFile multipartFile = entry.getValue();

                    File file = new File(request.getSession().getServletContext().getRealPath(BASE_IMG)+"/"+type, UUID.randomUUID().toString().replaceAll("-", "") + ".jpg");
                    if (!file.exists()) {
                        if (!file.getParentFile().exists())
                            file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                    //urls.put(entry.getKey(), "/.."+BASE_IMG + file.getName());
                    data.put("result_value", "/.."+BASE_IMG+type+"/"+ file.getName());
                }
            }
            data.put("result_code","SUCCESS");
        }catch (Exception e){
            data.put("result_code","FAIL");
            return data;
        }
        return data;
    }

    public static String[] transMap2Array(Map<String, String> urls){
        List<String> keys = new ArrayList<String>(urls.keySet());
        Collections.sort(keys);
        List<String> data = new ArrayList<>();

        for(String key : keys){
            data.add(urls.get(key));
        }
        return data.toArray(new String[data.size()]);
    }
}
