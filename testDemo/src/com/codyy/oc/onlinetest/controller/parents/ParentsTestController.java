package com.codyy.oc.onlinetest.controller.parents;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.entity.ExamQstRKnowledgeExtend;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.service.ExamTaskService;
import com.codyy.oc.onlinetest.view.ExamTaskView;
import com.codyy.oc.onlinetest.view.TestSearchView;

/**
 * 家长空间 - 教师布置
 * @author zhangshuangquan
 *
 */
@RequestMapping("parentTest/")
public class ParentsTestController extends BaseController{

	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private ExamTaskService  examTaskService;
	
	@Autowired
	private ExamService examService;

	
	
	 /**
	  * 对前台传递的Date进行格式化规定
	  * @param binder
	  */
	 @InitBinder  
	 public void initBinder(WebDataBinder binder) {  
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	     dateFormat.setLenient(false);  
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
	 
	 /**
	  * 获取年级和学科全部信息
	  * @param request
	  */
	private void getClassAndSubject(HttpServletRequest request,
				SessionUser sessionUser) {
		List<SelectModel> subjects = commonsService.getAllSubjects();
		request.setAttribute("subjects", subjects);
		List<SelectModel> classes = commonsService.getAllClasslevels();
		request.setAttribute("classes", classes);
		List<SelectModel> examTypes = examTaskService.getAllExamTypes();
		request.setAttribute("examTypes", examTypes);
	}
	 
	 /**
	  * 跳转到教师布置列表
	  * @author zhangshuangquan
	  * @return
	  */
	@RequestMapping("teacherAssignList/{userId}")
	public String teacherAssignList(@PathVariable("userId") String userId, HttpServletRequest request) {
		SessionUser sessionUser = getSessionUser(request);
		// 获取年级和学科全部信息
		getClassAndSubject(request, sessionUser);
		request.setAttribute("userId", userId);
		return "front/onlinetest/parents/teacherAssignList";

	}
	 
	/**
	 * 教师布置列表
	 * @author zhangshuangquan
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTeacherAssignList")
	public Page getTeacherAssignList(TestSearchView testSearchView, Page page){
    	return examTaskService.getTeacherAssignList(testSearchView,page);
		
	}
	
	/**
	 * 家长空间 - 查看待批阅
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("viewSubmittedExam/{id}/{userId}")
	public String viewSubmittedExam(@PathVariable("id") String examTaskId, 
			@PathVariable("userId") String userId, Model model){
		ExamTaskView examTaskView = examTaskService.getStudentTaskView(examTaskId, userId);
		List<ExamQuestionListResult>  examQuestions = examTaskService.getSubmittedExamByTaskId(examTaskId, userId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("examQuestions", examQuestions);
		return "front/onlinetest/parents/viewSubmittedExam";
	}
	
	/**
	 * 家长空间 - 查看已完成
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("viewCheckedExam/{id}/{userId}")
	public String viewCheckedExam(@PathVariable("id") String examTaskId, 
			@PathVariable("userId") String userId, Model model){
		ExamTaskView examTaskView = examTaskService.getStudentTaskView(examTaskId, userId);
		List<ExamQuestionListResult>  examQuestions = examTaskService.getStudentExamAnswer(examTaskId, userId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("examQuestions", examQuestions);
		model.addAttribute("userId", userId);
		return "front/onlinetest/parents/viewCheckedExam";
	}
	
	/**
	 * 家长空间 - 班级试卷统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getClassExamStatistics")
	public ExamTaskStatistics getClassExamStatistics(String examTaskId, String studentId,
			String classlevelId, String classId){
		return examTaskService.getClassExamStaticsByStudent(examTaskId, studentId, classlevelId, classId);   
	}
	
	/**
	 * 家长空间-获取知识点
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("getExamQstRKnowledgeInfoByExamQstId")
	@ResponseBody
	public List<ExamQstRKnowledgeExtend> getExamQstRKnowledgeInfoByExamQstId(String examQuestionId){
		return examTaskService.getExamQstRKnowledgeInfoByExamQstId(examQuestionId);
	}
    

		
}
