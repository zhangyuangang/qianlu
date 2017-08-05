package com.finger.birds.service.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.db.bean.ProjectQueryBean;
import com.finger.birds.db.bean.UserBean;
import com.finger.birds.db.bean.UserInfoBean;
import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.bean.UserSearchBean;
import com.finger.birds.db.dao.article.ArticleDao;
import com.finger.birds.db.dao.intelligent.CountDao;
import com.finger.birds.db.dao.intelligent.WeightDao;
import com.finger.birds.db.dao.msg.BirdsMsgDao;
import com.finger.birds.db.dao.msg.QianluMsgDao;
import com.finger.birds.db.dao.order.OrderDao;
import com.finger.birds.db.dao.project.ProjectDao;
import com.finger.birds.db.dao.user.UserConcernDao;
import com.finger.birds.db.dao.user.UserDao;
import com.finger.birds.db.dao.user.UserIncomeDao;
import com.finger.birds.db.dao.user.UserInfoDao;
import com.finger.birds.db.po.BirdsMsgPO;
import com.finger.birds.db.po.msg.QianluMsgListPO;
import com.finger.birds.db.po.user.AdminUserPO;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.db.po.user.UserExtPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.db.po.user.UserSearchPO;
import com.finger.birds.model.IntelligentEnum;
import com.finger.birds.model.OccTypeEnum;
import com.finger.birds.model.TimeTypeEnum;
import com.finger.birds.model.eum.BirdsMsgTypeEnum;
import com.finger.birds.model.intelligent.Count;
import com.finger.birds.model.intelligent.Weight;
import com.finger.birds.model.msg.BirdsMsg;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.model.order.Order;
import com.finger.birds.model.user.User;
import com.finger.birds.model.user.UserInfo;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.chizi.ChiziService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.IntelligentConstant;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.exception.business.BusinessException;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService{

	@Resource
	private UserDao userDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private UserConcernDao userConcernDao;
	
	@Resource
	private UserIncomeDao userIncomeDao;
	
	@Resource
	private ChiziService chiziService;
	
	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private BirdsMsgDao birdsMsgDao;
	
	@Resource
	private QianluMsgDao qianluMsgDao;
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private CountDao countDao;
	
	@Resource
	private WeightDao weightDao;
	
	@Resource
	private ArticleDao articleDao;
	
	@Override
	public UserPO getUserByThirdKey(String key) {
		UserPO upo = new UserPO();
		User user = userDao.getByThirdKey(key);
		if(user == null || user.getId() == null){
			throw new BusinessException("用户不存在");
		}
		BeanUtils.copyProperties(user, upo);
		upo.setUserInfo(userInfoDao.getByUserId(user.getId()));
		return upo;
	}

	@Override
	public UserPO getById(Long id) {
		UserPO upo = new UserPO();
		User user = userDao.getById(id);
		if(user == null || user.getId() == null){
			throw new BusinessException("用户不存在");
		}
		BeanUtils.copyProperties(user, upo);
		upo.setUserInfo(userInfoDao.getByUserId(user.getId()));
		return upo;
	}
	
	@Transactional
	@Override
	public UserPO updateInfo(UserInfoBean bean){
		if(bean.getStatus() != null){
			userDao.updateStatus(bean.getStatus(), bean.getUserId());
		}
		userInfoDao.updateByUserId(bean);
		//编辑个人中心
		countDao.add(new Count(bean.getUserId(), IntelligentEnum.Liveness修改个人信息.getType()));
		UserPO upo = getById(bean.getUserId());
//		if(upo.getUserType() == 1){
//			this.groupUser(bean);
//		}
		return upo;
	}

	@Override
	public UserCenterPO getInfoForUserCenter(Long userId, Long tUserId) {
		UserCenterPO ucpo = userDao.getInfoForUserCenter(tUserId);
		ProjectQueryBean bean = new ProjectQueryBean();
		bean.setTuserId(tUserId);
		if(userId != null && tUserId.equals(userId)){
			ucpo.setIsSelf((short)1);
		}
		return ucpo;
	}

	@Override
	public List<Map<String, Object>> getUserForType() {
		List<Map<String, Object>> list = OccTypeEnum.getAll();
		for(Map<String, Object> map : list){
			map.put("val", userDao.getCount((int)map.get("occType")));
		}
		return list;
	}
	
	@Override
	public int care(Long userId, Long tUserId){
		int isCare = userConcernDao.checkIsConcern(tUserId, userId);
		if(isCare == 1){
//			userConcernDao.del(tUserId, userId);
			userConcernDao.update(tUserId, userId);
			return 0;
		}
		userConcernDao.save(tUserId, userId);
		//进入用户主页，不区分是否重复进入
		countDao.add(new Count(userId, userId, tUserId, "user", IntelligentEnum.User进主页.getType()));
		return 1;
	}

	@Transactional
	@Override
	public UserPO register(UserBean bean) {
		User user = bean.initUser();
		if(bean.getUserType() == 1){
			this.checkCode(user);
		}
//		if(userDao.checkPhone(bean.getPhone()) > 0){
//			throw new BusinessException("手机号已经存在");
//		}
		if(user.getId() != null){
			userDao.updateThirdKeyById(user.getThirdKey(), user.getId());
			UserInfo userInfo = bean.initUserInfo();
			userInfo.setUserId(user.getId());
			userInfoDao.updateByUserId(userInfo);
		} else {
			Long userId = userDao.save(user);
			//新增大白,小白时初始化权重值
			if(user.getUserType() == 1){
				weightDao.add(new Weight(userId, "user", IntelligentConstant.Cardinality_Init));
			}else{
				weightDao.add(new Weight(userId, "user", IntelligentConstant.Cardinality_Init));
			}
			UserInfo userInfo = bean.initUserInfo();
			userInfo.setUserId(user.getId());
			if(userInfo.getHeadImage() == null){
				userInfo.setHeadImage(CommConstant.USER_HEADIMG_DEFAULT_WX);
			}
			userInfoDao.setUTF8mb4();
			userInfoDao.save(userInfo);
			userInfoDao.setUTF8();
			userIncomeDao.save(user.getId());
		}
		createKeFu(user);
		return getUserByThirdKey(bean.getThirdKey());
	}

	private void createKeFu(User user) {
		QianluMsg kefu2 = new QianluMsg();
		kefu2.setUserId(user.getId());
		kefu2.setTuserId(CommConstant.KEFU_USER_ID);
		QianluMsg kefu1 = new QianluMsg();
		kefu1.setUserId(CommConstant.KEFU_USER_ID);
		kefu1.setTuserId(user.getId());
		qianluMsgDao.save(kefu2);
		qianluMsgDao.save(kefu1);
	}
	
	private void checkCode(User user){
		int i = userDao.useUcode(user.getCode());
		if(i != 1){
			User suser = userDao.getUserByUcode(user.getCode());
			if(suser == null || suser.getId() == null){
				throw new BusinessException("无效邀请码");
			}
			user.setId(suser.getId());
		}
		byte type = userDao.getTypeByUcode(user.getCode());
		if(type == 1){//大白
			user.setUserType((short)1);
		} else {
			user.setUserType((short)2);
		}
	}

	/**
	 * 用户信息修改后，用户进行重新分类
	 */
//	private void groupUser(UserInfoBean bean){
//		if(!StringUtils.isEmpty(bean.getOccupationName())){//用户行业分类
//			int occType = OccTypeEnum.getVal(bean.getOccupationName());
//			userDao.updateOccType(occType, bean.getUserId());
//		}
//		String company = bean.getCompanyName() == null ? "" : bean.getCompanyName();
//		String occ = bean.getOccupationName() == null ? "" : bean.getOccupationName();
//		String trade = bean.getTradeName() == null ? "" : bean.getTradeName();
//		//池子归类
//		List<ChiziPO> list = chiziService.group(company, occ, trade);
//		List<Long> idsList = new ArrayList<Long>();
//		for(ChiziPO po : list){
//			idsList.add(po.getId());
//		}
//		chiziService.cuUpdate(idsList, bean.getUserId());
//	}
	
	@Override
	public BirdsMsgPO getMsgPO(Long userId){
		
		return birdsMsgDao.getMsgCount(userId, BirdsMsgTypeEnum.有消息发送.getVal());
	}

	@Override
	public String getMsgCount(Long userId, short type) {
		int num = birdsMsgDao.getCount(userId, type);
		return num > 99 ? 99 + "+" : num + "";
	}
	
	@Override
	public void readMsg(BirdsMsg msg){
		birdsMsgDao.readed(msg);
	}

	@Override
	public void addMsg(BirdsMsg msg) {
		birdsMsgDao.save(msg);
	}

	@Override
	public PageBean<UserCenterPO> queryOBForPage(UserQueryBean bean, PageBean<UserCenterPO> page) {
		page.setData(userDao.queryOB(bean, page.getStart(), page.getPageSize()));
		page.setCount(userDao.queryOBCount(bean));
		return page;
	}

	@Override
	public PageBean<UserSearchPO> searchByStart(UserSearchBean bean,  PageBean<UserSearchPO> page) {
		if(bean.getStart() == null){
			bean.setStart("%%");
		}else{
			bean.setStart(bean.getStart() + "%");
		}
		page.setData(userDao.searchByStart(bean));
		return page;
	}
	
	@Override
	public PageBean<QianluMsgListPO> getUserMsgList(Long userId, PageBean<QianluMsgListPO> page){
		page.setData(qianluMsgDao.getList(userId, page.getStart(), page.getPageSize()));
		page.setCount(qianluMsgDao.getCount(userId));
		return page;
	}
	
	@Override
	public void checkSendMsg(Long userId, Long tuserId){
		User user = userDao.getById(userId);
		User tuser = userDao.getById(tuserId);
		int now = DateUtils.getMinutes();
		if((user.getUserType() == 1 || tuser.getUserType() == 1) && (user.getUserType() != 3 && tuser.getUserType() != 3)){
			OrderQueryBean bean = new OrderQueryBean();
			{
				if(user.getUserType() == 2 && tuser.getUserType() == 1){
					bean.setUserId(userId);
					bean.setTuserId(tuserId);
				}else if(user.getUserType() == 1 && tuser.getUserType() == 2){
					bean.setUserId(tuserId);
					bean.setTuserId(userId);
				}else{
					bean.setUserId(userId);
					bean.setTuserId(tuserId);
				}
				bean.setCheckDate(new Byte("1"));
				bean.setStatus((short)2);
			}
			List<Order> list = orderDao.getListN(bean);
			if(list == null || list.size() == 0){
				bean.setUserId(tuserId);
				bean.setTuserId(userId);
				list = orderDao.getListN(bean);
				if(list == null || list.size() == 0){
					throw new BusinessException("您今天还未预约");
				}
			}
			boolean checked = false;
			for(Order o : list){
				if(o.getTimeType() == null){
					continue;
				}
				if(TimeTypeEnum.get(o.getTimeType()).check(now, o.getXudan())){
					checked = true;
				}
			}
			if(checked == false){
				throw new BusinessException("预约时间未到，请在预约前五分钟进入聊天页面");
			}
		}
	}

	@Transactional
	@Override
	public QianluMsg checkExist(Long userId, Long tuserId) {
		QianluMsg po = qianluMsgDao.checkExsit(userId, tuserId);
		if(po == null || po.getId() == null){
			QianluMsg msg1 = new QianluMsg();
			msg1.setUserId(userId);
			msg1.setTuserId(tuserId);
			QianluMsg msg2 = new QianluMsg();
			msg2.setUserId(tuserId);
			msg2.setTuserId(userId);
			qianluMsgDao.save(msg1);
			qianluMsgDao.save(msg2);
			return  msg1;
		} else {
			QianluMsg msg = new QianluMsg();
			msg.setId(po.getId());
			msg.setUserId(po.getUserId());
			msg.setTuserId(po.getTuserId());
			return msg;
		}
	}

	@Transactional
	@Override
	public void updateUserInfo(UserInfoBean bean) {
		if(bean.getStatus() != null){
			userDao.updateUserStatus(bean.getOccType(), bean.getStatus(), bean.getUserId());
		}
		userInfoDao.updateByUserId(bean);
	}

	@Transactional
	@Override
	public void addUser(UserInfoBean bean) {
		User user = new User();
		{
			user.setStatus((short)1);
			user.setUserType((short)1);
			user.setCode(bean.getCode());
			user.setOccType(bean.getOccType());
		}
		this.checkCode(user);
		if(user.getId() != null){
			throw new BusinessException("无效邀请码");
		}
		Long userId = userDao.save(user);
		//新增大白时初始化权重值
		weightDao.add(new Weight(userId, "user", IntelligentConstant.Cardinality_Init));
		bean.setUserId(user.getId());
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(bean, userInfo);
		userInfoDao.save(userInfo);
		userIncomeDao.save(user.getId());
	}	
	
	@Transactional
	@Override
	public void addUserAdmin(UserInfoBean bean) {
		int count = userDao.useUcode(bean.getCode());
		if(count == 0){
			throw new BusinessException("无效邀请码");
		}
		User user = new User();
		{
			user.setStatus((short)1);
			user.setUserType((short)1);
			user.setCode(bean.getCode());
			user.setOccType(bean.getOccType());
		}
		Long userId = userDao.save(user);
		//新增大白时初始化权重值
		weightDao.add(new Weight(userId, "user", IntelligentConstant.Cardinality_Init));
		bean.setUserId(user.getId());
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(bean, userInfo);
		if(userInfo.getHeadImage() == null){
			userInfo.setHeadImage(CommConstant.USER_HEADIMG_DEFAULT_WX);
		}
		userInfoDao.save(userInfo);
		userIncomeDao.save(user.getId());
	}
	
	@Override
	public UserExtPO getUserExtPO(Long userId){
		return userDao.getUserExt(userId);
	}

	@Override
	public UserPO getByCode(String code) {
		UserPO upo = new UserPO();
		User user = userDao.getByCode(code);
		if(user == null || user.getId() == null){
			throw new BusinessException("用户不存在");
		}
		BeanUtils.copyProperties(user, upo);
		upo.setUserInfo(userInfoDao.getByUserId(user.getId()));
		return upo;
	}

	@Override
	public AdminUserPO login(String username, String password) {
		return userDao.login(username, password);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
		userInfoDao.deleteByUserId(id);
		userIncomeDao.deleteByUserId(id);
		userConcernDao.deleteByUserId(id);
		weightDao.deleteByIdTable(id, "user");
	}

	@Override
	public PageBean<UserCenterPO> getList(UserQueryBean bean, PageBean<UserCenterPO> page) {
		page.setData(userDao.getList(bean, page.getStart(), page.getPageSize()));
		page.setCount(userDao.getListCount(bean));
		return page;
	}
	
	@Override
	public PageBean<UserCenterPO> getAdminList(UserQueryBean bean, PageBean<UserCenterPO> page) {
		page.setData(userDao.getAdminList(bean, page.getStart(), page.getPageSize()));
		page.setCount(userDao.getListCount(bean));
		return page;
	}

	@Override
	public List<UserCenterPO> searchByKeywords(Long userId, String companyName, Integer occType, String tradeName) {
		String toTable = IntelligentConstant.ToTable_Search + occType + "," + tradeName;
		countDao.add(new Count(userId, toTable, IntelligentEnum.Weight标签搜索.getType()));
		if(companyName != null){
			companyName = "%" + companyName + "%";
		}
		return userDao.searchByKeywords(companyName, occType, tradeName);
	}

	@Override
	public void shareWX(Long userId, Long toId, String type) {
		if("userCenter".equals(type)){
			countDao.add(new Count(userId, toId, toId, "user", IntelligentEnum.Weight分享大白.getType()));
		}else if("article".equals(type)){
			Long toUserId = articleDao.getUserIdById(toId);
			countDao.add(new Count(userId, toUserId, toId, "article", IntelligentEnum.Weight分享文章.getType()));
		}else if("index".equals(type)){
			countDao.add(new Count(userId, IntelligentEnum.Weight分享主页.getType()));
		}else{
			
		}
	}
}
