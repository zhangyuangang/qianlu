package com.finger.birds.db.po.article;

public class ArticleIdInfoPO {

	private Long articleUserId;
	private Long commentUserId;
	private Long toUserId;
	private Long articleId;
	
	public Long getArticleUserId() {
		return articleUserId;
	}
	public void setArticleUserId(Long articleUserId) {
		this.articleUserId = articleUserId;
	}
	public Long getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(Long commentUserId) {
		this.commentUserId = commentUserId;
	}
	public Long getToUserId() {
		return toUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
}
