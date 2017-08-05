package com.finger.birds.service.qianlu;

import java.util.List;

import com.finger.birds.db.po.intelligent.QianluPO;

public interface QianluService {

	List<QianluPO> getMessage(Long userId);

	void readMessage(Long id);

}