package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.ArticleColumnRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.ArticleColumn;
import com.ug369.backend.service.service.ArticleService;
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

@RestController
public class ArticleColumnController {

    @Autowired
    private ArticleService articleService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    @RequestMapping(value = "/articleColumn", method = RequestMethod.GET)
    public PagedDataResponse<ArticleColumn> articleColumns(@PageDefault PageRequest pageRequest,
                                                           @RequestParam(value = "searchValue",required = false) String name) {
        PagedResult<ArticleColumn> users = articleService.getAllColumn(pageRequest,name);

        return PagedDataResponse.of(users);
    }
    @RequestMapping(value = "/articleColumn", method = RequestMethod.PUT)
    public BasicResponse articleColumnsUpdate( @RequestParam(value = "column") Long column,
                                               @RequestParam(value = "addable") boolean addable,
                                               @RequestParam(value = "article") Long article) {
       articleService.addArticleToColumn(column,article,addable);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/articleColumn", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse articleColumn( @RequestParam(value = "title",required = false) String title,
                                        @RequestParam(value = "id") long id,
                                        @RequestParam(value = "paymode",required = false) boolean paymode,
                                        @RequestParam(value = "payitem1",required = false) String payitem1,
                                        @RequestParam(value = "payitem2",required = false) String payitem2,
                                        @RequestParam(value = "payitem3",required = false) String payitem3,
                                        @RequestParam(value = "status",required = false) Integer status,
                                        @RequestParam(value = "file",required = false) MultipartFile file,
                                        @RequestParam(value = "applyDetail",required = false) String applyDetail,
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

        ArticleColumn request = new ArticleColumn();
        request.setId(id);
        request.setTitle(title);
        request.setStatus(status);
        request.setApplydetail(applyDetail);
        request.setPayitem1(payitem1);
        request.setPayitem2(payitem2);
        request.setPayitem3(payitem3);
        request.setPaymode(paymode);
        request.setPicture(picUrl);
        request.setStatus(status);
        request.setApplypeople(user.getName());
        articleService.createOrUpdateColumn(request);
        return BasicResponse.success();
    }


    @RequestMapping(value = "/articleColumn/{id}", method = RequestMethod.DELETE)
    public BasicResponse one(@PathVariable("id") long id) {

        articleService.deleteColumn(id);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/articleColumn/{id}", method = RequestMethod.GET)
    public DataResponse<ArticleColumnRequest> detailOne(@PathVariable("id") long id) {

        ArticleColumnRequest response = articleService.findColumn(id);
        return new DataResponse<>(response);
    }

    @RequestMapping(value = "/articleColumn/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") Long id,@RequestParam("op") int op) {

        articleService.changeColumnStatus(id,op);
        return BasicResponse.success();
    }

}
