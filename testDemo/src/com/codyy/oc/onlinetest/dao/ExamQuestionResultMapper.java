package com.codyy.oc.onlinetest.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.onlinetest.entity.ExamQuestionResult;

public interface ExamQuestionResultMapper {
    int deleteByPrimaryKey(String examQuestionResultId);

    int insert(ExamQuestionResult record);

    int insertSelective(ExamQuestionResult record);

    ExamQuestionResult selectByPrimaryKey(String examQuestionResultId);

    int updateByPrimaryKeySelective(ExamQuestionResult record);

    int updateByPrimaryKeyWithBLOBs(ExamQuestionResult record);

    int updateByPrimaryKey(ExamQuestionResult record);

	List<ExamQuestionResult> getExamQuestionStatistics(Map<String, Object> map);

	List<ExamQuestionResult> getExamQuestionOtherStatics(Map<String, Object> map);

	List<ExamQuestionResult> getMultiChoiceStatistics(Map<String, Object> map);

	int insertExamQuestionResults(List<ExamQuestionResult> examQuestionResults);
	
	ExamQuestionResult getQuestionAnswer(Map<String, String> map);
	
	int noReviewStudentCountByQuestionId(Map<String, String> map);
	
	int updateTeaherCommentById(Map<String, Object> map);

	String getQuestionScore(String examResultId);
}