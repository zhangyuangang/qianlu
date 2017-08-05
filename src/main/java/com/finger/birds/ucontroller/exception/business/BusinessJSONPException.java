package com.finger.birds.ucontroller.exception.business;

import com.finger.birds.ucontroller.exception.JSONPException;
import com.finger.birds.utils.exception.business.BusinessException;

public class BusinessJSONPException extends BusinessException implements JSONPException{

	private static final long serialVersionUID = 7718260091819983685L;

	public BusinessJSONPException(String msg) {
		super(msg);
	}
}
