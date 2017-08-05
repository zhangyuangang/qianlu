package com.finger.birds.param.hotdis;

import javax.validation.constraints.Max;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.HotDisQueryBean;
import com.finger.birds.db.po.hotdis.HotDisListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class HotDisQueryParam extends PageParam<HotDisListPO> implements BeanParam<HotDisQueryBean>{

	@Max(12)
	private Short month;
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}

	@Override
	public HotDisQueryBean initBean() {
		HotDisQueryBean bean = new HotDisQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
