package com.finger.birds.db.po.project;

import java.math.BigDecimal;

public class ProjectListPO {

	private String name;
	private String desc;
	private Integer status;
	private BigDecimal startPrice;
	private BigDecimal endPrice;
	private Integer applyTimes;// 竞标次数
	private Integer times; // 查看次数
	private Integer joins;
	private Integer exacts;
	private BigDecimal commentScore;
	private BigDecimal price;
	private Long id;
	private Long sumupId;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public Integer getApplyTimes() {
		return applyTimes;
	}

	public void setApplyTimes(Integer applyTimes) {
		this.applyTimes = applyTimes;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getJoins() {
		return joins;
	}

	public void setJoins(Integer joins) {
		this.joins = joins;
	}

	public Integer getExacts() {
		return exacts;
	}

	public void setExacts(Integer exacts) {
		this.exacts = exacts;
	}

	public BigDecimal getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(BigDecimal commentScore) {
		this.commentScore = commentScore;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getSumupId() {
		return sumupId;
	}

	public void setSumupId(Long sumupId) {
		this.sumupId = sumupId;
	}

	public String getEndPriceStr(){
		return endPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
}
