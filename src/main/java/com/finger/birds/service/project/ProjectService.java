package com.finger.birds.service.project;

import java.util.List;

import com.finger.birds.db.bean.ProjectBean;
import com.finger.birds.db.bean.ProjectQueryBean;
import com.finger.birds.db.po.project.ProjectComPO;
import com.finger.birds.db.po.project.ProjectListPO;
import com.finger.birds.db.po.project.ProjectPO;
import com.finger.birds.db.po.project.ProjectSumupPO;
import com.finger.birds.model.project.ProjectRefApply;
import com.finger.birds.model.project.ProjectSumupDetail;
import com.finger.birds.model.user.UserComment;
import com.finger.birds.utils.bean.PageBean;

public interface ProjectService {
	
	void add(ProjectBean bean);
	
	PageBean<ProjectListPO> getListForPage(ProjectQueryBean bean, PageBean<ProjectListPO> page);
	
	/**
	 * 当前用户Id
	 * @param id
	 * @param userId
	 * @return
	 */
	ProjectPO getById(Long id, Long userId);
	
	void updateViewTimes(Long id);
	
	void updateApplyTimes(Long id);

	int checkComment(Long projectId, Long userId);

	void comment(UserComment uc, ProjectRefApply pra, Long projectId);

	ProjectComPO getCPOById(Long projectId);

	Long checkRef(Long userId, Long sumupId);

	void ref(Long userId, Long sumupId);
	
	List<ProjectSumupDetail> getSumpDetailList(Long sumpId);

	ProjectSumupPO getSumupPOById(Long id, Long userId);
	
	void Exact(Long sumupId, Long userId);
	
	Long checkExact(Long sumupId, Long userId);
}
