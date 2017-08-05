package com.finger.birds.model.hotdis;

import java.sql.Timestamp;

public class HotDisMsg {

	private Long id;
	private Long userId;
	private String content;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Byte status;
	private Long hotDiscussId;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getHotDiscussId() {
		return hotDiscussId;
	}

	public void setHotDiscussId(Long hotDiscussId) {
		this.hotDiscussId = hotDiscussId;
	}

}
