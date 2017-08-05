package com.finger.birds.db.dao.intelligent;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.intelligent.WeightPO;
import com.finger.birds.model.intelligent.Weight;

@Repository
public interface WeightDao {

	boolean add(@Param("bean")Weight bean);

	boolean deleteByIdTable(@Param("toId")Long toId, @Param("toTable")String toTable);
	
	boolean editByIdTable(@Param("bean")Weight bean);

	WeightPO getByIdTable(@Param("toId")Long toId, @Param("toTable")String toTable);
	
}
