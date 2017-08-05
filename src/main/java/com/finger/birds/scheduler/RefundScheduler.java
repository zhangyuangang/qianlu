package com.finger.birds.scheduler;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finger.birds.service.order.OrderService;

/**
 * @Author phil
 * @Data 2017/7/22.
 */
@Component
public class RefundScheduler {
	
	@Resource
	private OrderService orderService;

	private final Logger log = Logger.getLogger(this.getClass());
	
	@Scheduled(cron = "0 0 1 * * ?")
	public void refund() throws Exception {
		log.info("-----------------定时退款开始-------------------");
		List<Map<String, Object>> list = orderService.getJobInfoByStatusToday();
		Long orderId = null;
		for (Map<String, Object> map : list) {
			orderId = (Long) map.get("id");
			orderService.refundMoney(orderId);
		}
		log.info("-----------------定时退款结束-------------------");
	}
	
}
