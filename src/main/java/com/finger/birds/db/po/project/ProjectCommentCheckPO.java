package com.finger.birds.db.po.project;

public class ProjectCommentCheckPO {

	private Long puserId;
	
	private Long auserId;
	
	private Short refStatus;
	
	private Long refId;

	private Long applyId;
	
	private Long projectId;
	
	private Short commentStatus;
	
	public Long getPuserId() {
		return puserId;
	}

	public void setPuserId(Long puserId) {
		this.puserId = puserId;
	}

	public Long getAuserId() {
		return auserId;
	}

	public void setAuserId(Long auserId) {
		this.auserId = auserId;
	}

	public Short getRefStatus() {
		return refStatus;
	}

	public void setRefStatus(Short refStatus) {
		this.refStatus = refStatus;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Short getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Short commentStatus) {
		this.commentStatus = commentStatus;
	}
	
}
