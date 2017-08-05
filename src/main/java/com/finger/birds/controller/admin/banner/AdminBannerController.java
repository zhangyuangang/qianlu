package com.finger.birds.controller.admin.banner;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finger.birds.db.po.banner.BannerListPO;
import com.finger.birds.service.banner.BannerService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/banner")
public class AdminBannerController extends BaseController<AdminBannerController>{

	@Resource
	private BannerService bannerService;
	
	@RequestMapping(value="/addBanner.html", method=RequestMethod.POST)
	public void addProverbs(@Valid String cover, @Valid String detils, HttpServletResponse response){
		super.getAdminUser();
		if (cover == null || detils == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "新增失败"));
		}
		
		boolean retVal = bannerService.addBanner(cover, detils);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "新增失败"));
		}
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
	
	@RequestMapping(value="/getBannerList.html")
	public void getBannerList(HttpServletResponse response){
		Result<List<BannerListPO>> rslt = new Result<>();
		List<BannerListPO> list = bannerService.getList();
		rslt.setData(list);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/deleteById.html")
	public void deleteById(@Valid Long id, HttpServletResponse response){
		super.getAdminUser();
		if (id == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "新增失败"));
		}
		boolean retVal = bannerService.deleteById(id);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "新增失败"));
		}
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
}
