package com.finger.birds.db.dao.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.ArticleBean;
import com.finger.birds.db.bean.ArticleQueryBean;
import com.finger.birds.db.po.article.ArticleListPO;
import com.finger.birds.db.po.article.ArticlePO;

@Repository
public interface ArticleDao {

	List<ArticleListPO> getArticleList(@Param("bean")ArticleQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	List<ArticleListPO> getArticleAdminList(@Param("bean")ArticleQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int getArticleListCount(@Param("bean")ArticleQueryBean bean);
	
	ArticlePO getById(Long id);

	boolean add(@Param("bean")ArticleBean bean);

	boolean editById(@Param("id")Long id, @Param("userId")Long userId);

	boolean delete(Long id);

	Long getUserIdById(Long articleId);

}
