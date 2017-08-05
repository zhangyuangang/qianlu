package com.finger.birds.db.dao.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.UserInfoBean;
import com.finger.birds.model.user.UserInfo;
import com.finger.birds.param.user.UserInfoParam;

@Repository
public interface UserInfoDao {
	
	UserInfo getByUserId(Long userId);
	
	void save(UserInfo userInfo);
	
	void updateByUserId(UserInfo userInfo);
	
	boolean updateUserInfo(@Param("bean")UserInfoBean bean);

	boolean addUserInfo(@Param("bean")UserInfoParam bean);

	void deleteByUserId(Long id);

	void setUTF8mb4();

	void setUTF8();

}
