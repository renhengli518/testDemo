package com.codyy.oc.homework.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkHomework;
import com.codyy.oc.homework.entity.WorkQueFillInAnswer;
import com.codyy.oc.homework.entity.WorkQuestion;
import com.codyy.oc.homework.entity.WorkRecStuQueAnswer;
import com.codyy.oc.homework.entity.WorkReceiveDoc;
import com.codyy.oc.homework.entity.WorkReceiveStu;
import com.codyy.oc.homework.view.AnswerHomeworkView;

@Service
public class StudentHomeworkService extends WorkHomeworkService {

	/**
	 * 获取作业信息
	 * 
	 * @param homeworkId
	 * @return
	 */
	public AnswerHomeworkView getAnswerHomeworkInfo(String homeworkId) {
		WorkHomework homework = homeworkMapper.selectByPrimaryKey(homeworkId);
		if (homework != null) {
			AnswerHomeworkView view = new AnswerHomeworkView();
			view.setWorkHomeworkId(homework.getWorkHomeworkId());
			view.setTextQueContent(homework.getTextQueContent());
			view.setWorkTitle(homework.getWorkTitle());

			List<WorkQuestion> questions = workQuestionMapper.selectAnswerQuestionByHomework(homeworkId);
			List<WorkDoc> docs = docMapper.selectByHomework(homeworkId);
			view.setQuestions(questions);
			view.setDocs(docs);
			return view;
		}
		return null;
	}

	/**
	 * 保存学生回答
	 * 
	 * @param receiveStu
	 * @param questionResult
	 * @param txtResult
	 * @param fileResult
	 * @param answerCount
	 */
	public void answerHomework(WorkReceiveStu receiveStu, String questionResult, String txtResult, String fileResult, int answerCount) {
		receiveStu.setStatus(CommonsConstant.HOMEWORK_RECEIVE_STU_STATUS_END);
		receiveStu.setAnswerCount(answerCount);
		receiveStu.setSubmitTime(new Date());
		receiveStu.setTextQueAnswer(txtResult);

		String workReceiveStuId = receiveStu.getWorkReceiveStuId();
		if (StringUtils.isNotBlank(questionResult)) {
			answerHomeworkQuestion(questionResult, workReceiveStuId);
		}
		if (StringUtils.isNotBlank(fileResult)) {
			answerHomeworkFile(fileResult, workReceiveStuId);
		}
		
		receiveStuMapper.updateByPrimaryKey(receiveStu);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", CommonsConstant.HOMEWORK_STATUS_PROGRESS);
		map.put("workHomeworkId", receiveStu.getWorkHomeworkId());
		homeworkMapper.updateHomeworkStatus(map);
	}

	/**
	 * 保存附件回答
	 * 
	 * @param fileResult
	 * @param workReceiveStuId
	 */
	public void answerHomeworkFile(String fileResult, String workReceiveStuId) {
		JSONArray fileArray = JSONArray.fromObject(fileResult);
		if(CollectionUtils.isNotEmpty(fileArray)){
			for (int i = 0; i < fileArray.size(); i++) {
				JSONObject fileObj = fileArray.getJSONObject(i);
				WorkReceiveDoc receiveDoc = new WorkReceiveDoc();
				receiveDoc.setWorkDocId(UUIDUtils.getUUID());
				receiveDoc.setWorkReceiveStuId(workReceiveStuId);
				receiveDoc.setDocName(fileObj.getString("name"));
				receiveDoc.setDocPath(fileObj.getString("path"));
				receiveDoc.setDocSize(fileObj.getInt("size"));
				receiveDoc.setCreateTime(new Date());
				receiveDocMapper.insert(receiveDoc);
			}
		}
	}

	/**
	 * 保存习题回答
	 * 
	 * @param questionResult
	 * @param workReceiveStuId
	 */
	public void answerHomeworkQuestion(String questionResult, String workReceiveStuId) {
		JSONArray questionArray = JSONArray.fromObject(questionResult);
		if(CollectionUtils.isNotEmpty(questionArray)){
			List<WorkRecStuQueAnswer> queAnswers = new ArrayList<WorkRecStuQueAnswer>();
			for (int i = 0; i < questionArray.size(); i++) {
				WorkRecStuQueAnswer queAnswer = new WorkRecStuQueAnswer();
				JSONObject questionObj = questionArray.getJSONObject(i);
				String wQueId = questionObj.getString("wQueId");
				String answerResult = questionObj.getString("answer");
				String answerVideo = questionObj.getString("answerVideo");
				WorkQuestion workQuestion = workQuestionMapper.selectByPrimaryKey(wQueId);
				if (workQuestion != null) {
					if (CommonsConstant.isObjectiveQuestion(workQuestion.getType())) {
						if (CommonsConstant.QUESTION_TYPE_FILL_IN_BLANK.equals(workQuestion.getType())) {
							queAnswer.setCorrectFlag(checkFillinQuestion(workQuestion, answerResult) ? CommonsConstant.FLAG_YES
									: CommonsConstant.FLAG_NO);
						} else {
							queAnswer.setCorrectFlag(workQuestion.getAnswer().equals(answerResult) ? CommonsConstant.FLAG_YES
									: CommonsConstant.FLAG_NO);
						}
					}
					queAnswer.setWorkRecStuQueAnswerId(UUIDUtils.getUUID());
					queAnswer.setWorkReceiveStuId(workReceiveStuId);
					queAnswer.setWorkQuestionId(wQueId);
					queAnswer.setMyAnswer(answerResult);
					queAnswer.setAnswerVideo(answerVideo);
					queAnswer.setReadOverFlag(CommonsConstant.FLAG_NO);
					queAnswers.add(queAnswer);
				}
			}
			if (queAnswers != null && queAnswers.size() > 0) {
				workRecStuQueAnswerMapper.insertBatch(queAnswers);
			}
		}
	}

