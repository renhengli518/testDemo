package com.codyy.oc.homework.dao;

import java.util.List;

import com.codyy.oc.base.view.StudentClassView;
import com.codyy.oc.homework.entity.WorkReadOverStu;

public interface WorkReadOverStuMapper {
    int deleteByPrimaryKey(String workReadOverStuId);

    int insert(WorkReadOverStu record);

    int insertSelective(WorkReadOverStu record);

    WorkReadOverStu selectByPrimaryKey(String workReadOverStuId);

    int updateByPrimaryKeySelective(WorkReadOverStu record);

    int updateByPrimaryKey(WorkReadOverStu record);
    
    List<StudentClassView> selectByHomework(String workHomeworkId);
    
    void deleteByHomework(String workHomeworkId);
}