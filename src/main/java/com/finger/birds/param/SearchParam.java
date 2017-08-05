package com.finger.birds.param;

import org.springframework.util.StringUtils;

import com.finger.birds.utils.param.Param;

public class SearchParam extends Param {

	private String companyName;
	private String professionName;
	private String industryName;
	private String keyWords;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	public String getKeyWords(){
		if(!StringUtils.isEmpty(companyName)){
			return companyName;
		} 
		if(!StringUtils.isEmpty(professionName)){
			return professionName;
		}
		if(!StringUtils.isEmpty(industryName)){
			return industryName;
		}
		return keyWords;
	}
	
	public String getKeyName(){
		if(!StringUtils.isEmpty(companyName)){
			return "companyName";
		} 
		if(!StringUtils.isEmpty(professionName)){
			return "professionName";
		}
		if(!StringUtils.isEmpty(industryName)){
			return "industryName";
		}
		return "keyWords";
	}
	
	public int getSearchType(){
		if(!StringUtils.isEmpty(companyName)){
			return 1;
		} 
		if(!StringUtils.isEmpty(professionName)){
			return 2;
		}
		if(!StringUtils.isEmpty(industryName)){
			return 3;
		}
		return 0;
	}
}
