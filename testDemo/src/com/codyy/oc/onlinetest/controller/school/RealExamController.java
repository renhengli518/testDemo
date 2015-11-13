package com.codyy.oc.onlinetest.controller.school;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.BeanUtils;
import com.codyy.commons.utils.ResultJson;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.form.QuestionListCriteria;
import com.codyy.oc.base.service.BaseKnowledgeService;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.entity.ExamExam;
import com.codyy.oc.onlinetest.entity.ExamQueFillInAnswer;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionRChapter;
import com.codyy.oc.onlinetest.entity.ExamQuestionRKnowledge;
import com.codyy.oc.onlinetest.form.ExamFilelds;
import com.codyy.oc.onlinetest.service.ExamQuestionService;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.view.ExamView;
import com.codyy.oc.questionlib.form.QuestionFormFields;
import com.codyy.oc.questionlib.service.CreateQuestionLibService;
import com.codyy.oc.questionlib.service.QueQuestionService;

/**
 * @version 1.0
 * @author renhengli
 * 
 */
@Controller
@RequestMapping("schoolTest")
public class RealExamController extends BaseController {

	@Autowired
	private CommonsService commonsService;

	@Autowired
	private QueQuestionService queQuestionService;

	@Autowired
	private ExamService examService;

	@Autowired
	private CreateQuestionLibService createQuestionLibService;
	
	@Autowired
	private BaseKnowledgeService baseKnowledgeService;
	
	@Autowired
	private ExamQuestionService examQuestionService;

	private String filterSpecChar(String oldChar) {
		return (oldChar == null ? "" : oldChar.replaceAll("<br/>", "").replaceAll("\r\n", " "));
	}

	/**
	 * 进入真题试卷页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("toRealExam")
	public String toRealExamList(HttpServletRequest request) {
		request.setAttribute("tabType", "real");
		getClassAndSubject(request);
		// 获取10年内年份
		List<String> years = queQuestionService.getNearTenYears();
		request.setAttribute("years", years);
		return "front/onlinetest/school/realExam";
	}

	/**
	 * 
	 * getClassAndSubject:查询所有的年级、学科和试卷类型
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
		List<SelectModel> examTypes = commonsService.getAllExamType();
		request.setAttribute("examTypes", examTypes);
	}

	/**
	 * 查询真题试卷列表
	 * 
	 * @author renhengli
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("searchRealExamList")
	@ResponseBody
	public Page searchRealExamList(HttpServletRequest request, Page page) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonsConstant.SESSION_USER);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseSemesterId", request.getParameter("baseSemesterId"));// 学段
		map.put("baseClasslevelId", request.getParameter("baseClasslevelId"));// 年级
		map.put("baseSubjectId", request.getParameter("baseSubjectId"));// 学科
		map.put("areaName", request.getParameter("areaName"));// 地区
		// 获取10年内年份
		List<String> years = queQuestionService.getNearTenYears();
		map.put("year", request.getParameter("year"));// 年份
		map.put("years", years);
		map.put("examTypeId", request.getParameter("examTypeId"));// 试卷类型
		map.put("schoolId", sessionUser == null ? "" : sessionUser.getSchoolId());// 创建人
		map.put("createTimeSort", request.getParameter("createTimeSort"));// 时间排序
		map.put("useCountSort", request.getParameter("useCountSort"));// 使用次数排序
		page.setMap(map);
		page = examService.getRealExamPageList(page, map);
		return page;
	}

	/**
	 * @author renhengli 删除真题试卷
	 * @param examId
	 * @return
	 */
	@RequestMapping("deleteRealExamById")
	@ResponseBody
	public ResultJson deleteRealExamById(String examId) {
		Boolean result = (examService.delExam(examId) == 1);
		if (result) {
			return new ResultJson(true, "删除真题试卷成功！");
		} else {
			return new ResultJson(false, "删除真题试卷失败！");
		}
	}

	/**
	 * @author renhengli 新建真题试卷（上传试卷）
	 * @param request
	 * @return
	 */
	@RequestMapping("createRealExam")
	public String createRealExam(HttpServletRequest request) {
		request.setAttribute("tabType", "real");
		request.setAttribute("pageName", "save"); 
		getClassAndSubject(request);
		// 获取10年内年份
		List<String> years = queQuestionService.getNearTenYears();
		request.setAttribute("years", years);
		String examId = UUIDUtils.getUUID();// 初始化真题试卷Id
		request.setAttribute("examId", examId);
		return "front/onlinetest/school/createRealExam";
	}

