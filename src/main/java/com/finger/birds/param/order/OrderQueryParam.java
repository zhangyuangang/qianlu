package com.finger.birds.param.order;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.db.po.order.OrderListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class OrderQueryParam extends PageParam<OrderListPO> implements BeanParam<OrderQueryBean>{
	
	private String orderCode;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public OrderQueryBean initBean() {
		OrderQueryBean bean = new OrderQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
