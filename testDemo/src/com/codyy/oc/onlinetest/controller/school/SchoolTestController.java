package com.codyy.oc.onlinetest.controller.school;


import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
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
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.form.ExamListCriteria;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.controller.common.BaseOnlineTestController;
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
import com.codyy.oc.onlinetest.view.ExamView;
import com.codyy.oc.onlinetest.view.TestSearchView;

/**
 * 学校工作台-班级测试
 * @author zhangshuangquan
 * 
 */
@Controller
@RequestMapping("schoolTest/")
public class SchoolTestController extends BaseOnlineTestController{

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
	@RequestMapping("classExamList")
	public String classExamList(HttpServletRequest request){
		SessionUser sessionUser = getSessionUser(request);
		//获取年级和学科全部信息
		getClassAndSubject(request , sessionUser);
		request.setAttribute("tabType", "task");
		return "front/onlinetest/school/classExamList";
		
	}
	
	/**
	 * 教师空间 - 跳转到试卷列表
	 * @author xiaokan
	 * @param
	 * @return 
	 *
	 */
    @RequestMapping("examList")
	public String examList(HttpServletRequest request){
    	request.setAttribute("tabType", "exam");
		// 获取年级和学科全部信息
		getClassAndSubject(request);
		return "front/onlinetest/school/examList";
		
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
    	return examTaskService.getClassExamList(testSearchView,page);
		
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
		ExamTaskView examTaskView = examTaskService.getClassExamById(examTaskId);
		List<ExamQuestionListResult>  examQuestions = examTaskService.getExamQuestionByExamTaskId(examTaskId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("examQuestions", examQuestions);
		model.addAttribute("tabType", "task");
		return "front/onlinetest/school/viewClassExam";
		
	}
	
	/**
	 * 获取习题类型  单选 -多选-填空 
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getQuestionType")
	public List<String> getQuestionType(String examTaskId){
		List<String>  questionTypes = null;
		List<ExamQuestion> examQuestions = examTaskService.getQuestionType(examTaskId);
	    if(examQuestions != null && examQuestions.size() > 0){
	        questionTypes = new ArrayList<String>();
	        String type = null;
	    	for (int i = 0; i < examQuestions.size(); i++) {
	    		if ("COMPUTING".equals(examQuestions.get(i).getType())) {
					type = examQuestions.get(i).getType();
					examQuestions.remove(i);
				}
				questionTypes.add(examQuestions.get(i).getType());
			}
	    	questionTypes.add(type);
	    }
	    
		return questionTypes;
	}
	
	/**
	 * 班级测试  - 统计页面
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("toClassExamAnalyze/{id}")
	public String toClassExamAnalyze(@PathVariable("id") String examTaskId, Model model){
		ExamTaskView examTaskView = examTaskService.getClassExamById(examTaskId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("tabType", "task");
		return "front/onlinetest/school/classExamAnalyze";
		
	}
	
	/**
	 * 学校空间 - 班级统计 
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getExamStatisticsByClass")
	public List<ExamTaskStatistics> getExamStatisticsByClass(String examTaskId){
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
			ExamTaskView examTaskView = examTaskService.getClassExamById(examTaskSortView.getExamTaskId());
			
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
			// TODO Auto-generated catch block
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
		model.addAttribute("tabType", "task");
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
        model.addAttribute("tabType", "task");
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
	 * 学校空间 - 获取每道题的答题结果统计
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
	
	
	/**
	 * 学校空间，测试中心，系统组卷
	 */
	@RequestMapping("toCreateExam")
	public String toCreateExam(HttpServletRequest request, Model model) {
		getClassAndSubject(request);
		model.addAttribute("tabType", "exam");
		return "front/onlinetest/teacher/createExam";
	}
	
    /**
     * 学校空间 -测试任务列表
     * @author xiaokan
     * @param
     * @return 
     *
     */
    @ResponseBody
    @RequestMapping("getExamList")
    public Page getExamList(TestSearchView testSearchView, Page page,HttpServletRequest request){
    	SessionUser sessionUser = getSessionUser(request);
    	testSearchView.setCreateUserId(sessionUser.getUserId());
    	return examService.getSchoolExamList(testSearchView, page);
    }
    
    /**
     * 学校空间 - 查看试卷
     * @author xiaokan
     * @param
     * @return 
     *
     */
    @RequestMapping("viewExam/{type}/{id}")
    public String viewExam(@PathVariable("id") String examId,@PathVariable("type") String type, Model model){
    	ExamView examView = examService.getExamViewById(examId);
		List<ExamQuestionListResult>  examQuestions = examService.getExamQuestionByExamId(examId);
		model.addAttribute("examView", examView);
		model.addAttribute("examQuestions", examQuestions);
		if("exam".equals(type)){
    		//如果是我的试卷
    		model.addAttribute("tabType", "exam");
    	}else{
    		//如果是真题试卷
    		model.addAttribute("tabType", "real");
    	}
    	return "front/onlinetest/teacher/viewExam";	
    	
    }
    
	
    /**
     * 
    * @Title: uploadExam
    * @Description: 上传年级统考试卷
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws Exception
    * @return Map<String,Object>    
    * @throws
     */
	@RequestMapping("/uploadClazExam")
	@ResponseBody
	public Map<String,Object> uploadClazExam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return uploadExamBase(request, response, false, ExamListCriteria.ExamCategoryTypeEnum.claz.getValue());
	}
	
