package com.finger.birds.db.po.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class OrderListPO {

	private Long id;//
	private String orderCode;//
	private Long userId;// 付款人Id
	private String nickname;//付款人昵称
	private String openId;//付款人昵称
	private Long tuserId;// 付款对象Id
	private String tnickname;//付款对象人昵称
	private String topenId;//付款对象人昵称
	private String createTime;//
	private String updateTime;//
	private String payTime;//
	private Short status;// 0.未支付1.已支付2.大白确认3.大白否定4.已完成
	private Short timeType;// 1,2,3三个时间段
	private String intro;//
	private BigDecimal price;
	private String startDate;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTopenId() {
		return topenId;
	}
	public void setTopenId(String topenId) {
		this.topenId = topenId;
	}
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", updateTime);
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(Timestamp payTime) {
		this.payTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", payTime);
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
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTnickname() {
		return tnickname;
	}
	public void setTnickname(String tnickname) {
		this.tnickname = tnickname;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = DateUtils.format("yyyy-MM-dd HH:mm:ss", startDate);
	}
	
}
