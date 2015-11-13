package com.codyy.oc.onlinetest.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.view.QuestionKnowledgeView;
import com.codyy.oc.questionlib.entity.QueQuestion;

public interface QuestionMapper {
    int deleteByPrimaryKey(String queQuestionId);

    int insert(QueQuestion record);

    int insertSelective(QueQuestion record);

    QueQuestion selectByPrimaryKey(String queQuestionId);

    int updateByPrimaryKeySelective(QueQuestion record);

    int updateByPrimaryKeyWithBLOBs(QueQuestion record);

    int updateByPrimaryKey(QueQuestion record);

	List<QueQuestion> getPracticeMotherExam(List<QueQuestion> queQuestions);

	List<QuestionKnowledgeView> getPracticeExamByRelation(List<QueQuestion> queQuestions);

	List<QueQuestion> getQuestionsByResultId(String examResultId);

	List<QuestionKnowledgeView> getQuestionList(@Param("examQuestionResults") List<QuestionKnowledgeView> examQuestionResults,
			@Param("areaIdPath") String areaIdPath, @Param("schoolId") String schoolId);

	List<QuestionKnowledgeView> getPracticeByKnowledge(Map<String, Object> map);

	List<QuestionKnowledgeView> getQuestionsRandom(@Param("examQuestions") List<ExamQuestionListResult> examQuestions, 
			@Param("areaIdPath") String areaIdPath,@Param("schoolId") String schoolId);

	List<QuestionKnowledgeView> getQuestionIntersection(@Param("examQuestionIdList1") List<String> examQuestionIdList1, 
			@Param("queQuestions") List<QueQuestion> queQuestions);

	List<QuestionKnowledgeView> getPracticeExamByKnowledge(@Param("originResults1") List<ExamQuestionListResult> originResults1, 
			@Param("queIds") List<QuestionKnowledgeView> queIds, @Param("examQuestionResults") List<QuestionKnowledgeView> examQuestionResults,
			@Param("queQuestions") List<QueQuestion> queQuestions, @Param("areaIdPath") String areaIdPath, @Param("schoolId") String schoolId);

	List<QuestionKnowledgeView> getPracticeByKnowledge(@Param("originResults2") List<ExamQuestionListResult> originResults2,
			@Param("queIds") List<QuestionKnowledgeView> queIds, @Param("examQuestionResults") List<QuestionKnowledgeView> examQuestionResults, 
			@Param("queQuestions") List<QueQuestion> queQuestions,@Param("areaIdPath") String areaIdPath, @Param("schoolId") String schoolId);

	List<ExamQuestionListResult> getQuestionByIds(List<QuestionKnowledgeView> examQuestionResults);

}