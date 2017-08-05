package com.finger.birds.param.article;

import org.springframework.beans.BeanUtils;

import com.finger.birds.db.bean.ArticleQueryBean;
import com.finger.birds.db.po.article.ArticleListPO;
import com.finger.birds.utils.param.BeanParam;
import com.finger.birds.utils.param.PageParam;

public class ArticleQueryParam extends PageParam<ArticleListPO> implements BeanParam<ArticleQueryBean> {

	private Long id;
	private Long userId;
	private Long selfId;
	private String code;
	
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

	public Long getSelfId() {
		return selfId;
	}

	public void setSelfId(Long selfId) {
		this.selfId = selfId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public ArticleQueryBean initBean() {
		ArticleQueryBean bean = new ArticleQueryBean();
		BeanUtils.copyProperties(this, bean);
		return bean;
	}

}
