package com.finger.birds.db.dao.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.ProjectBean;
import com.finger.birds.db.bean.ProjectQueryBean;
import com.finger.birds.db.po.project.ProjectComPO;
import com.finger.birds.db.po.project.ProjectCommentCheckPO;
import com.finger.birds.db.po.project.ProjectListPO;
import com.finger.birds.db.po.project.ProjectPO;
import com.finger.birds.model.project.Project;
import com.finger.birds.model.project.ProjectRefApply;
import com.finger.birds.model.user.UserComment;

@Repository
public interface ProjectDao {

	void save(ProjectBean bean);
	
	List<ProjectListPO> getListForPage(@Param("bean")ProjectQueryBean bean, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
	
	int getListForPageCount(ProjectQueryBean bean);
	
	ProjectPO getById(@Param("id")Long id, @Param("userId")Long userId);
	
	int updateViewTimes(Long id);
	
	int updateApplyTimes(Long id);
	
	int updateStatus(@Param("id")Long id, @Param("status")Short status);
	
	Project get(@Param("id") Long id, @Param("isLock") byte isLock);
	
	void addComment(UserComment comment);
	
	int commentRef(ProjectRefApply pra);
	
	ProjectCommentCheckPO checkComment(Long projectId);
	
	ProjectComPO getCPOById(Long projectId);
}
