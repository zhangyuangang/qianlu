package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class OrderQueryBean implements Bean{

	private Long userId;

	private Long tuserId;

	private Byte checkDate;

	private Short status;

	private String orderCode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}

	public Byte getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Byte checkDate) {
		this.checkDate = checkDate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
