package com.finger.birds.param.article;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.po.article.ArticleCommentPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class CommentQueryParam extends PageParam<ArticleCommentPO> implements BeanParam<CommentQueryBean> {

	private Long id;
	private Long userId;
	private Long articleId;
	private Long dynamicId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Long dynamicId) {
		this.dynamicId = dynamicId;
	}

	@Override
	public CommentQueryBean initBean() {
		CommentQueryBean bean = new CommentQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
