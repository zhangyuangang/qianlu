package com.finger.birds.ucontroller.exception.system;

import com.finger.birds.ucontroller.exception.PageException;

public class SystemPageException extends SystemException implements PageException{

	private static final long serialVersionUID = -3098086324246184575L;
	
	public SystemPageException(Throwable t){
		super(t);
	}
	
	public SystemPageException(String t){
		super(t);
	}
}
