package com.finger.birds.service.article;

import com.finger.birds.db.bean.ArticleBean;
import com.finger.birds.db.bean.ArticleQueryBean;
import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.po.article.ArticleCommentPO;
import com.finger.birds.db.po.article.ArticleListPO;
import com.finger.birds.db.po.article.ArticlePO;
import com.finger.birds.utils.bean.PageBean;

public interface ArticleService {

	PageBean<ArticleListPO> getArticleList(ArticleQueryBean bean, PageBean<ArticleListPO> page);
	
	PageBean<ArticleListPO> getArticleAdminList(ArticleQueryBean bean, PageBean<ArticleListPO> page);
	
	ArticlePO getById(Long id, Long selfId);

	boolean add(ArticleBean bean);

	boolean edit(Long id, Long userId);

	boolean delete(Long id);

	boolean hitStarById(Long userId, Long articleId);

	boolean hitCommentStarById(Long userId, Long commentId);

	boolean comment(Long userId, Long tuserId, Long articleId, String content);

	PageBean<ArticleCommentPO> getArticleCommentList(CommentQueryBean bean, PageBean<ArticleCommentPO> page);
}
