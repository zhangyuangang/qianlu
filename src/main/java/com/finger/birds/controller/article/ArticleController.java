package com.finger.birds.controller.article;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.bean.ArticleQueryBean;
import com.finger.birds.db.bean.CommentQueryBean;
import com.finger.birds.db.po.article.ArticleCommentPO;
import com.finger.birds.db.po.article.ArticleListPO;
import com.finger.birds.param.article.ArticleQueryParam;
import com.finger.birds.param.article.CommentQueryParam;
import com.finger.birds.service.article.ArticleService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleController>{

	@Resource
	private ArticleService articleService;
	
	@RequestMapping(value="/getList.html")
	public void getArticleList(@Valid ArticleQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		Long selfId = super.getUserId();
		param.setSelfId(selfId);
		Result<List<ArticleListPO>> rslt = new Result<>();
		ArticleQueryBean bean = param.initBean();
		rslt.initRslt(articleService.getArticleList(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getById.html")
	public ModelAndView getById(ModelAndView mav, Long id){
		Long selfId = super.getUserId();
		mav.setViewName("Selected/Article");
		mav.addObject("article", articleService.getById(id, selfId));
		return mav;
	}

	@RequestMapping("/toArticleList.html")
	public ModelAndView toArticleList(ModelAndView mav){
		mav.setViewName("Selected");
		return mav;
	}
	
	@RequestMapping(value="/hitStarById.html")
	public void hitStarById(@Valid Long articleId, HttpServletResponse response){
		//Long userId = super.getUserId();
		if (articleId == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "文章不存在"));
		}
		boolean retVal = articleService.hitStarById(73L, articleId);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "失败"));
		}
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/comment.html")
	public void comment(@Valid Long tuserId, @Valid Long articleId, @Valid String content, HttpServletResponse response){
		//Long userId = super.getUserId();
		if (articleId == null || content == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "请求参数错误"));
		}
		boolean retVal = articleService.comment(73L, tuserId, articleId, content);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "失败"));
		}
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/hitCommentStarById.html")
	public void hitCommentStarById(@Valid Long commentId, HttpServletResponse response){
		//Long userId = super.getUserId();
		if (commentId == null) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.请求参数错误.getCode(), "文章不存在"));
		}
		boolean retVal = articleService.hitCommentStarById(73L, commentId);
		if (!retVal) {
			AjaxUtils.write(response, new Result<>(ResultStatusCode.未知错误.getCode(), "失败"));
		}
		AjaxUtils.write(response, new Result<>("成功"));
	}
	
	@RequestMapping(value="/loadingMoreComment.html")
	public void loadingMore(@Valid CommentQueryParam param, BindingResult bindingResult, HttpServletResponse response){
		Long selfId = super.getUserId();
		param.setUserId(selfId);
		Result<List<ArticleCommentPO>> rslt = new Result<>();
		CommentQueryBean bean = param.initBean();
		PageBean<ArticleCommentPO> page = param.initPage();
		rslt.initRslt(articleService.getArticleCommentList(bean, page));
		AjaxUtils.write(response, rslt);
	}
}
