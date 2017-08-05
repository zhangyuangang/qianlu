package com.finger.birds.db.po.user;

import java.math.BigDecimal;

public class UserExtPO {

	private BigDecimal money;

	private BigDecimal totalMoney;

	private Integer orderNum;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getMoneyStr(){
		if(this.money == null){
			return "0.00";
		}
		return money.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public String getTotalMoneyStr(){
		if(this.totalMoney == null){
			return "0.00";
		}
		return totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
}
