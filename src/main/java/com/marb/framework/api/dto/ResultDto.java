package com.marb.framework.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDto<T> {

	private int total;
	private List<T> rows;

	public ResultDto(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public static ResultDto<?> empty() {
		return new Empty();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public static class Empty extends ResultDto<Void> {

		public Empty() {
			super(0, new ArrayList<>());
		}
	}
}
