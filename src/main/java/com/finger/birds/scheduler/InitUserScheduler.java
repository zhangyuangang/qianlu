package com.finger.birds.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finger.birds.db.dao.intelligent.QianluDao;
import com.finger.birds.db.dao.intelligent.WeightDao;
import com.finger.birds.db.dao.user.UserDao;
import com.finger.birds.db.po.user.UserInfoPO;
import com.finger.birds.model.intelligent.Qianlu;
import com.finger.birds.model.intelligent.Weight;
import com.finger.birds.utils.IntelligentConstant;

/**
 * @Author phil
 * @Data 2017/7/26.
 */
@Component
public class InitUserScheduler {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private WeightDao weightDao;
	
	@Resource
	private QianluDao qianluDao;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	//初始化所有大白，小白，权重值，活跃度，被关注度
	//@Scheduled(cron = "0 30 1 * * ?")
	@Scheduled(cron = "0 3 17 * * ?")
	public void initUser(){
		log.info("-----------------初始化user开始-------------------");
		List<UserInfoPO> userList = userDao.getAll();
		int i = 1;
		for (UserInfoPO user : userList) {
			//初始化权重表
			if(weightDao.getByIdTable(user.getId(), "user") == null){
				weightDao.add(new Weight(user.getId(), "user", IntelligentConstant.Cardinality_Init));
			}
			//初始化前路头条表
			if(qianluDao.getCountByUserId(user.getId()) != 50){
				for (int j = 0; j < 50; j++) {
					qianluDao.add(new Qianlu(user.getId()*100+j, user.getId(), null, null, null, null));
				}
			}
			//初始化推荐大白表
			//初始化推荐长文表
			//初始化推荐随笔表
			
		}
		log.info("-----------------初始化user结束-------------------");
	}
}
