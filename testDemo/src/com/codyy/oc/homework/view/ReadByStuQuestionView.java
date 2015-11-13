package com.codyy.oc.homework.view;

import java.util.List;

import com.codyy.commons.CommonsConstant;
import com.codyy.oc.homework.entity.WorkQueFillInAnswer;

public class ReadByStuQuestionView {
	
	private String workQuestionId;
	private String content;
	private String contentVideo;
	private String answer;
	private String difficulty;
	private String type;
	private String options;
	private String fillInAnswerType;
	private String answerVideo;
	private String myAnswer;
	private String correctFlag;
	private String workRecStuQueAnswerId;
	@SuppressWarnings("unused")
	private String difficultyName;
	private String[] answers;
	
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	private List<WorkQueFillInAnswer> fillInAnswers;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentVideo() {
		return contentVideo;
	}
	public void setContentVideo(String contentVideo) {
		this.contentVideo = contentVideo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getFillInAnswerType() {
		return fillInAnswerType;
	}
	public void setFillInAnswerType(String fillInAnswerType) {
		this.fillInAnswerType = fillInAnswerType;
	}
	public String getAnswerVideo() {
		return answerVideo;
	}
	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}
	public String getMyAnswer() {
		return myAnswer;
	}
	public void setMyAnswer(String myAnswer) {
		this.setAnswers(myAnswer.split(CommonsConstant.QUESTION_SEPERATE));
		this.myAnswer = myAnswer;
	}
	public String getCorrectFlag() {
		return correctFlag;
	}
	public void setCorrectFlag(String correctFlag) {
		this.correctFlag = correctFlag;
	}
	public String getWorkRecStuQueAnswerId() {
		return workRecStuQueAnswerId;
	}
	public void setWorkRecStuQueAnswerId(String workRecStuQueAnswerId) {
		this.workRecStuQueAnswerId = workRecStuQueAnswerId;
	}
	public String getDifficultyName() {
		return CommonsConstant.QUESION_DIFFCULTY_MAP.get(difficulty);
	}
	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}
	public String getWorkQuestionId() {
		return workQuestionId;
	}
	public void setWorkQuestionId(String workQuestionId) {
		this.workQuestionId = workQuestionId;
	}
	public List<WorkQueFillInAnswer> getFillInAnswers() {
		return fillInAnswers;
	}
	public void setFillInAnswers(List<WorkQueFillInAnswer> fillInAnswers) {
		this.fillInAnswers = fillInAnswers;
	}

}
