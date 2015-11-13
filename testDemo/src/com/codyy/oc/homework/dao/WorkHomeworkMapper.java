package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.base.view.IdNameView;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.ReceiveStu;
import com.codyy.oc.homework.entity.WorkHomework;
import com.codyy.oc.homework.entity.correctCount;
import com.codyy.oc.homework.view.WorkCountView;

public interface WorkHomeworkMapper {
    int deleteByPrimaryKey(String workHomeworkId);

    int insert(WorkHomework record);

    int insertSelective(WorkHomework record);

    WorkHomework selectByPrimaryKey(String workHomeworkId);

    int updateByPrimaryKeySelective(WorkHomework record);

    int updateByPrimaryKeyWithBLOBs(WorkHomework record);

    int updateByPrimaryKey(WorkHomework record);
    
    void updateHomeworkStatus(Map<String, String> map);
    
    List<IdNameView> selectHomeworkClass(String workHomeworkId);

    public HomeWorkQuestionInfo findHomeWorkQuestionInfo(String workId);
    
    public int getStuAllCount(Map<String,String> map);
    
    public List<Map<String,Object>> getStuSubmitCountByStatus(Map<String,String> map);
    
    public List<Map<String,Object>> getStuCountByStatus(String workId);
    
    public WorkCountView getWorkCountByworkQueCount(String workId);
    
    public List<ReceiveStu> getReceiveStuInfo(Map<String,String> map);
    
    public List<correctCount> getCorrectCount(Map<String,String> map);
    
    void updateWorkStatusByWorkId(String workId);
    
    Map<String, String> selectClassInfoByClassId(String classId);
    
}