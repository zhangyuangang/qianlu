package com.finger.birds.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finger.birds.db.po.order.OrderListPO;
import com.finger.birds.service.order.OrderService;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.template.WechatTemplateUtils;

import net.sf.json.JSONObject;

/**
 * @Author phil
 * @Data 2017/7/19.
 */
@Component
public class OrderScheduler {
	
	@Resource
	private OrderService orderService;

	@Resource
	private WechatTemplateUtils wechatTemplateUtils;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Scheduled(cron = "0 55 9 * * ?")
	public void start1() throws Exception {
		start((short)1);
	}
	@Scheduled(cron = "0 25 10 * * ?")
	public void start2() throws Exception {
		start((short)2);
	}
	@Scheduled(cron = "0 55 18 * * ?")
	public void start3() throws Exception {
		start((short)3);
	}
	@Scheduled(cron = "0 25 19 * * ?")
	public void start4() throws Exception {
		start((short)4);
	}
	@Scheduled(cron = "0 55 19 * * ?")
	public void start5() throws Exception {
		start((short)5);
	}
	@Scheduled(cron = "0 25 20 * * ?")
	public void start6() throws Exception {
		start((short)6);
	}
	@Scheduled(cron = "0 55 20 * * ?")
	public void start7() throws Exception {
		start((short)7);
	}
	@Scheduled(cron = "0 25 21 * * ?")
	public void start8() throws Exception {
		start((short)8);
	}
	@Scheduled(cron = "0 55 21 * * ?")
	public void start9() throws Exception {
		start((short)9);
	}
	@Scheduled(cron = "0 25 22 * * ?")
	public void start10() throws Exception {
		start((short)10);
	}
	@Scheduled(cron = "0 55 22 * * ?")
	public void start11() throws Exception {
		start((short)11);
	}
	@Scheduled(cron = "0 25 23 * * ?")
	public void start12() throws Exception {
		start((short)12);
	}
	
	private void start(Short timeType){
		log.info("-----------------定时发送提醒模板开始-------------------");
		List<OrderListPO> list= orderService.getJobInfoByTimeTypeToday(timeType);
		for (OrderListPO po : list) {
			String tnickname = po.getTnickname();
	    	tnickname = tnickname==null?"用户":tnickname;
	    	String nickname = po.getNickname();
	    	nickname = nickname==null?"用户":nickname;
	    	String topenId = po.getTopenId();
	    	String openId = po.getOpenId();
	    	Long tuserId = po.getTuserId();
	    	Long userId = po.getUserId();
	    	String orderCode = po.getOrderCode();
	    	
	    	Long msgId = orderService.checkQianlu(userId, tuserId);
	    	Long tmsgId = orderService.checkQianlu(tuserId, userId);

	    	//给大白发消息
			String tdataStr = "{\"first\":{\"value\":\"尊敬的" + tnickname + "\"}, \"OrderSn\":{\"value\":\"" + orderCode + "\"}, \"OrderStatus\":{\"value\":\"交谈马上开始\"}, \"remark\":{\"value\":\"还有5min本次交谈开始，您可以提前进入交谈窗口。\"}}";
			wechatTemplateUtils.sendMsg(CommConstant.TEMAPLATE_ID_TIPS, JSONObject.fromObject(tdataStr), "http://m.upbirds.com/chat.html?selfId=" + tuserId +"&tId=" + userId + "&id=" + tmsgId, topenId, null);

			//给小白发消息
			String dataStr = "{\"first\":{\"value\":\"尊敬的" + nickname + "\"}, \"OrderSn\":{\"value\":\"" + orderCode + "\"}, \"OrderStatus\":{\"value\":\"交谈马上开始\"}, \"remark\":{\"value\":\"还有5min本次交谈开始，您可以提前进入交谈窗口。\"}}";
			wechatTemplateUtils.sendMsg(CommConstant.TEMAPLATE_ID_TIPS, JSONObject.fromObject(dataStr), "http://m.upbirds.com/chat.html?selfId=" + userId +"&tId=" + tuserId + "&id=" + msgId, openId, null);
		}
		log.info("-----------------定时发送提醒模板结束-------------------");
	}
}
