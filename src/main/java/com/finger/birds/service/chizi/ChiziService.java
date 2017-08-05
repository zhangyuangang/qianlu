package com.finger.birds.service.chizi;

import java.util.List;
import java.util.Map;

import com.finger.birds.db.po.chizi.ChiziPO;

public interface ChiziService {

	Map<String, List<ChiziPO>> getAll();
	
	ChiziPO getById(Long userId, Long chiziId);
	
	int care(Long userId, Long chiziId);

	/**
	 * 池子分类
	 * @param company 公司名称
	 * @param occ	岗位名称
	 * @param trade 行业名称
	 * @return
	 */
	List<ChiziPO> group(String company, String occ, String trade);
	
	void cuUpdate(List<Long> chiziId, Long userId);
	
	void cpUpdate(List<Long> chiziId, Long userId);
}
