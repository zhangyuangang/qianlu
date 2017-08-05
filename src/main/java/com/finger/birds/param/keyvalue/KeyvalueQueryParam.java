package com.finger.birds.param.keyvalue;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.KeyvalueQueryBean;
import com.finger.birds.db.po.keyvalue.KeyvalueListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class KeyvalueQueryParam extends PageParam<KeyvalueListPO> implements BeanParam<KeyvalueQueryBean>{

	private Long id;
	private Long key;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	@Override
	public KeyvalueQueryBean initBean() {
		KeyvalueQueryBean bean = new KeyvalueQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
