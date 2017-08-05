package com.finger.birds.model.project;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProjectSumup {

	private Long id;//
	private Long projectId;// 项目Id
	private Long applyId;// 标Id
	private BigDecimal price;// 价格
	private Integer joins;// 参与人数
	private Integer exacts;// 到位人数
	private Long userId;// 创建人Id
	private Timestamp createTime;// 创建时间
	private Timestamp updateTime;// 修改时间(内容)
	private String industryName;
	private String professionName;
	private String companyName;
	private BigDecimal refPrice;
	private String name;
	private String desc;
	private Short type;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getJoins() {
		return joins;
	}

	public void setJoins(Integer joins) {
		this.joins = joins;
	}

	public Integer getExacts() {
		return exacts;
	}

	public void setExacts(Integer exacts) {
		this.exacts = exacts;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getRefPrice() {
		return refPrice;
	}

	public void setRefPrice(BigDecimal refPrice) {
		this.refPrice = refPrice;
	}

}
