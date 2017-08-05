package com.finger.birds.db.po;

public class BirdsMsgPO {

	private Long userId;
	
	private Long projectId;
	
	private String counts;

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

	public String getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts > 99 ? "99+" : counts + "";
	}
	
}
