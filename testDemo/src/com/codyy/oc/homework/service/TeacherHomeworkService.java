package com.codyy.oc.homework.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.page.Page;
import com.codyy.commons.utils.ResultJson;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.base.view.StudentClassView;
import com.codyy.oc.homework.dao.WorkCommentTemplateMapper;
import com.codyy.oc.homework.dao.WorkHomeworkMapper;
import com.codyy.oc.homework.dao.WorkRecStuQueAnswerMapper;
import com.codyy.oc.homework.dao.WorkReceiveStuMapper;
import com.codyy.oc.homework.entity.ReceiveStu;
import com.codyy.oc.homework.entity.StuComment;
import com.codyy.oc.homework.entity.WorkCommentTemplate;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkHomework;
import com.codyy.oc.homework.entity.WorkQueFillInAnswer;
import com.codyy.oc.homework.entity.WorkQuestion;
import com.codyy.oc.homework.entity.WorkQuestionRKnowledge;
import com.codyy.oc.homework.entity.WorkReadOverStu;
import com.codyy.oc.homework.entity.WorkRecStuQueAnswer;
import com.codyy.oc.homework.entity.WorkReceiveDoc;
import com.codyy.oc.homework.entity.WorkReceiveStu;
import com.codyy.oc.homework.entity.correctCount;
import com.codyy.oc.homework.view.ReadByQueAnswerView;
import com.codyy.oc.homework.view.ReadByQueQuestionView;
import com.codyy.oc.homework.view.ReadByQueStuView;
import com.codyy.oc.homework.view.ReadByStuQuestionView;
import com.codyy.oc.homework.view.ReadByStuResultView;
import com.codyy.oc.homework.view.WorkCountView;
import com.codyy.oc.questionlib.dao.QueFillInAnswerMapper;
import com.codyy.oc.questionlib.dao.QueQuestionMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRKnowledgeMapper;
import com.codyy.oc.questionlib.entity.QueFillInAnswer;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;

@Service
public class TeacherHomeworkService extends WorkHomeworkService {

	@Autowired
	private QueQuestionMapper queQuestionMapper;

	@Autowired
	private QueFillInAnswerMapper queFillInAnswerMapper;

	@Autowired
	private QueQuestionRKnowledgeMapper queKnowledgeMapper;
	
	@Autowired
	private WorkHomeworkMapper workHomeworkMapper;
	
	@Autowired
	private WorkCommentTemplateMapper workCommentTemplateMapper;
	
	@Autowired
	private WorkRecStuQueAnswerMapper workRecStuQueAnswerMapper;
	
	@Autowired
	private WorkReceiveStuMapper workReceiveStuMapper;
	/**
	 * 习题选择-获取习题
	 * 
	 * @param page
	 */
	public void getSelectQuestion(Page page) {
		page.setData(workQuestionMapper.selectWorkSearchQuestionPageList(page));
	}

	/**
	 * 已选习题
	 * 
	 * @param page
	 */
	public void getSelectedQuestion(Page page) {
		page.setData(workQuestionMapper.selectSecletedQuestionPageList(page));
	}

	/**
	 * 获取作业习题
	 * 
	 * @param homeworkId
	 * @return
	 */
	public String getHomeworkQuestionIds(String homeworkId) {
		List<String> list = workQuestionMapper.selectHomeworkQuestion(homeworkId);
		if (CollectionUtils.isNotEmpty(list)) {
			StringBuilder builder = new StringBuilder();
			for (String id : list) {
				builder.append("," + id);
			}
			if (builder.length() > 0) {
				return builder.deleteCharAt(0).toString();
			}
		}
		return "";
	}

	/**
	 * 布置作业
	 * 
	 * @param homework
	 */
	public void createHomework(WorkHomework homework, String questions, String receiveStus, String readStus, String fileStrs) {
		String id = UUIDUtils.getUUID();
		homework.setWorkHomeworkId(id);
		homeworkMapper.insertSelective(homework);
		ResultJson resultJson = saveHomeworkReceive(homework, receiveStus, readStus);
		if (!resultJson.isResult()) {
			throw new RuntimeException(resultJson.getMessage());
		}
		saveHomeworkFile(id, fileStrs);
		saveHomeworkQuestion(id, questions);
	}

