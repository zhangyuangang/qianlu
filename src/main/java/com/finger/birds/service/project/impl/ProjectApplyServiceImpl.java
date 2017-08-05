package com.finger.birds.service.project.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.bean.ProjectApplyBean;
import com.finger.birds.db.bean.ProjectApplyQueryBean;
import com.finger.birds.db.dao.msg.BirdsMsgDao;
import com.finger.birds.db.dao.project.ProjectApplyDao;
import com.finger.birds.db.dao.project.ProjectDao;
import com.finger.birds.db.po.project.ProjectApplyCheckPO;
import com.finger.birds.db.po.project.ProjectApplyListPO;
import com.finger.birds.db.po.project.ProjectApplyPO;
import com.finger.birds.db.po.project.ProjectPO;
import com.finger.birds.model.eum.BirdsMsgTypeEnum;
import com.finger.birds.model.msg.BirdsMsg;
import com.finger.birds.model.project.Project;
import com.finger.birds.model.project.ProjectApplyDetail;
import com.finger.birds.model.project.ProjectRefApply;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.project.ProjectApplyService;
import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.exception.business.BusinessException;

@Service("projectApplyService")
public class ProjectApplyServiceImpl extends BaseService implements ProjectApplyService {

	@Resource
	private ProjectApplyDao projectApplyDao;
	
	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private BirdsMsgDao birdsMsgDao;
	
	@Transactional
	@Override
	public void add(ProjectApplyBean bean) {
		this.checkApply(bean.getProjectId(), bean.getUserId(), bean.getDetail(), bean.getPrice());
		projectApplyDao.save(bean);
		for(ProjectApplyDetail pad : bean.getDetail()){
			pad.setApplyId(bean.getId());
		}
		projectApplyDao.detailBatchSave(bean.getDetail());
		projectDao.updateApplyTimes(bean.getProjectId());
		Project p = projectDao.get(bean.getProjectId(), (byte)0);
		//产生提示消息，目标--项目发布人
		birdsMsgDao.save(new BirdsMsg(BirdsMsgTypeEnum.项目有人投标.getVal(), p.getUserId(), bean.getProjectId(), BirdsMsgTypeEnum.项目有人投标.getName()));
	}
	
	@Override
	public void checkIsApply(Long id, Long userId){
		int count = projectApplyDao.checkIsApply(id, userId);
		if(count > 0){
			throw new BusinessException("您已对该项目投标，不可重复投标");
		}
	}
	
	private void checkApply(Long id, Long userId, List<ProjectApplyDetail> list, BigDecimal totalPrice){
		if(list == null || list.isEmpty()){
			throw new BusinessException("详情不能为空");
		}
		int per = 0;
		BigDecimal total = BigDecimal.ZERO;
		for(ProjectApplyDetail pad : list){
			per += pad.getPayPer();
			total = total.add(pad.getPrice());
		}
		if(per != 100){
			throw new BusinessException("百分比设置错误");
		}
		if(total.compareTo(totalPrice) != 0){
			throw new BusinessException("价格比例设置错误");
		}
		ProjectPO ppo = projectDao.getById(id, userId);
		if(ppo.getApplyTimes() >= 3){
			throw new BusinessException("该项目竞标人数已满");
		}
		if(ppo.getStatus() != 1){
			throw new BusinessException("该项目已结束竞标");
		}
		if(ppo.getTUserId() != null && !ppo.getTUserId().equals(userId)){
			throw new BusinessException("该项目已指定竞标人，您不能对该项目进行投标");
		}
		int count = projectApplyDao.checkIsApply(id, userId);
		if(count > 0){
			throw new BusinessException("您已对该项目投标，不可重复投标");
		}
	}
	
