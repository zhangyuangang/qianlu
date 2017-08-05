package com.finger.birds.db.po.order;

import java.math.BigDecimal;
import java.util.List;

public class OrderAmountPO {

	private String code;//
	private BigDecimal payAmount;
	private String nickname;//
	private Long userType;// 付款人Id
	private Long userId;// 付款人Id
	private List<String> orderCode;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getUserType() {
		return userType;
	}
	public void setUserType(Long userType) {
		this.userType = userType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<String> getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(List<String> orderCode) {
		this.orderCode = orderCode;
	}

	
}