	/**
	 * @author renhengli 创建真题试卷添加习题（保存临时习题）
	 * @param qstForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("createTempoparyQuestion")
	@ResponseBody
	public ExamQuestion createTempoparyQuestion(@ModelAttribute QuestionFormFields qstForm, HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser sessionUser = getSessionUser(request);
		// 设置所有题型公共属性
		String questionId = UUIDUtils.getUUID();// 母题的id
		String baseSemesterId = qstForm.getSemesterOpts();// 学段
		String baseClasslevelId = qstForm.getClasslevelOpts();// 年级
		String baseSubjectId = qstForm.getDisciplineOpts();// 学科
		String baseUserId = sessionUser==null?"":sessionUser.getUserId();// 习题创建者
		String questionDifficult = qstForm.getDifficultyOpts();// 习题的难易程度
		String questionType = qstForm.getQuestionTypeOpts();// 题型
		String examId = qstForm.getExamId();
		
		//首先生成一条空的试卷记录
		ExamExam dto = new ExamExam();
		dto = examService.getExamById(examId);
		if(dto == null){//已经存在就不在新增
			ExamExam examExam = new ExamExam();
			examExam.setExamId(examId); 
			examService.insert(examExam);
		}

		List<ExamQuestion> queList = new ArrayList<ExamQuestion>(); // 将所有添加的习题逐个进行封装操作
																	// 第一个一定是母题

		for (int i = 0; i < qstForm.getQuestionSubArra().length; i++) {

			ExamQuestion ques = new ExamQuestion();
			ques = this.getQuestion(qstForm.getQuestionSubArra()[i], questionId, questionType, baseUserId, questionDifficult, request,
					baseSemesterId, baseClasslevelId, baseSubjectId, i, examId);
			queList.add(ques);
		}

		// 添加章节
		List<ExamQuestionRChapter> queQuestList = new ArrayList<ExamQuestionRChapter>();

		if (null != qstForm.getCh_input() && qstForm.getCh_input().length > 0) {
			for (int i = 0; i < qstForm.getCh_input().length; i++) {
				String[] VeVoChSec = qstForm.getCh_input()[i].split(":");// 将每一个逗号分隔的字符串元素分为一个数组
				ExamQuestionRChapter queQuestionRChapter = new ExamQuestionRChapter();
				queQuestionRChapter.setExamQuestionRChapterId(UUIDUtils.getUUID());
				queQuestionRChapter.setExamQuestionId(questionId);// 章节关联本习题的母题
				if (!"0".equals(VeVoChSec[0])) {
					queQuestionRChapter.setBaseVersionId(VeVoChSec[0]);// 一共四个
																		// 没有的0代替
				} else {
					queQuestionRChapter.setBaseVersionId("");// 如果是0则存空串
				}
				if (!"0".equals(VeVoChSec[1])) {
					queQuestionRChapter.setBaseVolumeId(VeVoChSec[1]);
				} else {
					queQuestionRChapter.setBaseVolumeId("");
				}
				if (!"0".equals(VeVoChSec[2])) {
					queQuestionRChapter.setBaseChapterId(VeVoChSec[2]);
				} else {
					queQuestionRChapter.setBaseChapterId("");
				}
				if (!"0".equals(VeVoChSec[3])) {
					queQuestionRChapter.setBaseSectionId(VeVoChSec[3]);
				} else {
					queQuestionRChapter.setBaseSectionId("");
				}

				queQuestList.add(queQuestionRChapter);
			}
		}

		// 添加相关的知识点信息
		List<ExamQuestionRKnowledge> knowList = new ArrayList<ExamQuestionRKnowledge>();
		if (null != qstForm.getK_input() && qstForm.getK_input().length > 0) {

			for (int i = 0; i < qstForm.getK_input().length; i++) {

				String[] knowObj = qstForm.getK_input()[i].split(":");
				ExamQuestionRKnowledge queKnow = new ExamQuestionRKnowledge();
				queKnow.setExamQuestionRKnowledgeId(UUIDUtils.getUUID());
				queKnow.setExamQuestionId(questionId);// 与母题进行关联

				// 添加最后一节知识点 //倒序判断找出最后一节知识点
				if (!"0".equals(knowObj[5])) {
					queKnow.setBaseEndKnowledgeId(knowObj[5]);
				}
				if (!"0".equals(knowObj[4]) && "0".equals(knowObj[5])) {
					queKnow.setBaseEndKnowledgeId(knowObj[4]);
				}
				if (!"0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])) {
					queKnow.setBaseEndKnowledgeId(knowObj[3]);
				}
				if (!"0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])) {
					queKnow.setBaseEndKnowledgeId(knowObj[2]);
				}
				if (!"0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4])
						&& "0".equals(knowObj[5])) {

					queKnow.setBaseEndKnowledgeId(knowObj[1]);
				}
				if (!"0".equals(knowObj[0]) && "0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3])
						&& "0".equals(knowObj[4]) && "0".equals(knowObj[5])) {

					queKnow.setBaseEndKnowledgeId(knowObj[0]);
				}

				if (!"0".equals(knowObj[0])) {// 添加基础知识点
					queKnow.setBaseKnowledgeId(knowObj[0]);
				} else {
					queKnow.setBaseKnowledgeId("");
				}
				if (!"0".equals(knowObj[1])) {
					queKnow.setBaseSubKnowledge1Id(knowObj[1]);
				} else {
					queKnow.setBaseSubKnowledge1Id("");
				}
				if (!"0".equals(knowObj[2])) {
					queKnow.setBaseSubKnowledge2Id(knowObj[2]);
				} else {
					queKnow.setBaseSubKnowledge2Id("");
				}
				if (!"0".equals(knowObj[3])) {
					queKnow.setBaseSubKnowledge3Id(knowObj[3]);
				} else {
					queKnow.setBaseSubKnowledge3Id("");
				}
				if (!"0".equals(knowObj[4])) {
					queKnow.setBaseSubKnowledge4Id(knowObj[4]);
				} else {
					queKnow.setBaseSubKnowledge4Id("");
				}
				if (!"0".equals(knowObj[5])) {
					queKnow.setBaseSubKnowledge5Id(knowObj[5]);
				} else {
					queKnow.setBaseSubKnowledge5Id("");
				}

				// 将封装好的知识点加入集合
				knowList.add(queKnow);
			}
		}

		try {
			// 将封装好的问题的集合传过去
			examService.createQuestion(queList, queQuestList, knowList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExamQuestion examQuestion = new ExamQuestion();
		examQuestion = examService.selectExamQueById(questionId);
		return examQuestion;
	}

	private ExamQuestion getQuestion(QuestionFormFields qstForm, String questionId, String questionType, String userId,
			String questionDifficulty, HttpServletRequest request, String baseSemesterId, String baseClasslevelId, String baseSubjectId,
			Integer questionNum, String examId) {

		if ("0".equals(baseSemesterId)) {// 如果是0将其置空 防止主外键约束异常

			baseSemesterId = "";
		}

		if ("0".equals(baseClasslevelId)) {

			baseClasslevelId = "";
		}

		if ("0".equals(baseSubjectId)) {

			baseSubjectId = "";
		}

		ExamQuestion question = new ExamQuestion();
		question.setBaseUserId(userId);// 获得创建者信息
		question.setContent(filterSpecChar(qstForm.getEdt_content()));// 习题题干内容
		question.setResolve(filterSpecChar(qstForm.getEdt_resolve()));// 题干解析内容
		question.setResolveVideo(qstForm.getResolveVideo());// 音频路径
		question.setUpdateTime(new Date());// 修改时间
		question.setContentVideo(qstForm.getContentVideo());// 存放音频内容路径
		question.setResolveVideo(qstForm.getResolveVideo());// 存放音频解析路径
		question.setDifficulty(questionDifficulty);// 题目的难易程度
		question.setType(questionType);// 加入题型
		question.setBaseSemesterId(baseSemesterId);// 设置学段
		question.setBaseClasslevelId(baseClasslevelId);// 年级
		question.setBaseSubjectId(baseSubjectId);// 学科
		question.setExamId(examId);
		question.setTempoparyFlag("Y");//临时习题标志：Y/是，N/否

		// 将单选和复选封装到一个字段 存放单选和多选题的答案
		if ("SINGLE_CHOICE".equals(questionType) || "JUDEMENT".equals(questionType)) {// 单选或判断题
			if (null != qstForm.getRdo_singleOpt() && !"".equals(qstForm.getRdo_singleOpt())) {
				question.setAnswer(qstForm.getRdo_singleOpt());
			}
		} else if ("MULTI_CHOICE".equals(questionType)) {
			if (qstForm.getChk_multOpt().length > 1 && null != qstForm.getChk_multOpt()) {
				String answer = "";
				for (int i = 0; i < qstForm.getChk_multOpt().length; i++) {
					answer += qstForm.getChk_multOpt()[i];
				}
				question.setAnswer(answer);
			}
		}

		// 封装创建的选择题选项
		String options = "";
		if ("SINGLE_CHOICE".equals(questionType) || "MULTI_CHOICE".equals(questionType) || "JUDEMENT".equals(questionType)) {

			if (qstForm.getContent_title().length > 0 && null != qstForm.getContent_title() && qstForm.getOpt_title().length > 0
					&& null != qstForm.getOpt_title()) {

				for (int i = 0; i < qstForm.getContent_title().length; i++) {

					options += qstForm.getOpt_title()[i] + qstForm.getContent_title()[i] + "∷";
				}
				options = options.substring(0, options.length() - 1);
			}
		}
		question.setOptions(options);
		question.setOptionsTxt(options);// 纯文本选项
		question.setRelationalIndicator("MOTHER");// 默认创建的习题为母题
		question.setExamQuestionId(questionId);// 如果是母题则添加其id

		if (null != qstForm.getEdt_options()) {
			question.setOptions(qstForm.getEdt_options());
		}

		// 如果创建的是填空题 进行操作
		if ("FILL_IN_BLANK".equals(questionType)) {
			String fillAnswerType=qstForm.getRdo_answerType();//答案类型
			Integer fillCount=Integer.parseInt(qstForm.getFillInAnswerCnt());
			if(1==fillCount){//当本填空题只有一个空的时候则默认是单独答案且全选给你分
				question.setFillInScoreType("ALL_CORRECT");//全对给分
				question.setFillInAnswerType(QuestionListCriteria.INDEPENDENT);//独立答案
			}else{//当题目空的个数大于1的时候
				question.setFillInScoreType(qstForm.getRdo_scoreType());//设置填空题按空给分还是全对给分
				if("2".equals(fillAnswerType)){
					question.setFillInAnswerType(QuestionListCriteria.COMBINATION);
				}else{
					question.setFillInAnswerType(QuestionListCriteria.INDEPENDENT);
				}
			}
			
			String questionsId = question.getExamQuestionId();
			List<ExamQueFillInAnswer> queFillList = this.obtainFillInAnswer(request, qstForm, questionNum, questionsId);
			// 将封装好的集合赋值给Question对象
			question.setFillInAnswers(queFillList);// 封装每个填空题
		}

		// 如果创建的习题是非填空题
		if (!"FILL_IN_BLANK".equals(questionType)) {

			question.setFillInAnswerType("");// 将填空题的答案类型置空
			question.setFillInScoreType("");// 将填空题的给分类型置空
		}

		return question;
	}

	private List<ExamQueFillInAnswer> obtainFillInAnswer(HttpServletRequest request, QuestionFormFields subQstForm, Integer questionIndex,
			String questionId) {// questionId填空题与母题的关联id

		String prefix = "indep_";
		String txt_prefix = "indep_txt_";

		if ("2".equals(subQstForm.getRdo_answerType())) {// 判断所选填空题的答案类型进行切换答案

			prefix = "comb_";
			txt_prefix = "comb_txt_";
		}

		List<ExamQueFillInAnswer> li = new ArrayList<ExamQueFillInAnswer>();// 封装每道填空题的答案

		for (int j = 0; j < Integer.parseInt(subQstForm.getFillInAnswerCnt()); j++) {// 循环结束一次则封装一道填空题

			ExamQueFillInAnswer queFillObj = new ExamQueFillInAnswer();// 创建一个填空题

			queFillObj.setExamQueFillInAnswerId(UUIDUtils.getUUID());
			queFillObj.setExamQuestionId(questionId);// 设置与对应题库的关联id

			String answerGrp1 = request.getParameter(prefix + questionIndex + "_" + (j + 1) + "_1");
			String answerGrp2 = request.getParameter(prefix + questionIndex + "_" + (j + 1) + "_2");
			String answerGrp3 = request.getParameter(prefix + questionIndex + "_" + (j + 1) + "_3");
			String answerGrp4 = request.getParameter(prefix + questionIndex + "_" + (j + 1) + "_4");

			queFillObj.setAnswerGrp1(filterSpecChar(answerGrp1));// 进行字符串处理
			queFillObj.setAnswerGrp2(filterSpecChar(answerGrp2));
			queFillObj.setAnswerGrp3(filterSpecChar(answerGrp3));
			queFillObj.setAnswerGrp4(filterSpecChar(answerGrp4));

			Integer sort=j+1;
			queFillObj.setSort(sort.toString());//添加每空的第几个
			 
			String answerGrp1Txt = (request.getParameter(txt_prefix + questionIndex + "_" + (j + 1) + "_1"));// 获得备份纯文本答案
			String answerGrp2Txt = (request.getParameter(txt_prefix + questionIndex + "_" + (j + 1) + "_2"));
			String answerGrp3Txt = (request.getParameter(txt_prefix + questionIndex + "_" + (j + 1) + "_3"));
			String answerGrp4Txt = (request.getParameter(txt_prefix + questionIndex + "_" + (j + 1) + "_4"));

			queFillObj.setAnswerGrp1Txt(filterSpecChar(answerGrp1Txt));// 对填空题的纯文本答案进行处理
			queFillObj.setAnswerGrp2Txt(filterSpecChar(answerGrp2Txt));
			queFillObj.setAnswerGrp3Txt(filterSpecChar(answerGrp3Txt));
			queFillObj.setAnswerGrp4Txt(filterSpecChar(answerGrp4Txt));

			li.add(queFillObj);// 每次封装一空的所有四种可能性答案
		}

		return li;
	}
	
	/**
	 * @author renhengli
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("editRealQuestion")
	public String createRealQuestion(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		getClassAndSubject(request);// 获得学段
		String examQuestionId = request.getParameter("examQuestionId");
		ExamQuestion queQuestion = examService.selectExamQueById(examQuestionId);
		if(queQuestion == null){
			return "front/onlinetest/school/editRealQuestion";
		}
		// 将母题的id传给页面
		String motherQuestionId ="";

		// 处理题干和解析中的公式中的/right -> /right
		String content = queQuestion.getContent().replaceAll("\\\\", "\\\\\\\\");
		queQuestion.setContent(content);
		String resolve = queQuestion.getResolve().replaceAll("\\\\", "\\\\\\\\");

		// 将与母题关联的多个知识点以及章节集合全部以集合的形式传递到页面上去
		List<ExamQuestionRChapter> rChapters = new ArrayList<ExamQuestionRChapter>();// 获得对应的章节集合
		List<ExamQuestionRKnowledge> rKnowledges = new ArrayList<ExamQuestionRKnowledge>();// 获得对应的知识点集合

		// 获得母题的motherId 来判断在目标页面上是否显示添加子项的链接
		if (null == queQuestion.getMotherId()) {// 表示是母题
			motherQuestionId = examQuestionId;// 若是母题则直接赋值
			rChapters = queQuestion.getrChapters();// 获得对应的章节集合
			rKnowledges = queQuestion.getrKnowledges();// 获得对应的知识点集合
		} 

		queQuestion.setResolve(resolve);
		List<ExamQueFillInAnswer> quaFillList = queQuestion.getFillInAnswers();

		String answerFillAll = "";// 存放答案的字符串
		String split = ":::::::::::::";
		if (quaFillList != null && quaFillList.size() > 0) {
			String answerGrp1 = "";
			String answerGrp2 = "";
			String answerGrp3 = "";
			String answerGrp4 = "";
			for (ExamQueFillInAnswer fillAnswer : quaFillList) {
				if (null != fillAnswer.getAnswerGrp1()) {
					answerGrp1 = fillAnswer.getAnswerGrp1().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp1(answerGrp1);
				} else {
					answerGrp1 = "";
				}
				answerFillAll += answerGrp1;// 拼接每空可能性1
				answerFillAll += "∷";// 隔开
				if (null != fillAnswer.getAnswerGrp2()) {
					answerGrp2 = fillAnswer.getAnswerGrp2().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp2(answerGrp2);
				} else {
					answerGrp2 = "";
				}
				answerFillAll += answerGrp2;// 拼接每空可能性2
				answerFillAll += "∷";// 隔开
				if (null != fillAnswer.getAnswerGrp3()) {
					answerGrp3 = fillAnswer.getAnswerGrp3().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp3(answerGrp3);
				} else {
					answerGrp3 = "";
				}
				answerFillAll += answerGrp3;// 拼接每空可能性3
				answerFillAll += "∷";// 隔开
				if (null != fillAnswer.getAnswerGrp4()) {
					answerGrp4 = fillAnswer.getAnswerGrp4().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp4(answerGrp4);
				} else {
					answerGrp4 = "";
				}
				answerFillAll += answerGrp4;// 拼接每空可能性4
				answerFillAll += split;// 表示第一空的四个答案结束
			}

		}

		queQuestion.setFillAnstwerStr(answerFillAll);// 封装选项
		request.setAttribute("motherQuestionId", motherQuestionId);// 将母题的id传给页面(已过滤处理)
		request.setAttribute("quesId", examQuestionId);// 将正在编辑的本题的id传到页面
		request.setAttribute("questionObj", queQuestion);

		// 将知识点在页面进行回显处理
		String knowName = "";// 存放每个知识点的字符串
		String knowValueId = "";// 将知识点的id拼接成一个字符串 组中元素以逗号隔开 每组之间以分号隔开
		String knowId = "";// 用于进行删除操作的串(id之间没有逗号隔开)
		// 将知识点的id对应的名字进行封装成一个字符串
		if (null != rKnowledges && rKnowledges.size() > 0) {
			for (ExamQuestionRKnowledge quKnowLedge : rKnowledges) {

				String baseKnowId = quKnowLedge.getBaseKnowledgeId();
				String sub1KnowId = quKnowLedge.getBaseSubKnowledge1Id();
				String sub2KnowId = quKnowLedge.getBaseSubKnowledge2Id();
				String sub3KnowId = quKnowLedge.getBaseSubKnowledge3Id();
				String sub4KnowId = quKnowLedge.getBaseSubKnowledge4Id();
				String sub5KnowId = quKnowLedge.getBaseSubKnowledge5Id();
				if (null != baseKnowId) {
					knowName += baseKnowledgeService.selecKnowLedgeNameById(baseKnowId) + "-->";
					knowValueId += baseKnowId + ":";
					knowId += baseKnowId;
				} else {
					knowValueId += "0" + ":";
					knowId += "0";
				}
				if (null != sub1KnowId) {
					knowName += baseKnowledgeService.selecKnowLedgeNameById(sub1KnowId) + "-->";
					knowValueId += sub1KnowId + ":";
					knowId += sub1KnowId;
				} else {
					knowValueId += "0" + ":";
					knowId += "0";
				}
				if (null != sub2KnowId) {
					knowName += baseKnowledgeService.selecKnowLedgeNameById(sub2KnowId) + "-->";
					knowValueId += sub2KnowId + ":";
					knowId += sub2KnowId;
				} else {
					knowValueId += "0" + ":";
					knowId += "0";
				}
				if (null != sub3KnowId) {
					knowName += baseKnowledgeService.selecKnowLedgeNameById(sub3KnowId) + "-->";
					knowValueId += sub3KnowId + ":";
					knowId += sub3KnowId;
				} else {
					knowValueId += "0" + ":";
					knowId += "0";
				}
				if (null != sub4KnowId) {
					knowName += baseKnowledgeService.selecKnowLedgeNameById(sub4KnowId) + "-->";
					knowValueId += sub4KnowId + ":";
					knowId += sub4KnowId;
				} else {
					knowValueId += "0" + ":";
					knowId += "0";
				}
				if (null != sub5KnowId) {
					knowName += baseKnowledgeService.selecKnowLedgeNameById(sub5KnowId) + "-->";
					knowValueId += sub5KnowId + ":";
					knowId += sub5KnowId;
				} else {
					knowValueId += "0" + ":";
					knowId += "0";
				}

				// 将拼接好的字符串的最后一个逗号截掉并添加一个分号
				knowName = knowName.substring(0, knowName.length() - 3);
				knowName = knowName + ";";

				// 将拼接好的知识点id字符串最后一个逗号截掉 添加一个分号表示一组
				knowValueId = knowValueId.substring(0, knowValueId.length() - 1);
				knowValueId = knowValueId + ";";

				// 给拼接好的无逗号的知识点id末尾添加一个分号
				knowId = knowId + ";";
			}

		}

		// 将已选的章节回显到页面
		String chapterSecNameStr = "";// 初始化版本章节名字的字符串
		String chapSecIdStr = "";// 初始化版本章节id不带逗号的字符串
		String chapSecVerStr = "";// 初始化版本章节含有逗号的字符串 每组均已逗号隔开

		if (null != rChapters && rChapters.size() > 0) {
			for (ExamQuestionRChapter queRChapters : rChapters) {
				String versionId = queRChapters.getBaseVersionId();
				String volumId = queRChapters.getBaseVolumeId();
				String chapterId = queRChapters.getBaseChapterId();
				String sectionId = queRChapters.getBaseSectionId();

				if (null != versionId) {
					chapterSecNameStr += commonsService.selecVerNameById(versionId) + "-->";
					chapSecVerStr += versionId + ":";
					chapSecIdStr += versionId;
				} else {
					chapSecVerStr += "0" + ":";
					chapSecIdStr += "0";
				}
				if (null != volumId) {
					chapterSecNameStr += commonsService.selecVoluNameById(volumId) + "-->";
					chapSecVerStr += volumId + ":";
					chapSecIdStr += versionId;
				} else {
					chapSecVerStr += "0" + ":";
					chapSecIdStr += "0";
				}
				if (null != chapterId) {
					chapterSecNameStr += commonsService.selecChapNameById(chapterId) + "-->";
					chapSecVerStr += chapterId + ":";
					chapSecIdStr += versionId;
				} else {
					chapSecVerStr += "0" + ":";
					chapSecIdStr += "0";
				}
				if (null != sectionId) {
					chapterSecNameStr += commonsService.selecSectNameById(sectionId) + "-->";
					chapSecVerStr += sectionId + ":";
					chapSecIdStr += versionId;
				} else {
					chapSecVerStr += "0" + ":";
					chapSecIdStr += "0";
				}

				// 将拼接好的字符串的最后一个逗号截掉并添加一个分号
				chapterSecNameStr = chapterSecNameStr.substring(0, chapterSecNameStr.length() - 3);
				chapterSecNameStr = chapterSecNameStr + ";";
				// 将拼接好的章节id字符串最后一个逗号截掉 添加一个分号表示一组
				chapSecVerStr = chapSecVerStr.substring(0, chapSecVerStr.length() - 1);
				chapSecVerStr = chapSecVerStr + ";";
				// 给拼接好的无逗号的章节id末尾添加一个分号
				chapSecIdStr = chapSecIdStr + ";";
			}
		}

		request.setAttribute("chapterSecNameStr", chapterSecNameStr);// 拼接好的版本章节的名字
		request.setAttribute("chapSecVerStr", chapSecVerStr);// 拼接好的带有逗号的版本章节id的子串
		request.setAttribute("chapSecIdStr", chapSecIdStr);// 拼接好的不含有逗号的版本章节的子串
		request.setAttribute("rKnowIdStr", knowId);// 没有逗号分隔的知识点id
		request.setAttribute("rKnowledgeIdStr", knowValueId);// 有逗号分隔的知识点id
		request.setAttribute("rKnowledgeNameStr", knowName);

		// 根据学段，年级，学科的名字
		String semerName = commonsService.selecSemById(queQuestion.getBaseSemesterId());
		String classLevelName = commonsService.selecClassById(queQuestion.getBaseClasslevelId());
		String subjectName = commonsService.selecSubjectById(queQuestion.getBaseSubjectId());

		request.setAttribute("semerName", semerName);
		request.setAttribute("classLevelName", classLevelName);
		request.setAttribute("subjectName", subjectName);
		return "front/onlinetest/school/editRealQuestion";
	}
	
	/**
	 * @author renhengli
	 * 编辑真题试卷习题
	 * @param qstForm
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateRealQuestion")
	@ResponseBody
    public ExamQuestion updateRealQuestion(@ModelAttribute QuestionFormFields qstForm,HttpServletRequest request, HttpServletResponse response){
	     	SessionUser sessionUser = getSessionUser(request);
		//章节与知识点均与母题进行关联
	    	String currentQuestionId=qstForm.getCurrentQuestionId();
	    	String currentQuesMotherId=qstForm.getCurrentQuestionIdMotherId();
	    	//共有属性
	    	String difficulty=qstForm.getDifficultyOpts();
	  		String questionTypeOpts=qstForm.getQuestionTypeOpts();//习题类型
	  		String baseUserId=sessionUser==null?"":sessionUser.getUserId();//习题创建者
	  		String baseSemesterId=request.getParameter("semesterOpts");//学段
			String baseClasslevelId=request.getParameter("classlevelOpts");//年级
			String baseSubjectId=request.getParameter("disciplineOpts");//学科
			String examId=qstForm.getExamId();//试卷ID
		
			//封装章节
			List<ExamQuestionRChapter> queQuestList = new ArrayList<ExamQuestionRChapter>();
			if(null !=qstForm.getCh_input() && qstForm.getCh_input().length>0 ){
				for(int i=0; i<qstForm.getCh_input().length; i++){
					String[] VeVoChSec =qstForm.getCh_input()[i].split(":");//将每一个逗号分隔的字符串元素分为一个数组
					ExamQuestionRChapter queQuestionRChapter = new ExamQuestionRChapter();
					queQuestionRChapter.setExamQuestionRChapterId(UUIDUtils.getUUID());
					if("".equals(currentQuesMotherId)){
						queQuestionRChapter.setExamQuestionId(currentQuestionId);//若是母题则直接关联
					}else{
						queQuestionRChapter.setExamQuestionId(currentQuesMotherId);//若是子题关联母题
					}
					
					if(!"0".equals(VeVoChSec[0])){
						queQuestionRChapter.setBaseVersionId(VeVoChSec[0]);//一共四个  没有的0代替
					}else{
						queQuestionRChapter.setBaseVersionId("");//如果是0则存空串
					}
					if(!"0".equals(VeVoChSec[1])){
						queQuestionRChapter.setBaseVolumeId(VeVoChSec[1]);
					}else{
						queQuestionRChapter.setBaseVolumeId("");
					}
               if(!"0".equals(VeVoChSec[2])){
            	   queQuestionRChapter.setBaseChapterId(VeVoChSec[2]);
					}else{
						queQuestionRChapter.setBaseChapterId("");
					}
             if(!"0".equals(VeVoChSec[3])){
            	    queQuestionRChapter.setBaseSectionId(VeVoChSec[3]);
               }else{
            	   queQuestionRChapter.setBaseSectionId("");
               }

					queQuestList.add(queQuestionRChapter);
				}
			}
		
			//封装知识点
		    List<ExamQuestionRKnowledge> knowList = new ArrayList<ExamQuestionRKnowledge>();
		    if(null !=qstForm.getK_input() && qstForm.getK_input().length>0){
		    	
		    	for(int i=0; i<qstForm.getK_input().length; i++){
		    		
		    		String[] knowObj =qstForm.getK_input()[i].split(":");
		    		ExamQuestionRKnowledge queKnow = new ExamQuestionRKnowledge();
		    		queKnow.setExamQuestionRKnowledgeId(UUIDUtils.getUUID());
		    		
				if ("".equals(currentQuesMotherId) || currentQuesMotherId == null) {
		    			queKnow.setExamQuestionId(currentQuestionId);//若是母题则可直接关联
		    		}else{	
		    			queKnow.setExamQuestionId(currentQuesMotherId);//若是子题则直接和其母题进行关联
		    		}	
		    		//添加最后一节知识点  //倒序判断找出最后一节知识点
		    	    if(!"0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[5]);
		    	    }
		    	    if(!"0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[4]);
		    	    }
		    	    if(!"0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[3]);
		    	    }
		    	    if(!"0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[2]);
		    	    }
		    	    if( !"0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[1]);
		    	    }
                  if( !"0".equals(knowObj[0]) && "0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[0]);
		    	    }
		    		
		    		if(!"0".equals(knowObj[0])){//添加基础知识点
		    			queKnow.setBaseKnowledgeId(knowObj[0]);
		    		}else{
		    			queKnow.setBaseKnowledgeId("");
		    		}
		    		if(!"0".equals(knowObj[1])){
		    			queKnow.setBaseSubKnowledge1Id(knowObj[1]);
		    		}else{
		    			queKnow.setBaseSubKnowledge1Id("");
		    		}
		    		if(!"0".equals(knowObj[2])){
		    			queKnow.setBaseSubKnowledge2Id(knowObj[2]);
		    		}else{
		    			queKnow.setBaseSubKnowledge2Id("");
		    		}
		    		if(!"0".equals(knowObj[3])){
		    			queKnow.setBaseSubKnowledge3Id(knowObj[3]);
		    		}else{
		    			queKnow.setBaseSubKnowledge3Id("");
		    		}
		    		if(!"0".equals(knowObj[4])){
		    			queKnow.setBaseSubKnowledge4Id(knowObj[4]);
		    		}else{
		    			queKnow.setBaseSubKnowledge4Id("");
		    		}
		    		if(!"0".equals(knowObj[5])){
		    			queKnow.setBaseSubKnowledge5Id(knowObj[5]);
		    		}else{
		    			queKnow.setBaseSubKnowledge5Id("");
		    		}
                  
		    		//将封装好的知识点加入集合
		    		knowList.add(queKnow);
		    	}
		    }
		    
      //1.若只有一道题需要进行修改(即1.母题不增加子题(只修改母题) 2.修改子题(由于页面没有新增子题的链接))
		    if(1==qstForm.getQuestionSubArra().length){
		    	//如果是修改的是母题则直接获得母题的id即可(默认真题试卷的习题都是母题)
	    		ExamQuestion question = new ExamQuestion();//创建一个习题对象
			if ("".equals(currentQuesMotherId) || currentQuesMotherId == null) {// 表示本题是母题
		    		QuestionFormFields qusForm =qstForm.getQuestionSubArra()[0];
		    		qusForm.setQuestionSubTypeOpts(qstForm.getRelationalIndicator());//将习题的家族属性进行赋值
		    		question=this.getQuestion(qusForm, currentQuestionId, questionTypeOpts, baseUserId, difficulty, request, baseSemesterId, baseClasslevelId, baseSubjectId, 0,examId);
		    		try {
						//将封装好的问题的集合传过去     实现对习题的更新操作
		    			examService.updateRealQuestion(question,queQuestList,knowList,currentQuestionId);   
					} catch (Exception e) {
						e.printStackTrace();
					}		
		    	}
		    }
		    
		    //返回编辑后的习题
		    ExamQuestion examQuestion = new ExamQuestion();
			examQuestion = examService.selectExamQueById(currentQuestionId);
			return examQuestion;
	   } 

	/**
	 * @author renhengli
	 * 根据examId获取所有真题试卷的习题列表
	 * @param request
	 * @param examId
	 * @return
	 */
	@RequestMapping("getRealExamQuestions")
	@ResponseBody
	public List<ExamQuestion> getRealExamQuestions(HttpServletRequest request, String examId) {
		List<ExamQuestion> list = new ArrayList<ExamQuestion>();
		list = examService.selectByExamId(examId);
		return list;
	}
	
