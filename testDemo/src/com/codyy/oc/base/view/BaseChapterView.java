package com.codyy.oc.base.view;

import com.codyy.oc.base.entity.BaseChapter;


public class BaseChapterView extends BaseChapter {
	
	private boolean hasSectionChild;

	public boolean isHasSectionChild() {
		return hasSectionChild;
	}

	public void setHasSectionChild(boolean hasSectionChild) {
		this.hasSectionChild = hasSectionChild;
	}

}
