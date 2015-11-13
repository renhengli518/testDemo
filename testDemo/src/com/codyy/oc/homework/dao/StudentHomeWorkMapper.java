package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.commons.page.Page;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.ReceiveStu;
import com.codyy.oc.homework.entity.WorkReceiveStu;

public interface StudentHomeWorkMapper {
	//根据学生空间下的学生的作业ID查看作业
	public HomeWorkQuestionInfo findStudentHomeWorkQuestionInfo(String workId);
	//查询习题（没有提交）
	public List<QuestionInfo> getStuQueInfo(Map<String,Object> map);
	//查询习题（已经提交）
	public List<QuestionInfo> getStuSubmitQueInfo(Map<String,Object> map);
	public String getWorkReceiveStuTextAnswer(Map<String,Object> map);
	//查询出学生空间--我的作业--作业列表	
	public List<HomeWork> findStuHomeworkByConditionsPageList(Page page);
	//查询出学生空间--我的批阅--作业列表	
	public List<HomeWork> findReadOverHomeworkByConditionsPageList(Page page);
	//查看批阅--批阅列表
	public List<ReceiveStu> getQueryReadOverListPageList(Page page);
	//批阅--批阅列表
	public List<ReceiveStu> getReadOverListPageList(Page page);
}
