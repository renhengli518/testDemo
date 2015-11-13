package com.codyy.oc.base.view;

import com.codyy.oc.base.entity.BaseKnowledge;

public class BaseKnowledgeView extends BaseKnowledge{

	private boolean hasKnowledgeChild;

	public boolean isHasKnowledgeChild() {
		return hasKnowledgeChild;
	}

	public void setHasKnowledgeChild(boolean hasKnowledgeChild) {
		this.hasKnowledgeChild = hasKnowledgeChild;
	}
}
