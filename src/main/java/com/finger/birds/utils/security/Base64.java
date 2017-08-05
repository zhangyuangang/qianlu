package com.finger.birds.utils.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * BASE64加密解密
 */
@SuppressWarnings("restriction")
public class Base64 {

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) throws Exception {
		return new String(new BASE64Decoder().decodeBuffer(key));
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key.getBytes());
	}

	public static void main(String[] args) throws Exception {
		String param="{\"salonId\":" + 1 + ",\"qrType\":"
				+ 1+"}";
		try {
			System.out.println(Base64.encryptBASE64(param));
			System.out.println(Base64.decryptBASE64("eyJzYWxvbklkIjoxLCJxclR5cGUiOjF9"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
