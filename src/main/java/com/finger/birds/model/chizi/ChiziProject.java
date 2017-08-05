package com.finger.birds.model.chizi;

import java.sql.Timestamp;

public class ChiziProject {

	private Long id;

	private Long projectId;

	private Long chiziId;

	private Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getChiziId() {
		return chiziId;
	}

	public void setChiziId(Long chiziId) {
		this.chiziId = chiziId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
