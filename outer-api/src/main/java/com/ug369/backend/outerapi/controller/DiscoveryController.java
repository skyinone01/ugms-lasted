package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.DiscoveryRequest;
import com.ug369.backend.bean.bean.request.DiscoveryTypeRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.Discovery;
import com.ug369.backend.service.service.DiscoveryService;
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

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class  DiscoveryController {

    @Autowired
    private DiscoveryService discoveryService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    /**
     * 列表
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public PagedDataResponse<DiscoveryRequest> welcome(@PageDefault PageRequest pageRequest) {
        PagedResult<DiscoveryRequest> users = discoveryService.getAll(pageRequest);

        return PagedDataResponse.of(users);
    }

    /**
     * type
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public DataResponse type() {
        return new DataResponse(discoveryService.getTypes());
    }

    /**
     * type
     */
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public BasicResponse typeDelete(@PathVariable("id") Integer id) {
        discoveryService.deleteType(id);
        return BasicResponse.success();
    }

    /**
     * type
     */
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public BasicResponse typeAddOrUpdate(@RequestBody DiscoveryTypeRequest request) {
        discoveryService.createOrUpdateType(request);
        return BasicResponse.success();
    }


    /**
     * 新增、修改
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse welcome( @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "id") Long id,
                                  @RequestParam(value = "orderId",required = false) Integer orderId,
                                  @RequestParam(value = "typeId",required = false) Integer typeId,
                                  @RequestParam(value = "isLink",required = false) Boolean isLink,
                                  @RequestParam(value = "linkUrl",required = false) String linkUrl,
                                  @RequestParam(value = "context",required = false) String context,
                                  @RequestParam(value = "summary",required = false) String summary,
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

        DiscoveryRequest discoveryRequest = new DiscoveryRequest();
        discoveryRequest.setId(id);
        discoveryRequest.setTitle(title);
        discoveryRequest.setOrderId(orderId);
        discoveryRequest.setIcon(picUrl);
        discoveryRequest.setLink(isLink);
        discoveryRequest.setLinkUrl(linkUrl);
        discoveryRequest.setContext(context);
        discoveryRequest.setStatus(status);
        discoveryRequest.setTypeId(typeId);
        discoveryRequest.setSummary(summary);

        discoveryService.createOrUpdate(discoveryRequest);
        return BasicResponse.success();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/discovery/{id}", method = RequestMethod.DELETE)
    public BasicResponse welcome(@PathVariable("id") long id) {

        discoveryService.delete(id);
        return BasicResponse.success();
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/discovery/{id}", method = RequestMethod.GET)
    public DataResponse<Discovery> welcomeOne(@PathVariable("id") long id) {

        Discovery response = discoveryService.findOne(id);
        return new DataResponse<>(response);
    }

    /**
     * 状态改变
     */
    @RequestMapping(value = "/discovery/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") long id,@RequestParam("op") int op) {

        discoveryService.changeStatus(id,op);
        return BasicResponse.success();
    }

}
