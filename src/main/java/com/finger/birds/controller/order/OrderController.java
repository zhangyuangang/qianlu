
package com.finger.birds.controller.order;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.bean.OrderQueryBean;
import com.finger.birds.model.TimeTypeEnum;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.model.order.Order;
import com.finger.birds.service.keyvalue.KeyvalueService;
import com.finger.birds.service.msg.QianluMsgService;
import com.finger.birds.service.order.OrderService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.service.vo.WechatOrderPayVO;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.rslt.type.Result;
import com.finger.birds.utils.security.WechatPayUtils;
import com.finger.birds.utils.template.WechatTemplateUtils;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController<OrderController>{
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private WechatTemplateUtils wechatTemplateUtils;
	
	@Resource
	private UserService userService;
	
	@Resource
	private QianluMsgService qianluMsgService;
	
	@Resource
	private KeyvalueService keyvalueService;
	
	@RequestMapping(value="/addOrder.html")
	public ModelAndView addOrder(ModelAndView mav, Long tuserId, String intro){
		Long userId = super.getUserId();
		Date date = new Date();
		if(!date.before(TimeTypeEnum.getLastEndTime())){
			throw new BusinessException("今天太晚啦，请您明日再来下单");
		}
		Long orderId = orderService.add(userId, tuserId, intro);
		String openid = (String)this.getFromSession(CommConstant.WECHAT_OPENID);
		WechatOrderPayVO payVo = new WechatOrderPayVO(orderService.getById(orderId), openid);
		keyvalueService.addQuestion(userId, tuserId, intro);
		mav.addObject("rslt", payVo);
		mav.addObject("tuserId", tuserId);
		mav.addObject("orderId", orderId);
		mav.addObject("type", "XIADAN");
		mav.setViewName("wechatPay");
		return mav;
	}
	
	@RequestMapping(value="/paySuccess.html", method=RequestMethod.POST)
	public void paySuccess(){
		
	}
	
	@RequestMapping(value="/wechatASynchroCallBack.html")
	public void wechatCallback(@RequestBody String body, HttpServletResponse response, HttpServletRequest request){
		Map<String, String> map = WechatPayUtils.parseXml(body);
		String retrun_code = map.get("return_code");
		if("SUCCESS".equals(retrun_code)){
			String result_code = map.get("result_code");
			if("SUCCESS".equals(result_code)){
				String mch_id = map.get("mch_id");
				String openid = map.get("openid");
				String transaction_id = map.get("transaction_id");
				String trade_type = map.get("trade_type");
				String total_fee = map.get("total_fee");
				String time_end = map.get("time_end");
				String out_trade_no = map.get("out_trade_no");
				String bank_type = map.get("bank_type");
				if(mch_id.equals(CommConstant.WECHAT_PAY_MCH_ID)){
					orderService.asnPayCallBack(transaction_id, out_trade_no, total_fee, request.getSession());
				} else{
					//do nothing
				}
				try{
					AjaxUtils.writeXml(response, "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				} catch(BusinessException e){
					AjaxUtils.writeXml(response, "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[" + e.getMessage() + "]]></return_msg></xml>");
				}
			} else {
				AjaxUtils.writeXml(response, "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[result_code fail]]></return_msg></xml>");
			}
		} else {
			AjaxUtils.writeXml(response, "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[return_code fail]]></return_msg></xml>");
		}
	}
	
	@RequestMapping("/synCallBack.html")
	public void synCallBack(HttpServletResponse response, Long orderId){
		super.getUser();
		Result<String> rslt = new Result<String>();
		orderService.synPayCallBack(orderId);
//		orderService.asnPayCallBack("79979790", "20170630000000009", "1");
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/getById.html")
	public ModelAndView getById(ModelAndView mav){
		return mav;
	}
	
	@RequestMapping(value="/choose.html")
	public ModelAndView choose(ModelAndView mav, Long orderId, Short timeType, Integer status, String startDate){
		Long userId = super.getUserId();
		orderService.choose(orderId, timeType, status, userId, DateUtils.strToTimestamp("yyyy-MM-dd", startDate));
		Order order = orderService.getById(orderId);
		QianluMsg msg = qianluMsgService.getByUID(order.getTuserId(), order.getUserId());
		mav.setViewName("redirect:/chat.html?selfId=" + order.getTuserId() +"&tId=" + order.getUserId() + "&id=" + msg.getId());
		return mav;
	}
	
	@RequestMapping(value="/changeTime.html")
	public ModelAndView changeTime(ModelAndView mav, Long orderId){
		Long userId = super.getUserId();
		Order order = null;
		if(orderId == null){
			order = orderService.getTopByUserId(userId);
		}else{
			order = orderService.getById(orderId);
			if(!order.getTuserId().equals(userId)){
				throw new BusinessException("您无权处理该订单");
			}
		}
		if(!DateUtils.format("yyyyMMdd", order.getPayTime()).equals(DateUtils.format("yyyyMMdd", Calendar.getInstance().getTime()))){
			throw new BusinessException("订单已经过期");
		}
		if(order.getTimeType() != null || order.getStatus() < 1){
			QianluMsg msg = qianluMsgService.getByUID(order.getTuserId(), order.getUserId());
			mav.setViewName("redirect:/chat.html?selfId=" + order.getTuserId() +"&tId=" + order.getUserId() + "&id=" + msg.getId());
			return mav;
		}
		mav.addObject("order", order);
		mav.addObject("payTime", DateUtils.format("yyyy-MM-dd HH:mm:ss", order.getPayTime()));
		mav.addObject("ouser", userService.getById(order.getUserId()));
		//取到大白已有约的时间timeType
		OrderQueryBean bean = new OrderQueryBean();
		bean.setCheckDate(new Byte("1"));
		bean.setTuserId(userId);
		bean.setStatus((short)2);
		List<Short> list = orderService.getListN(bean);
		//取到小白已有约的时间timeType
		OrderQueryBean beanSmall = new OrderQueryBean();
		beanSmall.setCheckDate(new Byte("1"));
		beanSmall.setUserId(order.getUserId());
		beanSmall.setStatus((short)2);
		List<Short> listSmall = orderService.getListN(beanSmall);
		//取到已过去的时间timeType
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		List<Short> passTime = TimeTypeEnum.getHasPassTimeType(hour*60 + minute);
		
		Set<Short> set = new HashSet<>();
		set.addAll(list);
		set.addAll(listSmall);
		set.addAll(passTime);
		
		mav.addObject("selected", set);
		
		mav.setViewName("changetime");
		return mav;
	}
	
	@RequestMapping("/xudan.html")
	public ModelAndView xudan(ModelAndView mav, Long tuserId){
		Long userId = super.getUserId();
		String openid = (String)this.getFromSession(CommConstant.WECHAT_OPENID);
		Long orderId = orderService.xuDan(userId, tuserId);
		WechatOrderPayVO payVo = new WechatOrderPayVO(orderService.getById(orderId), openid);
		QianluMsg msg = qianluMsgService.getByUID(userId, tuserId);
		mav.addObject("rslt", payVo);
		mav.addObject("tuserId", tuserId);
		mav.addObject("orderId", orderId);
		mav.addObject("type", "XUDAN");
		mav.addObject("msgId", msg.getId());
		mav.setViewName("wechatPay");
		return mav;
	}
	
	@RequestMapping("/xudanConfirm.html")
	public void xudanConfirm(HttpServletResponse response, String xudanId, Integer status){
		Long userId = super.getUserId();
//		Long userId = 68L;
		Result<String> rslt = new Result<String>();
		Long orderId = null;
		try{
			orderId = Long.parseLong(xudanId);
		} catch (Exception e) {
			throw new BusinessException("参数异常【xudanId】");
		}
		orderService.xudanConfirm(orderId, userId, status);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/sendXuDanMsg.html")
	public void sendXuDanMsg(HttpServletResponse response, String xudanId){
		super.getUserId();
		Result<String> rslt = new Result<String>();
		Long orderId = null;
		try{
			orderId = Long.parseLong(xudanId);
		} catch (Exception e) {
			throw new BusinessException("参数异常【xudanId】");
		}
		orderService.sendXuDanMsg(orderId);
		AjaxUtils.write(response, rslt);
	}
}
