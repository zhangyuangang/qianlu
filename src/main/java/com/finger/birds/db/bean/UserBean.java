package com.finger.birds.db.bean;

import org.springframework.beans.BeanUtils;

import com.finger.birds.model.user.User;
import com.finger.birds.model.user.UserInfo;
import com.finger.birds.utils.bean.Bean;

public class UserBean implements Bean{
	
	private Long id;
	
	private String nickname;
	
	private String headImgUrl;
	
	private Short sex;
	
	private String thirdKey;
	
	private String phone;
	
	private String source = "1";
	
	private String code;
	
	private short status;
	
	private Short userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getThirdKey() {
		return thirdKey;
	}

	public void setThirdKey(String thirdKey) {
		this.thirdKey = thirdKey;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}
	
	public User initUser(){
		User u = new User();
		BeanUtils.copyProperties(this, u);
		u.setStatus(status);
		return u;
	}
	
	public UserInfo initUserInfo(){
		UserInfo userInfo = new UserInfo();
		userInfo.setHeadImage(headImgUrl);
		userInfo.setSex(sex);
		userInfo.setNickname(nickname);
		return userInfo;
	}
}
