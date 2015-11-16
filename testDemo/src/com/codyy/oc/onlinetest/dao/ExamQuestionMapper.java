package com.codyy.oc.onlinetest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.codyy.commons.page.Page;
import com.codyy.oc.onlinetest.entity.ExamQstRKnowledgeExtend;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.view.ClassLevelClassView;
import com.codyy.oc.onlinetest.view.ExamScoreView;
import com.codyy.oc.onlinetest.view.ExamTestView;
import com.codyy.oc.onlinetest.view.QuestionKnowledgeView;
import com.codyy.oc.onlinetest.view.StudentView;
import com.codyy.oc.onlinetest.view.TeachCommentView;


public interface ExamQuestionMapper {
    int deleteByPrimaryKey(String examQuestionId);

    int insert(ExamQuestion record);

    int insertSelective(ExamQuestion record);

    ExamQuestionListResult selectByPrimaryKey(String examQuestionId);

    int updateByPrimaryKeySelective(ExamQuestion record);
    
    int updateSelective(ExamQuestion record);

    int updateByPrimaryKeyWithBLOBs(ExamQuestion record);

    int updateByPrimaryKey(ExamQuestion record);

	List<ExamQuestionListResult> getExamQuestionByExamTaskId(String examTaskId);

	List<ExamQuestion> getQuestionTypeByExamTaskId(String examTaskId);

	List<ExamQuestionListResult> getStudentExamAnswer(Map<String, Object> map);

	List<ExamQuestionListResult> getClassExamStatistics(Map<String, Object> map);

	List<ExamQstRKnowledgeExtend> getExamQstRKnowledgeByExamQstId(String examQuestionId);

	List<ExamQuestionListResult> getSubmittedExamByTaskId(Map<String, Object> map);
	
	List<ExamQuestionListResult> getExamQuestionByExamId(String examId);
	
	List<ExamQuestion> getQuestionTypeByExamId(String examId);
	
	List<ExamQuestion> selectByExamId (String examId);
	
	ExamQuestion selectByExamQuestionId(String examQuestionId);
	
	int deleteByExamIdAndExamQuestionId(Map<String ,Object> map);
	
	int deleteByExamIdAndQuestionId(Map<String ,Object> map);

	ExamQuestion getExamQuestion(@Param("examQuestionId") String examQuestionId,
			@Param("examTaskId") String examTaskId);
	
	/**
	 * @author lichen
	* @Title: selecClassByTaskExamId
	* @Description: TODO(根据试卷的任务id获得班级的集合)
	* @param @param examTaskId
	* @param @return    设定文件
	* @return List<ClassLevelClassView>    返回类型
	* @throws
	 */
	List<ClassLevelClassView> selecClassByTaskExamId(String examTaskId);
	
	/**
	 * @author lichen
	* @Title: getObjetiveNum
	* @Description: TODO(获得主观题的总个数)
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer getSubjetiveNum(String examTaskId);
	/**
	 * @author lichen
	* @Title: getSubjChecked
	* @Description: TODO(获得未批阅的主观题的个数)
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer getSubjUnChecked(ClassLevelClassView classLevelClassView);
	/**
	 * @author lichen
	* @Title: showStuStatuPageList
	* @Description: TODO(获得批阅的学生列表)
	* @param @return    设定文件
	* @return List<StudentView>    返回类型
	* @throws
	 */
	List<StudentView> showStuStatuPageList(Page page);
	/**
	 * @author lichen
	* @Title: selecObjectByStu
	* @Description: TODO(获得指定学生的纵向批阅的所有试题)
	* @param @param examQuestionId
	* @param @return    设定文件
	* @return List<ExamQuestionListResult>    返回类型
	* @throws
	 */
	List<ExamQuestionListResult> selecObjectByStu(String examQuestionResultId);
	
	/**
	 * 
	* @Title: getQueIdsByExamId
	* @Description: 根据ID
	* @param @return
	* @return Set<String>    
	* @throws
	 */
	Set<String> getQueIdsByExamId();

	List<ExamQuestionListResult> getPracticeExamByResultId(String examResultId);

	List<QuestionKnowledgeView> getPractieExamByKnowledge(Map<String, Object> map);

	List<QuestionKnowledgeView> getPracticByKnowledge(Map<String, Object> map);

	List<ExamQuestionListResult> getExamQuestionList(List<QuestionKnowledgeView> practice1);

	List<ExamQuestionListResult> getOriginalExamQuestion(@Param("examQuestionIds") List<String> examQuestionIds);

	List<ExamQuestionListResult> getOrgExamQuestion(List<String> originQuestionIds2);

	List<ExamQuestionListResult> getOriginExamResults(@Param("queIds") List<QuestionKnowledgeView> queIds, 
			@Param("examQuestions") List<ExamQuestionListResult> examQuestions);

	List<ExamQstRKnowledgeExtend> getPracticeExamKnowledge(String examPracticeQuestionId);
	
	List<ExamQuestion> getTestQuestionList(Map<String, String> map);
	
	/**
	 * @author lichen
	* @Title: updateQuestionCommScore
	* @Description: TODO(更新教师评价与获得的分数)
	* @param @param teachCommentView
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer updateQuestionCommScore(TeachCommentView teachCommentView);
	
	/**
	 * @author lichen
	* @Title: getSumScore
	* @Description: TODO(统计指定学生更新分数之后的总分)
	* @param @param examResultId
	* @param @return    设定文件
	* @return Float    返回类型
	* @throws
	 */
	Float getSumScore(String examResultId);
	
	/**
	 * @author lichen
	* @Title: insertTotalScore
	* @Description: TODO(将本学生本次测试的总分写入对应的字段中)
	* @param @param examScoreView
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer updateTotalScore(ExamScoreView examScoreView);
	
	/**
	 * @author lichen
	* @Title: CountTestPerson
	* @Description: TODO(按年级获得测试人数)
	* @param @param examTestView
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer countTestPerson(ExamTestView examTestView);

	ExamQuestion getExamQuestionByQuestionId(String examQuestionId);
	
	/**
	 * @author lichen
	* @Title: updateReadOverCount
	* @Description: TODO(更新本次测试的批阅数)
	* @param @param examTaskId
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer updateReadOverCount(String examTaskId);

	ExamQuestion getExamQuestionById(String examQuestionId);
	
	/**
	 * @author renhengli
	 * 定时删除临时习题
	 * @return
	 */
	int deleteLSExamQuestion();
}