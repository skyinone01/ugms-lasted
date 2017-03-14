package com.ugms.backend.bean.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Roy on 2017/3/4.
 */
public class TokenResponse {

	@JsonProperty("token")
	private String token;
	@JsonProperty("timeout")
	private int timeout;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
