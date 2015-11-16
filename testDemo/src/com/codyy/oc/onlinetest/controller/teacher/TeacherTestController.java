package com.codyy.oc.onlinetest.controller.teacher;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.filter.SecurityWrapper;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.ResultJson;
import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.entity.BaseUser;
import com.codyy.oc.base.form.ExamFormFields;
import com.codyy.oc.base.form.ExamListCriteria;
import com.codyy.oc.base.form.QuestionListResult;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.controller.common.BaseOnlineTestController;
import com.codyy.oc.onlinetest.entity.ExamQstRKnowledgeExtend;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionStatistics;
import com.codyy.oc.onlinetest.entity.ExamResult;
import com.codyy.oc.onlinetest.entity.ExamStudentStatistic;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;
import com.codyy.oc.onlinetest.service.ExamQuestionService;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.service.ExamTaskService;
import com.codyy.oc.onlinetest.view.ClassExamStatExcel;
import com.codyy.oc.onlinetest.view.ClassLevelClassView;
import com.codyy.oc.onlinetest.view.CountDataView;
import com.codyy.oc.onlinetest.view.ExamTaskSortView;
import com.codyy.oc.onlinetest.view.ExamTaskView;
import com.codyy.oc.onlinetest.view.ExamTestView;
import com.codyy.oc.onlinetest.view.ExamView;
import com.codyy.oc.onlinetest.view.StudentView;
import com.codyy.oc.onlinetest.view.TeachCommentView;
import com.codyy.oc.onlinetest.view.TestCountPersonView;
import com.codyy.oc.onlinetest.view.TestSearchView;
import com.codyy.oc.questionlib.service.QueQuestionService;

/**
 * 教师工作台
 * @author xiaokan
 * 
 */
@Controller
@RequestMapping("teacherTest")
public class TeacherTestController extends BaseOnlineTestController{
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamTaskService examTaskService;
	
	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private QueQuestionService questionService;
	
	@Autowired
	private ExamQuestionService examQuestionService;
	
	@Autowired
	private QueQuestionService queQuestionService;
	
	private String filterSpecChar(String oldChar) {
		return (oldChar == null ? "" : oldChar.replaceAll("<br/>", "").replaceAll("\r\n", " ").replaceAll("<p>", "").replaceAll("</p>", "")); 
	}
	
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
	 * 老师空间，测试中心，系统组卷
	 */
	@RequestMapping("toCreateExam")
	public String toCreateExam(HttpServletRequest request) {
		getClassAndSubject(request);
		request.setAttribute("tabType", "exam");
		return "front/onlinetest/teacher/createExam";
	}
	
