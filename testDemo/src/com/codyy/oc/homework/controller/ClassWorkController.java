package com.codyy.oc.homework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.page.Page;
import com.codyy.oc.BaseController;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.QuestionInfo;
import com.codyy.oc.homework.entity.ReadOverUser;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.service.ClassWorkService;
import com.codyy.oc.homework.service.TeacherWorkService;
@Controller
@RequestMapping("/classWork")
public class ClassWorkController extends BaseController{
@Autowired
private ClassWorkService classWorkService;
@Autowired
private TeacherWorkService teacherWorkService;
/*
 * 班级空间--作业列表
 * */
@RequestMapping("/toClassWorkList")
public String toClassWorkList(HttpServletRequest request) {
	List<Subject> subjects = null ;
		//String userId=getSessionUserId(request);
	String userId = "6961445b37304403a964bca7b90bdbec";
		subjects=classWorkService.findSubjects(userId);
	    request.setAttribute("subjects", subjects);
	    request.setAttribute("userId", userId);
	    request.setAttribute("type", "class");
		return "front/homework/class/classWorkList";		
	} 

@RequestMapping("/getClassWorkList")
@ResponseBody
public Page getHomeworkList(String assignStartTime,String assignEndTime,String status,String subjectId,String homeWorkName,String userId,HttpServletRequest request,Page page){
	return classWorkService.findClassHomeworkById(assignStartTime,assignEndTime,status,subjectId,homeWorkName,userId,page);	
}
/*
 * 查看作业
 * */
@RequestMapping("/toHomeWorkView/{workId}")
public String toHomeWorkView(@PathVariable String workId,HttpServletRequest request){
	request.setAttribute("workId", workId);
	HomeWorkQuestionInfo homeWorkQuestionInfo = classWorkService.findClassWorkInfo(workId);
	if(homeWorkQuestionInfo == null){ 
		return "error/404";
	}
	List<QuestionInfo> questionInfo=classWorkService.getQuestionInfo(workId);
	List<WorkDoc> workDocList = classWorkService.getWorkDoc(homeWorkQuestionInfo.getHomeWorkId());
	homeWorkQuestionInfo.setWorkDocList(workDocList);
	homeWorkQuestionInfo.setQuestionInfo(questionInfo);
	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	return "front/homework/class/classHomeWorkView" ;
}

/*
 * 进入查看批阅页面()
 * */
@RequestMapping("/toClassReadOverWorkView/{workId}/{userId}")
public String toClassReadOverWorkView(@PathVariable String workId,@PathVariable String userId,HttpServletRequest request){
	/*String workId = "1";
	String userId = "40c4973fe3fc4d178ffb123e2f765c79";*/
	HomeWorkQuestionInfo homeWorkQuestionInfo=teacherWorkService.findQueReadOverInfo(workId, userId);
	String userName = teacherWorkService.getUserNameById(userId);
	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	request.setAttribute("userName", userName);
	ReadOverUser user = teacherWorkService.getUserType(workId,userId);
	request.setAttribute("user", user);
	return "front/homework/class/classWorkReadOverView";
}
}
