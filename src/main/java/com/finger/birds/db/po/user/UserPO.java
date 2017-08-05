package com.finger.birds.db.po.user;

import com.finger.birds.model.user.User;
import com.finger.birds.model.user.UserInfo;

public class UserPO extends User {

	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
