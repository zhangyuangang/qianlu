package com.finger.birds.db.po.dynamic;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class DynamicCommentPO {

	private Long id;
	private Long userId;
	private String nickname;
	private String headImage;
	private Long tuserId;
	private String tnickname;
	private String theadImage;
	private Long dynamicId;
	private String content;
	private String starTimes;//
	private Boolean isTop;//
	private Boolean isDelete;//
	private Boolean isStar;
	private String createTime;
	private String updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTuserId() {
		return tuserId;
	}
	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getStarTimes() {
		return starTimes;
	}
	public void setStarTimes(String starTimes) {
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
	public Boolean getIsStar() {
		return isStar;
	}
	public void setIsStar(Boolean isStar) {
		this.isStar = isStar;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTnickname() {
		return tnickname;
	}
	public void setTnickname(String tnickname) {
		this.tnickname = tnickname;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getTheadImage() {
		return theadImage;
	}
	public void setTheadImage(String theadImage) {
		this.theadImage = theadImage;
	}

}
