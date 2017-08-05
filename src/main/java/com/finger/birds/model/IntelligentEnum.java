package com.finger.birds.model;

public enum IntelligentEnum {
	
	//只增加活跃度的
	Liveness修改个人信息(10001, 40),
	Liveness取消Article点赞(10002, 10),
	Liveness取消Article评论_点赞(10003, 5),
	Liveness取消Dynamic点赞(10004, 5),
	
	//加活跃度，权重值的
	WeightBanner点击(20001, 5),
	Weight标签搜索(20002, 5),
	Weight分享主页(20003, 40),
	Weight分享大白(20004, 40),
	Weight分享文章(20005, 40),
	
	//加活跃度，被关注度，权重值的
	//User：进主页+2，下订单+40，续单+40
	User进主页(30101, 2),
	User下订单(30102, 40),
	User续单(30103, 40),
	
	//Dynamic：查看全文+5，点赞+5，评论一次+20
	Dynamic查看全文(30201, 5),
	Dynamic点赞(30202, 5),
	Dynamic评论一次(30203, 20),
	
	//Article：查看全文+5，点赞+5，评论一次+20,评论点赞
	Article查看详情(30301, 5),
	Article点赞(30302, 10),
	Article评论一次(30303, 20),
	Article评论点赞(30304, 5);
	
	private IntelligentEnum(int type, int score) {
		this.type = type;
		this.score = score;
	}

	private int type;
	
	private int score;

	public int getType() {
		return type;
	}

	public int getScore() {
		return score;
	}
	
	public static Integer getScore(int type){
		for(IntelligentEnum e : IntelligentEnum.values()){
			if(type == e.type){
				return e.score;
			}
		}
		return 0;
	}
	
	
	public static IntelligentEnum get(Integer type){
		for(IntelligentEnum e : IntelligentEnum.values()){
			if(type == e.type){
				return e;
			}
		}
		return null;
	}
}
