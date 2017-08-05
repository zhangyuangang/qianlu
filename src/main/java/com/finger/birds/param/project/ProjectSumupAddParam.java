package com.finger.birds.param.project;

import javax.validation.constraints.NotNull;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.hibernate.validator.constraints.NotEmpty;

import com.finger.birds.db.bean.ProjectSumupBean;
import com.finger.birds.model.project.ProjectSumupDetail;
import com.finger.birds.ucontroller.exception.param.ParamException;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.Param;

public class ProjectSumupAddParam extends Param implements BeanParam<ProjectSumupBean>{

	@NotNull
	private Long applyId;

	//[{"applyDetailId":,"title":"","content":"","imgUrl"},{}]
	@NotEmpty
	private String detailStr;
	
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getDetailStr() {
		return detailStr;
	}

	public void setDetailStr(String detailStr) {
		this.detailStr = detailStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProjectSumupBean initBean() {
		ProjectSumupBean bean = new ProjectSumupBean();
		bean.setApplyId(applyId);
		try{
			JSONArray detailArr = JSONArray.fromObject(this.detailStr);
			bean.setDetailList(JSONArray.toList(detailArr, new ProjectSumupDetail(),new JsonConfig()));
		} catch(Exception e){
			throw new ParamException("详情格式错误");
		}
		return bean;
	}
	
}
