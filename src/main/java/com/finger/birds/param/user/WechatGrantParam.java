package com.finger.birds.param.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.finger.birds.utils.param.Param;


public class WechatGrantParam extends Param {

	/**
	 * 上一次的请求路径及参数
	 */
	private String pre;

	private String code;

	private String state;

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		try {
			this.pre = URLDecoder.decode(pre, "utf-8");
		} catch (UnsupportedEncodingException e) {
			this.pre = "";
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}