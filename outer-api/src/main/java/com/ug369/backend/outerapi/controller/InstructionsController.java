package com.ug369.backend.outerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.entity.mysql.Instructions;
import com.ug369.backend.service.entity.mysql.InstructionsXH;
import com.ug369.backend.service.service.InstructionsService;
import com.ug369.backend.utils.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
public class InstructionsController {
    //jjjhh
    @Autowired
    private InstructionsService instructionsService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;
    
    @RequestMapping(value = "/instructions", method = RequestMethod.GET)
    public PagedDataResponse<InstructionsXH> instructions(@PageDefault PageRequest pageRequest) {
        PagedResult<InstructionsXH> users = instructionsService.selectXH(pageRequest);

        return PagedDataResponse.of(users);
    }
    
    /*@RequestMapping(value = "/instructions/{offset}/{limit}")
    public List<InstructionsXH> getAll(@PathVariable Integer offset, @PathVariable Integer limit) {
        //PageHelper.offsetPage(offset, limit,true);
        List<InstructionsXH> list = instructionsService.selectXH();
        return list;
    }

    @RequestMapping(value = "/instructions")
    public List<InstructionsXH>  getXHAll() {
        List<InstructionsXH> list = instructionsService.selectXH();
        return list;
    }*/

    @RequestMapping(value = "/instructions/{id}", method = {RequestMethod.GET})
    public Instructions getOne(@PathVariable Integer id) {
        Instructions instructions = instructionsService.findOne(id);
        System.out.println(instructions.toString());
        return instructions;
    }

    @RequestMapping(value = "/instructions/{id}", method = {RequestMethod.POST}, produces = "application/json")
    public BasicResponse update(@PathVariable Integer id, @RequestBody Instructions instructions) throws NoSuchAlgorithmException {
    	String picUrl = "";
        if(!"".equals(instructions.getImgUrl())){
            if(!StringUtils.isPicture(instructions.getContent())){
                throw  new UserException(UgmsStatus.BAD_REQUEST,"图片格式不符合要求");
            }
            System.out.println("base64:"+instructions.getImgUrl());
            int pos = instructions.getImgUrl().indexOf(",");
            String data = instructions.getImgUrl().substring(pos+1);
            System.out.println(data);
            ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(buffer);
//            byte[] md5sum = messageDigest.digest();
            String name = System.currentTimeMillis() + StringUtils.getFileSuff(instructions.getContent());
            File img = new File(filePath + name);
            BufferedReader br = null; 
            BufferedWriter bw = null; 
            try {
          	  br = new BufferedReader(new StringReader(data)); 
                bw = new BufferedWriter(new FileWriter(img)); 
                char buf[] = new char[1024 * 64];         //字符缓冲区 
                int len; 
                while ((len = br.read(buf)) != -1) { 
                        bw.write(buf, 0, len); 
                } 
                bw.flush(); 
                br.close(); 
                bw.close(); 
//  			org.apache.commons.io.FileUtils.writeByteArrayToFile(img, data.getBytes(),false);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
            picUrl = staticUrl+name;
        }
        instructions.setImgUrl(picUrl);
        instructions.setContent("");
    	instructionsService.update(instructions);
        return BasicResponse.success();
    }

    
//    ByteBuffer buffer = ByteBuffer.wrap(file.getBytes());
//    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//    messageDigest.update(buffer);
//    byte[] md5sum = messageDigest.digest();
//
//    String name = StringUtils.MD5ToString(md5sum) +
//            file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//    File img = new File(filePath + name);
//    org.apache.commons.io.FileUtils.writeByteArrayToFile(img, file.getBytes(),false);
    
  @RequestMapping(value = "/instructions/save", method = RequestMethod.POST,produces = "application/json")
  public BasicResponse save(@RequestBody Instructions instructions) throws NoSuchAlgorithmException, IOException {
	  //使用content字段保存文件名
	  String picUrl = "";
      if(!"".equals(instructions.getImgUrl())){
          if(!StringUtils.isPicture(instructions.getContent())){
              throw  new UserException(UgmsStatus.BAD_REQUEST,"图片格式不符合要求");
          }
          int pos = instructions.getImgUrl().indexOf(",");
          String data = instructions.getImgUrl().substring(pos+1);
          System.out.println(data);
          ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
          MessageDigest messageDigest = MessageDigest.getInstance("MD5");
          messageDigest.update(buffer);
//          byte[] md5sum = messageDigest.digest();
          String name = System.currentTimeMillis() + StringUtils.getFileSuff(instructions.getContent());
          File img = new File(filePath + name);
          BufferedReader br = null; 
          BufferedWriter bw = null; 
          try {
        	  br = new BufferedReader(new StringReader(data)); 
              bw = new BufferedWriter(new FileWriter(img)); 
              char buf[] = new char[1024 * 64];         //字符缓冲区 
              int len; 
              while ((len = br.read(buf)) != -1) { 
                      bw.write(buf, 0, len); 
              } 
              bw.flush(); 
              br.close(); 
              bw.close(); 
//			org.apache.commons.io.FileUtils.writeByteArrayToFile(img, data.getBytes(),false);
		} catch (IOException e) {
			e.printStackTrace();
		}
          picUrl = staticUrl+name;
      }
      instructions.setImgUrl(picUrl);
      instructions.setContent("");
      instructionsService.save(instructions);
      return BasicResponse.success();
  }
    
    
//    @RequestMapping(value = "/instructions/save", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public BasicResponse save(@RequestBody Instructions instructions,
//    		@RequestParam(value = "file",required = false) MultipartFile file) {
//    	System.out.println(instructions.toString());
//    	System.out.println(file.isEmpty());
//        instructionsService.save(instructions);
//        return BasicResponse.success();
//    }
    

    @RequestMapping(value = "/instructions/{id}", method = {RequestMethod.DELETE})
    public BasicResponse delete(@PathVariable Integer id) {
        instructionsService.delete(id);
        return BasicResponse.success();
    }
    
    
}
