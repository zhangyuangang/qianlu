package com.finger.birds.db.dao.keyvalue;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.KeyvalueQueryBean;
import com.finger.birds.db.po.keyvalue.KeyvalueListPO;

@Repository
public interface KeyvalueDao {

	boolean add(@Param("key")String key, @Param("value")String value);
	
	boolean editById(@Param("bean")KeyvalueQueryBean bean);
	
	boolean editByKey(@Param("bean")KeyvalueQueryBean bean);
	
	boolean deleteById(Long id);
	
	boolean deleteByKey(String key);
	
	KeyvalueListPO findById(Long id);
	
	KeyvalueListPO findTopByKey(String key);
	
	List<KeyvalueListPO> findByKey(String key);
}
