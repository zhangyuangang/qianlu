package com.finger.birds.service.hotdis;

import com.finger.birds.db.bean.HotDisMsgBean;
import com.finger.birds.db.bean.HotDisMsgQueryBean;
import com.finger.birds.db.bean.HotDisQueryBean;
import com.finger.birds.db.po.hotdis.HotDisListPO;
import com.finger.birds.db.po.hotdis.HotDisMsgListPO;
import com.finger.birds.model.hotdis.HotDis;
import com.finger.birds.utils.bean.PageBean;

public interface HotDisService {

	PageBean<HotDisListPO> getList(HotDisQueryBean bean, PageBean<HotDisListPO> page);

	PageBean<HotDisMsgListPO> getMsgList(HotDisMsgQueryBean bean, PageBean<HotDisMsgListPO> page);

	HotDisListPO getById(Long id);

	void addMsg(HotDisMsgBean bean);

	Long getNextPrev(int op, Long id);

	boolean add(HotDis hotdis);

	boolean delete(Long id);

	PageBean<HotDisListPO> getCommentList(HotDisQueryBean bean, PageBean<HotDisListPO> page);

	boolean deleteCommentById(Long id);

}
