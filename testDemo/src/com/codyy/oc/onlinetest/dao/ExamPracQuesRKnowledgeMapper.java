package com.codyy.oc.onlinetest.dao;

import java.util.List;

import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.onlinetest.entity.ExamPracQuesRKnowledge;

public interface ExamPracQuesRKnowledgeMapper {
    int insert(ExamPracQuesRKnowledge record);

    int insertSelective(ExamPracQuesRKnowledge record);

	int insertPracKnowlegeBatch(List<ExamPracQuesRKnowledge> ePracQuesRKnowledges);

	List<BaseKnowledge> getAllBaseKnowledge(String examPracticeQuestionId);
}