package com.finger.birds.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.finger.birds.utils.CommConstant;

public enum TimeTypeEnum {

	TIME1((short)1,   10*60- 5, (10*60 + 25), "10:00 - 10:25"),
	TIME2((short)2,   10*60+25, (10*60 + 55), "10:30 - 10:55"),
	TIME3((short)3,   19*60- 5, (19*60 + 25), "19:00 - 19:25"),
	TIME4((short)4,   19*60+25, (19*60 + 55), "19:30 - 19:55"),
	TIME5((short)5,   20*60- 5, (20*60 + 25), "20:00 - 20:25"),
	TIME6((short)6,   20*60+25, (20*60 + 55), "20:30 - 20:55"),
	TIME7((short)7,   21*60- 5, (21*60 + 25), "21:00 - 21:25"),
	TIME8((short)8,   21*60+25, (21*60 + 55), "21:30 - 21:55"),
	TIME9((short)9,   22*60- 5, (22*60 + 25), "22:00 - 22:25"),
	TIME10((short)10, 22*60+25, (22*60 + 55), "22:30 - 22:55"),
	TIME11((short)11, 23*60- 5, (23*60 + 25), "23:00 - 23:25"),
	TIME12((short)12, 23*60+25, (23*60 + 55), "23:30 - 23:55");
	
	public static Date getLastEndTime(){
		Date date = new Date();
		date.setHours(23);
		date.setMinutes(30);
		date.setSeconds(0);
		return date;
	}

	private short val;

	private int start;

	private int end;
	
	private String name;

	private TimeTypeEnum(short val, int start, int end, String name) {
		this.val = val;
		this.start = start;
		this.end = end;
		this.name = name;
	}

	public short getVal() {
		return val;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	public String getName() {
		return name;
	}

	public boolean check(int now, short xudan){
		int extMin = xudan*CommConstant.xudanTime;
		if((this.start+extMin) <= now && (this.end+extMin) >= now){
			return true;
		}
		return false;
	}
	
	public static TimeTypeEnum get(short val){
		for(TimeTypeEnum e : TimeTypeEnum.values()){
			if(val == e.val){
				return e;
			}
		}
		return null;
	}
	
	public static List<TimeTypeEnum> getAll(){
		List<TimeTypeEnum> list = new LinkedList<>();
		for(TimeTypeEnum e : TimeTypeEnum.values()){
			list.add(e);
		}
		return list;
	}

	public static List<Short> getHasPassTimeType(Integer now){
		List<Short> list = new LinkedList<>();
		if(now == null){
			return list;
		}
		for(TimeTypeEnum e : TimeTypeEnum.values()){
			if(e.start+5 < now){
				list.add(e.val);
			}
		}
		return list;
	}
	
}
