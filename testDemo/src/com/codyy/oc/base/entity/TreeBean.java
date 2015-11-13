package com.codyy.oc.base.entity;

import java.util.List;

/**
 * 构建树
 * @author codyy
 *
 */
public class TreeBean {
	private String id;
	
	private String name;
	
	private String parentId;
	
	private List<TreeBean> children;

	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public List<TreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}
	
	
}
