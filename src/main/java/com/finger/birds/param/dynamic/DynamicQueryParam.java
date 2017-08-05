package com.finger.birds.param.dynamic;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.DynamicQueryBean;
import com.finger.birds.db.po.dynamic.DynamicListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class DynamicQueryParam extends PageParam<DynamicListPO> implements BeanParam<DynamicQueryBean>{

	private Long id;
	private Long userId;
	private Long selfId;
	private String code;

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
	
	public Long getSelfId() {
		return selfId;
	}

	public void setSelfId(Long selfId) {
		this.selfId = selfId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public DynamicQueryBean initBean() {
		DynamicQueryBean bean = new DynamicQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
