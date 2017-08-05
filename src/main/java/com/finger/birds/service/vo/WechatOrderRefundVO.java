package com.finger.birds.service.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.finger.birds.model.order.Order;
import com.finger.birds.ucontroller.jstl.StringUtils;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.bean.ReflectHelper;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.security.ALMD5;
import com.finger.birds.utils.security.WechatPayUtils;

public class WechatOrderRefundVO {
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String out_trade_no;
	private String total_fee;
	private String refund_desc = "大白拒绝";
	private String out_refund_no;
	private String refund_fee;
	private String transaction_id = "";

	public WechatOrderRefundVO() {
	}

	public WechatOrderRefundVO(Order order, String openId) {
		this.appid = CommConstant.APPID;
		this.mch_id = CommConstant.WECHAT_PAY_MCH_ID;
		String mcKey = CommConstant.WECHAT_PAY_MC_KEY;

		this.nonce_str = createRandomStr(order.getOrderCode());

		this.out_trade_no = order.getOrderCode();
		this.out_refund_no = order.getOrderCode();
		this.total_fee = (order.getPrice().multiply(new BigDecimal("100"))).intValue() + "";
		this.refund_fee = this.total_fee;
		if (StringUtils.isEmpty(openId)) {
			throw new BusinessException("无法获取用户openid");
		}
		Map<String, String> map = null;
		try {
			map = ReflectHelper.flectObj(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> sPara = WechatPayUtils.paraFilter(map);
		String str = WechatPayUtils.createLinkString(sPara);
		{// 退款
			this.sign = ALMD5.sign(str, "&key=" + mcKey, "utf-8").toUpperCase();
			String rsltXml = "";
			try {
				rsltXml = WechatPayUtils.postSSLRefund(CommConstant.WECHAT_REFUND_URL, WechatPayUtils.getXmlInfo(sPara, sign));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("退款申请失败");
			}
			Map<String, String> rsltMap = WechatPayUtils.parseXml(rsltXml);
			if ("SUCCESS".equals(rsltMap.get("return_code"))) {
				System.out.println("退款成功");
			} else {
				throw new BusinessException(rsltMap.get("return_msg"));
			}
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

	public String getRefund_desc() {
		return refund_desc;
	}

	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

}
