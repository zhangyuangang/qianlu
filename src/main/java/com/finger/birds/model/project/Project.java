package com.finger.birds.model.project;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Project {

	private Long id;
	private String name;
	private String desc;
	private String imgUrl;
	private Short timeType;
	private BigDecimal startPrice;
	private BigDecimal endPrice;
	private String industryName;
	private String companyName;
	private String professionName;
	private String workType;
	private Timestamp startTime;
	private Timestamp endTime;
	private Byte isPublic;
	private Timestamp createTime;
	private Long userId;
	private Timestamp updateTime;
	private Integer viewTimes;
	private Integer applyTimes;
	private Integer times;
	private Integer timeLine;
	private Short status;
	private Long tUserId;

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Short getTimeType() {
		return timeType;
	}

	public void setTimeType(Short timeType) {
		this.timeType = timeType;
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

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Byte getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Byte isPublic) {
		this.isPublic = isPublic;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
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

	public Integer getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(Integer timeLine) {
		this.timeLine = timeLine;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getTUserId() {
		return tUserId;
	}

	public void setTUserId(Long tUserId) {
		this.tUserId = tUserId;
	}
	

}
