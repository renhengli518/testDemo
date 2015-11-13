package com.codyy.oc.onlinetest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.OracleKeyWordUtils;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.base.dao.BaseKnowledgeDao;
import com.codyy.oc.base.dao.CommonsMapper;
import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.base.entity.BaseUser;
import com.codyy.oc.base.form.ExamTaskListCriteria;
import com.codyy.oc.base.form.QuestionListCriteria;
import com.codyy.oc.base.form.QuestionListCriteria.AnswerType;
import com.codyy.oc.base.form.QuestionListCriteria.QuestionType;
import com.codyy.oc.base.form.QuestionListCriteria.ScoreType;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.onlinetest.dao.ExamExamMapper;
import com.codyy.oc.onlinetest.dao.ExamPracQuesRKnowledgeMapper;
import com.codyy.oc.onlinetest.dao.ExamPracticeMapper;
import com.codyy.oc.onlinetest.dao.ExamPracticeQuestionMapper;
import com.codyy.oc.onlinetest.dao.ExamQueFillInAnswerMapper;
import com.codyy.oc.onlinetest.dao.ExamQuestionMapper;
import com.codyy.oc.onlinetest.dao.ExamQuestionRKnowledgeMapper;
import com.codyy.oc.onlinetest.dao.ExamQuestionResultMapper;
import com.codyy.oc.onlinetest.dao.ExamResultMapper;
import com.codyy.oc.onlinetest.dao.ExamTaskMapper;
import com.codyy.oc.onlinetest.dao.QuestionMapper;
import com.codyy.oc.onlinetest.entity.ExamPracQuesRKnowledge;
import com.codyy.oc.onlinetest.entity.ExamPractice;
import com.codyy.oc.onlinetest.entity.ExamPracticeQuestion;
import com.codyy.oc.onlinetest.entity.ExamQstRKnowledgeExtend;
import com.codyy.oc.onlinetest.entity.ExamQueFillInAnswer;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionRKnowledge;
import com.codyy.oc.onlinetest.entity.ExamQuestionResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionStatistics;
import com.codyy.oc.onlinetest.entity.ExamResult;
import com.codyy.oc.onlinetest.entity.ExamStudentStatistic;
import com.codyy.oc.onlinetest.entity.ExamTask;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;
import com.codyy.oc.onlinetest.view.ExamTaskSortView;
import com.codyy.oc.onlinetest.view.ExamTaskView;
import com.codyy.oc.onlinetest.view.QuestionKnowledgeView;
import com.codyy.oc.onlinetest.view.TestSearchView;
import com.codyy.oc.questionlib.entity.QueQuestion;

public class ExamTaskService {

	@Autowired
	private ExamTaskMapper examTaskMapper;
	
	@Autowired
	private ExamQuestionMapper examQuestionMapper;
	
	@Autowired
	private BaseKnowledgeDao baseKnowledgeDao;
	
	@Autowired
	private ExamQuestionResultMapper examQuestionResultMapper;
	
	@Autowired
	private ExamResultMapper examResultMapper;
	
	@Autowired
	private ExamQueFillInAnswerMapper  examQueFillInAnswerMapper;
	
	@Autowired
	private QuestionMapper queQuestionMapper;
	
	@Autowired
	private ExamExamMapper  examExamMapper;
	
	@Autowired
	private ExamPracticeMapper examPracticeMapper;
	
	@Autowired
	private ExamPracticeQuestionMapper examPracticeQuestionMapper;
	
	@Autowired
	private ExamQuestionRKnowledgeMapper examQuestionRKnowledgeMapper;
	
	@Autowired
	private ExamPracQuesRKnowledgeMapper examPracQuesRKnowledgeMapper;
	
