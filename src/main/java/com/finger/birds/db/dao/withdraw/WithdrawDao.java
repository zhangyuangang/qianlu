package com.finger.birds.db.dao.withdraw;

import org.springframework.stereotype.Repository;

import com.finger.birds.model.withdraw.Withdraw;

@Repository
public interface WithdrawDao {

	void save(Withdraw draw);
	
	int check(Long userId);
}
