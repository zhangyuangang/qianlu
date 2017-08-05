package com.finger.birds.param;

import com.finger.birds.ucontroller.authkey.AuthkeyUtil;
import com.finger.birds.ucontroller.validationext.Authkey;

public class MemberParam extends APIParam{
	
	@Authkey
	private String authkey;

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	
	public Long initUserId(){
		return AuthkeyUtil.checkKey(this.authkey);
	}
	
}
