package com.finger.birds.model.intelligent;

import java.sql.Timestamp;

public class Qianlu {
	
	private Long id;
	private Long userId;
	private String content;
	private Short type; //1前路头条，2前路消息，3用户消息
	private String url;   //type为1 的时候url为全链接
	private Double score;
	private Boolean isRead;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public Qianlu() {
		super();
	}
	
	public Qianlu(Long id, Long userId, String content, Short type, String url, Double score) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.type = type;
		this.url = url;
		this.score = score;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
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
