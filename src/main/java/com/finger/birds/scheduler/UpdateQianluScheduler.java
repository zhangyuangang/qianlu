package com.finger.birds.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.dao.intelligent.CountDao;
import com.finger.birds.db.dao.intelligent.QianluDao;
import com.finger.birds.db.dao.intelligent.WeightDao;
import com.finger.birds.db.dao.user.UserDao;
import com.finger.birds.db.po.intelligent.CountPO;
import com.finger.birds.db.po.intelligent.WeightPO;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.model.IntelligentEnum;
import com.finger.birds.model.intelligent.Qianlu;
import com.finger.birds.utils.intelligent.WeightCount;

/**
 * @Author phil
 * @Data 2017/7/28.
 */
@Component
public class UpdateQianluScheduler {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private WeightDao weightDao;
	
	@Resource
	private CountDao countDao;
	
	@Resource
	private QianluDao qianluDao;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	//更新并插入前路头条表，为每个用户分别插入
	@Scheduled(cron = "0 58 * * * ?")
	public void initQianlu(){
		log.info("-----------------更新前路头条开始-------------------");
		List<UserCenterPO> userList = userDao.getAdminList(new UserQueryBean(), null, null);
		List<CountPO> countList = countDao.getListPreHourByNotToNoll();
		for (CountPO count : countList) {
			String content = null;
			String url = null;
			Short type = null;
			if("qianlu".equals(count.getToTable())){
				//前路头条不需要转换，直接计算score
				if(count.getToId() == 1){
					content = "快来看看运营妹妹刚写的的前路头条";
					url = count.getToTable();
					type = 1;
				}else if(count.getToId() == 2){
					content = "前路又新添一位大白，想必符合你";
					url = "" + count.getUserId();
					type = 2;
				}else if(count.getToId() == 3){
					content = "你关注的大白写了篇文章，免费的，不来看吗？";
					url = "" + count.getUserId();
					type = 3;
				}else if(count.getToId() == 4){
					content = "花一分钟来看看大白刚写的灵感随笔";
					url =  "" + count.getUserId();
					type = 4;
				}
				for (UserCenterPO user : userList) {
					String tableName = null;
					if(count.getToId() == 2){
						tableName = "user";
					}else if(count.getToId() == 3){
						tableName = "article";
					}else if(count.getToId() == 4){
						tableName = "dynamic";
					}
					WeightPO weight = weightDao.getByIdTable(count.getUserId(), tableName);
					Double score = WeightCount.productOfVector(weight, user, count.getScore());
					updateByUserId(user.getUserId(), content, type, url, score);
				}
			}else{
				//用户消息score需要转换成IntelligentEnum
				IntelligentEnum e = IntelligentEnum.get(count.getScore());
				if("article".equals(count.getToTable())){
					url = "" + count.getUserId();
					type = 5;
					if(count.getScore() == 30301){
						content = "您的文章访问量又增加了";
					}else if(count.getScore() == 30302){
						content = "您的文章被人点赞了";
					}else if(count.getScore() == 30303){
						content = "您的文章有新评论了，快来看看吧";
					}else if(count.getScore() == 30304){
						content = "您的文章有条评论被人点赞了";
					}
				}else if("dynamic".equals(count.getToTable())){
					url = "" + count.getUserId();
					type = 6;
					if(count.getScore() == 30201){
						content = "您的随笔访问量又增加了";
					}else if(count.getScore() == 30202){
						content = "您的随笔被人点赞了";
					}else if(count.getScore() == 30203){
						content = "您的随笔有新评论了，快来看看吧";
					}
				}
				updateByUserId(count.getToUserId(), content, type, url, (double)e.getScore());
				if(count.getToUserId2() != null){
					if("article".equals(count.getToTable())){
						url = "长文消息";
						type = 5;
						if(count.getScore() == 30303){
							content = "您评论的文章有人@了你";
						}else if(count.getScore() == 30304){
							content = "您的评论被人点赞了";
						}
					}
					updateByUserId(count.getToUserId2(), content, type, url, (double)e.getScore());
				}
			}
		}
		log.info("-----------------更新前路头条结束-------------------");
	}
	
