package com.finger.birds.model.project;

import java.math.BigDecimal;

public class ProjectApplyDetail {

	private Long id; //
	private Long applyId; // 投标id
	private String content; // 描述
	private Integer payPer; // 付费比例
	private BigDecimal price; // 费用
	private Integer sort; // 排序，从小到大
	private Short obStatus;
	private Short nbStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPayPer() {
		return payPer;
	}

	public void setPayPer(Integer payPer) {
		this.payPer = payPer;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public String getPriceStr() {
		return price.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Short getObStatus() {
		return obStatus;
	}

	public void setObStatus(Short obStatus) {
		this.obStatus = obStatus;
	}

	public Short getNbStatus() {
		return nbStatus;
	}

	public void setNbStatus(Short nbStatus) {
		this.nbStatus = nbStatus;
	}

}
