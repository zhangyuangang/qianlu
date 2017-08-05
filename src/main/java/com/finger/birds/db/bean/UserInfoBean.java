package com.finger.birds.db.bean;

import com.finger.birds.model.user.UserInfo;
import com.finger.birds.utils.bean.Bean;

public class UserInfoBean extends UserInfo implements Bean {

	private Short status;// -1表示删除,1表示正常,3表示匿名

	private String code;


	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
