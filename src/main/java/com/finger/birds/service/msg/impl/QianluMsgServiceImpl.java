package com.finger.birds.service.msg.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.dao.msg.QianluMsgDao;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.model.msg.QianluMsgHistory;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.msg.QianluMsgService;
import com.finger.birds.utils.exception.business.BusinessException;

@Service("qianluMsgService")
public class QianluMsgServiceImpl extends BaseService implements QianluMsgService{

	@Resource
	private QianluMsgDao qianluMsgDao;
	
	@Transactional
	@Override
	public void sendMsg(Long id, String msg){
		if(msg == null){
			return;
		}
		String smsg = "";
		{
			QianluMsgHistory history = new QianluMsgHistory();
			history.setMsgId(id);
			if(msg.startsWith("[图片]:")){
				history.setType((short)3);
				history.setContent(msg.replace("[图片]:", ""));
				smsg = "[图片]";
			} else if(msg.startsWith("[录音]:")){
				history.setType((short)2);
				history.setContent(msg.replace("[录音]:", ""));
				smsg = "[录音]";
			} else {
				history.setType((short)1);
				history.setContent(msg);
				smsg = msg;
			}
			qianluMsgDao.addHistory(history);
		}
		
		QianluMsg qianlu = new QianluMsg();
		qianlu.setIsRead(new Byte("0"));
		qianlu.setLastest(smsg);
		qianluMsgDao.updateById(qianlu, id);
	}
	
	@Override
	public void read(Long id){
		QianluMsg qianlu = new QianluMsg();
		qianlu.setIsRead(new Byte("1"));
		qianluMsgDao.updateById(qianlu, id);
	}

	@Override
	public void check(Long userId, Long tuserId, Long id) {
		QianluMsg po = qianluMsgDao.checkExsit(userId, tuserId);
		if(po == null || po.getId() == null || id != po.getId()){
			throw new BusinessException("无法开启聊天");
		}
	}
	
	@Override
	public QianluMsg getByUID(Long userId, Long tuserId){
		return qianluMsgDao.checkExsit(userId, tuserId);
	} 
}