	/**
     * 
    * @Title: uploadRealExam
    * @Description: 上传真题试卷
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws Exception
    * @return Map<String,Object>    
    * @throws
     */
	@RequestMapping("/uploadRealExam")
	@ResponseBody
	public Map<String,Object> uploadRealExam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return uploadExamBase(request, response, true, ExamListCriteria.ExamCategoryTypeEnum.real.getValue());
	}
  
    
	/**
	 * 
	 * getClassAndSubject:查询所有的年级、学科
	 * 
	 * @param request
	 */
	private void getClassAndSubject(HttpServletRequest request) {
		List<BaseSemester> semesters = commonsService.getAllSemester();
		request.setAttribute("semesters", semesters);
		List<SelectModel> classes = commonsService.getAllClasslevels();
		request.setAttribute("classes", classes);
		List<SelectModel> subjects = commonsService.getAllSubjects();
		request.setAttribute("subjects", subjects);
		List<SelectModel> versions = commonsService.getAllVersions();
		request.setAttribute("versions", versions);
		List<SelectModel> volumes = commonsService.getAllVolumes();
		request.setAttribute("volumes", volumes);
		List<SelectModel> examTypes = examTaskService.getAllExamTypes();
		request.setAttribute("examTypes", examTypes);
	}
	
	/**
	 * 选择班级
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selectClassPre")
	public String selectStudentsPre(HttpServletRequest request,String examId,String isedit) {
		request.setAttribute("examId", examId);
		request.setAttribute("isedit", isedit);
		request.setAttribute("clId", examService.getExamById(examId).getBaseClasslevelId());
		return "front/onlinetest/school/createTask";
	}
	
	/**
	 * 
	* @Title: finishCreateTask
	* @Description: 布置试卷
	* @param @param examId
	* @param @param stuIds
	* @param @param beginTime
	* @param @param endTime
	* @param @return
	* @return String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("finishCreateTask")
	public String finishCreateTask(HttpServletRequest request,String examId,String[] classIds,Date beginTime,Date endTime){
		SessionUser sessionUser = getSessionUser(request);
		return examTaskService.finishCreateClassTask(examId, classIds, beginTime, endTime,sessionUser.getUserId());
	}
	
    /**
     * 教师空间 - 编辑试卷
     * @author xiaokan
     * @param
     * @return 
     *
     */
    @RequestMapping("editExam/{type}/{id}")
    public String editExam(@PathVariable("id") String examId,@PathVariable("type") String type, Model model,HttpServletRequest request){
    	ExamView examView = examService.getExamViewById(examId);
    	
		List<ExamQuestionListResult>  examQuestions = examService.getExamQuestionByExamId(examId);
		model.addAttribute("examView", examView);
		model.addAttribute("examQuestions", examQuestions);
		if("exam".equals(type)){
    		//如果是我的试卷
    		model.addAttribute("tabType", "exam");
    	}else{
    		//如果是真题试卷
    		model.addAttribute("tabType", "real");
    		examView.setExamId(null);
    	}
		getClassAndSubject(request);
    	return "front/onlinetest/teacher/editExam";	
    }
    
    /**
     * 删除 未开始的 年级统考试卷
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
    @ResponseBody
    @RequestMapping("delExamTask")
    public int delExamTask(String examTaskId){
    	return examTaskService.delExamTaskById(examTaskId);	
    }

	@ResponseBody
	@RequestMapping("delExam")
	public int delExam(String examId){
		return examService.delExam(examId);
	}
}
