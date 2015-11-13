package com.codyy.oc.homework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.OracleKeyWordUtils;
import com.codyy.oc.homework.dao.ClassHomeWorkMapper;
import com.codyy.oc.homework.dao.HomeWorkMapper;
import com.codyy.oc.homework.dao.SubjectMapper;
import com.codyy.oc.homework.dao.WorkDocMapper;
import com.codyy.oc.homework.dao.WorkHomeworkMapper;
import com.codyy.oc.homework.dao.WorkQuestionMapper;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.entity.WorkDoc;

@Service
public class ClassWorkService {
 @Autowired
private SubjectMapper subjectMapper;
 @Autowired
private HomeWorkMapper homeWorkMapper;
 @Autowired
 private ClassHomeWorkMapper classHomeWorkMapper;
 @Autowired
 private WorkDocMapper workDocMapper;
 @Autowired
private WorkHomeworkMapper workHomeworkMapper;
 @Autowired
private WorkQuestionMapper workQuestionMapper;
 /*
  * 查询出班级下面的学科
  * */
 public List<Subject> findSubjects(String classId){
	 List<Subject> subjects=subjectMapper.selectSubjectByClassId(classId);
	 return subjects;
 }
 /*
  * 条件查询出班级下面的作业列表
  * */
 public Page findClassHomeworkById(String assignStartTime,String assignEndTime,String status,String subjectId,String homeWorkName,String userId,Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("assignStartTime", assignStartTime);
			map.put("assignEndTime", assignEndTime);
			map.put("status", status);
			map.put("subjectId", subjectId);
		    map.put("homeWorkName",StringUtils.isEmpty(homeWorkName)?"":OracleKeyWordUtils.oracleKeyWordReplace(homeWorkName.trim()));
		    map.put("userId", userId);
		    page.setMap(map);
		List<HomeWork> homeWorkList=classHomeWorkMapper.findClassHomeworkByConditionsPageList(page);
		page.setData(homeWorkList);
		return page;
	}
 /*
  * 班级空间--查看作业
  * */
 public HomeWorkQuestionInfo findClassWorkInfo(String workId){
	 HomeWorkQuestionInfo homeWorkQuestionInfo = workHomeworkMapper.findHomeWorkQuestionInfo(workId);
	 return homeWorkQuestionInfo;
 }
 public List<QuestionInfo> getQuestionInfo(String workId) {
	 List<QuestionInfo> list = workQuestionMapper.findQuestionListById(workId);
	return list;
 }
 /*
  * 查询作业中的附件题
  * */
 public List<WorkDoc> getWorkDoc(String workId){
 	List<WorkDoc> workDocList = workDocMapper.selectByHomework(workId);
 	return workDocList;
 }
}
