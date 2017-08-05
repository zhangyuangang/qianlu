package com.finger.birds.ucontroller.actiontype;

/**
 * @Action : request type
 * @author danny
 *
 */
public enum ActionType {
	DEFAULT(0),
	JSON(1),
	PAGE(2),
	JSONP(3);

	private Integer val;

	private ActionType(Integer val) {
		this.val = val;
	}
	
	
	public Integer getVal() {
		return val;
	}

}
