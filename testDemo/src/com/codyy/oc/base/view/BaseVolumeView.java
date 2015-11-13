package com.codyy.oc.base.view;

import com.codyy.oc.base.entity.BaseVolume;


public class BaseVolumeView extends BaseVolume {

	private boolean hasChapterChild;

	public boolean isHasChapterChild() {
		return hasChapterChild;
	}

	public void setHasChapterChild(boolean hasChapterChild) {
		this.hasChapterChild = hasChapterChild;
	}
}
