package com.finger.birds.service.order.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.db.dao.intelligent.CountDao;
import com.finger.birds.db.dao.msg.QianluMsgDao;
import com.finger.birds.db.dao.order.OrderDao;
import com.finger.birds.db.dao.user.UserDao;
import com.finger.birds.db.dao.user.UserIncomeDao;
import com.finger.birds.db.dao.user.UserInfoDao;
import com.finger.birds.db.po.order.OrderAmountPO;
import com.finger.birds.db.po.order.OrderListPO;
import com.finger.birds.model.IntelligentEnum;
import com.finger.birds.model.TimeTypeEnum;
import com.finger.birds.model.intelligent.Count;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.model.msg.QianluMsgHistory;
import com.finger.birds.model.order.Order;
import com.finger.birds.model.user.User;
import com.finger.birds.model.user.UserIncomeLog;
import com.finger.birds.model.user.UserInfo;
import com.finger.birds.model.user.UserPayLog;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.order.OrderService;
import com.finger.birds.service.vo.WechatOrderRefundVO;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.template.WechatTemplateUtils;

import net.sf.json.JSONObject;

@Service("orderService")
public class OrderServiceImpl extends BaseService implements OrderService{
	
	@Resource
	private OrderDao orderDao;

	@Resource
	private UserIncomeDao userIncomeDao;
	
	@Resource
	private QianluMsgDao qianluMsgDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private WechatTemplateUtils wechatTemplateUtils;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private CountDao countDao;
	
	@Transactional
	@Override
	public Long add(Long userId, Long tuserId, String intro) {
		Order order = new Order();
		order.setUserId(userId);
		order.setTuserId(tuserId);
		order.setIntro(intro);
		order.setStatus((short)0);
		//TODO 测试下单价格0.01
		if((userId == 73 || userId == 202 || userId == 452) && (tuserId == 68 || tuserId == 178)){
			order.setPrice(new BigDecimal(0.01));
		}else{
			order.setPrice(CommConstant.XUDAN_PRICE);
		}
		//TODO  测试下单价格0.01
		//order.setPrice(CommConstant.PRICE);
		countDao.add(new Count(userId, tuserId, "user", IntelligentEnum.User下订单.getType()));
		orderDao.save(order);
		String orderCode = createCode(DateUtils.getDay(), order.getId());//生成订单号
		Order norder = new Order();
		norder.setOrderCode(orderCode);
		orderDao.updateById(norder, order.getId());
		return order.getId();
	}
	
	private static String createCode(String time, Long id){
		String idStr = id+"";
		int k = 9 - idStr.length();
		for(int i = 0; i < k; i++){
			idStr = 0 + idStr;
		}
		return time + idStr;
	}

	@Override
	public Order getById(Long id) {
		return orderDao.getById(id);
	}

