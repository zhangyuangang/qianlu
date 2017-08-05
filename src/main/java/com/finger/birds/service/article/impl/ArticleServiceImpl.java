package com.finger.birds.service.article.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.ArticleBean;
import com.finger.birds.db.bean.ArticleQueryBean;
import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.dao.article.ArticleDao;
import com.finger.birds.db.dao.article.ArticleInfoDao;
import com.finger.birds.db.dao.intelligent.CountDao;
import com.finger.birds.db.po.article.ArticleCommentPO;
import com.finger.birds.db.po.article.ArticleIdInfoPO;
import com.finger.birds.db.po.article.ArticleListPO;
import com.finger.birds.db.po.article.ArticlePO;
import com.finger.birds.model.IntelligentEnum;
import com.finger.birds.model.article.ArticleComment;
import com.finger.birds.model.article.ArticleCommentStar;
import com.finger.birds.model.article.ArticleStar;
import com.finger.birds.model.intelligent.Count;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.article.ArticleService;
import com.finger.birds.utils.bean.PageBean;

@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService{

	@Resource
	private ArticleDao articleDao;
	
	@Resource
	private ArticleInfoDao articleInfoDao;
	
	@Resource
	private CountDao countDao;
	
	@Override
	public PageBean<ArticleListPO> getArticleList(ArticleQueryBean bean, PageBean<ArticleListPO> page) {
		List<ArticleListPO> list = articleDao.getArticleList(bean, page.getStart(), page.getPageSize());
		Long id = bean.getSelfId();
		for (ArticleListPO po : list) {
			if(articleInfoDao.getArticleStar(id, po.getId()) == null){
				po.setIsStar(false);
			}else{
				po.setIsStar(true);
			}
		}
		page.setData(list);
		page.setCount(articleDao.getArticleListCount(bean));
		return page;
	}
	
	@Override
	public PageBean<ArticleListPO> getArticleAdminList(ArticleQueryBean bean, PageBean<ArticleListPO> page) {
		page.setData(articleDao.getArticleAdminList(bean, page.getStart(), page.getPageSize()));
		page.setCount(articleDao.getArticleListCount(bean));
		return page;
	}

	@Override
	public ArticlePO getById(Long id, Long selfId) {
		Long toUserId = articleDao.getUserIdById(id);
		articleInfoDao.upView(id);
		articleInfoDao.addView(selfId, id);
		countDao.add(new Count(selfId, toUserId, id, "article", IntelligentEnum.Article查看详情.getType()));
		return articleDao.getById(id);
	}

	@Override
	public boolean add(ArticleBean bean) {
		return articleDao.add(bean);
	}

	@Override
	public boolean edit(Long id, Long userId) {
		return articleDao.editById(id, userId);
	}

	@Override
	public boolean delete(Long id) {
		return articleDao.delete(id);
	}

	@Transactional
	@Override
	public boolean hitStarById(Long userId, Long articleId) {
		ArticleStar star = articleInfoDao.getArticleStar(userId, articleId);
		Long toUserId = articleDao.getUserIdById(articleId);
		Boolean isVal;
		if(star == null){
			isVal = articleInfoDao.addStar(userId, articleId);
			isVal = isVal && articleInfoDao.upStar(articleId);
			countDao.add(new Count(userId, toUserId, articleId, "article", IntelligentEnum.Article点赞.getType()));
		}else{
			isVal = articleInfoDao.deleteStar(userId, articleId);
			isVal = isVal && articleInfoDao.downStar(articleId);
			countDao.deleteByAll(userId, toUserId, articleId, "article", IntelligentEnum.Article点赞.getType());
			countDao.add(new Count(userId, toUserId, IntelligentEnum.Liveness取消Article点赞.getType()));
		}
		return isVal;
	}
	
	@Transactional
	@Override
	public boolean hitCommentStarById(Long userId, Long commentId) {
		ArticleCommentStar star = articleInfoDao.getArticleCommentStar(userId, commentId);
		ArticleIdInfoPO info = articleInfoDao.getInfoByCommentId(commentId);
		Boolean isVal;
		if(star == null){
			isVal = articleInfoDao.addCommentStar(userId, commentId);
			isVal = isVal && articleInfoDao.upCommentStar(commentId);
			countDao.add(new Count(userId, info.getArticleUserId(), info.getCommentUserId(), info.getArticleId(), "article", IntelligentEnum.Article评论点赞.getType()));
		}else{
			isVal = articleInfoDao.deleteCommentStar(userId, commentId);
			isVal = isVal && articleInfoDao.downCommentStar(commentId);
			countDao.deleteByAll(userId, info.getArticleUserId(), info.getArticleId(), "article", IntelligentEnum.Article评论点赞.getType());
			countDao.add(new Count(userId, info.getArticleUserId(), info.getCommentUserId(), IntelligentEnum.Liveness取消Article评论_点赞.getType()));
		}
		return isVal;
	}

	@Override
	public boolean comment(Long userId, Long tuserId, Long articleId, String content) {
		articleInfoDao.setUTF8mb4();
		ArticleComment ac = new ArticleComment(userId, tuserId, articleId, content);
		articleInfoDao.addComment(ac);
		Long commentId = articleInfoDao.getIdByBean(ac);
		articleInfoDao.setUTF8();
		articleInfoDao.upCommentTimes(articleId);
		ArticleIdInfoPO info = articleInfoDao.getInfoByCommentId(commentId);
		if(info == null){
			return commentId > 0;
		}
		if(tuserId == null){
			countDao.add(new Count(userId, info.getArticleUserId(), articleId, "article", IntelligentEnum.Article评论一次.getType()));
		}else{
			countDao.add(new Count(userId, info.getArticleUserId(), tuserId, articleId, "article", IntelligentEnum.Article评论一次.getType()));
		}
		return commentId > 0;
	}

	@Override
	public PageBean<ArticleCommentPO> getArticleCommentList(CommentQueryBean bean, PageBean<ArticleCommentPO> page) {
		List<ArticleCommentPO> list = articleInfoDao.getArticleCommentList(bean, page.getStart(), page.getPageSize());
		Long id = bean.getUserId();
		for (ArticleCommentPO po : list) {
			if(articleInfoDao.getArticleCommentStar(id, po.getId()) == null){
				po.setIsStar(false);
			}else{
				po.setIsStar(true);
			}
		}
		page.setData(list);
		page.setCount(articleInfoDao.getArticleCommentListCount(bean));
		return page;
	}
}