	private void updateByUserId(Long userId, String content, Short type, String url, Double score) {
		Long qianluId = qianluDao.getIsReadIdByUserId(userId);
		if(qianluId != null){
			//有已读消息则替换
			qianluDao.editById(new Qianlu(qianluId, userId, content, type, url, score));
		}else{
			//没有已读消息则替换最小score的
			Qianlu qianlu =  qianluDao.getMinByUserId(userId);
			if(qianlu.getScore() < score){
				qianluDao.editById(new Qianlu(qianlu.getId(), userId, content, type, url, score));
			}
		}
	}
	
//	//初始化头条
//	@Scheduled(cron = "0 46 12 * * ?")
//	public void initQianlu1(){
//		List<UserCenterPO> userList = userDao.getAdminList(new UserQueryBean(), null, null);
//		int i = 1;
//		System.out.println("--------------------开始------------------");
//		for (UserCenterPO user : userList) {
//			System.out.println("--------------------第  " + i++ + "  条------------------");
//			String url = "https://mp.weixin.qq.com/s?__biz=MzU1NjA1NzYxMA==&mid=2247483704&idx=1&sn=1445c73387685a43e99795f56ad3bfe2&chksm=fbcb945dccbc1d4b912b9fbc5a4c01031e362987b381a9628fee3a9773a50f121b62ed84c97d&scene=0&key=e885a9508b54ccdf7bbb97ee17fedca0d8ff4ca51395354d3e4d7ba8d07a5d1b161c430a1620ffc7801b3ff02d3e4887122b3604213143bcc6c69920df89e70643dbedcf4f817d7e4b54c88224c7a0d9&ascene=1&uin=ODI1NjkyNzIy&devicetype=Windows+7&version=62040549&pass_ticket=aHy3WHOvWkRC3w8zVpzFnIn8PjhM9%2BgYd3o4whVDEuWMH6bUvbIDxo1gvh8N5fT%2B&winzoom=1";
//			qianluDao.editById(new Qianlu(user.getUserId()*100, user.getUserId(), "快来看看运营妹妹刚写的的前路头条", (short)1, url, (double)30));
//			qianluDao.editById(new Qianlu(user.getUserId()*100+1, user.getUserId(), "前路又新添一位大白，想必符合你", (short)2, "480", (double)36));
//			qianluDao.editById(new Qianlu(user.getUserId()*100+2, user.getUserId(), "你关注的大白写了篇文章，免费的，不来看吗？", (short)3, "109", (double)22));
//			qianluDao.editById(new Qianlu(user.getUserId()*100+3, user.getUserId(), "花一分钟来看看大白刚写的灵感随笔", (short)4, "97", (double)24));
//				
//		}
//		System.out.println("--------------------结束------------------");
//		qianluDao.editById(new Qianlu(68*100+4L, 68L, "您的文章有新评论了，快来看看吧", (short)5, "72", (double)16));
//		qianluDao.editById(new Qianlu(68*100+5L, 68L, "您的随笔被人点赞了", (short)6, "89", (double)12));
//		qianluDao.editById(new Qianlu(73*100+4L, 73L, "您的文章有新评论了，快来看看吧", (short)5, "72", (double)16));
//		qianluDao.editById(new Qianlu(73*100+5L, 73L, "您的随笔被人点赞了", (short)6, "89", (double)12));
//		qianluDao.editById(new Qianlu(202*100+4L, 202L, "您的文章有新评论了，快来看看吧", (short)5, "72", (double)16));
//		qianluDao.editById(new Qianlu(202*100+5L, 202L, "您的随笔被人点赞了", (short)6, "89", (double)12));
//		System.out.println("--------------------结束结束结束------------------");
//	}
}
