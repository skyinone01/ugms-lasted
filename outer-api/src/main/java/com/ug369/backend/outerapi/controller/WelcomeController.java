package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.response.WelcomeEntry;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.Welcome;
import com.ug369.backend.service.service.WelcomeService;
import com.ug369.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

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
    @RequestMapping(value = "/welcome", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse welcome( @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "id") Long id,
                                  @RequestParam(value = "useable",required = false) Integer useable,
                                  @RequestParam(value = "begin_date",required = false) String begin_date,
                                  @RequestParam(value = "orders",required = false) Integer orders,
                                  @RequestParam(value = "status",required = false) Integer status,
                                  @RequestParam(value = "file",required = false) MultipartFile file) throws NoSuchAlgorithmException, IOException, ParseException {

        if(!StringUtils.isPicture(file.getOriginalFilename())){
            throw  new UserException(UgmsStatus.BAD_REQUEST,"图片格式不符合要求");
        }

        ByteBuffer buffer = ByteBuffer.wrap(file.getBytes());
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(buffer);
        byte[] md5sum = messageDigest.digest();

        String name = StringUtils.MD5ToString(md5sum) +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File img = new File(filePath + name);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(img, file.getBytes(),false);

        Date begin = null;
        if (!org.apache.commons.lang3.StringUtils.isEmpty(begin_date)){
            begin = SimpleDateFormat.getDateInstance().parse(begin_date);
        }
        welcomeService.createOrUpdate(id,staticUrl+name,title,useable,begin,orders,status);

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

    /**
     * 详情
     */
    @RequestMapping(value = "/welcome/{id}", method = RequestMethod.GET)
    public DataResponse<Welcome> welcomeOne(@PathVariable("id") long id) {

        Welcome response = welcomeService.findOne(id);
        return new DataResponse<>(response);
    }

}
