package com.finger.birds.controller.hotdis;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.bean.HotDisMsgBean;
import com.finger.birds.db.bean.HotDisMsgQueryBean;
import com.finger.birds.db.bean.HotDisQueryBean;
import com.finger.birds.db.po.hotdis.HotDisListPO;
import com.finger.birds.db.po.hotdis.HotDisMsgListPO;
import com.finger.birds.param.hotdis.HotDisMsgAddParam;
import com.finger.birds.param.hotdis.HotDisMsgQueryParam;
import com.finger.birds.param.hotdis.HotDisQueryParam;
import com.finger.birds.service.hotdis.HotDisService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/hotdis")
public class HotDisController extends BaseController<HotDisController>{
	
	@Resource
	private HotDisService hotDisService;
	
	@RequestMapping("/toHotDisList.html")
	public ModelAndView toHotDisList(ModelAndView mav){
		mav.setViewName("Upsurge");
		return mav;
	}
	
	@RequestMapping(value="/getList.html")
	public void getList(@Valid HotDisQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getUser();
		Result<List<HotDisListPO>> rslt = new Result<>();
		HotDisQueryBean bean = param.initBean();
		rslt.initRslt(hotDisService.getList(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/getById.html")
	public ModelAndView getById(ModelAndView mav, Long id){
		super.getUser();
		mav.addObject("hotDis", hotDisService.getById(id));
		mav.setViewName("/Upsurge/Ghat");
		return mav;
	}
	
	@RequestMapping("/getNextPrev.html")
	public void getNextPrev(HttpServletResponse response, int op, Long id){
		Result<Long> rslt = new Result<>();
		rslt.setData(hotDisService.getNextPrev(op, id));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping("/getMsgList.html")
	public void getMsgList(@Valid HotDisMsgQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getUser();
		Result<List<HotDisMsgListPO>> rslt = new Result<>();
		HotDisMsgQueryBean bean = param.initBean();
		rslt.initRslt(hotDisService.getMsgList(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/addMsg.html", method=RequestMethod.POST)
	public void addMsg(@Valid HotDisMsgAddParam param, BindingResult bindingResult, HttpServletResponse response){
		Long userId = super.getUserId();
		Result<List<HotDisMsgListPO>> rslt = new Result<>();
		HotDisMsgBean bean = param.initBean();
		bean.setUserId(userId);
		hotDisService.addMsg(bean);
		AjaxUtils.write(response, rslt);
	}
}
