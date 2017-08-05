package com.finger.birds.db.dao.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConcernDao {

	int getCareCount(Long userId);

	int getTCareCount(Long userId);

	int checkIsConcern(@Param("tUserId") Long tUserId, @Param("userId") Long userId);
	
	void save(@Param("tUserId") Long tUserId, @Param("userId") Long userId);
	
	void del(@Param("tUserId") Long tUserId, @Param("userId") Long userId);

	void deleteByUserId(Long id);

	void update(@Param("tUserId") Long tUserId, @Param("userId") Long userId);
}
