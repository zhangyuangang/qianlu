package com.finger.birds.db.po.chizi;

import com.finger.birds.utils.CommConstant;

public class ChiziPO {

	private String typeName;

	private Short type;

	private int projectNum;

	private int dabaiNum;

	private int careNum;

	private String name;

	private Long id;
	
	private String imgUrl;
	
	private byte isCare;
	
	private String words;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
		switch (type) {
		case 1:
			typeName = "岗位之我得";
			break;
		case 2:
			typeName = "行业之我见";
			break;
		case 3:
			typeName = "公司之我评论";
			break;
		default:
			break;
		}
	}

	public int getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}

	public int getDabaiNum() {
		return dabaiNum;
	}

	public void setDabaiNum(int dabaiNum) {
		this.dabaiNum = dabaiNum;
	}

	public int getCareNum() {
		return careNum;
	}

	public void setCareNum(int careNum) {
		this.careNum = careNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		if(imgUrl.startsWith("http:") || imgUrl.startsWith("https:")){
			this.imgUrl = imgUrl;
		} else {
			this.imgUrl = CommConstant.IMG_URL + imgUrl;
		}
	}

	public byte getIsCare() {
		return isCare;
	}

	public void setIsCare(byte isCare) {
		this.isCare = isCare;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}
	
}
