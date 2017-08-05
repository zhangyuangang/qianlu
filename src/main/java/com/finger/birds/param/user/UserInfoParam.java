package com.finger.birds.param.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.UserInfoBean;
import com.finger.birds.utils.param.BeanParam;

public class UserInfoParam implements BeanParam<UserInfoBean> {

	private String code;
	private Long userId;
	@Length(max=64)
	private String nickname;// 昵称
	private String headImage;// 头像地址
	private Short sex;// 0:未知,1:男,2:女
	@Length(max=128)
	private String schoolName;// 所在学校名称
	@Length(max=128)
	private String majorName;// 专业名称
	@Length(max=128)
	private String companyName;// 公司名称
	@Length(max=128)
	private String occupationName;// 职业名称
	@Length(max=128)
	private String tradeName;// 所属于行业
	private Integer occType;//行业分类
	@Length(max=1024)
	private String intro;// 自我介绍
	private Short status = 1;
	@Length(max=128)
	private String majorName2;
	@Length(max=1024)
	private String resTxt;
	@Length(max=128)
	private String place;
	@Length(max=1024)
	private String hob;
	@Length(max=64)
	private String workStatus;
	@Length(max=128)
	private String schoolName2;// 所在学校名称
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public Integer getOccType() {
		return occType;
	}

	public void setOccType(Integer occType) {
		this.occType = occType;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getMajorName2() {
		return majorName2;
	}

	public void setMajorName2(String majorName2) {
		this.majorName2 = majorName2;
	}

	public String getResTxt() {
		return resTxt;
	}

	public void setResTxt(String resTxt) {
		this.resTxt = resTxt;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getHob() {
		return hob;
	}

	public void setHob(String hob) {
		this.hob = hob;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getSchoolName2() {
		return schoolName2;
	}

	public void setSchoolName2(String schoolName2) {
		this.schoolName2 = schoolName2;
	}

	@Override
	public UserInfoBean initBean() {
		UserInfoBean bean = new UserInfoBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
