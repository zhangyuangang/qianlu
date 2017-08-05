package com.finger.birds.ucontroller.request;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtils {
	
	public static Map<String, Object> getParamIgnoreCase(Set<String> paramName, HttpServletRequest request){
		Enumeration<String> e = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			if(paramName.contains(name.toLowerCase())){
				map.put(name.toLowerCase(), request.getParameter(name));
			}
		}
		return map;
	}
	
}
