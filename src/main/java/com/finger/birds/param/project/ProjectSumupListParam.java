package com.finger.birds.param.project;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.ProjectSumupQueryBean;
import com.finger.birds.db.po.project.ProjectSumupListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class ProjectSumupListParam extends PageParam<ProjectSumupListPO> implements BeanParam<ProjectSumupQueryBean>{

	private Long chiziId;
	
	private Integer searchType = -1;
	
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

	@Override
	public ProjectSumupQueryBean initBean() {
		ProjectSumupQueryBean bean = new ProjectSumupQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
