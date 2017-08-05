package com.finger.birds.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OccTypeEnum {
	营销(1,"营销"),
	产品(2,"产品"),
	管理(4,"管理"),
	技术(8,"技术"),
	财务(16,"财务"),
	其他(0,"热门"),
	;
	
	private int val;
	private String name;
	
	private OccTypeEnum(int val, String name) {
		this.val = val;
		this.name = name;
	}

	public int getVal() {
		return val;
	}
	
	public static int getVal(String words) {
		int occType = 0;
		for(OccTypeEnum e : OccTypeEnum.values()){
			if(e.name.contains(words)){
				occType += e.val;
			}
		}
		return occType;
	}
	
	public static String getName(int val) {
		for(OccTypeEnum e : OccTypeEnum.values()){
			if(e.val == val){
				return e.name;
			}
		}
		return "";
	}
	
	public static List<Map<String, Object>> getAll(){
		List<Map<String, Object>> list = new ArrayList<>();
		for(OccTypeEnum e : OccTypeEnum.values()){
			HashMap<String, Object> map = new HashMap<>();
			map.put("name", e.name);
			map.put("occType", e.val);
			list.add(map);
		}
		return list;
	}
	
}
