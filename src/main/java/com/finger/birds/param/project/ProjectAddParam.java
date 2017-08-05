package com.finger.birds.param.project;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.ProjectBean;
import com.finger.birds.ucontroller.exception.param.ParamException;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.Param;

public class ProjectAddParam extends Param implements BeanParam<ProjectBean>{

	@NotEmpty
	private String name;// 项目名称
	@NotEmpty
	private String desc;// 项目描述
	
	private String imgUrl;// 项目图片url地址
	private short timeType;// 0:整段集中1:3天内碎片
	@NotNull
	private BigDecimal startPrice;// 价格开始区间
	@NotNull
	private BigDecimal endPrice;// 价格结束区间
	@NotEmpty
	private String industryName;// 行业名称
	private String companyName;// 公司名称
	@NotEmpty
	private String professionName;// 职业名称
	// private Long workType ;//0[图文互动]0[实时语音]0[语音留言]
	private Timestamp startTime;// 项目开始时间
	@NotNull
	private Timestamp endTime;// 项目结束时间
	private byte isPublic;// 项目完成是否公开0,1
	
	private Long tUserId;

	private Integer times;//次数
	private Integer timeLine;//时长
	
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

	public short getTimeType() {
		return timeType;
	}

	public void setTimeType(short timeType) {
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

	public void setProfessionNam(String professionName) {
		this.professionName = professionName;
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

	public void setEndTime(String endTime) {
		this.endTime = DateUtils.strToTimestamp("yyyy-MM-dd", endTime);
	}

	public byte getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(byte isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public ProjectBean initBean() {
		if(this.endTime == null){
			throw new ParamException("截至时间不能未空");
		}
		ProjectBean bean = new ProjectBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
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

	public Long getTUserId() {
		return tUserId;
	}

	public void setTUserId(Long tUserId) {
		this.tUserId = tUserId;
	}
	
}
