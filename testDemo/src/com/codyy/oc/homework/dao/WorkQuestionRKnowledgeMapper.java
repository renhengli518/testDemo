package com.codyy.oc.homework.dao;

import com.codyy.oc.homework.entity.WorkQuestionRKnowledge;

public interface WorkQuestionRKnowledgeMapper {
    int deleteByPrimaryKey(String workQuestionRKnowledgeId);

    int insert(WorkQuestionRKnowledge record);

    int insertSelective(WorkQuestionRKnowledge record);

    WorkQuestionRKnowledge selectByPrimaryKey(String workQuestionRKnowledgeId);

    int updateByPrimaryKeySelective(WorkQuestionRKnowledge record);

    int updateByPrimaryKey(WorkQuestionRKnowledge record);
}