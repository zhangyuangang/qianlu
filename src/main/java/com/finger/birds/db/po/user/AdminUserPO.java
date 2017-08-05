package com.finger.birds.db.po.user;

import java.util.Date;

import com.finger.birds.utils.date.DateUtils;

public class AdminUserPO {

	private Long id;//
	private String username;// 用户名
	private String password;// 密码
	private String createTime;//
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);;
	}

}
