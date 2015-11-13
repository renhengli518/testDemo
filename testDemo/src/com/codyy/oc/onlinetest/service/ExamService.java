package com.codyy.oc.onlinetest.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.page.Page;
import com.codyy.commons.utils.BeanUtils;
import com.codyy.commons.utils.OracleKeyWordUtils;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.base.dao.BaseKnowledgeDao;
import com.codyy.oc.base.dao.BaseSemesterDao;
import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.base.form.ExamFormFields;
import com.codyy.oc.base.form.ExamListCriteria;
import com.codyy.oc.base.form.QuestionListCriteria;
import com.codyy.oc.base.form.QuestionListCriteria.Difficulty;
import com.codyy.oc.base.form.QuestionListCriteria.QuestionType;
import com.codyy.oc.base.form.QuestionListResult;
import com.codyy.oc.base.parse.ErrorInfo;
import com.codyy.oc.base.parse.ExamInfo;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.onlinetest.dao.ExamExamMapper;
import com.codyy.oc.onlinetest.dao.ExamQueFillInAnswerMapper;
import com.codyy.oc.onlinetest.dao.ExamQuestionMapper;
import com.codyy.oc.onlinetest.dao.ExamQuestionRChapterMapper;
import com.codyy.oc.onlinetest.dao.ExamQuestionRKnowledgeMapper;
import com.codyy.oc.onlinetest.entity.ExamExam;
import com.codyy.oc.onlinetest.entity.ExamQueFillInAnswer;
import com.codyy.oc.onlinetest.entity.ExamQuestion;
import com.codyy.oc.onlinetest.entity.ExamQuestionListResult;
import com.codyy.oc.onlinetest.entity.ExamQuestionRChapter;
import com.codyy.oc.onlinetest.entity.ExamQuestionRKnowledge;
import com.codyy.oc.onlinetest.view.ExamView;
import com.codyy.oc.onlinetest.view.TestSearchView;
import com.codyy.oc.questionlib.dao.QueFillInAnswerMapper;
import com.codyy.oc.questionlib.dao.QueQuestionMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRChapterMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRKnowledgeMapper;
import com.codyy.oc.questionlib.entity.QueFillInAnswer;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.entity.QueQuestionRChapter;
import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;
import com.codyy.oc.questionlib.service.QueQuestionService;

@Service
public class ExamService {
	@Autowired
	private QueQuestionMapper questionDao;

	@Autowired
	private QueQuestionService questionService;

	@Autowired
	private CommonsService baseCommonService;

	@Autowired
	private ExamExamMapper examExamDao;

	@Autowired
	private ExamQuestionMapper examQuestionDao;

	@Autowired
	private BaseKnowledgeDao baseKnowledgeDao;

	@Autowired
	private ExamQuestionMapper examQuestionMapper;

	@Autowired
	private ExamQuestionRChapterMapper examQuestionRChapterMapper;

	@Autowired
	private ExamQuestionRKnowledgeMapper examQuestionRKnowledgeMapper;

	@Autowired
	private ExamQueFillInAnswerMapper examQueFillInAnswerMapper;

	@Autowired
	private ExamExamMapper examExamMapper;
	
	@Autowired
	private QueQuestionMapper queQuestionMapper;
	
	@Autowired
	private BaseSemesterDao baseSemesterDao;
	
	@Autowired
	private ExamTaskService examTaskService;
	
	@Autowired
	private QueQuestionRChapterMapper queQuestionRChapterMapper;
	
	@Autowired
	private QueQuestionRKnowledgeMapper queQuestionRKnowledgeMapper;
	
	
	@Autowired
	private QueFillInAnswerMapper queFillInAnswerMapper;
	/**
	 * 智能选题
	 * 
	 * @param qlist
	 * @param selectcatalogs
	 * @return
	 */
	public List<QuestionListResult> searchIntelligenceList(int[] qlist, String[] selectcatalogs, String version, String baseSemesterId,
			String baseSubjectId) {
		List<QuestionListResult> ls = new ArrayList<QuestionListResult>();

		// 单选题获取随机
		ls.addAll(searchIntelligenceListByType(qlist[0], QuestionType.single_option.getValue(), Difficulty.easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[1], QuestionType.single_option.getValue(), Difficulty.not_easy.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[2], QuestionType.single_option.getValue(), Difficulty.normal.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[3], QuestionType.single_option.getValue(), Difficulty.hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[4], QuestionType.single_option.getValue(), Difficulty.very_hard.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));

		// 多选题获取随机
		ls.addAll(searchIntelligenceListByType(qlist[5], QuestionType.multple_option.getValue(), Difficulty.easy.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[6], QuestionType.multple_option.getValue(), Difficulty.not_easy.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[7], QuestionType.multple_option.getValue(), Difficulty.normal.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[8], QuestionType.multple_option.getValue(), Difficulty.hard.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[9], QuestionType.multple_option.getValue(), Difficulty.very_hard.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));

		// 填空题获取随机
		ls.addAll(searchIntelligenceListByType(qlist[10], QuestionType.fill_in.getValue(), Difficulty.easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[11], QuestionType.fill_in.getValue(), Difficulty.not_easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[12], QuestionType.fill_in.getValue(), Difficulty.normal.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[13], QuestionType.fill_in.getValue(), Difficulty.hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[14], QuestionType.fill_in.getValue(), Difficulty.very_hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));

		// 解答题获取随机
		ls.addAll(searchIntelligenceListByType(qlist[15], QuestionType.resolve.getValue(), Difficulty.easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[16], QuestionType.resolve.getValue(), Difficulty.not_easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[17], QuestionType.resolve.getValue(), Difficulty.normal.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[18], QuestionType.resolve.getValue(), Difficulty.hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[19], QuestionType.resolve.getValue(), Difficulty.very_hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));

		// 判断获取随机
		ls.addAll(searchIntelligenceListByType(qlist[20], QuestionType.judement.getValue(), Difficulty.easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[21], QuestionType.judement.getValue(), Difficulty.not_easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[22], QuestionType.judement.getValue(), Difficulty.normal.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[23], QuestionType.judement.getValue(), Difficulty.hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[24], QuestionType.judement.getValue(), Difficulty.very_hard.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));

		// 计算获取随机
		ls.addAll(searchIntelligenceListByType(qlist[25], QuestionType.computing.getValue(), Difficulty.easy.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[26], QuestionType.computing.getValue(), Difficulty.not_easy.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[27], QuestionType.computing.getValue(), Difficulty.normal.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[28], QuestionType.computing.getValue(), Difficulty.hard.getValue(), selectcatalogs,
				baseSemesterId, baseSubjectId, version));
		ls.addAll(searchIntelligenceListByType(qlist[29], QuestionType.computing.getValue(), Difficulty.very_hard.getValue(),
				selectcatalogs, baseSemesterId, baseSubjectId, version));

		for (QuestionListResult questionListResult : ls) {
			questionService.formQuestionDetailResult(questionListResult);
		}

		return ls;
	}

