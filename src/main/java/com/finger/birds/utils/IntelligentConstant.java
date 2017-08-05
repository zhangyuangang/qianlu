package com.finger.birds.utils;

public interface IntelligentConstant {
	
	//大白初始化权重基数
	Integer Cardinality_Init = 200;
	
	//岗位：营销，产品，管理，技术，财务，其他（存数字代号）
	//行业：IT互联网、金融快消、传统行业、其他（直接存）
	/** 搜索标签，toTable=toTable,search,岗位,行业*/
	String ToTable_Search = "toTable,search,";
	
	String ToTable_Liveness = "toTable,liveness";
	
	
	//前路头条不同内容对应的权重值 
	
	//发布文章回复：20，发布文章被点赞：10
	/** 发布文章回复 */
	Integer Qianlu_Self_Comment = 20;  //toId = 5
	/** 发布文章被点赞*/
	Integer Qianlu_Self_Star = 10;
	
	//评论被点赞：6，评论的文章有回复：4，被艾特：40
	/** 评论被点赞  */
	Integer Qianlu_Comment_Star = 6;  //toId = 6
	/** 评论的文章有回复 */
	Integer Qianlu_Comment_Comment = 4;
	/** 被艾特 */
	Integer Qianlu_Ai_Te = 40;

	
	/** 新增前路新闻：30 */
	Integer Qianlu_News = 30;    //toId = 1  定时任务UpdateQianlu类需要判断
	
	//新增大白：60   0.2-0.7（12 - 42）
	/** 新增大白  */
	Integer Qianlu_Add_Big_User = 60;   //toId = 2

	//新增长文：40   0.2-0.7（8 - 28） 
	/** 新增长文  */
	Integer Qianlu_Add_Article = 40;   //toId = 3
	
	//新增随笔：20   0.2-0.7（4 - 14） 
	/** 新增随笔  */
	Integer Qianlu_Add_Dynamic = 40;    //toId = 4
	
}
