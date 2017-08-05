package com.finger.birds.utils.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final Long ONE_HOUR = 1000*60*60l;
	
	public static final Long ONE_DAY = 1000*60*60*24l;
	
	public static final int ONE_MOUTH = 31;
	
	public static final int ONE_YEAR = 366;
	
	public static String getTodayDay(){
		Calendar cal=Calendar.getInstance();
		String mouth = (cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE)) > 9 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));
		return mouth + day;
	}
	
	public static String getTodayDayWithYear(){
		Calendar cal=Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String mouth = (cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE)) > 9 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));
		return year + mouth + day;
	}
	
	public static String getTodayDayWithYear2(){
		Calendar cal=Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String mouth = (cal.get(Calendar.MONTH) + 1) > 9 ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE)) > 9 ? (cal.get(Calendar.DATE)) + "" : "0" + (cal.get(Calendar.DATE));
		return year + "-" + mouth + "-" + day;
	}

	public static String getNow(){
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date().getTime());
	}
	
	public static String getDay(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date().getTime());
	}
	
	public static String getNow(String format){
		return new SimpleDateFormat(format).format(new Date().getTime());
	}
	
	public static String getNow24(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime());
	}
	
	public static String format(String format, Date date){
		if(date == null){
			return "";
		}
		return new SimpleDateFormat(format).format(date);
	}
	
	public static String format(String format, Timestamp date){
		if(date == null){
			return "";
		}
		return new SimpleDateFormat(format).format(new Date(date.getTime()));
	}
	
	public static String dealTimeToString(Timestamp time){
		Long t = System.currentTimeMillis() - time.getTime();

		if(t <= ONE_HOUR){
			return "刚刚";
		}
		if(t > ONE_HOUR && t <= ONE_DAY){
			return new SimpleDateFormat("HH:mm:ss").format(time);
		}
		if(t/ONE_DAY <= 30){
			return t/ONE_DAY + "天前";
		}
		if(t/ONE_DAY > 30 && t/ONE_DAY <= ONE_YEAR){
			return (t/ONE_DAY)/ONE_MOUTH + "个月前";
		}
		if(t/ONE_DAY > 365){
			return t/ONE_DAY/365 + "年前";
		}
		return "很久以前";
	}
	
	public static String dealTimeToStringForDynamic(Timestamp time){
		Long now = System.currentTimeMillis();
		Long t = now - time.getTime();
		long d = t/ONE_DAY;
		if(d == 0){
			int nowDate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			int tDate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date(time.getTime())));
			if(nowDate > tDate){
				d = 1;
			}
		}
		if(t <= ONE_HOUR){
			return "刚刚";
		}
		if(t > ONE_HOUR && t <= ONE_DAY && d == 0){
			return "今天 " + new SimpleDateFormat("HH:mm").format(time);
		}
		if(t/ONE_DAY <= 7){
			return d + "天前 " + new SimpleDateFormat("HH:mm").format(time);
		}
		if(t/ONE_DAY > 7 && t/ONE_DAY <= ONE_YEAR){
			return new SimpleDateFormat("MM月dd日  HH:mm").format(time);
		}
		if(t/ONE_DAY > 365){
			return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(time);
		}
		return "很久以前";
	}
	
	public static Date strToDate(String format, String str){
		SimpleDateFormat sdf =   new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return date;
	}
	
	public static Timestamp strToTimestamp(String format, String str){
		SimpleDateFormat sdf =   new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return date == null ? null : new Timestamp(date.getTime());
	}
	
	/**
	 * 获取一天种得第几分钟
	 * @return
	 */
	public static int getMinutes(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
	}
}
