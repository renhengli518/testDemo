package com.codyy.oc.onlinetest.controller.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import com.codyy.commons.CommonsConstant;
import com.codyy.commons.filter.SecurityWrapper;
import com.codyy.commons.image.ImageUtil;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.ResultJson;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.form.ExamFormFields;
import com.codyy.oc.base.form.ExamListCriteria;
import com.codyy.oc.base.form.QuestionListCriteria.QuestionType;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.base.view.SelectStudentView;
import com.codyy.oc.onlinetest.entity.ExamPractice;
import com.codyy.oc.onlinetest.entity.ExamPracticeQuestion;
import com.codyy.oc.onlinetest.entity.ExamQstRKnowledgeExtend;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionResult;
import com.codyy.oc.onlinetest.entity.ExamResult;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;
import com.codyy.oc.onlinetest.service.ExamQuestionService;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.service.ExamTaskService;
import com.codyy.oc.onlinetest.view.ExamTaskView;
import com.codyy.oc.onlinetest.view.TestSearchView;


/**
 * 学生空间- 测试
 * @author zhangshuangquan
 *
 */
@Controller
@RequestMapping("studentTest/")
public class StudentTestController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(StudentTestController.class);

	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private ExamTaskService  examTaskService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamQuestionService examQuestionService;

	
	
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
	 * 学生空间 - 跳转到测试列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("studentTaskList")
	public String studentTaskList(HttpServletRequest request){
		request.setAttribute("tabType", "studentTask");
		// 获取年级和学科全部信息
		getClassAndSubject(request);
		return "front/onlinetest/student/studentTaskList";
	}
	
	/**
	 * 学生空间 - 跳转到测试列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("selfExamList")
	public String selfExamList(HttpServletRequest request){
		request.setAttribute("tabType", "selfExam");
		// 获取年级和学科全部信息
		getClassAndSubject(request);
		return "front/onlinetest/student/selfExamList";
	}
	
	/**
	 * 系统组卷
	 */
	@RequestMapping("toCreateExam")
	public String toCreateExam(HttpServletRequest request) {
		SessionUser user = getSessionUser(request);
		SelectStudentView stuinfo = commonsService.selectStuInfoById(user.getUserId());
		if(stuinfo == null){
			//如果学生找不年级班级
		}
		request.setAttribute("stuinfo", stuinfo);
		getClassAndSubject(request);
		request.setAttribute("tabType", "selfExam");
		return "front/onlinetest/student/createExam";
	}
	
	/**
	 * 学生空间 - 测试任务列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getStudentTaskList")
	public Page getStudentTaskList(TestSearchView testSearchView, Page page){
		return examTaskService.getStudentTaskList(testSearchView, page);
		
	}
	
	/**
	 * 学生空间 - 查看待批阅
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
		model.addAttribute("tabType", "studentTask");
		return "front/onlinetest/student/viewSubmittedExam";
	}
	
	/**
	 * 学生空间 - 查看已完成
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
		model.addAttribute("tabType", "studentTask");
		return "front/onlinetest/student/viewCheckedExam";
	}
	
	/**
	 * 学生空间 - 班级试卷统计
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
	 * 学生空间-获取知识点
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
	 * 学生空间 - 答题
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("studentAnswerExam/{id}/{userId}")
	public String studentAnswerExam(@PathVariable("id") String examTaskId, 
			@PathVariable("userId") String userId, Model model){
		ExamTaskView examTaskView = examTaskService.getStudentTaskView(examTaskId, userId);
		List<ExamQuestionListResult>  examQuestions = examTaskService.getExamQuestionByExamTaskId(examTaskId);
		model.addAttribute("examTaskView", examTaskView);
		model.addAttribute("examQuestions", examQuestions);
		
		if(ExamListCriteria.ExamCategoryTypeEnum.self.getValue().equals(examTaskView.getExamCategoryType())){
			model.addAttribute("tabType", "selfExam");
		}else{
			model.addAttribute("tabType", "studentTask");
		}
		
		return "front/onlinetest/student/studentAnswerExam";
	}
	
	
	/**
	 * 填空题弹框
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("fillInEditorPopup")
	public String fillInEditorPopup(HttpServletRequest request) {
		return "front/onlinetest/student/fillInEditorPopup";
	}
    
	/**
	 * 学生空间 - 学生提交答题
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	
	@ResponseBody
	@RequestMapping("studentSubmitExam")
	public ResultJson studentSubmitExam(String answerInfo, String examResultId, String examTaskId,
			String[] questionId, String[] answerVideoPath, HttpServletRequest request){
		try {
			JSONArray jsonArray = JSONArray.fromObject(answerInfo);
			@SuppressWarnings("unchecked")
			List<ExamQuestionResult> examQuestionResults = (List<ExamQuestionResult>) JSONArray.toCollection(jsonArray, ExamQuestionResult.class);
			//清除session 中 从富文本框上传图片
			for (int i = 0; i < examQuestionResults.size(); i++) {
				 ExamQuestion examQuestion = examQuestionService.getExamQuestionById(examQuestionResults.get(i).getExamQuestionId());
				
				 if (examQuestion.getType().equals(QuestionType.resolve.getValue())
						 || examQuestion.getType().equals(QuestionType.computing.getValue())) {
					 ImageUtil.removeUploadImageByFilteringContent(request, examQuestionResults.get(i).getAnswer());	
				}
			}
			examTaskService.answerExamByStudent(examQuestionResults, examTaskId, examResultId, questionId, answerVideoPath);
	        return new ResultJson(true, "已提交答题！");
			
		} catch (Exception e) {
			logger.error(e);
			return new ResultJson(false, "答题错误！");
		}
		
	}
	
	/**
	 * 学生空间 - 巩固测试
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	
	@RequestMapping("studentPracticeExam/{id}/{userId}")
	public String studentPracticeExam(@PathVariable("id") String examTaskId, 
			@PathVariable("userId") String userId, Model model, HttpServletRequest request){
	    //获取学生答题结果
	    ExamResult examResult = examTaskService.getStudentExamResult(examTaskId, userId);
	    ExamPractice examPractice = examTaskService.getPracticeByExamResultId(examResult.getExamResultId());
	    String examPracticeId = null;
	    List<ExamPracticeQuestion> examPracticeQuestions = null;
	    if (examPractice != null) {
	    	 examPracticeId = examPractice.getExamPracticeId();
	    	 examPracticeQuestions = examTaskService.getExamPracticeQuestion(examPractice.getExamPracticeId());
			 List<ExamPracticeQuestion> practiceQuestions = new ArrayList<ExamPracticeQuestion>();
			 practiceQuestions = examTaskService.getPracticeQuestionSort(practiceQuestions, examPracticeQuestions);
	    	 model.addAttribute("examQuestions", practiceQuestions);
	    }else{
	    	SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonsConstant.SESSION_USER);
	    	BaseArea baseArea = commonsService.getAreaById(sessionUser.getAreaId());
	        String areaIdPath = baseArea.getAreaIdPath();
	        String schoolId = sessionUser.getSchoolId();
	    	List<ExamQuestionListResult> examQuestion = examTaskService.getPracticeExamByRandom(examResult, areaIdPath, schoolId);
		    
			//把随机获取的题目放入巩固测试
		    examPracticeId = examTaskService.insertPracticeExam(examResult, examTaskId, examQuestion);
		    
		    //查询巩固测试的题
		    if (StringUtils.isNotBlank(examPracticeId)) {
		    	examPracticeQuestions = examTaskService.getExamPracticeQuestion(examPracticeId);
		    	List<ExamPracticeQuestion> practiceQuestions = new ArrayList<ExamPracticeQuestion>();
		    	practiceQuestions = examTaskService.getPracticeQuestionSort(practiceQuestions, examPracticeQuestions);
		    	model.addAttribute("examQuestions", practiceQuestions);  
		    }  
		}
	    model.addAttribute("examPracticeId", examPracticeId);
		model.addAttribute("tabType", "studentTask");
		return "front/onlinetest/student/studentPracticeExam";	
	}
	
	
	
	/**
	 * 检查该套测试是否全对
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("checkPracticeExam")
	public ResultJson checkPracticeExam(String examTaskId, String userId){
		ExamResult examResult = examTaskService.checkPracticeExam(examTaskId, userId);
		if (examResult != null) {
			if (examResult.getTotal().equals(examResult.getRightCount())) {
				return new ResultJson(true);
			}
		}
		return new ResultJson(false);
	}
	
	/**
	 * 学生提交巩固测试
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("submitPracticeExam")
	public ResultJson submitPracticeExam(String answerInfo, String examPracticeId,
			String[] questionId, String[] answerVideoPath, HttpServletRequest request){
		try {
			
			JSONArray jsonArray = JSONArray.fromObject(answerInfo);
			@SuppressWarnings("unchecked")
			List<ExamPracticeQuestion> examPracticeQuestions = (List<ExamPracticeQuestion>) JSONArray.toCollection(jsonArray, ExamPracticeQuestion.class);
			//清除session 中 从富文本框上传图片
			for (int i = 0; i < examPracticeQuestions.size(); i++) {
				
				ExamPracticeQuestion examPracticeQuestion = examTaskService.getExamPracticeQuestionById(examPracticeQuestions.get(i).getExamPracticeQuestionId());
				if (examPracticeQuestion.getType().equals(QuestionType.resolve.getValue())
						|| examPracticeQuestion.getType().equals(QuestionType.computing.getValue())) {
					ImageUtil.removeUploadImageByFilteringContent(request, examPracticeQuestions.get(i).getMyAnswer());
				}
			}
			//提交巩固测试回答
			examTaskService.answerPracticeExam(examPracticeQuestions, examPracticeId, questionId, answerVideoPath);
			
			return new ResultJson(true, "已提交答题！");
		} catch (Exception e) {
			logger.error(e);
			return new ResultJson(false, "答题错误！");
		}
		
	}
	
	/**
	 * 巩固测试结果
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("practiceExamResult/{examPracticeId}")
	public String practiceExamResult(@PathVariable("examPracticeId") String examPracticeId, Model model){
		
		List<ExamPracticeQuestion> examPracticeQuestions = examTaskService.getExamPracticeQuestion(examPracticeId);
		List<ExamPracticeQuestion> practiceQuestions = new ArrayList<ExamPracticeQuestion>();
    	practiceQuestions = examTaskService.getPracticeQuestionSort(practiceQuestions, examPracticeQuestions);
		model.addAttribute("practiceQuestions", practiceQuestions);
		model.addAttribute("examPracticeId", examPracticeId);
		model.addAttribute("tabType", "studentTask");
		return "front/onlinetest/student/practiceExamResult";
		
	}
	
	/**
	 * 学生空间-获取巩固测试知识点
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@RequestMapping("getPracticeExamKnowledge")
	@ResponseBody
	public List<ExamQstRKnowledgeExtend> getPracticeExamKnowledge(String examPracticeQuestionId){
		return examTaskService.getPracticeExamKnowledge(examPracticeQuestionId);
	}
	
	/**
	 * 学生空间 - 巩固测试答题统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getPracticeStatistics")
	public ExamPractice getPracticeStatistics(String examPracticeId){
		return examTaskService.getPracticeStatistics(examPracticeId);
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
	public ExamTaskView finishCreateExam(ExamFormFields ef,Model model, HttpServletRequest request) {
		//处理字符串
		ef.setExamTitle(SecurityWrapper.tansValue(ef.getExamTitle()));
		SessionUser sessionUser = getSessionUser(request);
		ExamTaskView et = new ExamTaskView();
		et.setExamTaskId(examService.createExam(ef,sessionUser.getUserId(),ExamListCriteria.ExamCategoryTypeEnum.self.getValue()));
		et.setBaseUserId(sessionUser.getUserId());
		return et;
	}
    
	/**
	 * 学生空间 - 测试任务列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getStudentSelfTaskList")
	public Page getStudentSelfTaskList(TestSearchView testSearchView, Page page,HttpServletRequest request){
		testSearchView.setIsSelf(true);
		testSearchView.setUserId(getSessionUser(request).getUserId());
		return examTaskService.getStudentTaskList(testSearchView, page);
		
	}
	
	
	@ResponseBody
	@RequestMapping("delExam")
	public int delExam(String examTaskId){
		return examService.delSelfExam(examTaskId);
	}
    
}
