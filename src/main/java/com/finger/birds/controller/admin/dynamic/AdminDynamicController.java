package com.finger.birds.controller.admin.dynamic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finger.birds.db.bean.DynamicBean;
import com.finger.birds.db.bean.DynamicQueryBean;
import com.finger.birds.db.po.dynamic.DynamicListPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.param.dynamic.DynamicQueryParam;
import com.finger.birds.service.dynamic.DynamicService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/dynamic")
public class AdminDynamicController extends BaseController<AdminDynamicController>{

	@Resource
	private DynamicService dynamicService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/addDynamic.html", method = RequestMethod.POST)
	public void addDynamic(@Valid DynamicBean dynamic, BindingResult bindingResult, HttpServletResponse response){
		Long userId = super.getAdminUserId();
		if (dynamic.getCode() == null || dynamic.getContent() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "新增失败"));
		}
		UserPO user = userService.getByCode(dynamic.getCode());
		if (user == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "大白不存在"));
		}
		dynamic.setUserId(user.getId());
		dynamic.setAdminId(userId);
		
		boolean retVal = dynamicService.add(dynamic);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "新增失败"));
		}
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
	
	@RequestMapping(value="/loadingMore.html")
	public void loadingMore(@Valid DynamicQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<DynamicListPO>> rslt = new Result<>();
		DynamicQueryBean bean = param.initBean();
		PageBean<DynamicListPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(dynamicService.getAdminList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/editById.html", method=RequestMethod.POST)
	public void editById(@Valid DynamicQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Long id = param.getId();
		String code = param.getCode();
		if (id == null || code == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "修改失败"));
		}
		UserPO user = userService.getByCode(code);
		if (user == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "大白不存在"));
		}
		
		boolean retVal = dynamicService.edit(id, user.getId());
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "修改失败"));
		}
		AjaxUtils.write(response, new Result<>("修改成功"));
	}
	
	@RequestMapping(value="/deleteById.html", method=RequestMethod.POST)
	public void deleteById(@Valid DynamicQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		if (param.getId() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "删除失败"));
		}
		
		boolean retVal = dynamicService.delete(param.getId());
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "删除失败"));
		}
		AjaxUtils.write(response, new Result<>("删除成功"));
	}
	
}
