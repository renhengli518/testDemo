package com.codyy.commons.utils;

import java.util.Map;

public class ResultJsonWithData extends ResultJson {

	public ResultJsonWithData(boolean result) {
		super(result);
	}
	
	public ResultJsonWithData(boolean result, String msg) {
		super(result, msg);
	}
	
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
