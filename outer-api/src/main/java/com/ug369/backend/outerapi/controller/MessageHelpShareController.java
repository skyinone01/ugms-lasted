package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.ContentRequest;
import com.ug369.backend.bean.bean.request.TypeRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.Content;
import com.ug369.backend.service.service.MessageHelpShareService;
import com.ug369.backend.utils.StringUtils;
import com.ug369.backend.utils.TokenUtils;
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

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class MessageHelpShareController {

    @Autowired
    private MessageHelpShareService messageHelpShareService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * message 列表
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public PagedDataResponse<Content> welcome(@PageDefault PageRequest pageRequest,
                                              @UserInjected WebUser user,
                                              @RequestParam("category") String category) {
        PagedResult<Content> users = messageHelpShareService.getAll(pageRequest,user,category);

        return PagedDataResponse.of(users);
    }

    /**
     * 新增、修改 message
     */
    @RequestMapping(value = "/message", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse welcome(    @UserInjected WebUser user,
                                     @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "id" ,required = false) int id,
                                  @RequestParam(value = "typeStr",required = false) String typeStr,
                                  @RequestParam(value = "typeId",required = false) Integer typeId,
                                  @RequestParam(value = "orderId",required = false) Integer orderId,
                                  @RequestParam(value = "context",required = false) String context,
                                  @RequestParam(value = "summary",required = false) String summary,
                                  @RequestParam(value = "applyDetail",required = false) String applyDetail,
                                  @RequestParam(value = "category",required = false) String category,
                                  @RequestParam(value = "linkUrl",required = false) String linkUrl,
                                  @RequestParam(value = "status",required = false) Integer status,
                                  @RequestParam(value = "file",required = false) MultipartFile file) throws NoSuchAlgorithmException, IOException, ParseException {


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

//        Long uid = tokenUtils.validate(token);
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setUserId((int)user.getId());
        contentRequest.setId(id);
        contentRequest.setTitle(title);
        contentRequest.setStatus(status);
        contentRequest.setContext(context);
        contentRequest.setSummary(summary);
        contentRequest.setTypeId(typeId);
        contentRequest.setTypeStr(typeStr);
        contentRequest.setCategory(category);
        contentRequest.setOrderId(orderId);
        contentRequest.setPictures(picUrl);
        contentRequest.setLinkUrl(linkUrl);
        contentRequest.setApplyDetail(applyDetail);
        contentRequest.setApplyPeople(user.getName());

        messageHelpShareService.createOrUpdate(contentRequest);
        return BasicResponse.success();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public BasicResponse welcome(@PathVariable("id") int id) {

        messageHelpShareService.delete(id);
        return BasicResponse.success();
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public DataResponse<ContentRequest> welcomeOne(@PathVariable("id") int id) {

        ContentRequest response = messageHelpShareService.findOne(id);
        return new DataResponse<>(response);
    }

    /**
     * 状态改变
     */
    @RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") int id,@RequestParam("op") int op) {

        messageHelpShareService.changeStatus(id,op);
        return BasicResponse.success();
    }

    /**
     * list types
     */
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public DataResponse<TypeRequest> types() {

        return new DataResponse(messageHelpShareService.getTypes());
    }

}
