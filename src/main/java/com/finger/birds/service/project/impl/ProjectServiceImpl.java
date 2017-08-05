package com.finger.birds.service.project.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.ProjectBean;
import com.finger.birds.db.bean.ProjectQueryBean;
import com.finger.birds.db.dao.msg.BirdsMsgDao;
import com.finger.birds.db.dao.project.ProjectDao;
import com.finger.birds.db.dao.project.ProjectSumupDao;
import com.finger.birds.db.po.chizi.ChiziPO;
import com.finger.birds.db.po.project.ProjectComPO;
import com.finger.birds.db.po.project.ProjectCommentCheckPO;
import com.finger.birds.db.po.project.ProjectListPO;
import com.finger.birds.db.po.project.ProjectPO;
import com.finger.birds.db.po.project.ProjectSumupPO;
import com.finger.birds.model.eum.BirdsMsgTypeEnum;
import com.finger.birds.model.msg.BirdsMsg;
import com.finger.birds.model.project.ProjectRefApply;
import com.finger.birds.model.project.ProjectSumup;
import com.finger.birds.model.project.ProjectSumupDetail;
import com.finger.birds.model.project.ProjectSumupUser;
import com.finger.birds.model.user.UserComment;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.chizi.ChiziService;
import com.finger.birds.service.project.ProjectService;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.exception.business.BusinessException;

@Service("projectServiceImpl")
public class ProjectServiceImpl extends BaseService implements ProjectService{

	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private ProjectSumupDao projectSumupDao;
	
	@Resource
	private ChiziService chiziService;
	
	@Resource
	private BirdsMsgDao birdsMsgDao;
	
	@Transactional
	@Override
	public void add(ProjectBean bean) {
		projectDao.save(bean);
		String company = bean.getCompanyName() == null ? "" : bean.getCompanyName();
		String occ = bean.getProfessionName() == null ? "" : bean.getProfessionName();
		String trade = bean.getIndustryName() == null ? "" : bean.getIndustryName();
		//池子归类
		List<ChiziPO> list = chiziService.group(company, occ, trade);
		List<Long> idsList = new ArrayList<Long>();
		for(ChiziPO po : list){
			idsList.add(po.getId());
		}
		chiziService.cpUpdate(idsList, bean.getId());
		
		if(bean.getTUserId() != null){
			//有人邀请，目标--投标人
			birdsMsgDao.save(new BirdsMsg(BirdsMsgTypeEnum.有人邀请.getVal(), bean.getTUserId(), bean.getId(), BirdsMsgTypeEnum.有人邀请.getName()));
		}
	}

	@Override
	public PageBean<ProjectListPO> getListForPage(ProjectQueryBean bean, PageBean<ProjectListPO> page) {
		page.setData(projectDao.getListForPage(bean, page.getStart(), page.getPageSize()));
		page.setCount(projectDao.getListForPageCount(bean));
		return page;
	}

	@Override
	public ProjectPO getById(Long id, Long userId) {
		return projectDao.getById(id, userId);
	}

	@Override
	public void updateViewTimes(Long id) {
		projectDao.updateViewTimes(id);
	}

	@Override
	public void updateApplyTimes(Long id) {
		projectDao.updateApplyTimes(id);
	}

	@Override
	public int checkComment(Long projectId, Long userId){
		ProjectCommentCheckPO pccpo = projectDao.checkComment(projectId);
		if(pccpo == null || pccpo.getRefId() == null){
			throw new BusinessException("该项目还未完成竞标，无法进行评论");
		}
		if(pccpo.getRefStatus() < 3){
			throw new BusinessException("该项目还未完成，无法进行评论");
		}
		int type = 0;
		if(userId.equals(pccpo.getAuserId())){//老鸟评论菜鸟
			type = 2;
			if((pccpo.getCommentStatus() & 2) > 0){
				throw new BusinessException("你已完成评论，不可再次评论");
			}
		} else if(userId.equals(pccpo.getPuserId())){
			type = 1;
			if((pccpo.getCommentStatus() & 1) > 0){
				throw new BusinessException("你已完成评论，不可再次评论");
			}
		}
		return type;
	}
	
	@Transactional
	@Override
	public void comment(UserComment uc, ProjectRefApply pra, Long projectId){
		ProjectCommentCheckPO pccpo = projectDao.checkComment(projectId);
		uc.setApplyId(pccpo.getApplyId());
		uc.setProjectId(projectId);
		if(pra == null){
			uc.setTUserId(pccpo.getPuserId());
			uc.setUserId(pccpo.getAuserId());
			pra = new ProjectRefApply();
			pra.setId(pccpo.getRefId());
			pra.setCommentStatus((short)2);
		} else {
			uc.setTUserId(pccpo.getAuserId());
			uc.setUserId(pccpo.getPuserId());
			pra.setId(pccpo.getRefId());
			pra.setCommentStatus((short)1);
		}
		projectDao.addComment(uc);
		projectDao.commentRef(pra);
	}
	
	@Override
	public ProjectComPO getCPOById(Long projectId){
		return projectDao.getCPOById(projectId);
	}
	
	@Override
	public Long checkRef(Long userId, Long sumupId){
		ProjectSumup ps = projectSumupDao.getById(sumupId);
		if(ps == null || ps.getId() == null){
			throw new BusinessException("该项目提炼不存在");
		}
		return projectSumupDao.checkSumup(userId, sumupId);
	}
	
	@Override
	public void ref(Long userId, Long sumupId){
		ProjectSumup ps = projectSumupDao.getById(sumupId);
		if(ps == null || ps.getId() == null){
			throw new BusinessException("该项目提炼不存在");
		}
		ProjectSumupUser psu = new ProjectSumupUser();
		psu.setSumupId(sumupId);
		psu.setUserId(userId);
		projectSumupDao.saveSumupUser(psu);
		projectSumupDao.updateById(1, sumupId);//参考人数+1
	}

	@Override
	public ProjectSumupPO getSumupPOById(Long id, Long userId){
		int count = projectSumupDao.checkIsSelf(id, userId);
		if(count > 0){
			userId = null;
		}
		ProjectSumupPO pspo = projectSumupDao.getSumupPOById(id, userId);
		if(pspo != null && pspo.getId() != null){
			pspo.setDetail(projectSumupDao.getSumupDetail(pspo.getId()));
		}
		return pspo; 
	}
	
	@Override
	public List<ProjectSumupDetail> getSumpDetailList(Long sumupId) {
		return projectSumupDao.getSumupDetail(sumupId);
	}

	@Override
	public void Exact(Long sumupId, Long userId) {
		Long projectId = projectSumupDao.checkExact(sumupId, userId);
		if(projectId == null){
			throw new BusinessException("您无法对自己的项目进行到位点评");
		}
		projectSumupDao.updateSumupUser(sumupId, userId);
	}

	@Override
	public Long checkExact(Long sumupId, Long userId) {
		return projectSumupDao.checkExact(sumupId, userId);
	}
}