	private List<QuestionListResult> searchIntelligenceListByType(int num, String questiontype, String difficulty, String[] selectcatalogs,
			String baseSemesterId, String baseSubjectId, String version) {
		if (num == 0) {
			return new ArrayList<QuestionListResult>();
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("num", num);
			map.put("questionType", questiontype);
			map.put("difficulty", difficulty);
			map.put("selectcatalogs", selectcatalogs);
			map.put("baseSemesterId", baseSemesterId);
			map.put("baseSubjectId", baseSubjectId);
			map.put("version", version);
			return questionDao.searchIntelligenceList(map);
		}
	};

	/**
	 * 创建试卷
	 * 
	 * @param ef
	 * @return
	 */
	public String createExam(ExamFormFields ef, String userId, String type) {
		ExamExam e = new ExamExam();

		// 生成uuid
		String examid = UUIDUtils.getUUID();

		e.setExamId(examid);
		e.setAnswerTime(ef.getAnswerTime());
		e.setBaseSemesterId(ef.getBaseSemesterId());
		e.setBaseSubjectId(ef.getBaseSubjectId());
		e.setBaseClasslevelId(ef.getBaseClasslevelId());
		e.setExamTypeId(ef.getExamtype());
		e.setBaseUserId(userId);
		e.setCreateTime(new Date());
		e.setUpdateTime(new Date());
		e.setScore(ef.getScoreInput());
		e.setTitle(ef.getExamTitle());
		e.setExamCategoryType(type);
		e.setIsDelete("N");
		e.setAreaName(ef.getAreaName());
		e.setYear(ef.getYear());
		examExamDao.insert(e);

		// 保存试卷题目
		for (int i = 0; i < ef.getQuestionIds().length; i++) {
			QueQuestion q = questionDao.queryQuestionById(ef.getQuestionIds()[i]);

			ExamQuestion eq = new ExamQuestion();
			String examquestionid = UUIDUtils.getUUID();
			eq.setExamQuestionId(examquestionid);
			eq.setExamId(examid);
			// eq.setExamQuestionId(q.getQueQuestionId());
			eq.setAnswer(q.getAnswer());
			eq.setContent(q.getContent());
			eq.setUpdateTime(q.getUpdateTime());
			eq.setDifficulty(q.getDifficulty());
			eq.setFillInAnswerType(q.getFillInAnswerType());
			eq.setFillInScoreType(q.getFillInScoreType());
			eq.setOptions(q.getOptions());
			eq.setOptionsTxt(q.getOptionsTxt());
			eq.setResolve(q.getResolve());
			eq.setResolveVideo(q.getResolveVideo());
			eq.setScore(ef.getScore() == null?0:ef.getScore()[i]);//如果是自测则默认给0分
			eq.setSort((i + 1));
			eq.setType(q.getType());
			eq.setBaseSemesterId(ef.getBaseSemesterId());
			eq.setBaseSubjectId(ef.getBaseSubjectId());
			eq.setBaseClasslevelId(ef.getBaseClasslevelId());
			eq.setMotherId(q.getMotherId());
			eq.setRelationalIndicator(q.getRelationalIndicator());
			eq.setStoreServer(q.getStoreServer());
			eq.setContentVideo(q.getContentVideo());
			eq.setQueQuestionId(q.getQueQuestionId());
			eq.setTempoparyFlag(CommonsConstant.FLAG_NO); 
			examQuestionDao.insert(eq);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("examQuestionId", examquestionid);
			map.put("questionId", ef.getQuestionIds()[i]);
			examExamDao.copyFillInAnswer(map);
			examExamDao.copyQuestionRChapter(map);
			examExamDao.copyQuestionRKnowledge(map);
		}
		
		//添加题目使用次数
		examExamDao.addUseNum(Arrays.asList(ef.getQuestionIds()));

		
		if(ExamListCriteria.ExamCategoryTypeEnum.self.getValue().equals(type)){
			//如果是自测组卷则布置自动给自己
			return examTaskService.finishCreateTask(examid, new String[]{userId},null, null, userId);
		}else{
			return "success";
		}
	}
	
	
	
	
	/**
	 * 编辑试卷
	 * 
	 * @param ef
	 * @return
	 */
	public String updateExam(ExamFormFields ef, String userId, String type,String[] stuIds,String[] classIds,Date beginTime,Date endTime) {
		ExamExam e = new ExamExam();
		String examid = "";
		// 生成uuid
		if(StringUtils.isEmpty(ef.getExamId())){
			//如果是空则为真题试卷复制
			examid = UUIDUtils.getUUID();
			e.setExamId(examid);
			e.setAnswerTime(ef.getAnswerTime());
			e.setBaseSemesterId(ef.getBaseSemesterId());
			e.setBaseSubjectId(ef.getBaseSubjectId());
			e.setBaseClasslevelId(ef.getBaseClasslevelId());
			e.setExamTypeId(ef.getExamtype());
			e.setBaseUserId(userId);
			e.setCreateTime(new Date());
			e.setUpdateTime(new Date());
			e.setScore(ef.getScoreInput());
			e.setTitle(ef.getExamTitle());
			e.setExamCategoryType(type);
			e.setIsDelete("N");
			e.setAreaName(ef.getAreaName());
			e.setYear(ef.getYear());
			examExamDao.insert(e);
			examExamDao.addRealExamUseNum(ef.getRealExamId());
		}else{
			//否则为编辑
			examid = StringUtils.contains(ef.getExamId(), ',')?ef.getExamId().split(",")[0]:ef.getExamId();
			e.setExamId(examid);
			e.setAnswerTime(ef.getAnswerTime());
			e.setExamTypeId(ef.getExamtype());
			e.setUpdateTime(new Date());
			e.setScore(ef.getScoreInput());
			e.setTitle(ef.getExamTitle());
			examExamDao.updateByPrimaryKeySelective(e);
			
		}
		
		//删除被删除的题目
		Map<String, Object> delmap = new HashMap<String, Object>();
		delmap.put("examId", examid);
		delmap.put("questionIds", ef.getQuestionIds());
		examQuestionDao.deleteByExamIdAndQuestionId(delmap);
		
		// 保存或更新试卷题目
		 //获取原试题所有题目
		Set<String> queIds = examQuestionDao.getQueIdsByExamId();
		for (int i = 0; i < ef.getQuestionIds().length; i++) {
			if(queIds.contains(ef.getQuestionIds()[i])){
				//如果是原有题目，则更新排序和分数
				ExamQuestion eq = new ExamQuestion();
				eq.setExamId(examid);
				eq.setQueQuestionId(ef.getQuestionIds()[i]);
				eq.setScore(ef.getScore()[i]);
				eq.setSort((i + 1));
				examQuestionDao.updateSelective(eq);
			}else{
				//如果是新题目则添加记录
				QueQuestion q = questionDao.queryQuestionById(ef.getQuestionIds()[i]);
				ExamQuestion eq = new ExamQuestion();
				String examquestionid = UUIDUtils.getUUID();
				eq.setExamQuestionId(examquestionid);
				eq.setExamId(examid);
				// eq.setExamQuestionId(q.getQueQuestionId());
				eq.setAnswer(q.getAnswer());
				eq.setContent(q.getContent());
				eq.setUpdateTime(q.getUpdateTime());
				eq.setDifficulty(q.getDifficulty());
				eq.setFillInAnswerType(q.getFillInAnswerType());
				eq.setFillInScoreType(q.getFillInScoreType());
				eq.setOptions(q.getOptions());
				eq.setOptionsTxt(q.getOptionsTxt()); 
				eq.setResolve(q.getResolve());
				eq.setResolveVideo(q.getResolveVideo());
				eq.setScore(ef.getScore()[i]);
				eq.setSort((i + 1));
				eq.setType(q.getType());
				eq.setBaseSemesterId(ef.getBaseSemesterId());
				eq.setBaseSubjectId(ef.getBaseSubjectId());
				eq.setBaseClasslevelId(ef.getBaseClasslevelId());
				eq.setMotherId(q.getMotherId());
				eq.setRelationalIndicator(q.getRelationalIndicator());
				eq.setStoreServer(q.getStoreServer());
				eq.setContentVideo(q.getContentVideo());
				eq.setQueQuestionId(q.getQueQuestionId());
				eq.setTempoparyFlag(CommonsConstant.FLAG_NO); 
				examQuestionDao.insert(eq);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("examQuestionId", examquestionid);
				map.put("questionId", ef.getQuestionIds()[i]);
				examExamDao.copyFillInAnswer(map);
				examExamDao.copyQuestionRChapter(map);
				examExamDao.copyQuestionRKnowledge(map);
			}
			
		}

		
		//如果有选择布置的人则为保存并布置
		if(stuIds != null && stuIds.length > 0){
			//如果有学生ID
			examTaskService.finishCreateTask(examid, stuIds, beginTime, endTime, userId);
		}else if(classIds != null && classIds.length > 0){
			//如果是班级ID
			examTaskService.finishCreateClassTask(examid, classIds, beginTime, endTime, userId);
		}
		
		
		return "success";
	}
	
	