	@Autowired
	private CommonsMapper commonsMapper;
	
	
	/**
	 * 学校空间 - 班级测试列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public Page getClassExamList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null?null:new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("classId", testSearchView.getClassId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("status", testSearchView.getStatus());
		map.put("taskType", "ASSIGN");
		map.put("schoolId",  testSearchView.getUser_schoolId());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		page.setMap(map);
		List<ExamTaskView> examTaskList = examTaskMapper.getClassExamPageList(page);
		page.setData(examTaskList);
		return page;
	}
	
	/**
	 * 班级空间  - 班级测试列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public Page getClsClassExamList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null?null:new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("classId", testSearchView.getClassId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("status", testSearchView.getStatus());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		page.setMap(map);
		List<ExamTaskView> examTaskList = examTaskMapper.getClsClassExamPageList(page);
		page.setData(examTaskList);
		return page;
	}


	/**
	 * 获取所有试卷类型
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<SelectModel> getAllExamTypes() {
		return examTaskMapper.getAllExamTypes();
	}

	/**
	 * 查看测试
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamTaskView getClassExamById(String examTaskId) {
		return examTaskMapper.getClassExamById(examTaskId);
	}

	/**
	 * 查看测试 -获取所有试题
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestionListResult> getExamQuestionByExamTaskId(String examTaskId) {
		List<ExamQuestionListResult> qListResults=examQuestionMapper.getExamQuestionByExamTaskId(examTaskId);
		return qListResults;
	}

    /**
     * 获取与试题相关的知识点信息
     * @author zhangshuangquan
     * @param  qListResult
     * @return ExamQuestionListResult
     *
     */
	private ExamQuestionListResult formQuestionDetailResult(ExamQuestionListResult qListResult) {
		//组装末节知识点部分
	   	List<ExamQuestionRKnowledge> rknowledgesList = qListResult.getrKnowledges();
	   	ExamQuestionRKnowledge rknowledge = null;
	   	 if (rknowledgesList != null && rknowledgesList.size() > 0){
	   		 StringBuilder knowledgeIds = new StringBuilder();
	   		 String kId = "";
	       	 List<String> kIds = new ArrayList<String>();
	   		 //形成知识点IDs
	   		 for (int j = 0; j<rknowledgesList.size(); j++){
	   			 rknowledge = rknowledgesList.get(j);
	   			 kId = rknowledge.getBaseEndKnowledgeId();
	   			 kIds.add(kId);
	   			 if(j!=(rknowledgesList.size()-1)){
	   				 knowledgeIds.append("'"+kId+"',");
	   			 } else {
	   				 knowledgeIds.append("'"+kId+"'");
	   			 }    			
	   		 }
	   		 qListResult.setKnowledgeEndIds(knowledgeIds.toString());
	   		 //根据IDs形成末节名称
	   		 StringBuilder knowledgeNames = new StringBuilder();
	   		 List<BaseKnowledge> knowledgeList = baseKnowledgeDao.findKnowledgeListByIds(kIds);//knowledgeIds.toString().split("\\,")
	   		 qListResult.setKnowledgeEndList(knowledgeList);
	   		 if (knowledgeList != null && knowledgeList.size() > 0){
	   			 BaseKnowledge knowledge = null;
	   			 for (int k =0; k<knowledgeList.size(); k++){
	   				 knowledge = knowledgeList.get(k);
	   				 if(k!=(knowledgeList.size()-1)){
	   					 knowledgeNames.append(knowledge.getKnowledgeName()+",");
	   				 }else{
	   					 knowledgeNames.append(knowledge.getKnowledgeName());
	   				 }
	   			 }
	   		 }
	   		qListResult.setKnowledgeEndNames(knowledgeNames.toString());	 
	   	 }
	   	 return qListResult;
	}

	
	/**
	 * 获取习题类型 单选-多选-填空
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestion> getQuestionType(String examTaskId) {
		return examQuestionMapper.getQuestionTypeByExamTaskId(examTaskId);
	}

	/**
	 * 学校空间 - 班级统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamTaskStatistics> getExamStaticsByClass(String examTaskId) {
		List<ExamTaskStatistics> examTaskStatistics = null;
		//获得所有班级统计之和
		ExamTaskStatistics eStatistics = examTaskMapper.getExamAllStatics(examTaskId);
		//获得每个班级的统计
		List<ExamTaskStatistics> eStatistics2=examTaskMapper.getExamStaticsByClass(examTaskId);
		if (eStatistics != null && eStatistics2 != null 
				&& eStatistics2.size() > 0) {
			examTaskStatistics = new ArrayList<ExamTaskStatistics>();
		    eStatistics.setClasslevelName("");
			eStatistics.setClassName("所有班级");
			examTaskStatistics.add(eStatistics);
			examTaskStatistics.addAll(eStatistics2);	
		}
		return examTaskStatistics;
	}

	

	/**
	 * 正确率统计 -根据班级获取统计数据
	 * @author zhangshuangquan
	 * @param flag2 
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestionStatistics> getExamRightStatisByClass(
			String examTaskId, String classlevelId, String classId, String flag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("classlevelId", classlevelId);
		map.put("classId", classId);
		map.put("flag", flag);
		return examTaskMapper.getExamRightStatisByClass(map);
	}

	/**
	 * 获取学生统计
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamStudentStatistic> getStudentStatisList(ExamTaskSortView examTaskSortView,
			String classlevelId, String classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskSortView.getExamTaskId());
		map.put("classlevelId", classlevelId);
		map.put("classId", classId);
		map.put("nameSort", examTaskSortView.getNameSort());
		map.put("scoreSort", examTaskSortView.getScoreSort());
		map.put("numSort", examTaskSortView.getNumSort());
		map.put("rightSort", examTaskSortView.getRightSort());
		return examTaskMapper.getStudentStatisList(map);
	}

	/**
	 *  测试结果
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamResult getExamResultByUserId(String examTaskId, String baseUserId) {
		return examTaskMapper.getExamResultByUserId(examTaskId, baseUserId);
	}

	/**
	 * 查看学生答题
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestionListResult> getStudentExamAnswer(String examTaskId, String baseUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("baseUserId", baseUserId);
		return examQuestionMapper.getStudentExamAnswer(map);
	}

	/**
	 * 班级测试 - 详细统计
	 * @author zhangshuangquan
	 * @param classlevelId 
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestionListResult> getClassExamStatistics(String examTaskId, String classId,
			String classlevelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("classlevelId", classlevelId);
		map.put("classId", classId);
		return examQuestionMapper.getClassExamStatistics(map);
	}

	/**
	 * 获取知识点
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQstRKnowledgeExtend> getExamQstRKnowledgeInfoByExamQstId(String examQuestionId) {
		//获取知识点
		List<ExamQstRKnowledgeExtend> rKnowledgeLi = examQuestionMapper.getExamQstRKnowledgeByExamQstId(examQuestionId);
		//获取题目
		ExamQuestionListResult eqlr = examQuestionMapper.selectByPrimaryKey(examQuestionId);
		if (rKnowledgeLi != null && rKnowledgeLi.size() > 0){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("baseSemesterId", eqlr.getBaseSemesterId());
			map.put("baseSubjectId", eqlr.getBaseSubjectId());
			//获取基本知识点
			List<BaseKnowledge> baseKnowledgeLi = baseKnowledgeDao.getAllBaseKnowledgeTreeBySemesterAndDiscipline(map);
			//将知识点列表组装成map
			Map<String,BaseKnowledge> knowledgeMap = new HashMap<String, BaseKnowledge>();
		    BaseKnowledge knowledgeObj = null;
			for (int i = 0; i< baseKnowledgeLi.size(); i++){
				knowledgeObj = baseKnowledgeLi.get(i);
				knowledgeMap.put(knowledgeObj.getBaseKnowledgeId(), knowledgeObj);
			}
		   
			//将关联知识点ID和名字关联到习题属性
		    ExamQstRKnowledgeExtend rKnowledgeObj = null;
		    BaseKnowledge refKnowledgeObj = null;
		
	        for(int k = 0; k < rKnowledgeLi.size(); k++){
				rKnowledgeObj = rKnowledgeLi.get(k);

				refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseKnowledgeId());
				rKnowledgeObj.setBaseKnowledgeName(refKnowledgeObj.getKnowledgeName());

				if (rKnowledgeObj.getBaseSubKnowledge1Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge1Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge1Name(refKnowledgeObj.getKnowledgeName());
					}
					
				}

				if (rKnowledgeObj.getBaseSubKnowledge2Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge2Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge2Name(refKnowledgeObj.getKnowledgeName());
					}
					
				}

				if (rKnowledgeObj.getBaseSubKnowledge3Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge3Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge3Name(refKnowledgeObj.getKnowledgeName());
					}
					
				}

				if (rKnowledgeObj.getBaseSubKnowledge4Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge4Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge4Name(refKnowledgeObj.getKnowledgeName());
					}
					
				}

				if (rKnowledgeObj.getBaseSubKnowledge5Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge5Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge5Name(refKnowledgeObj.getKnowledgeName());
					}
				}
			}
				
		  }
		return rKnowledgeLi;
	}

	
	public ExamQuestion getExamQuestionByQuestionId(String examQuestionId) {
		return examQuestionMapper.getExamQuestionByQuestionId(examQuestionId);
	}

	
	/**
	 * 获取每道题的答题结果统计
	 * @author zhangshuangquan
	 * @param classId2 
	 * @param
	 * @return 
	 *
	 */
	public String[] getExamQuestionStatistics(ExamQuestion examQuestion, String examTaskId,
			String classlevelId, String classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String examType = examQuestion.getType();
		String examQuestionId = examQuestion.getExamQuestionId();
		if (StringUtils.isNotBlank(examType) && StringUtils.isNotBlank(examQuestionId)
				&& StringUtils.isNotBlank(classId) && StringUtils.isNotBlank(classlevelId)) {
			map.put("examQuestionId", examQuestionId);
			map.put("classlevelId", classlevelId);
			map.put("classId", classId);
			map.put("examTaskId", examTaskId);
			String[] result = null;
			if (QuestionType.single_option.getValue().equals(examType) 
					|| QuestionType.judement.getValue().equals(examType)) {
			  
				List<ExamQuestionResult> eqResults = examQuestionResultMapper.getExamQuestionStatistics(map);
			
			  //获取填空题选项   A.1∷B.2
			  String[] options = examQuestion.getOptions().split("∷");
			  
			  if (eqResults != null && eqResults.size() > 0) {
				  String[] LETTERS = {"A","B","C","D","E","F","G","H"};
				  String opt = "";
				  result = new String [options.length];
				  int resCnt = 0;
				  for (int i = 0; i < options.length; i++) {
					  resCnt = 0;
					  opt = LETTERS[i].substring(0, 1);
					  
					  for (ExamQuestionResult eq : eqResults) {
						 if(eq != null){
							 if (StringUtils.isBlank(eq.getAnswer())) {
								 continue;
							} else if(eq.getAnswer().indexOf(opt) != -1){
	                              resCnt++;
							}
						 }
						
					  }
	               result[i] = opt+"-"+String.valueOf(resCnt)+"-"+examType;
				}
				return result;
			  }
			} else if (QuestionType.multple_option.getValue().equals(examType)) {
		       List<ExamQuestionResult> eqResults = examQuestionResultMapper.getMultiChoiceStatistics(map);
		       
		       if (eqResults != null && eqResults.size() > 0) {
		    	     result = new String[eqResults.size()];
				   
		    	   for (int i = 0; i < result.length; i++) {
		    		   
		    		  result[i] = String.valueOf(eqResults.get(i).getOptionAnswer())+"-"
					               +String.valueOf(eqResults.get(i).getAnswerCount())+"-"
					               +examType;
					  
					   
				   }
		         return result;
			   }
			} else {
		      List<ExamQuestionResult> eqResults = examQuestionResultMapper.getExamQuestionOtherStatics(map);
		      if (eqResults != null && eqResults.size() > 0) {
		    	  result = new String[eqResults.size()];
				  for (int i = 0; i < result.length; i++) {
					  result[i] = String.valueOf(eqResults.get(i).getScore())+"-"
				                +String.valueOf(eqResults.get(i).getScoreCount())+"-"
				                +examType;
				  }
			   return result;
			  }

			}
			
		}
		return null;
	}


