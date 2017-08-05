package com.finger.birds.param.user;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class UserQueryParam extends PageParam<UserCenterPO> implements BeanParam<UserQueryBean> {

	private Integer occType;
	
	private Long cuserId;
	
	private Boolean isHot;
	
	private Short userType = 1;
	
	private String companyNamee;
	
	private String tradeName;
	
	public Integer getOccType() {
		return occType;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
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

	public void setUserType(Short userType) {
		this.userType = userType;
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

	@Override
	public UserQueryBean initBean() {
		UserQueryBean bean = new UserQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
