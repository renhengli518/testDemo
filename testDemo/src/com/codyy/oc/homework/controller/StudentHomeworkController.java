package com.codyy.oc.homework.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.image.ImageUtil;
import com.codyy.commons.utils.ResultJson;
import com.codyy.oc.BaseController;
import com.codyy.oc.homework.entity.WorkReceiveStu;
import com.codyy.oc.homework.service.StudentHomeworkService;
import com.codyy.oc.homework.view.AnswerHomeworkView;

@Controller
@RequestMapping("homework")
public class StudentHomeworkController extends BaseController {

	private static final Logger logger = Logger.getLogger(StudentHomeworkController.class);

	@Autowired
	private StudentHomeworkService studentHomeworkService;

	/**
	 * 答题页面
	 * 
	 * @param request
	 * @param homeworkId
	 * @return
	 */
	@RequestMapping("answerHomeworkPre")
	public String answerHomeworkPre(HttpServletRequest request, @RequestParam(required = true) String homeworkId) {
		 request.setAttribute("type", "studentWork");
		AnswerHomeworkView view = studentHomeworkService.getAnswerHomeworkInfo(homeworkId);
		if (view == null) {
			return CommonsConstant.ERROR_PAGE_404;
		}
		request.setAttribute("homework", view);
		request.setAttribute("queTypeTitleName", new HashMap<String, Object>());
		return "front/homework/student/answerHomework";
	}

	/**
	 * 填空题弹框
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("fillInEditorPopup")
	public String fillInEditorPopup(HttpServletRequest request) {
		return "front/homework/student/fillInEditorPopup";
	}

	/**
	 * 学生答题
	 * 
	 * @param request
	 * @param homeworkId
	 * @param questionResult
	 * @param txtResult
	 * @param fileResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping("answerHomework")
	public ResultJson answerHomework(HttpServletRequest request, @RequestParam(required = true) String homeworkId, String questionResult,
			String txtResult, String fileResult, Integer answerCount) {
		String userId = getSessionUserId(request);
		WorkReceiveStu receiveStu = studentHomeworkService.selectByUserAndHomework(userId, homeworkId);
		if (receiveStu == null) {
			return new ResultJson(false, "答题错误！");
		}
		try {
			studentHomeworkService.answerHomework(receiveStu, questionResult, txtResult, fileResult, answerCount == null ? 0 : answerCount);
			if (StringUtils.isNotBlank(fileResult)) {
				JSONArray array = JSONArray.fromObject(fileResult);
				if (!array.isEmpty()) {
					for (int i = 0; i < array.size(); i++) {
						ImageUtil.tryRemoveUploadImage(request, array.getJSONObject(i).getString("path"));
					}
				}
			}
			if (StringUtils.isNotBlank(questionResult)) {
				JSONArray array = JSONArray.fromObject(questionResult);
				if (!array.isEmpty()) {
					for (int i = 0; i < array.size(); i++) {
						ImageUtil.removeUploadImageByFilteringContent(request, array.getJSONObject(i).getString("answer"));
					}
				}
			}
			ImageUtil.removeUploadImageByFilteringContent(request, txtResult);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return new ResultJson(false, "答题错误！");
		}
		return new ResultJson(true);
	}

}