	//异步回调
	@Transactional
	@Override
	public void asnPayCallBack(String thridCode, String orderCode, String totalFee, HttpSession session){
		Timestamp now = new Timestamp (new Date().getTime());
		Order order = orderDao.getByCode(orderCode);
		if(order == null){
			log.error("订单不存在，订单号:" + orderCode);
			return;
		}
		String total_fee = (order.getPrice().multiply(new BigDecimal("100"))).intValue() + "";
		if(totalFee.equals(total_fee)){//判断回调费用是否与实际支付相等
			Order norder = new Order();
			norder.setStatus((short)1);
			orderDao.updateById(norder, order.getId());
			UserPayLog payLog = userIncomeDao.getPayLogByCode(order.getId());
			if(payLog == null || payLog.getId() == null){
				throw new BusinessException("在等待同步回调先完成");
			}
			UserIncomeLog incomeLog = new UserIncomeLog();
			{
				incomeLog.setFlow(new Byte("1"));
				incomeLog.setMoney(payLog.getMoney());
				incomeLog.setPayLogId(payLog.getId());
				incomeLog.setType((short)0);
				incomeLog.setUserId(payLog.getUserId());
			}
			userIncomeDao.addLog(incomeLog);
			userIncomeDao.updatePayLog(order.getId(), thridCode, now);
		} else {
			log.error("非法金额，订单号:" + orderCode + "金额:" + totalFee);
		}
		
		if(order.getXudan() == 0){
			//通知大白处理订单
			User user = userDao.getById(order.getTuserId());
			UserInfo userInfo = userInfoDao.getByUserId(user.getId());
			UserInfo userInfoSmall = userInfoDao.getByUserId(order.getUserId());
			String openid = user.getThirdKey();
//			String openid = "oCiL107dTXoNCNzWDjfBeHQWOqgE";
			String type = userInfoSmall.getCode()==null?"小白":"大白";
			String dataStr = "{\"first\":{\"value\":\"尊敬的" + userInfo.getNickname() + "\"}, \"OrderSn\":{\"value\":\"" + orderCode + "\"}, \"OrderStatus\":{\"value\":\"" + type + userInfoSmall.getNickname() + "已支付\"}, \"remark\":{\"value\":\"请您尽快选中与小白交流的时段！\"}}";
			wechatTemplateUtils.sendMsg(CommConstant.TEMAPLATE_ID_TO_DABAI_CHOOSE, JSONObject.fromObject(dataStr), "http://m.upbirds.com/order/changeTime.html?orderId=" + order.getId(), openid, session);
			//给小白发消息
			String tdataStr = "{\"first\":{\"value\":\"尊敬的" + userInfoSmall.getNickname() + "\"}, \"OrderSn\":{\"value\":\"" + orderCode + "\"}, \"OrderStatus\":{\"value\":\"等待大白接受\"}, \"remark\":{\"value\":\"您已下单成功，等待大白选择交流时间\"}}";
			wechatTemplateUtils.sendMsg(CommConstant.TEMAPLATE_ID_TIPS, JSONObject.fromObject(tdataStr), null, userInfoSmall.getThirdKey(), null);
		} else {//续单 //TODO 处理续单消息
			User user = userDao.getById(order.getTuserId());
			UserInfo userInfo = userInfoDao.getByUserId(user.getId());
			UserInfo userInfoSmall = userInfoDao.getByUserId(order.getUserId());
			String openid = user.getThirdKey();
//			String openid = "oCiL107dTXoNCNzWDjfBeHQWOqgE";
			String type = userInfoSmall.getCode()==null?"小白":"大白";
			String dataStr = "{\"first\":{\"value\":\"尊敬的" + userInfo.getNickname() + "\"}, \"OrderSn\":{\"value\":\"" + order.getOrderCode() + "\"}, \"OrderStatus\":{\"value\":\"" + type + userInfoSmall.getNickname() + "已支付\"}, \"remark\":{\"value\":\"小白续单成功，请您继续与小白交流！\"}}";
			wechatTemplateUtils.sendMsg(CommConstant.TEMAPLATE_ID_TO_DABAI_CHOOSE, JSONObject.fromObject(dataStr), "http://m.upbirds.com/order/changeTime.html?orderId=" + order.getId(), openid, session);
		}
	}
	
	@Transactional
	@Override
	public void synPayCallBack(Long orderId){
		Order order = orderDao.getById(orderId);
		Timestamp now = new Timestamp (new Date().getTime());
		if(order == null){
			throw new BusinessException("所支付订单不存在");
		}
		UserPayLog payLog = new UserPayLog();
		{
			payLog.setSynCallbackTime(now);
			payLog.setMoney(order.getPrice());
			payLog.setTargetType((short)1);
			payLog.setTargetId(orderId);
			payLog.setUserId(order.getUserId());
			payLog.setCode(order.getOrderCode());
			payLog.setCallbackStatus((short)1);
		}
		userIncomeDao.addPayLog(payLog);
		
		Order norder = new Order();
		norder.setPayTime(now);
		norder.setStatus((short)5);
		orderDao.updateById(norder, orderId);
	}
	
