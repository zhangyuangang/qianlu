package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class ProjectSumupQueryBean implements Bean{
	
	private Long chiziId;
	
	private Integer searchType;
	
	private String keyWords;

	public Long getChiziId() {
		return chiziId;
	}

	public void setChiziId(Long chiziId) {
		this.chiziId = chiziId;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
}
