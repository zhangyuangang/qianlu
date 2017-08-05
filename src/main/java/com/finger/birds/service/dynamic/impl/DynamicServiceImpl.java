package com.finger.birds.service.dynamic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.bean.DynamicBean;
import com.finger.birds.db.bean.DynamicQueryBean;
import com.finger.birds.db.dao.dynamic.DynamicDao;
import com.finger.birds.db.dao.dynamic.DynamicInfoDao;
import com.finger.birds.db.dao.intelligent.CountDao;
import com.finger.birds.db.po.dynamic.DynamicCommentPO;
import com.finger.birds.db.po.dynamic.DynamicIdInfoPO;
import com.finger.birds.db.po.dynamic.DynamicListPO;
import com.finger.birds.model.IntelligentEnum;
import com.finger.birds.model.dynamic.DynamicComment;
import com.finger.birds.model.dynamic.DynamicCommentStar;
import com.finger.birds.model.dynamic.DynamicStar;
import com.finger.birds.model.intelligent.Count;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.dynamic.DynamicService;
import com.finger.birds.utils.bean.PageBean;

@Service("dynamicService")
public class DynamicServiceImpl extends BaseService implements DynamicService{

	@Resource
	private DynamicDao dynamicDao;
	
	@Resource
	private DynamicInfoDao dynamicInfoDao;
	
	@Resource
	private CountDao countDao;

	@Override
	public PageBean<DynamicListPO> getList(DynamicQueryBean bean, PageBean<DynamicListPO> page) {
		List<DynamicListPO> list = dynamicDao.getList(bean, page.getStart(), page.getPageSize());
		Long id = bean.getSelfId();
		for (DynamicListPO po : list) {
			if(dynamicInfoDao.getDynamicStar(id, po.getId()) == null){
				po.setIsStar(false);
			}else{
				po.setIsStar(true);
			}
		}
		page.setData(list);
		page.setCount(dynamicDao.getListCount(bean));
		return page;
	}
	
	@Override
	public PageBean<DynamicListPO> getAdminList(DynamicQueryBean bean, PageBean<DynamicListPO> page) {
		page.setData(dynamicDao.getAdminList(bean, page.getStart(), page.getPageSize()));
		page.setCount(dynamicDao.getListCount(bean));
		return page;
	}

	@Override
	public boolean add(DynamicBean dynamic) {
		return dynamicDao.add(dynamic) > 0;
	}

	@Override
	public boolean delete(Long id) {
		return dynamicDao.delete(id) > 0;
	}

	@Override
	public boolean edit(Long id, Long userId) {
		return dynamicDao.edit(id, userId) > 0;
	}

	@Transactional
	@Override
	public boolean hitStarById(Long userId, Long dynamicId) {
		DynamicStar star = dynamicInfoDao.getDynamicStar(userId, dynamicId);
		Long toUserId = dynamicDao.getUserIdById(dynamicId);
		Boolean isVal;
		if(star == null){
			isVal = dynamicInfoDao.addStar(userId, dynamicId);
			isVal = isVal && dynamicInfoDao.upStar(dynamicId);
			countDao.add(new Count(userId, toUserId, dynamicId, "dynamic", IntelligentEnum.Dynamic点赞.getType()));
		}else{
			isVal = dynamicInfoDao.deleteStar(userId, dynamicId);
			isVal = isVal && dynamicInfoDao.downStar(dynamicId);
			countDao.deleteByAll(userId, toUserId, dynamicId, "dynamic", IntelligentEnum.Dynamic点赞.getType());
			countDao.add(new Count(userId, toUserId, IntelligentEnum.Liveness取消Dynamic点赞.getType()));
		}
		return isVal;
	}
	
	@Transactional
	@Override
	public boolean hitCommentStarById(Long userId, Long commentId) {
		DynamicCommentStar star = dynamicInfoDao.getDynamicCommentStar(userId, commentId);
		Boolean isVal;
		if(star == null){
			isVal = dynamicInfoDao.addCommentStar(userId, commentId);
			isVal = isVal && dynamicInfoDao.upCommentStar(commentId);
		}else{
			isVal = dynamicInfoDao.deleteCommentStar(userId, commentId);
			isVal = isVal && dynamicInfoDao.downCommentStar(commentId);
		}
		return isVal;
	}

	@Override
	public boolean comment(Long userId, Long tuserId, Long dynamicId, String content) {
		dynamicInfoDao.setUTF8mb4();
		DynamicComment dc = new DynamicComment(userId, tuserId, dynamicId, content);
		dynamicInfoDao.addComment(dc);
		Long commentId = dynamicInfoDao.getIdByBean(dc);
		dynamicInfoDao.setUTF8();
		dynamicInfoDao.upCommentTimes(dynamicId);
		DynamicIdInfoPO info = dynamicInfoDao.getInfoByCommentId(commentId);
		if(info == null){
			return commentId > 0;
		}
		if(tuserId == null){
			countDao.add(new Count(userId, info.getDynamicUserId(), dynamicId, "dynamic", IntelligentEnum.Dynamic评论一次.getType()));
		}else{
			countDao.add(new Count(userId, info.getDynamicUserId(), tuserId, dynamicId, "dynamic", IntelligentEnum.Dynamic评论一次.getType()));
		}
		return commentId > 0;
	}

	@Override
	public PageBean<DynamicCommentPO> getDynamicCommentList(CommentQueryBean bean, PageBean<DynamicCommentPO> page) {
		List<DynamicCommentPO> list = dynamicInfoDao.getDynamicCommentList(bean, page.getStart(), page.getPageSize());
		Long id = bean.getUserId();
		for (DynamicCommentPO po : list) {
			if(dynamicInfoDao.getDynamicCommentStar(id, po.getId()) == null){
				po.setIsStar(false);
			}else{
				po.setIsStar(true);
			}
		}
		page.setData(list);
		page.setCount(dynamicInfoDao.getDynamicCommentListCount(bean));
		return page;
	}

	@Override
	public DynamicListPO getById(Long userId, Long dynamicId) {
		Long toUserId = dynamicDao.getUserIdById(dynamicId);
		countDao.add(new Count(userId, toUserId, dynamicId, "dynamic", IntelligentEnum.Dynamic查看全文.getType()));
		//return dynamicDao.getById(dynamicId);
		return null;
	}
}
