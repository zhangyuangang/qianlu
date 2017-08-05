package com.finger.birds.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.bean.UserInfoBean;
import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.po.msg.QianluMsgListPO;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.model.UserTypeEnum;
import com.finger.birds.model.msg.QianluMsg;
import com.finger.birds.param.user.UserAddParam;
import com.finger.birds.param.user.UserInfoParam;
import com.finger.birds.param.user.UserMsgListQueryParam;
import com.finger.birds.param.user.UserQueryParam;
import com.finger.birds.service.keyvalue.KeyvalueService;
import com.finger.birds.service.msg.QianluMsgService;
import com.finger.birds.service.user.UserIncomeService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.service.withdraw.WithdrawService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserController>{

	@Resource
	private UserService userService;
	
	@Resource
	private UserIncomeService userIncomeService;
	
	@Resource
	private QianluMsgService qianluMsgService;
	
	@Resource
	private KeyvalueService keyvalueService;
	
	@Resource
	private WithdrawService withdrawService;
	
	@RequestMapping(value = "/toUserInfo.html", method = RequestMethod.GET)
	public ModelAndView toUserInfo(ModelAndView mav){
		UserPO upo = super.getUser();
		if(upo.getUserType() == UserTypeEnum.大白.getVal()){
			mav.setViewName("person/pbigbbj");
		} else {
			mav.setViewName("person/psmallbbj");
		}
		return mav;
	}
	
	//2 编辑个人资料
	@RequestMapping(value = "/updateUserInfo.html", method=RequestMethod.POST)
	public void updateUserInfo(@Valid UserInfoParam param, BindingResult bindingResult, HttpServletResponse response) {
		UserPO upo = super.getUser();
		UserInfoBean bean = param.initBean();
		bean.setUserId(upo.getId());
		bean.setId(upo.getId());
		Result<String> rslt = new Result<>();
		super.addSession(LOGIN_KEY_USER, userService.updateInfo(bean));
//		mav.setViewName("redirect:/user/toUserCenter.html?tUserId="+upo.getId());
		AjaxUtils.write(response, rslt);
	}
	
	
	// 1
	@RequestMapping(value="/register.html")
	public ModelAndView register(@Valid UserAddParam param, BindingResult bindingResult, ModelAndView mav){
		UserPO upo = (UserPO) this.getFromSession(LOGIN_KEY_USER);
		if(upo != null && upo.getId() != null){
			String t = "小白";
			if(upo.getUserType() == 1){
				t = "大白";
			}
			throw new BusinessException("你已经成为" + t + ",需要转换身份请联系前路小助手");
		}
		upo = userService.register(param.initBean());
		super.addSession(LOGIN_KEY_USER, upo);
		mav.setViewName("redirect:/home.html");
		return mav;
	}
	
	//Just for test
	@RequestMapping(value="/toRegister.html")
	public ModelAndView toRegister(ModelAndView mav, String unionid, String nickname){
		HashMap<String, Object> user = new HashMap<>();
		user.put("unionid", unionid);
		user.put("nickname", nickname);
		mav.addObject("user", user);
		mav.setViewName("register");
		return mav;
	}
	
	//3 过来人请求数据列表
	@RequestMapping(value="/getUserList.html")
	public void getUserList(@Valid UserQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getUser();
		Result<List<UserCenterPO>> rslt = new Result<>();
		UserQueryBean bean = param.initBean();
		rslt.initRslt(userService.queryOBForPage(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/toUserCenter.html")
	public ModelAndView toUserCenter(ModelAndView mav, Long userId, Integer payStatus, String msg){
		UserPO user = null;
		UserPO self = super.getUser();
		mav.addObject("payStatus", 0);
		mav.addObject("userExtPo", userService.getUserExtPO(self.getId()));
		if(payStatus != null && msg != null){
			mav.addObject("payStatus", payStatus);
			mav.addObject("msg", msg);
		}
		if(userId == null || self.getId().equals(userId)){
			user = self;
			if(user.getUserType() == UserTypeEnum.小白.getVal()){
				mav.setViewName("person/psmallb");
			} else {
				mav.setViewName("person/pbigb");
			}
		} else {
			user = userService.getById(userId);
			if(user == null || user.getId() == null){
				throw new BusinessException("用户不存在");
			}
			userService.care(self.getId(), userId);
			Short selfUserType = self.getUserType();
			Short tUserType = user.getUserType();
			if(selfUserType == 1 && tUserType == 1){
				mav.setViewName("person/psmallbtob");
			} else if(selfUserType == 2 && tUserType == 1){
				mav.setViewName("person/psmallbtob");
			} else if(selfUserType == 3 && tUserType == 1){
				mav.setViewName("person/pbigbtob");
			} else {
				mav.setViewName("person/psmallbtos");
			}
		}
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("/toUserList.html")
	public ModelAndView toUserList(ModelAndView mav){
		mav.setViewName("allstar");
		return mav;
	}
	
	@RequestMapping("/toChatList.html")
	public ModelAndView toChatList(ModelAndView mav){
		super.getUser();
		mav.setViewName("Chatlist");
		return mav;
	}
	
	@RequestMapping("/getUserForType.html")
	public void getUserForType(HttpServletResponse response){
		super.getUser();
		Result<List<Map<String, Object>>> rslt = new Result<>();
		rslt.setData(userService.getUserForType());
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/getUserMsgList.html")
	public void getUserMsgList(@Valid UserMsgListQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		Long userId = super.getUserId();
		Result<List<QianluMsgListPO>> rslt = new Result<>();
		rslt.initRslt(userService.getUserMsgList(userId, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/checkSendMsg.html")
	public void checkSendMsg(HttpServletResponse response, Long tuserId){
		Long userId = super.getUserId();
		Result<String> rslt = new Result<>();
		userService.checkSendMsg(userId, tuserId);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/sendMsg.html")
	public void sendMsg(HttpServletResponse response, String msg, Long id){
		super.getUser();
		Result<String> rslt = new Result<>();
		qianluMsgService.sendMsg(id, msg);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/createChat.html")
	public ModelAndView createChat(ModelAndView mav, Long tuserId){
		Long userId = super.getUserId();
		QianluMsg msg = userService.checkExist(userId, tuserId);
		mav.setViewName("redirect:/chat.html?selfId=" + userId + "&tId=" + msg.getTuserId() + "&id=" + msg.getId());
		return mav;
	}
	
	@RequestMapping("/withdraw.html")
	public void withdraw(HttpServletResponse response){
		Long userId = super.getUserId();
		Result<String> rslt = new Result<>();
		withdrawService.add(userId);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/getQuestionByMsgId.html")
	public void getQuestionByMsgId(@Valid Long selfId, @Valid Long tuserId, HttpServletResponse response){
		Map<String, Object> question = new HashMap<>();
		question = keyvalueService.getQuestionByMsgId(selfId, tuserId);
		AjaxUtils.write(response, question);
	}
	
	@RequestMapping(value="/searchByKeywords.html")
	public void searchByKeywords(@Valid String companyName, @Valid Integer occType, @Valid String tradeName, HttpServletResponse response){
		Long userId = super.getUserId();
		List<UserCenterPO> list = userService.searchByKeywords(userId, companyName, occType, tradeName);
		Map<String, Object> map = new HashMap<>();
		map.put("date", list);
		AjaxUtils.write(response, map);
	}
	
	@RequestMapping("/shareWX.html")
	public void shareWX(@Valid Long toId, @Valid String type, HttpServletResponse response){
		Long userId = super.getUserId();
		if(toId == null || type == null){
			AjaxUtils.write(response, new Result<>("失败"));
		}
		userService.shareWX(userId, toId, type);
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
}
