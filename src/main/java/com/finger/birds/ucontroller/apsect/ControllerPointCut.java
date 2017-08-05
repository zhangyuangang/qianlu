package com.finger.birds.ucontroller.apsect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.finger.birds.ucontroller.actiontype.ActionType;
import com.finger.birds.ucontroller.exception.auth.AuthorityJSONPException;
import com.finger.birds.ucontroller.exception.auth.AuthorityPageException;
import com.finger.birds.ucontroller.exception.business.BusinessJSONException;
import com.finger.birds.ucontroller.exception.business.BusinessJSONPException;
import com.finger.birds.ucontroller.exception.business.BusinessPageException;
import com.finger.birds.ucontroller.exception.param.ParamException;
import com.finger.birds.ucontroller.exception.param.RequestParamInvalidJSONException;
import com.finger.birds.ucontroller.exception.param.RequestParamInvalidJSONPException;
import com.finger.birds.ucontroller.exception.param.RequestParamInvalidPageException;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.exception.auth.AuthorityException;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.param.Param;
import com.finger.birds.utils.rslt.code.ResultStatusCode;


@Component
@Aspect
public class ControllerPointCut {

	private final Logger log = Logger.getLogger(this.getClass());
	
	@Pointcut("execution(public * com.finger.birds.controller..*.*(..))")
	public void controller() {
	}

	@Before("controller()")
	public void init(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object[] obs = jp.getArgs();
		Integer actionType = ActionType.DEFAULT.getVal();
		
		{//deal param
			Param param = this.getParam(jp);
			actionType = this.getUserActionType(param, request);
			this.setActionType(param, actionType);
			log.info("=== uri === "+ request.getRequestURI());
			log.info("=== param info === " + param);
		}
		
		for (Object ob : obs) {
			if (ob instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) ob;
				if (bindingResult.hasErrors()) {
					if (actionType == ActionType.JSON.getVal()) {
						throw new RequestParamInvalidJSONException(bindingResult.getFieldError());
					}
					if (actionType == ActionType.PAGE.getVal()) {
						throw new RequestParamInvalidPageException(bindingResult.getFieldError());
					}
					if(actionType == ActionType.JSONP.getVal()){
						throw new RequestParamInvalidJSONPException(bindingResult.getFieldError());
					}
				}
			}
		}
		//set the url 
		request.setAttribute("IMG_URL",CommConstant.IMG_URL);
	}

	@AfterThrowing(pointcut = "controller()", throwing="e")
	public void excption(JoinPoint jp, Exception e){
		e.printStackTrace();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Param param = this.getParam(jp);
		Integer actionType = this.getUserActionType(param, request);
		//request.setAttribute("param", param);
		request.setAttribute("err", e.getMessage());	
		request.setAttribute("uri", request.getRequestURI());
//		request.setAttribute("domain", SysConfig.getConfig(SysEnum.DOMAIN));
		if(e instanceof ParamException){
			log.info("=== param err === " + e.getMessage());
			request.setAttribute("code", ResultStatusCode.请求参数错误.getCode());
			if(actionType.equals(ActionType.JSON.getVal())){
				throw new RequestParamInvalidJSONException(e.getMessage());
			} else if(actionType.equals(ActionType.PAGE.getVal())){
				throw new RequestParamInvalidPageException(e.getMessage());
			}else{
				throw new RequestParamInvalidJSONPException(e.getMessage());
			}
		} else if(e instanceof BusinessException){
			log.info("=== business err ===" + e.getMessage());
			request.setAttribute("data", ((BusinessException) e).getData());
			request.setAttribute("code", ResultStatusCode.数据错误.getCode());
			if(actionType.equals(ActionType.JSON.getVal())){
				throw new BusinessJSONException(e.getMessage());
			} else if(actionType.equals(ActionType.PAGE.getVal())){
				throw new BusinessPageException(e.getMessage());
			}else{
				throw new BusinessJSONPException(e.getMessage());
			}
		} else if(e instanceof AuthorityException){
			log.info("=== Auth err ===" + e.getMessage());
			request.setAttribute("code", ResultStatusCode.权限不足.getCode());
			if(actionType.equals(ActionType.JSON.getVal())){
				throw new BusinessJSONException(e.getMessage());
			} else if(actionType.equals(ActionType.PAGE.getVal())){
				throw new AuthorityPageException(e.getMessage());
			}else{
				throw new AuthorityJSONPException(e.getMessage());
			}
		} else {
			log.error("=== system err ===");
			log.error("", e);
			request.setAttribute("err", "系统错误!");
			request.setAttribute("code", ResultStatusCode.系统错误.getCode());
		}
	}
	
	/**
	 * To judge the user is set action type.
	 * 
	 * @param obs
	 * @return
	 */
	private Integer getUserActionType(Param param, HttpServletRequest request) {
		Integer actionType = ActionType.DEFAULT.getVal();
		if(param != null){
			actionType = param.getActionType() == null ? ActionType.DEFAULT.getVal() : param.getActionType();
		}
		// Judge the action type by the request head.
		if (actionType == ActionType.DEFAULT.getVal()) {
			if (isAjaxRequest(request)) {
				actionType = ActionType.JSON.getVal();
			} else {
				actionType = ActionType.PAGE.getVal();
			}
		}
		return actionType;
	}
	
	/**
	 * Set actionType in param
	 * @param jp
	 * @param actionType
	 */
	private void setActionType(Param param, Integer actionType){
		if(param != null){
			param.setActionType(actionType);
		}
	}

	/**
	 * Whether by the request head of the actionType;
	 * @param request
	 * @return
	 */
	private Boolean isAjaxRequest(HttpServletRequest request) {
		Boolean flag = Boolean.FALSE;
		Enumeration<String> e = request.getHeaderNames();
		
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			if (("x-requested-with").equals(name.toLowerCase())) {
				if(request.getHeader("x-requested-with") != null){
					flag = true;
					break;
				}	
			}
		}
		return flag;
	}
	
	/**
	 * 在参数中获取 Param
	 * @param jp
	 * @return
	 */
	private Param getParam(JoinPoint jp){
		Object[] obs = jp.getArgs();
		
		for (Object ob : obs) {
			if(ob instanceof Param){
				return ((Param)ob);
			}
		}
		return null;
	}
}