	@Override
	public PageBean<ProjectApplyListPO> getListForPage(ProjectApplyQueryBean bean, PageBean<ProjectApplyListPO> page) {
		page.setData(projectApplyDao.getListForPage(bean, page.getStart(), page.getPageSize()));
		page.setCount(projectApplyDao.getListForPageCount(bean));
		
		if(bean.getProjectId() != null){
			BirdsMsg msg = new BirdsMsg(BirdsMsgTypeEnum.项目有人投标.getVal(), null, bean.getProjectId());
			birdsMsgDao.readed(msg);
		}
		return page;
	}

	@Override
	public ProjectApplyPO getById(Long id) {
		ProjectApplyPO papo = projectApplyDao.getById(id);
		if(papo != null && papo.getId() != null){
			papo.setDetailList(projectApplyDao.getDetailByPAId(papo.getId()));
		}
		return papo;
	}
	
	@Override
	public ProjectApplyPO getChoosedByProjectId(Long projectId){
		ProjectApplyPO papo = projectApplyDao.getChoosedByProjectId(projectId);
		if(papo != null && papo.getId() != null){
			papo.setDetailList(projectApplyDao.getDetailByPAId(papo.getId()));
		}
		return papo;
	}

	@Transactional
	@Override
	public Long choose(Long id, Long userId) {
		ProjectApplyPO papo = projectApplyDao.getById(id);
		if(papo == null || papo.getId() == null){
			throw new BusinessException("非法请求");
		}
		Project p = projectDao.get(papo.getProjectId(), (byte)1);
		if(p.getStatus() != 1){
			throw new BusinessException("该项目已经完成竞标，不可再次选标");
		}
		if(!p.getUserId().equals(userId)){
			throw new BusinessException("无法为不是您发布的项目选标");
		}
		ProjectRefApply ref = new ProjectRefApply();
		ref.setApplyId(id);
		ref.setProjectId(p.getId());
		projectApplyDao.saveRef(ref);
		//XXX 付款操作
		projectDao.updateStatus(p.getId(), (short)2);
		
		//投标被选中，目标--投标人
		birdsMsgDao.save(new BirdsMsg(BirdsMsgTypeEnum.投标被选中.getVal(), papo.getUserId(), papo.getProjectId(), BirdsMsgTypeEnum.投标被选中.getName()));
		
		return papo.getProjectId();
	}

	@Transactional
	@Override
	public void complete(Long paDetailId, Long userId) {
		ProjectApplyCheckPO paco = projectApplyDao.getByPAByDetailId(paDetailId);
		if(paco == null || paco.getId() == null){
			throw new BusinessException("数据错误，非法请求!");
		}
		if(paco.getAuserId().equals(userId)){//老鸟点完成
			projectApplyDao.updateDetailStatus(paDetailId, 1, (short)1);
		} else if(paco.getPuserId().equals(userId)){//菜鸟点完成, 计算给老鸟的费用
			projectApplyDao.updateDetailStatus(paDetailId, 2, (short)1);
			projectApplyDao.updateRefStatus(paco.getProjectId(), (short)2);
		} else {
			throw new BusinessException("数据错误非法请求");
		}
		List<ProjectApplyDetail> detailList = projectApplyDao.getDetailByPAId(paco.getId());
		boolean isComplete = true;
		for(ProjectApplyDetail pad : detailList){
			if(pad.getNbStatus() == 0){
				isComplete = false;
				break;
			}
		}
		if(isComplete){//项目完成
			projectDao.updateStatus(paco.getProjectId(), (short)3);
			projectApplyDao.updateRefStatus(paco.getProjectId(), (short)3);
		}
	}
	
	@Transactional
	@Override
	public void completeAll(Long applyId, Long userId){//XXX
		List<ProjectApplyDetail> detailList = projectApplyDao.getDetailByPAId(applyId);
		for(ProjectApplyDetail detail : detailList){
			this.complete(detail.getId(), userId);
		}
	}

	@Override
	public void checkSumup(Long id, Long userId) {
		if(projectApplyDao.checkSumup(userId, id) == 0){
			throw new BusinessException("项目已提炼或者未完成...，您无法进行提炼总结");
		}
	}

	@Override
	public int checkComment(Long userId, Long projectId) {
		
		return 0;
	}
}
