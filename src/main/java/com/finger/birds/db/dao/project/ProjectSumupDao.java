package com.finger.birds.db.dao.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.ProjectSumupQueryBean;
import com.finger.birds.db.po.project.ProjectSumupListPO;
import com.finger.birds.db.po.project.ProjectSumupPO;
import com.finger.birds.model.project.ProjectSumup;
import com.finger.birds.model.project.ProjectSumupDetail;
import com.finger.birds.model.project.ProjectSumupUser;

@Repository
public interface ProjectSumupDao {

	List<ProjectSumupListPO> getListForHPage(@Param("bean") ProjectSumupQueryBean bean, @Param("start")Integer start, @Param("pageSize") Integer pageSize);
	
	int getListForHPageCount(@Param("bean") ProjectSumupQueryBean bean);
	
	void save(ProjectSumup projectSumup);
	
	void detailBatchSave(List<ProjectSumupDetail> detail);
	
	Long checkSumup(@Param("userId")Long userId, @Param("sumupId")Long sumupId);
	
	List<ProjectSumupDetail> getSumupDetail(Long sumupId);
	
	int updateById(@Param("type")Integer type, @Param("id")Long id);
	
	int updateSumupUser(@Param("sumupId")Long sumupId, @Param("userId")Long userId);
	
	void saveSumupUser(ProjectSumupUser psu);
	
	ProjectSumup getById(Long id);
	
	ProjectSumupPO getSumupPOById(@Param("id")Long id, @Param("userId")Long userId);
	
	Long checkExact(@Param("sumupId")Long sumupId, @Param("userId")Long userId);
	
	int checkIsSelf(@Param("sumupId")Long sumupId, @Param("userId")Long userId);
}
