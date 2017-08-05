package com.finger.birds.db.po.article;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class ArticlePO {

	private Long id;
	private String title;// '标题',
	private String tips1;
	private String tips2;
	private String tips3;
	private String tips4;
	private String tips5;
	private String content;
	private String headImg;// '封面图片',
	private String intro;// '文章摘要',
	private Long userId;// '大白id',
	private String viewTimes;// '查看次数',
	private String starTimes;//
	private String commentTimes;//
	private Boolean isTop;//
	private Boolean isDelete;//
	private Long adminId; // '管理员Id',
	private String createTime;
	private String updateTime;
	private String nickname;
	private String schoolName;
	private String majorName;
	private String companyName;
	private String headImage;
	private String occupationName;

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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(String viewTimes) {
		this.viewTimes = viewTimes;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public String getTips1() {
		return tips1;
	}

	public void setTips1(String tips1) {
		this.tips1 = tips1;
	}

	public String getTips2() {
		return tips2;
	}

	public void setTips2(String tips2) {
		this.tips2 = tips2;
	}

	public String getTips3() {
		return tips3;
	}

	public void setTips3(String tips3) {
		this.tips3 = tips3;
	}

	public String getTips4() {
		return tips4;
	}

	public void setTips4(String tips4) {
		this.tips4 = tips4;
	}

	public String getTips5() {
		return tips5;
	}

	public void setTips5(String tips5) {
		this.tips5 = tips5;
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
