package com.codyy.oc.questionlib.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.ResultJson;
import com.codyy.commons.utils.StringUtils;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.commons.web.CustomMultipartResolver;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.form.QuestionListCriteria;
import com.codyy.oc.base.parse.ErrorInfo;
import com.codyy.oc.base.parse.QuestionInfo;
import com.codyy.oc.base.parse.ReadWordUtil;
import com.codyy.oc.base.service.BaseKnowledgeService;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.questionlib.entity.QueFavorite;
import com.codyy.oc.questionlib.entity.QueFillInAnswer;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.entity.QueQuestionRChapter;
import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;
import com.codyy.oc.questionlib.service.QueFavoriteService;
import com.codyy.oc.questionlib.service.QueQuestionService;

@Controller
@RequestMapping("questionLib")
public class QuestionLibController extends BaseController {
	@Autowired
	private CommonsService commonsService;

	@Autowired
	private QueQuestionService queQuestionService;

	@Autowired
	private QueFavoriteService queFavoriteService;

	@Autowired
	private BaseKnowledgeService baseKnowledgeService;

	/*
	 * 转向共享题库页面
	 */
	@RequestMapping("toSharedLib")
	public String toSharedLib(HttpServletRequest request) {
		request.setAttribute("tabType", "share");
		getClassAndSubject(request);
		return "front/questionlib/sharedLib";
	}

	/**
	 * @author lichen
	 * @Title: toCreateQuestion
	 * @Description: TODO(跳转到新建习题页面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("tocreatequestion")
	public String toCreateQuestion(HttpServletRequest request) {
		request.setAttribute("tabType", "own");
		getClassAndSubject(request);
		//获得当前登录的用户对象
		SessionUser sessionUser = getSessionUser(request);
		 String userType =sessionUser.getUserType();
		 request.setAttribute("userType", userType);
		return "front/questionlib/createQuestion";
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
		return queQuestionService.getShareQuePageList(page, map1);
	}

	/**
	 * 根据Id 获取question
	 * 
	 * @param request
	 * @param queQuestionId
	 * @return
	 */
	@RequestMapping("getQuestionById")
	@ResponseBody
	public QueQuestion getQuestionById(HttpServletRequest request, String queQuestionId) {
		return queQuestionService.getQuestionById(queQuestionId);
	}

