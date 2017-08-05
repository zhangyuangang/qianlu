package com.finger.birds.ucontroller.exception.param;


import org.springframework.validation.FieldError;

public class ParamException extends RuntimeException{

	private static final long serialVersionUID = 316365053412736410L;
	
	private FieldError fieldErr;

	public ParamException(FieldError fieldErr) {
		super(fieldErr.getField() + ":" + fieldErr.getDefaultMessage());
		this.fieldErr = fieldErr;
	}
	
	public ParamException(String errMsg) {
		super(errMsg);
	}
	
	public FieldError getFieldErr() {
		return fieldErr;
	}

	public void setFieldErr(FieldError fieldErr) {
		this.fieldErr = fieldErr;
	}
}
