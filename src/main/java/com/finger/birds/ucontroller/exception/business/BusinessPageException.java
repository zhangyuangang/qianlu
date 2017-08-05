package com.finger.birds.ucontroller.exception.business;

import com.finger.birds.ucontroller.exception.PageException;
import com.finger.birds.utils.exception.business.BusinessException;


public class BusinessPageException extends BusinessException implements PageException{

	private static final long serialVersionUID = -2423883624603295119L;
	
	public BusinessPageException(String msg) {
		super(msg);
	}
}
