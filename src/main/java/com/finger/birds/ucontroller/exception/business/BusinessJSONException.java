package com.finger.birds.ucontroller.exception.business;

import com.finger.birds.ucontroller.exception.JSONException;
import com.finger.birds.utils.exception.business.BusinessException;

public class BusinessJSONException extends BusinessException implements JSONException{

	private static final long serialVersionUID = -3035777996694265799L;

	public BusinessJSONException(String msg) {
		super(msg);
	}
}
