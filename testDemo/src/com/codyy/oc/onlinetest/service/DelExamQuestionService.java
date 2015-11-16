package com.codyy.oc.onlinetest.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.oc.onlinetest.dao.ExamQuestionMapper;

@Service("delExamQuestionService")
public class DelExamQuestionService {
	private static Logger logger = Logger.getLogger(DelExamQuestionService.class);
	
	@Autowired
	private ExamQuestionMapper examQuestionMapper;
	
	/**
	 * 定期删除临时习题
	 * @return
	 */
	public void deleteLSExamQuestion(){
		logger.info("定期删除临时习题开始：");
		int x = examQuestionMapper.deleteLSExamQuestion();
		logger.info("定期删除临时习题结束：共删除"+x+"题");
	}

}
