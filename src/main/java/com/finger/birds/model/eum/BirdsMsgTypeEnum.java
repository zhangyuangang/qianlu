package com.finger.birds.model.eum;

public enum BirdsMsgTypeEnum {

	项目有人投标((short) 1, "项目有人投标"),
	投标被选中((short) 2, "投标被选中"),
	有人邀请((short) 3, "有人邀请"),
	有消息发送((short) 4, "有消息发送");
	
	private short val;
	
	private String name;

	private BirdsMsgTypeEnum(short val, String name) {
		this.val = val;
		this.name = name;
	}

	public short getVal() {
		return val;
	}

	public String getName() {
		return name;
	}
}
