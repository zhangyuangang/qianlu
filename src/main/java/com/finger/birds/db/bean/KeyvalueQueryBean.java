package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class KeyvalueQueryBean implements Bean{
	
	private Long id;
	private Long key;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}
	
}
