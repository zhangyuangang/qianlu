package com.finger.birds.utils.param;

import com.finger.birds.utils.bean.Bean;

public interface BeanParam<E extends Bean> {
	
	E initBean();
	
}
