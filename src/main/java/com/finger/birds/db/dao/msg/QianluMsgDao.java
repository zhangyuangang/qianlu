package com.finger.birds.db.dao.msg;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.msg.QianluMsgListPO;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.model.msg.QianluMsgHistory;

@Repository
public interface QianluMsgDao {
	
	void save(QianluMsg msg);
	
	void updateById(@Param("qianlu")QianluMsg qianlu, @Param("id")Long id);
	
	List<QianluMsgListPO> getList(@Param("userId")Long userId, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int getCount(Long userId);
	
	QianluMsg checkExsit(@Param("userId")Long userId, @Param("tuserId")Long tuserId);
	
	void addHistory(QianluMsgHistory history);

	List<QianluMsgHistory> getMsgList(@Param("id1")Long id1, @Param("id2")Long id2);
}