	/**
	 * 智能选题
	 * @param qlist 组卷题目难易程度和题目数
	 * @param selectcatalogs 选择的章节
	 * @return
	 */
	@RequestMapping("searchIntelligenceList")
	@ResponseBody
	public List<QuestionListResult> searchIntelligenceList(int[] qlist,String[] chapters,String version, String semesterId, String subjectId,HttpServletRequest request) {
		return examService.searchIntelligenceList(qlist, chapters,version,semesterId,subjectId);
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
	 * 教师空间 - 跳转到测试任务列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
    @RequestMapping("classExamList")
	public String classExamList(HttpServletRequest request){
    	request.setAttribute("tabType", "classExam");
		// 获取年级和学科全部信息
		getClassAndSubject(request);
		
		return "front/onlinetest/teacher/classExamList";
		
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
		return "front/onlinetest/teacher/examList";
		
	}
    
    /**
     * 教师空间 -测试任务列表
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
    @ResponseBody
    @RequestMapping("getClassExamList")
    public Page getClassExamList(TestSearchView testSearchView, Page page){
    	return examTaskService.getClassExamTaskList(testSearchView, page);
    }
    
    /**
     * 教师空间 -测试任务列表
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
    @ResponseBody
    @RequestMapping("getExamList")
    public Page getExamList(TestSearchView testSearchView, Page page,HttpServletRequest request){
    	SessionUser sessionUser = getSessionUser(request);
    	//testSearchView.setCreateUserId(sessionUser.getUserId());
    	testSearchView.setCreateUserId(sessionUser.getUserId());
    	return examService.getExamList(testSearchView, page);
    }
    
    
    /**
     * 教师空间 - 查看测试
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
		model.addAttribute("tabType", "classExam");
    	return "front/onlinetest/teacher/viewClassExam";	
    }
    
    
    
    
    
    /**
     * 教师空间 - 统计试卷
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
    @RequestMapping("toClassExamAnalyze/{id}")
    public String toClassExamAnalyze(@PathVariable("id") String examTaskId, Model model){
    	ExamTaskView examTaskView = examTaskService.getClassExamById(examTaskId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("tabType", "classExam");
		return "front/onlinetest/teacher/classExamAnalyze";
    	
    }
    
    
    /**
     * 完成组卷
    * @Title: finishCreateExam
    * @Description: TODO
    * @param @param ef
    * @param @param model
    * @param @param request
    * @param @return
    * @return String    
    * @throws
     */
    @RequestMapping("/finishCreateExam")
	@ResponseBody
	public String finishCreateExam(ExamFormFields ef,Model model, HttpServletRequest request) {
		//处理字符串
		ef.setExamTitle(SecurityWrapper.tansValue(ef.getExamTitle()));
		SessionUser sessionUser = getSessionUser(request);
		if(CommonsConstant.USER_TYPE_SCHOOL_USER.equals(sessionUser.getUserType())){
			examService.createExam(ef,sessionUser.getUserId(),ExamListCriteria.ExamCategoryTypeEnum.classlevel.getValue());
		}else if(CommonsConstant.USER_TYPE_TEACHER.equals(sessionUser.getUserType())){
			examService.createExam(ef,sessionUser.getUserId(),ExamListCriteria.ExamCategoryTypeEnum.claz.getValue());
		}
		return "success";
	}
    
    
    /**
     * 完成编辑试卷
    * @Title: finishEditExam
    * @Description: TODO
    * @param @param ef
    * @param @param model
    * @param @param request
    * @param @return
    * @return String    
    * @throws
     */
    @RequestMapping("/finishEditExam")
	@ResponseBody
	public String finishEditExam(ExamFormFields ef,Model model, HttpServletRequest request,String[] stuIds,String[] classIds,Date beginTime,Date endTime) {
		//处理字符串
		ef.setExamTitle(SecurityWrapper.tansValue(ef.getExamTitle()));
		SessionUser sessionUser = getSessionUser(request);
		if(CommonsConstant.USER_TYPE_SCHOOL_USER.equals(sessionUser.getUserType())){
			examService.updateExam(ef,sessionUser.getUserId(),ExamListCriteria.ExamCategoryTypeEnum.classlevel.getValue(),stuIds,classIds,beginTime,endTime);
		}else if(CommonsConstant.USER_TYPE_TEACHER.equals(sessionUser.getUserType())){
			examService.updateExam(ef,sessionUser.getUserId(),ExamListCriteria.ExamCategoryTypeEnum.claz.getValue(),stuIds,classIds,beginTime,endTime);
		}
		return "success";
	}
    
	
	/**
	 * 教师空间 - 班级统计每个班级
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
	 * 教师空间 -按班级获取题目正确率
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getExamRightStatisByClass")
	public List<ExamQuestionStatistics> getExamRightStatisByClass(String examTaskId, String classId,
			String flag){
		 if (StringUtils.isNotBlank(classId)) {
			 List<ExamQuestionStatistics> examQuestionStatistics = examTaskService.getExamRightStatisByClass(examTaskId, classId, flag);
	    	 return examQuestionStatistics;
		 }
	   return null;
	}
	
	/**
	 * 教师空间 -获取学生统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getStudentStatisList")
	public List<ExamStudentStatistic>  getStudentStatisList(ExamTaskSortView examTaskSortView){
		List<ExamStudentStatistic> examStudentStatistics=examTaskService.getStudentStatisList(examTaskSortView);
		return examStudentStatistics;
	    
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
			Map<String, Object> model = new HashMap<String, Object>();
			
			//试卷信息
			ExamTaskView examTaskView = examTaskService.getClassExamById(examTaskSortView.getExamTaskId());

			//每个班级
			List<ExamTaskStatistics> examTaskStatistics = examTaskService.getExamStaticsByClass(examTaskSortView.getExamTaskId());
			
			//正确率统计
			List<ExamQuestionStatistics> examQuestionStatistics = examTaskService.getExamRightStatisByClass(examTaskSortView.getExamTaskId(), examTaskSortView.getClassId(), flag);
			
			//学生统计
			List<ExamStudentStatistic> examStudentStatistics = examTaskService.getStudentStatisList(examTaskSortView);
			
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
	 * 教师空间 - 详细统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
@RequestMapping("toViewAnalyzeDetail/{id}/{classId}")
   public String toViewAnalyzeDetail(@PathVariable("id") String examTaskId, 
			@PathVariable("classId") String classId, Model model){
	    List<ExamQuestionListResult>  examQuestions = examTaskService.getClassExamStatistics(examTaskId, classId);
	    model.addAttribute("examTaskId", examTaskId);
		model.addAttribute("examQuestions", examQuestions);
		model.addAttribute("classId", classId);
		model.addAttribute("tabType", "classExam");
		return "front/onlinetest/teacher/viewAnalyzeDetail";
	}
	
	/**
	 * 教师空间 -查看答题
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
        model.addAttribute("tabType", "classExam");
		return "front/onlinetest/teacher/viewExamAnswer";
	}
	
	/**
	 * 教师空间-获取知识点
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
	 * 教师空间 -获取每道题的答题结果统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getExamQstOptionStatisticsByExamQstId")
	public String[] getExamQstOptionStatisticsByExamQstId(String examTaskId,
			String examQuestionId, String classId){
		ExamQuestion examQuestion = examTaskService.getExamQuestionByQuestionId(examQuestionId);
		if (examQuestion != null){
			return examTaskService.getExamQuestionStatistics(examQuestion, examTaskId, classId);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("delExam")
	public int delExam(String examId){
		return examService.delExam(examId);
	}
	
	/**
	 * 选择学生
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selectStudentsPre")
	public String selectStudentsPre(HttpServletRequest request,String examId,String isedit) {
		request.setAttribute("examId", examId);
		request.setAttribute("isedit", isedit);
		request.setAttribute("clId", examService.getExamById(examId).getBaseClasslevelId());
		return "front/onlinetest/teacher/createTask";
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
	public String finishCreateTask(HttpServletRequest request,String examId,String[] stuIds,Date beginTime,Date endTime){
		SessionUser sessionUser = getSessionUser(request);
		return examTaskService.finishCreateTask(examId, stuIds, beginTime, endTime,sessionUser.getUserId());
	}
	
	/**
	 * 删除任务
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
	
    /**
     * 教师空间 - 查看试卷
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
    * @Description: 上传我的试卷
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws Exception
    * @return Map<String,Object>    
    * @throws
     */
	@RequestMapping("/uploadExam")
	@ResponseBody
	public Map<String,Object> uploadExam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return uploadExamBase(request, response, false, ExamListCriteria.ExamCategoryTypeEnum.claz.getValue());
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
    		examView.setRealExamId(examView.getExamId());
    		examView.setExamId(null);
    	}
		getClassAndSubject(request);
    	return "front/onlinetest/teacher/editExam";	
    }
    
    
    /**
     * 教师空间 - 编辑试卷-添加试题
     * @author xiaokan
     * @param
     * @return 
     *
     */
    @RequestMapping("editExamAdd")
    public String editExamAdd(Model model,HttpServletRequest request,String semesterId,String subjectId){
		getClassAndSubject(request);
		model.addAttribute("isedit",true);
		model.addAttribute("semesterId",semesterId);
		model.addAttribute("subjectId",subjectId);
    	return "front/onlinetest/teacher/editExamAdd";	
    }
    
    

	/**
	 * @author lichen
	* @Title: readOverOnLineTest
	* @Description: TODO(老师批阅页面)
	* @param @param request
	* @param @param homeworkId
	* @param @param classId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("readOverOnLineTest/{id}")
	public String readOverOnLineTest(HttpServletRequest request,@PathVariable("id") String examTaskId, Model model) {
            List<ClassLevelClassView> classLevelClassList =examQuestionService.selecClassByTaskExamId(examTaskId);
            if(null!=classLevelClassList && classLevelClassList.size()>0){
            	//将第一个年级班级对象传过去默认选中第一个
            	request.setAttribute("classLevelFirstId", classLevelClassList.get(0).getClassLevalId());
            	request.setAttribute("classFirstId", classLevelClassList.get(0).getClassId());
            	request.setAttribute("classLevelClassList", classLevelClassList);
            }
            request.setAttribute("examTaskId", examTaskId);
            request.setAttribute("tabType", "classExam");
			return "front/onlinetest/teacher/readList";
		
	}
	
	/**
	 * @author lichen
	* @Title: showStuStatuPageList
	* @Description: TODO(获取批阅学生的列表)
	* @param @param page
	* @param @param realName
	* @param @return    设定文件
	* @return List<StudentView>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("showStuStatuPageList")
	public Page showStuStatuPageList(Page page,String realName,String classLevId,String classId,String examTaskId){
		
		List<StudentView> stuList=examQuestionService.showStuStatuPageList(page, realName,classLevId,classId,examTaskId);
		page.setData(stuList);
		return page;
	}

	/**
	 * @author lichen
	* @Title: readByQuestionPre
	* @Description: TODO(跳转到主观题的在线批阅页面)
	* @param @param request
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("readByQuestionPre")
	public String readByQuestionPre(HttpServletRequest request, String classId, String examTaskId, String classLevelId) {
		// 获取批阅学生列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("baseClassId", classId);
		map.put("examTaskId", examTaskId);
		map.put("baseClasslevelId", classLevelId);
		List<BaseUser> students = examTaskService.getReviewStudentList(map);
		request.setAttribute("students", students);
		List<ExamQuestion> questions = examTaskService.getTestQuestionList(map);
		request.setAttribute("questions", questions);
		request.setAttribute("examTaskId", examTaskId);
		request.setAttribute("tabType", "classExam");
 		return "front/onlinetest/teacher/readByQuestion";
	}
	
	/**
	 * @author lichen
	* @Title: readByStudentPre
	* @Description: TODO(按学生进行批阅)
	* @param @param request
	* @param @param homeworkId
	* @param @param workReceiveStuId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("readByStudentPre")
	public String readByStudentPre(HttpServletRequest request,String resultId,String stuName) {
		
		List<ExamQuestionListResult> examQuestionList =examQuestionService.selecObjectByStu(resultId);
		if(null!=examQuestionList && examQuestionList.size()>0){
			request.setAttribute("examQuestionList", examQuestionList);
			request.setAttribute("stuName", stuName);
			request.setAttribute("resultId", resultId);
			request.setAttribute("examTaskId", examQuestionList.get(0).getExamTaskId());
			request.setAttribute("tabType", "classExam");
		}
		return "front/onlinetest/teacher/readByStudent";
	}
	
	/**
	 * @author lichen
	* @Title: updateStuComent
	* @Description: TODO(纵向批阅,对客观题和主观题给出点评并对主观题给出分值)
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updatestucoment")
	public ResultJson updateStuComent(String[] examResultQuestionId,HttpServletRequest request,String examResultId,String examTaskId){
		
		List<TeachCommentView> teaCommList = new ArrayList<TeachCommentView>();
		for (String questionId : examResultQuestionId) {
			TeachCommentView teachComment = new TeachCommentView();
			String teacherComment =request.getParameter("teacherComment_"+questionId);
			if(null==teacherComment){
				teacherComment="";
			}
			Float score;
			Float standardScore;
			String ObjectiveScore =request.getParameter("getScore_"+questionId);	
			String allStardScore =request.getParameter("standardScore_"+questionId);
			if(!"".equals(allStardScore) && !"".equals(ObjectiveScore)){
				standardScore=Float.parseFloat(allStardScore);
				score=Float.parseFloat(ObjectiveScore);
				if(standardScore.equals(score)){
					teachComment.setResult(1);//表示主观题答对了
				}else{
					teachComment.setResult(0);//表示主观题答错了
				}
			}else{
				score=null;
				teachComment.setResult(2);//表示是客观题不用评分
			}
			teachComment.setScore(score);
	        teachComment.setExamResultQuestionId(questionId);//封装每个人对应的题的id
	        teachComment.setTeacheComment(teacherComment);
	        teaCommList.add(teachComment);
		}
    	 return examQuestionService.updateQuestionCommScore(teaCommList,examResultId,examTaskId);
	}
	
	/**
	 * @author renhengli
	 * 获取学生答案
	 * @param request
	 * @param studentId
	 * @param examTaskId
	 * @param classId
	 * @param questionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getQuestionAnswer")
	public ExamQuestionResult getQuestionAnswer(HttpServletRequest request, @RequestParam(required = true) String studentId,
			@RequestParam(required = true) String examTaskId, @RequestParam(required = true) String classId,
			@RequestParam(required = true) String questionId) {
		Map<String, String> map =  new HashMap<String, String>();
		map.put("examTaskId", examTaskId);
		map.put("baseUserId", studentId);
		map.put("baseClassId", classId);
		map.put("examQuestionId", questionId);
		ExamQuestionResult  result = examTaskService.getQuestionAnswer(map);
		result.setAnswer(filterSpecChar(result.getAnswer()));
		int noReviewCount = examTaskService.noReviewStudentCountByQuestionId(map);
		result.setNoReviewCount(noReviewCount);
 		return result;
	}
	
	/**
	 * @author lichen
	* @Title: CountTestPerson
	* @Description: TODO(统计测试人数)
	* @param @param examTestView
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("countTestPerson")
	public TestCountPersonView countTestPerson(ExamTestView examTestView){
		return examQuestionService.countTestPerson(examTestView);
	}
	
	/**
	 * @author renhengli
	 * 保存按题批阅教师评语和得分
	 * @param request
	 * @param examQuestionResultId
	 * @param result
	 * @param teacherComment
	 * @param score
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveReadByQuestionComment")
	public ResultJson readByQuestion(HttpServletRequest request, @RequestParam(required = true) String examQuestionResultId,
			@RequestParam(required = true) String result, String teacherComment, String score) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("examQuestionResultId", examQuestionResultId);
		map.put("result", result);
		map.put("teacherComment", teacherComment);
		map.put("score", score);
		try {
			int i = examTaskService.updateTeaherCommentById(map);
			if(i!=1){
				return new ResultJson(false, "按题批阅失败！");
			}else {
				Map<String, String> map1 =  new HashMap<String, String>();
				map1.put("examTaskId", request.getParameter("examTaskId")); 
				map1.put("baseUserId", request.getParameter("studentId"));
				map1.put("baseClassId", request.getParameter("classId"));
				map1.put("examQuestionId", request.getParameter("questionId"));
				map1.put("baseClasslevelId", request.getParameter("classLevelId"));
				List<ExamQuestion> questions = examTaskService.getTestQuestionList(map1);
				int noReviewCount = examTaskService.noReviewStudentCountByQuestionId(map1);
				return new ResultJson(true, questions.size()+","+noReviewCount);
			}
		} catch (Exception e) {
			return new ResultJson(false, "按题批阅失败！");
		}
	}
	
	/**
	 * @author lichen
	* @Title: getSubjetiveNum
	* @Description: TODO(获得已提交的未批阅本次测试的所有主观题的个数和已批阅的主观题个数)
	* @param @param examTaskId
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getsubjetivenum")
	public CountDataView getSubjetiveNum(ClassLevelClassView classLevelClassView){
		return examQuestionService.getSubjetiveNum(classLevelClassView);
	}
	
	
	/**
	 * 查询题库习题列表
	 * 
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("searchShareList")
	@ResponseBody
	public Page searchShareListByPagination(Page page, HttpServletRequest request) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonsConstant.SESSION_USER);
		BaseArea baseArea = new BaseArea();
		String baseUserId = "";
		if (sessionUser != null) {
			baseArea = commonsService.getAreaById(sessionUser.getAreaId());
			baseUserId = sessionUser.getUserId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId",sessionUser.getUserId());
		map.put("baseSemesterId", request.getParameter("baseSemesterId"));
		map.put("baseClasslevelId", request.getParameter("baseClasslevelId"));
		map.put("baseSubjectId", request.getParameter("baseSubjectId"));
		map.put("baseVersionId", request.getParameter("baseVersionId"));
		String chapterIdStr = request.getParameter("chapterIdStr");
		map.put("baseVolumeId", chapterIdStr.split(",")[0]);
		map.put("type", request.getParameter("type"));// 题型
		map.put("baseChapterId", chapterIdStr.split(",")[1]);
		map.put("baseSectionId", chapterIdStr.split(",")[2]);
		map.put("baseKnowledgeId", request.getParameter("knowledgeIdStr").split(",")[0]);
		map.put("baseSubKnowledge1Id", request.getParameter("knowledgeIdStr").split(",")[1]);
		map.put("baseSubKnowledge2Id", request.getParameter("knowledgeIdStr").split(",")[2]);
		map.put("baseSubKnowledge3Id", request.getParameter("knowledgeIdStr").split(",")[3]);
		map.put("baseSubKnowledge4Id", request.getParameter("knowledgeIdStr").split(",")[4]);
		map.put("baseSubKnowledge5Id", request.getParameter("knowledgeIdStr").split(",")[5]);
		map.put("auditStatus", request.getParameter("auditStatus"));// 审核状态（学校工作台使用）
		map.put("relationalIndicator", request.getParameter("relationalIndicator"));// 是否包含孪生题、衍生题
		map.put("difficulty", request.getParameter("difficulty"));// 难易度
		map.put("baseAreaId", sessionUser == null ? "" : sessionUser.getAreaId());// 区域Id
		map.put("clsSchoolId", sessionUser == null ? "" : sessionUser.getSchoolId());// 学校Id
		map.put("areaIdPath", baseArea == null ? "" : baseArea.getAreaIdPath());// areaIdPath
		map.put("userType", sessionUser == null ? "" : sessionUser.getUserType());
		page.setMap(map);
		// 添加判断收藏标记map
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("baseUserId", baseUserId);
		return queQuestionService.getTeaQuePageList(page, map1);
	}
    
}
	
