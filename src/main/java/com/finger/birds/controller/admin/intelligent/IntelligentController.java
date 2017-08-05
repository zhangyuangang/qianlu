package com.finger.birds.controller.admin.intelligent;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finger.birds.db.po.intelligent.WeightPO;
import com.finger.birds.model.intelligent.Weight;
import com.finger.birds.service.intelligent.IntelligentService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/intelligent")
public class IntelligentController extends BaseController<IntelligentController>{

	@Resource
	private IntelligentService intelligentService;
	
	@RequestMapping(value="/addWeight.html")
	public void getList(@Valid Weight bean, HttpServletResponse response){
		super.getAdminUser();
		if(bean.getToId() == null || bean.getToTable() == null){
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "请求参数错误"));
		}
		Boolean isVal = intelligentService.addWeight(bean);
		if(!isVal){
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "未知错误"));
		}
		AjaxUtils.write(response, new Result<>(ResultStatusCode.成功.getCode(), "成功"));
	}
	
	@RequestMapping(value="/getByIdTable.html")
	public void getByIdTable(@Valid Long toId, @Valid String toTable, HttpServletResponse response){
		super.getAdminUser();
		if(toId == null || toTable == null){
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "请求参数错误"));
		}
		WeightPO weight = intelligentService.getByIdTable(toId, toTable);
		AjaxUtils.write(response, weight);
	}
	
}
