package com.finger.birds.service.user;

import com.finger.birds.db.po.user.UserIncomeStatPO;

public interface UserIncomeService {

	UserIncomeStatPO getCountByUserId(Long userId);

}
