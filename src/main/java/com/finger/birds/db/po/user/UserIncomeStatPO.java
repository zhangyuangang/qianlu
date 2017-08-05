package com.finger.birds.db.po.user;

import java.math.BigDecimal;

public class UserIncomeStatPO {

	private Long userId;
	
	private String nickname;
	
	private BigDecimal ans;
	
	private BigDecimal projectRef;
	
	private BigDecimal ansRef;
	
	private BigDecimal total;

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

	public BigDecimal getAns() {
		return ans;
	}

	public void setAns(BigDecimal ans) {
		this.ans = ans.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getProjectRef() {
		return projectRef;
	}

	public void setProjectRef(BigDecimal projectRef) {
		this.projectRef = projectRef.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getAnsRef() {
		return ansRef;
	}

	public void setAnsRef(BigDecimal ansRef) {
		this.ansRef = ansRef.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = ans.add(ansRef).add(projectRef).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}
