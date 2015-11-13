package com.codyy.oc.onlinetest.dao;

import java.util.List;

import com.codyy.oc.onlinetest.entity.ExamQuestionRChapter;

public interface ExamQuestionRChapterMapper {
    int deleteByPrimaryKey(String examQuestionRChapterId);

    int insert(ExamQuestionRChapter record);

    int insertSelective(ExamQuestionRChapter record);

    ExamQuestionRChapter selectByPrimaryKey(String examQuestionRChapterId);

    int updateByPrimaryKeySelective(ExamQuestionRChapter record);

    int updateByPrimaryKey(ExamQuestionRChapter record);
    
    int deleteExamQuesChapterByExamQuestionId(String examQuestionId);
    
    List<ExamQuestionRChapter>selectByExamQuestionId(String examQuestionId);
}