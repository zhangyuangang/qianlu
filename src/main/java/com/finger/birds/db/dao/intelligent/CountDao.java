package com.finger.birds.db.dao.intelligent;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.intelligent.CountPO;
import com.finger.birds.model.intelligent.Count;

@Repository
public interface CountDao {

	boolean add(@Param("bean")Count bean);

	CountPO getList(@Param("userId")Long userId, @Param("toUserId")Long toUserId, @Param("toId")Long toId, @Param("toTable")String toTable);
	
	boolean deleteByAll(@Param("userId")Long userId, @Param("toUserId")Long toUserId, @Param("toId")Long toId, @Param("toTable")String toTable, @Param("score")Integer score);

	List<CountPO> getListPreHourByNotToNoll();
}
