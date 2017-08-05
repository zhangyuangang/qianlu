package com.finger.birds.param.user;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.UserSearchBean;
import com.finger.birds.db.po.user.UserSearchPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class UserSearchParam extends PageParam<UserSearchPO> implements BeanParam<UserSearchBean> {

	private Long id;
	private String start;
	private String code;
	
	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	@Override
	public UserSearchBean initBean() {
		UserSearchBean bean = new UserSearchBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
