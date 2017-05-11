package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.BannerRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.Banner;
import com.ug369.backend.service.service.BannerAdvertisementService;
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

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class BannerController {

    @Autowired
    private BannerAdvertisementService bannerAdvertisementService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    /**
     * banner页列表列表
     */
    @RequestMapping(value = "/banner", method = RequestMethod.GET)
    public PagedDataResponse<BannerRequest> welcome(@PageDefault PageRequest pageRequest,@RequestParam("type") int type) {
        PagedResult<BannerRequest> users = bannerAdvertisementService.getAll(pageRequest,type);

        return PagedDataResponse.of(users);
    }

    /**
     * 新增、修改 banner
     */
    @RequestMapping(value = "/banner", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse welcome( @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "useable",required = false) Integer useable,
                                  @RequestParam(value = "beginDate",required = false) String begin_date,
                                  @RequestParam(value = "endDate",required = false) String endDate,
                                  @RequestParam(value = "orderId",required = false) Integer orderId,
                                  @RequestParam(value = "link",required = false) String link,
                                  @RequestParam(value = "contactName",required = false) String contactName,
                                  @RequestParam(value = "contactPhone",required = false) String contactPhone,
                                  @RequestParam(value = "content",required = false) String content,
                                  @RequestParam(value = "weight",required = false) Integer weight,
                                  @RequestParam(value = "type",required = false) Integer type,
                                  @RequestParam(value = "status",required = false) Integer status,
                                  @RequestParam(value = "isBanner",required = false) Integer isBanner,
                                  @RequestParam(value = "isDefault",required = false) Boolean isDefault,
                                  @RequestParam(value = "applyDetail",required = false) String applyDetail,
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


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        BannerRequest bannerRequest = new BannerRequest();
        if (!org.apache.commons.lang3.StringUtils.isEmpty(begin_date)){
            bannerRequest.setBeginDate(simpleDateFormat.parse(begin_date));
        }
        if (!org.apache.commons.lang3.StringUtils.isEmpty(endDate)){
            bannerRequest.setEndDate(simpleDateFormat.parse(endDate));
        }
//        bannerRequest.setContent(title);
        bannerRequest.setId(id);
        bannerRequest.setTitle(title);
        bannerRequest.setLink(link);
        bannerRequest.setId(id);
        bannerRequest.setOrderId(orderId);
        bannerRequest.setStatus(status);
        bannerRequest.setPicture(picUrl);
        bannerRequest.setStatus(status);
        bannerRequest.setLink(link);
        bannerRequest.setUseable(useable);
        bannerRequest.setIsBanner(isBanner);
        bannerRequest.setIsdefault(isDefault);
        bannerRequest.setApplyDetail(applyDetail);
        bannerRequest.setApplyPeople(user.getName());

        //广告
        bannerRequest.setContactName(contactName);
        bannerRequest.setContactPhone(contactPhone);
        bannerRequest.setWeight(weight);
        bannerRequest.setType(type);
        bannerRequest.setContent(content);

        bannerAdvertisementService.createOrUpdate(bannerRequest);
        return BasicResponse.success();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/banner/{id}", method = RequestMethod.DELETE)
    public BasicResponse welcome(@PathVariable("id") Integer id) {

        bannerAdvertisementService.delete(id);
        return BasicResponse.success();
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/banner/{id}", method = RequestMethod.GET)
    public DataResponse<Banner> bannerOne(@PathVariable("id") int id) {

        Banner response = bannerAdvertisementService.findOne(id);
        return new DataResponse<>(response);
    }

    /**
     * 状态改变
     */
    @RequestMapping(value = "/banner/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") Integer id,@RequestParam("op") int op) {

        bannerAdvertisementService.changeStatus(id,op);
        return BasicResponse.success();
    }

}
