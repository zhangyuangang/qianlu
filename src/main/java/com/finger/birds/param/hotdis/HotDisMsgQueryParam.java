package com.finger.birds.param.hotdis;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.HotDisMsgQueryBean;
import com.finger.birds.db.po.hotdis.HotDisMsgListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class HotDisMsgQueryParam extends PageParam<HotDisMsgListPO> implements BeanParam<HotDisMsgQueryBean>{

	private Long hotDiscussId;
	
	private int order;
	
	private Byte status = new Byte("1");
	
	public Long getHotDiscussId() {
		return hotDiscussId;
	}

	public void setHotDiscussId(Long hotDiscussId) {
		this.hotDiscussId = hotDiscussId;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public HotDisMsgQueryBean initBean() {
		HotDisMsgQueryBean bean = new HotDisMsgQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
