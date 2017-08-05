package com.finger.birds.db.po.hotdis;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class HotDisMsgListPO {

	private Long id;
	private Long userId;
	private String content;
	private String createTime;
	private String updateTime;
	private Byte status;
	private Long hotDiscussId;
	private String headImage;
	private String nickname;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy/MM/dd", createTime);
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = DateUtils.format("yyyy/MM/dd", updateTime);
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getHotDiscussId() {
		return hotDiscussId;
	}

	public void setHotDiscussId(Long hotDiscussId) {
		this.hotDiscussId = hotDiscussId;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
