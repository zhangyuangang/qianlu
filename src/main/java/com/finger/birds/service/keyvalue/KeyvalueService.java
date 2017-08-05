package com.finger.birds.service.keyvalue;

import java.util.Map;

import com.finger.birds.db.po.keyvalue.KeyvalueListPO;

public interface KeyvalueService {

	boolean addProverbs(String value);

	KeyvalueListPO getTopProverbs();

	Map<String, Object> getQuestionByMsgId(Long selfId, Long tuserId);

	void addQuestion(Long userId, Long tuserId, String intro);
}
