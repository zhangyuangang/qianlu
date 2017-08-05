package com.finger.birds.model.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserComment {

	private Long id;//
	private Long userId;// 评论的人Id
	private Long projectId;// 项目Id
	private Long applyId;// 中标Id
	private Long tUserId;// 被评价人Id
	private BigDecimal commentScore;// 评分
	private String commentText;// 评论内容
	private Timestamp createTime;//

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

	public Long getTUserId() {
		return tUserId;
	}

	public void setTUserId(Long tUserId) {
		this.tUserId = tUserId;
	}

	public BigDecimal getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(BigDecimal commentScore) {
		this.commentScore = commentScore;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
