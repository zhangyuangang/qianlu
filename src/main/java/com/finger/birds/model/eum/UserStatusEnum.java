package com.finger.birds.model.eum;

public enum UserStatusEnum {
	删除(-1),
	正常(1),
	匿名(2);
	
	private UserStatusEnum(int val) {
		this.val = val;
	}

	private int val;

	public int getVal() {
		return val;
	}
	
}
