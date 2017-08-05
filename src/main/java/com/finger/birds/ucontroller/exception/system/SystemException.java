package com.finger.birds.ucontroller.exception.system;

public class SystemException extends RuntimeException{

	private static final long serialVersionUID = 2200783376668441978L;
	
	public SystemException(Throwable t){
		super(t);
	}
	
	public SystemException(String msg){
		super(msg);
	}
	
	public SystemException(String msg, Throwable t){
		super(msg, t);
	}
}
