package com.finger.birds.service.hotdis.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.HotDisMsgBean;
import com.finger.birds.db.bean.HotDisMsgQueryBean;
import com.finger.birds.db.bean.HotDisQueryBean;
import com.finger.birds.db.dao.hotdis.HotDisDao;
import com.finger.birds.db.po.hotdis.HotDisListPO;
import com.finger.birds.db.po.hotdis.HotDisMsgListPO;
import com.finger.birds.model.hotdis.HotDis;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.hotdis.HotDisService;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.exception.business.BusinessException;

@Service("hotDisService")
public class HotDisServiceImpl extends BaseService implements HotDisService{

	@Resource
	private HotDisDao hotDisDao;
	
	@Override
	public PageBean<HotDisListPO> getList(HotDisQueryBean bean, PageBean<HotDisListPO> page) {
		List<HotDisListPO> list = hotDisDao.getList(bean, page.getStart(), page.getPageSize());
		if(list != null && !list.isEmpty()){
			HotDisMsgQueryBean msgBean = new HotDisMsgQueryBean();
			msgBean.setStatus(new Byte("1"));
			for(HotDisListPO hdlp : list){
				msgBean.setHotDiscussId(hdlp.getId());
				hdlp.setNum(hotDisDao.getMsgListCount(msgBean));
			}
		}
		page.setData(list);
		page.setCount(hotDisDao.getListCount(bean));
		return page;
	}

	@Override
	public PageBean<HotDisMsgListPO> getMsgList(HotDisMsgQueryBean bean, PageBean<HotDisMsgListPO> page){
		List<HotDisMsgListPO> list = hotDisDao.getMsgList(bean, page.getStart(), page.getPageSize());
		page.setData(list);
		page.setCount(hotDisDao.getMsgListCount(bean));
		return page;
	}
	
	@Override
	public HotDisListPO getById(Long id){
		HotDisListPO po = hotDisDao.getById(id);
		if(po != null && po.getId() != null){
			HotDisMsgQueryBean msgBean = new HotDisMsgQueryBean();
			msgBean.setStatus(new Byte("1"));
			msgBean.setHotDiscussId(id);
			po.setNum(hotDisDao.getMsgListCount(msgBean));
		}
		return po;
	}
	
	@Override
	public void addMsg(HotDisMsgBean bean){
		hotDisDao.addMsg(bean);
	}
	
	@Override
	public Long getNextPrev(int op, Long id){
		Long rId = null;
		if(op == -1){
			 rId = hotDisDao.getPrev(id);
		} else {
			rId = hotDisDao.getNext(id);
		}
		if(rId == null){
			throw new BusinessException("没有更多了");
		}
		return rId;
	}

	@Override
	public boolean add(HotDis hotdis) {
		return hotDisDao.add(hotdis) > 0;
	}
	
	@Transactional
	@Override
	public boolean delete(Long id) {
		return hotDisDao.delete(id) + hotDisDao.deleteMsgByHotdisId(id) > 0;
	}

	@Override
	public PageBean<HotDisListPO> getCommentList(HotDisQueryBean bean, PageBean<HotDisListPO> page) {
		page.setData(hotDisDao.findNearDate());
		return page;
	}

	@Override
	public boolean deleteCommentById(Long id) {
		return hotDisDao.deleteMsgById(id) > 0;
	}

}
