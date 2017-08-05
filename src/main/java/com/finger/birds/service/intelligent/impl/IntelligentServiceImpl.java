package com.finger.birds.service.intelligent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.finger.birds.db.dao.intelligent.WeightDao;
import com.finger.birds.db.po.intelligent.WeightPO;
import com.finger.birds.model.intelligent.Weight;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.intelligent.IntelligentService;
import com.finger.birds.utils.IntelligentConstant;

@Service("intelligentService")
public class IntelligentServiceImpl extends BaseService implements IntelligentService{

	@Resource
	private WeightDao weightDao;

	@Override
	public Boolean addWeight(Weight bean) {
		Boolean isVal;
		if(bean.getId() == null || bean.getId() < 1){
			if("user".equals(bean.getToTable())){
				bean.setCardinality(IntelligentConstant.Cardinality_Init);
			}
			isVal = weightDao.add(bean);
		}else{
			isVal = weightDao.editByIdTable(bean);
		}
		return isVal;
	}

	@Override
	public WeightPO getByIdTable(Long toId, String toTable) {
		return weightDao.getByIdTable(toId, toTable);
	}

}
