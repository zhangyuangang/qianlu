package com.finger.birds.ucontroller.ajax;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.finger.birds.ucontroller.exception.system.SystemException;
import com.finger.birds.utils.param.Param;
import com.finger.birds.utils.rslt.type.APIResult;
import com.finger.birds.utils.rslt.type.Result;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Ajax print tools.
 * <p>Description: </p>
 * @since 2014-10-30
 * @author 段烨
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2014</p>
 */
public class AjaxUtils {
	
	/**
	 * Action: Ajax response. send the string to page. 
	 * @param response
	 * @param obj
	 */
	public static void write(HttpServletResponse response, Object obj){
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out;
		try {
			JSONObject info = JSONObject.fromObject(obj);
			out = response.getWriter();
			out.print(info);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new SystemException("write failed.", e);
		} catch (JSONException je){
			throw new SystemException("obj can't convert to json.", je);
		}
	}
	
	/**
	 * Action: Ajax response. send the string to page. 
	 * @param response
	 * @param obj
	 */
	public static void write(HttpServletResponse response, String str){
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new SystemException("write failed.", e);
		} 
	}
	
	/**
	 * Action: Ajax response. send the string to page. 
	 * @param response
	 * @param obj
	 */
	public static void writeXml(HttpServletResponse response, String str){
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new SystemException("write failed.", e);
		} 
	}
	
	/**
	 * Action: Ajax response. send the string to page. 
	 * @param response
	 * @param obj
	 */
	public static void writeZone(HttpServletResponse response, Object obj){
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
//		response.addHeader("Access-Control-Allow-Origin", "http://172.16.1.135:8080");
//		response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		//SysConfig.getConfig(SysEnum.UP_TARGET_URL)
		PrintWriter out;
		try {
			JSONObject info = JSONObject.fromObject(obj);
			out = response.getWriter();
			out.print(info);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new SystemException("write failed.", e);
		} catch (JSONException je){
			throw new SystemException("obj can't convert to json.", je);
		}
	}
	
	/**
	 * Action: Ajax response. send the string to page. 
	 * @param response
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	public static void write(HttpServletResponse response, APIResult rslt, Param param){
		//测试用，正式环境注释 rslt.setParam(param);
		write(response, rslt);
	}
	
	/**
	 * 跨域请求支持
	 * @param response
	 * @param rslt
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	public static void writeDomain(HttpServletResponse response, APIResult rslt, Param param){
		response.setHeader("Access-Control-Allow-Origin", "*");
		write(response, rslt, param);
	}
	
	/**
	 * 跨域请求支持
	 * @param response
	 * @param rslt
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	public static void writeJSONP(HttpServletResponse response, Result rslt){
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			JSONObject info = JSONObject.fromObject(rslt);
			out = response.getWriter();
			out.print("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><script>document.domain='" + "upbirds.com" + "';</script><div id='jsonData'>"+info+"</div>");
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new SystemException("write failed.", e);
		} catch (JSONException je){
			throw new SystemException("obj can't convert to json.", je);
		}
	}
}
