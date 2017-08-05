package com.finger.birds.db.po.banner;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class BannerListPO {

	private Long id;
	private String title;
	private String cover;
	private String detils;
	private String createTime;
	private String updateTime;

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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDetils() {
		return detils;
	}

	public void setDetils(String detils) {
		this.detils = detils;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", updateTime);
	}

}
