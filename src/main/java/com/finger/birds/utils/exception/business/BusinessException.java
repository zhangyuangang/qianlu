package com.finger.birds.utils.exception.business;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4069445620933450089L;

	private BExceptionData data;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, BExceptionData data) {
		super(msg);
		this.data = data;
	}
	
	public BusinessException(Exception e) {
		super(e);
	}

	public BExceptionData getData() {
		return data;
	}

	public void setData(BExceptionData data) {
		this.data = data;
	}

}
