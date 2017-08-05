package com.finger.birds.model.dynamic;

import java.sql.Timestamp;

public class DynamicComment {
	
	private Long id;
	private Long userId;
	private Long tuserId;
	private Long dynamicId;
	private String content;
	private Integer starTimes;
	private Boolean isTop;
	private Boolean isDelete;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public DynamicComment() {
		super();
	}
	
	public DynamicComment(Long userId, Long tuserId, Long dynamicId, String content) {
		super();
		this.userId = userId;
		this.tuserId = tuserId;
		this.dynamicId = dynamicId;
		this.content = content;
	}

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
	public Long getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(Long dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStarTimes() {
		return starTimes;
	}
	public void setStarTimes(Integer starTimes) {
		this.starTimes = starTimes;
	}
	public Boolean getIsTop() {
		return isTop;
	}
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

}
