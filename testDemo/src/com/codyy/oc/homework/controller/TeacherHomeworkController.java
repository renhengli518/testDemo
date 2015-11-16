package com.codyy.oc.homework.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.filter.SecurityWrapper;
import com.codyy.commons.image.ImageUtil;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.ResultJson;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.IdNameView;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.homework.entity.ClassLevel;
import com.codyy.oc.homework.entity.ReceiveStu;
import com.codyy.oc.homework.entity.StuComment;
import com.codyy.oc.homework.entity.WorkCommentTemplate;
import com.codyy.oc.homework.entity.WorkHomework;
import com.codyy.oc.homework.service.TeacherHomeworkService;
import com.codyy.oc.homework.service.TeacherWorkService;
import com.codyy.oc.homework.view.ReadByQueAnswerView;
import com.codyy.oc.homework.view.ReadByStuResultView;
import com.codyy.oc.homework.view.WorkCountView;

@Controller
@RequestMapping("homework")
public class TeacherHomeworkController extends BaseController {

	private static final Logger logger = Logger.getLogger(TeacherHomeworkController.class);

	@Autowired
	private CommonsService commonsService;

	@Autowired
	private TeacherHomeworkService teacherHomeworkService;

	@Autowired
	private TeacherWorkService teacherWorkService;
	/**
	 * 作业的入口
	 * */
	@RequestMapping("index")
	public String index(HttpServletRequest request,String studentUserId){
		SessionUser sessionUser = getSessionUser(request);
		if(CommonsConstant.USER_TYPE_TEACHER.equals(sessionUser.getUserType())){
			//如果是老师
			return "redirect:../teacherWork/toTeacherWork.html";
		}else if (CommonsConstant.USER_TYPE_STUDENT.equals(sessionUser.getUserType())){
			return "redirect:../studentWork/toStudentWork.html";
		}else if (CommonsConstant.USER_TYPE_PARENT.equals(sessionUser.getUserType())){
			return "redirect:../parentWork/toParentWork.html?studentUserId="+studentUserId;
		}else if(CommonsConstant.USER_TYPE_CLASS.equals(sessionUser.getUserType())){
			return "redirect:../classWork/toClassWork.html"; 
		}else{
			return CommonsConstant.ERROR_PAGE_404;
		}
	}
	/**
	 * 老师布置作业页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("createHomeworkPre")
	public String createHomeworkPre(HttpServletRequest request) {
		String userId = getSessionUserId(request);
		request.setAttribute("subjects", commonsService.getTeacherSubjects(userId));
		return "front/homework/teacher/addHomework";
	}

	/**
	 * 选择习题
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selectQuestionPre")
	public String selectQuestionPre(HttpServletRequest request) {
		List<BaseSemester> semesters = commonsService.getAllSemester();
		request.setAttribute("semesters", semesters);
		List<SelectModel> versions = commonsService.getAllVersions();
		request.setAttribute("versions", versions);
		return "front/homework/teacher/selectQuestion";
	}

	/**
	 * 已选习题
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selectedQuestionPre")
	public String selectedQuestionPre(HttpServletRequest request) {
		return "front/homework/teacher/selectedQuestion";
	}

	/**
	 * 选择学生
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selectStudentsPre")
	public String selectStudentsPre(HttpServletRequest request) {
		return "front/homework/teacher/selectStudents";
	}

	/**
	 * 获取习题
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectQuestion")
	public Page selectQuestion(HttpServletRequest request, Page page, String type, String baseSemesterId, String baseClasslevelId,
			String baseSubjectId, String baseVersionId, String baseVolumeId, String baseChapterId) {
		SessionUser sessionUser = getSessionUser(request);
		String areaId = sessionUser.getAreaId();
		String schoolId = sessionUser.getSchoolId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areaId", areaId);
		map.put("schoolId", schoolId);

		map.put("type", type);
		map.put("baseSemesterId", baseSemesterId);
		map.put("baseClasslevelId", baseClasslevelId);
		map.put("baseSubjectId", baseSubjectId);
		map.put("baseVersionId", baseVersionId);
		map.put("baseVolumeId", baseVolumeId);
		map.put("baseChapterId", baseChapterId);
		page.setMap(map);

		teacherHomeworkService.getSelectQuestion(page);
		return page;
	}

	/**
	 * 已选习题数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectedQuestion")
	public Page selectedQuestion(HttpServletRequest request, Page page, String queIds) {
		if (StringUtils.isBlank(queIds)) {
			page.setTotal(0);
			page.setData(new ArrayList<>());
			return page;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queIds", queIds.split(","));
		page.setMap(map);
		teacherHomeworkService.getSelectedQuestion(page);
		return page;
	}

	/**
	 * 老师布置作业
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createHomework")
	public ResultJson createHomework(HttpServletRequest request, WorkHomework homework, String questions, String receiveStus,
			String readStus, String fileStrs) {
		String userId = getSessionUserId(request);
		try {
			homework.setBaseUserId(userId);
			homework.setCreateTime(new Date());
			if (CommonsConstant.HOMEWORK_STATUS_PROGRESS.equals(homework.getStatus())) {
				homework.setAssignTime(new Date());
			}
			teacherHomeworkService.createHomework(homework, questions, receiveStus, readStus, fileStrs);
			if (StringUtils.isNotBlank(fileStrs)) {
				JSONArray array = JSONArray.fromObject(fileStrs);
				if (!array.isEmpty()) {
					for (int i = 0; i < array.size(); i++) {
						ImageUtil.tryRemoveUploadImage(request, array.getJSONObject(i).getString("path"));
					}
				}
			}
			return new ResultJson(true);
		} catch (RuntimeException e) {
			logger.info(e);
			return new ResultJson(false, e.getMessage());
		} catch (Exception e) {
			logger.info(e);
			return new ResultJson(false, "老师布置作业错误！");
		}
	}

	/**
	 * 老师修改作业页面
	 * 
	 * @param request
	 * @param homeworkId
	 * @return
	 */
	@RequestMapping("editHomeworkPre")
	public String editHomeworkPre(HttpServletRequest request, @RequestParam String homeworkId) {
		String userId = getSessionUserId(request);
		WorkHomework homework = teacherHomeworkService.getHomeworkById(homeworkId);
		if (homework != null) {
			request.setAttribute("homework", homework);
			request.setAttribute("subjects", commonsService.getTeacherSubjects(userId));
			request.setAttribute("docs", teacherHomeworkService.getWorkDocByHomework(homeworkId));
			request.setAttribute("qIds", teacherHomeworkService.getHomeworkQuestionIds(homeworkId));
			if (CommonsConstant.HOMEWORK_ASSIGN_TYPE_CUSTOM.equals(homework.getAssignType())) {
				request.setAttribute("receiveStus", teacherHomeworkService.getWorkReceiveStuByHomework(homeworkId));
			}
			if (CommonsConstant.HOMEWORK_READ_OVER_TYPE_STU_SPECIFIED.equals(homework.getReadOverType())) {
				request.setAttribute("readStu", teacherHomeworkService.getReadOverStuByHomework(homeworkId));
			}
			return "front/homework/teacher/editHomework";
		} else {
			return CommonsConstant.ERROR_PAGE_404;
		}
	}

