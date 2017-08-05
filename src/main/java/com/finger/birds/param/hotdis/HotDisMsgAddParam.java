package com.finger.birds.param.hotdis;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.HotDisMsgBean;
import com.finger.birds.utils.param.BeanParam;

public class HotDisMsgAddParam implements BeanParam<HotDisMsgBean>{
	
	private Long hotDiscussId;
	
	@NotEmpty
	private String content;

	public Long getHotDiscussId() {
		return hotDiscussId;
	}

	public void setHotDiscussId(Long hotDiscussId) {
		this.hotDiscussId = hotDiscussId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public HotDisMsgBean initBean() {
		HotDisMsgBean bean = new HotDisMsgBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}
	
	
}
