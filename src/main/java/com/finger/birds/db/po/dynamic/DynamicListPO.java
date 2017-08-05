package com.finger.birds.db.po.dynamic;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class DynamicListPO {

	private Long id;// 精选广播Id（动态）
	private Long userId;// 大白Id
	private String code;// 大白Id
	private String content;// 动态内容
	private String imgUrls;// 图片路径，<split>隔开
	private Long adminId;// 管理员id
	private String viewTimes;//
	private String starTimes;//
	private String commentTimes;//
	private Boolean isTop;//
	private Boolean isDelete;//
	private Boolean isStar;//
	private String createTime;
	private String updateTime;
	private String headImage;
	private String schooleName;
	private String majorName;
	private String companyName;
	private String occupationName;
	private String nickname;

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
	private Integer hotness;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(String viewTimes) {
		this.viewTimes = viewTimes;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", createTime);
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = DateUtils.format("yyyy-MM-dd HH:mm:ss", updateTime);
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getSchooleName() {
		return schooleName;
	}

	public void setSchooleName(String schooleName) {
		this.schooleName = schooleName;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStarTimes() {
		return starTimes;
	}

	public void setStarTimes(String starTimes) {
		this.starTimes = starTimes;
	}

	public String getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(String commentTimes) {
		this.commentTimes = commentTimes;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsStar() {
		return isStar;
	}

	public void setIsStar(Boolean isStar) {
		this.isStar = isStar;
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

	public Integer getHotness() {
		return hotness;
	}

	public void setHotness(Integer hotness) {
		this.hotness = hotness;
	}

}
