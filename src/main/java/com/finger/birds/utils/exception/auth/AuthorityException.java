package com.finger.birds.utils.exception.auth;

public class AuthorityException extends RuntimeException{

	private static final long serialVersionUID = -4069445620933450089L;
	
	public AuthorityException(String msg){
		super(msg);
	}
	
	public AuthorityException(Exception e){
		super(e);
	}
}
