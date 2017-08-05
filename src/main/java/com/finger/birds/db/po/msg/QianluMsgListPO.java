package com.finger.birds.db.po.msg;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class QianluMsgListPO {

	private Long id;
	private Long userId;
	private Long tuserId;
	private String lastest;
	private String createTime;
	private Byte isRead;
	private String updateTime;
	private String tusername;
	private String theadImg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}

	public String getLastest() {
		return lastest;
	}

	public void setLastest(String lastest) {
		this.lastest = lastest;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", updateTime);
	}

	public String getTusername() {
		return tusername;
	}

	public void setTusername(String tusername) {
		this.tusername = tusername;
	}

	public String getTheadImg() {
		return theadImg;
	}

	public void setTheadImg(String theadImg) {
		this.theadImg = theadImg;
	}

}
