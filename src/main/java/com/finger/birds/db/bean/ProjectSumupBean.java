package com.finger.birds.db.bean;

import java.util.List;

import com.finger.birds.model.project.ProjectSumup;
import com.finger.birds.model.project.ProjectSumupDetail;
import com.finger.birds.utils.bean.Bean;

public class ProjectSumupBean extends ProjectSumup implements Bean{

	private List<ProjectSumupDetail> detailList;

	public List<ProjectSumupDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ProjectSumupDetail> detailList) {
		this.detailList = detailList;
	}
	
}
