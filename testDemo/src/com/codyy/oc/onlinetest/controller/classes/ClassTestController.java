package com.codyy.oc.onlinetest.controller.classes;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.ResultJson;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.entity.ExamQstRKnowledgeExtend;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionStatistics;
import com.codyy.oc.onlinetest.entity.ExamResult;
import com.codyy.oc.onlinetest.entity.ExamStudentStatistic;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.service.ExamTaskService;
import com.codyy.oc.onlinetest.view.ClassExamStatExcel;
import com.codyy.oc.onlinetest.view.ExamTaskSortView;
import com.codyy.oc.onlinetest.view.ExamTaskView;
import com.codyy.oc.onlinetest.view.TestSearchView;

/**
 * 班级空间-测试
 * @author zhangshuangquan
 *
 */
@RequestMapping("classTest/")
public class ClassTestController extends BaseController{

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
	 * 跳转到班级测试
	 * @author zhangshuangquan
	 * @return
	 */
	@RequestMapping("classExamList/{classlevelId}/{classId}")
	public String classExamList(@PathVariable("classlevelId") String classlevelId, @PathVariable("classId") String classId, 
			HttpServletRequest request) {
		// 获取年级和学科全部信息
		SessionUser sessionUser = getSessionUser(request);
		request.setAttribute("classlevelId", classlevelId);
		request.setAttribute("classId", classId);
		getClassAndSubject(request, sessionUser);
		return "front/onlinetest/class/classExamList";

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
	 * 班级测试列表
	 * @author zhangshuangquan
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getClassExamList")
	public Page getClassExamList(TestSearchView testSearchView, Page page){
    	return examTaskService.getClsClassExamList(testSearchView,page);
		
	}
	
	/**
	 * 查看测试
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("viewClassExam/{id}")
	public String viewClassExam(@PathVariable("id") String examTaskId, Model model){
		ExamTaskView examTaskView = examTaskService.getClsClassExamTaskById(examTaskId);
		List<ExamQuestionListResult>  examQuestions = examTaskService.getExamQuestionByExamTaskId(examTaskId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("examQuestions", examQuestions);
		return "front/onlinetest/class/viewClassExam";
		
	}
	
	/**
	 * 班级测试  - 统计页面
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("toClassExamAnalyze/{id}/{classlevelId}/{classId}")
	public String toClassExamAnalyze(@PathVariable("id") String examTaskId, 
			@PathVariable("classlevelId") String classlevelId, 
			@PathVariable("classId") String classId,
			Model model){
		ExamTaskView examTaskView = examTaskService.getClsClassExamTask(examTaskId, classlevelId, classId);
		String classMsg = examTaskView.getClasslevelName()+examTaskView.getClassName();
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("classlevelId", classlevelId);
		model.addAttribute("classId", classId);
		model.addAttribute("classMsg", classMsg);
		return "front/onlinetest/class/classExamAnalyze";
		
	}
	
	
	
	/**
	 * 班级统计 -每个班级
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getExamStatisticsByClass")
	public List<ExamTaskStatistics> getExamStatisticsByClass(String examTaskId, Model model){
	   List<ExamTaskStatistics> examTaskStatistics= examTaskService.getExamStaticsByClass(examTaskId);
       return examTaskStatistics;
		
	}
	
	/**
	 * 按班级获取题目正确率
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getExamRightStatisByClass")
	public List<ExamQuestionStatistics> getExamRightStatisByClass(String examTaskId, String classlevel,
			String flag){
		 String[] classIds= classlevel.split(":");
		 String classlevelId = classIds[0];
		 String classId = classIds[1];
		 if (StringUtils.isNotBlank(classlevelId) && StringUtils.isNotBlank(classId)) {
    	    List<ExamQuestionStatistics> examQuestionStatistics = examTaskService.getExamRightStatisByClass(examTaskId, classlevelId, classId, flag);
    	    return examQuestionStatistics;
		 }
       return null;
	}
	
	/**
	 * 获取学生统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getStudentStatisList")
	public List<ExamStudentStatistic>  getStudentStatisList(ExamTaskSortView examTaskSortView){
		String[] classIds = examTaskSortView.getClasslevel().split(":");
		String classlevelId = classIds[0];
		String classId = classIds[1];
		if(StringUtils.isNotBlank(classlevelId) && StringUtils.isNotBlank(classId)){
			List<ExamStudentStatistic> examStudentStatistics=examTaskService.getStudentStatisList(examTaskSortView, classlevelId, classId);
		   return examStudentStatistics;
		}
		return null;
	}
	
	/**
	 * 导出统计数据
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("exportStatisticsData")
	public ResultJson exportStatisticsData(ExamTaskSortView examTaskSortView, String className,
			String flag, HttpServletResponse response, HttpServletRequest request){
		
		try {
			String[] classIds = examTaskSortView.getClasslevel().split(":");
			String classlevelId = classIds[0];
			String classId = classIds[1];
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			//试卷信息
			ExamTaskView examTaskView = examTaskService.getClsClassExamTaskById(examTaskSortView.getExamTaskId());
			
			//每个班级
			List<ExamTaskStatistics> examTaskStatistics = examTaskService.getExamStaticsByClass(examTaskSortView.getExamTaskId());
			
			//正确率统计
			List<ExamQuestionStatistics> examQuestionStatistics = examTaskService.getExamRightStatisByClass(examTaskSortView.getExamTaskId(), classlevelId, classId, flag);
			
			//学生统计
			List<ExamStudentStatistic> examStudentStatistics = examTaskService.getStudentStatisList(examTaskSortView, classlevelId, classId);
		
			model.put("examTaskId", examTaskSortView.getExamTaskId());
			model.put("className", className);
			model.put("examTaskView", examTaskView);
			model.put("examTaskStatistics", examTaskStatistics);
			model.put("examQuestionStatistics", examQuestionStatistics);
			model.put("examStudentStatistics", examStudentStatistics);
			ClassExamStatExcel viewExcel = new ClassExamStatExcel();
			ModelAndView e = new ModelAndView(viewExcel, model);
		    String fileName=new String((className).getBytes("utf-8"), "iso8859-1");//默认
			String userAgent=request.getHeader("User-Agent").toLowerCase();
	        if (userAgent.indexOf("msie") > 0||userAgent.indexOf("trident") > 0){//IE
	        	fileName = URLEncoder.encode(className, "UTF-8");
	        }
			request.getSession().setAttribute("viewExcel", e);
			request.getSession().setAttribute("fileName", fileName);
			return new ResultJson(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultJson(false);
		}
	}
	
	/**
	 * 导出数据
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("exportStatisticsUrl")
	public ModelAndView exportStatisticsUrl(HttpServletRequest request, HttpServletResponse response){
		ModelAndView e =(ModelAndView)request.getSession().getAttribute("viewExcel");
		String fileName = (String)request.getSession().getAttribute("fileName");
		request.removeAttribute("viewExcel");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName + ".xls");
		response.setContentType("application/x-msdownload");
      return e;
	}
	
	/**
	 * 班级测试 - 详细统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("toViewAnalyzeDetail/{id}/{classlevel}")
	public String toViewAnalyzeDetail(@PathVariable("id") String examTaskId, 
			@PathVariable("classlevel") String classlevel, Model model){
		String[] classIds = classlevel.split(":");
		String classlevelId = classIds[0];
		String classId = classIds[1];
		List<ExamQuestionListResult>  examQuestions = examTaskService.getClassExamStatistics(examTaskId, classlevelId, classId);
		model.addAttribute("examTaskId", examTaskId);
		model.addAttribute("examQuestions", examQuestions);
		model.addAttribute("classlevelId", classlevelId);
		model.addAttribute("classId", classId);
		return "front/onlinetest/school/viewAnalyzeDetail";
	}
	
	/**
	 * 查看答题
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("toViewExamAnswer/{id}/{userId}")
	public String toViewExamAnswer(@PathVariable("id") String examTaskId, 
			@PathVariable("userId") String userId, Model model){
		ExamResult examResult = examTaskService.getExamResultByUserId(examTaskId, userId);
		List<ExamQuestionListResult> examQuestions = examTaskService.getStudentExamAnswer(examTaskId, userId);
		model.addAttribute("examResult", examResult);
		 model.addAttribute("examTaskId", examTaskId);
        model.addAttribute("examQuestions", examQuestions);
		return "front/onlinetest/school/viewExamAnswer";
	}
	
	/**
	 * 获取知识点
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
	
	/**
	 * 获取每道题的答题结果统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getExamQstOptionStatisticsByExamQstId")
	public String[] getExamQstOptionStatisticsByExamQstId(String examTaskId,
			String examQuestionId, String classlevelId, String classId){
		ExamQuestion examQuestion = examTaskService.getExamQuestionByQuestionId(examQuestionId);
		if (examQuestion != null){
			return examTaskService.getExamQuestionStatistics(examQuestion, examTaskId, classlevelId, classId);
		}
		
		return null;
	}
	
}