	/**
	 * 编辑作业
	 * 
	 * @param homework
	 */
	public void editHomework(WorkHomework homework, String questions, String receiveStus, String readStus, String fileStrs) {
		String id = homework.getWorkHomeworkId();
		homeworkMapper.updateByPrimaryKeyWithBLOBs(homework);

		receiveStuMapper.deleteByHomework(id);
		overStuMapper.deleteByHomework(id);
		docMapper.deleteByHomework(id);
		workQuestionMapper.deleteByHomework(id);
		ResultJson resultJson = saveHomeworkReceive(homework, receiveStus, readStus);
		if (!resultJson.isResult()) {
			throw new RuntimeException(resultJson.getMessage());
		}
		saveHomeworkFile(id, fileStrs);
		saveHomeworkQuestion(id, questions);
	}

	/**
	 * 保存作业学生
	 * 
	 * @param homework
	 * @param receiveStus
	 * @param readStus
	 */
	public ResultJson saveHomeworkReceive(WorkHomework homework, String receiveStus, String readStus) {
		List<WorkReceiveStu> stus = new ArrayList<WorkReceiveStu>();
		String homeworkId = homework.getWorkHomeworkId();
		String userId = homework.getBaseUserId();
		boolean teacherRead = CommonsConstant.HOMEWORK_READ_OVER_TYPE_TEACHER.equals(homework.getReadOverType());
		Map<String, Map<String, String>> classInfoMap = new HashMap<String, Map<String,String>>();
		if (CommonsConstant.HOMEWORK_ASSIGN_TYPE_CUSTOM.equals(homework.getAssignType())) {
			if (StringUtils.isBlank(receiveStus)) {
				return new ResultJson(false, "所选学生为空！");
			}
			JSONArray array = JSONArray.fromObject(receiveStus);
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				WorkReceiveStu receiveStu = setReceiveStuData(jsonObject.getString("userId"), jsonObject.getString("classId"), i + 1, homeworkId, classInfoMap);
				if (teacherRead) {
					receiveStuMapper.insertSelective(receiveStu);
				}
				stus.add(receiveStu);
			}
		} else {
			List<StudentClassView> stuClassViews = commonsMapper.getStudentByTeacher(userId);
			if (stuClassViews != null && stuClassViews.size() > 0) {
				for (int i = 0; i < stuClassViews.size(); i++) {
					StudentClassView studentClassView = stuClassViews.get(i);
					WorkReceiveStu receiveStu = setReceiveStuData(studentClassView.getUserId(), studentClassView.getClassId(), i + 1, homeworkId, classInfoMap);
					if (teacherRead) {
						receiveStuMapper.insertSelective(receiveStu);
					}
					stus.add(receiveStu);
				}
			} else {
				return new ResultJson(false, "所带班级没有学生！");
			}
		}

		if (!teacherRead) {
			WorkReceiveStu.randomSort(stus);
			int stuNum = stus.size();
			if (CommonsConstant.HOMEWORK_READ_OVER_TYPE_STU_EACH_OTHER.equals(homework.getReadOverType())) {
				if (stuNum == 1) {
					return new ResultJson(false, "所带班级只有一个学生，无法进行学生互评！");
				}
				for (int i = 0; i < stuNum; i++) {
					WorkReceiveStu each = stus.get(i);
					if (i == stus.size() - 1) {
						each.setReadOverStuId(stus.get(0).getBaseUserId());
					} else {
						each.setReadOverStuId(stus.get(i + 1).getBaseUserId());
					}
					receiveStuMapper.insertSelective(each);
				}
			} else {
				String[] reads = readStus.split(",");
				int readNum = reads.length;
				int eachReadNum = stuNum / readNum;
				Map<Integer, Integer> exchangeMap = new HashMap<Integer, Integer>();
				for (int i = 0; i < stuNum; i++) {
					WorkReceiveStu specified = stus.get(i);
					int index = i / eachReadNum;
					if (index >= readNum) {
						index = i - readNum * eachReadNum;
					}
					if (readNum == 1 && reads[0].equals(specified.getBaseUserId())) {
						return new ResultJson(false, "学生不能批阅自己的作业！");
					}
					if (exchangeMap.containsKey(index)) {
						specified.setReadOverStuId(reads[exchangeMap.get(index)]);
						exchangeMap.remove(index);
					} else {
						if (!reads[index].equals(specified.getBaseUserId())) {
							specified.setReadOverStuId(reads[index]);
						} else {
							int exchangeIndex = (index == readNum - 1) ? 0 : (index + 1);
							specified.setReadOverStuId(reads[exchangeIndex]);
							exchangeMap.put(exchangeIndex, index);
						}
					}
					receiveStuMapper.insertSelective(specified);
				}
				for (int i = 0; i < readNum; i++) {
					WorkReadOverStu readOverStu = new WorkReadOverStu();
					readOverStu.setBaseUserId(reads[i]);
					readOverStu.setWorkHomeworkId(homeworkId);
					readOverStu.setWorkReadOverStuId(UUIDUtils.getUUID());
					readOverStu.setSort(i + 1);
					overStuMapper.insertSelective(readOverStu);
				}
			}
		}

