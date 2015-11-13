package com.codyy.oc.onlinetest.dao;

import java.util.List;
import java.util.Map;

import com.codyy.commons.page.Page;
import com.codyy.oc.onlinetest.entity.ExamExam;
import com.codyy.oc.onlinetest.view.ExamView;

public interface ExamExamMapper {
	int deleteByPrimaryKey(String examId);
	
	int deleteByTaskId(String examTaskId);

	int insert(ExamExam record);

	int insertSelective(ExamExam record);

	ExamExam selectByPrimaryKey(String examId);

	int updateByPrimaryKeySelective(ExamExam record);

	int updateByPrimaryKey(ExamExam record);

	// 复制填空题答案
	int copyFillInAnswer(Map<String, Object> map);

	// 复制知识点
	int copyQuestionRKnowledge(Map<String, Object> map);

	// 复制章节
	int copyQuestionRChapter(Map<String, Object> map);

	/**
	 * 查询真题试卷列表
	 * @param page
	 * @return
	 */
	List<ExamView> getRealExamPageList(Page page);
    
	/**
	 * 
	* @Title: getExamPageList
	* @Description: 查询试卷列表
	* @param @param page
	* @param @return
	* @return List<ExamView>    
	* @throws
	 */
	List<ExamView> getExamPageList(Page page);
	
	/**
	 * 
	* @Title: getSchoolExamPageList
	* @Description: 查询试卷列表
	* @param @param page
	* @param @return
	* @return List<ExamView>    
	* @throws
	 */
	List<ExamView> getSchoolExamPageList(Page page);
	
	/**
	 * 
	* @Title: getExamViewById
	* @Description: 根据ID获取试卷显示信息
	* @param @param examId
	* @param @return
	* @return ExamView    
	* @throws
	 */
	ExamView getExamViewById(String examId);

	int updateExamExamById(Map<String, Object> map);
	
	/**
	 * 
	* @Title: addUseNum
	* @Description: 批量添加题目组卷次数
	* @param @param queIds
	* @param @return
	* @return int    
	* @throws
	 */
	int addUseNum(List<String> queIds);
	
	/**
	 * 
	* @Title: addRealExamUseNum
	* @Description: 更新真题试卷使用次数
	* @param @param id
	* @param @return
	* @return int    
	* @throws
	 */
	int addRealExamUseNum(String id);
}
