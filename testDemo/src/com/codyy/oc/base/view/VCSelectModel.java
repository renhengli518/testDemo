package com.codyy.oc.base.view;

import java.util.List;

/**
 * 分册章节查询对象（智能组卷用）
 * @author yaodaqing
 *
 */
public class VCSelectModel {
	
	private String id;
	private String name;
	
	private List<VCSelectModel> list;

	public List<VCSelectModel> getList() {
		return list;
	}

	public void setList(List<VCSelectModel> list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
