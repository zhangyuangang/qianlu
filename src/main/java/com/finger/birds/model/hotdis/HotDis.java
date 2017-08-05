package com.finger.birds.model.hotdis;

import java.sql.Timestamp;

public class HotDis {
	
	private Long id;// 热论id
	private String title;//
	private String content;//
	private Timestamp createTime;//
	private Timestamp updateTime;//
	private Short month;// 月份
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Short getMonth() {
		return month;
	}
	public void setMonth(Short month) {
		this.month = month;
	}

}
