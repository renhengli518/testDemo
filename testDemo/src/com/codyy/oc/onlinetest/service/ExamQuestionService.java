package com.codyy.oc.onlinetest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.OracleKeyWordUtils;
import com.codyy.commons.utils.ResultJson;
import com.codyy.oc.onlinetest.dao.ExamQuestionMapper;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.view.ClassLevelClassView;
import com.codyy.oc.onlinetest.view.CountDataView;
import com.codyy.oc.onlinetest.view.ExamScoreView;
import com.codyy.oc.onlinetest.view.ExamTestView;
import com.codyy.oc.onlinetest.view.StudentView;
import com.codyy.oc.onlinetest.view.TeachCommentView;
import com.codyy.oc.onlinetest.view.TestCountPersonView;

@Service
public class ExamQuestionService {
	@Autowired
	private ExamQuestionMapper examQuestionMapper;

	/**
	 * @author renhengli
	 * 删除真题试卷临时习题
	 * @param examQuestionId
	 */
	public void deleteExamQueByExamQueId(String examQuestionId){
		examQuestionMapper.deleteByPrimaryKey(examQuestionId);
	}

	/**
	 * 获取题目
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamQuestion getExamQuestionById(String examQuestionId) {
		return examQuestionMapper.getExamQuestionById(examQuestionId);
	}
	
	/**
	 * @author lichen
	* @Title: selecClassByTaskExamId
	* @Description: TODO(根据试卷的任务id来获得年级集合)
	* @param @param examTaskId
	* @param @return    设定文件
	* @return List<ClassLevelClassView>    返回类型
	* @throws
	 */
	public List<ClassLevelClassView> selecClassByTaskExamId(String examTaskId){
		return examQuestionMapper.selecClassByTaskExamId(examTaskId);
	}
	
	/**
	 * @author lichen
	* @Title: getObjetiveNum
	* @Description: TODO(获得主观题的总个数和未批阅的个数)
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	public CountDataView getSubjetiveNum(ClassLevelClassView classLevelClassView){
		CountDataView countDataView = new CountDataView();
		Integer allSubjectiveNum=examQuestionMapper.getSubjetiveNum(classLevelClassView.getTaskExamId());
		Integer  unCheckedSubjectNum = this.getSubjUnChecked(classLevelClassView);
		Integer checkedSubjectNum =allSubjectiveNum-unCheckedSubjectNum;
		countDataView.setAllSubjectiveNum(allSubjectiveNum);
		countDataView.setCheckedNum(checkedSubjectNum);
		return countDataView;
	}
	
	/**
	 * @author lichen
	* @Title: getSubjChecked
	* @Description: TODO(获得未批阅的主观题的个数)
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	public Integer getSubjUnChecked(ClassLevelClassView classLevelClassView){
		Integer unCheckQueCount =examQuestionMapper.getSubjUnChecked(classLevelClassView);//未批阅的题的个数
		return unCheckQueCount;
	}
	
	/**
	 * @author lichen
	* @Title: showStuStatuPageList
	* @Description: TODO(获得批阅的学生列表)
	* @param @param page
	* @param @return    设定文件
	* @return List<StudentView>    返回类型
	* @throws
	 */
	public List<StudentView> showStuStatuPageList(Page page,String realName,String classLevId,String classId,String examTaskId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("realName", OracleKeyWordUtils.oracleKeyWordReplace(realName));//去除Oracle的特殊符号的输入影响
		map.put("classLevId", OracleKeyWordUtils.oracleKeyWordReplace(classLevId));
		map.put("classId", OracleKeyWordUtils.oracleKeyWordReplace(classId));
		map.put("examTaskId", examTaskId);
		page.setMap(map);
		return examQuestionMapper.showStuStatuPageList(page);
	}
	
	/**
	 * @author lichen
	* @Title: selecObjectByStu
	* @Description: TODO(获得指定学生的纵向批阅的所有试题)
	* @param @param examQuestionId
	* @param @return    设定文件
	* @return List<ExamQuestionListResult>    返回类型
	* @throws
	 */
	public List<ExamQuestionListResult> selecObjectByStu(String examQuestionResultId){
		return examQuestionMapper.selecObjectByStu(examQuestionResultId);
	}
	
	/**
	 * @author lichen
	* @Title: updateQuestionCommScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param teachCommentView
	* @param @return    设定文件
	* @return    返回类型
	* @throws
	 */
	public ResultJson updateQuestionCommScore(List<TeachCommentView> teaComeList,String examResultId,String examTaskId){
		try{
			if(null!=teaComeList && teaComeList.size()>0){//更新教师评价与分数
				for(TeachCommentView teachCommentView : teaComeList){
					examQuestionMapper.updateQuestionCommScore(teachCommentView);
				}	 
			}
			
			//统计本次测试总分
			float totalScore =examQuestionMapper.getSumScore(examResultId);
			//插入分数
			ExamScoreView examScoreView = new ExamScoreView();
			examScoreView.setExamResultId(examResultId);
			examScoreView.setScore(totalScore);
			examQuestionMapper.updateTotalScore(examScoreView);
			//更新本次测试的批阅数
			examQuestionMapper.updateReadOverCount(examTaskId);
            return new ResultJson(true);
		}catch(Exception e){
			e.printStackTrace();
			return new ResultJson(false);
		}
		
	}
	
	/**
	 * @author lichen
	* @Title: CountTestPerson
	* @Description: TODO(根据年级和班级的id获得对应的测试人数)
	* @param @param examTestView
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	public TestCountPersonView countTestPerson(ExamTestView examTestView){
		
		TestCountPersonView testCountPersonView = new TestCountPersonView();
		
		examTestView.setStatus("all");
		Integer allTestNum=examQuestionMapper.countTestPerson(examTestView);//所有测试的人
		
		examTestView.setStatus("SUBMITTED");
		Integer submitedTestNum=examQuestionMapper.countTestPerson(examTestView);//所有已提交的人
		
		examTestView.setStatus("INIT");
		Integer initTestNum=examQuestionMapper.countTestPerson(examTestView);
		
		testCountPersonView.setAllTestCountPer(allTestNum);
		testCountPersonView.setSubmitedCountPer(submitedTestNum);
		testCountPersonView.setInitedCounterPer(initTestNum);

		return testCountPersonView;
	}
}
