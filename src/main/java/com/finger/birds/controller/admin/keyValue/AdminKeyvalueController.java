package com.finger.birds.controller.admin.keyValue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finger.birds.db.po.keyvalue.KeyvalueListPO;
import com.finger.birds.service.keyvalue.KeyvalueService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/keyvalue")
public class AdminKeyvalueController extends BaseController<AdminKeyvalueController>{

	@Resource
	private KeyvalueService keyvalueService;
	
	@RequestMapping(value="/addProverbs.html", method=RequestMethod.POST)
	public void addProverbs(@Valid String value, HttpServletResponse response){
		super.getAdminUser();
		if (value == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "新增失败"));
		}
		
		boolean retVal = keyvalueService.addProverbs(value);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "新增失败"));
		}
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
	
	@RequestMapping(value="/getTopProverbs.html")
	public void getTopProverbs(HttpServletResponse response){
		super.getAdminUser();
		KeyvalueListPO proverbs = keyvalueService.getTopProverbs();
		AjaxUtils.write(response, proverbs);
	}
	
}
