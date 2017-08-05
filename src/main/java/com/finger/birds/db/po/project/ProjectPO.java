package com.finger.birds.db.po.project;

import java.math.BigDecimal;

import com.finger.birds.model.project.Project;
import com.finger.birds.utils.date.DateUtils;

public class ProjectPO extends Project {
	
	private int isSelf; //1查看自己的，0不是查看自己的,2自己投标的
	
	private Long applyId;
	
	public int getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(int isSelf) {
		this.isSelf = isSelf;
	}
	
	@Override
	public BigDecimal getStartPrice(){
		return super.getStartPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public BigDecimal getEndPrice(){
		return super.getEndPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public String getEndTimeStr(){
		return DateUtils.format("yyyy-MM-dd", super.getEndTime());
	}
	
	public String getStartTimeStr(){
		return DateUtils.format("yyyy-MM-dd", super.getStartTime());
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

}
