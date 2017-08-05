package com.finger.birds.db.bean;

import com.finger.birds.utils.bean.Bean;

public class HotDisMsgQueryBean implements Bean{
	
	private Byte status;
	
	private Long hotDiscussId;

	private int order;
	
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

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
	
}
