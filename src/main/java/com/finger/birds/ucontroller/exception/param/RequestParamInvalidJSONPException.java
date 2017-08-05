package com.finger.birds.ucontroller.exception.param;

import org.springframework.validation.FieldError;

import com.finger.birds.ucontroller.exception.JSONPException;


public class RequestParamInvalidJSONPException extends ParamException implements JSONPException{

	private static final long serialVersionUID = -8458485206922963849L;

	public RequestParamInvalidJSONPException(String errMsg) {
		super(errMsg);
	}
	
	public RequestParamInvalidJSONPException(FieldError fieldErr) {
		super(fieldErr);
	}
}
