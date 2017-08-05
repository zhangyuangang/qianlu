package com.finger.birds.controller.admin.article;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finger.birds.db.bean.ArticleBean;
import com.finger.birds.db.bean.ArticleQueryBean;
import com.finger.birds.db.po.article.ArticleListPO;
import com.finger.birds.db.po.user.UserPO;
import com.finger.birds.param.article.ArticleQueryParam;
import com.finger.birds.service.article.ArticleService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseController<AdminArticleController>{

	@Resource
	private ArticleService articleService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/addArticle.html", method = RequestMethod.POST)
	public void addDynamic(@Valid ArticleBean bean, BindingResult bindingResult, HttpServletResponse response){
		Long userId = super.getAdminUserId();
		if (bean.getCode() == null || bean.getHeadImg() == null || bean.getTitle() == null || bean.getIntro() == null || bean.getContent() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "新增失败"));
		}
		UserPO user = userService.getByCode(bean.getCode());
		if (user == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "大白id不存在"));
		}
		bean.setUserId(user.getId());
		bean.setAdminId(userId);

		boolean retVal = articleService.add(bean);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "新增失败"));
		}
		AjaxUtils.write(response, new Result<>("新增成功"));
	}
	
	@RequestMapping(value="/loadingMore.html")
	public void loadingMore(@Valid ArticleQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		Result<List<ArticleListPO>> rslt = new Result<>();
		ArticleQueryBean bean = param.initBean();
		PageBean<ArticleListPO> page = param.initPage();
		if(page.getPageSize() == null){
			page.setPageSize(5);
		}
		rslt.initRslt(articleService.getArticleAdminList(bean, page));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/editById.html", method=RequestMethod.POST)
	public void editById(@Valid ArticleQueryParam param, BindingResult bindingResult, HttpServletResponse response){
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
		
		boolean retVal = articleService.edit(id, user.getId());
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "修改失败"));
		}
		AjaxUtils.write(response, new Result<>("修改成功"));
	}
	
	@RequestMapping(value="/deleteById.html", method=RequestMethod.POST)
	public void deleteById(@Valid ArticleQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		super.getAdminUser();
		if (param.getId() == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "删除失败"));
		}
		
		boolean retVal = articleService.delete(param.getId());
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "删除失败"));
		}
		AjaxUtils.write(response, new Result<>("删除成功"));
	}
}
