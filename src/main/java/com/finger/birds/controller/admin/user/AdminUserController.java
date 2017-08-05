package com.finger.birds.controller.admin.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finger.birds.db.bean.UserInfoBean;
import com.finger.birds.db.bean.UserQueryBean;
import com.finger.birds.db.bean.UserSearchBean;
import com.finger.birds.db.po.user.UserCenterPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.db.po.user.UserSearchPO;
import com.finger.birds.param.user.UserInfoParam;
import com.finger.birds.param.user.UserQueryParam;
import com.finger.birds.param.user.UserSearchParam;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController<AdminUserController>{

	@Resource
	private UserService userService;

	@RequestMapping(value="/addUser.html")
	public void addUser(@Valid UserInfoParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		userService.addUserAdmin(param.initBean());
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
	
	@RequestMapping(value="/searchByStart.html")
	public void loadingMore(@Valid UserSearchParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<UserSearchPO>> rslt = new Result<>();
		UserSearchBean bean = param.initBean();
		rslt.initRslt(userService.searchByStart(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/findByCode.html")
	public void findByCode(@Valid UserSearchParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		if (param.getCode() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "输入不能为空"));
		}
		Result<UserPO> rslt = new Result<>();
		UserPO user = userService.getByCode(param.getCode());
		rslt.setData(user);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value = "/editById.html", method=RequestMethod.POST)
	public void editById(@Valid UserInfoParam param, BindingResult bindingResult, HttpServletResponse response) {
		super.getAdminUser();
		UserInfoBean bean = param.initBean();
		userService.updateUserInfo(bean);
		AjaxUtils.write(response, new Result<>("修改成功"));
	}
	
	@RequestMapping(value = "/deleteById.html")
	public void deleteById(@Valid UserInfoParam param, BindingResult bindingResult, HttpServletResponse response) {
		super.getAdminUser();
		userService.deleteById(param.getUserId());
		AjaxUtils.write(response, new Result<>("修改成功"));
	}
	
	@RequestMapping(value="/loadingMore.html")
	public void loadingMore(@Valid UserQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<UserCenterPO>> rslt = new Result<>();
		UserQueryBean bean = param.initBean();
		PageBean<UserCenterPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(userService.getAdminList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
}
