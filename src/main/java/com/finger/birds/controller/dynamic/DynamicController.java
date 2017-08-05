package com.finger.birds.controller.dynamic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.bean.DynamicQueryBean;
import com.finger.birds.db.po.dynamic.DynamicCommentPO;
import com.finger.birds.db.po.dynamic.DynamicListPO;
import com.finger.birds.param.dynamic.CommentQueryParam;
import com.finger.birds.param.dynamic.DynamicQueryParam;
import com.finger.birds.service.dynamic.DynamicService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/dynamic")
public class DynamicController extends BaseController<DynamicController>{

	@Resource
	private DynamicService dynamicService;
	
	@RequestMapping(value="/getList.html")
	public void getList(@Valid DynamicQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		Long selfId = super.getUserId();
		param.setSelfId(selfId);
		Result<List<DynamicListPO>> rslt = new Result<>();
		DynamicQueryBean bean = param.initBean();
		rslt.initRslt(dynamicService.getList(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getById.html")
	public void getById(@Valid Long dynamicId, HttpServletResponse response){
		Long userId = super.getUserId();
		//DynamicListPO po = dynamicService.getById(userId, dynamicId);
		dynamicService.getById(userId, dynamicId);
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/hitStarById.html")
	public void hitStarById(@Valid Long dynamicId, HttpServletResponse response){
		Long userId = super.getUserId();
		if (dynamicId == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "文章不存在"));
		}
		boolean retVal = dynamicService.hitStarById(userId, dynamicId);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "失败"));
		}
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/comment.html")
	public void comment(@Valid Long tuserId, @Valid Long dynamicId, @Valid String content, HttpServletResponse response){
		Long userId = super.getUserId();
		if (dynamicId == null || content == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "文章不存在"));
		}
		boolean retVal = dynamicService.comment(userId, tuserId, dynamicId, content);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "失败"));
		}
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/hitCommentStarById.html")
	public void hitCommentStarById(@Valid Long commentId, HttpServletResponse response){
		Long userId = super.getUserId();
		if (commentId == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "文章不存在"));
		}
		boolean retVal = dynamicService.hitCommentStarById(userId, commentId);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "失败"));
		}
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/loadingMoreComment.html")
	public void loadingMore(@Valid CommentQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		Long selfId = super.getUserId();
		param.setUserId(selfId);
		Result<List<DynamicCommentPO>> rslt = new Result<>();
		CommentQueryBean bean = param.initBean();
		PageBean<DynamicCommentPO> page = param.initPage();
		rslt.initRslt(dynamicService.getDynamicCommentList(bean, page));
		AjaxUtils.write(response, rslt);
	}
}
