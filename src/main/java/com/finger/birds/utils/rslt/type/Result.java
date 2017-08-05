package com.finger.birds.utils.rslt.type;

import com.finger.birds.utils.bean.PageBean;
import com.finger.birds.utils.rslt.code.ResultStatusCode;


/**
 * 普通返回值类型
 * 
 * @author danny
 *
 * @param <T>
 */
public class Result<T> {

	private Long startTime;
	private Long costTime;
	private String code;
	private String info;
	protected T data;
	private String havePage;
	private Integer curPage;
	private Integer count;
	private Integer pageSize;
	private Integer totalPage;

	public Result() {
		this.startTime = System.currentTimeMillis();
		this.code = ResultStatusCode.成功.getCode();
	}
	
	public Result(String info) {
		this.code = ResultStatusCode.成功.getCode();
		this.info = info;
	}
	public Result(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getHavePage() {
		return havePage;
	}

	public void setHavePage(String havePage) {
		this.havePage = havePage;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initRslt(PageBean page) {
		if (page.getTotalPage() != null && page.getTotalPage() > 0) {
			this.setTotalPage(page.getTotalPage());
		}
		this.setCurPage(page.getCurPage());
		this.setCount(page.getCount());
		this.setData((T) page.getData());
		this.setPageSize(page.getPageSize());
		if(page.getTotalPage() > page.getCurPage()){
			this.setHavePage(Boolean.TRUE.toString());
		}
		
	}
}
