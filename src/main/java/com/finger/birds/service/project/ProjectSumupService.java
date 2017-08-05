package com.finger.birds.service.project;

import com.finger.birds.db.bean.ProjectSumupBean;
import com.finger.birds.db.bean.ProjectSumupQueryBean;
import com.finger.birds.db.po.project.ProjectSumupListPO;
import com.finger.birds.utils.bean.PageBean;

public interface ProjectSumupService {
	
	PageBean<ProjectSumupListPO> getListForHPage(ProjectSumupQueryBean bean, PageBean<ProjectSumupListPO> page);

	void add(ProjectSumupBean bean);
	
}
