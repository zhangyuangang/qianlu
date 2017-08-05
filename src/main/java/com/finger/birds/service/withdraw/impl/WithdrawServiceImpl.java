package com.finger.birds.service.withdraw.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.finger.birds.db.dao.user.UserDao;
import com.finger.birds.db.dao.withdraw.WithdrawDao;
import com.finger.birds.model.user.User;
import com.finger.birds.model.withdraw.Withdraw;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.withdraw.WithdrawService;
import com.finger.birds.utils.exception.business.BusinessException;

@Service("WithdrawService")
public class WithdrawServiceImpl extends BaseService implements WithdrawService{

	@Resource
	private WithdrawDao withdrawDao;
	
	@Resource
	private UserDao userDao;
	
	@Override
	public void add(Long userId){
		User user = userDao.getById(userId);
		if(user.getUserType() == 2){
			throw new BusinessException("你无法发起提现请求");
		}
		if(withdrawDao.check(userId) > 0){
			throw new BusinessException("您的上一次提现请求正在处理中，暂时无法发起提现");
		}
		Withdraw w = new Withdraw();
		{
			w.setStatus((short)1);
			w.setUserId(userId);
		}
		withdrawDao.save(w);
	}
}
