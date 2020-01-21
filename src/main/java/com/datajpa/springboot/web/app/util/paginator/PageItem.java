package com.datajpa.springboot.web.app.util.paginator;

public class PageItem {

	private int num;
	private boolean current;

	public PageItem(int num, boolean current) {
		super();
		this.num = num;
		this.current = current;
	}

	public int getNum() {
		return num;
	}

	public boolean isCurrent() {
		return current;
	}

}
