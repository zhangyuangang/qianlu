package com.finger.birds.model;

public enum UserTypeEnum {
	大白((short)1),
	小白((short)2);
	
	private UserTypeEnum(short val) {
		this.val = val;
	}

	private short val;

	public short getVal() {
		return val;
	}

}
