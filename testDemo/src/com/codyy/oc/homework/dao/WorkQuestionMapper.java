package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.commons.page.Page;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.WorkQuestion;
import com.codyy.oc.homework.view.WorkSearchQuestionView;

public interface WorkQuestionMapper {
    int deleteByPrimaryKey(String workQuestionId);

    int insert(WorkQuestion record);

    int insertSelective(WorkQuestion record);

    WorkQuestion selectByPrimaryKey(String workQuestionId);

    int updateByPrimaryKeySelective(WorkQuestion record);

    int updateByPrimaryKeyWithBLOBs(WorkQuestion record);

    int updateByPrimaryKey(WorkQuestion record);
    
    void deleteByHomework(String workHomeworkId);
    
    List<WorkQuestion> selectByHomework(String workHomeworkId);
    
    List<WorkQuestion> selectAnswerQuestionByHomework(String workHomeworkId);
    
    List<WorkQuestion> selectReadByQueQuestionByHomework(Map<String, String> map);
    
    //根据作业ID查询出作业中习题的信息
    public List<QuestionInfo> findQueReadOverInfo(Map<String, Object> map);
    
    List<WorkSearchQuestionView> selectWorkSearchQuestionPageList(Page page);
    
    List<WorkSearchQuestionView> selectSecletedQuestionPageList(Page page);
    
    List<String> selectHomeworkQuestion(String workHomeworkId);
    
    public List<QuestionInfo> findQuestionListById(String workId);
    
    int selectCountByHomework(String workHomeworkId);
    
    int selectObjectiveCountByHomework(String workHomeworkId);
}