package com.finger.birds.utils.template;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.http.HttpClientUtil;

import net.sf.json.JSONObject;

@Component("wechatTemplateUtils")
public class WechatTemplateUtils {

	@Resource
	private HttpClientUtil httpClientUtil;

	private Logger log = Logger.getLogger(this.getClass());

	private final static WechatTemplateUtils wechatTemplateUtils = new WechatTemplateUtils();
	
	public static WechatTemplateUtils getInstance(){//恶汉单例，仅仅为SendTipsJob以防创建太多对象
		return wechatTemplateUtils;
	}
	
	public String getAssToken(HttpSession session) {
		String assToken = null;
		if(session != null){
			assToken = (String) session.getAttribute("asstkn");
		}
		if (assToken != null) {
			String[] arr = assToken.split(CommConstant.SPLIT);
			Long time = Long.parseLong(arr[1]);
			if ((System.currentTimeMillis() - time) / 1000 < 7200) {
				return arr[0];
			} else {
				return createAssToken(session);
			}
		} else {
			return createAssToken(session);
		}
	}

	private String createAssToken(HttpSession session) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + CommConstant.APPID
				+ "&secret=" + CommConstant.APPSCRECT;
		String assToken = null;
		try {
			String rslt = httpClientUtil.httpGetData(url);
			log.info(rslt);
			JSONObject json = JSONObject.fromObject(rslt);
			assToken = json.getString("access_token");
			if(session == null){
				return assToken;
			}
			String assTokenStr = (String) session.getAttribute("asstkn");
			if (assTokenStr != null && assTokenStr.contains(assToken)) {
			} else {// 更新session
				String sessionStr = assToken + CommConstant.SPLIT + System.currentTimeMillis();
				session.setAttribute("asstkn", sessionStr);
			}
		} catch (Exception e) {
			log.error("获取asstoken失败");
			log.error(e);
		}
		return assToken;
	}

	public String sendMsg(String templateId, JSONObject data, String url, String openid, HttpSession session) {
		//TODO 
		if(httpClientUtil == null){
			httpClientUtil = HttpClientUtil.getInstance();
		}
		String reqUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + this.getAssToken(session);
		TemplateData postData = new TemplateData();
		postData.setTouser(openid);
		postData.setUrl(url);
		postData.setData(data);
		postData.setTemplate_id(templateId);
		String str = httpClientUtil.httpPostJSONData(reqUrl, JSONObject.fromObject(postData), "utf-8");
		log.info(str);
		return str;
	}

	public static void main(String[] args) throws InterruptedException {
////		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + CommConstant.APPID
////				+ "&secret=" + CommConstant.APPSCRECT;
////		HttpClientUtil httpClientUtil = new HttpClientUtil();
////		String rslt = httpClientUtil.httpGetData(url);
////		JSONObject json = JSONObject.fromObject(rslt);
////System.err.println(json);
////		String assToken = json.getString("access_token");
////		String reqUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + assToken;
//		TemplateData postData = new TemplateData();
//		postData.setTouser("oCiL107dTXoNCNzWDjfBeHQWOqgE");
//		postData.setUrl("");
//		String dataStr = "{\"first\":\"尊敬的" + "stra" + "\", \"OrderSn\":\"" + 1223 + "\", \"OrderStatus\":\"小白已支付\", \"remark\":\"请您尽快选中与小白交流的时段！\"}";
//		JSONObject data = JSONObject.fromObject(dataStr);
//		postData.setData(data);
//		postData.setTemplate_id(CommConstant.TEMAPLATE_ID_TO_DABAI_CHOOSE);
//		JSONObject.fromObject(postData);
////		String str = httpClientUtil.httpPostJSONData(reqUrl, JSONObject.fromObject(postData), "utf-8");
////System.err.println(str);
		
//		System.out.println("【系统启动】开始(每1秒输出一次)...");  
//		QuartzManager.addJob("job_name", "com.finger.birds.utils.quartz.SendTipsJob", "0/1 * * * * ?");  
//		//QuartzManager.addJob(job_name, job, "0 0/3 8-20 ? ? *");  
//		  
//		Thread.sleep(5000);
//		System.out.println("【移除定时】开始...");
//		QuartzManager.removeJob("job_name");
//		System.out.println("【移除定时】成功");
//		Thread.sleep(10000);
//		System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
//		QuartzManager.addJob("job_name", "com.finger.birds.utils.quartz.SendTipsJob", "*/10 * * * * ?");  
//		Thread.sleep(60000);
//		System.out.println("【移除定时】开始...");
//		QuartzManager.removeJob("job_name");
//		System.out.println("【移除定时】成功");
	}
	
}
