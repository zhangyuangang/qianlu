package com.finger.birds.utils.exception.valicode;

import com.finger.birds.utils.exception.business.BusinessException;

/**
 * api短信验证码异常
 * @author pt
 *
 */
public class ValiCodeException extends BusinessException{

	private static final long serialVersionUID = 6125722580351681979L;

	public ValiCodeException(Exception e) {
		super(e);
	}
	
	public ValiCodeException(String msg){
		super(msg);
	}
}
