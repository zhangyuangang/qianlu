package com.finger.birds.db.dao.msg;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.BirdsMsgPO;
import com.finger.birds.model.msg.BirdsMsg;

@Repository
public interface BirdsMsgDao {
	
	void save(BirdsMsg msg);
	
	void readed(BirdsMsg msg);
	
	int getCount(@Param("userId")Long userId, @Param("type") Short type);
	
	BirdsMsgPO getMsgCount(@Param("userId")Long userId, @Param("type") Short type);
}
