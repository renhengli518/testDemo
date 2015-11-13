package com.codyy.oc.homework.dao;

import java.util.List;

import com.codyy.oc.homework.entity.WorkQueFillInAnswer;

public interface WorkQueFillInAnswerMapper {
    int deleteByPrimaryKey(String workQueFillInAnswerId);

    int insert(WorkQueFillInAnswer record);

    int insertSelective(WorkQueFillInAnswer record);

    WorkQueFillInAnswer selectByPrimaryKey(String workQueFillInAnswerId);

    int updateByPrimaryKeySelective(WorkQueFillInAnswer record);

    int updateByPrimaryKeyWithBLOBs(WorkQueFillInAnswer record);

    int updateByPrimaryKey(WorkQueFillInAnswer record);
    
    List<WorkQueFillInAnswer> selectByWorkQuestion(String workQuestionId);
}