package com.ug369.backend.bean.exception;

/**
 * Ugms 应用请求异常状态定义
 * Created by Roy on 2017/3/8.
 */
public enum UgmsStatus {

	/*
	 * 字符串是error，errno根据顺序自动生成
	 */
	SUCCESS(0, "succ"), 
	
	AUTH_FAILED(1, "登录失败"),
	
	BAD_REQUEST(2, "请求参数错误"),
	
	PARAM_REQUIRED(3,"需要携带参数"),
	
	NOT_FOUND(4,"不存在"),
	
	ALREADY_EXISTS(5, "已经存在"),
	
	FORMAT_ERROR(6, "格式错误"),
	
	INTERNAL_ERROR(7,"服务器内部错误"),

	LOGIC_ERROR(8,"逻辑错误"),

	INVALID_TOKEN(100,"无效token"),


	VALIDATE_FAILED(101,"验证失败"),
	;

	

	private String error;

	private int errorNo;


	UgmsStatus(int errorNo, String error) {
		this.error = error;
		this.errorNo = errorNo;
	}

	public String getError() {
		return error;
	}

	public int getErrorNo() {
		return errorNo;
	}

}
