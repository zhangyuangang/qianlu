package com.finger.birds.db.po.intelligent;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class CountPO {
	
	private Long id;
	private Long userId;
	private Long toUserId;
	private Long toUserId2;
	private Long toId;
	private String toTable;
	private Integer score;
	private String createTime;
	
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
	public Long getToUserId() {
		return toUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public Long getToUserId2() {
		return toUserId2;
	}
	public void setToUserId2(Long toUserId2) {
		this.toUserId2 = toUserId2;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
	}
	public String getToTable() {
		return toTable;
	}
	public void setToTable(String toTable) {
		this.toTable = toTable;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);
	}
	
}