	/**
	 * 学生答题填空题答案比对
	 * 
	 * @param homeworkQuestion
	 * @param answer
	 * @return
	 */
	public boolean checkFillinQuestion(WorkQuestion workQuestion, String answer) {
		// 获取填空题所有答案
		List<WorkQueFillInAnswer> fillInAnswers = workQueFillInAnswerMapper.selectByWorkQuestion(workQuestion.getWorkQuestionId());
		Map<String, WorkQueFillInAnswer> eanswermap = new HashMap<String, WorkQueFillInAnswer>();
		for (WorkQueFillInAnswer ea : fillInAnswers) {
			eanswermap.put(ea.getSort(), ea);
		}
		// 拆分答案
		String[] myanswer = answer.split(CommonsConstant.QUESTION_SEPERATE);
		// 若没答题则返回错
		if (myanswer.length == 0) {
			return false;
		}

		if (CommonsConstant.QUESTION_FILL_IN_ANSWER_TYPE_INDEPENDENT.equals(workQuestion.getFillInAnswerType())) {
			// 如果是独立答案,只要有一空错就算错
			for (int i = 0; i < myanswer.length; i++) {
				if (!checkOneFill(eanswermap.get((i + 1) + ""), myanswer[i])) {
					return false;
				}
			}
			return true;
		} else if (CommonsConstant.QUESTION_FILL_IN_ANSWER_TYPE_COMBINATION.equals(workQuestion.getFillInAnswerType())) {
			// 如果是组合答案
			List<String> group1 = new ArrayList<String>();
			List<String> group2 = new ArrayList<String>();
			List<String> group3 = new ArrayList<String>();
			List<String> group4 = new ArrayList<String>();

			for (int i = 0; i < myanswer.length; i++) {
				group1.add(eanswermap.get((i + 1) + "").getAnswerGrp1());
				group2.add(eanswermap.get((i + 1) + "").getAnswerGrp2());
				group3.add(eanswermap.get((i + 1) + "").getAnswerGrp3());
				group4.add(eanswermap.get((i + 1) + "").getAnswerGrp4());
			}

			int[] myscore = new int[4];
			myscore[0] = checkOneFill(group1, myanswer);
			myscore[1] = checkOneFill(group2, myanswer);
			myscore[2] = checkOneFill(group3, myanswer);
			myscore[3] = checkOneFill(group4, myanswer);

			for (int i : myscore) {
				if (myscore[i] == myanswer.length && myanswer.length > 0) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	// 单个答案
	private boolean checkOneFill(WorkQueFillInAnswer answer, String myanswer) {
		if (StringUtils.equals(answerStr(answer.getAnswerGrp1()), answerStr(myanswer))) {
			return true;
		}
		if (StringUtils.equals(answerStr(answer.getAnswerGrp2()), answerStr(myanswer))) {
			return true;
		}
		if (StringUtils.equals(answerStr(answer.getAnswerGrp3()), answerStr(myanswer))) {
			return true;
		}
		if (StringUtils.equals(answerStr(answer.getAnswerGrp4()), answerStr(myanswer))) {
			return true;
		}
		return false;
	}

	// 组合答案
	private int checkOneFill(List<String> answers, String[] myanswers) {
		int score = 0;
		for (int i = 0; i < myanswers.length; i++) {
			if (StringUtils.equals(answerStr(answers.get(i)), answerStr(myanswers[i]))) {
				score += 1;
			}
		}
		return score;
	}

	// 处理填空题答案和回答的字符串
	private String answerStr(String str) {
		if (str == null) {
			return null;
		} else {
			str = str.trim().replaceAll("src=\".+?\"", "*").replace("/>", ">");
			return str;
		}
	}

	/**
	 * 根据用户和作业获取答题学生
	 * 
	 * @param userId
	 * @param homeworkId
	 * @return
	 */
	public WorkReceiveStu selectByUserAndHomework(String userId, String homeworkId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("baseUserId", userId);
		map.put("workHomeworkId", homeworkId);
		return receiveStuMapper.selectByUserAndHomework(map);
	}

}
