package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class HotDisQueryBean implements Bean{
	
	private Short month;
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}
	
}
