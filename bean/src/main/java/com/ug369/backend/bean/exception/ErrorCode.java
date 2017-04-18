package com.ug369.backend.bean.exception;

/**
 * 异常编号.
 * Created by flyong86 on 2016/4/5.
 */
public enum ErrorCode {

    /** 成功. */
    SUCCESS(0),

    /** 字段不能为null. */
    FIELD_NOT_NULL(1001),
    /** 字段类型不正确. */
    FIELD_TYPE_ERROR(1002),
    /** 字段超长. */
    FIELD_OVER_LONG(1003),
    /** 查询对象不存在. */
    OBJECT_NOT_EXIST(1004),
    /** 对象已存在. */
    OBJECT_EXIST_ERROR(1005),
    /**对象不能为空*/
    OBJECT_NOT_NULL(1006),
    /** 用户名已存在. */
    USER_NAME_EXIST(2001),
    /** PROPERTY COPY ERROR. */
    PROPERTY_COPY_ERROR(2002),

    //服务器异常 -100至 -199
    /** 异常（未知异常）-100 */
    SERVER_ERROR_UNKONW(-100),
    /** 数据库连接异常 -102 */
    SERVER_ERROR_DB_COLLECTION(-102),

    //客户端错误	-200至 -299
    /** 请求参数/参数列表错误 */
    CLIENT_ERROR_INVALID_PARAMS(-200),
    /** token异常，无效访问 */
    CLIENT_ERROR_INVALID_VISIT(-201),

    /*token过期*/
    CLIENT_TIMEOUT_INVALID_VISIT(-202),


    //第三方接口异常	-300 至 -399
    /** 短信平台接口异常 */
    THIRD_SMS_API_EXCEPTION(-301),
    /** 推送平台接口异常 */
    THIRD_PUSH_API_EXCEPTION(-311),
    /** 推送接口参数异常 */
    THIRD_PUSH_INVALID_PARAMS(-312),


    //业务类异常(-400 以后)
    /** 未登录 */
    BUSINESS_ERROR_UNLOGIN(-401),
    /** 账号不存在/手机号不存在 */
    BUSINESS_ERROR_NOT_EXIST(-402),
    /** 密码错误 */
    BUSINESS_ERROR_INVALID_PWD(-403),
    /** 手机号已绑定 */
    BUSINESS_ERROR_PHONE_ALREADY_BIND(-404),
    /** 验证码错误 */
    BUSINESS_ERROR_INVALID_VERIFY_CODE(-405),
    /** 密保问题答案错误 */
    BUSINESS_ERROR_INVALID_SAFE_ANSWER(-406),
    /** 账号已存在 */
    BUSINESS_ERROR_ACCOUNT_EXIST(-407),

    /** 新密码与旧密码相同 */
    BUSINESS_ERROR_OLDPASSWORD_EQUAL(-408),
    /** 重复密码与新密码不匹配 */
    BUSINESS_ERROR_REPASSWORD_NOT_EQUAL(-409),
    /** 密保问题错误 */
    BUSINESS_ERROR_INVALID_SAFE_QUESTION(-410),
    /** 倒计时未完成 */
    BUSINESS_ERROR_NOT_OUT_COUNTDOWN(-411),
    /** 已有顶级提醒设置 */
    BUSINESS_ERROR_ALREADY_SET_TOP(-412),
    /** 用户信息失效 */
    BUSINESS_ERROR_USER_LOSE_EFFICACY(-413),
    /** 删除已选食物出错，至少保留一项饮食 */
    BUSINESS_ERROR_DEL_SELECTFOOD(-414),
    /**已添加收藏**/
    BUSINESS_ERROR_HAVE_FOOD(-415),
    /** 邮箱地址已存在 */
    BUSINESS_ERROR_EMAIL_EXIST(-416),
    /** 电话号码已存在 */
    BUSINESS_ERROR_PHONE_EXIST(-417),
    /** 提醒设置已存在 */
    BUSINESS_ERROR_TIMERECORD_EXIST(-418),
    /** 用户布局已存在 */
    BUSINESS_ERROR_LAYOUT_EXIST(-419),
    /** 当前用户不可用 */
    BUSINESS_ERROR_USER_UNUSABLE(-420),
    /** 问题标题已存在 */
    BUSINESS_ERROR_QUESTIONTITLE_EXIST(-501),
    /** 问卷导入模板无效、或验证不通过 */
    BUSINESS_ERROR_QN_TEMPLETE_INVALID(-502),

    /** 数据已被使用 */
    BUSINESS_ERROR_DATA_HAS_USED(-601);

    int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
