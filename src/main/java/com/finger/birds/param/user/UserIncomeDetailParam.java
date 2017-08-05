package com.finger.birds.param.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.finger.birds.utils.param.Param;

public class UserIncomeDetailParam extends Param {

	@NotNull
	private Long userId;

	@NotEmpty
	private String nickname;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
