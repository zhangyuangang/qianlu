package com.finger.birds.service.intelligent;

import com.finger.birds.db.po.intelligent.WeightPO;
import com.finger.birds.model.intelligent.Weight;

public interface IntelligentService {

	Boolean addWeight(Weight bean);

	WeightPO getByIdTable(Long toId, String toTable);
}
