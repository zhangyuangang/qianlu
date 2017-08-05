package com.finger.birds.param;

import com.finger.birds.ucontroller.actiontype.ActionType;
import com.finger.birds.utils.param.Param;

public class APIParam extends Param{
	
	public APIParam(){
		this.setActionType(ActionType.JSON.getVal());
	}
	
}
