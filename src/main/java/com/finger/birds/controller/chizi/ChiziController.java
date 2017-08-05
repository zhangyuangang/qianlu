package com.finger.birds.controller.chizi;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.param.IdParam;
import com.finger.birds.service.chizi.ChiziService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/chizi")
public class ChiziController extends BaseController<ChiziController>{

	@Resource
	private ChiziService chiziService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/toChiziDetail.html")
	public ModelAndView toChiziDetail(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		Long userId = super.getUserId();
		mav.addObject("data", chiziService.getById(userId, param.getId()));
		mav.addObject("msgPo", userService.getMsgPO(userId));
		mav.setViewName("chizianli");
		return mav;
	}
	
	@RequestMapping("/care.html")
	public void care(@Valid IdParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<Integer> rslt = new Result<>();
		rslt.setData(chiziService.care(super.getUserId(), param.getId()));
		AjaxUtils.write(response, rslt);
	}
}
