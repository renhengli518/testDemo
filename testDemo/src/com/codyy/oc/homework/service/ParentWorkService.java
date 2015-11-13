package com.codyy.oc.homework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.OracleKeyWordUtils;
import com.codyy.oc.homework.dao.HomeWorkMapper;
import com.codyy.oc.homework.dao.ParentHomeWorkMapper;
import com.codyy.oc.homework.dao.SubjectMapper;
import com.codyy.oc.homework.dao.WorkDocMapper;
import com.codyy.oc.homework.dao.WorkReceiveDocMapper;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkReceiveDoc;

@Service
public class ParentWorkService {
@Autowired
private SubjectMapper subjectMapper;
@Autowired
private HomeWorkMapper homeWorkMapper;
@Autowired
private ParentHomeWorkMapper parentHomeWorkMapper;
@Autowired
private WorkDocMapper workDocMapper;
@Autowired
private WorkReceiveDocMapper workReceiveDocMapper;
public List<Subject> findSubjects(String parentId){
	List<Subject> subjects=subjectMapper.selectSubjectByStuId(parentId);
	return subjects;
}
/*
 * 条件查询出家长下面的作业列表
 * */
public Page findParentHomeworkById(String assignStartTime,String assignEndTime,String status,String subjectId,String homeWorkName,String userId,Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("assignStartTime", assignStartTime);
			map.put("assignEndTime", assignEndTime);
			map.put("status", status);
			map.put("subjectId", subjectId);
		    map.put("homeWorkName",StringUtils.isEmpty(homeWorkName)?"":OracleKeyWordUtils.oracleKeyWordReplace(homeWorkName.trim()));
		    map.put("userId", userId);
		    page.setMap(map);
		List<HomeWork> homeWorkList=parentHomeWorkMapper.findParentHomeworkByConditionsPageList(page);
		page.setData(homeWorkList);
		return page;
	}
/*
 * 家长空间--查看作业(已经提交)
 * */
public HomeWorkQuestionInfo getParentHomeWorkQueInfo(String workId,String userId){
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("workId", workId);
	map.put("userId", userId);
	HomeWorkQuestionInfo homeWorkQuestionInfo = parentHomeWorkMapper.findParentHomeWorkQuestionInfo(workId);
	List<QuestionInfo> queList=parentHomeWorkMapper.getParentQueInfo(map);
	List<WorkDoc> workDocList = workDocMapper.selectByHomework(workId);
	homeWorkQuestionInfo.setWorkDocList(workDocList);
	homeWorkQuestionInfo.setQuestionInfo(queList);
	return homeWorkQuestionInfo;
}
/*
 * 家长空间--查看作业（未提交）
 * */
public HomeWorkQuestionInfo getParentProgressHomeWorkQueInfo(String workId){
	HomeWorkQuestionInfo homeWorkQuestionInfo = parentHomeWorkMapper.findParentHomeWorkQuestionInfo(workId);
	List<QuestionInfo> queList=parentHomeWorkMapper.getParentQueProgressInfo(workId);
	List<WorkDoc> workDocList = workDocMapper.selectByHomework(workId);
	homeWorkQuestionInfo.setWorkDocList(workDocList);
	homeWorkQuestionInfo.setQuestionInfo(queList);
	return homeWorkQuestionInfo;
}

}
