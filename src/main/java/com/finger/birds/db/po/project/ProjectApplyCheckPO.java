package com.finger.birds.db.po.project;

import com.finger.birds.model.project.ProjectApply;

public class ProjectApplyCheckPO extends ProjectApply{
	
	private Long puserId;
	
	private Long auserId;
	
	private Integer status;

	public Long getPuserId() {
		return puserId;
	}

	public void setPuserId(Long puserId) {
		this.puserId = puserId;
	}

	public Long getAuserId() {
		return auserId;
	}

	public void setAuserId(Long auserId) {
		this.auserId = auserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
