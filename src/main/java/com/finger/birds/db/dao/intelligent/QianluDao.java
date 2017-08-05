package com.finger.birds.db.dao.intelligent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.intelligent.QianluPO;
import com.finger.birds.model.intelligent.Qianlu;

@Repository
public interface QianluDao {

	boolean add(Qianlu bean);

	boolean readById(Long id);
	
	boolean editById(Qianlu bean);

	Qianlu getMinByUserId(Long userId);
	
	Integer getNotReadCountByUserId(Long userId);
	
	Integer getCountByUserId(Long userId);
	
	Long getIsReadIdByUserId(Long userId);

	List<QianluPO> getMessage(Long userId);
	
}