	/**
	 * 老师编辑作业
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editHomework")
	public ResultJson editHomework(HttpServletRequest request, WorkHomework homework, String questions, String receiveStus,
			String readStus, String fileStrs) {
		try {
			WorkHomework oldHomework = teacherHomeworkService.getHomeworkById(homework.getWorkHomeworkId());
			if (oldHomework != null) {
				oldHomework.setBaseSubjectId(homework.getBaseSubjectId());
				oldHomework.setWorkTitle(homework.getWorkTitle());
				oldHomework.setAssignType(homework.getAssignType());
				oldHomework.setReadOverType(homework.getReadOverType());
				oldHomework.setStatus(homework.getStatus());
				if (CommonsConstant.HOMEWORK_STATUS_PROGRESS.equals(homework.getStatus())) {
					oldHomework.setAssignTime(new Date());
				}
				oldHomework.setFinishTime(homework.getFinishTime());
				oldHomework.setTextQueContent(homework.getTextQueContent());
				teacherHomeworkService.editHomework(oldHomework, questions, receiveStus, readStus, fileStrs);
				if (StringUtils.isNotBlank(fileStrs)) {
					JSONArray array = JSONArray.fromObject(fileStrs);
					if (!array.isEmpty()) {
						for (int i = 0; i < array.size(); i++) {
							ImageUtil.tryRemoveUploadImage(request, array.getJSONObject(i).getString("path"));
						}
					}
				}
				return new ResultJson(true);
			} else {
				return new ResultJson(false, "作业不存在！");
			}
		} catch (RuntimeException e) {
			logger.info(e);
			return new ResultJson(false, e.getMessage());
		} catch (Exception e) {
			logger.info(e);
			return new ResultJson(false, "老师编辑作业错误！");
		}
	}

	/**
	 * 老师批阅页面
	 * 
	 * @param request
	 * @param homeworkId
	 * @return
	 */
	@RequestMapping("readOverHomework")
	public String readOverHomework(HttpServletRequest request, @RequestParam(required = true) String homeworkId, String classId) {
		WorkHomework homework = teacherHomeworkService.getHomeworkById(homeworkId);
		if (homework != null) {
			List<IdNameView> classList = teacherHomeworkService.getHomeworkClass(homeworkId);
			if (StringUtils.isBlank(classId)) {
				classId = classList.get(0).getId();
			}
			int num[] = teacherHomeworkService.getQueUncheckedNum(classId, homework);
			request.setAttribute("classList", classList);
			request.setAttribute("checkedNum", num[1]);
			request.setAttribute("totalNum", num[0]);
			request.setAttribute("homeworkId", homeworkId);
			request.setAttribute("classId", classId);
			request.setAttribute("answerNum", teacherHomeworkService.getAnswerStudentNum(classId, homeworkId));
			return "front/homework/teacher/readList";
		} else {
			return CommonsConstant.ERROR_PAGE_404;
		}
	}

