package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.homework.entity.WorkRecStuQueAnswer;
import com.codyy.oc.homework.view.ReadByQueAnswerView;
import com.codyy.oc.homework.view.ReadByStuQuestionView;

public interface WorkRecStuQueAnswerMapper {
    int deleteByPrimaryKey(String workRecStuQueAnswerId);

    int insert(WorkRecStuQueAnswer record);

    int insertSelective(WorkRecStuQueAnswer record);

    WorkRecStuQueAnswer selectByPrimaryKey(String workRecStuQueAnswerId);

    int updateByPrimaryKeySelective(WorkRecStuQueAnswer record);

    int updateByPrimaryKeyWithBLOBs(WorkRecStuQueAnswer record);

    int updateByPrimaryKey(WorkRecStuQueAnswer record);

    void insertBatch(List<WorkRecStuQueAnswer> list);
    
    int selectQueUncheckedNum(Map<String, String> map);
    
    ReadByQueAnswerView selectQuestionAnswer(Map<String, String> map);
    
    int selectQuestionNoReadNum(Map<String, String> map);
    
    List<ReadByStuQuestionView> selectReadByStuQuestion(String workReceiveStuId);
    
    void updateBystuIdAndQueId(Map<String,String> map);

}