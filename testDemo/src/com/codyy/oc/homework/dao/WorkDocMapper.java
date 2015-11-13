package com.codyy.oc.homework.dao;

import java.util.List;

import com.codyy.oc.homework.entity.WorkDoc;

public interface WorkDocMapper {
    int deleteByPrimaryKey(String workDocId);

    int insert(WorkDoc record);

    int insertSelective(WorkDoc record);

    WorkDoc selectByPrimaryKey(String workDocId);

    int updateByPrimaryKeySelective(WorkDoc record);

    int updateByPrimaryKey(WorkDoc record);
    
    List<WorkDoc> selectByHomework(String workHomeworkId);
    
    void deleteByHomework(String workHomeworkId);
    
    int selectCountByHomework(String workHomeworkId);
}