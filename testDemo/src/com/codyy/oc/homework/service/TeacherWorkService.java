package com.codyy.oc.homework.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.OracleKeyWordUtils;
import com.codyy.oc.homework.dao.CommonWorkMapper;
import com.codyy.oc.homework.dao.HomeWorkMapper;
import com.codyy.oc.homework.dao.SubjectMapper;
import com.codyy.oc.homework.dao.WorkDocMapper;
import com.codyy.oc.homework.dao.WorkHomeworkMapper;
import com.codyy.oc.homework.dao.WorkQuestionMapper;
import com.codyy.oc.homework.dao.WorkRecStuQueAnswerMapper;
import com.codyy.oc.homework.dao.WorkReceiveDocMapper;
import com.codyy.oc.homework.entity.HomeWork;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.QuestionKnowLedge;
import com.codyy.oc.homework.entity.ReadOverUser;
import com.codyy.oc.homework.entity.Resolve;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkHomework;
import com.codyy.oc.homework.entity.WorkReceiveDoc;

@Service
public class TeacherWorkService {
	@Autowired
	private HomeWorkMapper homeWorkMapper;
	@Autowired
	private WorkQuestionMapper workQuestionMapper;
	@Autowired
	private WorkDocMapper workDocMapper;
	@Autowired
	private WorkReceiveDocMapper workReceiveDocMapper;
	@Autowired
	private WorkRecStuQueAnswerMapper workRecStuQueAnswerMapper;
	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private CommonWorkMapper commonWorkMapper;
	@Autowired
	private WorkHomeworkMapper workHomeworkMapper;
	
	/*
	 * 查询出教师所带的科目
	 * */
	public List<Subject> getSubject(String userId){
		List<Subject> list = subjectMapper.findSubject(userId);
		return list;
	}
/*
 * 条件查询教师所布置的作业
 * */
public Page findTeacherHomeworkById(String assignStartTime,
		String assignEndTime,
		String status,
		String subjectId,
		String homeWorkName,
		String userId,
		Page page){
	Map<String, Object> map = new HashMap<String, Object>();
		map.put("assignStartTime", assignStartTime);
		map.put("assignEndTime", assignEndTime);
		map.put("status", status);
		map.put("subjectId", subjectId);
	    map.put("homeWorkName",StringUtils.isEmpty(homeWorkName)?"":OracleKeyWordUtils.oracleKeyWordReplace(homeWorkName.trim()));
	    map.put("userId", userId);
	    page.setMap(map);
	List<HomeWork> homeWorkList = homeWorkMapper.findTeacherHomeworkByConditionsPageList(page);
	page.setData(homeWorkList);
	return page;
}

/*
 * 当点击布置时更新作业的状态
 * 
 * */
public void updateWorkStatus(String workId,Date date){
	Map<String,Object> map =new HashMap<String,Object>();
	map.put("workId", workId);
	map.put("date", date);
	homeWorkMapper.updateWorkStatus(map);	
}
/*
 * 删除作业
 * */
public void deleteWork(String workId){
	homeWorkMapper.deleteWork(workId);
}
/*
 * 根据作业ID查询出作业中的习题的相关信息
 * */
public HomeWorkQuestionInfo findHomeWorkQuestionById(String workId){
	HomeWorkQuestionInfo homeWorkQuestionInfo = workHomeworkMapper.findHomeWorkQuestionInfo(workId);
	return homeWorkQuestionInfo;
	
}
/*
 * 查询作业中的习题
 * */
public List<QuestionInfo> getQuesInfo(String workId){
	List<QuestionInfo> questionInfo = workQuestionMapper.findQuestionListById(workId);
	return questionInfo;
}
/*
 * 查询作业中的附件题
 * */
public List<WorkDoc> getWorkDoc(String workId){
	List<WorkDoc> workDocList = workDocMapper.selectByHomework(workId);
	return workDocList;
}
/*
 * 查询知识点
 * */
public List<QuestionKnowLedge> getQuestionKnowLedge(String queId){
	List<QuestionKnowLedge> queKnowLedge = commonWorkMapper.getQuestionKnowLedge(queId);
	return queKnowLedge;
}
/*
 * 查询习题解析
 * */
public Resolve findQueResolve(String queId){
	Resolve resolve = commonWorkMapper.findQueResolve(queId);
	return resolve;
}
/*
 * 查询作业的批阅人
 * */
public ReadOverUser getUserType(String workId,String userId){
	Map<String,String> map = new HashMap<String,String>();
	map.put("workId", workId);
	map.put("userId", userId);
	ReadOverUser readOverUser = commonWorkMapper.findUserType(map);
	return readOverUser;
}
/*
 * 根据作业查询批阅作业信息
 * */
public HomeWorkQuestionInfo findQueReadOverInfo(String workId,String userId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workId", workId);
		map.put("userId", userId);
		HomeWorkQuestionInfo homeWorkQuestionInfo = homeWorkMapper.findHomeWorkReadOverInfo(map);//得到作业ID，标题，文本题的相关信息等
		if(homeWorkQuestionInfo != null){ 
			List<QuestionInfo> questionInfo = homeWorkMapper.findReadOverQuestionListById(map);//将习题注入
			List<WorkDoc> workDocList = workDocMapper.selectByHomework(workId);//得到附件习题
			List<WorkReceiveDoc> workReceiveDoc = workReceiveDocMapper.findWorkReceiveDoc(map);
			homeWorkQuestionInfo.setQuestionInfo(questionInfo);
			homeWorkQuestionInfo.setWorkDocList(workDocList);
			homeWorkQuestionInfo.setWorkReceiveDoc(workReceiveDoc);
		}
		
		return homeWorkQuestionInfo;
	}
/*
 * 查询作业的答题人
 * */
	public String getUserNameById(String userId){
		String userName = homeWorkMapper.findUserNameById(userId);
		return userName;
	}
}

