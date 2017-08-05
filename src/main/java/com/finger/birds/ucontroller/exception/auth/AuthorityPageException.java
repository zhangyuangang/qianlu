package com.finger.birds.ucontroller.exception.auth;

import com.finger.birds.ucontroller.exception.PageException;
import com.finger.birds.utils.exception.auth.AuthorityException;


public class AuthorityPageException extends AuthorityException implements PageException{

	private static final long serialVersionUID = -2423883624603295119L;
	
	public AuthorityPageException(String msg) {
		super(msg);
	}
}
