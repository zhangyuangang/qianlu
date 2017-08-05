package com.finger.birds.param.project;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.ProjectQueryBean;
import com.finger.birds.db.po.project.ProjectListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class ProjectListParam extends PageParam<ProjectListPO> implements BeanParam<ProjectQueryBean> {

	private Long userId;

	private int type; // 0 他完成的，1他发布的，2他参考的
	
	private Integer searchType = -1;
	
	private String keyWords;
	
	private Long tuserId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}

	@Override
	public ProjectQueryBean initBean() {
		ProjectQueryBean bean = new ProjectQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
