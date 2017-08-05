package com.finger.birds.model.project;

import java.sql.Timestamp;

public class ProjectSumupUser {

	private Long id;//
	private Long sumupId;// 提炼Id
	private Long userId;// 参考人Id
	private Timestamp createTime;// 参考时间
	private Byte isExact;// 觉得是否到位
	private Timestamp endTime;// 权限有效期（预留）
	private Byte status;//付款是否成功， 目前默认1，需要付款的时候默认0

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSumupId() {
		return sumupId;
	}

	public void setSumupId(Long sumupId) {
		this.sumupId = sumupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Byte getIsExact() {
		return isExact;
	}

	public void setIsExact(Byte isExact) {
		this.isExact = isExact;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
