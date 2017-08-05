package com.finger.birds.model.intelligent;

import java.sql.Timestamp;

import com.finger.birds.utils.IntelligentConstant;

public class Count {
	
	private Long id;
	private Long userId;  //对应用户加活跃度，计算权重
	private Long toUserId;   //对应用户加热度，发通知
	private Long toUserId2;   //对应用户加热度，被艾特用户，发通知
	private Long toId;   //用于计算权重
	private String toTable;   //用于计算权重
	private Integer score;   //用于计算权重
	private Timestamp createTime;
	
	public Count() {
		super();
	}

	/**
	 * 记录增加活跃度，权重计算的
	 * @param userId
	 * @param toId
	 * @param toTable
	 * @param score
	 */
	public Count(Long userId, Long toId, String toTable, Integer score) {
		super();
		this.userId = userId;
		this.toId = toId;
		this.toTable = toTable;
		this.score = score;
	}
	
	/**
	 * 记录增加活跃度，被关注度，权重计算的
	 * @param userId
	 * @param toUserId
	 * @param toId
	 * @param toTable
	 * @param score
	 */
	public Count(Long userId, Long toUserId, Long toId, String toTable, Integer score) {
		super();
		this.userId = userId;
		this.toUserId = toUserId;
		this.toId = toId;
		this.toTable = toTable;
		this.score = score;
	}

	/**
	 * 记录增加活跃度，被关注度，权重计算的（有艾特）
	 * @param userId
	 * @param toUserId
	 * @param toUserId2
	 * @param toId
	 * @param toTable
	 * @param score
	 */
	public Count(Long userId, Long toUserId, Long toUserId2, Long toId, String toTable, Integer score) {
		super();
		this.userId = userId;
		this.toUserId = toUserId;
		this.toUserId2 = toUserId2;
		this.toId = toId;
		this.toTable = toTable;
		this.score = score;
	}

	/**
	 * 记录搜索等特殊操作的：权重表没有记录的
	 * @param userId
	 * @param toTable
	 * @param score
	 */
	public Count(Long userId, String toTable, Integer score) {
		super();
		this.userId = userId;
		this.toTable = toTable;
		this.score = score;
	}

	/**
	 * 记录增加活跃度，和被关注度的
	 * @param userId
	 * @param score
	 */
	public Count(Long userId, Long toUserId, Integer score) {
		super();
		this.userId = userId;
		this.toUserId = toUserId;
		this.toTable = IntelligentConstant.ToTable_Liveness;
		this.score = score;
	}
	
	/**
	 * 记录增加活跃度，和被关注度的
	 * @param userId
	 * @param score
	 */
	public Count(Long userId, Long toUserId, Long toUserId2, Integer score) {
		super();
		this.userId = userId;
		this.toUserId = toUserId;
		this.toUserId2 = toUserId2;
		this.toTable = IntelligentConstant.ToTable_Liveness;
		this.score = score;
	}
	
	/**
	 * 记录只增加活跃度的
	 * @param userId
	 * @param score
	 */
	public Count(Long userId, Integer score) {
		super();
		this.userId = userId;
		this.toTable = IntelligentConstant.ToTable_Liveness;
		this.score = score;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Long getToUserId2() {
		return toUserId2;
	}

	public void setToUserId2(Long toUserId2) {
		this.toUserId2 = toUserId2;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
	}
	public String getToTable() {
		return toTable;
	}
	public void setToTable(String toTable) {
		this.toTable = toTable;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
