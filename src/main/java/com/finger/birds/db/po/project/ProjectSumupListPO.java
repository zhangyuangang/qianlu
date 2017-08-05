package com.finger.birds.db.po.project;

import java.math.BigDecimal;

public class ProjectSumupListPO {
	private Long id;
	private String nickname;
	private String name;
	private String desc;
	private String price;
	private int exacts;
	private int joins;
	private String industryName;
	private String professionName;
	private String refPrice;
	private String companyName;
	private BigDecimal commentScore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public int getExacts() {
		return exacts;
	}

	public void setExacts(int exacts) {
		this.exacts = exacts;
	}

	public int getJoins() {
		return joins;
	}

	public void setJoins(int joins) {
		this.joins = joins;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getRefPrice() {
		return refPrice;
	}

	public void setRefPrice(BigDecimal refPrice) {
		this.refPrice = refPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public BigDecimal getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(BigDecimal commentScore) {
		this.commentScore = commentScore;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
