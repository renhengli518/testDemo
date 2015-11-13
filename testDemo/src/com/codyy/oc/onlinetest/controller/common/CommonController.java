package com.codyy.oc.onlinetest.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.onlinetest.entity.ExamPracticeQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.service.ExamService;
import com.codyy.oc.onlinetest.service.ExamTaskService;

/**
 * 
 * @author zhangshuangquan
 *
 */
@Controller
@RequestMapping("common/")
public class CommonController {

	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private ExamTaskService  examTaskService;
	
	@Autowired
	private ExamService examService;
	
	
	/**
	 * 获取习题类型  单选 -多选-填空-
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getQuestionTypeByTask")
	public List<String> getQuestionTypeByTask(String examTaskId){
		List<String>  questionTypes = null;
		List<ExamQuestion> examQuestions = examTaskService.getQuestionType(examTaskId);
	    if(examQuestions != null && examQuestions.size() > 0){
	        questionTypes = new ArrayList<String>();
	        String type = null;
	    	for (int i = 0; i < examQuestions.size(); i++) {
	    		if ("COMPUTING".equals(examQuestions.get(i).getType())) {
					type = examQuestions.get(i).getType();
					continue;
				}
				questionTypes.add(examQuestions.get(i).getType());
			}
	    	questionTypes.add(type);
	    }
	    
		return questionTypes;
	}
	
	@ResponseBody
	@RequestMapping("getQuestionTypeByPracticeId")
	public List<String> getQuestionTypeByPracticeId(String examPracticeId){
		List<String>  questionTypes = null;
		List<ExamPracticeQuestion> examQuestions = examTaskService.getQuestionTypeByPracticeId(examPracticeId);
	    if(examQuestions != null && examQuestions.size() > 0){
	        questionTypes = new ArrayList<String>();
	        String type = null;
	    	for (int i = 0; i < examQuestions.size(); i++) {
	    		if ("COMPUTING".equals(examQuestions.get(i).getType())) {
					type = examQuestions.get(i).getType();
					continue;
				}
				questionTypes.add(examQuestions.get(i).getType());
			}
	    	questionTypes.add(type);
	    }
	    
		return questionTypes;
		
	}
	
	
	
	
	/**
	 * 获取习题类型  单选 -多选-填空-
	 * @author xiaokan
	 * @param
	 * @return 
	 *
	 */
	@ResponseBody
	@RequestMapping("getQuestionTypeByExam")
	public List<String> getQuestionTypeByExam(String examId){
		List<String>  questionTypes = null;
		List<ExamQuestion> examQuestions = examService.getQuestionType(examId);
	    if(examQuestions != null && examQuestions.size() > 0){
	        questionTypes = new ArrayList<String>();
	        String type = null;
	    	for (int i = 0; i < examQuestions.size(); i++) {
	    		if ("COMPUTING".equals(examQuestions.get(i).getType())) {
					type = examQuestions.get(i).getType();
					continue;
				}
				questionTypes.add(examQuestions.get(i).getType());
			}
	    	questionTypes.add(type);
	    }
	    
		return questionTypes;
	}
}
