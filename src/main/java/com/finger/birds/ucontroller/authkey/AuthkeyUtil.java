package com.finger.birds.ucontroller.authkey;

import org.apache.commons.lang.StringUtils;

import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.security.DesSecret;

public class AuthkeyUtil {
	
	private final static String AUTHSECRETKEY = "upbirds_";
	private final static String SPLIT = "<split>";
	
	/**
	 * create auth key
	 * @param id
	 * @return
	 */
	public static String createKey(Long id){
		String start = DateUtils.getTodayDay();
//		String authSecretKey = SysConfig.getConfig(SysEnum.AUTH_SECRET_KEY);
		return DesSecret.encrypt(start + SPLIT + id, AUTHSECRETKEY);
	}
	
	/**
	 * check the auth key
	 * @param cipherText
	 * @return
	 */
	public static Long checkKey(String cipherText){
//		String authSecretKey = SysConfig.getConfig(SysEnum.AUTH_SECRET_KEY);
		String key = DesSecret.decrypt(cipherText, AUTHSECRETKEY);
		if(StringUtils.isEmpty(key)){
			return null;
		}
//		String split = SysConfig.getConfig(SysEnum.AUTHKEY_SPLIT);
		Long id = new Long(-1);
		try{
			String[] strArr = key.split(SPLIT);
			id = Long.parseLong(strArr[1]);
		} catch(Exception e){
			//Do Nothing;
		}
		return id;
	}
	
	
	public static void main(String[] args) {
		System.out.println(createKey(1L));

	}
}
