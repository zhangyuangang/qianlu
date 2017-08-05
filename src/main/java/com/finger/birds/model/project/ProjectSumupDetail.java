package com.finger.birds.model.project;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ProjectSumupDetail {

	private Long id;//
	private Long applyDetailId;// 中标任务明细Id
	private String title;// 任务明细标题
	private String content;// 提炼内容
	private String imgUrl;// 图片url地址
	private Long sumupId;// 提炼内容Id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplyDetailId() {
		return applyDetailId;
	}

	public void setApplyDetailId(Long applyDetailId) {
		this.applyDetailId = applyDetailId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getSumupId() {
		return sumupId;
	}

	public void setSumupId(Long sumupId) {
		this.sumupId = sumupId;
	}

	public List<String> getImgList(){
		if(StringUtils.isEmpty(imgUrl)){
			return null;
		}
		return Arrays.asList(imgUrl.split(","));
	}
}