	/**
	 * 收藏习题
	 * 
	 * @param request
	 * @param queFavoriteId
	 * @throws Exception
	 */
	@RequestMapping("toCollection")
	@ResponseBody
	public QueFavorite toCollection(HttpServletRequest request, String queQuestionId) throws Exception {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonsConstant.SESSION_USER);
		String baseUserId = "";
		QueFavorite queFavorite  = new QueFavorite();
		if (sessionUser != null) {
			baseUserId = sessionUser.getUserId();
		}
		try {
			queFavorite = queFavoriteService.insert(queQuestionId, baseUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queFavorite;
	}

	/**
	 * 取消收藏习题
	 * 
	 * @param request
	 * @param queFavoriteId
	 */
	@RequestMapping("cancelCollection")
	@ResponseBody
	public ResultJson cancelCollection(HttpServletRequest request, String queFavoriteId) {
		try {
			queFavoriteService.deleteByPrimaryKey(queFavoriteId);
			return new ResultJson(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResultJson(false, "取消收藏习题失败");
		}
	}

	/**
	 * 习题审核弹出页面
	 * 
	 * @param request
	 * @param queQuestionId
	 * @return
	 */
	@RequestMapping("auditing")
	public String auditing(HttpServletRequest request, String queQuestionId) {
		request.setAttribute("queQuestionId", queQuestionId);
		return "front/questionlib/auditing";
	}

	/**
	 * 更新习题审核状态
	 * 
	 * @param request
	 * @param queQuestionId
	 * @return
	 */
	@RequestMapping("auditShareQuestion")
	@ResponseBody
	public QueQuestion auditShareQuestion(HttpServletRequest request, String auditStatus, String queQuestionId) {
		if (StringUtils.isBlank(queQuestionId)) {
			return null;
		}
		if (StringUtils.isBlank(auditStatus)) {
			return null;
		}
		QueQuestion result = queQuestionService.updateAuditStatusByKey(queQuestionId, auditStatus);
		return result;
	}

	/**
	 * 我的习题列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("toOwnLib")
	public String toOwnLib(HttpServletRequest request) {
		request.setAttribute("tabType", "own");
		getClassAndSubject(request);
		return "front/questionlib/ownLib";
	}

	/**
	 * 查询我的习题列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("searchOwnList")
	@ResponseBody
	public Page searchOwnList(HttpServletRequest request, Page page) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonsConstant.SESSION_USER);
		String baseUserId = "";
		if (sessionUser != null) {
			baseUserId = sessionUser.getUserId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
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
		map.put("relationalIndicator", request.getParameter("relationalIndicator"));// 是否包含孪生题、衍生题
		map.put("difficulty", request.getParameter("difficulty"));// 难易度
		map.put("auditStatus", request.getParameter("auditStatus"));// 审核状态（教师使用）
		map.put("baseUserId", baseUserId);
		page.setMap(map);
		return queQuestionService.getOwnQuePageList(page);
	}

	/**
	 * 删除习题
	 * 
	 * @param request
	 * @param queQuestionId
	 * @return
	 */
	@RequestMapping("deleteOwnQuestion")
	@ResponseBody
	public ResultJson deleteOwnQuestion(HttpServletRequest request, String queQuestionId) {
		Boolean result = queQuestionService.deleteOwnQuestion(queQuestionId);
		if (result) {
			return new ResultJson(true, "删除习题成功！");
		} else {
			return new ResultJson(true, "删除习题失败！");
		}
	}

	// --------------------------lichen
	// start------------------------------------------
	/**
	 * @author lichen
	 * @Title: toEditQuestion
	 * @Description: TODO(跳转到编辑习题页面(跳转之前根据习题id来获取对应习题的所有内容))
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws 343f994c72e14b29bc1d810544c64679 填空
	 *         1e9e236481b44e8eb747b40c039eed45 单选
	 *         66ffc1c2773c4efe90d9a7647a27f449 多选
	 *         c2c7603c80fa47528f4d646744f02b81 判断
	 *         99c8a8e2af194177a9dacab2b2606108 问答
	 *         e441acb1f2a648519b814a72ba4d644d 计算题
	 */
	@RequestMapping("toeditquestion")
	public String toEditQuestion(HttpServletRequest request) {
		request.setAttribute("tabType", "own");
		getClassAndSubject(request);// 获得学段
		String quesId = request.getParameter("questionId");
		QueQuestion queQuestion = queQuestionService.selectQuesById(quesId);
		// 如果是母题直接查出，如果是子题则通过motherId查出知识点与章节

		// 将母题的id传给页面
		String motherQuestionId;

		// 处理题干和解析中的公式中的/right -> /r ight
		String content = queQuestion.getContent().replaceAll("\\\\", "\\\\\\\\");
		queQuestion.setContent(content);
		String resolve = queQuestion.getResolve().replaceAll("\\\\", "\\\\\\\\");

		// 将与母题关联的多个知识点以及章节集合全部以集合的形式传递到页面上去
		List<QueQuestionRChapter> rChapters;// 获得对应的章节集合
		List<QueQuestionRKnowledge> rKnowledges;// 获得对应的知识点集合

		// 获得母题的motherId 来判断在目标页面上是否显示添加子项的链接
		if (null == queQuestion.getMotherId()) {// 表示是母题
			motherQuestionId = quesId;// 若是母题则直接赋值
			rChapters = queQuestion.getrChapters();// 获得对应的章节集合
			rKnowledges = queQuestion.getrKnowledges();// 获得对应的知识点集合
			
			//如果是母题则获取当前编辑的母题拥有的子题个数
			Integer countSons =queQuestionService.countMotherSon(queQuestion.getQueQuestionId());
			request.setAttribute("countSons", countSons);
		} else {
			// 子题 则通过母题的motherId来获得关联的知识点
			QueQuestion motherQuestion = queQuestionService.selectQuesById(queQuestion.getMotherId());
			rChapters = motherQuestion.getrChapters();// 通过母题获得子题关联的章节
			rKnowledges = motherQuestion.getrKnowledges();// 通过母题获得子集的关联的知识点
			// 若是子题则获得母题id
			motherQuestionId = queQuestion.getMotherId();// 获得母题的id
		}

		queQuestion.setResolve(resolve);
		List<QueFillInAnswer> quaFillList = queQuestion.getFillInAnswers();

		String answerFillAll = "";// 存放答案的字符串
		String split = ":::::::::::::";
		if (quaFillList != null && quaFillList.size() > 0) {
			String answerGrp1 = "";
			String answerGrp2 = "";
			String answerGrp3 = "";
			String answerGrp4 = "";
			for (QueFillInAnswer fillAnswer : quaFillList) {
				if (null != fillAnswer.getAnswerGrp1()) {
					answerGrp1 = fillAnswer.getAnswerGrp1().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp1(answerGrp1);
				} else {
					answerGrp1 = "";
				}
				answerFillAll += answerGrp1;// 拼接每空可能性1
				answerFillAll += QuestionListCriteria.OPT_SEPERATE;// 隔开
				if (null != fillAnswer.getAnswerGrp2()) {
					answerGrp2 = fillAnswer.getAnswerGrp2().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp2(answerGrp2);
				} else {
					answerGrp2 = "";
				}
				answerFillAll += answerGrp2;// 拼接每空可能性2
				answerFillAll += QuestionListCriteria.OPT_SEPERATE;// 隔开
				if (null != fillAnswer.getAnswerGrp3()) {
					answerGrp3 = fillAnswer.getAnswerGrp3().replaceAll("\\\\", "\\\\\\\\");
					fillAnswer.setAnswerGrp3(answerGrp3);
				} else {
					answerGrp3 = "";
				}
				answerFillAll += answerGrp3;// 拼接每空可能性3
				answerFillAll += QuestionListCriteria.OPT_SEPERATE;// 隔开
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
		// System.out.println("answerFillAll"+answerFillAll);
		// System.out.println(queQuestion);
		request.setAttribute("motherQuestionId", motherQuestionId);// 将母题的id传给页面(已过滤处理)
		request.setAttribute("quesId", quesId);// 将正在编辑的本题的id传到页面
		request.setAttribute("questionObj", queQuestion);

		// 将知识点在页面进行回显处理
		String knowName = "";// 存放每个知识点的字符串
		String knowValueId = "";// 将知识点的id拼接成一个字符串 组中元素以逗号隔开 每组之间以分号隔开
		String knowId = "";// 用于进行删除操作的串(id之间没有逗号隔开)
		// 将知识点的id对应的名字进行封装成一个字符串
		if (null != rKnowledges && rKnowledges.size() > 0) {
			for (QueQuestionRKnowledge quKnowLedge : rKnowledges) {

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

			for (QueQuestionRChapter queRChapters : rChapters) {

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
		
		 SessionUser sessionUser = getSessionUser(request);
		 String userType =sessionUser.getUserType();
		 request.setAttribute("userType", userType);
		 
		return "front/questionlib/editQuestion";

	}

	/**
	 * @author lichen
	 * @Title: openUeditPage
	 * @Description: TODO(点击选项打开富文本框 选择题)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("openueditpage")
	public String openUeditPage() {

		return "front/questionlib/optionEditorPopup";
	}

	/**
	 * @author lichen
	 * @Title: openfillPage
	 * @Description: TODO(填写填空题内容弹框)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("openfillpage")
	public String openfillPage() {

		return "front/questionlib/fillInEditorPopup";
	}

	/**
	 * @author lichen
	 * @Title: toMyQuestion
	 * @Description: TODO(跳转到我的习题)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("tomyquestion")
	public String toMyQuestion(HttpServletRequest request) {
		getClassAndSubject(request);
		return "front/questionlib/myQuestion";
	}
	
	/**
	 * 
	 * forward2UploadPage:(弹出框  弹出上传页面)
	 *
	 * @return
	 * @author lichen
	 */
	@RequestMapping("uploadQuestionPage")
	public String forward2UploadQuestionPage(String type,Model model) {
		model.addAttribute("type",type);
		return "front/questionlib/uploadQuestion" ;
	}
	
	/**
	 * 
	 *
	 * @param req
	 * @param response
	 * @param writer
	 * @return
	 * @throws Exception   导入习题
	 * @author lichen
	 */
	@RequestMapping("uploadQuestion")
	@ResponseBody
	public Map<String,Object> uploadQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
//		log.info("执行Excel文件上传......");
		CustomMultipartResolver resolver = new CustomMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(500*1024*1024); 
		resolver.setServletContext(request.getSession().getServletContext());
		resolver.setMaxInMemorySize(5*1024*1024);
		response.setContentType("text/html;charset=UTF-8");
		MultipartHttpServletRequest multiRequest = null ;
		
		try {
			multiRequest = resolver.resolveMultipart(request);
		} catch (MaxUploadSizeExceededException e) {
			result.put("flag", false);
			result.put("message", "文件过大！");
//			log.debug("multiFile is big");
			return result ;
		}
		
		CommonsMultipartFile file  = (CommonsMultipartFile) multiRequest.getFile("uploadFile");
		String fileName 		   = file.getOriginalFilename() ;
		String suffix			   = StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, "."),fileName.length());
		String userType 		   = StringUtils.substring(fileName, 0, StringUtils.lastIndexOf(fileName, ".")) ;
		InputStream in 			   = file.getInputStream() ;
		
		String tempName 	= UUIDUtils.getUUID() ;
		String tempPath 	= request.getServletContext().getRealPath("/public/common/upload_modal") + File.separator + tempName + suffix ;
		File descFile 		= new File(tempPath) ;
		
		// ==== 拷贝文件流到临时目录
		FileUtils.copyInputStreamToFile(in, descFile);
		
		// === 文件上传完毕，开始解析Excel
//		String jsonStr = analysisExcel(tempPath, userType, request) ;
		 List<ErrorInfo> infoList = new ArrayList<ErrorInfo>();
		 List<QuestionInfo> questionInfo = new ArrayList<QuestionInfo>();
		 InputStream fin = new FileInputStream(descFile);
		 if((suffix.replace(".", "")).equalsIgnoreCase(ReadWordUtil.WORD_TYPE_DOC)){
			  questionInfo = ReadWordUtil.readQuestionList(fin, ReadWordUtil.WORD_TYPE_DOC, infoList);
		 }else{
			  questionInfo = ReadWordUtil.readQuestionList(fin, ReadWordUtil.WORD_TYPE_DOCX, infoList);
		 }
		 
		 SessionUser sessionUser = getSessionUser(request);
		 String baseUserId=sessionUser==null?"":sessionUser.getUserId();//习题创建者
		 String schoolId=sessionUser==null?"":sessionUser.getSchoolId();
		 String areaId=sessionUser==null?"":sessionUser.getAreaId();
		 String usersType=sessionUser==null?"":sessionUser.getUserType();//用户的角色
			 result = queQuestionService.uploadQuestion(questionInfo, infoList,baseUserId,schoolId,areaId,usersType,false);
//		 
			 System.out.println("result="+result);
//		 writer.write(jsonStr) ; 
		// === 文件导入完毕后，删除临时文件
		FileUtils.deleteQuietly(descFile) ;
		return result ;
	}

}
