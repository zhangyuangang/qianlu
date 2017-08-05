package com.finger.birds.db.po.article;

import java.sql.Timestamp;

import com.finger.birds.utils.date.DateUtils;

public class ArticleListPO {

	private Long id;
	private String title;// '标题',
	private String tips1;
	private String tips2;
	private String tips3;
	private String tips4;
	private String tips5;
	private String headImg;// '封面图片',
	private String intro;// '文章摘要',
	private Long userId;// '大白id',
	private String nickname;
	private String headImage;
	private String code;
	private String viewTimes;// '查看次数',
	private String starTimes;//
	private String commentTimes;//
	private Boolean isTop;//
	private Boolean isDelete;//
	private Boolean isStar;//
	private Long adminId; // '管理员Id',
	private String createTime;
	private String updateTime;
	
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
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTips1() {
		return tips1;
	}
	public void setTips1(String tips1) {
		this.tips1 = tips1;
	}
	public String getTips2() {
		return tips2;
	}
	public void setTips2(String tips2) {
		this.tips2 = tips2;
	}
	public String getTips3() {
		return tips3;
	}
	public void setTips3(String tips3) {
		this.tips3 = tips3;
	}
	public String getTips4() {
		return tips4;
	}
	public void setTips4(String tips4) {
		this.tips4 = tips4;
	}
	public String getTips5() {
		return tips5;
	}
	public void setTips5(String tips5) {
		this.tips5 = tips5;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(String viewTimes) {
		this.viewTimes = viewTimes;
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
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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
