package com.finger.birds.db.po.project;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.finger.birds.model.project.ProjectApplyDetail;
import com.finger.birds.utils.date.DateUtils;

public class ProjectApplyPO {

	private Long id;

	private Long userId;

	private String nickname;

	private String headImage;
	
	private String intro;

	private BigDecimal price;

	private String createTime;

	private String planTime;
	
	private List<ProjectApplyDetail> detailList;
	
	private Long projectId;

	private Long tuserId;
	
	private String tnickname;
	
	private String theadImage;
	
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
	
	public String getPriceStr() {
		return price.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
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

	public List<ProjectApplyDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ProjectApplyDetail> detailList) {
		this.detailList = detailList;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
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
