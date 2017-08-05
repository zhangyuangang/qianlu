package com.finger.birds.service.chizi.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finger.birds.db.dao.chizi.ChiziDao;
import com.finger.birds.db.po.chizi.ChiziPO;
import com.finger.birds.model.chizi.ChiziProject;
import com.finger.birds.model.chizi.ChiziUser;
import com.finger.birds.service.BaseService;
import com.finger.birds.service.chizi.ChiziService;

@Service("chiziService")
public class ChiziServiceImpl extends BaseService implements ChiziService{

	@Resource
	private ChiziDao chiziDao;
	
	@Override
	public Map<String, List<ChiziPO>> getAll() {
		List<ChiziPO> list = chiziDao.getAll();
		LinkedHashMap<String, List<ChiziPO>> map = new LinkedHashMap<>();
		
		for(ChiziPO po : list){
			List<ChiziPO> sonList = map.get(po.getName());
			if(sonList == null){
				sonList = new ArrayList<>();
				map.put(po.getName(), sonList);
			}
			sonList.add(po);
		}
		return map;
	}

	@Override
	public ChiziPO getById(Long userId, Long chiziId) {
		ChiziPO po = chiziDao.getById(chiziId);
		if(po != null && po.getId() != null){
			po.setIsCare((byte)chiziDao.checkCare(userId, chiziId));
		}
		return po;
	}

	@Transactional
	@Override
	public int care(Long userId, Long chiziId) {
		if(chiziDao.checkCare(userId, chiziId) == 1){
			chiziDao.cancelCare(userId, chiziId);
			chiziDao.addNumById(-1, chiziId);
			return 0;
		} else {
			chiziDao.addCare(userId, chiziId);
			chiziDao.addNumById(1, chiziId);
			return 1;
		}
	}

	@Override
	public List<ChiziPO> group(String company, String occ, String trade){
		List<ChiziPO> list = chiziDao.getAll();
		List<ChiziPO> rsltList = new ArrayList<>();
		for(ChiziPO po : list){
			String[] wordsArr = po.getWords().split(",");
			if(po.getType() == 3){//公司
				if(contained(company, wordsArr)){
					rsltList.add(po);
				}
			} else if(po.getType() == 2){
				if(contained(trade, wordsArr)){
					rsltList.add(po);
				}
			} else if(po.getType() == 1){
				if(contained(occ, wordsArr)){
					rsltList.add(po);
				}
			}
		}
		return rsltList;
	}
	
	private boolean contained(String str, String[] strs){
		for(String s : strs){
			if(str.contains(s)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void cuUpdate(List<Long> chiziIds, Long userId) {
		List<Long> hIds = null;
		if(!chiziIds.isEmpty()){
			hIds = chiziDao.getCUByIdList(userId, chiziIds);//需要的已经存在的关联关系
		}
		List<ChiziUser> bList = chiziDao.getCUByUserId(userId);//已经存在的关联关系
		List<Long> needAdd = new ArrayList<Long>();
		List<Long> needDel = new ArrayList<Long>();
		hIds = hIds == null ? new ArrayList<Long>() : hIds;
		for(Long chiziId : chiziIds){
			if(!hIds.contains(chiziId)) {
				needAdd.add(chiziId);
				//
				chiziDao.addCUser(userId, chiziId);
				chiziDao.addNumById(2, chiziId);
			}
		}
		for(ChiziUser cub : bList){
			if(!hIds.contains(cub.getChiziId())){
				needDel.add(cub.getChiziId());
				//
				chiziDao.cancelCUser(userId, cub.getChiziId());
				chiziDao.addNumById(-2, cub.getChiziId());
			}
		}
		
		
	}

	@Transactional
	@Override
	public void cpUpdate(List<Long> chiziIds, Long projectId) {
		List<Long> hIds = null;
		if(!chiziIds.isEmpty()){
			hIds = chiziDao.getCPByIdList(projectId, chiziIds);//需要的已经存在的关联关系
		}
		List<ChiziProject> bList = chiziDao.getCPByProjectId(projectId);//已经存在的关联关系
		List<Long> needAdd = new ArrayList<Long>();
		List<Long> needDel = new ArrayList<Long>();
		hIds = hIds == null ? new ArrayList<Long>() : hIds;
		for(Long chiziId : chiziIds){
			if(!hIds.contains(chiziId)) {//TODO 批量
				needAdd.add(chiziId);
				//添加
				chiziDao.addCProject(projectId, chiziId);
				chiziDao.addNumById(3, chiziId);
			}
		}
		for(ChiziProject cub : bList){
			if(!hIds.contains(cub.getChiziId())){ //TODO批量
				needDel.add(cub.getChiziId());
				//减少
				chiziDao.cancelCProject(projectId, cub.getChiziId());
				chiziDao.addNumById(-3, cub.getChiziId());
			}
		}
	}
	
}
