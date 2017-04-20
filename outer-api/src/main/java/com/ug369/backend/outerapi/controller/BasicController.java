package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.BasicRequest;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.service.service.BannerAdvertisementService;
import com.ug369.backend.service.service.BasicService;
import com.ug369.backend.utils.ExcelUtils;
import com.ug369.backend.utils.SMSUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
public class BasicController {
	
    @Autowired
    private BasicService basicService;

    /**
     * basic页列表列表
     */
    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public PagedDataResponse<BasicRequest> basicList(@PageDefault PageRequest pageRequest, @RequestParam(value = "userName") String userName) {
        PagedResult<BasicRequest> users = basicService.getAll(pageRequest, userName);

        return PagedDataResponse.of(users);
    }
    
    /**
     * 详情
     */
    @RequestMapping(value = "/basic/{userId}", method = RequestMethod.GET)
    public DataResponse<BasicRequest> basicOne(@PathVariable("userId") long userId) {

    	BasicRequest response = basicService.findOne(userId);
        return new DataResponse<>(response);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/basic", method = RequestMethod.POST)
    public BasicResponse basicUpdate( @RequestParam(value = "userName") String userName,
                                  @RequestParam(value = "userId") Long userId,
                                  @RequestParam(value = "userMobile") String userMobile,
                                  @RequestParam(value = "userSex") String userSex,
                                  @RequestParam(value = "userBirthday") String userBirthday,
                                  @RequestParam(value = "userHeight") String userHeight,
                                  @RequestParam(value = "userSleep") String userSleep,
                                  @RequestParam(value = "userExperience") String userExperience) throws NoSuchAlgorithmException, IOException, ParseException {


        BasicRequest basicRequest = new BasicRequest();
        String birthday = null;
        if(null != userBirthday){
        	birthday = userBirthday.replace("-", ".").substring(0, 10);
        }
        basicRequest.setUserId(userId);
        basicRequest.setUserName(userName);
        basicRequest.setUserMobile(userMobile);
        basicRequest.setUserSex(new Integer(userSex));
        if(null!=birthday){
        	basicRequest.setUserBirthday(birthday);
        }
        basicRequest.setUserHeight(new Integer(userHeight));
        basicRequest.setUserSleep(new Integer(userSleep));
        basicRequest.setUserExperience(new Integer(userExperience));
        
        basicService.update(basicRequest);
        return BasicResponse.success();
    }

    /**
     *  更改状态
     */
    @RequestMapping(value = "/basic/updateStatus")
    public BasicResponse updateStatus(@RequestParam("userId") int userId, @RequestParam("status") String status) {
    	BasicRequest basicRequest = new BasicRequest();
    	basicRequest.setUserId(userId);
    	if(status.equals("1")){
    		basicRequest.setUseable(true);
    	}else{
    		basicRequest.setUseable(false);
    	}
    	
    	basicService.updateStatus(basicRequest);
        return BasicResponse.success();
    }
    
    
    
    @RequestMapping(value = "/basic/sendSMS4ResetPwd")
    public BasicResponse sendSMS4ResetPwd(@RequestParam("userId") int userId){
    	BasicRequest user = basicService.findOne(userId);
    	if(org.apache.commons.lang3.StringUtils.isEmpty(user.getModelNumber())){
    		SMSUtils.sendPwdprompt(user.getUserMobile(), "111111");
    		user.setUserPwd(DigestUtils.md5Hex("111111"));
    		basicService.update(user);
    	
            return BasicResponse.success();
        }else {
            return BasicResponse.success();
        }
    }

    @RequestMapping(value = "/basic/exportUserStats")
    public void exportUserStats(@RequestParam(value = "userName") String userName,
            /*@RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate,*/ HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
        	
    		String fileName = "用户统计数据.xls"; //文件名 
            String sheetName = "统计数据";//sheet名
            
            String []title = new String[]{"用户名","年龄","性别","手机号","终端","访问时长","注册时间","城市"};//标题
            
            List<Map<String, Object>> list = basicService.statsExportUsers(userName, "", "");//内容list
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String [][]values = new String[list.size()][];
            for(int i=0;i<list.size();i++){
                values[i] = new String[title.length];
                //将对象内容转换成string
//                BasicRequest obj = list.get(i);  null==item.get("userName") ? "/" : item.get("userName").toString()
                values[i][0] = null == list.get(i).get("userName") ? "/" : list.get(i).get("userName").toString();
                values[i][1] = null == list.get(i).get("userAge") ? "/" : list.get(i).get("userAge").toString();
                values[i][2] = null == list.get(i).get("userGender") ? "/" : list.get(i).get("userGender").toString();
                values[i][3] = null == list.get(i).get("userPhone") ? "/" : list.get(i).get("userPhone").toString();
                values[i][4] = null == list.get(i).get("terminal") ? "/" : list.get(i).get("terminal").toString();
                Double onlineTime = null==list.get(i).get("onlineTime") ? 0 : Double.valueOf((Double.valueOf(list.get(i).get("onlineTime").toString()) / 60));
                values[i][5] = new DecimalFormat("#.00").format(onlineTime) + "小时";
                values[i][6] = null==list.get(i).get("userCreateTime") ? "/" : list.get(i).get("userCreateTime").toString();
                values[i][7] = null==list.get(i).get("userCity") ? "/" : list.get(i).get("userCity").toString();
                
            }
            
            HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, values, null);
            fileName = new String(fileName.getBytes(),"GBK"); 
    		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+new String("用户统计数据.xls".getBytes("gb2312"),"ISO8859-1"));  
            OutputStream os = response.getOutputStream();
    		wb.write(os);
            os.flush();
            os.close();
        
        } catch (Exception e) {
			System.out.println(e);
		}
    }
    
}
