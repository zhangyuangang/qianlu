package com.finger.birds.ucontroller.exception.param;

import org.springframework.validation.FieldError;

import com.finger.birds.ucontroller.exception.JSONException;



public class RequestParamInvalidJSONException extends ParamException implements JSONException{


	private static final long serialVersionUID = 2484531906862681599L;

	public RequestParamInvalidJSONException(String errMsg) {
		super(errMsg);
	}
	
	public RequestParamInvalidJSONException(FieldError fieldErr) {
		super(fieldErr);
	}
}
