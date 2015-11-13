package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.commons.page.Page;
import com.codyy.oc.base.view.StudentClassView;
import com.codyy.oc.homework.entity.WorkReceiveStu;
import com.codyy.oc.homework.view.ReadByQueStuView;
import com.codyy.oc.homework.view.ReadByStuResultView;
import com.codyy.oc.homework.view.ReadOverClassStudentView;

public interface WorkReceiveStuMapper {
    int deleteByPrimaryKey(String workReceiveStuId);

    int insert(WorkReceiveStu record);

    int insertSelective(WorkReceiveStu record);

    WorkReceiveStu selectByPrimaryKey(String workReceiveStuId);

    int updateByPrimaryKeySelective(WorkReceiveStu record);

    int updateByPrimaryKeyWithBLOBs(WorkReceiveStu record);

    int updateByPrimaryKey(WorkReceiveStu record);
    
    List<StudentClassView> selectUserByHomework(String workHomeworkId);
    
    void deleteByHomework(String workHomeworkId);
    
    WorkReceiveStu selectByUserAndHomework(Map<String, String> map);
    
    List<ReadOverClassStudentView> selectReadOverClassStudentPageList(Page page);
    
    int selectTextAndFileUncheckedNum(Map<String, String> map);
    
    List<ReadByQueStuView> selectReadByquestionStudent(Map<String, String> map);
    
    int selectAnswerCountStudent(Map<String, String> map);
    
    ReadByStuResultView selectReadByStuInfo(String workReceiveStuId);
    
    void updateWorkReceiveStuByPrimaryKey(Map<String,String> map);
    
    int selectStuReadCount(String workId);
    int selectCheckedCount(String workId);
}