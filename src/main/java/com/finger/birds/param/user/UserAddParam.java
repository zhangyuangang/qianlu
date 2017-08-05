package com.finger.birds.param.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.UserBean;
import com.finger.birds.utils.param.BeanParam;

public class UserAddParam implements BeanParam<UserBean> {

	private String headImgUrl;

	private String code;

	private String sexStr;

	@NotEmpty
	private String unionid;

	@NotEmpty
	private String nickname;

	private short status = 1;

	//@NotEmpty
	private String phone;

	@NotNull(message="[userType]用户类型不能为空")
	private Short userType;
	
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	@Override
	public UserBean initBean() {
		UserBean bean = new UserBean();
		BeanUtils.copyProperties(this, bean);
		bean.setThirdKey(unionid);
		short sex = 0;
		try{
			sex = Short.parseShort(this.getSexStr());
		} catch(Exception e){
			//Do nothing
		}
		bean.setSex(sex);
		return bean;
	}

}
