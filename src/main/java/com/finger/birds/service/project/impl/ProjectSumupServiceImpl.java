package com.finger.birds.service.project.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.ProjectSumupBean;
import com.finger.birds.db.bean.ProjectSumupQueryBean;
import com.finger.birds.db.dao.project.ProjectApplyDao;
import com.finger.birds.db.dao.project.ProjectDao;
import com.finger.birds.db.dao.project.ProjectSumupDao;
import com.finger.birds.db.po.project.ProjectApplyPO;
import com.finger.birds.db.po.project.ProjectSumupListPO;
import com.finger.birds.model.project.Project;
import com.finger.birds.model.project.ProjectSumupDetail;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.project.ProjectSumupService;
import com.finger.birds.utils.bean.PageBean;

@Service("projectSumupService")
public class ProjectSumupServiceImpl extends BaseService implements ProjectSumupService{

	@Resource
	private ProjectSumupDao projectSumupDao; 
	
	@Resource
	private ProjectApplyDao projectApplyDao; 
	
	@Resource
	private ProjectDao projectDao;
	
	@Override
	public PageBean<ProjectSumupListPO> getListForHPage(ProjectSumupQueryBean bean, PageBean<ProjectSumupListPO> page) {
		page.setData(projectSumupDao.getListForHPage(bean, page.getStart(), page.getPageSize()));
		page.setCount(projectSumupDao.getListForHPageCount(bean));
		return page;
	}
	
	@Transactional
	@Override
	public void add(ProjectSumupBean bean){
		ProjectApplyPO papo = projectApplyDao.getById(bean.getApplyId());
		Project p = projectDao.get(papo.getProjectId(), (byte)0);
		
		{
			bean.setType((short)1);//系统产生
			bean.setIndustryName(p.getIndustryName());
			bean.setName(p.getName());
			bean.setProfessionName(p.getProfessionName());
			bean.setCompanyName(p.getCompanyName());
			bean.setDesc(p.getDesc());
			bean.setRefPrice(papo.getPrice());
		}
		bean.setPrice(papo.getPrice().multiply(new BigDecimal("0.1")));
		bean.setProjectId(papo.getProjectId());
		
		projectSumupDao.save(bean);
		for(ProjectSumupDetail detail : bean.getDetailList()){
			detail.setSumupId(bean.getId());
		}
		projectSumupDao.detailBatchSave(bean.getDetailList());
		projectApplyDao.updateRefStatus(papo.getProjectId(), (short)4);
	} 
}
