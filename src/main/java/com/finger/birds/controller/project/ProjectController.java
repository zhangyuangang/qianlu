package com.finger.birds.controller.project;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finger.birds.db.bean.ProjectApplyBean;
import com.finger.birds.db.bean.ProjectBean;
import com.finger.birds.db.bean.ProjectQueryBean;
import com.finger.birds.db.bean.ProjectSumupBean;
import com.finger.birds.db.po.project.ProjectApplyPO;
import com.finger.birds.db.po.project.ProjectListPO;
import com.finger.birds.db.po.project.ProjectPO;
import com.finger.birds.db.po.project.ProjectSumupListPO;
import com.finger.birds.db.po.project.ProjectSumupPO;
import com.finger.birds.model.eum.BirdsMsgTypeEnum;
import com.finger.birds.model.msg.BirdsMsg;
import com.finger.birds.model.project.ProjectRefApply;
import com.finger.birds.model.user.UserComment;
import com.finger.birds.param.IdParam;
import com.finger.birds.param.project.ProjectAddParam;
import com.finger.birds.param.project.ProjectApplyAddParam;
import com.finger.birds.param.project.ProjectApplyListParam;
import com.finger.birds.param.project.ProjectCommentParam;
import com.finger.birds.param.project.ProjectListParam;
import com.finger.birds.param.project.ProjectSumupAddParam;
import com.finger.birds.param.project.ProjectSumupListParam;
import com.finger.birds.service.project.ProjectApplyService;
import com.finger.birds.service.project.ProjectService;
import com.finger.birds.service.project.ProjectSumupService;
import com.finger.birds.service.user.UserService;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.actiontype.ActionType;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController<ProjectController>{
	
	@Resource
	private ProjectSumupService projectSumupService;
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ProjectApplyService projectApplyService; 
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/toAdd.html", method=RequestMethod.GET)
	public ModelAndView toAdd(ModelAndView mav, Long tUserId){
		super.getUserId();
		mav.addObject("tUserId", tUserId);
		mav.setViewName("publishProject");
		return mav;
	}
	
	@RequestMapping(value="/add.html", method=RequestMethod.POST)
	public void add(@Valid ProjectAddParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<String> rslt = new Result<>();
		Long userId = super.getUserId();
		ProjectBean bean = param.initBean();
		bean.setUserId(userId);
		projectService.add(bean);
		rslt.setInfo("发布项目成功");
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getSumpData.html", method=RequestMethod.GET)
	public void getData(@Valid ProjectSumupListParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<List<ProjectSumupListPO>> rslt = new Result<>();
		rslt.initRslt(projectSumupService.getListForHPage(param.initBean(), param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getSumpDetail.html", method=RequestMethod.GET)
	public ModelAndView getSumpDetail(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		Long userId = super.getUserId();
		Long projectId = projectService.checkRef(userId, param.getId());
		if(projectId == null){
			//付款
			projectService.ref(userId, param.getId());
		} 
		ProjectSumupPO sump = projectService.getSumupPOById(param.getId(), userId);
		mav.addObject("sumup", sump);
		if(sump.getPuserId() != null){
			mav.addObject("userCenterPO", userService.getInfoForUserCenter(userId, sump.getPuserId()));
		}
		if(sump.getAuserId() != null){
			mav.addObject("userCenterPOA", userService.getInfoForUserCenter(userId, sump.getAuserId()));
		}
		mav.setViewName("projectRef");
		return mav;
	}
	
	@RequestMapping(value="/getList.html", method=RequestMethod.GET)
	public void getProject(@Valid ProjectListParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<List<ProjectListPO>> rslt = new Result<>();
		ProjectQueryBean bean = param.initBean();
		rslt.initRslt(projectService.getListForPage(bean, param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getById.html", method=RequestMethod.GET)
	public ModelAndView getDetail(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		Long userId = super.getUserId();
		ProjectPO ppo = projectService.getById(param.getId(), userId);
		mav.addObject("project", ppo);
		String viewname = "";
		if(ppo.getIsSelf() == 0 || (ppo.getIsSelf() == 2 && ppo.getStatus() == 1)){
			viewname = "projectOther";
		} else if(ppo.getIsSelf() == 1 && ppo.getStatus() == 1){
			viewname = "selfProject";
		} else if((ppo.getIsSelf() == 3 && ppo.getStatus() == 2)|| ppo.getStatus() == 2){
			mav.addObject("apply", projectApplyService.getChoosedByProjectId(param.getId()));
			{//清楚选中标信息
				if(ppo.getId() != null){
					BirdsMsg msg = new BirdsMsg(BirdsMsgTypeEnum.投标被选中.getVal(), null, ppo.getId());
					userService.readMsg(msg);
				}
			}
			viewname = "projectIng";
		} else if(ppo.getStatus() == 3){
			mav.addObject("apply", projectApplyService.getChoosedByProjectId(param.getId()));
			viewname = "projectEd";
		}
		projectService.updateViewTimes(param.getId());//查看次数+1
		mav.setViewName(viewname);
		return mav;
	}
	
	@RequestMapping(value="/toApply.html", method=RequestMethod.GET)
	public ModelAndView toApply(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		param.setActionType(ActionType.PAGE.getVal());
		Long userId = super.getUserId();
		ProjectPO ppo = projectService.getById(param.getId(), userId);
		if(ppo.getApplyTimes() >= 3){
			throw new BusinessException("该项目竞标人数已满");
		}
		if(ppo.getStatus() != 1){
			throw new BusinessException("该项目已结束竞标");
		}
		if(ppo.getTUserId() != null && !ppo.getTUserId().equals(userId)){
			throw new BusinessException("该项目已指定竞标人，您不能对该项目进行投标");
		}
		projectApplyService.checkIsApply(param.getId(), userId);
		mav.addObject("project", projectService.getById(param.getId(), userId));
		mav.setViewName("addApply");
		return mav;
	}
	
	@RequestMapping(value="/apply.html", method=RequestMethod.POST)
	public void apply(@Valid ProjectApplyAddParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<String> rslt = new Result<>();
		Long userId = super.getUserId();
		ProjectApplyBean bean = param.initBean();
		bean.setUserId(userId);
		projectApplyService.add(bean);
		rslt.setInfo("发布成功");
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getApplyList.html", method=RequestMethod.GET)
	public void getApplyList(@Valid ProjectApplyListParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<String> rslt = new Result<>();
		rslt.initRslt(projectApplyService.getListForPage(param.initBean(), param.initPage()));
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/getApplyDetail.html", method = RequestMethod.GET)
	public ModelAndView getApplyDetail(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		Long userId = super.getUserId();
		ProjectApplyPO papo = projectApplyService.getById(param.getId());
		mav.addObject("apply", papo);
		if(papo != null){
			ProjectPO ppo = projectService.getById(papo.getProjectId(), userId);
			mav.addObject("project", ppo);
		} else {
			throw new BusinessException("竞标数据不存在");
		}
		mav.setViewName("projectApplyDetail");
		return mav;
	}
	
	@RequestMapping(value="/chooseApply.html", method = RequestMethod.GET)
	public ModelAndView chooseApply(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		//XXX 付款
		Long projectId = projectApplyService.choose(param.getId(), super.getUserId());
		mav.setViewName("redirect:/project/getById.html?id=" + projectId);
		return mav;
	}
	
	@RequestMapping(value="/complete.html", method = RequestMethod.POST)
	public void complete(@Valid IdParam param, BindingResult bindingResult, HttpServletResponse response){
		Long userId = super.getUserId();
		Result<String> rslt = new Result<>();
		projectApplyService.complete(param.getId(), userId);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/completeAll.html", method = RequestMethod.POST)
	public void completeAll(@Valid IdParam param, BindingResult bindingResult, HttpServletResponse response){
		Long userId = super.getUserId();
		Result<String> rslt = new Result<>();
		projectApplyService.completeAll(param.getId(), userId);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/toSumup.html", method=RequestMethod.GET)
	public ModelAndView toSumup(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		projectApplyService.checkSumup(param.getId(), super.getUserId());
		mav.addObject("apply", projectApplyService.getById(param.getId()));
		mav.setViewName("sumup");
		return mav;
	}
	
	@RequestMapping(value="/sumup.html", method=RequestMethod.POST)
	public void sumup(@Valid ProjectSumupAddParam param, BindingResult bindingResult, HttpServletResponse response){
		Result<String> rslt = new Result<>();
		Long userId = super.getUserId();
		projectApplyService.checkSumup(param.getApplyId(), userId);
		ProjectSumupBean bean = param.initBean();
		bean.setUserId(userId);
		projectSumupService.add(bean);
		AjaxUtils.write(response, rslt);
	}
	
	@RequestMapping(value="/toComment.html", method=RequestMethod.GET)
	public ModelAndView toComment(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		Long userId = super.getUserId();
		int type = projectService.checkComment(param.getId(), userId);
		if(type == 1){
			mav.setViewName("toObirdComment");
		} else if(type == 2){
			mav.setViewName("toNbirdComment");
		}
		mav.addObject("project", projectService.getCPOById(param.getId()));
		return mav;
	}
	
	@RequestMapping(value="/comment.html", method=RequestMethod.POST)
	public ModelAndView comment(@Valid ProjectCommentParam param, BindingResult bindingResult, ModelAndView mav){
		Long userId = super.getUserId();
		int type = projectService.checkComment(param.getProjectId(), userId);
		ProjectRefApply pra = null;
		UserComment uc = new UserComment();
		uc.setCommentScore(param.getUscore());
		uc.setCommentText(param.getUtext());
		if(type == 1){
			 pra = new ProjectRefApply();
			 pra.setCommentScore(param.getPscore());
			 pra.setCommentText(param.getPtext());
		}
		projectService.comment(uc, pra, param.getProjectId());
		mav.addObject("data", "评论成功");
		mav.addObject("toUrl", "/user/toUserCenter.html?tUserId=" + userId);
		mav.setViewName("rslt");
		return mav;
	}
	
	@RequestMapping(value="/exact.html", method=RequestMethod.GET)
	public ModelAndView exact(@Valid IdParam param, BindingResult bindingResult, ModelAndView mav){
		projectService.Exact(param.getId(), super.getUserId());
		mav.setViewName("redirect:/project/getSumpDetail.html?id=" + param.getId());
		return mav;
	}
	
	@RequestMapping(value="/toSearchSumup.html")
	public ModelAndView searchSumup(ModelAndView mav, Integer searchType, String keyWords){
		super.getUser();
		keyWords = StringUtils.isEmpty(keyWords) ? "" : keyWords;
		mav.addObject("name", keyWords);
		mav.addObject("queryParam", "searchType=" + searchType + "&keyWords=" + keyWords);
		mav.setViewName("searchSumup");
		return mav;
	}
	
	@RequestMapping(value="/toSearchProject.html")
	public ModelAndView searchProject(ModelAndView mav, Integer searchType, String keyWords){
		super.getUser();
		keyWords = StringUtils.isEmpty(keyWords) ? "" : keyWords;
		mav.addObject("name", keyWords + "相关疑问");
		mav.addObject("queryParam", "searchType=" + searchType + "&keyWords=" + keyWords + "&type=-1");
		mav.setViewName("searchProject");
		return mav;
	}
	
	@RequestMapping(value="/toInvertProject.html")
	public ModelAndView invertProject(ModelAndView mav, Long tuserId){
		super.getUser();
		mav.addObject("name", "接收到的邀请");
		mav.addObject("queryParam", "tuserId=" + tuserId + "&type=-1");
		mav.setViewName("searchProject");
		
		{//清楚邀请信息
			if(tuserId != null){
				BirdsMsg msg = new BirdsMsg(BirdsMsgTypeEnum.有人邀请.getVal(), tuserId, null);
				userService.readMsg(msg);
			}
		}
		return mav;
	}
}
