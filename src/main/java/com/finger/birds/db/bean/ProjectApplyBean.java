package com.finger.birds.db.bean;

import java.util.List;

import com.finger.birds.model.project.ProjectApply;
import com.finger.birds.model.project.ProjectApplyDetail;
import com.finger.birds.utils.bean.Bean;

public class ProjectApplyBean extends ProjectApply implements Bean{

	private List<ProjectApplyDetail> detail;

	public List<ProjectApplyDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<ProjectApplyDetail> detail) {
		this.detail = detail;
	}

}
