package com.finger.birds.model.msg;

import java.sql.Timestamp;

public class BirdsMsg {

	private Long id;
	
	private Short type; //
	
	private Long userId;
	
	private Long projectId;
	
	private Timestamp createTime;
	
	private Byte isRead;
	
	private String msg;

	
	
	public BirdsMsg(Short type, Long userId, Long projectId, String msg) {
		this.type = type;
		this.userId = userId;
		this.projectId = projectId;
		this.msg = msg;
	}
	//for update
	public BirdsMsg(Short type, Long userId, Long projectId) {
		this.type = type;
		this.userId = userId;
		this.projectId = projectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