	/**
	 * 家长空间 - 教师布置列表
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public Page getTeacherAssignList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null?null:new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("userId", testSearchView.getUserId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("status", testSearchView.getStatus());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		page.setMap(map);
		List<ExamTaskView> examTaskList = examTaskMapper.getTeacherAssignPageList(page);
		page.setData(examTaskList);
		return page;
	}


	/**
	 * 家长空间 - 查看待批阅
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestionListResult> getSubmittedExamByTaskId(String examTaskId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("userId", userId);
		return examQuestionMapper.getSubmittedExamByTaskId(map);
	}
	
	/**
	 * 学生空间 - 班级试卷统计
	 * @author zhangshuangquan
	 * @param classlevelId 
	 * @param
	 * @return 
	 *
	 */
	public ExamTaskStatistics getClassExamStaticsByStudent(String examTaskId, String studentId,
			String classlevelId, String classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("userId", studentId);
		map.put("classlevelId", classlevelId);
		map.put("classId", classId);
		return examTaskMapper.getClassExamStaticsByStudent(map);
	}

	
    /**
     * 教师空间 - 测试任务列表
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
	public Page getClassExamTaskList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null?null:new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		map.put("userId", testSearchView.getUserId());
		page.setMap(map);
		List<ExamTaskView> examTaskList = examTaskMapper.getClassExamTaskPageList(page);
		page.setData(examTaskList);
		return page;
	}

	
	/**
	 * 
	* @Title: finishCreateTask
	* @Description: 完成布置试卷
	* @param @param examId
	* @param @param stuIds
	* @param @param beginTime
	* @param @param endTime
	* @param @return
	* @return String    
	* @throws
	 */
	public String finishCreateTask(String examId,String[] stuIds,Date beginTime,Date endTime,String baseUserId){
		//插入任务信息
		ExamTask et = new ExamTask();
		String etId = UUIDUtils.getUUID();
		et.setBaseUserId(baseUserId);
		et.setCreateTime(new Date());
		et.setExamId(examId);
		et.setExamTaskId(etId);
		et.setFinishedCount(0);
		et.setAssignedCount(stuIds.length);
		et.setReadOverCount(0);
		et.setFinishedTime(endTime);
		et.setStartTime(beginTime);
		if(beginTime == null && endTime == null){
			//自测组卷没有开始和结束时间
			et.setTaskType(ExamTaskListCriteria.ExamTaskEnum.self.getValue());
		}else{
			et.setTaskType(ExamTaskListCriteria.ExamTaskEnum.assign.getValue());
		}
		examTaskMapper.insert(et);
		
		//复制测试任务题目信息
		examTaskMapper.copyTaskQuestion(examId, etId);
		
		//批量插入学生考试任务信息
		examResultMapper.insertBatch(Arrays.asList(stuIds), examId, etId);
		
		return etId;
	}
	
	/**
	 * 
	* @Title: finishCreateTask
	* @Description: 完成布置试卷
	* @param @param examId
	* @param @param stuIds
	* @param @param beginTime
	* @param @param endTime
	* @param @return
	* @return String    
	* @throws
	 */
	public String finishCreateClassTask(String examId,String[] classIds,Date beginTime,Date endTime,String baseUserId){
		//获取班级下的所有学生
		List<String> stuIds = commonsMapper.selectStuIdsByClassIds(Arrays.asList(classIds));
		
		return finishCreateTask(examId, (String[])stuIds.toArray(new String[stuIds.size()]), beginTime, endTime, baseUserId);
		
	}


	/**
	 * 删除测试任务
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public int delExamTaskById(String examTaskId) {
		try {
			return examTaskMapper.deleteByPrimaryKey(examTaskId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

    /**
     * 学生空间 - 测试任务
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
	public Page getStudentTaskList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null?null:new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		map.put("userId", testSearchView.getUserId());
		map.put("status", testSearchView.getStatus());
		map.put("isSelf", testSearchView.getIsSelf());
		page.setMap(map);
		List<ExamTaskView> examTaskList = examTaskMapper.getStudentTaskPageList(page);
		page.setData(examTaskList);
		return page;
	}

	/**
	 * 学生空间 - 学生答题
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public void answerExamByStudent(List<ExamQuestionResult> examQuestionResults, String examTaskId, 
			String examResultId, String[] questionId, String[] answerVideoPath) {
		int answerCount = 0;
		boolean flag = false;
		
		for (int i = 0; i < examQuestionResults.size(); i++) {
			flag = false;
			examQuestionResults.get(i).setExamQuestionResultId(UUIDUtils.getUUID());
			examQuestionResults.get(i).setExamResultId(examResultId);
			
			ExamQuestion examQuestion = examQuestionMapper.getExamQuestion(examQuestionResults.get(i).getExamQuestionId(), examTaskId);
			 
			//单选  多选  判断
			if (examQuestion.getType().equals(QuestionType.single_option.getValue())
					|| examQuestion.getType().equals(QuestionType.multple_option.getValue())
					|| examQuestion.getType().equals(QuestionType.judement.getValue())) {
				
				if (examQuestion.getAnswer().equals(examQuestionResults.get(i).getAnswer())) {
					examQuestionResults.get(i).setResult(ExamQuestionResult.RESULT_RIGHT);
					examQuestionResults.get(i).setScore(String.valueOf(examQuestion.getScore()));
				}else {
					examQuestionResults.get(i).setResult(ExamQuestionResult.RESULT_WRONG);
					examQuestionResults.get(i).setScore("0");
					
				}
				
				if(StringUtils.isNotBlank(examQuestionResults.get(i).getAnswer())){
					answerCount++ ;
				}
				
			}else if (examQuestion.getType().equals(QuestionType.fill_in.getValue())) {
		       //填空题
			   
			   //答对空数
			   int rightNum = checkFillinQuestion(examQuestion.getExamQuestionId(), examQuestionResults.get(i).getAnswer());
			   //总空数
			   int allNum = examQuestionResults.get(i).getAnswer().split(QuestionListCriteria.FILLIN_ANSWER_SEPERATE).length;
				
		       if (allNum == 0) {
				 examQuestionResults.get(i).setResult(ExamQuestionResult.RESULT_WRONG);
				 examQuestionResults.get(i).setScore("0");
			   } else {
				  if (rightNum == allNum) {
					  //获得满分则算回答正确
					  examQuestionResults.get(i).setResult(ExamQuestionResult.RESULT_RIGHT);
					  examQuestionResults.get(i).setScore(String.valueOf(examQuestion.getScore()));
					 
				  } else {
					  examQuestionResults.get(i).setResult(ExamQuestionResult.RESULT_WRONG);
					  examQuestionResults.get(i).setScore(((double)examQuestion.getScore()*rightNum/allNum)+"");
				  }
				  //examQuestionResults.get(i).setScore(((double)examQuestion.getScore()*rightNum/allNum)+"");
			   }
			   
		       if (StringUtils.isNotBlank(examQuestionResults.get(i).getAnswer().replaceAll(QuestionListCriteria.FILLIN_ANSWER_SEPERATE, ""))){
					//如果不为空则算做了该题
					answerCount++ ;
			   }
		       
		       if (StringUtils.isBlank(examQuestionResults.get(i).getAnswer().replaceAll(QuestionListCriteria.FILLIN_ANSWER_SEPERATE, ""))) {
		    	   examQuestionResults.get(i).setAnswer(null);
			   }
		       
			} else {
			  //问答题或计算题
			 if (answerVideoPath != null && answerVideoPath.length > 0) {
				
		        for (int j = 0; j < questionId.length; j++) {
				if (examQuestion.getExamQuestionId().equals(questionId[j])) {
				   examQuestionResults.get(i).setAnswerVideo(answerVideoPath[j]);
				   flag = true;
				   break;
				 }
		       
			   }
			 }
			 
		      if (StringUtils.isNotBlank(examQuestionResults.get(i).getAnswer())
		    		  || flag) {
		    	 //如果不为空或者有上传音视频 则算做了该题
			     answerCount++ ;
			  }	
			}
		}
		//保存学生提交答题
		int i = examQuestionResultMapper.insertExamQuestionResults(examQuestionResults);
	    if (i > 0) {
	    	//更新学生试卷状态
	    	ExamResult examResult = examResultMapper.selectByPrimaryKey(examResultId);
	    	ExamTask examTask = examTaskMapper.selectByPrimaryKey(examTaskId);              
	        if (examResult != null && examTask != null) {
	            examResult.setStatus(ExamResult.SUBMITTED); //已提交
	        	examResult.setAnswerCount(String.valueOf(answerCount));
	    		examResult.setCommitTime(new Date());
	    		if (examTask.getFinishedTime()!=null && examResult.getCommitTime().after(examTask.getFinishedTime())) {
					examResult.setIsDelay("Y");
				} else {
					examResult.setIsDelay("N");
				}
	    		ExamTask etTask = new ExamTask();
		    	etTask.setExamTaskId(examTaskId);
		    	//获取当前checked 的人数
		    	ExamResult erResult = examResultMapper.getExamResultStatus(examTaskId);
		    	etTask.setFinishedCount(Integer.parseInt(erResult.getSubmitCnt())+1);
		    	examTaskMapper.updateExamTaskCount(etTask);
	    	    examResultMapper.updateExamResultById(examResult);
	    	   
	    	    
			}
		}
	}

	/**
	 * 
	* @Title: getIdByName
	* @Description: 根据名称获取getExamTypeIdByName
	* @param @return
	* @return String    
	* @throws
	 */
	public String getExamTypeIdByName(String name){
		return examTaskMapper.getExamTypeIdByName(name);
	}
	
