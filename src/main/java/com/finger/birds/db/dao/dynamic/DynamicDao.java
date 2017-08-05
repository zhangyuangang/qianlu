package com.finger.birds.db.dao.dynamic;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.DynamicBean;
import com.finger.birds.db.bean.DynamicQueryBean;
import com.finger.birds.db.po.dynamic.DynamicListPO;

@Repository
public interface DynamicDao {

	List<DynamicListPO> getList(@Param("bean")DynamicQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	List<DynamicListPO> getAdminList(@Param("bean")DynamicQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int getListCount(@Param("bean")DynamicQueryBean bean);

	int add(DynamicBean dynamic);

	int delete(Long id);

	int edit(@Param("id")Long id, @Param("userId")Long userId);

	DynamicListPO getById(Long dynamicId); 
	
	Long getUserIdById(Long dynamicId);
}
