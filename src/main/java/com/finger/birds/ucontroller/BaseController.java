package com.finger.birds.ucontroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.finger.birds.db.po.user.AdminUserPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.exception.param.ParamException;
import com.finger.birds.ucontroller.ip.RemoteIPUtil;
import com.finger.birds.utils.exception.auth.AuthorityException;
import com.finger.birds.utils.exception.business.BusinessException;

public class BaseController<T> {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public final static String LOGIN_KEY = "BIRDS";

	public final static String LOGIN_KEY_ADMIN = "BIRDS_ADMIN";
	
	public final static String LOGIN_KEY_USER = "BIRDS_USER";
	
	public final static String LOGIN_KEY_USER_ADMIN = "BIRDS_USER_ADMIN";
	
	@Resource
	private UserService userService;
	
	/**
	 * 添加session
	 * @param key
	 * @param obj
	 */
	protected void addSession(String key, Object obj) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(key, obj);
	}
	
	/**
	 * 获取session值
	 * @param key
	 * @return
	 */
	protected Object getFromSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return session.getAttribute(key);
	}
	/**
	 * 移除session
	 * @param key
	 */
	protected void removeSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute(key);
	}
	/**
	 * 获取请求ip
	 */
	protected String getRemoteIp(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = RemoteIPUtil.getRemortIP(request);
		return ip;
	}
	
	protected boolean checkToken(String key, String clientToken){
		String serverToken = this.getFromSession(key) + "";
		if(StringUtils.isEmpty(serverToken) || StringUtils.isEmpty(clientToken)){
			return false;
		}
		if(serverToken.equals(clientToken)){
			return true;
		}
		return false;
	}
	
	protected boolean checkTokenExpire(String key, long time){
		String val = this.getFromSession(key) + "";
		try{
			if(System.currentTimeMillis() - Long.parseLong(val) > time){
				return false;
			}
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	protected Long checkUser(Long userId){
		if(userId == null){
			Long suid = (Long)this.getFromSession(LOGIN_KEY);
			if(suid != null && suid > 0){
				return suid;
			} else {
				throw new ParamException("用户未登陆");
			}
		}
		return userId;
	}
	
	/**
	 * 
	 * @param isCheckLogin 是否检查登陆，如果没有去到
	 * @return
	 */
	protected UserPO getUser(){
		UserPO upo = (UserPO) this.getFromSession(LOGIN_KEY_USER);
		if(upo == null || upo.getId() == null){
//			upo = forTest();
//			this.addSession(LOGIN_KEY_USER, upo);
//			return upo;
			throw new AuthorityException("未登录");
		}
		return upo;
	}
	
	protected Long getUserId(){
		Long id = null;
		try{
			id = this.getUser().getId();
		} catch(AuthorityException e){
			throw e;
		}
		return id;
	}
	
	/**
	 * @param isCheckLogin 是否检查登陆，如果没有去到
	 * @return
	 */
	protected AdminUserPO getAdminUser(){
		AdminUserPO admin = (AdminUserPO) this.getFromSession(LOGIN_KEY_USER_ADMIN);
		if(admin == null || admin.getId() == null){
			throw new BusinessException("<h1><a target=\"_top\" href=\"http://m.upbirds.com/admin/login.html\">去登录</a></h1>");
		}
		return admin;
	}
	
	protected Long getAdminUserId(){
		Long id = null;
		try {
			id = this.getAdminUser().getId();
		} catch (Exception e) {
			throw new BusinessException("<h1><a target=\"_top\" href=\"http://m.upbirds.com/admin/login.html\">去登录</a></h1>");
		}
		return id;
	}
	
	private UserPO forTest(){
		UserPO upo = userService.getUserByThirdKey("oCiL107dTXoNCNzWDjfBeHQWOqgE");
		//oVKMFxPlcImP82uT2qz5d3BMVVGw
		//oVKMFxOzrQdNhF4s1nitTIkorcqI
		return upo;
	}
}
