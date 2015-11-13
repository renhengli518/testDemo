package com.codyy.oc.homework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.page.Page;
import com.codyy.oc.BaseController;
import com.codyy.oc.homework.entity.HomeWorkQuestionInfo;
import com.codyy.oc.homework.entity.ReadOverUser;
import com.codyy.oc.homework.entity.Subject;
import com.codyy.oc.homework.service.ParentWorkService;
import com.codyy.oc.homework.service.StudentWorkService;
import com.codyy.oc.homework.service.TeacherWorkService;

@Controller
@RequestMapping("/parentWork")
public class ParentWorkController extends BaseController{
	@Autowired
	private ParentWorkService parentWorkService;
	@Autowired
	private TeacherWorkService teacherWorkService;
	@Autowired
	private StudentWorkService studentWorkService;
	@RequestMapping("/toParentWork")
	public String parentWork(HttpServletRequest request,@RequestParam("studentUserId") String studentUserId) {
		List<Subject> subjects = null ;
		subjects = parentWorkService.findSubjects(studentUserId);
		request.setAttribute("subjects", subjects);
		request.setAttribute("userId", studentUserId);
		return "front/homework/parent/parentWorkList";
			
	}

	@RequestMapping("/getParentWorkList")
	@ResponseBody
	public Page getStuHomeworkList(String assignStartTime, String assignEndTime, String status, String subjectId, String homeWorkName,String userId, HttpServletRequest request, Page page){
		
		return parentWorkService.findParentHomeworkById(assignStartTime, assignEndTime, status, subjectId, homeWorkName, userId, page);	
}
	/*
	 * 家长空间--查看作业
	 * */
	 @RequestMapping("/parentHomeWorkView/{workId}/{userId}/{status}")
	public String stuHomeWorkView(@PathVariable String workId,@PathVariable String userId,@PathVariable String status,HttpServletRequest request){
		 HomeWorkQuestionInfo homeWorkQuestionInfo = studentWorkService.getStuHomeWorkQueInfo(workId,userId,status);
			request.setAttribute("status", status);
			request.setAttribute("workQuestionInfo", homeWorkQuestionInfo);
		return "front/homework/parent/parentHomeWorkView";
}
	 /*
	  * 家长空间--查看批阅
	  * */
	 @RequestMapping("/toParentReadOverWorkView/{workId}/{userId}")
	 public String toParentReadOverWorkView(@PathVariable String workId,@PathVariable String userId,HttpServletRequest request){
	 	
	  	HomeWorkQuestionInfo homeWorkQuestionInfo=teacherWorkService.findQueReadOverInfo(workId, userId);
	  	String userName = teacherWorkService.getUserNameById(userId);//根据学生ID查找到学生的姓名（答卷人）
	  	request.setAttribute("homeWorkQuestionInfo", homeWorkQuestionInfo);
	  	request.setAttribute("userId", userId);
	  	request.setAttribute("userName", userName);
	  	ReadOverUser user = teacherWorkService.getUserType(workId,userId);//根据作业ID查找到作业的批阅人（如果批阅人为空，即是老师批阅，否则是这个学生批阅）
	  	request.setAttribute("user", user);
	  	return "front/homework/parent/parentHomeWorkReadOverView";
}
	 
}