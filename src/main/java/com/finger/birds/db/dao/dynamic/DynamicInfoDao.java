package com.finger.birds.db.dao.dynamic;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.po.dynamic.DynamicCommentPO;
import com.finger.birds.db.po.dynamic.DynamicIdInfoPO;
import com.finger.birds.model.dynamic.DynamicComment;
import com.finger.birds.model.dynamic.DynamicCommentStar;
import com.finger.birds.model.dynamic.DynamicStar;

@Repository
public interface DynamicInfoDao {

	long addComment(DynamicComment bean);

	boolean deleteComment(Long id);
	
	void setUTF8mb4();

	void setUTF8();
	
	boolean addStar(@Param("userId")Long userId, @Param("dynamicId")Long dynamicId);
	
	boolean deleteStar(@Param("userId")Long userId, @Param("dynamicId")Long dynamicId);
	
	boolean addView(@Param("userId")Long userId, @Param("dynamicId")Long dynamicId);
	
	boolean addCommentStar(@Param("userId")Long userId, @Param("commentId")Long commentId);
	
	boolean deleteCommentStar(@Param("userId")Long userId, @Param("commentId")Long commentId);
	
	boolean upView(Long id);

	boolean upStar(Long id);
	
	boolean downStar(Long id);
	
	boolean upCommentStar(Long commentId);
	
	boolean downCommentStar(Long commentId);
	
	boolean upCommentTimes(Long id);
	
	boolean downCommentTimes(Long id);

	DynamicStar getDynamicStar(@Param("userId")Long userId, @Param("dynamicId")Long dynamicId);

	DynamicCommentStar getDynamicCommentStar(@Param("userId")Long userId, @Param("commentId")Long commentId);
	
	List<DynamicCommentPO> getDynamicCommentList(@Param("bean")CommentQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Integer getDynamicCommentListCount(@Param("bean")CommentQueryBean bean);

	DynamicIdInfoPO getInfoByCommentId(Long commentId);

	Long getIdByBean(DynamicComment bean);

}
