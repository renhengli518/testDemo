package com.codyy.oc.onlinetest.dao;

import java.util.List;

import com.codyy.oc.onlinetest.entity.ExamPracticeQuestion;

public interface ExamPracticeQuestionMapper {
    int deleteByPrimaryKey(String examPracticeQuestionId);

    int insert(ExamPracticeQuestion record);

    int insertSelective(ExamPracticeQuestion record);

    ExamPracticeQuestion selectByPrimaryKey(String examPracticeQuestionId);

    int updateByPrimaryKeySelective(ExamPracticeQuestion record);

    int updateByPrimaryKeyWithBLOBs(ExamPracticeQuestion record);

    int updateByPrimaryKey(ExamPracticeQuestion record);

	int insertBatch(List<ExamPracticeQuestion> examPracticeQuestions);

	List<ExamPracticeQuestion> getExamPracticeQuestion(String examPracticeId);

	int updateExamPracticeAnswer(List<ExamPracticeQuestion> examPracticeQuestions);

	List<ExamPracticeQuestion> getQuestionTypeByPracticeId(String examPracticeId);
}