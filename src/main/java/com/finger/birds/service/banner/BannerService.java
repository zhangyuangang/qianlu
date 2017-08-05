package com.finger.birds.service.banner;

import java.util.List;

import com.finger.birds.db.po.banner.BannerListPO;

public interface BannerService {

	boolean addBanner(String cover, String detils);
	
	boolean deleteById(Long id);
	
	List<BannerListPO> getList();
}
