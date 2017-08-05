package com.finger.birds.ucontroller.exception.auth;

import com.finger.birds.ucontroller.exception.JSONPException;
import com.finger.birds.utils.exception.auth.AuthorityException;


public class AuthorityJSONPException extends AuthorityException implements JSONPException{

	private static final long serialVersionUID = 1720736881995348479L;

	public AuthorityJSONPException(String msg) {
		super(msg);
	}

}
