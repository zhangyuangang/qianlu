package com.finger.birds.utils.param;

/**
 * 接口所传参数
 * 
 * @author danny
 *
 */
public class Param {
	/**
	 * 请求类型 1 ajax, 2 page, ref ActionType enum.
	 */
	private Integer actionType;

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}
	
}
