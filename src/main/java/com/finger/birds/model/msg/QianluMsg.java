package com.finger.birds.model.msg;

import java.sql.Timestamp;

public class QianluMsg {

	private Long id;
	private Long userId;
	private Long tuserId;
	private String lastest;
	private Timestamp createTime;
	private Byte isRead;
	private Timestamp updateTime;

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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