	/**
	 * 按学生批阅
	 * 
	 * @param request
	 * @param homeworkId
	 * @param classId
	 * @param page
	 */
	@ResponseBody
	@RequestMapping("readOverStudent")
	public Page readOverStudent(HttpServletRequest request, @RequestParam(required = true) String homeworkId,
			@RequestParam(required = true) String classId, @RequestParam(required = true) String sort, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workHomeworkId", homeworkId);
		map.put("baseClassId", classId);
		map.put("sort", sort);
		page.setMap(map);
		teacherHomeworkService.getReadOverClassStudent(page);
		return page;
	}

	/**
	 * 按题批阅页面
	 * 
	 * @param request
	 * @param homeworkId
	 * @param classId
	 * @return
	 */
	@RequestMapping("readByQuestionPre")
	public String readByQuestionPre(HttpServletRequest request, @RequestParam(required = true) String homeworkId,
			@RequestParam(required = true) String classId) {
		WorkHomework homework = teacherHomeworkService.getHomeworkById(homeworkId);
		if (homework != null) {
			int[] num = teacherHomeworkService.getQueUncheckedNum(classId, homework);
			request.setAttribute("unReadNum", num[0] - num[1]);
			request.setAttribute("totalNum", num[0]);
			request.setAttribute("students", teacherHomeworkService.getReadByQueStu(classId, homeworkId));
			request.setAttribute("homework", teacherHomeworkService.getReadByQueQuestion(homework,classId));
			request.setAttribute("homeworkId", homeworkId);
			request.setAttribute("classId", classId);
			return "front/homework/teacher/readByQuestion";
		} else {
			return CommonsConstant.ERROR_PAGE_404;
		}
	}

