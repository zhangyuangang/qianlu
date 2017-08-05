package com.finger.birds.model.intelligent;

import java.sql.Timestamp;

public class Weight {
	
	private Long id;
	private Long toId;
	private String toTable;
	private Double gw_yx;
	private Double gw_cp;
	private Double gw_gl;
	private Double gw_js;
	private Double gw_cw;
	private Double gw_qt;
	private Double hy_it;
	private Double hy_jr;
	private Double hy_ct;
	private Double hy_qt;
	private Integer cardinality;
	private Integer liveness;
	private Integer hotness;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public Weight() {
		super();
	}
	
	/**
	 * 初始化权重基数
	 * @param toId
	 * @param toTable
	 * @param cardinality
	 */
	public Weight(Long toId, String toTable, Integer cardinality) {
		super();
		this.toId = toId;
		this.toTable = toTable;
		this.gw_yx = 0.1666666667;
		this.gw_cp = 0.1666666667;
		this.gw_gl = 0.1666666667;
		this.gw_js = 0.1666666667;
		this.gw_cw = 0.1666666667;
		this.gw_qt = 0.1666666667;
		this.hy_it = 0.25;
		this.hy_jr = 0.25;
		this.hy_ct = 0.25;
		this.hy_qt = 0.25;
		this.cardinality = cardinality;
		this.liveness = 0;
		this.hotness = 0;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Double getGw_yx() {
		return gw_yx;
	}
	public void setGw_yx(Double gw_yx) {
		this.gw_yx = gw_yx;
	}
	public Double getGw_cp() {
		return gw_cp;
	}
	public void setGw_cp(Double gw_cp) {
		this.gw_cp = gw_cp;
	}
	public Double getGw_gl() {
		return gw_gl;
	}
	public void setGw_gl(Double gw_gl) {
		this.gw_gl = gw_gl;
	}
	public Double getGw_js() {
		return gw_js;
	}
	public void setGw_js(Double gw_js) {
		this.gw_js = gw_js;
	}
	public Double getGw_cw() {
		return gw_cw;
	}
	public void setGw_cw(Double gw_cw) {
		this.gw_cw = gw_cw;
	}
	public Double getGw_qt() {
		return gw_qt;
	}
	public void setGw_qt(Double gw_qt) {
		this.gw_qt = gw_qt;
	}
	public Double getHy_it() {
		return hy_it;
	}
	public void setHy_it(Double hy_it) {
		this.hy_it = hy_it;
	}
	public Double getHy_jr() {
		return hy_jr;
	}
	public void setHy_jr(Double hy_jr) {
		this.hy_jr = hy_jr;
	}
	public Double getHy_ct() {
		return hy_ct;
	}
	public void setHy_ct(Double hy_ct) {
		this.hy_ct = hy_ct;
	}
	public Double getHy_qt() {
		return hy_qt;
	}
	public void setHy_qt(Double hy_qt) {
		this.hy_qt = hy_qt;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getLiveness() {
		return liveness;
	}

	public void setLiveness(Integer liveness) {
		this.liveness = liveness;
	}

	public Integer getCardinality() {
		return cardinality;
	}

	public void setCardinality(Integer cardinality) {
		this.cardinality = cardinality;
	}

	public Integer getHotness() {
		return hotness;
	}

	public void setHotness(Integer hotness) {
		this.hotness = hotness;
	}
	
}
