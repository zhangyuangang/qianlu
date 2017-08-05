package com.finger.birds.utils;

import java.math.BigDecimal;

public interface CommConstant {
	
	/** 系统通用字符串连接符，下划线 */
	String STR_CONNECTOR = "_";
	/** admin登录缓存的id的 key*/
	String ADMIN_ID="admin";
	
	String COMM_REPLACE = "xxxx";
	String TIME_REPLACE = "tttt";
	/**zbs字符串，apk上传时，filename前缀**/
	String ZBS="zbs";
	
	/**项目host，在master的客户端管理里边，判断是否重新上传了app**/
	String ZBUBAOSHI="zhubaoshi";
	/***缩略图文件后缀***/
	String SMALLER = "-smaller";
	
	/**用户默认头像**/
	String USER_HEADIMG_DEFAULT = "face.jpg";
	String USER_HEADIMG_DEFAULT_WX = "http://wx.qlogo.cn/mmopen/WXUfyLmt4mfYLbcphWz9g4qgcAdRNwdAqxIiaQA8iaHAt9g9WP6WlCZ5UiblT6GudKwBOJcqr9QRoPTC4sBcwaUodJkhLBm2x5d/0";
	
/**================ Cache Code start --- ===================*/
	
	/** 字符串code的标志符"code" */
	String SIGN_CODE = "code";

	/** 权限在redis中的缓存key前缀 **/
	String AUTHORITY_VALI_KEY_PRE = "authority_user_";// 需要验证的权限
	String AUTHORITY_COMM_KEY_PRE = "authority_common_";// 不需要验证的权限
	
	/** vcode_[sessionId] 用于缓存验证码*/
	String VCODE_CACHE_KEY_PRE = "vcode_";
	
	/** user_[sessionId] 用于缓存验证码*/
	String USER_CACHE_KEY_PRE = "user_";
	
	/**垃圾图片清理缓存**/
	String IMG_CLEAR_KEY = "img_clear_key";
	
	
/**================ Cache Code end --- ===================*/
	
	String EMAIL_BIND_TEMP_MAIL = "temp_bind_email_";
	
	String SPLIT = "<split>";
	
	String DEFAULT_PNG = "default.png";
	
	String APP_H5_LOGIN_KEY = "app_h5_login_key";
	
	String WECHAT_H5_USER_KEY = "wechat_h5_user_key";
	
	String WECHAT_OPENID = "wechat_openid";
	
	String RESOURCE_URL = "/usr/local/nginx/resource";
	
	String URL = "http://m.upbirds.com";
	
	String APPID = "wx8858c54271aadf64";
	
	String APPSCRECT = "a8b485748be53d6dea729d33b2ecd3cc";
	
	String APP_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	//静态资源目录
	String IMG_URL = "http://resource.upbirds.com/";
	
	//统一下单地址
	String WECHAT_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	//退款接口地址
	String WECHAT_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	//商户KEY，商户平台
	String WECHAT_PAY_MC_KEY = "QWERTYUIOPmiju457dfbsdgeDFHFS7dd";
	
	//商户ID
	String WECHAT_PAY_MCH_ID = "1482274422";
	
	//微信支付异步回调URL
	String WECHAT_PAY_ASYN_URL = "http://m.upbirds.com/order/wechatASynchroCallBack.html";
	
	//付款价格
	BigDecimal PRICE = new BigDecimal("99.00");
	
	//续单价格
	BigDecimal XUDAN_PRICE = new BigDecimal("99.00");
	
	//无意义，支付商品名称
	String COMM_GOODS_NAME = "大白按需交流";
	
	//无意义，支付商品Id
	String COMM_GOODS_ID = "911";
	
	//消息模板id，见微信公众号，模板消息
	String TEMAPLATE_ID_TO_DABAI_CHOOSE = "w5AwTBdXw2ROCmNySmEb2EH8aZ7CsdTeQY5mTSrF0-4";
	
	//消息模板id，见微信公众号，模板消息
	String TEMPLATE_ID_TO_XIAOBAI_SUCCESS = "fXSSsMHyMI02AktUXZ8fljDeqY9BL5E0rS36i8ZuEK8";
	
	//消息模板id，见微信公众号，模板消息
	String TEMAPLATE_ID_TIPS = "w5AwTBdXw2ROCmNySmEb2EH8aZ7CsdTeQY5mTSrF0-4";
	
	String QUARTZ_CLASS_NAME = "com.finger.birds.utils.quartz.SendTipsJob";
	
	String KEY_CERT_LOCATION = "/usr/1482274422.p12";
	
	Long KEFU_USER_ID = 52L;
	
	//续单时长
	int xudanTime = 30; //25min
}
