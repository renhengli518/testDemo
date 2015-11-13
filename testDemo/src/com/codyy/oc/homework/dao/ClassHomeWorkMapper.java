package com.codyy.oc.homework.dao;

import java.util.List;
import com.codyy.commons.page.Page;
import com.codyy.oc.homework.entity.HomeWork;

public interface ClassHomeWorkMapper {
	//查询出班级空间下的作业列表	
	public List<HomeWork> findClassHomeworkByConditionsPageList(Page page);
}
