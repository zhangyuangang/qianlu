package com.finger.birds.param.project;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.ProjectApplyQueryBean;
import com.finger.birds.db.po.project.ProjectApplyListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class ProjectApplyListParam extends PageParam<ProjectApplyListPO> implements BeanParam<ProjectApplyQueryBean>{

	private Long projectId;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public ProjectApplyQueryBean initBean() {
		ProjectApplyQueryBean bean = new ProjectApplyQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}
	
}
