package com.finger.birds.service.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.finger.birds.model.order.Order;
import com.finger.birds.ucontroller.jstl.StringUtils;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.bean.ReflectHelper;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.security.ALMD5;
import com.finger.birds.utils.security.WechatPayUtils;

public class WechatOrderPayVO {

	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String body;
	private String device_info;
	private String attach;
	private String out_trade_no;
	private String total_fee;
	private String spbill_create_ip;
	private String time_start;
	private String notify_url;
	private String trade_type = "JSAPI";
	private String openid;
	private String prepay_id;
	private String timeStamp;
	private String goodsId;
	
	public WechatOrderPayVO() {
	}

	public WechatOrderPayVO(Order order, String openId) {
		this.appid = CommConstant.APPID;
		this.mch_id = CommConstant.WECHAT_PAY_MCH_ID;
		String mcKey = CommConstant.WECHAT_PAY_MC_KEY;
		
		this.notify_url = CommConstant.WECHAT_PAY_ASYN_URL;
		this.nonce_str = createRandomStr(order.getOrderCode());
		
		this.out_trade_no = order.getOrderCode();
		this.total_fee = (order.getPrice().multiply(new BigDecimal("100"))).intValue() + "";
		this.body = CommConstant.COMM_GOODS_NAME;
		this.goodsId = CommConstant.COMM_GOODS_ID;
		this.time_start = DateUtils.getNow24();
		this.timeStamp = new Date().getTime() + "";
		if (StringUtils.isEmpty(openId)) {
			throw new BusinessException("无法获取用户openid");
		}
		this.openid = openId;
		Map<String, String> map = null;
		try {
			map = ReflectHelper.flectObj(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> sPara = WechatPayUtils.paraFilter(map);
		String str = WechatPayUtils.createLinkString(sPara);
		{// 预下单签名
			this.sign = ALMD5.sign(str,
					"&key=" + mcKey,
					"utf-8").toUpperCase();
			String rsltXml = WechatPayUtils.postUnifiedOrder(CommConstant.WECHAT_PAY_URL,
					WechatPayUtils.getXmlInfo(sPara, sign));
			Map<String, String> rsltMap = WechatPayUtils.parseXml(rsltXml);
			if ("SUCCESS".equals(rsltMap.get("return_code"))) {
				this.prepay_id = rsltMap.get("prepay_id");
			} else {
				throw new BusinessException(rsltMap.get("return_msg"));
			}
		}
		{// 页面支付签名
			Map<String, String> toPayPara = new HashMap<String, String>();
			toPayPara.put("appId", this.appid);
			toPayPara.put("timeStamp", this.timeStamp);
			toPayPara.put("nonceStr", this.nonce_str);
			toPayPara.put("package", "prepay_id=" + this.prepay_id);
			toPayPara.put("signType", "MD5");
			String toPayStr = WechatPayUtils.createLinkString(toPayPara);
			this.sign = ALMD5.sign(toPayStr,
					"&key=" + CommConstant.WECHAT_PAY_MC_KEY,
					"utf-8").toUpperCase();
		}
	}

	protected String createRandomStr(String no) {
		String[] keys = { "q", "x", "w", "3", "r", "t", "z", "c", "m", "7" };
		String str = "";
		for (String s : no.split("")) {
			str += keys[Integer.parseInt(s)];
		}
		return str;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

}
