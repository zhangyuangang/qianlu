package com.finger.birds.utils.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class HttpClientUtil {
	private Logger log = Logger.getLogger(this.getClass());

	private static HttpClientUtil httpClientUtil = new HttpClientUtil();
	
	public static HttpClientUtil getInstance(){//XXX 浪费
		return httpClientUtil;
	}
	
	/**
	 * 以post请求发送http数据
	 * 
	 * @param url
	 *            数据提交至的url
	 * @param param
	 *            表单参数,以map方式传入
	 * @param chatset
	 *            表单提交的编码格式
	 * @return 返回信息，以字符串格式返回
	 */
	public String httpPostData(String url, Map<String, String> param, String chatset) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> formParams = initFormParams(param);
			UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(formParams, chatset);
			httpPost.setEntity(urlEntity);
			CloseableHttpResponse response;
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, chatset);
			}
		} catch (Exception e) {
			log.error(e);
			throw new HttpClientException(e.getMessage());
		}
		return result;
	}
	
	public String httpPostJSONData(String url, JSONObject json, String chatset) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json");
			StringEntity s = new StringEntity(json.toString(), "utf-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(s);
			CloseableHttpResponse response;
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, chatset);
			}
		} catch (Exception e) {
			log.error(e);
			throw new HttpClientException(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 以get请求发送http数据
	 * 
	 * @param url
	 *            数据提交至的url
	 * @return 返回信息，以字符串格式返回
	 */
	public String httpGetData(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response;
		String result = null;
		try {
			response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			log.error(e);
			throw new HttpClientException(e.getMessage());
		}

		return result;
	}

	/**
	 * 在post请求模式下，构建form表单参数实例
	 * 
	 * @param map
	 *            form表单参数map，key对应表单参数name，value对应表单参数value
	 * @return List<NameValuePair>,HttpClient post请求form表单对象
	 */
	private List<NameValuePair> initFormParams(Map<String, String> map) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			formParams.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		return formParams;
	}

	@SuppressWarnings("unused")
	private String parseResult(String result) {
		result = "000/Send:2/Consumption:.14/Tmoney:999.61/sid:0526162233103306";
		String[] s = result.split("/");
		String str = s[0];
		if ("000".equals(str)) {
			System.out.println("短信发送成功，回执信息:" + result);
		}
		return null;
	}
	

	public static void main(String[] args) {
//		HttpClientUtil hc = new HttpClientUtil();
//		System.out.println(hc.httpGetData("https://note.sdo.com/my&type=email&target=top&email=200592114%40qq.com&password=zx1992&submit_type=ajax"));
	}
}
