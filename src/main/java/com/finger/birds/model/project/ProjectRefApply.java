package com.finger.birds.model.project;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProjectRefApply {

	private Long id;//
	private Long projectId;// 项目id
	private Long applyId;// 标id
	private Timestamp createTime;// 中标时间
	private String commentText;// 评论
	private BigDecimal commentScore;// 评分
	private Timestamp endTime;// 结标时间
	private Short status;// 1.开始2.部分完成.3完成
	private Short commentStatus;// 00,01菜,10老,11
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public BigDecimal getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(BigDecimal commentScore) {
		this.commentScore = commentScore;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Short commentStatus) {
		this.commentStatus = commentStatus;
	}

}
