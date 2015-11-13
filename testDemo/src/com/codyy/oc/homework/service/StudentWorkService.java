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
import com.codyy.oc.homework.dao.StudentHomeWorkMapper;
import com.codyy.oc.homework.dao.SubjectMapper;
import com.codyy.oc.homework.dao.WorkDocMapper;
import com.codyy.oc.homework.dao.WorkReceiveDocMapper;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.ReceiveStu;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkReceiveDoc;

@Service
public class StudentWorkService {
@Autowired
private SubjectMapper subjectMapper;
@Autowired
private HomeWorkMapper homeWorkMapper;
@Autowired
private WorkDocMapper workDocMapper;
@Autowired
private StudentHomeWorkMapper studentHomeWorkMapper;
@Autowired
private WorkReceiveDocMapper workReceiveDocMapper;

public List<Subject> getSubjectsByStuId(String stuId){
	List<Subject> list=subjectMapper.selectSubjectByStuId(stuId);
	return list;
}

/*
 * 学生空间--我的作业--作业列表
 * */
public Page findStuHomeworkById(String assignStartTime,String assignEndTime,String status,String subjectId,String homeWorkName,String userId,Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("assignStartTime", assignStartTime);
			map.put("assignEndTime", assignEndTime);
			map.put("status", status);
			map.put("subjectId", subjectId);
		    map.put("homeWorkName",StringUtils.isEmpty(homeWorkName)?"":OracleKeyWordUtils.oracleKeyWordReplace(homeWorkName.trim()));
		    map.put("userId", userId);
		    page.setMap(map);
		List<HomeWork> homeWorkList=studentHomeWorkMapper.findStuHomeworkByConditionsPageList(page);
		page.setData(homeWorkList);
		return page;
	}
/*
 * 学生空间--我的作业--查看作业
 * */
public HomeWorkQuestionInfo getStuHomeWorkQueInfo(String workId,String userId,String status){
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("workId", workId);
	map.put("userId", userId);
	List<QuestionInfo> queList = null;
	List<WorkReceiveDoc> workReceiveDoc = null;
	HomeWorkQuestionInfo homeWorkQuestionInfo = studentHomeWorkMapper.findStudentHomeWorkQuestionInfo(workId);
	if(homeWorkQuestionInfo != null){
		if(status.equals("PROGRESS")){
			queList=studentHomeWorkMapper.getStuQueInfo(map);
			 
		}else{
			queList=studentHomeWorkMapper.getStuSubmitQueInfo(map);
			workReceiveDoc = workReceiveDocMapper.findWorkReceiveDoc(map);
			String textQueAnswer=studentHomeWorkMapper.getWorkReceiveStuTextAnswer(map);
			homeWorkQuestionInfo.setTextQueAnswer(textQueAnswer);
			homeWorkQuestionInfo.setWorkReceiveDoc(workReceiveDoc);
		}
		
		List<WorkDoc> workDocList = workDocMapper.selectByHomework(workId);
		homeWorkQuestionInfo.setWorkDocList(workDocList);
		homeWorkQuestionInfo.setQuestionInfo(queList);
		
	}
	return homeWorkQuestionInfo;
}
/*
 * 学生空间--我的批阅--作业列表
 * */
public Page findReadOverHomeworkById(String assignStartTime,String assignEndTime,String status,String subjectId,String homeWorkName,String userId,Page page){
	Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("assignStartTime", assignStartTime);
		map.put("assignEndTime", assignEndTime);
		map.put("status", status);
		map.put("subjectId", subjectId);
	    map.put("homeWorkName",StringUtils.isEmpty(homeWorkName)?"":OracleKeyWordUtils.oracleKeyWordReplace(homeWorkName.trim()));
	    map.put("userId", userId);
	    page.setMap(map);
	List<HomeWork> homeWorkList = studentHomeWorkMapper.findReadOverHomeworkByConditionsPageList(page);
	page.setData(homeWorkList);
	return page;
}
/*
 * 我的批阅 --查看批阅--进入批阅列表
 * */
public Page getQueryReadOverList(String userId,String workId,String type,Page page){
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("userId", userId);
	map.put("workId", workId);
	map.put("type", type);
	page.setMap(map);
	List<ReceiveStu> readOverStuList = studentHomeWorkMapper.getQueryReadOverListPageList(page);
	page.setData(readOverStuList);
	return page;
}
/*
 * 我的批阅--批阅--进入批阅列表
 * */
public Page getReadOverList(String type,String userId,String workId,Page page){
	Map<String, Object> map = new HashMap<String, Object>();
map.put("type", type);
map.put("userId", userId);
map.put("workId", workId);
page.setMap(map);
List<ReceiveStu> readOverStuList = studentHomeWorkMapper.getReadOverListPageList(page);
page.setData(readOverStuList);
return page;
}

}
