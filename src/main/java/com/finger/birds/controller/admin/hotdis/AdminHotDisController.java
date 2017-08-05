package com.finger.birds.controller.admin.hotdis;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finger.birds.db.bean.HotDisBean;
import com.finger.birds.db.bean.HotDisMsgQueryBean;
import com.finger.birds.db.bean.HotDisQueryBean;
import com.finger.birds.db.po.hotdis.HotDisListPO;
import com.finger.birds.db.po.hotdis.HotDisMsgListPO;
import com.finger.birds.param.hotdis.HotDisMsgQueryParam;
import com.finger.birds.param.hotdis.HotDisQueryParam;
import com.finger.birds.service.hotdis.HotDisService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/hotdis")
public class AdminHotDisController extends BaseController<AdminHotDisController>{
	
	@Resource
	private HotDisService hotDisService;
	
	@RequestMapping(value="/addHotdis.html", method=RequestMethod.POST)
	public void addMsg(@Valid HotDisBean hotdis, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		if (hotdis.getTitle() == null || hotdis.getContent() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "新增失败"));
		}
		boolean retVal = hotDisService.add(hotdis);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "新增失败"));
		}
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
	
	@RequestMapping(value="/loadingMore.html")
	public void loadingMore(@Valid HotDisQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<HotDisListPO>> rslt = new Result<>();
		HotDisQueryBean bean = param.initBean();
		PageBean<HotDisListPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(hotDisService.getList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/deleteById.html", method=RequestMethod.POST)
	public void deleteById(@Valid HotDisQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		if (param.getId() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "删除失败"));
		}
		
		boolean retVal = hotDisService.delete(param.getId());
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "删除失败"));
		}
		AjaxUtils.write(response, new Result<>("删除成功"));
	}
	
	@RequestMapping(value="/getCommentList.html")
	public void getCommentList(@Valid HotDisQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<HotDisListPO>> rslt = new Result<>();
		HotDisQueryBean bean = param.initBean();
		rslt.initRslt(hotDisService.getCommentList(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getCommentDetilsList.html")
	public void getCommentDetilsList(@Valid HotDisMsgQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<HotDisMsgListPO>> rslt = new Result<>();
		HotDisMsgQueryBean bean = param.initBean();
		PageBean<HotDisMsgListPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(20);
		}
		rslt.initRslt(hotDisService.getMsgList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/deleteCommentById.html", method=RequestMethod.POST)
	public void deleteCommentById(@Valid HotDisQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		if (param.getId() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "删除失败"));
		}
		boolean retVal = hotDisService.deleteCommentById(param.getId());
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "删除失败"));
		}
		AjaxUtils.write(response, new Result<>("删除成功"));
	}
}
