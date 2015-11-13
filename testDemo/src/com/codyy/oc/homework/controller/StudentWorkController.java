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
import com.codyy.oc.homework.entity.ReadOverUser;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.service.StudentWorkService;
import com.codyy.oc.homework.service.TeacherHomeworkService;
import com.codyy.oc.homework.service.TeacherWorkService;

@Controller
@RequestMapping("/studentWork")
public class StudentWorkController extends BaseController{
	
	@Autowired
	private StudentWorkService studentWorkService;
	@Autowired
	private TeacherWorkService teacherWorkService;
	@Autowired
	private TeacherHomeworkService teacherHomeworkService;
	@RequestMapping("/toStudentWork")
	public String studentWork(HttpServletRequest request) {
/*
 * 我的作业  作业列表
 * */
		request.setAttribute("type", "studentWork");
		List<Subject> subjects = null ;
		String userId=getSessionUserId(request);
		subjects=studentWorkService.getSubjectsByStuId(userId);
		request.setAttribute("userId", userId);
		request.setAttribute("subjects", subjects);
		return "front/homework/student/studentWorkList";		
	}
	
	@RequestMapping("/getStudentWorkList")
	@ResponseBody
	public Page getStuHomeworkList(String assignStartTime, String assignEndTime, String status, String subjectId, String homeWorkName,String userId, HttpServletRequest request, Page page){
		return studentWorkService.findStuHomeworkById(assignStartTime, assignEndTime, status, subjectId, homeWorkName, userId, page);	
	}
	
	/*
	 * 我的批阅---作业列表
	 * 
	 * */
	@RequestMapping("/myReadOver")
	public String getStuReadOverWorkList(HttpServletRequest request){
		request.setAttribute("type", "studentRead");
		String userId=getSessionUserId(request);
		List<Subject> subjects=null;
		subjects=studentWorkService.getSubjectsByStuId(userId);
		request.setAttribute("userId", userId);
		request.setAttribute("subjects", subjects);
		return "front/homework/student/studentReadOverWorkList";
}
	/*
	 * 我的批阅---作业列表查询
	 * */
	@RequestMapping("/getReadOverWorkList")
	@ResponseBody
	public Page getReadOverHomeworkList(String assignStartTime,
			String assignEndTime,
			String status,
			String subjectId,
			String homeWorkName,
			String userId,
			HttpServletRequest request,
			Page page){
		
		return studentWorkService.findReadOverHomeworkById(assignStartTime, assignEndTime, status, subjectId, homeWorkName, userId, page);	
	}
	/*
	 * 学生空间下	查看作业
	 * */
	@RequestMapping("/stuHomeWorkView/{workId}/{userId}/{status}")
	public String stuHomeWorkView(@PathVariable String workId,
			@PathVariable String userId,
			@PathVariable String status,
			HttpServletRequest request){
			request.setAttribute("type", "studentWork");
			HomeWorkQuestionInfo homeWorkQuestionInfo = studentWorkService.getStuHomeWorkQueInfo(workId,userId,status);
			request.setAttribute("status", status);
			request.setAttribute("workQuestionInfo", homeWorkQuestionInfo);
			return "front/homework/student/studentHomeWorkView";
		
		
	}
	/*
	  *我的作业--查看批阅 
	  * */
	  @RequestMapping("/toStudentReadOverWorkView/{workId}")
	  public String toStudentReadOverWorkView(@PathVariable String workId,HttpServletRequest request){
		  request.setAttribute("type", "studentWork");
		  String userId=getSessionUserId(request);
		  HomeWorkQuestionInfo homeWorkQuestionInfo=teacherWorkService.findQueReadOverInfo(workId, userId);
	  	String userName = teacherWorkService.getUserNameById(userId);//根据学生ID查找到学生的姓名（答卷人）
	  	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	  	request.setAttribute("userName", userName);
	  	request.setAttribute("myWorkRead", "queryRead");
	  	if(!homeWorkQuestionInfo.getReadOverType().equals("TEACHER")){
	  		ReadOverUser user = teacherWorkService.getUserType(workId,userId);//根据作业ID查找到作业的批阅人（）
		  	request.setAttribute("user", user);
	  	}
	  	
	  	return "front/homework/student/studentWorkReadOverView";
	  } 
	  
	  /*
	   *我的批阅--查看批阅 --进入查看批阅列表
	   * */
	  
	  @RequestMapping("/toQueryReadOverView/{workId}/{userId}")
		public String toQueryReadOverWorkView(@PathVariable String workId,@PathVariable String userId,HttpServletRequest request){
			request.setAttribute("userId", userId);
			request.setAttribute("workId", workId);
			request.setAttribute("type", "studentRead");
			return "front/homework/student/queryReadOverView";
	}
	 /*
	  * 查看批阅--批阅列表
	  * */
	@RequestMapping("/queryReadOverList")
	@ResponseBody
	public Page getQueryReadOverWorkView(String userId,String workId,String type,Page page){
		return studentWorkService.getQueryReadOverList(userId, workId, type,page);
	} 
	  /*
	   *我的批阅--批阅 --进入批阅列表
	   * */
	  
	  @RequestMapping("/toReadOverView/{workId}/{userId}")
		public String toMyReadOverWorkView(@PathVariable String workId,@PathVariable String userId,HttpServletRequest request){
			request.setAttribute("userId", userId);
			request.setAttribute("workId", workId);
			request.setAttribute("type", "studentRead");
			return "front/homework/student/MyReadOverView";
	}
	
	/*
	 * 批阅--批阅列表
	 * */
	@RequestMapping("/ReadOverList")
	@ResponseBody
	public Page getNotReadOverWorkView(String type,String userId,String workId,Page page){
		return studentWorkService.getReadOverList(type,userId, workId, page);
}
	/*
	 * 查看批阅列表	查看批阅
	 * */
	  @RequestMapping("/queryReadOverWorkView/{workId}/{userId}")
	  public String toReadOverWorkView(@PathVariable String workId,@PathVariable String userId,HttpServletRequest request){
		  request.setAttribute("type", "studentRead");
		  String readUserId=getSessionUserId(request);
		  HomeWorkQuestionInfo homeWorkQuestionInfo=teacherWorkService.findQueReadOverInfo(workId, userId);
	  	String userName = teacherWorkService.getUserNameById(userId);//根据学生ID查找到学生的姓名（答卷人）
	  	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	  	request.setAttribute("userName", userName);
	  	request.setAttribute("readUserId", readUserId);
	  	request.setAttribute("studentRead", "student");
	  	if(!"TEACHER".equals(homeWorkQuestionInfo.getReadOverType())){
	  		ReadOverUser user = teacherWorkService.getUserType(workId,userId);//根据作业ID查找到作业的批阅人（）
		  	request.setAttribute("user", user);
	  	}
	  	return "front/homework/student/studentWorkReadOverView";
	  }
}