package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class DynamicQueryBean implements Bean{
	
	private Long id;
	private Long userId;
	private Long selfId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSelfId() {
		return selfId;
	}

	public void setSelfId(Long selfId) {
		this.selfId = selfId;
	}
	
}
