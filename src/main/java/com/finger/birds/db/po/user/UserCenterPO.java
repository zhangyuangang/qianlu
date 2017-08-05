package com.finger.birds.db.po.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserCenterPO {

	private String nickname;
	private Long userId;
	private String code;
	private String companyName;
	private String headImage;// 头像地址
	private Short sex;// 0:未知,1:男,2:女
	private Timestamp createTime;//
	private String schoolName;// 所在学校名称
	private String majorName;// 专业名称
	private String occupationName;// 职业名称
	private String tradeName;// 所属于行业
	private String occType;// 所属于岗位
	private String intro;// 自我介绍
	private BigDecimal totalIncome;
	private Short status;
	private short isSelf;
	private short userType;
	private Boolean isHot;
	private String resTxt;
	private String majorName2;
	private String place;
	//权重表
	private Long weightId;
	private Double gw_yx;
	private Double gw_cp;
	private Double gw_gl;
	private Double gw_js;
	private Double gw_cw;
	private Double gw_qt;
	private Double hy_it;
	private Double hy_jr;
	private Double hy_ct;
	private Double hy_qt;
	private Integer liveness;
	private Integer hotness;
	
	public String getCode() {
		return code;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOccType() {
		return occType;
	}

	public void setOccType(String occType) {
		this.occType = occType;
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

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome.setScale(2, BigDecimal.ROUND_HALF_UP);
	}


	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}


	public short getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(short isSelf) {
		this.isSelf = isSelf;
	}

	public short getUserType() {
		return userType;
	}

	public void setUserType(short userType) {
		this.userType = userType;
	}

	public String getResTxt() {
		return resTxt;
	}

	public void setResTxt(String resTxt) {
		this.resTxt = resTxt;
	}

	public String getMajorName2() {
		return majorName2;
	}

	public void setMajorName2(String majorName2) {
		this.majorName2 = majorName2;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Long getWeightId() {
		return weightId;
	}

	public void setWeightId(Long weightId) {
		this.weightId = weightId;
	}

	public Double getGw_yx() {
		return gw_yx;
	}

	public void setGw_yx(Double gw_yx) {
		this.gw_yx = gw_yx;
	}

	public Double getGw_cp() {
		return gw_cp;
	}

	public void setGw_cp(Double gw_cp) {
		this.gw_cp = gw_cp;
	}

	public Double getGw_gl() {
		return gw_gl;
	}

	public void setGw_gl(Double gw_gl) {
		this.gw_gl = gw_gl;
	}

	public Double getGw_js() {
		return gw_js;
	}

	public void setGw_js(Double gw_js) {
		this.gw_js = gw_js;
	}

	public Double getGw_cw() {
		return gw_cw;
	}

	public void setGw_cw(Double gw_cw) {
		this.gw_cw = gw_cw;
	}

	public Double getGw_qt() {
		return gw_qt;
	}

	public void setGw_qt(Double gw_qt) {
		this.gw_qt = gw_qt;
	}

	public Double getHy_it() {
		return hy_it;
	}

	public void setHy_it(Double hy_it) {
		this.hy_it = hy_it;
	}

	public Double getHy_jr() {
		return hy_jr;
	}

	public void setHy_jr(Double hy_jr) {
		this.hy_jr = hy_jr;
	}

	public Double getHy_ct() {
		return hy_ct;
	}

	public void setHy_ct(Double hy_ct) {
		this.hy_ct = hy_ct;
	}

	public Double getHy_qt() {
		return hy_qt;
	}

	public void setHy_qt(Double hy_qt) {
		this.hy_qt = hy_qt;
	}

	public Integer getLiveness() {
		return liveness;
	}

	public void setLiveness(Integer liveness) {
		this.liveness = liveness;
	}

	public Integer getHotness() {
		return hotness;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public void setHotness(Integer hotness) {
		this.hotness = hotness;
	}
}
