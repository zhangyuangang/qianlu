package com.finger.birds.model.chizi;

public class Chizi {

	private Long id;
	private String name;
	private Short type;
	private Integer careNum;
	private Integer dabaiNum;
	private Integer projectNum;
	private String imgUrl;
	private String words;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public int getCareNum() {
		return careNum;
	}

	public void setCareNum(int careNum) {
		this.careNum = careNum;
	}

	public int getDabaiNum() {
		return dabaiNum;
	}

	public void setDabaiNum(int dabaiNum) {
		this.dabaiNum = dabaiNum;
	}

	public int getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	
}