	@Transactional
	@Override
	public void choose(Long orderId, Short timeType, Integer status, Long userId, Timestamp startDate){
		Order order = orderDao.getById(orderId);
		startDate = startDate == null ? new Timestamp(System.currentTimeMillis()) : startDate;
		if(order == null || order.getId() == null){
			throw new BusinessException("订单不存在");
		}
		if(!order.getTuserId().equals(userId)){
			throw new BusinessException("你无权处理该订单");
		}
		if(order.getStatus() < 1){
			throw new BusinessException("订单还未支付");
		}
		if(order.getStatus() > 1){
			throw new BusinessException("你已经处理该订单");
		}
		
		OrderQueryBean bean = new OrderQueryBean();
		bean.setCheckDate(new Byte("1"));
		bean.setTuserId(order.getUserId());
		bean.setStatus((short)2);
		List<Order> list = orderDao.getListN(bean);
		for(Order o : list){
			if(o.getTimeType().equals(timeType)){
				throw new BusinessException("该时段您已经预约其他小白");
			}
			if((o.getTimeType() + o.getXudan()) == timeType){
				throw new BusinessException("该时段您已经预约其他小白");
			}
		}
		OrderQueryBean beanSmall = new OrderQueryBean();
		beanSmall.setCheckDate(new Byte("1"));
		beanSmall.setUserId(userId);
		beanSmall.setStatus((short)2);
		List<Order> listSmall = orderDao.getListN(bean);
		for(Order o : listSmall){
			if(o.getTimeType().equals(timeType)){
				throw new BusinessException("该时段小白已经有约别人");
			}
			if((o.getTimeType() + o.getXudan()) == timeType){
				throw new BusinessException("该时段小白已经有约别人");
			}
		}
		//取到已过去的时间timeType
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		List<Short> passTime = TimeTypeEnum.getHasPassTimeType(hour*60 + minute);
		if(passTime.contains(status)){
			throw new BusinessException("请选择当前时间之后的时间段");
		}
		
		Order norder = new Order();
		if(status == 1){//确认
			norder.setStatus((short)2);
			norder.setTimeType(timeType);
			norder.setStartDate(startDate);
			//创建消息列表
			Long msgId = checkQianlu(order.getUserId(), order.getTuserId());
			UserPayLog payLog = userIncomeDao.getPayLogByCode(order.getId());
			{//计算收入
				userIncomeDao.updateLogStatusByPayLogId(payLog.getId(), new Byte("1"));
				userIncomeDao.updateByUserId(order.getTuserId(), order.getPrice(), 1);
			}
			{//给小白发消息
				UserInfo user = userInfoDao.getByUserId(order.getUserId());
				String openId = user.getThirdKey();
				
				TimeTypeEnum timeTypeEnum = TimeTypeEnum.get(timeType);
				String dataStr = "{\"name\":{\"value\":\"" + CommConstant.COMM_GOODS_NAME + "\"}, \"remark\":{\"value\":\"大白已经确认时段[" + timeTypeEnum.getName() + "]！为提高服务质量，我们会提前5min开通聊天窗口，以便双方的快速认识。\"}}";
				wechatTemplateUtils.sendMsg(CommConstant.TEMPLATE_ID_TO_XIAOBAI_SUCCESS, JSONObject.fromObject(dataStr), "http://m.upbirds.com/chat.html?selfId=" + order.getUserId() +"&tId=" + order.getTuserId() + "&id=" + msgId, openId, null);
			}
		} else {
			norder.setStatus((short)3);
			//退款 
			User user = userDao.getById(order.getUserId());
			WechatOrderRefundVO vo = new WechatOrderRefundVO(order, user.getThirdKey());
			log.info("申请退款成功，orderId:" + vo.getOut_trade_no() + "，time:" + DateUtils.getNow());
			UserPayLog payLog = userIncomeDao.getPayLogByCode(order.getId());
			UserIncomeLog incomeLog = new UserIncomeLog();
			{//退款
				incomeLog.setFlow(new Byte("1"));
				incomeLog.setMoney(payLog.getMoney());
				incomeLog.setPayLogId(payLog.getId());
				incomeLog.setType((short)-1);
				incomeLog.setUserId(payLog.getUserId());
			}
			userIncomeDao.addLog(incomeLog);
		}
		orderDao.updateById(norder, orderId);
	}
	
