package org.yyz.helper;

import java.io.Serializable;
import java.util.List;

public class Pagenation<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private long total;
	private List<T> rows;
	private long page;
	private long tp;
	private long pagesize;
	
	public Pagenation(int page,int limit){
		this.page = page;
		this.pagesize = limit;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public long getTp() {
		if(this.pagesize<=0) return 0;
		this.tp = this.total/this.pagesize;
		if(this.total % this.pagesize>0) this.tp++;
		return this.tp;
	}
	public void setTp(int tp) {
		this.tp = tp;
	}
	public long getPagesize() {
		return pagesize;
	}
	public void setPagesize(long limit) {
		this.pagesize = limit;
	}
	
	

}
