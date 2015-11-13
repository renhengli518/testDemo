package com.codyy.oc.onlinetest.controller.teacher;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.ResultJson;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.service.BaseKnowledgeService;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.entity.ExamExam;
import com.codyy.oc.onlinetest.entity.ExamQueFillInAnswer;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionRChapter;
import com.codyy.oc.onlinetest.entity.ExamQuestionRKnowledge;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.questionlib.form.QuestionFormFields;
import com.codyy.oc.questionlib.service.CreateQuestionLibService;
import com.codyy.oc.questionlib.service.QueQuestionService;

/**
 * @version 1.0
 * @author renhengli
 * 
 */
@Controller
@RequestMapping("teacherTest")
public class TeacherRealExamController extends BaseController {

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

	private String filterSpecChar(String oldChar) {
		return (oldChar == null ? "" : oldChar.replaceAll("<br>", "").replaceAll("\r\n", " "));
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
		return "front/onlinetest/teacher/realExam";
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
		map.put("year", request.getParameter("year"));// 年份
		map.put("examTypeId", request.getParameter("examTypeId"));// 试卷类型
		map.put("schoolId", sessionUser == null ? "" : sessionUser.getSchoolId());// 创建人
		map.put("createTimeSort", request.getParameter("createTimeSort"));// 时间排序
		map.put("useCountSort", request.getParameter("useCountSort"));// 使用次数排序
		page.setMap(map);
		page = examService.getRealExamPageList(page, map);
		return page;
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
			return "front/onlinetest/teacher/editRealQuestion";
		}
		// 将母题的id传给页面
		String motherQuestionId ="";

		// 处理题干和解析中的公式中的/right -> /r ight
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

		// 将所有子串的最后一个分号删除
		/*
		 * knowId=knowId.substring(0,knowId.length()-1);
		 * knowValueId=knowValueId.substring(0,knowValueId.length()-1);
		 * knowName=knowName.substring(0,knowName.length()-1);
		 */

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
		return "front/onlinetest/teacher/editRealQuestion";
	}

}