	public void refundMoney(Long orderId){
		Order order = orderDao.getById(orderId);
		Order norder = new Order();
		norder.setStatus((short)3);
		//退款 
		User user = userDao.getById(order.getUserId());
		WechatOrderRefundVO vo = new WechatOrderRefundVO(order, user.getThirdKey());
		log.info("申请退款成功，orderId:" + vo.getOut_trade_no() + "，time:" + DateUtils.getNow());
		UserPayLog payLog = userIncomeDao.getPayLogByCode(order.getId());
		UserIncomeLog incomeLog = new UserIncomeLog();
		{//退款
			incomeLog.setFlow(new Byte("1"));
			incomeLog.setMoney(payLog.getMoney());
			incomeLog.setPayLogId(payLog.getId());
			incomeLog.setType((short)-1);
			incomeLog.setUserId(payLog.getUserId());
		}
		userIncomeDao.addLog(incomeLog);
		orderDao.updateById(norder, orderId);
	}
	
	public Long checkQianlu(Long userId, Long tuserId){
		QianluMsg po = qianluMsgDao.checkExsit(userId, tuserId);
		Long id = null;
		if(po == null || po.getId() == null){
			QianluMsg msg1 = new QianluMsg();
			msg1.setUserId(userId);
			msg1.setTuserId(tuserId);
			QianluMsg msg2 = new QianluMsg();
			msg2.setUserId(tuserId);
			msg2.setTuserId(userId);
			qianluMsgDao.save(msg1);
			qianluMsgDao.save(msg2);
			id = msg1.getId();
		} else {
			id = po.getId();
		}
		return id;
	}
	
	@Override
	public PageBean<OrderListPO> getList(OrderQueryBean bean, PageBean<OrderListPO> page) {
		if(bean.getOrderCode() != null){
			bean.setOrderCode("%" + bean.getOrderCode() + "%");
		}
		page.setData(orderDao.getList(bean, page.getStart(), page.getPageSize()));
		page.setCount(orderDao.getListCount(bean));
		return page;
	}

	@Override
	public Map<String, Object> getOrderData() {
		Map<String, Object> map = new HashMap<>();
		map.putAll(orderDao.getTotalPayPerson());
		map.putAll(orderDao.getTotalProfitPerson());
		map.putAll(orderDao.getTotalAmount());
		return map;
	}

	@Override
	public PageBean<OrderListPO> searchByStart(OrderQueryBean bean, PageBean<OrderListPO> page) {
		if(bean.getOrderCode() == null){
			bean.setOrderCode("%%");
		}else{
			bean.setOrderCode(bean.getOrderCode() + "%");
		}
		page.setData(orderDao.searchByStart(bean));
		return page;
	}

	@Override
	public PageBean<OrderAmountPO> getPayAmountList(OrderQueryBean bean, PageBean<OrderAmountPO> page) {
		List<OrderAmountPO> data = orderDao.getPayAmountList(bean, page.getStart(), page.getPageSize());
		Iterator<OrderAmountPO> iter = data.iterator();
		while(iter.hasNext()){
			OrderAmountPO map = iter.next();
			Long userId = (Long) map.getUserId();
			List<Map<String, Object>> listMap = orderDao.getOrderCodeByUserId(userId);
			List<String> list = new LinkedList<>();
			for (Map<String, Object> map2 : listMap) {
				list.add((String) map2.get("orderCode"));
			}
			map.setOrderCode(list);
		}
		page.setData(data);
		page.setCount(orderDao.getPayListCount(bean));
		return page;
	}
	
	@Override
	public PageBean<OrderAmountPO> getProfitAmountList(OrderQueryBean bean, PageBean<OrderAmountPO> page) {
		List<OrderAmountPO> data = orderDao.getProfitAmountList(bean, page.getStart(), page.getPageSize());
		Iterator<OrderAmountPO> iter = data.iterator();
		while(iter.hasNext()){
			OrderAmountPO map = iter.next();
			Long userId = (Long) map.getUserId();
			List<Map<String, Object>> listMap = orderDao.getOrderCodeByUserId(userId);
			List<String> list = new LinkedList<>();
			for (Map<String, Object> map2 : listMap) {
				list.add((String) map2.get("orderCode"));
			}
			map.setOrderCode(list);
		}
		page.setData(data);
		page.setCount(orderDao.getProfitListCount(bean));
		return page;
	}

	@Override
	public List<OrderListPO> getJobInfoByTimeTypeToday(Short timeType) {
		return orderDao.getJobInfoByTimeTypeToday(timeType);
	}
	
