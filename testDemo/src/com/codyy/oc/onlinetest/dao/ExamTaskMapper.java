package com.codyy.oc.onlinetest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.codyy.commons.page.Page;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.entity.ExamQuestionStatistics;
import com.codyy.oc.onlinetest.entity.ExamResult;
import com.codyy.oc.onlinetest.entity.ExamStudentStatistic;
import com.codyy.oc.onlinetest.entity.ExamTask;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;
import com.codyy.oc.onlinetest.view.ExamTaskView;

public interface ExamTaskMapper {
    int deleteByPrimaryKey(String examTaskId);

    int insert(ExamTask record);

    int insertSelective(ExamTask record);

    ExamTask selectByPrimaryKey(String examTaskId);

    int updateByPrimaryKeySelective(ExamTask record);

    int updateByPrimaryKey(ExamTask record);

	List<ExamTaskView> getClassExamPageList(Page page);

	List<SelectModel> getAllExamTypes();

	ExamTaskView getClassExamById(String examTaskId);

	List<ExamTaskStatistics> getExamStaticsByClass(String examTaskId);

	ExamTaskStatistics getExamAllStatics(String examTaskId);

	List<ExamQuestionStatistics> getExamRightStatisByClass(Map<String, Object> map);

	List<ExamStudentStatistic> getStudentStatisList(Map<String, Object> map);

	ExamResult getExamResultByUserId(@Param("examTaskId") String examTaskId, @Param("baseUserId") String baseUserId);

	List<ExamTaskView> getTeacherAssignPageList(Page page);

	List<ExamTaskView> getClassExamTaskPageList(Page page);
	
	//将examquestion表中的相关题目复制到EXAM_TASK_R_EXAM_QUESTION关系表中
	void copyTaskQuestion(@Param("examId") String examId,@Param("examTaskId") String examTaskId);

	List<ExamTaskView> getStudentTaskPageList(Page page);

	String getExamTypeIdByName(String name);

	ExamTaskView getStudentTaskView(Map<String, Object> map);

	ExamTaskStatistics getClassExamStaticsByStudent(Map<String, Object> map);

	ExamResult getStudentExamResult(Map<String, Object> map);

	ExamResult checkPracticeExam(Map<String, Object> map);

	List<ExamTaskView> getClsClassExamPageList(Page page);

	ExamTaskView getClsClassExamTask(Map<String, Object> map);

	ExamTaskView getClsClassExamTaskById(String examTaskId);

	void updateExamTaskCount(ExamTask etTask);

	
}