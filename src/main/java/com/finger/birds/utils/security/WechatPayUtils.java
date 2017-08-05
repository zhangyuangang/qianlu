package com.finger.birds.utils.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.exception.business.BusinessException;

public class WechatPayUtils {

	public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
	
	public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("timeStamp")|| key.equalsIgnoreCase("goodsId")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
	
	public static String postUnifiedOrder(String urlStr, String param) {  
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		CloseableHttpResponse response;
		try {
			HttpPost httpPost = new HttpPost(urlStr);
			StringEntity entity = new StringEntity(param, "UTF-8");
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			HttpEntity rspentity = response.getEntity();
			if (rspentity != null) {
				result = EntityUtils.toString(rspentity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} 
		return result;
	}  
	
	public static String postSSLRefund(String urlStr, String param) throws Exception{
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(CommConstant.KEY_CERT_LOCATION));
        String result = "";
        try {
            keyStore.load(instream, CommConstant.WECHAT_PAY_MCH_ID.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, CommConstant.WECHAT_PAY_MCH_ID.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
        		sslcontext,
        		new String[] { "TLSv1" },
        		null,
        		SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
        		.setSSLSocketFactory(sslsf)
        		.build();
        
        try {
        	HttpPost httpPost = new HttpPost(urlStr);
			StringEntity reqEntity = new StringEntity(param, "UTF-8");
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                    	result += text;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
		return result;
	}
  
    public static String getXmlInfo(Map<String,String> map, String sign) {  
    	 List<String> keys = new ArrayList<String>(map.keySet());
    	 Collections.sort(keys);
        StringBuilder sb = new StringBuilder(); 
        sb.append("<xml encoding=\"UTF-8\">");
        for (int i = 0; i < keys.size(); i++) {
        	sb.append("<" + keys.get(i) + ">").append("<![CDATA[" + map.get(keys.get(i)) + "]]>").append("</" + keys.get(i) + ">");
        }
        sb.append("<sign><![CDATA[" + sign + "]]></sign>");
        sb.append("</xml>");
System.out.println(sb.toString());
        return sb.toString();  
    }  
  
    /**
     * 单层
     * @param xml
     * @return
     * @throws DocumentException 
     */
    public static Map<String, String> parseXml(String xml) {
    	Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			System.out.println(xml);
			//TODO
			e1.printStackTrace();
		}
    	Map<String, String> map = new HashMap<String, String>(); 
        if(doc == null) 
            return map; 
        Element root = doc.getRootElement(); 
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) { 
            Element e = (Element) iterator.next(); 
            List list = e.elements(); 
            if(list.size() > 0){ 
             //   map.put(e.getName(), Dom2Map(e)); 
            }else 
                map.put(e.getName(), e.getText()); 
        } 
        return map; 
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String str = "<xml encoding=\"UTF-8\"><appid><![CDATA[wx1b18303dfee56e12]]></appid><body><![CDATA[测试数据_请勿拍_2015冬季新款红石榴耳环套装]]></body><mch_id><![CDATA[1302327401]]></mch_id><nonce_str><![CDATA[wqxzqxqzxz3mw7qqqqqxmx]]></nonce_str><notify_url><![CDATA[http://m.zhubaoshi.cn/pay/WechatASynchroCallBack.html]]></notify_url><out_trade_no><![CDATA[2016010616382900000181]]></out_trade_no><spbill_create_ip><![CDATA[127.0.0.1]]></spbill_create_ip><time_start><![CDATA[20160106163830]]></time_start><total_fee><![CDATA[1]]></total_fee><trade_type><![CDATA[APP]]></trade_type><sign><![CDATA[61A8E5E565DAFAB45DB6C4933999DEBC]]></sign></xml>";
    	System.out.println(postUnifiedOrder("https://api.mch.weixin.qq.com/pay/unifiedorder", str));
    }
    
    
}
