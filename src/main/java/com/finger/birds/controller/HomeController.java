package com.finger.birds.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.po.intelligent.QianluPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.param.user.WechatGrantParam;
import com.finger.birds.service.chizi.ChiziService;
import com.finger.birds.service.msg.QianluMsgService;
import com.finger.birds.service.project.ProjectService;
import com.finger.birds.service.project.ProjectSumupService;
import com.finger.birds.service.qianlu.QianluService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.http.HttpClientUtil;
import com.finger.birds.utils.rslt.type.Result;
import com.finger.birds.utils.security.SHA1;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
public class HomeController extends BaseController<HomeController>{
	
	@Resource
	private UserService userService;
	
	@Resource
	private HttpClientUtil httpClientUtil;
	
	@Resource
	private ChiziService chiziService;
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ProjectSumupService projectSumupService;
	
	@Resource
	private QianluMsgService qianluMsgService;
	
	@Resource
	private QianluService qianluService;
	
	@RequestMapping(value="/home.html", method=RequestMethod.GET)
	public ModelAndView home(ModelAndView mav){
		Long userId = super.getUserId();
		mav.addObject("msgPo", userService.getMsgPO(userId));
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/chat.html", method = RequestMethod.GET)
	public ModelAndView chat(ModelAndView mav, String selfId, String tId, Long id){
		if(StringUtils.isEmpty(selfId)){
			throw new BusinessException("请先登录");
		}
		Long userId = super.getUserId();
		if(!selfId.equals(userId + "")){
			throw new BusinessException("非法进入他人聊天界面");
		}
		mav.addObject("selfId", selfId);
		mav.addObject("tId", tId);
		mav.addObject("password", "upbirds");
		mav.addObject("id", id);
		mav.setViewName("wechat");
		{//获取头像
			UserPO self = userService.getById(Long.parseLong(selfId));
			UserPO target = userService.getById(Long.parseLong(tId));
			String selfHeadImage = self.getUserInfo().getHeadImage();
			String tHeadImage = target.getUserInfo().getHeadImage();
			mav.addObject("selfHeadImage", StringUtils.isEmpty(selfHeadImage) ? CommConstant.IMG_URL + CommConstant.USER_HEADIMG_DEFAULT : selfHeadImage);
			mav.addObject("tHeadImage", StringUtils.isEmpty(tHeadImage) ? CommConstant.IMG_URL + CommConstant.USER_HEADIMG_DEFAULT : tHeadImage);
			mav.addObject("tName", target.getUserInfo().getNickname());
			mav.addObject("selfName", self.getUserInfo().getNickname());
		}
		{
//			BirdsMsg msg = new BirdsMsg(BirdsMsgTypeEnum.有消息发送.getVal(), Long.parseLong(selfId), null);
//			userService.readMsg(msg);
			qianluMsgService.read(id);
		}
		return mav;
	}
	
	@RequestMapping(value = "/fack.html", method = RequestMethod.GET)
	public ModelAndView fack(ModelAndView mav, String url, String data, String err, String toUrl){
		Long userId = super.getUserId();
		if(data != null){
			mav.addObject("data", data);
		}
		if(err != null){
			mav.addObject("err", err);
		}
		mav.addObject("msgPo", userService.getMsgPO(userId));
		mav.addObject("toUrl", toUrl);
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping(value = "/fack2.html", method = RequestMethod.GET)
	public ModelAndView fack2(ModelAndView mav, String url, String data, String err, String toUrl){
		if(data != null){
			mav.addObject("data", data);
		}
		if(err != null){
			mav.addObject("err", err);
		}
		mav.addObject("toUrl", toUrl);
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("/getWeChatSign.html")
	public void sign(HttpServletResponse response, String timestamp, String url, String noncestr){
		HttpClientUtil hc = new HttpClientUtil();
		String asstStr = hc.httpGetData("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + CommConstant.APPID + "&secret=" + CommConstant.APPSCRECT);
		JSONObject json = JSONObject.fromObject(asstStr);
		String asst = null;
		try{
			asst = json.getString("access_token");
		} catch(Exception e){
			log.info(json);
			log.error(e);
		}
		if(StringUtils.isNotEmpty(asst)){
			String jsapiStr = hc.httpGetData("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + asst + "&type=jsapi");
			JSONObject jaJson = JSONObject.fromObject(jsapiStr);
			String jsapiTicket = jaJson.getString("ticket");
System.out.println(jsapiTicket);
			if(StringUtils.isNotEmpty(jsapiTicket)){
				String shaStr = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
System.out.println(shaStr);				
				Result<String> rslt = new Result<String>();
				rslt.setData(SHA1.encode(shaStr));
				AjaxUtils.write(response, rslt);
			} else {
				throw new BusinessException("微信生成jsapi_ticket失败");
			}
		}else {
			throw new BusinessException("微信获取access_token失败");
		}
	}
	
	@RequestMapping("/wechat.html")
	public ModelAndView wechat(String url, ModelAndView mav){
		mav.setViewName("wechat");
		return mav;
	}
	
	@RequestMapping("/wechatA.html")
	public ModelAndView wechatA(String url, ModelAndView mav){
		mav.addObject("url", url);
		mav.setViewName("wechatA");
		return mav;
	}
	
	@RequestMapping("/wechatRedirectCenter.html")
	public ModelAndView wechatRedirectCenter(@Valid WechatGrantParam param, BindingResult bindingResult, ModelAndView mav){
		String code = param.getCode();
		if(StringUtils.isEmpty(code)){
			mav.setViewName("redirect:" + param.getPre() + "#wechat_redirect");
		} else {
			String userStr = this.getUserInfoStr(code);
			JSONObject userJson = JSONObject.fromObject(userStr);
			UserPO upo = null;
			try{//TODO 目前接口没有uuionid字段
				upo = userService.getUserByThirdKey(userJson.getString("openid"));
			} catch(BusinessException e){
				//Do nothing
			}
			if(upo != null && upo.getId() != null){
				super.addSession(LOGIN_KEY_USER, upo);
				mav.setViewName("redirect:"+param.getPre());
			} else {
				mav.addObject("user", this.userNeed(userJson));
				mav.setViewName("register");
			}
		}
		return mav;
	}
	
	private Map<String, Object> userNeed(JSONObject userJson){
		Map<String, Object> map = new HashMap<>();
		map.put("nickname", userJson.getString("nickname"));
		map.put("headImgUrl", userJson.getString("headimgurl"));
		map.put("unionid", userJson.getString("openid"));//TODO 目前接口没有uuionid字段
		map.put("sex", userJson.getInt("sex"));
		return map;
	}
	
	
	private String getUserInfoStr(String code){
		String url = CommConstant.APP_TOKEN_URL + "?appid=" + CommConstant.APPID + "&secret=" + CommConstant.APPSCRECT + "&code=" + code + "&grant_type=authorization_code";
		String rsltStr = httpClientUtil.httpGetData(url);
		JSONObject rsltJson = JSONObject.fromObject(rsltStr);
		try{
			String openid = rsltJson.getString("openid");
			super.addSession(CommConstant.WECHAT_OPENID, openid);			
			String assToken = rsltJson.getString("access_token");
System.out.println(assToken);
			System.out.println(openid);
			String getUUIDUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + assToken + "&openid=" + openid + "&lang=zh_CN";
			String uuidStr = httpClientUtil.httpGetData(getUUIDUrl);
System.out.println(uuidStr);
			rsltJson = JSONObject.fromObject(uuidStr);//TODO 目前接口没有uuionid字段
			rsltJson.getString("openid");
			return uuidStr;
		} catch(JSONException e){
			log.error("wechat failed,code:" + rsltJson.getString("errcode") + "msg:" + rsltJson.getString("errmsg"));
			throw new BusinessException(rsltJson.toString());
		}
	}
	
	@RequestMapping(value="/loadingMessage.html")
	public void loadingMessage(HttpServletResponse response){
		Long userId = super.getUserId();
		List<QianluPO> list = qianluService.getMessage(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", list);
		AjaxUtils.write(response, map);
	}
	
	@RequestMapping(value="/readMessage.html")
	public void readMessage(@Valid Long id, HttpServletResponse response){
		Long userId = super.getUserId();
		if(id/100 != userId){
			throw new BusinessException("您不能读取别人的消息");
		}
		qianluService.readMessage(id);
		AjaxUtils.write(response, new Result<>("成功"));	}
	
}
