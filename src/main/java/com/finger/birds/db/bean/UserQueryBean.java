package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class UserQueryBean implements Bean {

	private String id;
	
	private Integer occType;
	
	private Long cuserId;
	
	private Short userType;
	
	private Boolean isHot;
	
	private String companyNamee;
	
	private String tradeName;
	
	public String getId() {
		return id;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOccType() {
		return occType;
	}

	public void setOccType(Integer occType) {
		this.occType = occType;
	}

	public Long getCuserId() {
		return cuserId;
	}

	public void setCuserId(Long cuserId) {
		this.cuserId = cuserId;
	}

	public Short getUserType() {
		return userType;
	}

	public String getCompanyNamee() {
		return companyNamee;
	}

	public void setCompanyNamee(String companyNamee) {
		this.companyNamee = companyNamee;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}
	
}
