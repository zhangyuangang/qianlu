package com.finger.birds.model.user;

import java.sql.Timestamp;

public class UserConcern {

	private Long id;//
	private Long userId;//
	private Long tUserId;//
	private Short type;// 1:关注
	private Timestamp createTime;//

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

	public Long gettUserId() {
		return tUserId;
	}

	public void settUserId(Long tUserId) {
		this.tUserId = tUserId;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
