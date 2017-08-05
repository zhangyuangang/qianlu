package com.finger.birds.ucontroller.exception.auth;

import com.finger.birds.ucontroller.exception.JSONException;
import com.finger.birds.utils.exception.auth.AuthorityException;


public class AuthorityJSONException extends AuthorityException implements JSONException{

	private static final long serialVersionUID = -3035777996694265799L;

	public AuthorityJSONException(String msg) {
		super(msg);
	}
}