	/**
	 * 学生空间 - 获取测试信息
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamTaskView getStudentTaskView(String examTaskId, String userId) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("examTaskId", examTaskId);
	    map.put("userId", userId);
		return examTaskMapper.getStudentTaskView(map);
	}
	
	/**
	 * 学生空间 - 获取测试结果
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamResult getStudentExamResult(String examTaskId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("userId", userId);
		return examTaskMapper.getStudentExamResult(map);
	}

	/**
	 * 学生空间 - 检验测试题目是否全对
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamResult checkPracticeExam(String examTaskId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("userId", userId);
		return examTaskMapper.checkPracticeExam(map);
	}
	
	/**
	 * 学生空间 - 随机获取  巩固测试需要的题目
	 * @author zhangshuangquan
	 * @param areaIdPath 
	 * @param schoolId 
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQuestionListResult> getPracticeExamByRandom(ExamResult examResult, String areaIdPath,
			String schoolId) {
		
		//获取巩固测试的题目错误的本题
	    List<ExamQuestionListResult> examQuestions = examQuestionMapper.getPracticeExamByResultId(examResult.getExamResultId());
	    	
	    //获取巩固测试的错误题目对应题库中的题
	    List<QueQuestion> queQuestions = queQuestionMapper.getQuestionsByResultId(examResult.getExamResultId());
	    
	    //获取的巩固测试  在题库中的题目 id
	    List<QuestionKnowledgeView> examQuestionResults = new ArrayList<QuestionKnowledgeView>();
	    
	    //原来的题目 id
	    List<String> examQuestionIds = new ArrayList<String>();
	    
	    //要返回的 总的题目
	    List<ExamQuestionListResult> examQueLists = new ArrayList<ExamQuestionListResult>();
	    	
	    if (queQuestions != null && queQuestions.size() > 0) {
		
	    	if (examQuestions.size() > queQuestions.size()) {
			   //除去 原错题 在题库中没有 对应的题目
	    	   List<ExamQuestionListResult> questions = new ArrayList<ExamQuestionListResult>();
	    		
	    	   for (int i = 0; i < examQuestions.size(); i++) {
					
	    			for (int j = 0; j < queQuestions.size(); j++) {
						
	    				if (!examQuestions.get(i).getQueQuestionId().equals(queQuestions.get(j).getQueQuestionId())) {
	    					questions.add(examQuestions.get(i));
	    					examQuestions.remove(i);
						}
					}
				 
	    	   }
	    	   //根据知识点随机选题
	    	   List<QuestionKnowledgeView> practice = queQuestionMapper.getQuestionsRandom(questions,areaIdPath,schoolId);
	    	   
	    	   getExamQuestionByknow(examQuestions, examQuestionResults, examQuestionIds, examQueLists, practice, areaIdPath, schoolId);
	    	  
	    	   return examQueLists;
	    	}
	    	
	    	//获取错误题目 在题库中 的题   对应的母题
	    	List<QueQuestion> examMotherQuestions = queQuestionMapper.getPracticeMotherExam(queQuestions);
		    	
		    //根据错误的本题 获取 错误题在题库中 对应的题目  母题  孪生题 或衍生题  每道题随机选2个
		    examQuestionResults = queQuestionMapper.getPracticeExamByRelation(queQuestions);
		    
		    if (examQuestionResults != null && examQuestionResults.size() > 0) {
				//获取巩固测试题目
		    	getPracticeExam(queQuestions, examMotherQuestions, examQuestionResults, examQuestionIds, examQuestions,areaIdPath,schoolId);
		    
		    	//获取题库中的题目
		    	List<ExamQuestionListResult> examPracticeList1 = queQuestionMapper.getQuestionByIds(examQuestionResults);
		    	
		    	if(examQuestionIds != null && examQuestionIds.size() > 0){
		    	  //获取原题的题
			      List<ExamQuestionListResult> eListResults = examQuestionMapper.getOriginalExamQuestion(examQuestionIds);
			      examQueLists.addAll(eListResults);	
		    	}
		    	
		    	examQueLists.addAll(examPracticeList1);
		       
		    	
		        return examQueLists;
		    
		    }else {
			    /**
			     * 一道题都没有随到
			     * 根据原错题的知识点直接在题库中随机两题
			     * 
			     */
		    	List<QuestionKnowledgeView> practice = queQuestionMapper.getQuestionsRandom(examQuestions,areaIdPath,schoolId);
		    
		    	
		    	getExamQuestionByknow(examQuestions, examQuestionResults, examQuestionIds, examQueLists, practice,areaIdPath,schoolId); 

