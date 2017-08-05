package com.finger.birds.db.po.user;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class UserSearchPO{

	private Long id;//
	private String account;// 用户名
	private Short userType;// 用户类型
	private String phone;// 手机号
	private Short source;// 1:微信
	private Short status;//
	private String createTime;//
	private String updateTime;//
	private String thirdKey;// 第三方key
	private String code;// 邀请码
	private Short type; // 分类
	private Integer occType;//行业分类
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Short getSource() {
		return source;
	}

	public void setSource(Short source) {
		this.source = source;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", updateTime);
	}

	public String getThirdKey() {
		return thirdKey;
	}

	public void setThirdKey(String thirdKey) {
		this.thirdKey = thirdKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getOccType() {
		return occType;
	}

	public void setOccType(Integer occType) {
		this.occType = occType;
	}

}
