package com.finger.birds.db.dao.banner;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.po.banner.BannerListPO;

@Repository
public interface BannerDao {

	boolean add(@Param("cover")String cover, @Param("detils")String detils);
	
	boolean deleteById(Long id);
	
	BannerListPO findById(Long id);
	
	List<BannerListPO> findAll();
}
