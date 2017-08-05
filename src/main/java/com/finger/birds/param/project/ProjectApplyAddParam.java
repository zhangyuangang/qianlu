package com.finger.birds.param.project;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.ProjectApplyBean;
import com.finger.birds.model.project.ProjectApplyDetail;
import com.finger.birds.ucontroller.exception.param.ParamException;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.Param;

public class ProjectApplyAddParam extends Param implements BeanParam<ProjectApplyBean> {

	@NotNull
	private Long projectId; // 项目外键关联
	
	@Length(min=1,max=100)
	@NotEmpty
	private String intro; // 自我介绍
	
	@NotNull
	private BigDecimal price; // 竞标价格
	@NotNull
	private Timestamp planTime; // 预计完成时间
	@NotEmpty
	private String detailStr;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = DateUtils.strToTimestamp("yyyy-MM-dd", planTime);
	}

	public String getDetailStr() {
		return detailStr;
	}

	public void setDetailStr(String detailStr) {
		this.detailStr = detailStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProjectApplyBean initBean() {
		ProjectApplyBean bean = new ProjectApplyBean();
		BeanUtils.copyProperties(this, bean);
		try{
			JSONArray detailArr = JSONArray.fromObject(this.detailStr);
			bean.setDetail(JSONArray.toList(detailArr, new ProjectApplyDetail(),new JsonConfig()));
		} catch(Exception e){
			throw new ParamException("详情格式错误");
		}
		return bean;
	}

}
