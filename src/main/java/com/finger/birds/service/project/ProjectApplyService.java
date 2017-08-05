package com.finger.birds.service.project;

import com.finger.birds.db.bean.ProjectApplyBean;
import com.finger.birds.db.bean.ProjectApplyQueryBean;
import com.finger.birds.db.po.project.ProjectApplyListPO;
import com.finger.birds.db.po.project.ProjectApplyPO;
import com.finger.birds.utils.bean.PageBean;

public interface ProjectApplyService {

	void add(ProjectApplyBean bean);

	void checkIsApply(Long id, Long userId);

	PageBean<ProjectApplyListPO> getListForPage(ProjectApplyQueryBean bean, PageBean<ProjectApplyListPO> page);
	
	ProjectApplyPO getById(Long id);
	
	ProjectApplyPO getChoosedByProjectId(Long projectId);
	
	void complete(Long paDetailId, Long userId);

	void completeAll(Long applyId, Long userId);
	
	void checkSumup(Long id, Long userId);
	
	int checkComment(Long userId, Long projectId);

	Long choose(Long id, Long userId);
}
