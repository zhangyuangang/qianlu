package com.finger.birds.db.dao.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.po.article.ArticleCommentPO;
import com.finger.birds.db.po.article.ArticleIdInfoPO;
import com.finger.birds.model.article.ArticleComment;
import com.finger.birds.model.article.ArticleCommentStar;
import com.finger.birds.model.article.ArticleStar;

@Repository
public interface ArticleInfoDao {

	long addComment(ArticleComment bean);

	boolean deleteComment(Long id);
	
	void setUTF8mb4();

	void setUTF8();
	
	boolean addStar(@Param("userId")Long userId, @Param("articleId")Long articleId);
	
	boolean deleteStar(@Param("userId")Long userId, @Param("articleId")Long articleId);
	
	boolean addView(@Param("userId")Long userId, @Param("articleId")Long articleId);
	
	boolean addCommentStar(@Param("userId")Long userId, @Param("commentId")Long commentId);
	
	boolean deleteCommentStar(@Param("userId")Long userId, @Param("commentId")Long commentId);

	boolean upView(Long id);

	boolean upStar(Long id);
	
	boolean downStar(Long id);
	
	boolean upCommentStar(Long commentId);
	
	boolean downCommentStar(Long commentId);
	
	boolean upCommentTimes(Long id);
	
	boolean downCommentTimes(Long id);

	ArticleStar getArticleStar(@Param("userId")Long userId, @Param("articleId")Long articleId);

	ArticleCommentStar getArticleCommentStar(@Param("userId")Long userId, @Param("commentId")Long commentId);

	List<ArticleCommentPO> getArticleCommentList(@Param("bean")CommentQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Integer getArticleCommentListCount(@Param("bean")CommentQueryBean bean);

	ArticleIdInfoPO getInfoByCommentId(Long commentId);

	Long getIdByBean(ArticleComment bean);

}
