package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.DescriptionRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.Explain;
import com.ug369.backend.service.service.DescriptionService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class DescriptionController {

    @Autowired
    private DescriptionService descriptionService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    /**
     * 列表
     */
    @RequestMapping(value = "/description", method = RequestMethod.GET)
    public PagedDataResponse<Explain> welcome(@PageDefault PageRequest pageRequest) {
        PagedResult<Explain> users = descriptionService.getAll(pageRequest);

        return PagedDataResponse.of(users);
    }

    /**
     * lay out
     */
    @RequestMapping(value = "/description/layout", method = RequestMethod.GET)
    public DataResponse<List<Map<String, String>>> getLayout() {
        List<Map<String, String>> layout = descriptionService.getLayout();
        return new DataResponse(layout);
    }

    /**
     * 新增、修改 说明
     */
    @RequestMapping(value = "/description", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse welcome( @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "id") Long id,
                                  @RequestParam(value = "useable",required = false) Boolean useable,
                                  @RequestParam(value = "type",required = false) String type,
                                  @RequestParam(value = "layoutCode",required = false) String layoutCode,
                                  @RequestParam(value = "content",required = false) String content,
                                  @RequestParam(value = "applyDetail",required = false) String applyDetail,
                                  @RequestParam(value = "status",required = false) Integer status,
                                  @RequestParam(value = "file",required = false) MultipartFile file,
                                  @UserInjected WebUser user) throws NoSuchAlgorithmException, IOException, ParseException {


        String picUrl = null;
        if(file != null){
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
            picUrl = staticUrl+name;
        }

        DescriptionRequest descriptionRequest = new DescriptionRequest();
        descriptionRequest.setId(id);
        descriptionRequest.setTitle(title);
        descriptionRequest.setStatus(status);
        descriptionRequest.setPictures(picUrl);
        descriptionRequest.setContent(content);
        descriptionRequest.setUseable(useable);
        descriptionRequest.setType(type);
        descriptionRequest.setLayoutCode(layoutCode);
        descriptionRequest.setApplyDetail(applyDetail);
        descriptionRequest.setApplyPeople(user.getName());

        descriptionService.createOrUpdate(descriptionRequest);
        return BasicResponse.success();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/description/{id}", method = RequestMethod.DELETE)
    public BasicResponse welcome(@PathVariable("id") long id) {

        descriptionService.delete(id);
        return BasicResponse.success();
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/description/{id}", method = RequestMethod.GET)
    public DataResponse<Explain> welcomeOne(@PathVariable("id") long id) {

        Explain response = descriptionService.findOne(id);
        return new DataResponse<>(response);
    }

    /**
     * 状态改变
     */
    @RequestMapping(value = "/description/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") long id,@RequestParam("op") int op) {

        descriptionService.changeStatus(id,op);
        return BasicResponse.success();
    }

}
