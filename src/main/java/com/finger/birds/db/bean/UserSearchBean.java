package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class UserSearchBean implements Bean {

	private Long id;
	private String start;
	private String code;

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}
	
}
