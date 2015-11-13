package com.codyy.oc.onlinetest.controller.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.sso.SessionUser;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
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
	public String index(HttpServletRequest request){
		SessionUser sessionUser = getSessionUser(request);
		if(CommonsConstant.USER_TYPE_TEACHER.equals(sessionUser.getUserType())){
			//如果是老师
			return "redirect:../teacherTest/examList.html";
		}else if (CommonsConstant.USER_TYPE_STUDENT.equals(sessionUser.getUserType())){
			return "redirect:../studentTest/studentTaskList.html";
		}else if (CommonsConstant.USER_TYPE_SCHOOL_USER.equals(sessionUser.getUserType())){
			return "redirect:../schoolTest/examList.html";
		}else{
			return "redirect:../teacherTest/examList.html"; 
		}
	}
	

}
