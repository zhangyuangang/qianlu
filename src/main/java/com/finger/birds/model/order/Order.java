package com.finger.birds.model.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {

	private Long id;//
	private String orderCode;//
	private Long userId;// 付款人Id
	private Long tuserId;// 付款对象Id
	private Timestamp createTime;//
	private Timestamp updateTime;//
	private Timestamp payTime;//
	private Short status;// 0.未支付1.已支付2.大白确认3.大白否定4.已完成.
	private Short timeType;// 1,2,3三个时间段
	private String intro;//
	private BigDecimal price;
	private Timestamp startDate;
	private short xudan;
	private Byte isSend;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getTimeType() {
		return timeType;
	}

	public void setTimeType(Short timeType) {
		this.timeType = timeType;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Short getXudan() {
		return xudan;
	}

	public void setXudan(Short xudan) {
		this.xudan = xudan;
	}

	public Byte getIsSend() {
		return isSend;
	}

	public void setIsSend(Byte isSend) {
		this.isSend = isSend;
	}

}