		return new ResultJson(true);
	}
	
	public WorkReceiveStu setReceiveStuData(String userId, String classId, int sort, String homeworkId, Map<String, Map<String, String>> classInfoMap) {
		WorkReceiveStu receiveStu = new WorkReceiveStu();
		receiveStu.setBaseClassId(classId);
		receiveStu.setBaseUserId(userId);
		receiveStu.setSort(sort);
		receiveStu.setStatus(CommonsConstant.HOMEWORK_RECEIVE_STU_STATUS_PROGRESS);
		receiveStu.setWorkHomeworkId(homeworkId);
		receiveStu.setWorkReceiveStuId(UUIDUtils.getUUID());
		receiveStu.setDocQueReadOverFlag(CommonsConstant.FLAG_NO);
		receiveStu.setTextQueReadOverFlag(CommonsConstant.FLAG_NO);
		Map<String, String> map = null;
		if(classInfoMap.containsKey(classId)){
			map = classInfoMap.get(classId);
		}else{
			map = homeworkMapper.selectClassInfoByClassId(classId);
			classInfoMap.put(classId, map);
		}
		receiveStu.setClasslevelName(map.get("CLASSLEVELNAME"));
		receiveStu.setBaseClasslevelId(map.get("CLASSLEVELID"));
		receiveStu.setBaseClassName(map.get("CLASSNAME"));
		return receiveStu;
	}

	/**
	 * 保存作业附件
	 * 
	 * @param homeworkId
	 * @param fileStrs
	 */
	public void saveHomeworkFile(String homeworkId, String fileStrs) {
		if (StringUtils.isNotBlank(fileStrs)) {
			JSONArray array = JSONArray.fromObject(fileStrs);
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				WorkDoc workDoc = new WorkDoc();
				workDoc.setWorkHomeworkId(homeworkId);
				workDoc.setDocName(jsonObject.getString("name"));
				workDoc.setDocPath(jsonObject.getString("path"));
				workDoc.setDocSize(jsonObject.getInt("size"));
				workDoc.setCreateTime(new Date());
				workDoc.setWorkDocId(UUIDUtils.getUUID());
				docMapper.insertSelective(workDoc);
			}
		}
	}

	/**
	 * 保存作业习题
	 * 
	 * @param homeworkId
	 * @param questions
	 */
	public void saveHomeworkQuestion(String homeworkId, String questions) {
		if (StringUtils.isNotBlank(questions)) {
			String[] queIds = questions.split(",");
			List<QueQuestion> queQuestions = queQuestionMapper.selectByIds(queIds);
			int i = 1;
			for (QueQuestion queQuestion : queQuestions) {
				String queId = queQuestion.getQueQuestionId();
				WorkQuestion question = new WorkQuestion();
				String wqId = UUIDUtils.getUUID();
				question.setWorkHomeworkId(homeworkId);
				question.setBaseSemesterId(queQuestion.getBaseSemesterId());
				question.setBaseClasslevelId(queQuestion.getBaseClasslevelId());
				question.setBaseSubjectId(queQuestion.getBaseSubjectId());
				question.setBaseUserId(queQuestion.getBaseUserId());
				question.setContent(queQuestion.getContent());
				question.setContentVideo(queQuestion.getContentVideo());
				question.setOptions(queQuestion.getOptions());
				question.setAnswer(queQuestion.getAnswer());
				question.setDifficulty(queQuestion.getDifficulty());
				question.setType(queQuestion.getType());
				question.setResolve(queQuestion.getResolve());
				question.setResolveVideo(queQuestion.getResolveVideo());
				question.setFillInAnswerType(queQuestion.getFillInAnswerType());
				question.setFillInScoreType(queQuestion.getFillInScoreType());
				question.setOptionsTxt(queQuestion.getOptionsTxt());
				question.setRelationalIndicator(queQuestion.getRelationalIndicator());
				question.setStoreServer(queQuestion.getStoreServer());
				question.setUseCount(queQuestion.getUseCount());
				question.setUpdateTime(queQuestion.getUpdateTime());
				question.setQueQuestionId(queId);
				question.setMotherId(queQuestion.getMotherId());
				question.setSort(i + 1);
				question.setWorkQuestionId(wqId);
				i++;
				workQuestionMapper.insertSelective(question);
				if (CommonsConstant.QUESTION_TYPE_FILL_IN_BLANK.equals(question.getType())) {
					List<QueFillInAnswer> queFillInAnswers = queFillInAnswerMapper.selectByQuestion(queId);
					if (queFillInAnswers != null && queFillInAnswers.size() > 0) {
						for (QueFillInAnswer queFillInAnswer : queFillInAnswers) {
							WorkQueFillInAnswer fillInAnswer = new WorkQueFillInAnswer();
							fillInAnswer.setAnswerGrp1(queFillInAnswer.getAnswerGrp1());
							fillInAnswer.setAnswerGrp1Txt(queFillInAnswer.getAnswerGrp1Txt());
							fillInAnswer.setAnswerGrp2(queFillInAnswer.getAnswerGrp2());
							fillInAnswer.setAnswerGrp2Txt(queFillInAnswer.getAnswerGrp2Txt());
							fillInAnswer.setAnswerGrp3(queFillInAnswer.getAnswerGrp3());
							fillInAnswer.setAnswerGrp3Txt(queFillInAnswer.getAnswerGrp3Txt());
							fillInAnswer.setAnswerGrp4(queFillInAnswer.getAnswerGrp4());
							fillInAnswer.setAnswerGrp4Txt(queFillInAnswer.getAnswerGrp4Txt());
							fillInAnswer.setSort(queFillInAnswer.getSort());
							fillInAnswer.setWorkQuestionId(wqId);
							fillInAnswer.setWorkQueFillInAnswerId(UUIDUtils.getUUID());
							workQueFillInAnswerMapper.insertSelective(fillInAnswer);
						}
					}
				}
				List<QueQuestionRKnowledge> queKnowledges = queKnowledgeMapper.selectByQuestion(queId);
				if (queKnowledges != null && queKnowledges.size() > 0) {
					for (QueQuestionRKnowledge queKnowledge : queKnowledges) {
						WorkQuestionRKnowledge workKnowledge = new WorkQuestionRKnowledge();
						workKnowledge.setWorkQuestionId(wqId);
						workKnowledge.setWorkQuestionRKnowledgeId(UUIDUtils.getUUID());
						workKnowledge.setBaseKnowledgeId(queKnowledge.getBaseKnowledgeId());
						workKnowledge.setBaseSubKnowledge1Id(queKnowledge.getBaseSubKnowledge1Id());
						workKnowledge.setBaseSubKnowledge2Id(queKnowledge.getBaseSubKnowledge2Id());
						workKnowledge.setBaseSubKnowledge3Id(queKnowledge.getBaseSubKnowledge3Id());
						workKnowledge.setBaseSubKnowledge4Id(queKnowledge.getBaseSubKnowledge4Id());
						workKnowledge.setBaseSubKnowledge5Id(queKnowledge.getBaseSubKnowledge5Id());
						workKnowledge.setBaseEndKnowledgeId(queKnowledge.getBaseEndKnowledgeId());
						workKnowledgeMapper.insertSelective(workKnowledge);
					}
				}
			}
		}
	}

	/**
	 * 获取班级已提交学生人数
	 * 
	 * @param classId
	 * @param homeworkId
	 * @return
	 */
	public int getAnswerStudentNum(String classId, String homeworkId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		return receiveStuMapper.selectAnswerCountStudent(map);
	}

	/**
	 * 获取班级已批阅习题数
	 * 
	 * @param classId
	 * @param homework
	 * @return
	 */
	public int[] getQueUncheckedNum(String classId, WorkHomework homework) {
		int[] result = new int[2];
		String homeworkId = homework.getWorkHomeworkId();
		int totalNum = homework.getQuestionCount();
		int objectiveNum = workQuestionMapper.selectObjectiveCountByHomework(homeworkId);
		totalNum = totalNum - objectiveNum;
		result[0] = totalNum;
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		if (StringUtils.isNotBlank(homework.getTextQueContent())) {
			map.put("type", "text");
			int uncheckedTextNum = receiveStuMapper.selectTextAndFileUncheckedNum(map);
			if (uncheckedTextNum > 0) {
				totalNum = totalNum - 1;
			}
		}
		int fileCount = docMapper.selectCountByHomework(homeworkId);
		if (fileCount > 0) {
			map.put("type", "file");
			int uncheckedFileNum = receiveStuMapper.selectTextAndFileUncheckedNum(map);
			if (uncheckedFileNum > 0) {
				totalNum = totalNum - 1;
			}
		}
		int queCount = workQuestionMapper.selectCountByHomework(homeworkId);
		if (queCount > 0) {
			int uncheckedQueNum = workRecStuQueAnswerMapper.selectQueUncheckedNum(map);
			totalNum = totalNum - uncheckedQueNum;
		}
		result[1] = totalNum;
		return result;
	}

	/**
	 * 按习题批阅获取 批阅学生
	 * 
	 * @param classId
	 * @param homeworkId
	 * @return
	 */
	public List<ReadByQueStuView> getReadByQueStu(String classId, String homeworkId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		return receiveStuMapper.selectReadByquestionStudent(map);
	}

	/**
	 * 按习题批阅获取 习题信息
	 * 
	 * @param homework
	 * @return
	 */
	public ReadByQueQuestionView getReadByQueQuestion(WorkHomework homework, String classId) {
		String homeworkId = homework.getWorkHomeworkId();
		ReadByQueQuestionView view = new ReadByQueQuestionView();
		view.setWorkHomeworkId(homeworkId);
		view.setTextQueContent(homework.getTextQueContent());
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		List<WorkQuestion> questions = workQuestionMapper.selectReadByQueQuestionByHomework(map);
		List<WorkDoc> docs = docMapper.selectByHomework(homeworkId);
		view.setQuestions(questions);
		view.setDocs(docs);
		return view;
	}

	/**
	 * 获取习题回答信息
	 * 
	 * @param classId
	 * @param homeworkId
	 * @param workReceiveStuId
	 * @param workQueId
	 * @return
	 */
	public ReadByQueAnswerView getQuestionAnswer(String classId, String homeworkId, String workReceiveStuId, String workQueId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("workReceiveStuId", workReceiveStuId);
		map.put("workQueId", workQueId);
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		ReadByQueAnswerView view = workRecStuQueAnswerMapper.selectQuestionAnswer(map);
		int notReadNum = workRecStuQueAnswerMapper.selectQuestionNoReadNum(map);
		view.setUnReadNum(notReadNum);
		return view;
	}

	/**
	 * 获取文本作业回答信息
	 * 
	 * @param classId
	 * @param homeworkId
	 * @param workReceiveStuId
	 * @return
	 */
	public ReadByQueAnswerView getTextHomeWorkAnswer(String classId, String homeworkId, String workReceiveStuId) {
		ReadByQueAnswerView view = new ReadByQueAnswerView();
		WorkReceiveStu receiveStu = receiveStuMapper.selectByPrimaryKey(workReceiveStuId);
		view.setAnswer(receiveStu.getTextQueAnswer());
		view.setComment(receiveStu.getTextQueComment());
		view.setReadFlag(receiveStu.getTextQueReadOverFlag());
		view.setWorkReceiveStuId(workReceiveStuId);

		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		map.put("type", "text");
		int uncheckedTextNum = receiveStuMapper.selectTextAndFileUncheckedNum(map);
		view.setUnReadNum(uncheckedTextNum);
		return view;
	}

	/**
	 * 获取附件作业回答信息
	 * 
	 * @param classId
	 * @param homeworkId
	 * @param workReceiveStuId
	 * @return
	 */
	public ReadByQueAnswerView getFileHomeWorkAnswer(String classId, String homeworkId, String workReceiveStuId) {
		ReadByQueAnswerView view = new ReadByQueAnswerView();
		WorkReceiveStu receiveStu = receiveStuMapper.selectByPrimaryKey(workReceiveStuId);
		view.setComment(receiveStu.getDocQueComment());
		view.setReadFlag(receiveStu.getDocQueReadOverFlag());
		view.setWorkReceiveStuId(workReceiveStuId);
		List<WorkReceiveDoc> docs = receiveDocMapper.selectByReceiveStu(workReceiveStuId);
		view.setDocs(docs);
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		map.put("type", "file");
		int uncheckedTextNum = receiveStuMapper.selectTextAndFileUncheckedNum(map);
		view.setUnReadNum(uncheckedTextNum);
		return view;
	}

	/**
	 * 保存按题批阅
	 * 
	 * @param workRecStuQueAnswerId
	 * @param teacherComment
	 * @param workReceiveStuId
	 * @param type
	 */
	public void saveReadByQuestion(String workRecStuQueAnswerId, String teacherComment, String workReceiveStuId, String type) {
		if ("que".equals(type)) {
			WorkRecStuQueAnswer queAnswer = workRecStuQueAnswerMapper.selectByPrimaryKey(workRecStuQueAnswerId);
			if (queAnswer != null) {
				queAnswer.setComment(teacherComment);
				queAnswer.setReadOverFlag(CommonsConstant.FLAG_YES);
				workRecStuQueAnswerMapper.updateByPrimaryKeyWithBLOBs(queAnswer);
			}
		} else if ("text".equals(type)) {
			WorkReceiveStu receiveStu = receiveStuMapper.selectByPrimaryKey(workReceiveStuId);
			receiveStu.setTextQueComment(teacherComment);
			receiveStu.setTextQueReadOverFlag(CommonsConstant.FLAG_YES);
			receiveStuMapper.updateByPrimaryKeyWithBLOBs(receiveStu);
		} else if ("file".equals(type)) {
			WorkReceiveStu receiveStu = receiveStuMapper.selectByPrimaryKey(workReceiveStuId);
			receiveStu.setDocQueComment(teacherComment);
			receiveStu.setDocQueReadOverFlag(CommonsConstant.FLAG_YES);
			receiveStuMapper.updateByPrimaryKeyWithBLOBs(receiveStu);
		}
	}

	/**
	 * 获取按学生批阅信息
	 * 
	 * @param homework
	 * @param workReceiveStuId
	 * @return
	 */
	public ReadByStuResultView getReadByStudentInfo(WorkHomework homework, String workReceiveStuId) {
		String homeworkId = homework.getWorkHomeworkId();
		ReadByStuResultView view = receiveStuMapper.selectReadByStuInfo(workReceiveStuId);
		if (view != null) {
			view.setWorkTitle(homework.getWorkTitle());
			view.setTextQueContent(homework.getTextQueContent());
			if (CommonsConstant.FLAG_NO.equals(view.getDocQueReadOverFlag())) {
				List<WorkDoc> docs = docMapper.selectByHomework(homeworkId);
				if (CollectionUtils.isNotEmpty(docs)) {
					List<WorkReceiveDoc> receiveDocs = receiveDocMapper.selectByReceiveStu(workReceiveStuId);
					view.setDocs(docs);
					view.setReceiveDocs(receiveDocs);
				}
			}
			List<ReadByStuQuestionView> questions = workRecStuQueAnswerMapper.selectReadByStuQuestion(workReceiveStuId);
			if (CollectionUtils.isNotEmpty(questions)) {
				for (ReadByStuQuestionView questionView : questions) {
					if (CommonsConstant.QUESTION_TYPE_FILL_IN_BLANK.equals(questionView.getType())) {
						questionView.setFillInAnswers(workQueFillInAnswerMapper.selectByWorkQuestion(questionView.getWorkQuestionId()));
					}
				}
			}
			view.setQuestions(questions);
		}
		return view;
	}
