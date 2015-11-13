package com.codyy.oc.onlinetest.dao;

import java.util.List;

import com.codyy.oc.onlinetest.entity.ExamQuestionRKnowledge;

public interface ExamQuestionRKnowledgeMapper {
    int deleteByPrimaryKey(String examQuestionRKnowledgeId);

    int insert(ExamQuestionRKnowledge record);

    int insertSelective(ExamQuestionRKnowledge record);

    ExamQuestionRKnowledge selectByPrimaryKey(String examQuestionRKnowledgeId);

    int updateByPrimaryKeySelective(ExamQuestionRKnowledge record);

    int updateByPrimaryKey(ExamQuestionRKnowledge record);
    
    List<ExamQuestionRKnowledge> selectKonwledgesByExamQuestionId(String examQuestionId);
    
    int deleteKnowListByExamQuestionId(String examQuestionId);

	int insertKnowledgeBatch(List<ExamQuestionRKnowledge> examPracticeKnowledge);
}