		    	return examQueLists;
			}
		    
           } else {
			   
        	   /*
        	    * 若原错题在题库中没有对应的题目
        	    * 
        	    * 根据原错题的知识点直接在题库中随机两题
        	    */
        	   List<QuestionKnowledgeView> practice = queQuestionMapper.getQuestionsRandom(examQuestions, areaIdPath,schoolId);
        	   
        	   getExamQuestionByknow(examQuestions, examQuestionResults, examQuestionIds, examQueLists, practice,areaIdPath,schoolId); 
		      
        	   return examQueLists;
           }
	}


	/**
	 * 根据知识点选题
	 * @author zhangshuangquan
	 * @param schoolId 
	 * @param areaIdPath 
	 * @param
	 * @return 
	 *
	 */
	private void getExamQuestionByknow(List<ExamQuestionListResult> examQuestions,
			List<QuestionKnowledgeView> examQuestionResults, List<String> examQuestionIds, List<ExamQuestionListResult> examQueLists,
			List<QuestionKnowledgeView> practice, String areaIdPath, String schoolId) {
		
		if (practice != null && practice.size() > 0) {
			   
			   getQuestionsByKnowledges(examQuestions, examQuestionResults, practice, examQuestionIds);
			   
			
		    	//获取题库中的题目
		    	List<ExamQuestionListResult> examPracticeList1 = queQuestionMapper.getQuestionByIds(examQuestionResults);
			
		    	if (examQuestionIds != null && examQuestionIds.size() > 0) {
			       //获取原题的题
			       List<ExamQuestionListResult> eListResults = examQuestionMapper.getOriginalExamQuestion(examQuestionIds);
			       examQueLists.addAll(eListResults);
		    	}
			   examQueLists.addAll(examPracticeList1);
			   
			 
		   
		   }else {
			   
			   List<String> examIds = new ArrayList<String>();
			   for (int i = 0; i < examQuestions.size(); i++) {
				   examIds.add(examQuestions.get(i).getExamQuestionId());
			   }
			   List<ExamQuestionListResult> results = examQuestionMapper.getOriginalExamQuestion(examIds);
			   examQueLists.addAll(results);
			
		   }
	}
	
	/**
	 * 直接获取错题 在题库中的知识点
	 * @author zhangshuangquan
	 * @param examQuestionIds 
	 * @param
	 * @return 
	 *
	 */
	private void getQuestionsByKnowledges(List<ExamQuestionListResult> examQuestions, List<QuestionKnowledgeView> examQuestionResults,
			List<QuestionKnowledgeView> practice, List<String> examQuestionIds) {
	
		    //两个集合求 差 集 取出 需要用原题作为巩固测试 题目 的id
			
		    List<String> originQuestionIds = new ArrayList<String>();
			
		    int num = 0;
			ExamQuestionListResult eq = null;
			for (int i = 0; i < examQuestions.size(); i++) {
				num = 0;
			    eq = examQuestions.get(i);
				for (int j = 0; j < practice.size(); j++) {
					
					if (eq.getExamQuestionId().equals(practice.get(j).getExamQuestionId())) {
					   num++;
				    }
				}
				if (num < 2) {
					originQuestionIds.add(eq.getExamQuestionId());
				}
			}
			
			//加入题目
			//List<QueQuestion> examPracticeList = queQuestionMapper.getExamQuestionList(practice);
			examQuestionResults.addAll(practice);
		   
			//根据需要用原题作为巩固测试 题目 的id 获取原题
			if (originQuestionIds !=null && originQuestionIds.size() > 0) {
				//获取差一题的原错题
				//List<QueQuestion> orResults = examQuestionMapper.getOriginalExamQuestion(originQuestionIds);
			    //examQuestionResults.addAll(orResults);
				examQuestionIds.addAll(originQuestionIds);
			}
	}

	/**
	 * 根据知识点获取题
	 * @author zhangshuangquan
	 * @param examQuestionIds 
	 * @param examQuestions 
	 * @param schoolId 
	 * @param areaIdPath 
	 * @param
	 * @return 
	 *
	 */
	private void getPracticeExam(List<QueQuestion> queQuestions, List<QueQuestion> examMotherQuestions,
			List<QuestionKnowledgeView> examQuestionResults, List<String> examQuestionIds, List<ExamQuestionListResult> examQuestions, String areaIdPath, String schoolId) {
		    //选出不够两题的 题目  再根据知识点补全为两题
			int count = 0;
			List<String> examQuestionIdList1 = new ArrayList<String>();
	    	List<String> examQuestionIdList2 = new ArrayList<String>();
	    	
			for (int i = 0; i < examMotherQuestions.size(); i++) {
			    count = 0;
				for (int j = 0; j < examQuestionResults.size(); j++) {
					
					if (examQuestionResults.get(j).getMotherId().equals(examMotherQuestions.get(i).getQueQuestionId())) {
						count ++;
					}
				}
				if (count == 1) {
					//差一道题的题目对应题库中题的母题Id
					examQuestionIdList1.add(examMotherQuestions.get(i).getQueQuestionId());
				}
				if (count == 0) {
					//差两道题的题目对应题库中题的母题id
					examQuestionIdList2.add(examMotherQuestions.get(i).getQueQuestionId());
				}
			}
			
		   /*
		    * 如果是差一题的题目  与   错题对应题库中的题目   求  交集    可以得出 对应题库中查一题的 题目  
		    * 
		    * 再通过求交集得出的 题目  找到  原来的错题     获取原来错题的知识点
		    */
			
			List<String> originQuestionIds1 = null;
			
			List<QuestionKnowledgeView> practice1 = null;
			//根据知识点选题
			if (examQuestionIdList1 != null && examQuestionIdList1.size() > 0) {
				
				//求差集 获取题库中差一题的题目
				List<QuestionKnowledgeView> queIds = queQuestionMapper.getQuestionIntersection(examQuestionIdList1, queQuestions);
				
				//获取差一题的原来的错题题目
				List<ExamQuestionListResult> originResults1 = examQuestionMapper.getOriginExamResults(queIds, examQuestions);
				
				
				//根据知识点随机获取一道题
		    	practice1 = queQuestionMapper.getPracticeExamByKnowledge(originResults1, queIds, examQuestionResults, queQuestions,areaIdPath,schoolId);
		    	
		    	if (practice1 != null && practice1.size() > 0) {
		            //两个集合求 差 集 取出 需要用原题作为巩固测试 题目 的id
					
		    		originQuestionIds1 = new ArrayList<String>();
					
					List<String> minusList = new ArrayList<String>();
					for (int i = 0; i < practice1.size(); i++) {
						 minusList.add(practice1.get(i).getExamQuestionId());
					}
					//求差集
					for (int i = 0; i < originResults1.size(); i++) {
					  originQuestionIds1.add(originResults1.get(i).getExamQuestionId());
					}
					originQuestionIds1.removeAll(minusList);
					
					//加入题目 并且获取到 知识点 和 对应的填空题答案
					//List<QueQuestion> examPracticeList1 = queQuestionMapper.getExamQuestionList(practice1);
					
					examQuestionResults.addAll(practice1);
		    	}	
			}
			
			List<String> originQuestionIds2 = null;
			List<QuestionKnowledgeView> practice2 = null;
			if (examQuestionIdList2 != null && examQuestionIdList2.size() > 0) {
				
				//求差集 获取题库中差两题的题目
				List<QuestionKnowledgeView> queIds = queQuestionMapper.getQuestionIntersection(examQuestionIdList2, queQuestions);
				
				//获取差两题的原来的错题题目
				List<ExamQuestionListResult> originResults2 = examQuestionMapper.getOriginExamResults(queIds, examQuestions);
				
				
				//根据知识点随机获取两道题
				practice2 = queQuestionMapper.getPracticeByKnowledge(originResults2, queIds, examQuestionResults, queQuestions,areaIdPath,schoolId);
				
				if (practice2 != null && practice2.size() > 0) {
					
					//怎样判断差两题的题目
					originQuestionIds2 = new ArrayList<String>();
					int num = 0;
					ExamQuestionListResult eq = null;
					for (int i = 0; i < originResults2.size(); i++) {
						num = 0;
					    eq = originResults2.get(i);
						for (int j = 0; j < practice2.size(); j++) {
							
							if (eq.getExamQuestionId().equals(practice2.get(j).getExamQuestionId())) {
							   num++;
						    }
						}
						if (num < 2) {
							originQuestionIds2.add(eq.getExamQuestionId());
						}
					}
					
					//加入题目
					//List<QueQuestion> examPracticeList2 = queQuestionMapper.getExamQuestionList(practice2);
				    examQuestionResults.addAll(practice2);	
					
				}
			}
			
			//根据需要用原题作为巩固测试 题目 的id 获取原题
			if (originQuestionIds1 !=null && originQuestionIds1.size() > 0) {
				//获取差一题的原错题
				//List<QueQuestion> orResults = examQuestionMapper.getOriginalExamQuestion(originQuestionIds1);
			    //examQuestionResults.addAll(orResults);
				examQuestionIds.addAll(originQuestionIds1);
			}
			
			if (originQuestionIds2 != null && originQuestionIds2.size() > 0) {
				//获取差两题的原错题
				//List<QueQuestion> orResults2 = examQuestionMapper.getOriginalExamQuestion(originQuestionIds2);
				examQuestionIds.addAll(originQuestionIds2);
			}
	}
	

	/**
	 * 保存 巩固测试题目信息
	 * @author zhangshuangquan
	 * @param examTaskId 
	 * @param
	 * @return 
	 *
	 */
	public String insertPracticeExam(ExamResult examResult, String examTaskId, List<ExamQuestionListResult> examQuestion) {
		//获取测试 
		String examPracticeId = null;
	
		ExamPractice examPractice = new ExamPractice();
		examPractice.setExamPracticeId(UUIDUtils.getUUID());
		examPractice.setQuestionCount(examQuestion.size());
		examPractice.setCreateTime(new Date());
		examPractice.setExamResultId(examResult.getExamResultId());
		examPractice.setStatus(ExamPractice.INIT);
		
		
		//巩固测试题目
		List<ExamPracticeQuestion> examPracticeQuestions = new ArrayList<ExamPracticeQuestion>();
		
	    //巩固测试填空题答案	
		List<ExamQueFillInAnswer> examPracticeFillAnswers = new ArrayList<ExamQueFillInAnswer>();
		
		//巩固测试知识点
		List<ExamQuestionRKnowledge> examPracticeKnowledge = new ArrayList<ExamQuestionRKnowledge>();
		
		//巩固测试与基础知识点
		List<ExamPracQuesRKnowledge> ePracQuesRKnowledges = new ArrayList<ExamPracQuesRKnowledge>();
		
		//获取巩固测试的题目 以及知识点和填空题答案
		getExamPracticeFillAnswerAndKnow(examQuestion, examPractice, examPracticeQuestions, examPracticeFillAnswers, examPracticeKnowledge,
				ePracQuesRKnowledges);
		
		
		//插入巩固测试
		examPracticeMapper.insertSelective(examPractice);
		
		//插入巩固测试题目
		examPracticeQuestionMapper.insertBatch(examPracticeQuestions);
		
		//插入巩固测试的填空题答案
		examQueFillInAnswerMapper.insertFillAnswerBatch(examPracticeFillAnswers);
		
		//插入巩固测试的知识点
		examQuestionRKnowledgeMapper.insertKnowledgeBatch(examPracticeKnowledge);
		
		//插入巩固测试基础知识点
		examPracQuesRKnowledgeMapper.insertPracKnowlegeBatch(ePracQuesRKnowledges);
		
		examPracticeId = examPractice.getExamPracticeId();
		
		return examPracticeId;
	}

    /**
     * 获取 巩固测试 的知识点 和填空题答案
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
	private void getExamPracticeFillAnswerAndKnow(List<ExamQuestionListResult> examQuestion, ExamPractice examPractice,
			List<ExamPracticeQuestion> examPracticeQuestions, List<ExamQueFillInAnswer> examPracticeFillAnswers,
			List<ExamQuestionRKnowledge> examPracticeKnowledge, List<ExamPracQuesRKnowledge> ePracQuesRKnowledges) {
		for (int i = 0; i < examQuestion.size(); i++) {
			ExamPracticeQuestion eQuestion = new ExamPracticeQuestion();
			eQuestion.setExamPracticeQuestionId(UUIDUtils.getUUID());
			eQuestion.setExamPracticeId(examPractice.getExamPracticeId());
			eQuestion.setContent(examQuestion.get(i).getContent());
			eQuestion.setOptions(examQuestion.get(i).getOptions());
			eQuestion.setAnswer(examQuestion.get(i).getAnswer());
			eQuestion.setResolve(examQuestion.get(i).getResolve());
			eQuestion.setDifficulty(examQuestion.get(i).getDifficulty());
			eQuestion.setType(examQuestion.get(i).getType());
			eQuestion.setResolveVideo(examQuestion.get(i).getResolveVideo());
			eQuestion.setFillInAnswerType(examQuestion.get(i).getFillInAnswerType());
			eQuestion.setFillInScoreType(examQuestion.get(i).getFillInScoreType());
			eQuestion.setContentVideo(examQuestion.get(i).getContentVideo());
			
			
			
			List<ExamQueFillInAnswer> eFillInAnswers = examQuestion.get(i).getFillInAnswers();
			
			//填空题答案
			for (int j = 0; j < eFillInAnswers.size(); j++) {
				ExamQueFillInAnswer examQueFillInAnswer = new ExamQueFillInAnswer();
				examQueFillInAnswer.setExamQueFillInAnswerId(UUIDUtils.getUUID());
				examQueFillInAnswer.setExamPracticeQuestionId(eQuestion.getExamPracticeQuestionId());
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp1())) {
					examQueFillInAnswer.setAnswerGrp1(eFillInAnswers.get(j).getAnswerGrp1());
				}
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp2())) {
					examQueFillInAnswer.setAnswerGrp2(eFillInAnswers.get(j).getAnswerGrp2());
				}
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp3())) {
					examQueFillInAnswer.setAnswerGrp3(eFillInAnswers.get(j).getAnswerGrp3());
				}
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp4())) {
					examQueFillInAnswer.setAnswerGrp4(eFillInAnswers.get(j).getAnswerGrp4());
				} 
				
				
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp1Txt())) {
					examQueFillInAnswer.setAnswerGrp1Txt(eFillInAnswers.get(j).getAnswerGrp1Txt());
				} 
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp2Txt())) {
					examQueFillInAnswer.setAnswerGrp2Txt(eFillInAnswers.get(j).getAnswerGrp2Txt());
				}
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp3Txt())) {
					examQueFillInAnswer.setAnswerGrp3Txt(eFillInAnswers.get(j).getAnswerGrp3Txt());
				} 
				
				if (StringUtils.isNotBlank(eFillInAnswers.get(j).getAnswerGrp4Txt())) {
					examQueFillInAnswer.setAnswerGrp4Txt(eFillInAnswers.get(j).getAnswerGrp4Txt());
				} 
				
				examQueFillInAnswer.setSort(eFillInAnswers.get(j).getSort());
				examPracticeFillAnswers.add(examQueFillInAnswer);
			}
			
			//知识点
			List<ExamQuestionRKnowledge> eRKnowledges = examQuestion.get(i).getrKnowledges();
			for (int j = 0; j < eRKnowledges.size(); j++) {
				ExamQuestionRKnowledge eKnowledge = new ExamQuestionRKnowledge();
				eKnowledge.setExamQuestionRKnowledgeId(UUIDUtils.getUUID());
				eKnowledge.setExamPracticeQuestionId(eQuestion.getExamPracticeQuestionId());
				
				eKnowledge.setBaseKnowledgeId(eRKnowledges.get(j).getBaseKnowledgeId());
				
				if (StringUtils.isNotBlank(eRKnowledges.get(j).getBaseSubKnowledge1Id())) {
					eKnowledge.setBaseSubKnowledge1Id(eRKnowledges.get(j).getBaseSubKnowledge1Id());
				}
				
				if (StringUtils.isNotBlank(eRKnowledges.get(j).getBaseSubKnowledge2Id())) {
					eKnowledge.setBaseSubKnowledge2Id(eRKnowledges.get(j).getBaseSubKnowledge2Id());
				}
				
				if (StringUtils.isNotBlank(eRKnowledges.get(j).getBaseSubKnowledge3Id())) {
					eKnowledge.setBaseSubKnowledge3Id(eRKnowledges.get(j).getBaseSubKnowledge3Id());
				}
				
				if (StringUtils.isNotBlank(eRKnowledges.get(j).getBaseSubKnowledge4Id())) {
					eKnowledge.setBaseSubKnowledge4Id(eRKnowledges.get(j).getBaseSubKnowledge4Id());
				}
				
				eKnowledge.setBaseEndKnowledgeId(eRKnowledges.get(j).getBaseEndKnowledgeId());
				
				//巩固测试与基础知识点
				ExamPracQuesRKnowledge exQuesRKnowledge = new ExamPracQuesRKnowledge();
				exQuesRKnowledge.setExamPracticeQuestionId(eQuestion.getExamPracticeQuestionId());
				exQuesRKnowledge.setBaseKnowledgeId(eRKnowledges.get(j).getBaseKnowledgeId());
			
				
				examPracticeKnowledge.add(eKnowledge);
				ePracQuesRKnowledges.add(exQuesRKnowledge);
				
				}
			
			examPracticeQuestions.add(eQuestion);
		}
	}
	
    /**
     * 获取测试信息	
     * @author zhangshuangquan
     * @param
     * @return 
     *
     */
	public ExamPractice getPracticeByExamResultId(String examResultId) {
		return examPracticeMapper.getPracticeByExamResultId(examResultId);
	}
	
	/**
	 * 获取巩固测试题目
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamPracticeQuestion> getExamPracticeQuestion(String examPracticeId) {
		return examPracticeQuestionMapper.getExamPracticeQuestion(examPracticeId);
	}
	

	/**
	 * 获取巩固测试题目
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamPracticeQuestion getExamPracticeQuestionById(String examPracticeQuestionId) {
		return examPracticeQuestionMapper.selectByPrimaryKey(examPracticeQuestionId);
	}
	

	/**
	 * 巩固测试提交答案
	 * @author zhangshuangquan
	 * @param examPracticeId 
	 * @param
	 * @return 
	 *
	 */
	public void answerPracticeExam(List<ExamPracticeQuestion> examPracticeQuestions, String examPracticeId, String[] questionId, String[] answerVideoPath) {
		int answerCount = 0;  //回答题目的数量
		boolean flag = false;

		for (int i = 0; i < examPracticeQuestions.size(); i++) {
			flag = false;
			//获取巩固测试题目 
			ExamPracticeQuestion ePracticeQuestion = examPracticeQuestionMapper.selectByPrimaryKey(examPracticeQuestions.get(i).getExamPracticeQuestionId());
			
			//单选  多选  判断
			if (ePracticeQuestion.getType().equals(QuestionType.single_option.getValue())
					|| ePracticeQuestion.getType().equals(QuestionType.multple_option.getValue())
					|| ePracticeQuestion.getType().equals(QuestionType.judement.getValue())) {
				
				if (ePracticeQuestion.getAnswer().equals(examPracticeQuestions.get(i).getMyAnswer())) {
					examPracticeQuestions.get(i).setResult(ExamQuestionResult.RESULT_RIGHT);
					
				}else {
					examPracticeQuestions.get(i).setResult(ExamQuestionResult.RESULT_WRONG);
					
				}
				
				if(StringUtils.isNotBlank(examPracticeQuestions.get(i).getMyAnswer())){
					answerCount++ ;
				}
				
			}else if (ePracticeQuestion.getType().equals(QuestionType.fill_in.getValue())) {
		       //填空题
			   
			   //答对空数
			   int rightNum = checkFillinPracticeQuestion(ePracticeQuestion, examPracticeQuestions.get(i).getMyAnswer());
			   
			   //总空数
			   int allNum = examPracticeQuestions.get(i).getMyAnswer().split(QuestionListCriteria.FILLIN_ANSWER_SEPERATE).length;
			   
		       if (allNum == 0) {
		    	   examPracticeQuestions.get(i).setResult(ExamQuestionResult.RESULT_WRONG);
			   } else {
				  if (rightNum == allNum) {
					   //获得满分则算回答正确
					  examPracticeQuestions.get(i).setResult(ExamQuestionResult.RESULT_RIGHT); 
				  } else {
					  examPracticeQuestions.get(i).setResult(ExamQuestionResult.RESULT_WRONG);
				  }
			   }
			   
		       if (StringUtils.isNotBlank(examPracticeQuestions.get(i).getMyAnswer().replaceAll(QuestionListCriteria.FILLIN_ANSWER_SEPERATE, ""))){
				   //如果不为空则算做了该题
		    	   answerCount++ ;
			   }
		       
		       if (StringUtils.isBlank(examPracticeQuestions.get(i).getMyAnswer().replaceAll(QuestionListCriteria.FILLIN_ANSWER_SEPERATE, ""))) {
		    	   examPracticeQuestions.get(i).setMyAnswer(null);
			   }
		       
		       
			} else {
			  //问答题或计算题
		    
			 if (answerVideoPath != null && answerVideoPath.length > 0) {
				
				
		        for (int j = 0; j < questionId.length; j++) {
				 if (ePracticeQuestion.getExamPracticeQuestionId().equals(questionId[j])) {
				     examPracticeQuestions.get(i).setAnswerVideo(answerVideoPath[j]);
				     flag = true;
				     break;
				 }
		       
			   }
			 }
			 
		      if (StringUtils.isNotBlank(examPracticeQuestions.get(i).getMyAnswer())
		    		  || flag) {
		    	   //如果不为空或者有上传音视频 则算做了该题
		    	  answerCount++ ;
			  }	
			}
		}
		//保存学生提交巩固测试答题
	    examPracticeQuestionMapper.updateExamPracticeAnswer(examPracticeQuestions);
	     
	    examPracticeMapper.updatePracticeQuestion(answerCount, examPracticeId,ExamPractice.END);
	   
	}
	
	/**
	 * 学生空间 - 获取巩固测试知识点
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamQstRKnowledgeExtend> getPracticeExamKnowledge(String examPracticeQuestionId) {
		//获取知识点
		List<ExamQstRKnowledgeExtend> rKnowledgeLi = examQuestionMapper.getPracticeExamKnowledge(examPracticeQuestionId);
		if (rKnowledgeLi != null && rKnowledgeLi.size() > 0){
			//获取巩固测试对应的基础知识点
			List<BaseKnowledge> baseKnowledgeLi = examPracQuesRKnowledgeMapper.getAllBaseKnowledge(examPracticeQuestionId);
					
			//将知识点列表组装成map
		    Map<String,BaseKnowledge> knowledgeMap = new HashMap<String, BaseKnowledge>();
				    
			BaseKnowledge knowledgeObj = null;
					
			for (int i = 0; i< baseKnowledgeLi.size(); i++){
				 knowledgeObj = baseKnowledgeLi.get(i);
				 knowledgeMap.put(knowledgeObj.getBaseKnowledgeId(), knowledgeObj);				
			}
				   
			//将关联知识点ID和名字关联到习题属性
			ExamQstRKnowledgeExtend rKnowledgeObj = null;
				    
			BaseKnowledge refKnowledgeObj = null;
				
			for(int k = 0; k < rKnowledgeLi.size(); k++){
				rKnowledgeObj = rKnowledgeLi.get(k);

				refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseKnowledgeId());
				rKnowledgeObj.setBaseKnowledgeName(refKnowledgeObj.getKnowledgeName());

				if (rKnowledgeObj.getBaseSubKnowledge1Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge1Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge1Name(refKnowledgeObj.getKnowledgeName());
					}
					
				}

				if (rKnowledgeObj.getBaseSubKnowledge2Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge2Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge2Name(refKnowledgeObj.getKnowledgeName());
					}
				}

				if (rKnowledgeObj.getBaseSubKnowledge3Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge3Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge3Name(refKnowledgeObj.getKnowledgeName());
					}
				}

				if (rKnowledgeObj.getBaseSubKnowledge4Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge4Id());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge4Name(refKnowledgeObj.getKnowledgeName());
					}
				}

				if (rKnowledgeObj.getBaseSubKnowledge5Id() != null) {
					refKnowledgeObj = knowledgeMap.get(rKnowledgeObj.getBaseSubKnowledge5Id());
					rKnowledgeObj.setBaseSubKnowledge5Name(refKnowledgeObj.getKnowledgeName());
					if (refKnowledgeObj != null) {
						rKnowledgeObj.setBaseSubKnowledge5Name(refKnowledgeObj.getKnowledgeName());
					}
				}
			}
						
		}
		return rKnowledgeLi;
	}
	
	/**
	 * 学生空间 - 获取巩固测试统计结果
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamPractice getPracticeStatistics(String examPracticeId) {
		return examPracticeMapper.getPracticeStatistics(examPracticeId);
	}
	
	/**
	 * 班级空间 - 查看测试
	 * @author zhangshuangquan
	 * @param classId 
	 * @param classlevelId 
	 * @param
	 * @return 
	 *
	 */
	public ExamTaskView getClsClassExamTask(String examTaskId, String classlevelId, String classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examTaskId", examTaskId);
		map.put("classlevelId", classlevelId);
		map.put("classId", classId);
		return examTaskMapper.getClsClassExamTask(map);
	}
	
	/**
	 * 班级空间 - 查看测试
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public ExamTaskView getClsClassExamTaskById(String examTaskId) {
		return examTaskMapper.getClsClassExamTaskById(examTaskId);
	}


	//***************************************************************私有方法************************************************
	//学生测试判断填空题答对空数
	private int checkFillinQuestion(String examQuestionId, String answer) {
		//总分
		int score = 0;
		//获取填空题内容
		ExamQuestion eq =examQuestionMapper.selectByPrimaryKey(examQuestionId);
		//获取填空题所有答案
	    List<ExamQueFillInAnswer> eanswers = examQueFillInAnswerMapper.getQueFillInAnswerByQuestionId(examQuestionId);
		
	    Map<String, ExamQueFillInAnswer> eanswermap =new HashMap<String, ExamQueFillInAnswer>();
		for (ExamQueFillInAnswer ea : eanswers) {
			eanswermap.put(ea.getSort(), ea);
		}
	    
		//拆分答案
	    String[] myanswer = answer.split(QuestionListCriteria.FILLIN_ANSWER_SEPERATE);
				
		if (AnswerType.independent.getValue().equals(eq.getFillInAnswerType())) {
			//独立答案
			if (ScoreType.partial.getValue().equals(eq.getFillInScoreType())) {
				//按空给分
				for (int i = 0; i < myanswer.length; i++) {
					score += checkOneFill(eanswermap.get((i+1)+""), myanswer[i]);
				}
			}else if(ScoreType.all.getValue().equals(eq.getFillInScoreType())){
				//全对给分
				int myscore = 0;
				for (int i = 0; i < myanswer.length; i++) {
					myscore += checkOneFill(eanswermap.get((i+1)+""), myanswer[i]);
				}
				
				if(myscore == eanswermap.size()){
					score += myscore;
				}
			}	
			
		} else if (AnswerType.combination.getValue().equals(eq.getFillInAnswerType())) {
			//如果是组合答案
			List<String> group1 =new ArrayList<String>();
			List<String> group2 =new ArrayList<String>();
			List<String> group3 =new ArrayList<String>();
			List<String> group4 =new ArrayList<String>();
			
			for (int i = 0; i < myanswer.length; i++) {
				group1.add(eanswermap.get((i+1)+"").getAnswerGrp1());
				group2.add(eanswermap.get((i+1)+"").getAnswerGrp2());
				group3.add(eanswermap.get((i+1)+"").getAnswerGrp3());
				group4.add(eanswermap.get((i+1)+"").getAnswerGrp4());
			}
			
			int[] myscore = new int[4];
			myscore[0] = checkOneFill(group1, myanswer) ;
			myscore[1] = checkOneFill(group2, myanswer) ;
			myscore[2] = checkOneFill(group3, myanswer) ;
			myscore[3] = checkOneFill(group4, myanswer) ;
			
			if(ScoreType.partial.getValue().equals(eq.getFillInScoreType())){
				//按空给分
				return NumberUtils.max(myscore);
			}else if(ScoreType.all.getValue().equals(eq.getFillInScoreType())){
				//全对给分
				return NumberUtils.max(myscore) == eanswermap.size()?eanswermap.size():0;
			}
			
		}
	    return score;
	}

    //组合答案
	private int checkOneFill(List<String> answers, String[] myanswer) {
	    int score = 0;
		for (int i = 0; i < myanswer.length; i++) {
			if(StringUtils.equals(answerStr(answers.get(i)), answerStr(myanswer[i]))){
				score += 1;
			}
		}
	   return score;
	}


	//单个答案
	private int checkOneFill(ExamQueFillInAnswer answer, String myanswer) {
		if(StringUtils.equals(answerStr(answer.getAnswerGrp1()),answerStr(myanswer))){
			return 1;
		}
		if(StringUtils.equals(answerStr(answer.getAnswerGrp2()),answerStr(myanswer))){
			return 1;
		}
		if(StringUtils.equals(answerStr(answer.getAnswerGrp3()),answerStr(myanswer))){
			return 1;
		}
		if(StringUtils.equals(answerStr(answer.getAnswerGrp4()),answerStr(myanswer))){
			return 1;
		}
		return 0;
	}


	//处理填空题答案和回答的字符串
	private String answerStr(String str){
		if (str == null ){
		    return null;
		} else{
		    str=str.trim().replaceAll("src=\".+?\"","*").replace("/>", ">");
			return str;
		}
	}
	
	//巩固测试判断填空题的答案
	private int checkFillinPracticeQuestion(ExamPracticeQuestion examPracticeQuestion, String myAnswer) {
		//总分
		int score = 0;
	
		//获取填空题所有答案
		List<ExamQueFillInAnswer> eanswers = examQueFillInAnswerMapper.getPracticeFillInAnswer(examPracticeQuestion.getExamPracticeQuestionId());
		
		
		Map<String, ExamQueFillInAnswer> eanswermap =new HashMap<String, ExamQueFillInAnswer>();
		for (ExamQueFillInAnswer ea : eanswers) {
			eanswermap.put(ea.getSort(), ea);
		}
			    
		//拆分答案
		String[] myanswer = myAnswer.split(QuestionListCriteria.FILLIN_ANSWER_SEPERATE);
						
		if (AnswerType.independent.getValue().equals(examPracticeQuestion.getFillInAnswerType())) {
		    //独立答案
			if (ScoreType.partial.getValue().equals(examPracticeQuestion.getFillInScoreType())) {
				 //按空给分
				 for (int i = 0; i < myanswer.length; i++) {
					   score += checkOneFill(eanswermap.get((i+1)+""), myanswer[i]);
				 }
		    }else if(ScoreType.all.getValue().equals(examPracticeQuestion.getFillInScoreType())){
				 //全对给分
				 int myscore = 0;
				 for (int i = 0; i < myanswer.length; i++) {
						myscore += checkOneFill(eanswermap.get((i+1)+""), myanswer[i]);
				 }
						
			     if(myscore == eanswermap.size()){
				    score += myscore;
			     }
		   }	
					
		} else if (AnswerType.combination.getValue().equals(examPracticeQuestion.getFillInAnswerType())) {
				    //如果是组合答案
					List<String> group1 =new ArrayList<String>();
					List<String> group2 =new ArrayList<String>();
					List<String> group3 =new ArrayList<String>();
					List<String> group4 =new ArrayList<String>();
					
					for (int i = 0; i < myanswer.length; i++) {
						group1.add(eanswermap.get((i+1)+"").getAnswerGrp1());
						group2.add(eanswermap.get((i+1)+"").getAnswerGrp2());
						group3.add(eanswermap.get((i+1)+"").getAnswerGrp3());
						group4.add(eanswermap.get((i+1)+"").getAnswerGrp4());
					}
					
					int[] myscore = new int[4];
					myscore[0] = checkOneFill(group1, myanswer) ;
					myscore[1] = checkOneFill(group2, myanswer) ;
					myscore[2] = checkOneFill(group3, myanswer) ;
					myscore[3] = checkOneFill(group4, myanswer) ;
					
					if(ScoreType.partial.getValue().equals(examPracticeQuestion.getFillInScoreType())){
						//按空给分
						return NumberUtils.max(myscore);
					}else if(ScoreType.all.getValue().equals(examPracticeQuestion.getFillInScoreType())){
						//全对给分
						return NumberUtils.max(myscore) == eanswermap.size()?eanswermap.size():0;
					}
					
				}
		return score;
	}
	
	/**
	 * @author renhengli
	 * 查询待批阅学生列表
	 * @param map
	 * @return
	 */
	public List<BaseUser> getReviewStudentList(Map<String, String> map) {
		return examResultMapper.getReviewStudentList(map);
	}
	
	/**
	 * @author renhengli
	 * 获取主观题的在线批阅习题列表
	 * @param map
	 * @return
	 */
	public List<ExamQuestion> getTestQuestionList(Map<String, String> map){
		return examQuestionMapper.getTestQuestionList(map);
	}
	
	/**
	 * @author renhengli
	 * 查询某个学生的某个测试任务的某一题的答案 
	 * @param map
	 * @return
	 */
	public ExamQuestionResult getQuestionAnswer(Map<String, String> map) {
		return examQuestionResultMapper.getQuestionAnswer(map);
	}

	/**
	 * @author renhengli
	 * 统计当前剩余没批阅学生 
	 * @param map
	 * @return
	 */
	public int noReviewStudentCountByQuestionId(Map<String, String> map){
		return examQuestionResultMapper.noReviewStudentCountByQuestionId(map);
	}

	
	/**
	 * @author renhengli
	 * 更新老师评语和得分
	 * @param map
	 * @return
	 */
	public int updateTeaherCommentById(Map<String, Object> map) {
		return examQuestionResultMapper.updateTeaherCommentById(map);
	}

	/**
	 * 对巩固测试的试题进行排序
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamPracticeQuestion> getPracticeQuestionSort(List<ExamPracticeQuestion> practiceQuestions,
			List<ExamPracticeQuestion> examPracticeQuestions) {
		if(examPracticeQuestions != null && examPracticeQuestions.size() > 0){
			List<ExamPracticeQuestion> questions = new ArrayList<ExamPracticeQuestion>();
			ExamPracticeQuestion sortQuestions = null;
	    	for (int i = 0; i < examPracticeQuestions.size(); i++) {
	    		if ("COMPUTING".equals(examPracticeQuestions.get(i).getType())) {
	    			sortQuestions = examPracticeQuestions.get(i);
	    			questions.add(sortQuestions);
					continue;
				}
	    		practiceQuestions.add(examPracticeQuestions.get(i));
			}
	    	practiceQuestions.addAll(questions);
	    }
		return practiceQuestions;
	}

	/**
	 * 获取巩固测试 - 题目顺序
	 * @author zhangshuangquan
	 * @param
	 * @return 
	 *
	 */
	public List<ExamPracticeQuestion> getQuestionTypeByPracticeId(String examPracticeId) {
		return examPracticeQuestionMapper.getQuestionTypeByPracticeId(examPracticeId);
	}
	
	
}
