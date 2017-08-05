package com.finger.birds.utils.param;

import com.finger.birds.utils.bean.PageBean;

public class PageParam<T> extends Param {

	private int isPaging; // 0 不分页；1 分页
	private int pageSize; // 每页显示的条数
	private int curPage; // 页码

	public int getIsPaging() {
		return isPaging;
	}

	public void setIsPaging(int isPaging) {
		this.isPaging = isPaging;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public PageBean<T> initPage(){
		if(this.isPaging == 1){
			return new PageBean<T>(pageSize, curPage);
		}else {
			return new PageBean<T>();
		}
	}
}
