//package com.ug369.backend.utils;
//
//import com.cloopen.rest.sdk.CCPRestSDK;
//import com.ug369.backend.bean.exception.BusinessException;
//import com.ug369.backend.bean.exception.ErrorCode;
//
//import java.util.HashMap;
//
///**
// * Created by 王磊 on 2016/9/18.
// */
//public class SMSUtils {
//
//    private static volatile CCPRestSDK restAPI = new CCPRestSDK();
//    public SMSUtils(){}
//
//    public SMSUtils(String serverIP, String serverPort, String accountSid, String accountToken, String appId){
//        restAPI.init(serverIP, serverPort);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
//        restAPI.setAccount(accountSid, accountToken);// 初始化主帐号和主帐号TOKEN
//        restAPI.setAppId(appId);// 初始化应用ID
//    }
//
//    /**
//     * 发送手机验证码接口
//     * @param phone
//     * @param msg
//     * @param templateId
//     * @param index
//     * @return
//     */
//    public static int sendMS(String phone, String msg, String templateId, Integer index){
//        HashMap<String, Object> result = restAPI.sendTemplateSMS(phone, templateId ,new String[]{msg, index.toString()});
//        if("000000".equals(result.get("statusCode"))){
//            return ErrorCode.SUCCESS.code();
//        }else{
//            //异常返回输出错误码和错误信息
//            //System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//            throw new BusinessException(result.get("statusMsg").toString(), ErrorCode.THIRD_SMS_API_EXCEPTION.code());
//        }
//    }
//    /**
//     * 发送手机验证码
//     * @param phone
//     * @param verifyCode
//     * @return
//     */
//    public static int sendVerifyCode(String phone, String verifyCode){
//        return sendMS(phone, verifyCode, "93623", 10);
//    }
//    /**
//     * 发送密码提示
//     * @param phone
//     * @param pwd
//     * @return
//     */
//    public static int sendPwdprompt(String phone, String pwd){
//        HashMap<String, Object> result = restAPI.sendTemplateSMS(phone, "130612" ,new String[]{});
//        if("000000".equals(result.get("statusCode"))){
//            return ErrorCode.SUCCESS.code();
//        }else{
//            //异常返回输出错误码和错误信息
//            //System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//            throw new BusinessException(result.get("statusMsg").toString(), ErrorCode.THIRD_SMS_API_EXCEPTION.code());
//        }
//    }
//
//}