/*
 * 统计
 * */
	public WorkCountView getWorkCountByWorkQueCount(String pid,String type,String workId,String classId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("workId", workId);
		map.put("classId", classId);
		map.put("pid", pid);
		map.put("type", type);
		WorkCountView workCountView = workHomeworkMapper.getWorkCountByworkQueCount(workId);
		String averageCorrect =""; //总平均正确率
		int average = 0;//
		int checkedCount =0;//批阅学生数
		List<Map<String,Object>> workCountList = workHomeworkMapper.getStuSubmitCountByStatus(map);
		List<correctCount> correctCountList = workHomeworkMapper.getCorrectCount(map);
		for(correctCount c:correctCountList){
			checkedCount = c.getCheckedStuCount();
			if(0!=checkedCount){
				average += (c.getRightStuCount());//将每一题的正确学生数相加
			}
		}
		if(checkedCount !=0){
			DecimalFormat decimalFormat=new DecimalFormat("#.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			
			if(average == 0){
				averageCorrect = "0.00%";
			}else{
				float t = (float)average/(float)checkedCount/(float)(workCountView.getObjectQueCount());
				averageCorrect=(decimalFormat.format(t*100))+"%";
			}
			
		}
		
		int stuAllCount = workHomeworkMapper.getStuAllCount(map);
		//Map<String,String> map =new HashMap<String,String>();
		for(Map<String,Object> m: workCountList){
			if("END".equals(m.get("STATUS"))){
				workCountView.setSubmitCount(Integer.parseInt(m.get("STUCOUNT").toString()));
			}
			if("PROGRESS".equals(m.get("STATUS"))){
				workCountView.setNotSubmitCount(Integer.parseInt(m.get("STUCOUNT").toString()));
			}
			if("CHECKED".equals(m.get("STATUS"))){
				workCountView.setReadOverCount(Integer.parseInt(m.get("STUCOUNT").toString()));
			}
		}
		//workCountView.setRecStuList(recStuList);
		workCountView.setStuAllCount(stuAllCount);
		workCountView.setCorrectCountList(correctCountList);
		workCountView.setAverageCorrect(averageCorrect);
		return workCountView;
	}
	
	public List<ReceiveStu> getReceiveStuInfo(String pid,String type,String workId,String classId){
		Map<String,String> map=new HashMap<String,String>();
		map.put("workId", workId);
		map.put("classId", classId);
		map.put("pid", pid);
		map.put("type", type);
		List<ReceiveStu> recStuList = workHomeworkMapper.getReceiveStuInfo(map);
		return recStuList;
	}
	public void addComment(String uuid,String userId,String comment,Date date){
		WorkCommentTemplate workCommentTemplate = new WorkCommentTemplate();
		workCommentTemplate.setWorkCommentTemplateId(uuid);
		workCommentTemplate.setBaseUserId(userId);
		workCommentTemplate.setCommentContent(comment);
		workCommentTemplate.setCreateTime(date);
		workCommentTemplateMapper.insert(workCommentTemplate);
	}
	public List<WorkCommentTemplate> findComments(String userId){
		List<WorkCommentTemplate> workCommentList = workCommentTemplateMapper.selectByUserId(userId);
		return workCommentList;
	}
	public void saveComments(StuComment stuComment){
		List<Map<String,String>> list = stuComment.getExercisesCommonets();
		
			for(int i=0;i<list.size();i++){
				Map<String,String> map = new HashMap<String,String>();
				map = list.get(i);
				workRecStuQueAnswerMapper.updateBystuIdAndQueId(map);
		}
		Map<String,String> textAndDocComment = new HashMap<String,String>();
		textAndDocComment.put("textComment", stuComment.getHomeworkTextCommonet());
		textAndDocComment.put("docComment", stuComment.getHomeworkAnnexCommonet());
		textAndDocComment.put("summary", stuComment.getHomeworkGeneralCommonet());
		textAndDocComment.put("receiveStuId", stuComment.getHomeworkReceiverId());
		receiveStuMapper.updateWorkReceiveStuByPrimaryKey(textAndDocComment);
		int totalStu = workReceiveStuMapper.selectStuReadCount(stuComment.getHomeWorkId());
		int readStu = workReceiveStuMapper.selectCheckedCount(stuComment.getHomeWorkId());
		if(totalStu == readStu){
			workHomeworkMapper.updateWorkStatusByWorkId(stuComment.getHomeWorkId());
		}
	}

}
