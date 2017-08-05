package com.finger.birds.model.user;

import java.sql.Timestamp;

public class UserInfo {

	private Long id;//
	private Long userId;// 用户Id
	private String phone;
	private String account;
	private String password;
	private String thirdKey;
	private String code;
	private String nickname;// 昵称
	private String headImage;// 头像地址
	private Short sex;// 0:未知,1:男,2:女
	private Timestamp createTime;//
	private Timestamp updateTime;//
	private String schoolName;// 所在学校名称
	private String majorName;// 专业名称
	private String companyName;// 公司名称
	private String occupationName;// 职业名称
	private String tradeName;// 所属于行业
	private Integer occType;//行业分类
	private String intro;// 自我介绍
	private String majorName2; // 研究生专业名称
	private String resTxt; //个人资源
	private String place; //居住地方
	private String hob;
	private String workStatus;
	private String schoolName2;
	
	public Long getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public Integer getOccType() {
		return occType;
	}

	public void setOccType(Integer occType) {
		this.occType = occType;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getThirdKey() {
		return thirdKey;
	}

	public void setThirdKey(String thirdKey) {
		this.thirdKey = thirdKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}
