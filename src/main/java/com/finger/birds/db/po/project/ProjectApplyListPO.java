package com.finger.birds.db.po.project;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class ProjectApplyListPO {

	private Long id;

	private Long userId;

	private String nickname;

	private String intro;

	private BigDecimal price;

	private String createTime;

	private String planTime;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd", createTime);
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Timestamp planTime) {
		this.planTime = DateUtils.format("yyyy-MM-dd", planTime);
	}

}
