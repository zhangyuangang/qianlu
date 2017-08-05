package com.finger.birds.db.dao.user;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.bean.UserSearchBean;
import com.finger.birds.db.po.user.AdminUserPO;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.db.po.user.UserExtPO;
import com.finger.birds.db.po.user.UserInfoPO;
import com.finger.birds.db.po.user.UserSearchPO;
import com.finger.birds.model.user.User;

@Repository
public interface UserDao {

	long save(User user);
	
	User getByThirdKey(@Param("thirdKey")String thirdKey);
	
	User getById(Long id);
	
	int updateStatus(@Param("status")Short status, @Param("id")Long id);
	
	int updateOccType(@Param("occType")int occType, @Param("id")Long id);
	
	UserCenterPO getInfoForUserCenter(Long userId);

	int getCount(@Param("occType")Integer occType);
	
	BigDecimal getAvgScore(Long tUserId);
	
	byte getTypeByUcode(String ucode);
	
	int useUcode(String ucode);
	
	int checkPhone(String phone);
	
	List<UserCenterPO> queryOB(@Param("bean")UserQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	int queryOBCount(@Param("bean")UserQueryBean bean);
	
	User getUserByUcode(String ucode);
	
	void updateThirdKeyById(@Param("thirdKey")String thirdKey, @Param("id")Long id);

	List<UserSearchPO> searchByStart(@Param("bean")UserSearchBean bean);
	
	UserExtPO getUserExt(Long userId);
	
	boolean updateUser(@Param("bean")User bean);

	int updateUserStatus(@Param("occType")Integer occType, @Param("status")Short status, @Param("id")Long id);

	User getByCode(String code);

	int checkUcode(String code);

	AdminUserPO login(@Param("username")String username, @Param("password")String password);

	void deleteById(Long id);

	List<UserCenterPO> getList(@Param("bean")UserQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	Integer getListCount(@Param("bean")UserQueryBean bean);

	List<UserCenterPO> searchByKeywords(@Param("companyName")String companyName, @Param("occType")Integer occType, @Param("tradeName")String tradeName);

	List<UserCenterPO> getAdminList(@Param("bean")UserQueryBean bean, @Param("start")Integer start, @Param("pageSize")Integer pageSize);

	List<UserInfoPO> getAll();

}
