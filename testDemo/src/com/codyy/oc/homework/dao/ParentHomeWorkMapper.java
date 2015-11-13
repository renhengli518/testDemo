package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.commons.page.Page;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;

public interface ParentHomeWorkMapper {
//查询出家长空间下的作业列表	
public List<HomeWork> findParentHomeworkByConditionsPageList(Page page);
//根据家长空间下的作业ID查看作业
public HomeWorkQuestionInfo findParentHomeWorkQuestionInfo(String workId);
//查询习题(已经提交作业的习题)
public List<QuestionInfo> getParentQueInfo(Map<String,Object> map);
//查询习题(未提交作业的习题)
public List<QuestionInfo> getParentQueProgressInfo(String workId);
}