	@Override
	public List<Short> getListN(OrderQueryBean bean){
		List<Short> list = new ArrayList<Short>();
		List<Order> orderList = orderDao.getListN(bean);
		for(Order o : orderList){
			list.add(o.getTimeType());
		}
		return list;  
	}

	@Override
	public Order getTopByUserId(Long tuserId) {
		OrderQueryBean bean = new OrderQueryBean();
		bean.setCheckDate(new Byte("1"));
		bean.setTuserId(tuserId);
		bean.setStatus((short)1);
		List<Order> list = orderDao.getListN(bean);
		return list.get(0);
	}

	@Override
	public List<Map<String, Object>> getJobInfoByStatusToday() {
		return orderDao.getJobInfoByStatusToday();
	}
	
	@Transactional
	@Override
	public Long xuDan(Long userId, Long tuserId){
		int now = DateUtils.getMinutes();
		OrderQueryBean bean = new OrderQueryBean();
		bean.setCheckDate(new Byte("1"));
		bean.setTuserId(tuserId);
		bean.setStatus((short)2);
		List<Order> list = orderDao.getListN(bean);
		Long nowOrderId = null;
		Order nowOrder = null;
		for(Order o : list){
			if(o.getTimeType() == null){
				continue;
			}
			if(TimeTypeEnum.get(o.getTimeType()).check(now, o.getXudan())){
				nowOrder = o;
				nowOrderId = o.getId();
				break;
			}
		}
		if(nowOrder == null){
			throw new BusinessException("无法续单[你还未在该时段下单]");
		}
		short nextTimeType = (short)(nowOrder.getTimeType() + 1);
		for(Order o : list){
			if(o.getTimeType() == null){
				continue;
			}
			if(o.getTimeType() == nextTimeType){
				throw new BusinessException("大白接下来已经有约无法续单");
			}
		}
		
		OrderQueryBean sbean = new OrderQueryBean();
		sbean.setCheckDate(new Byte("1"));
		sbean.setUserId(userId);
		sbean.setStatus((short)2);
		List<Order> slist = orderDao.getListN(sbean);
		for(Order o : slist){
			if(o.getTimeType() == nextTimeType){
				throw new BusinessException("您接下来已有约");
			}
		}
		
		
		if(nowOrderId == null){
			throw new BusinessException("聊天已经结束，无法续单，您可以选择重新下单约该大白。");
		}
		
		int had = orderDao.checkXuDan(userId, tuserId, nowOrder.getTimeType(), (short)(nowOrder.getXudan() + 1));
		if(had > 0){
			throw new BusinessException("你已经在接下来的"+CommConstant.xudanTime+"分钟续单。");
		}
		Order order = new Order();
		order.setUserId(userId);
		order.setTuserId(tuserId);
		order.setIntro("小白续单");
		order.setStatus((short)0);
		//TODO 测试下单价格0.01
		if((userId == 73 || userId == 202 || userId == 452) && (tuserId == 68 || tuserId == 178)){
			order.setPrice(new BigDecimal(0.01));
		}else{
			order.setPrice(CommConstant.XUDAN_PRICE);
		}
		//TODO  测试下单价格0.01
		//order.setPrice(CommConstant.XUDAN_PRICE);
		order.setTimeType(nowOrder.getTimeType());
		order.setXudan((short)(nowOrder.getXudan() + 1));
		order.setStartDate(new Timestamp(new Date().getTime()));
		countDao.add(new Count(userId, tuserId, "user", IntelligentEnum.User续单.getType()));
		orderDao.save(order);
		String orderCode = createCode(DateUtils.getDay(), order.getId());//生成订单号
		Order norder = new Order();
		norder.setOrderCode(orderCode);
		orderDao.updateById(norder, order.getId());
		
		return order.getId();
	}
	
