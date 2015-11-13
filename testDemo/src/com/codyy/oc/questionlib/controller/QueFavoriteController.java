package com.codyy.oc.questionlib.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.page.Page;
import com.codyy.commons.sso.SessionUser;
import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.questionlib.service.QueFavoriteService;

@Controller
@RequestMapping("questionLib")
public class QueFavoriteController {
	
	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private QueFavoriteService queFavoriteService;
	
	/**
	 * 收藏题库页面
	 * @param request
	 * @return
	 */
	@RequestMapping("toFavoriteLib")
	public String toFavoriteLib(HttpServletRequest request){
		request.setAttribute("tabType", "favorite"); 
		getClassAndSubject(request);
		return "front/questionlib/favoriteLib";
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
	 * 收藏习题列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("searchFavoriteList")
	@ResponseBody
	public Page searchFavoriteListByPagination(HttpServletRequest request,Page page){
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
		map.put("baseUserId", baseUserId);
		page.setMap(map);
		return queFavoriteService.getFavoriteQuePageList(page);
	}


}
