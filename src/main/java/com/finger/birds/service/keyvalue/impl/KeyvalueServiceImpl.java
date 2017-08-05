package com.finger.birds.service.keyvalue.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.finger.birds.db.dao.keyvalue.KeyvalueDao;
import com.finger.birds.db.po.keyvalue.KeyvalueListPO;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.keyvalue.KeyvalueService;
import com.finger.birds.utils.date.DateUtils;

@Service("keyvalueService")
public class KeyvalueServiceImpl extends BaseService implements KeyvalueService{

	private static final String key_proverbs = "proverbs";
	private static final String key_question = "question_";
	@Resource
	private KeyvalueDao keyvalueDao;

	@Override
	public boolean addProverbs(String value) {
		return keyvalueDao.add(key_proverbs, value);
	}

	@Override
	public KeyvalueListPO getTopProverbs() {
		return keyvalueDao.findTopByKey(key_proverbs);
	}

	@Override
	public Map<String, Object> getQuestionByMsgId(Long selfId, Long tuserId) {
		KeyvalueListPO po1 = keyvalueDao.findTopByKey(key_question + selfId + "_" + tuserId);
		KeyvalueListPO po2 = keyvalueDao.findTopByKey(key_question + tuserId + "_" + selfId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(po1 == null && po2 == null){
			return null;
		}
		if(po1 == null){
			map.put("question", po2.getValue());
			return map;
		}
		if(po2 == null){
			map.put("question", po1.getValue());
			return map;
		}
		Date po1Date = DateUtils.strToDate("yyyy-MM-dd HH:mm:ss", po1.getCreateTime());
		Date po2Date = DateUtils.strToDate("yyyy-MM-dd HH:mm:ss", po2.getCreateTime());
		if(po1Date.before(po2Date)){
			map.put("question", po2.getValue());
		}else{
			map.put("question", po1.getValue());
		}
		return map;
	}

	@Override
	public void addQuestion(Long userId, Long tuserId, String intro) {
		keyvalueDao.add(key_question + userId + "_" + tuserId, intro);
	}

}
