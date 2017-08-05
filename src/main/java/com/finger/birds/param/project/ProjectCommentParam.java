package com.finger.birds.param.project;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.finger.birds.utils.param.Param;

public class ProjectCommentParam extends Param {
	
	@NotNull
	private Long projectId;
	
	@NotNull
	private BigDecimal uscore;
	
	private String utext;
	
	private BigDecimal pscore;
	
	private String ptext;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getUscore() {
		return uscore;
	}

	public void setUscore(BigDecimal uscore) {
		this.uscore = uscore;
	}

	public BigDecimal getPscore() {
		return pscore;
	}

	public void setPscore(BigDecimal pscore) {
		this.pscore = pscore;
	}

	public String getUtext() {
		return utext;
	}

	public void setUtext(String utext) {
		this.utext = utext;
	}

	public String getPtext() {
		return ptext;
	}

	public void setPtext(String ptext) {
		this.ptext = ptext;
	}

}
