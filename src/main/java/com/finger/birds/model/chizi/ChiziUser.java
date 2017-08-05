package com.finger.birds.model.chizi;

import java.sql.Timestamp;

public class ChiziUser {

	private Long id;

	private Long userId;

	private Long chiziId;

	private Timestamp createTime;

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
