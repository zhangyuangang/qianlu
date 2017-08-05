package com.finger.birds.db.dao.chizi;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.chizi.ChiziPO;
import com.finger.birds.model.chizi.ChiziProject;
import com.finger.birds.model.chizi.ChiziUser;

@Repository
public interface ChiziDao {
	
	List<ChiziPO> getAll();
	
	ChiziPO getById(Long id);
	
	int checkCare(@Param("userId")Long userId, @Param("chiziId")Long chiziId);
	
	void addCare(@Param("userId")Long userId, @Param("chiziId")Long chiziId);
	
	int cancelCare(@Param("userId")Long userId, @Param("chiziId")Long chiziId);
	
	int addNumById(@Param("numType") int numType, @Param("id") Long id);
	
	List<ChiziUser> getCUByUserId(@Param("userId") Long userId);
	
	List<Long> getCUByIdList(@Param("userId") Long userId, @Param("list") List<Long> list);
	
	List<ChiziProject> getCPByProjectId(@Param("projectId") Long projectId);
	
	List<Long> getCPByIdList(@Param("projectId") Long projectId, @Param("list") List<Long> list);
	
	void addCUser(@Param("userId")Long userId, @Param("chiziId")Long chiziId);
	
	void addCProject(@Param("projectId")Long projectId, @Param("chiziId")Long chiziId);
	
	void cancelCUser(@Param("userId")Long userId, @Param("chiziId")Long chiziId);
	
	void cancelCProject(@Param("projectId")Long projectId, @Param("chiziId")Long chiziId);
}
