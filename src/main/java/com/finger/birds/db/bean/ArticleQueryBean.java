package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class ArticleQueryBean implements Bean{

	private Long id;
	private Long userId;
	private Long selfId;
	private String code;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
