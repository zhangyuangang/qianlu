package com.finger.birds.db.bean;

import com.finger.birds.model.dynamic.Dynamic;
import com.finger.birds.utils.bean.Bean;

public class DynamicBean extends Dynamic implements Bean{
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
