package com.finger.birds.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.finger.birds.db.dao.user.UserIncomeDao;
import com.finger.birds.db.po.user.UserIncomeStatPO;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.user.UserIncomeService;

@Service("userIncomeService")
public class UserIncomeServiceImpl extends BaseService implements UserIncomeService{

	@Resource
	private UserIncomeDao userIncomeDao;
	
	@Override
	public UserIncomeStatPO getCountByUserId(Long userId){
		UserIncomeStatPO uipo = userIncomeDao.getCountByUserId(userId);
		uipo.setUserId(userId);
		uipo.setTotal();
		return uipo;
	}
	
}
