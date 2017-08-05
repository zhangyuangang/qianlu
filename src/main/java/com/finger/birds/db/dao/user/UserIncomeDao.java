package com.finger.birds.db.dao.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.user.UserIncomeStatPO;
import com.finger.birds.model.user.UserIncomeLog;
import com.finger.birds.model.user.UserPayLog;

@Repository
public interface UserIncomeDao {

	UserIncomeStatPO getCountByUserId(Long userId);

	void save(Long userId);
	
	void updateByUserId(@Param("userId")Long userId, @Param("money")BigDecimal money, @Param("type")Integer type);
	
	void addLog(UserIncomeLog log);
	
	void addPayLog(UserPayLog payLog);
	
	void updatePayLog(@Param("targetId")Long targetId, @Param("thridCode")String thridCode, @Param("asnCallbackTime")Timestamp asnCallbackTime);
	
	UserPayLog getPayLogByCode(Long orderId);
	
	void updateLogStatusByPayLogId(@Param("payLogId") Long payLogId, @Param("status")Byte status);

	void deleteByUserId(Long id);
}
