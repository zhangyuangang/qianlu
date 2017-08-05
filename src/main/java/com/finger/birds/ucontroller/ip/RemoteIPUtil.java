package com.finger.birds.ucontroller.ip;

import javax.servlet.http.HttpServletRequest;

public class RemoteIPUtil {
	/**
	 * 获取请求的ip地址
	 * 
	 * @param request
	 * @return String
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");//nginx config
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" x-forwarded-for ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
