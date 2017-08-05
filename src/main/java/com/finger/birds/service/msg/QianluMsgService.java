package com.finger.birds.service.msg;

import com.finger.birds.model.msg.QianluMsg;

public interface QianluMsgService {

	void sendMsg(Long id, String msg);

	void read(Long id);

	void check(Long userId, Long tuserId, Long id);

	QianluMsg getByUID(Long userId, Long tuserId);
}
