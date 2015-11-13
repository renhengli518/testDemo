package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;
import com.codyy.commons.page.Page;
import com.codyy.oc.homework.entity.ClassLevel;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;

public interface HomeWorkMapper {
//查询出教师空间下的作业列表	
public List<HomeWork> findTeacherHomeworkByConditionsPageList(Page page);

//根据角色查询出对应的年级
public List<ClassLevel> findClassLevel(String userId);

//当待布置布置时，更新作业的状态
public void updateWorkStatus(Map<String,Object> map);

//删除作业
public void deleteWork(String workId);


//根据作业ID和学生的ID查询出该学生的某个作业的批阅的相关信息
public HomeWorkQuestionInfo findHomeWorkReadOverInfo(Map<String,Object> map );
public List<QuestionInfo> findReadOverQuestionListById(Map<String,Object> map);
public String findUserNameById(String userId);


}
