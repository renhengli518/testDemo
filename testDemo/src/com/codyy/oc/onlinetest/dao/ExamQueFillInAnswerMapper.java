package com.codyy.oc.onlinetest.dao;

import java.util.List;

import com.codyy.oc.onlinetest.entity.ExamQueFillInAnswer;

public interface ExamQueFillInAnswerMapper {
    int deleteByPrimaryKey(String examQueFillInAnswerId);

    int insert(ExamQueFillInAnswer record);

    int insertSelective(ExamQueFillInAnswer record);

    ExamQueFillInAnswer selectByPrimaryKey(String examQueFillInAnswerId);

    int updateByPrimaryKeySelective(ExamQueFillInAnswer record);

    int updateByPrimaryKeyWithBLOBs(ExamQueFillInAnswer record);

    int updateByPrimaryKey(ExamQueFillInAnswer record);
    
    int deleteFillInAnswerByExamQuestionId(String examQuestionId);

	List<ExamQueFillInAnswer> getQueFillInAnswerByQuestionId(String examQuestionId);

	int insertFillAnswerBatch(List<ExamQueFillInAnswer> examPracticeFillAnswers);

	List<ExamQueFillInAnswer> getPracticeFillInAnswer(String examPracticeQuestionId);
}