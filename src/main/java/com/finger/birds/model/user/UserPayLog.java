package com.finger.birds.model.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserPayLog {

	private Long id;//
	private BigDecimal money;// 金额
	private Short type;// 支付类型1:微信支付
	private Short targetType;// 业务类型1:指点付款2.参考付款
	private Long targetId;// 指点付款时为applyId,参考付款为:sumupId
	private Long userId;// 付款人
	private String code;// 付款订单号
	private String thridCode;// 第三方支付流水号
	private Short callbackStatus;// 0:未回调，1:接受同步回调2:异步回调成功3:异步回调失败
	private Timestamp createTime;//
	private Timestamp synCallbackTime;// 同步回调时间
	private Timestamp asnCallbackTime;// 异步回调时间

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

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getTargetType() {
		return targetType;
	}

	public void setTargetType(Short targetType) {
		this.targetType = targetType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getThridCode() {
		return thridCode;
	}

	public void setThridCode(String thridCode) {
		this.thridCode = thridCode;
	}

	public Short getCallbackStatus() {
		return callbackStatus;
	}

	public void setCallbackStatus(Short callbackStatus) {
		this.callbackStatus = callbackStatus;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getSynCallbackTime() {
		return synCallbackTime;
	}

	public void setSynCallbackTime(Timestamp synCallbackTime) {
		this.synCallbackTime = synCallbackTime;
	}

	public Timestamp getAsnCallbackTime() {
		return asnCallbackTime;
	}

	public void setAsnCallbackTime(Timestamp asnCallbackTime) {
		this.asnCallbackTime = asnCallbackTime;
	}

}
