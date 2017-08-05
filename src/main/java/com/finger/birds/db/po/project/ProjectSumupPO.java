package com.finger.birds.db.po.project;

import java.math.BigDecimal;
import java.util.List;

import com.finger.birds.model.project.ProjectSumup;
import com.finger.birds.model.project.ProjectSumupDetail;

public class ProjectSumupPO extends ProjectSumup{

	private BigDecimal commentScore;
	
	
	
	private List<ProjectSumupDetail> detail;
	
	private Byte isExact; 
	
	private Long puserId;
	
	private Long auserId;

	public BigDecimal getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(BigDecimal commentScore) {
		this.commentScore = commentScore;
	}

	public List<ProjectSumupDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<ProjectSumupDetail> detail) {
		this.detail = detail;
	}
	
	public Byte getIsExact() {
		return isExact;
	}

	public void setIsExact(Byte isExact) {
		this.isExact = isExact;
	}

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

	public String getPriceStr(){
		if(super.getPrice() == null){
			return "";
		} else {
			return super.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		}
	}
}
