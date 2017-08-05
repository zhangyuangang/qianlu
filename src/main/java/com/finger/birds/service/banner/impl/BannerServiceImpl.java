package com.finger.birds.service.banner.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.finger.birds.db.dao.banner.BannerDao;
import com.finger.birds.db.po.banner.BannerListPO;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.banner.BannerService;

@Service("bannerService")
public class BannerServiceImpl extends BaseService implements BannerService{

	@Resource
	private BannerDao bannerDao;
	
	@Override
	public boolean addBanner(String cover, String detils) {
		return bannerDao.add(cover, detils);
	}

	@Override
	public boolean deleteById(Long id) {
		return bannerDao.deleteById(id);
	}

	@Override
	public List<BannerListPO> getList() {
		return bannerDao.findAll();
	}

}
