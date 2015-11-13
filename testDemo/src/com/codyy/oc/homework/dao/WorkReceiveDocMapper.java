package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.homework.entity.WorkReceiveDoc;

public interface WorkReceiveDocMapper {
    int deleteByPrimaryKey(String workDocId);

    int insert(WorkReceiveDoc record);

    int insertSelective(WorkReceiveDoc record);

    WorkReceiveDoc selectByPrimaryKey(String workDocId);

    int updateByPrimaryKeySelective(WorkReceiveDoc record);

    int updateByPrimaryKey(WorkReceiveDoc record);
    //查询学生附件的回答
    List<WorkReceiveDoc> findWorkReceiveDoc(Map<String,Object> map);
    //根据家长ID查询出其孩子附件的回答
    List<WorkReceiveDoc> findParentWorkReceiveDoc (Map<String,Object> map);
    
    List<WorkReceiveDoc> selectByReceiveStu(String workReceiveStuId);
}