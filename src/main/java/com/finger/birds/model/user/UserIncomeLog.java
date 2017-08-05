package com.finger.birds.model.user;

import java.math.BigDecimal;

public class UserIncomeLog {

	private Long id;//
	private BigDecimal money;//
	private Byte flow;// 0负数，1正数
	private Short type;// 0.订单 1.提现支出申请 2.提现支出
	private Long payLogId;// 支付流水号
	private Long createTime;//
	private Long userId;//
	private Byte status;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Byte getFlow() {
		return flow;
	}

	public void setFlow(Byte flow) {
		this.flow = flow;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Long getPayLogId() {
		return payLogId;
	}

	public void setPayLogId(Long payLogId) {
		this.payLogId = payLogId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
