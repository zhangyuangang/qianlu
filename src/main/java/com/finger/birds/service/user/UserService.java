package com.finger.birds.service.user;

import java.util.List;
import java.util.Map;

import com.finger.birds.db.bean.UserBean;
import com.finger.birds.db.bean.UserInfoBean;
import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.bean.UserSearchBean;
import com.finger.birds.db.po.BirdsMsgPO;
import com.finger.birds.db.po.msg.QianluMsgListPO;
import com.finger.birds.db.po.user.AdminUserPO;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.db.po.user.UserExtPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.db.po.user.UserSearchPO;
import com.finger.birds.model.msg.BirdsMsg;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.utils.bean.PageBean;

public interface UserService {
	
	UserPO getUserByThirdKey(String key);

	UserPO updateInfo(UserInfoBean bean);
	
	/**
	 * @param userId  自己的Id
	 * @param tUserId 被查看人的Id
	 * @return
	 */
	UserCenterPO getInfoForUserCenter(Long userId, Long tUserId);
	
	/**
	 * 获取用户分类
	 * @return
	 */
	List<Map<String, Object>> getUserForType();

	int care(Long userId, Long tUserId);
	
	UserPO register(UserBean bean);

	UserPO getById(Long id);

	PageBean<UserCenterPO> queryOBForPage(UserQueryBean bean, PageBean<UserCenterPO> page);

	String getMsgCount(Long userId, short type);

	void readMsg(BirdsMsg msg);
	
	void addMsg(BirdsMsg msg);

	BirdsMsgPO getMsgPO(Long userId);

	PageBean<QianluMsgListPO> getUserMsgList(Long userId, PageBean<QianluMsgListPO> page);

	void checkSendMsg(Long userId, Long tuserId);

	PageBean<UserSearchPO> searchByStart(UserSearchBean bean, PageBean<UserSearchPO> page);
	
	QianluMsg checkExist(Long userId, Long tuserId);

	UserExtPO getUserExtPO(Long userId);
	
	void updateUserInfo(UserInfoBean bean);

	void addUser(UserInfoBean param);
	
	void addUserAdmin(UserInfoBean param);

	UserPO getByCode(String code);

	AdminUserPO login(String username, String password);

	void deleteById(Long id);

	PageBean<UserCenterPO> getList(UserQueryBean bean, PageBean<UserCenterPO> page);
	
	PageBean<UserCenterPO> getAdminList(UserQueryBean bean, PageBean<UserCenterPO> page);

	List<UserCenterPO> searchByKeywords(Long userId, String companyName, Integer occType, String tradeName);

	void shareWX(Long userId, Long toId, String type);
}
