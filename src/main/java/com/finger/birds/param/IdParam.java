package com.finger.birds.param;

import javax.validation.constraints.NotNull;

import com.finger.birds.utils.param.Param;

public class IdParam extends Param {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