	@Override
	public void xudanConfirm(Long orderId, Long userId, Integer status){
		Order order = orderDao.getById(orderId);
		if(order == null || order.getId() == null){
			throw new BusinessException("订单不存在");
		}
		
		if(!order.getTuserId().equals(userId)){
			throw new BusinessException("你无权处理该订单");
		}
		
		if(order.getStatus() < 1){
			throw new BusinessException("订单还未支付");
		}
		
		if(order.getStatus() > 1){
			throw new BusinessException("你已经处理该订单");
		}
		//重复代码
		Order norder = new Order();
		if(status == 1){//确认
			norder.setStatus((short)2);
			//创建消息列表
			Long msgId = checkQianlu(order.getUserId(), order.getTuserId());
			UserPayLog payLog = userIncomeDao.getPayLogByCode(order.getId());
			{//计算收入
				userIncomeDao.updateLogStatusByPayLogId(payLog.getId(), new Byte("1"));
				userIncomeDao.updateByUserId(order.getTuserId(), order.getPrice(), 1);
			}
			{//给小白发消息
				UserInfo user = userInfoDao.getByUserId(order.getUserId());
				String openId = user.getThirdKey();
				
				String dataStr = "{\"name\":{\"value\":\"" + CommConstant.COMM_GOODS_NAME + "\"}, \"remark\":{\"value\":\"大白已经同意您得续单请求\"}}";
				wechatTemplateUtils.sendMsg(CommConstant.TEMPLATE_ID_TO_XIAOBAI_SUCCESS, JSONObject.fromObject(dataStr), "http://m.upbirds.com/chat.html?selfId=" + order.getUserId() +"&tId=" + order.getTuserId() + "&id=" + msgId, openId, null);
			}
		} else {
			norder.setStatus((short)3);
			//退款 
			User user = userDao.getById(order.getUserId());
			WechatOrderRefundVO vo = new WechatOrderRefundVO(order, user.getThirdKey());
System.out.println("续单:申请退款成功，orderId:" + vo.getOut_trade_no() + "，time:" + DateUtils.getNow());
log.info("续单:申请退款成功，orderId:" + vo.getOut_trade_no() + "，time:" + DateUtils.getNow());
			UserPayLog payLog = userIncomeDao.getPayLogByCode(order.getId());
			UserIncomeLog incomeLog = new UserIncomeLog();
			{//退款
				incomeLog.setFlow(new Byte("1"));
				incomeLog.setMoney(payLog.getMoney());
				incomeLog.setPayLogId(payLog.getId());
				incomeLog.setType((short)-1);
				incomeLog.setUserId(payLog.getUserId());
			}
			userIncomeDao.addLog(incomeLog);
		}
		orderDao.updateById(norder, orderId);
	}

	@Override
	public void sendXuDanMsg(Long orderId) {
		Order order = orderDao.getById(orderId);
		if(order == null || order.getId() == null){
			throw new BusinessException("订单不存在");
		}
		if(order.getIsSend() == 1){
			throw new BusinessException("订单消息已经发送过。");
		}
		Order nOrder = new Order();
		nOrder.setIsSend(new Byte("1"));
		orderDao.updateById(nOrder, orderId);
	}

	@Override
	public Map<String, Object> getMsgByOrderCode(String orderCode) {
		Order order = orderDao.getByCode(orderCode);
		QianluMsg msg = qianluMsgDao.checkExsit(order.getUserId(), order.getTuserId());
		QianluMsg tmsg = qianluMsgDao.checkExsit(order.getTuserId(), order.getUserId());
		List<QianluMsgHistory> list = qianluMsgDao.getMsgList(msg.getId(), tmsg.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("smallId", msg.getId());
		map.put("bigId", tmsg.getId());
		map.put("smallName", userInfoDao.getByUserId(order.getUserId()).getNickname());
		map.put("bigName", userInfoDao.getByUserId(order.getTuserId()).getNickname());
		return map;
	}
	
//	public static void main(String[] args) {
//		//String dataStr = "{\"first\":\"尊敬的" + "stra" + "\", \"OrderSn\":\"" + 1223 + "\", \"OrderStatus\":\"小白已支付\", \"remark\":\"请您尽快选中与小白交流的时段！\"}";
//		String dataStr = "{\"name\":{\"value\":\"" + CommConstant.COMM_GOODS_NAME + "\"}, \"remark\":{\"value\":\"大白已经确认时段[18:00-19:00]！//n为提高服务质量，我们会提前5min开通聊天窗口，以便双方的快速认识。\"}}";
//		JSONObject json = JSONObject.fromObject(dataStr);
//		System.out.println(json.getString("OrderSn"));
//	}
	
	
}
