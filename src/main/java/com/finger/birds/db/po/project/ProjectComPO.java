package com.finger.birds.db.po.project;

import com.finger.birds.model.project.Project;
import com.finger.birds.utils.date.DateUtils;

public class ProjectComPO extends Project{
	
	private String nickname;
	
	private Long auserId;
	
	private String anickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getAuserId() {
		return auserId;
	}

	public void setAuserId(Long auserId) {
		this.auserId = auserId;
	}

	public String getAnickname() {
		return anickname;
	}

	public void setAnickname(String anickname) {
		this.anickname = anickname;
	}
	
	public String getEndTimeStr(){
		if(super.getEndTime() == null){
			return "";
		} else {
			return DateUtils.format("yyyy-MM-dd", super.getEndTime());
		}
	}
	
	public String getCreateTimeStr(){
		return DateUtils.format("yyyy-MM-dd", super.getCreateTime());
	}
}
