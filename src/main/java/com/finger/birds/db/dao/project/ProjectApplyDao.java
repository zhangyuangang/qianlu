package com.finger.birds.db.dao.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.ProjectApplyBean;
import com.finger.birds.db.bean.ProjectApplyQueryBean;
import com.finger.birds.db.po.project.ProjectApplyCheckPO;
import com.finger.birds.db.po.project.ProjectApplyListPO;
import com.finger.birds.db.po.project.ProjectApplyPO;
import com.finger.birds.model.project.ProjectApplyDetail;
import com.finger.birds.model.project.ProjectRefApply;

@Repository
public interface ProjectApplyDao {

	void save(ProjectApplyBean bean);
	
	void detailBatchSave(List<ProjectApplyDetail> list);
	
	int checkIsApply(@Param("projectId")Long projectId, @Param("userId")Long userId);
	
	List<ProjectApplyListPO> getListForPage(@Param("bean") ProjectApplyQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int getListForPageCount(ProjectApplyQueryBean bean);
	
	List<ProjectApplyDetail> getDetailByPAId(Long applyId);
	
	ProjectApplyPO getById(Long id);
	
	void saveRef(ProjectRefApply apply);
	
	ProjectApplyPO getChoosedByProjectId(Long projectId);
	
	ProjectApplyCheckPO getByPAByDetailId(Long detailId);
	
	/**
	 * 
	 * @param id 
	 * @param type 1老鸟点击完成，2菜鸟点击完成
	 * @param status
	 */
	void updateDetailStatus(@Param("id")Long id, @Param("type")Integer type, @Param("status")Short status);
	
	void updateRefStatus(@Param("projectId")Long projectId, @Param("status")Short status);
	
	int checkSumup(@Param("userId")Long userId, @Param("applyId")Long applyId);
}
