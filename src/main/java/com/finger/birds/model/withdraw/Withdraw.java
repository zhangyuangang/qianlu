package com.finger.birds.model.withdraw;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Withdraw {

	private Long id;// 提现id
	private Long userId;// 发起人
	private BigDecimal money;// 提现金额 如果为，空则全部提取
	private Timestamp createTime;// 发起提现时间
	private Short status;// 1:发起提现，2:用户撤销提现，3：后台管理员撤销，4：审批通过，5，提现完成
	private Long adminId;// 管理员id
	private Timestamp updateTime;//
	private Timestamp completeTime;// 完成提现时间

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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

}