	/**
	 * 获取习题回答信息
	 * 
	 * @param request
	 * @param workReceiveStuId
	 * @param homeworkId
	 * @param classId
	 * @param workQueId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getQuestionAnswer")
	public ReadByQueAnswerView getQuestionAnswer(HttpServletRequest request, @RequestParam(required = true) String workReceiveStuId,
			@RequestParam(required = true) String homeworkId, @RequestParam(required = true) String classId,
			@RequestParam(required = true) String workQueId) {
		return teacherHomeworkService.getQuestionAnswer(classId, homeworkId, workReceiveStuId, workQueId);
	}

	/**
	 * 获取文本作业回答信息
	 * 
	 * @param request
	 * @param workReceiveStuId
	 * @param homeworkId
	 * @param classId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTextHomeWorkAnswer")
	public ReadByQueAnswerView getTextHomeWorkAnswer(HttpServletRequest request, @RequestParam(required = true) String workReceiveStuId,
			@RequestParam(required = true) String homeworkId, @RequestParam(required = true) String classId) {
		return teacherHomeworkService.getTextHomeWorkAnswer(classId, homeworkId, workReceiveStuId);
	}

	/**
	 * 获取附件作业回答信息
	 * 
	 * @param request
	 * @param workReceiveStuId
	 * @param homeworkId
	 * @param classId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getFileHomeWorkAnswer")
	public ReadByQueAnswerView getFileHomeWorkAnswer(HttpServletRequest request, @RequestParam(required = true) String workReceiveStuId,
			@RequestParam(required = true) String homeworkId, @RequestParam(required = true) String classId) {
		return teacherHomeworkService.getFileHomeWorkAnswer(classId, homeworkId, workReceiveStuId);
	}

	/**
	 * 按题批阅
	 * 
	 * @param request
	 * @param homeworkId
	 * @param classId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("readByQuestion")
	public ResultJson readByQuestion(HttpServletRequest request, @RequestParam(required = true) String homeworkId,
			@RequestParam(required = true) String classId, String workRecStuQueAnswerId, String teacherComment, String workReceiveStuId,
			@RequestParam(required = true) String type) {
		WorkHomework homework = teacherHomeworkService.getHomeworkById(homeworkId);
		if (homework != null) {
			try {
				teacherHomeworkService.saveReadByQuestion(workRecStuQueAnswerId, teacherComment, workReceiveStuId, type);
				int num[] = teacherHomeworkService.getQueUncheckedNum(classId, homework);
				ImageUtil.removeUploadImageByFilteringContent(request, teacherComment);
				return new ResultJson(true, String.valueOf(num[0] - num[1]));
			} catch (Exception e) {
				logger.info(e);
				return new ResultJson(false, "按题批阅失败！");
			}
		} else {
			return new ResultJson(false, "作业不存在！");
		}
	}

	/**
	 * 按学生批阅
	 * @param request
	 * @param homeworkId
	 * @param workReceiveStuId
	 * @return
	 */
	@RequestMapping("readByStudentPre")
	public String readByStudentPre(HttpServletRequest request, @RequestParam(required = true) String homeworkId,
			@RequestParam(required = true) String workReceiveStuId) {
		request.setAttribute("type", "studentRead");
		String userId=getSessionUserId(request);
		String userName = teacherWorkService.getUserNameById(userId);
		WorkHomework homework = teacherHomeworkService.getHomeworkById(homeworkId);
		if (homework != null) {
			ReadByStuResultView view = teacherHomeworkService.getReadByStudentInfo(homework, workReceiveStuId);
			request.setAttribute("homework", view);
			request.setAttribute("userId", userId);
			request.setAttribute("userName", userName);
			request.setAttribute("work", homework);
			request.setAttribute("queTypeTitleName", CommonsConstant.QUESTION_TYPE_MAP);
			return "front/homework/teacher/readByStudent";
		} else {
			return CommonsConstant.ERROR_PAGE_404;
		}

	}
	/*
	 * 作业统计
	 * */
	@RequestMapping("/workCount")
	public String workCount(String pid,String type,HttpServletRequest request,@RequestParam(required = true) String workId,String classId,String objType){
		WorkHomework homework = teacherHomeworkService.getHomeworkById(workId);
		ClassLevel classLevel = null;
		if (homework != null) {
			List<IdNameView> classList = teacherHomeworkService.getHomeworkClass(workId);
			if (StringUtils.isBlank(classId)) {
				classId = classList.get(0).getId();
			}
			classLevel = teacherHomeworkService.findClassNameAndClassLevelByClassId(classId);
			WorkCountView workCountView = teacherHomeworkService.getWorkCountByWorkQueCount(pid,type,workId,classId);
			request.setAttribute("workCountView",workCountView);
			request.setAttribute("classList", classList);
			request.setAttribute("workId", workId);
			request.setAttribute("homework", homework);
			request.setAttribute("classId", classId);
			request.setAttribute("classLevel", classLevel);
			request.setAttribute("objType", objType);
			return "front/homework/teacher/workCount";
		} else {
			return CommonsConstant.ERROR_PAGE_404;
		}
		
	}
	/*
	 * 刷新学生信息
	 * */
	@RequestMapping("/getReceiveStu")
	@ResponseBody
	public List<ReceiveStu> getReceive(String pid,String type,HttpServletRequest request,@RequestParam(required = true) String workId, String classId){
		List<ReceiveStu> receiveStuList = teacherHomeworkService.getReceiveStuInfo(pid, type, workId, classId);
		return receiveStuList;
	}
	/*
	 * 添加常用评论
	 * */
	@ResponseBody
	@RequestMapping("/addComment")
	public ResultJson addComment(@RequestParam String comment, HttpServletRequest request){
		String userId = getSessionUserId(request);
		String uuid = UUIDUtils.getUUID();
		Date date = new Date();
		try {
			teacherHomeworkService.addComment(uuid,userId,comment,date);
			return new ResultJson(true);
		} catch (Exception e) {
			return new ResultJson(false,"添加常用评论出错");
		}
		
	}
	/*
	 * 查询老师评论
	 * */
	@ResponseBody
	@RequestMapping("findComments")
	public List<WorkCommentTemplate> findComments(HttpServletRequest request){
		String userId = getSessionUserId(request);
		return teacherHomeworkService.findComments(userId);
		
	}
	/*
	 * 提交批阅
	 * */
	@ResponseBody
	@RequestMapping("correctHomework")
	public ResultJson correctExercises(@RequestParam("workId") String workId,@RequestParam String homeworkReceiverId,
			@RequestParam String ExercisesCommonets,
			@RequestParam String homeworkTextCommonet,
			@RequestParam String homeworkAnnexCommonet,
			@RequestParam String homeworkGeneralCommonet,
			HttpServletRequest request){
		String userId = getSessionUserId(request);
		StuComment stuComment = new StuComment();
		stuComment.setHomeWorkId(workId);
		stuComment.setHomeworkReceiverId(homeworkReceiverId);
		try {
			//学生习题评语的提交
			JSONArray jsonArray = JSONArray.fromObject(ExercisesCommonets);
			List<Map<String,String>> queMap = new ArrayList<Map<String,String>>();
			for(int i=0;i<jsonArray.size();i++){
				Map<String,String> qMap = new HashMap<String,String>();
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				qMap.put("questionId", jsonObject.get("id").toString());
				qMap.put("comment", jsonObject.get("comment").toString());
				qMap.put("receiveStuId", homeworkReceiverId);
				queMap.add(qMap);
				
			}
			stuComment.setExercisesCommonets(queMap);
			stuComment.setHomeworkTextCommonet(homeworkTextCommonet);
			stuComment.setHomeworkAnnexCommonet(homeworkAnnexCommonet);
			stuComment.setHomeworkGeneralCommonet(SecurityWrapper.tansValue(homeworkGeneralCommonet));
			
			teacherHomeworkService.saveComments(stuComment);
			return new ResultJson(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultJson(false, "作业批阅失败");
		}
	}
	
}
