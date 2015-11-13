package com.codyy.oc.homework.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.ResultJson;
import com.codyy.oc.BaseController;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.QuestionKnowLedge;
import com.codyy.oc.homework.entity.ReadOverUser;
import com.codyy.oc.homework.entity.Resolve;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.service.TeacherHomeworkService;
import com.codyy.oc.homework.service.TeacherWorkService;

@RequestMapping("/teacherWork")
@Controller
public class TeacherWorkController extends BaseController{

 @Autowired
private TeacherWorkService teacherWorkService;

/*
 * 教师空间--作业中心--作业列表
 * */
@RequestMapping("/toTeacherWork")
public String browser(HttpServletRequest request) {
	String userId=getSessionUserId(request);
	List<Subject> subjects = null ;
	subjects =teacherWorkService.getSubject(userId);
    request.setAttribute("subjects", subjects);
    request.setAttribute("userId", userId);
    request.setAttribute("type", "teacher");
	return "front/homework/teacher/teacherWorkList" ;
}
/*
 * 查询作业列表
 * */
@RequestMapping("/getTeacherHomeWorkList")
@ResponseBody
public Page getHomeworkList(String assignStartTime,
		String assignEndTime, 
		String status,
		String subjectId, 
		String homeWorkName, 
		String userId,
		HttpServletRequest request, 
		Page page){
	return teacherWorkService.findTeacherHomeworkById(assignStartTime, assignEndTime, status, subjectId, homeWorkName, userId, page);	
}
/*
 * 布置作业
 * */
@RequestMapping("/arrangeWork")
@ResponseBody
public ResultJson arrangeWork(String workId){
	try {
		Date date=new Date();
		teacherWorkService.updateWorkStatus(workId,date);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResultJson(false, "作业布置失败！");
	}
	return new ResultJson(true);
}
/*
 * 删除作业
 * */
@RequestMapping("/deleteWork")
@ResponseBody
public ResultJson deleteWork(String workId){
	try {
		teacherWorkService.deleteWork(workId);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResultJson(false, "作业删除失败！");
	}
	return new ResultJson(true);
}
/*
 * 进入查看作业页面
 * */
 
@RequestMapping("/toHomeWorkView/{workID}")
public String toHomeWorkView(@PathVariable String workID, HttpServletRequest request){
	request.setAttribute("workID", workID);
	HomeWorkQuestionInfo homeWorkQuestionInfo = teacherWorkService.findHomeWorkQuestionById(workID);
	if(homeWorkQuestionInfo == null){ 
		return "error/404";
	}
	List<QuestionInfo> questionInfo=teacherWorkService.getQuesInfo(homeWorkQuestionInfo.getHomeWorkId());
	List<WorkDoc> workDocList = teacherWorkService.getWorkDoc(homeWorkQuestionInfo.getHomeWorkId());
	homeWorkQuestionInfo.setWorkDocList(workDocList);
	homeWorkQuestionInfo.setQuestionInfo(questionInfo);
	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	return "front/homework/teacher/homeWorkView" ;
}
/*
 * 查询知识点
 * */
@RequestMapping("/getQuestionKnowLedge")
@ResponseBody
public List<QuestionKnowLedge> getQuestionKnowLedgeList(String queId){
	List<QuestionKnowLedge> list = teacherWorkService.getQuestionKnowLedge(queId);
	return list;
}
/*
 * 进入查看批阅页面()
 * */
@RequestMapping("/toReadOverWorkView/{workId}/{userId}")
public String toReadOverWorkView(@PathVariable String workId,@PathVariable String userId,HttpServletRequest request){
	HomeWorkQuestionInfo homeWorkQuestionInfo=teacherWorkService.findQueReadOverInfo(workId, userId);
	String userName = teacherWorkService.getUserNameById(userId);
	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	request.setAttribute("userName", userName);
	ReadOverUser user = teacherWorkService.getUserType(workId,userId);
	request.setAttribute("user", user);
	return "front/homework/teacher/teacherWorkReadOverView";
}
//查询习题解析
@RequestMapping("/getResolve")
@ResponseBody
public Resolve findQueResolve(String queId){
	Resolve resolve = teacherWorkService.findQueResolve(queId);
	return resolve;
}

}