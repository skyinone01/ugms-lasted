package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.response.WelcomeEntry;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.service.WelcomeService;
import com.ug369.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @Value("${ugms.content.file.path}")
    private String filePath;

    @Value("${ugms.static.file.prex}")
    private String staticPath;

    /**
     * 欢迎页列表列表
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public PagedDataResponse<WelcomeEntry> welcome(@PageDefault PageRequest pageRequest) {
        PagedResult<WelcomeEntry> users = welcomeService.getAll(pageRequest);

        return PagedDataResponse.of(users);
    }

    /**
     * 新增、修改 欢迎页
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.PUT)
    public BasicResponse welcome( @RequestBody WelcomeEntry welcomeEntry,
                                  @RequestParam(value = "file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
        if(!StringUtils.isPicture(file.getName())){
            throw  new UserException(UgmsStatus.BAD_REQUEST,"图片格式不符合要求");
        }

        ByteBuffer buffer = ByteBuffer.wrap(file.getBytes());
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(buffer);
        byte[] md5sum = messageDigest.digest();

        String name = StringUtils.MD5ToString(md5sum) +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        File img = new File(filePath + "/" + name);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(img, file.getBytes(),false);

        welcomeEntry.setPicture(staticPath+"/"+name);
        welcomeService.createOrUpdate(welcomeEntry);

        return BasicResponse.success();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/welcome/{id}", method = RequestMethod.DELETE)
    public BasicResponse welcome(@PathVariable("id") long id) {

        welcomeService.delete(id);
        return BasicResponse.success();
    }


}
