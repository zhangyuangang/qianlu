package com.finger.birds.service.dynamic;

import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.bean.DynamicBean;
import com.finger.birds.db.bean.DynamicQueryBean;
import com.finger.birds.db.po.dynamic.DynamicCommentPO;
import com.finger.birds.db.po.dynamic.DynamicListPO;
import com.finger.birds.utils.bean.PageBean;

public interface DynamicService {

	PageBean<DynamicListPO> getList(DynamicQueryBean bean, PageBean<DynamicListPO> page);
	
	PageBean<DynamicListPO> getAdminList(DynamicQueryBean bean, PageBean<DynamicListPO> page);

	boolean add(DynamicBean dynamic);

	boolean delete(Long id);

	boolean edit(Long id, Long userId);
	
	boolean hitStarById(Long userId, Long dynamicId);

	boolean hitCommentStarById(Long userId, Long commentId);

	boolean comment(Long userId, Long tuserId, Long dynamicId, String content);

	PageBean<DynamicCommentPO> getDynamicCommentList(CommentQueryBean bean, PageBean<DynamicCommentPO> page);

	DynamicListPO getById(Long userId, Long dynamicId);
}