	/**
	 * 查询真题试卷列表
	 * 
	 * @param page
	 * @param map
	 * @return
	 * @author renhengli
	 */
	public Page getRealExamPageList(Page page, Map<String, Object> map) {
		List<ExamView> examList = examExamDao.getRealExamPageList(page);
		page.setData(examList);
		return page;
	}

	/**
	 * 教师空间 - 试卷列表
	 * 
	 * @author xiaokan
	 * @param
	 * @return
	 * 
	 */
	public Page getExamList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null ? null : new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("taskType", testSearchView.getTaskType());
		map.put("createUserId", testSearchView.getCreateUserId());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		page.setMap(map);
		List<ExamView> examList = examExamDao.getExamPageList(page);
		page.setData(examList);
		return page;
	}
	
	/**
	 * 学校空间 - 试卷列表
	 * 
	 * @author xiaokan
	 * @param
	 * @return
	 * 
	 */
	public Page getSchoolExamList(TestSearchView testSearchView, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", testSearchView.getBeginTime());
		map.put("endTime", testSearchView.getEndTime() == null ? null : new Date(testSearchView.getEndTime().getTime() + 86400000 - 1));
		map.put("subjectId", testSearchView.getSubjectId());
		map.put("classlevelId", testSearchView.getClasslevelId());
		map.put("examTypeId", testSearchView.getExamTypeId());
		map.put("taskType", testSearchView.getTaskType());
		map.put("createUserId", testSearchView.getCreateUserId());
		map.put("examName", OracleKeyWordUtils.oracleKeyWordReplace(testSearchView.getExamName()));
		page.setMap(map);
		List<ExamView> examList = examExamDao.getSchoolExamPageList(page);
		page.setData(examList);
		return page;
	}

	/**
	 * 
	 * @Title: delExam
	 * @Description: 删除试卷
	 * @param @param examId
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int delExam(String examId) {
		try {
			return examExamDao.deleteByPrimaryKey(examId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 
	 * @Title: delExam
	 * @Description: 删除试卷
	 * @param @param examId
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int delSelfExam(String examTaskId) {
		try {
			return examExamDao.deleteByTaskId(examTaskId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	

	/**
	 * 
	 * @Title: getExamViewById
	 * @Description: 根据ID获取试卷显示信息
	 * @param @param examId
	 * @param @return
	 * @return ExamView
	 * @throws
	 */
	public ExamView getExamViewById(String examId) {

		return examExamDao.getExamViewById(examId);
	}

	/**
	 * 
	 * @Title: getExamById
	 * @Description: 根据ID获取试卷信息
	 * @param @param examId
	 * @param @return
	 * @return ExamExam
	 * @throws
	 */
	public ExamExam getExamById(String examId) {

		return examExamDao.selectByPrimaryKey(examId);
	}

	/**
	 * 查看测试 -获取所有试题
	 * 
	 * @author zhangshuangquan
	 * @param
	 * @return
	 * 
	 */
	public List<ExamQuestionListResult> getExamQuestionByExamId(String examId) {
		List<ExamQuestionListResult> qListResults = examQuestionDao.getExamQuestionByExamId(examId);
		for (ExamQuestionListResult qListResult : qListResults) {
			formQuestionDetailResult(qListResult);
		}
		return qListResults;
	}

	/**
	 * 获取与试题相关的知识点信息
	 * 
	 * @author zhangshuangquan
	 * @param qListResult
	 * @return ExamQuestionListResult
	 * 
	 */
	public ExamQuestionListResult formQuestionDetailResult(ExamQuestionListResult qListResult) {
		// 组装末节知识点部分
		List<ExamQuestionRKnowledge> rknowledgesList = qListResult.getrKnowledges();
		ExamQuestionRKnowledge rknowledge = null;
		if (rknowledgesList != null && rknowledgesList.size() > 0) {
			StringBuilder knowledgeIds = new StringBuilder();
			String kId = "";
			List<String> kIds = new ArrayList<String>();
			// 形成知识点IDs
			for (int j = 0; j < rknowledgesList.size(); j++) {
				rknowledge = rknowledgesList.get(j);
				kId = rknowledge.getBaseEndKnowledgeId();
				kIds.add(kId);
				if (j != (rknowledgesList.size() - 1)) {
					knowledgeIds.append("'" + kId + "',");
				} else {
					knowledgeIds.append("'" + kId + "'");
				}
			}
			qListResult.setKnowledgeEndIds(knowledgeIds.toString());
			// 根据IDs形成末节名称
			StringBuilder knowledgeNames = new StringBuilder();
			List<BaseKnowledge> knowledgeList = baseKnowledgeDao.findKnowledgeListByIds(kIds);// knowledgeIds.toString().split("\\,")
			qListResult.setKnowledgeEndList(knowledgeList);
			if (knowledgeList != null && knowledgeList.size() > 0) {
				BaseKnowledge knowledge = null;
				for (int k = 0; k < knowledgeList.size(); k++) {
					knowledge = knowledgeList.get(k);
					if (k != (knowledgeList.size() - 1)) {
						knowledgeNames.append(knowledge.getKnowledgeName() + ",");
					} else {
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
	 * 
	 * @author xiaokan
	 * @param
	 * @return
	 * 
	 */
	public List<ExamQuestion> getQuestionType(String examId) {
		return examQuestionDao.getQuestionTypeByExamId(examId);
	}

	/**
	 * 添加临时习题到ExamQuestion中
	 * 
	 * @author renhengli
	 * @param queList
	 * @param queQuestList
	 * @param knowList
	 */
	public void createQuestion(List<ExamQuestion> queList, List<ExamQuestionRChapter> queQuestList, List<ExamQuestionRKnowledge> knowList) {

		// 1.插入习题的基本内容到题库
		if (null != queList && queList.size() > 0) {
			for (ExamQuestion question : queList) {
				// 将组卷次数进行初始化操作
				examQuestionMapper.insert(question);
			}
		}
		// 2.插入习题与章节的关系
		if (null != queQuestList && queQuestList.size() > 0) {
			for (ExamQuestionRChapter examQuestionRChapter : queQuestList) {
				examQuestionRChapterMapper.insert(examQuestionRChapter);
			}
		}
		// 3.插入习题与知识点的关系
		if (null != knowList && knowList.size() > 0) {
			for (ExamQuestionRKnowledge examQuestionRKnowledge : knowList) {
				examQuestionRKnowledgeMapper.insert(examQuestionRKnowledge);
			}
		}

		// 4.循环插入填空题
		if (null != queList && queList.size() > 0) {

			for (ExamQuestion quest : queList) {

				List<ExamQueFillInAnswer> queFiList = quest.getFillInAnswers();
				if (queFiList != null && queFiList.size() > 0) {
					for (ExamQueFillInAnswer queFill : queFiList) {
						examQueFillInAnswerMapper.insert(queFill);
					}
				}
			}
		}

	}

	/**
	 * @author renhengli 根据examId 查询试题
	 * @param examId
	 * @return
	 */
	public List<ExamQuestion> selectByExamId(String examId) {
		List<ExamQuestion> list = examQuestionMapper.selectByExamId(examId);
		if (list != null && list.size() > 0) {//组装end知识点（逗号分隔）
			for (ExamQuestion dto : list) {
				String endKonwledges = "";
				List<ExamQuestionRKnowledge> konwledges = examQuestionRKnowledgeMapper.selectKonwledgesByExamQuestionId(dto
						.getExamQuestionId());
				if (konwledges != null && konwledges.size() > 0) {
					for (ExamQuestionRKnowledge knowledge : konwledges) {
						String konwledgeName= baseKnowledgeDao.selecKnowLedgeNameById(knowledge.getBaseEndKnowledgeId());
						endKonwledges+=konwledgeName+",";
					}
					endKonwledges=endKonwledges.substring(0,endKonwledges.length()-1);
				}
				dto.setEndKonwledges(endKonwledges); 
			}
		}
		return list;
	}

	/**
	 * @author renhengli 添加一个空的真题试卷记录（方便添加临时习题）
	 * @param dto
	 * @return
	 */
	public int insert(ExamExam dto) {
		return examExamMapper.insert(dto);
	}
	
	/**
	 * @author renhengli
	 * 查询examQuestion
	 * @param examQuestionId
	 * @return
	 */
	public  ExamQuestion selectExamQueById(String examQuestionId){
		ExamQuestion dto = examQuestionMapper.selectByExamQuestionId(examQuestionId); 
		if(dto!=null){
			String endKonwledges = "";
			List<ExamQuestionRKnowledge> konwledges = examQuestionRKnowledgeMapper.selectKonwledgesByExamQuestionId(dto
					.getExamQuestionId());
			if (konwledges != null && konwledges.size() > 0) {
				for (ExamQuestionRKnowledge knowledge : konwledges) {
					String konwledgeName= baseKnowledgeDao.selecKnowLedgeNameById(knowledge.getBaseEndKnowledgeId());
					endKonwledges+=konwledgeName+",";
				}
				endKonwledges=endKonwledges.substring(0,endKonwledges.length()-1);
			}
			dto.setEndKonwledges(endKonwledges); 
		}
		return dto;
	}
	
	/**
	 * @author renhengli
	 * 编辑真题试卷习题
	 * @param question
	 * @param queQuestList
	 * @param knowList
	 * @param questionMotherId
	 */
	public void updateRealQuestion(ExamQuestion question, List<ExamQuestionRChapter> queQuestList, List<ExamQuestionRKnowledge> knowList,
			String examQuestionId) {

		// 1.修改习题的基本内容
		examQuestionMapper.updateByPrimaryKeySelective(question);
		// 2.删除本习题母题(母题或自己)的所有章节关系
		examQuestionRChapterMapper.deleteExamQuesChapterByExamQuestionId(examQuestionId);
		examQuestionRKnowledgeMapper.deleteKnowListByExamQuestionId(examQuestionId);

		// 2.插入习题与章节的关系
		if (null != queQuestList && queQuestList.size() > 0) {
			for (ExamQuestionRChapter examQuestionRChapter : queQuestList) {
				examQuestionRChapterMapper.insert(examQuestionRChapter);
			}
		}
		// 3.插入习题与知识点的关系
		if (null != knowList && knowList.size() > 0) {
			for (ExamQuestionRKnowledge examQuestionRKnowledge : knowList) {
				examQuestionRKnowledgeMapper.insert(examQuestionRKnowledge);
			}
		}

		// 修改填空题
		List<ExamQueFillInAnswer> queFillList = question.getFillInAnswers();
		if (null != queFillList && queFillList.size() > 0) {
			// 1.清空对应题目的所有填空题答案
			examQueFillInAnswerMapper.deleteFillInAnswerByExamQuestionId(examQuestionId);
			// 2.将修改后的填空题答案进行添加
			for (ExamQueFillInAnswer queFill : queFillList) {
				queFill.setExamQuestionId(question.getExamQuestionId());
				examQueFillInAnswerMapper.insert(queFill);
			}
		}
	}
	
	/**
	 * 保存真题试卷以及真题习题列表(编辑和新建保存公用)
	 * @author renhengli
	 * @param exam
	 * @param examQueList
	 * @throws Exception 
	 */
	public void updateExamExam(ExamExam exam, String examQueList, String schoolId, String areaId, String saveOrEditType, String userType)
			throws Exception {
		// 首先更新真题试卷
		ExamExam examEdit = examExamMapper.selectByPrimaryKey(exam.getExamId());// 获取原来的试卷信息
		if(examEdit == null){
			throw new Exception("更新真题试卷失败！");
		}
		if ("save".equals(saveOrEditType)) {// 新建真题试卷保存
			exam.setUseCount(0);
			exam.setCreateTime(new Date());
		} else {// 编辑真题试卷保存
			exam.setUseCount(examEdit.getUseCount() == null ? 0 : examEdit.getUseCount());
			exam.setCreateTime(examEdit.getCreateTime());
		}
		exam.setIsDelete(CommonsConstant.FLAG_NO);// 此字段暂无卵用（冗余）
		exam.setPublicFlag((exam.getPublicFlag() == "" || exam.getPublicFlag() == null) ? CommonsConstant.FLAG_YES : CommonsConstant.FLAG_NO);
		exam.setUpdateTime(new Date());
		exam.setExamCategoryType("REAL");
		// 更新examExam
		examExamMapper.updateByPrimaryKeySelective(exam);
		// 更新所有习题
		String[] examQueArray = examQueList.split(";");
		List<String> questionIds = new ArrayList<String>();
		for (int i = 0; i < examQueArray.length; i++) {
			String queQuestionId = UUIDUtils.getUUID();
			String examQuestionId = examQueArray[i].split(",")[0];
			String score = examQueArray[i].split(",")[1];
			ExamQuestion dto = examQuestionMapper.selectByExamQuestionId(examQuestionId);
			dto.setScore(Integer.parseInt(score));
			dto.setSort(i + 1);
			dto.setTempoparyFlag(CommonsConstant.FLAG_NO);
			dto.setUpdateTime(new Date());
			dto.setQueQuestionId((dto.getQueQuestionId() == null || "".equals(dto.getQueQuestionId())) ? queQuestionId : dto.getQueQuestionId());

			QueQuestion question = new QueQuestion();
			if ("edit".equals(saveOrEditType)) {// 编辑真题试卷保存，如果试题存在则更新，否则新增queQuestion
				questionIds.add(examQuestionId);//把所有习题的Id封装起来用于删除其他移出的习题（编辑习题时使用）
				question = queQuestionMapper.selectByPrimaryKey(dto.getQueQuestionId());
				if (question == null) {
					QueQuestion questionQuestion = new QueQuestion();
					BeanUtils.copyProperties(questionQuestion, dto);
					questionQuestion.setUsableType(exam.getPublicFlag().equals(CommonsConstant.FLAG_YES) ? "PUBLIC" : "SCHOOL");
					questionQuestion.setUseCount(0);
					questionQuestion.setClsSchoolId(schoolId);
					questionQuestion.setAreaId(areaId);
					questionQuestion.setQueQuestionId(queQuestionId);
					questionQuestion.setCreateTime(new Date());
					questionQuestion.setUpdateTime(new Date());
					if("TEACHER".equals(userType)){
						questionQuestion.setAuditStatus("INIT");
					}else{
						questionQuestion.setAuditStatus("PASSED"); 
					}
					queQuestionMapper.addQuestionLib(questionQuestion);
				} else {
					BeanUtils.copyProperties(question, dto);
					question.setUpdateTime(new Date());
					queQuestionMapper.updateByPrimaryKeySelective(question);
					// 先清除对应的章节和知识点关联
					queQuestionRChapterMapper.deleteQuesChapterByMotherId(question.getQueQuestionId());
					queQuestionRKnowledgeMapper.deleteKnowListByMotherId(question.getQueQuestionId());
				}
				
			} else {// 新建真题试卷保存，直接新增试题到queQuestion
				BeanUtils.copyProperties(question, dto);
				question.setUsableType(exam.getPublicFlag().equals(CommonsConstant.FLAG_YES) ? "PUBLIC" : "SCHOOL");
				question.setUseCount(0);
				question.setClsSchoolId(schoolId);
				question.setAreaId(areaId);
				question.setQueQuestionId(queQuestionId);
				question.setCreateTime(new Date());
				question.setUpdateTime(new Date());
				if("TEACHER".equals(userType)){
					question.setAuditStatus("INIT");
				}else{
					question.setAuditStatus("PASSED"); 
				}
				queQuestionMapper.addQuestionLib(question);
			}
			// 将examQuestion对应的关联章节和知识点，以及填空题daan也拷贝给对应的queQuestion
			copyChapterAndKnowledge(dto.getExamQuestionId(), dto.getQueQuestionId(),dto.getType(), saveOrEditType);
			// 需最后执行
			examQuestionMapper.updateByPrimaryKeySelective(dto);
		}
		

		if ("edit".equals(saveOrEditType)) {
			// 编辑习题时最后需要删除和试卷无关的习题
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("examId", exam.getExamId());
			map.put("questionIds", questionIds);
			examQuestionMapper.deleteByExamIdAndExamQuestionId(map);
		}
	}
	
	/**
	 * @author renhengli
	 * 将examQuestion对应的关联章节和知识点也拷贝给对应的queQuestion
	 * @param examQuestionId
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void copyChapterAndKnowledge(String examQuestionId, String queQuestionId, String type, String saveOrEditType) throws IllegalAccessException,
			InvocationTargetException {
		// 将examQuestion对应的关联章节和知识点也拷贝给对应的queQuestion
		List<ExamQuestionRChapter> list = examQuestionRChapterMapper.selectByExamQuestionId(examQuestionId);
		if (list.size() > 0) {
			for (ExamQuestionRChapter examQuestionRChapter : list) {
				String queQuestionRChapterId = UUIDUtils.getUUID();
				QueQuestionRChapter queQuestionRChapter = new QueQuestionRChapter();
				BeanUtils.copyProperties(queQuestionRChapter, examQuestionRChapter);
				queQuestionRChapter.setQueQuestionRChapterId(queQuestionRChapterId);
				queQuestionRChapter.setQueQuestionId(queQuestionId);
				queQuestionRChapterMapper.insertSelective(queQuestionRChapter);
			}

			// 拷贝知识点
			List<ExamQuestionRKnowledge> list_1 = examQuestionRKnowledgeMapper.selectKonwledgesByExamQuestionId(examQuestionId);
			for (ExamQuestionRKnowledge examQuestionRKnowledge : list_1) {
				String queQuestionRKnowledgeId = UUIDUtils.getUUID();
				QueQuestionRKnowledge queQuestionRKnowledge = new QueQuestionRKnowledge();
				BeanUtils.copyProperties(queQuestionRKnowledge, examQuestionRKnowledge);
				queQuestionRKnowledge.setQueQuestionRKnowledgeId(queQuestionRKnowledgeId);
				queQuestionRKnowledge.setQueQuestionId(queQuestionId);
				queQuestionRKnowledgeMapper.insertSelective(queQuestionRKnowledge);
			}
		}
		
		//如果是填空题还需要将对应的填空题答案拷贝给queFillInAnswer
		if("FILL_IN_BLANK".equals(type)){
			List<ExamQueFillInAnswer> list_exam = new ArrayList<ExamQueFillInAnswer>();
			list_exam = examQueFillInAnswerMapper.getQueFillInAnswerByQuestionId(examQuestionId);
			if("edit".equals(saveOrEditType)){//如果是编辑试卷保存，先删除原来的填空题答案，重新拷贝
				queFillInAnswerMapper.deleFillInAnswerById(queQuestionId);
			}
			for (ExamQueFillInAnswer examQueFillInAnswer : list_exam){
				String Id = UUIDUtils.getUUID();
				QueFillInAnswer queFillInAnswer = new QueFillInAnswer();
				BeanUtils.copyProperties(queFillInAnswer, examQueFillInAnswer);
				queFillInAnswer.setQueQuestionId(queQuestionId); 
				queFillInAnswer.setQueFillInAnswerId(Id); 
				queFillInAnswerMapper.insertSelective(queFillInAnswer);
			}
		}
	}
	
	/**
	 * 通过word上传试卷
	 * @return
	 */
	public Map<String,Object> uploadExam(ExamInfo examinfo, List<ErrorInfo> errorList,String userId,String schoolId,String areaId,String userType,String type ){
		Map<String,Object> result = new HashMap<String, Object>();
		
		
		
		//上传试题
		Map<String,Object> questionresult = questionService.uploadQuestion(examinfo.getQuestionList(), errorList, userId,schoolId,areaId,userType,true);
		
		if(!questionService.getFlag(errorList)){
			  result.put("flag", false);
			  result.put("errormeg", errorList);
			  result.put("resultmsg", "导入试题时出错");
		}else{
			//保存试卷
			ExamFormFields ef = turntoExamFormFields((List<QueQuestion>)questionresult.get("questions"), errorList,examinfo);
			if(!questionService.getFlag(errorList)){
				 result.put("flag", false);
				 result.put("errormeg", errorList);
				 result.put("resultmsg", "导入试卷出错");
			}else{
				try{
					createExam(ef,userId,type);
					result.put("flag", true);
					//result.put("errormeg", errorList);
					result.put("resultmsg", "导入试卷成功");
				}catch(Exception e){
					e.printStackTrace();
					result.put("flag", false);
					errorList.get(0).addErrorInfo("导入试卷出错");
					result.put("errormeg", errorList);
					result.put("resultmsg", "导入试卷出错");
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 转换为保存试卷所需的数据格式
	 * @param qs
	 * @param errorList
	 * @param examInfo
	 * @return
	 */
	private ExamFormFields turntoExamFormFields(List<QueQuestion> qs,List<ErrorInfo> errorList,ExamInfo examInfo){
		ExamFormFields ef = new ExamFormFields();
		String[] areanames ={"河北省","山西省","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省","福建省","江西","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古","广西","西藏","宁夏","新疆"};
		SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
		
		List<String> scdids = baseSemesterDao.getscdIds(examInfo.getSemesterName(),examInfo.getClasslevelName(),examInfo.getSubjectName());
		if(scdids == null || scdids.size()<3){
			 errorList.get(0).addErrorInfo("请检查试卷学段年级学科");
		}else{
			ef.setBaseSemesterId(scdids.get(0));
			ef.setBaseClasslevelId(scdids.get(1));
			ef.setBaseSubjectId(scdids.get(2));
		}
		String typeid = examTaskService.getExamTypeIdByName(examInfo.getExamType());
		if(StringUtils.isNotEmpty(typeid)){
			ef.setExamtype(typeid);
		}else{
			errorList.get(0).addErrorInfo("请检查试卷类型");
		}
		ef.setAnswerTime(NumberUtils.createInteger(examInfo.getResponseTime()));
		ef.setExamTitle(examInfo.getExamName());
		ef.setScoreInput(NumberUtils.createInteger(examInfo.getTotalScore()));
		
		if(ArrayUtils.lastIndexOf(areanames, examInfo.getAreaName()) == -1){
			errorList.get(0).addErrorInfo("请检查试卷区域名称");
		}else{
			ef.setAreaName(examInfo.getAreaName());
		}
		if(NumberUtils.isNumber(examInfo.getYear()) && NumberUtils.createInteger(examInfo.getYear()) <= NumberUtils.createInteger(df.format(new Date()))&& NumberUtils.createInteger(examInfo.getYear()) >= (NumberUtils.createInteger(df.format(new Date()))-10)){
			ef.setYear(examInfo.getYear());
		}else{
			ef.setYear("其他");
		}
		
		List<Object[]> idandscore = getQuestionIdAndSrore(qs); 
		
		ef.setQuestionIds((String[])idandscore.get(0));
		ef.setScore((Integer[])idandscore.get(1));
		
		return ef;
	}
	
	
	/**
	 * 将题目排序并获取id和分数
	 * @param qs
	 * @return
	 */
	private List<Object[]> getQuestionIdAndSrore(List<QueQuestion> qs){
		List<Object[]> result = new ArrayList<Object[]>();
		
		List<String> type1id = new ArrayList<String>();//单选题ID
		List<String> type2id = new ArrayList<String>();//多选题ID
		List<String> type3id = new ArrayList<String>();//判断题ID
		List<String> type4id = new ArrayList<String>();//解答题ID
		List<String> type5id = new ArrayList<String>();//计算题ID
		
		List<Integer> type1score = new ArrayList<Integer>();//单选题分数
		List<Integer> type2score = new ArrayList<Integer>();//多选题分数
		List<Integer> type3score = new ArrayList<Integer>();//判断题分数
		List<Integer> type4score = new ArrayList<Integer>();//解答题分数
		List<Integer> type5score = new ArrayList<Integer>();//计算题分数
		
		List<String> ids = new ArrayList<String>();//总id
		List<Integer> scores = new ArrayList<Integer>();//总分数
		
		for (QueQuestion q : qs) {
			//如果是单选题
			if(QuestionListCriteria.QuestionType.single_option.getValue().equals(q.getType())){
				type1id.add(q.getQueQuestionId());
				type1score.add(q.getScore());
			}
			//如果是多选题
			if(QuestionListCriteria.QuestionType.multple_option.getValue().equals(q.getType())){
				type2id.add(q.getQueQuestionId());
				type2score.add(q.getScore());
			}
			//如果是判断
			if(QuestionListCriteria.QuestionType.judement.getValue().equals(q.getType())){
				type3id.add(q.getQueQuestionId());
				type3score.add(q.getScore());
			}
			//如果是问答
			if(QuestionListCriteria.QuestionType.resolve.getValue().equals(q.getType())){
				type4id.add(q.getQueQuestionId());
				type4score.add(q.getScore());
			}
			//如果是计算
			if(QuestionListCriteria.QuestionType.computing.getValue().equals(q.getType())){
				type5id.add(q.getQueQuestionId());
				type5score.add(q.getScore());
			}
		}
		
		ids.addAll(type1id);
		ids.addAll(type2id);
		ids.addAll(type3id);
		ids.addAll(type4id);
		ids.addAll(type5id);
		
		scores.addAll(type1score);
		scores.addAll(type2score);
		scores.addAll(type3score);
		scores.addAll(type4score);
		scores.addAll(type5score);
		
		final int idsize = ids.size();
		result.add((String[])ids.toArray(new String[idsize]));
		
		final int scoresize = scores.size();
		result.add((Integer[])scores.toArray(new Integer[scoresize]));
		
		return result;
	}
}
