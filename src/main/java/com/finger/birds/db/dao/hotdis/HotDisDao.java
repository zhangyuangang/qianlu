package com.finger.birds.db.dao.hotdis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.HotDisMsgBean;
import com.finger.birds.db.bean.HotDisMsgQueryBean;
import com.finger.birds.db.bean.HotDisQueryBean;
import com.finger.birds.db.po.hotdis.HotDisListPO;
import com.finger.birds.db.po.hotdis.HotDisMsgListPO;
import com.finger.birds.model.hotdis.HotDis;

@Repository
public interface HotDisDao {

	List<HotDisListPO> getList(@Param("bean")HotDisQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int getListCount(@Param("bean")HotDisQueryBean bean); 
	
	HotDisListPO getById(Long id);
	
	List<HotDisMsgListPO> getMsgList(@Param("bean")HotDisMsgQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int getMsgListCount(@Param("bean")HotDisMsgQueryBean bean);
	
	void addMsg(HotDisMsgBean bean);
	
	Long getNext(Long id);
	
	Long getPrev(Long id);

	int add(HotDis hotdis);

	int delete(Long id);

	int deleteMsgByHotdisId(Long hotDiscussId);

	List<HotDisListPO> findNearDate();

	int deleteMsgById(Long id);
}
