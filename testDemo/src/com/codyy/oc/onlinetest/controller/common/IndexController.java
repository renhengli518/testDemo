package com.codyy.oc.onlinetest.controller.common;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.sso.SessionUser;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.service.ExamTaskService;

/**
 * 
 * @author zhangshuangquan
 *
 */
@Controller
@RequestMapping("test")
public class IndexController extends BaseController{

	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private ExamTaskService  examTaskService;
	
	@Autowired
	private ExamService examService;
	
	
	/**
	 * 
	* @Title: index
	* @Description: 在线测试入口
	* @param @return
	* @return String    
	* @throws
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, String studentUserId){
		SessionUser sessionUser = getSessionUser(request);
		String path = CommonsConstant.ERROR_PAGE_404;
		if(CommonsConstant.USER_TYPE_TEACHER.equals(sessionUser.getUserType())){
			//如果是老师
			return "redirect:../teacherTest/examList.html";
		}else if (CommonsConstant.USER_TYPE_STUDENT.equals(sessionUser.getUserType())){
			return "redirect:../studentTest/studentTaskList.html";
		}else if (CommonsConstant.USER_TYPE_SCHOOL_USER.equals(sessionUser.getUserType())){
			return "redirect:../schoolTest/examList.html";
		}else if (CommonsConstant.USER_TYPE_PARENT.equals(sessionUser.getUserType())) {
			if (StringUtils.isNotBlank(studentUserId)) {
				return "redirect:../parentTest/teacherAssignList/"+studentUserId+".html";
			}
	    } else {
	    	return "redirect:../teacherTest/examList.html"; 
		}
		return path;
	}
}
