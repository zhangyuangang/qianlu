package com.finger.birds.model.dynamic;

import java.sql.Timestamp;

public class Dynamic {

	private Long id;// 精选广播Id（动态）
	private Long userId;// 大白Id
	private String content;// 动态内容
	private String imgUrls;// 图片路径，<split>隔开
	private Timestamp createTime;//
	private Long adminId;// 管理员id
	private Timestamp updateTime;//
	private String viewTimes;//
	private String starTimes;//
	private String commentTimes;//
	private Boolean isTop;//
	private Boolean isDelete;//
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
	public String getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(String viewTimes) {
		this.viewTimes = viewTimes;
	}
	public String getStarTimes() {
		return starTimes;
	}
	public void setStarTimes(String starTimes) {
		this.starTimes = starTimes;
	}
	public String getCommentTimes() {
		return commentTimes;
	}
	public void setCommentTimes(String commentTimes) {
		this.commentTimes = commentTimes;
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

}
