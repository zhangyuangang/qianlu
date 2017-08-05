package com.finger.birds.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.po.user.AdminUserPO;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin")
public class AdminHomeController extends BaseController<AdminHomeController>{
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav){
		mav.setViewName("admin/login");
		return mav;
	}
	
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public void login(String username, String password, HttpServletResponse response){
		if (username == null || password == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "账号或密码不能为空"));
		}
		AdminUserPO admin = userService.login(username, password);
		if (admin == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.数据错误.getCode(), "账号或密码错误"));
		}
		super.addSession(LOGIN_KEY_USER_ADMIN, admin);
		AjaxUtils.write(response, new Result<>(ResultStatusCode.成功.getCode(), "登录成功"));
	}
	
	@RequestMapping("/index.html")
	public ModelAndView index(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/index");
		}
		return mav;
	}
	
	@RequestMapping("/arclongt.html")
	public ModelAndView arclongt(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/arclongt");
		}
		return mav;
	}
	
	@RequestMapping("/dbessay.html")
	public ModelAndView dbessay(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/dbessay");
		}
		return mav;
	}

	@RequestMapping("/dbmanage.html")
	public ModelAndView dbmanage(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/dbmanage");
		}
		return mav;
	}
	
	@RequestMapping("/hot.html")
	public ModelAndView hot(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/hot");
		}
		return mav;
	}
	
	@RequestMapping("/lbmanage.html")
	public ModelAndView lbmanage(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/lbmanage");
		}
		return mav;
	}
	
	@RequestMapping("/order.html")
	public ModelAndView order(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/order");
		}
		return mav;
	}
	
	@RequestMapping("/paydata.html")
	public ModelAndView paydata(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/paydata");
		}
		return mav;
	}
	
	@RequestMapping("/plshen.html")
	public ModelAndView plshen(ModelAndView mav){
		if (super.getAdminUser() == null) {
			mav.setViewName("admin/login");
		} else {
			mav.setViewName("admin/plshen");
		}
		return mav;
	}
	
}
