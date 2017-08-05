package com.finger.birds.service.qianlu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.finger.birds.db.dao.intelligent.CountDao;
import com.finger.birds.db.dao.intelligent.QianluDao;
import com.finger.birds.db.po.intelligent.QianluPO;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.qianlu.QianluService;

@Service("qianluService")
public class QianluServiceImpl extends BaseService implements QianluService{

	@Resource
	private QianluDao qianluDao;
	
	@Resource
	private CountDao countDao;

	@Override
	public List<QianluPO> getMessage(Long userId) {
		return qianluDao.getMessage(userId);
	}

	@Override
	public void readMessage(Long id) {
		qianluDao.readById(id);
	}

}