	/**
	 * @author renhengli
	 * 删除真题试卷临时习题
	 * @param request
	 * @param examQuestionId
	 * @return
	 */
	@RequestMapping("deleteRealQueByExamQueId")
	@ResponseBody
	public ResultJson deleteRealQueByExamQueId(HttpServletRequest request, String examQuestionId) {
		try {
			examQuestionService.deleteExamQueByExamQueId(examQuestionId);
			return new ResultJson(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultJson(false);
		}
	}
	
	
	/**
	 * @author renhengli
	 * 保存真题试卷 以及习题（编辑和新建保存公用）
	 * @param request
	 * @param examFilelds
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("saveRealExam")
	@ResponseBody
	public String saveRealExam(HttpServletRequest request,@ModelAttribute ExamFilelds examFilelds) throws Exception{
		SessionUser sessionUser = getSessionUser(request);
		String schoolId = "";
		String areaId = "";
		String baseUserId = "";
		String userType = "";
		String saveOrUpdateType = examFilelds.getSaveOrEditType();
		if (sessionUser != null) {
			schoolId = sessionUser.getSchoolId();
			areaId = sessionUser.getAreaId();
			baseUserId = sessionUser.getUserId();
			userType = sessionUser.getUserType();
		}
		// 首先更新真题试卷ExamExam,然后插入习题列表
		ExamExam exam = new ExamExam();
		BeanUtils.copyProperties(exam, examFilelds);
		exam.setBaseUserId(baseUserId); 
		examService.updateExamExam(exam, examFilelds.getExamQueList(), schoolId, areaId, saveOrUpdateType, userType);
		// 保存成功返回真题试卷列表
		return CommonsConstant.RESULT_SUCCESS;
	}
	
	/**
	 * @author renhengli
	 * 编辑真题试卷页面
	 * @param request
	 * @return
	 */
	@RequestMapping("editRealExam")
	public String editRealExam(HttpServletRequest request, String examId) {
		getClassAndSubject(request);
		// 获取10年内年份
		List<String> years = queQuestionService.getNearTenYears();
		request.setAttribute("years", years);
		request.setAttribute("tabType", "real"); 
		request.setAttribute("examId", examId); 
		//获取试卷的基本信息
		ExamExam examExam = examService.getExamById(examId);
		request.setAttribute("examExam", examExam); 
		request.setAttribute("pageName", "edit"); 
		return "front/onlinetest/school/editRealExam";
	}
	
	 /**
     * 题库学校工作台 - 试卷详情
     * @author renhengli
     * @param
     * @return 
     *
     */
    @RequestMapping("viewExam/{id}")
    public String viewExam(@PathVariable("id") String examId, Model model){
    	ExamView examView = examService.getExamViewById(examId);
		List<ExamQuestionListResult>  examQuestions = examService.getExamQuestionByExamId(examId);
		model.addAttribute("examView", examView);
		model.addAttribute("examQuestions", examQuestions);
		model.addAttribute("menuName","schoolTest");
		model.addAttribute("tabType", "real");
    	return "front/onlinetest/teacher/viewExam";	
    }
    
}
