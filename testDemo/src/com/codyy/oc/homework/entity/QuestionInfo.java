package com.codyy.oc.homework.entity;

import java.util.List;

import com.codyy.commons.CommonsConstant;

public class QuestionInfo {
	private String homeWorkId;
	private String workQuestionId;//习题ID
	private String content;//习题题干                                           
	private String contentVideo;//习题题干视频
	private String options;//习题选项
	private String answer;//非填空题的答案
	private String type;//习题的类型
	private String resolve;//习题解析
	private String resolveVideo;//习题的解析视频
    private String difficulty;//习题的难易度
    private String fillInAnswerType;//填空題答案的類型
	private List<QuestionKnowLedge> questionKnowLedge; //习题的知识点
	private List<FillInQuesAnswer> fillInQuesAnswer;//填空题的答案
	private String workReceiveStuId;
	private String workRecStuQueAnswerId; 
	private String correctFlag;//是否答对
	private String myAnswer;//学生回答
	private String answerVideo;
	private String stuWorkStatus;//学生作业的状态
	private String comment;//习题评语
	private String readOverFlag;//是否批阅
	private String workQueId;
	private String[] answers;
	public String getAnswerVideo() {
		return answerVideo;
	}
	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}
	public String getWorkQueId() {
		return workQueId;
	}
	public void setWorkQueId(String workQueId) {
		this.workQueId = workQueId;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReadOverFlag() {
		return readOverFlag;
	}
	public void setReadOverFlag(String readOverFlag) {
		this.readOverFlag = readOverFlag;
	}

	public String getStuWorkStatus() {
		return stuWorkStatus;
	}
	public void setStuWorkStatus(String stuWorkStatus) {
		this.stuWorkStatus = stuWorkStatus;
	}
	//private String comment;
    public String getWorkReceiveStuId() {
		return workReceiveStuId;
	}
	public void setWorkReceiveStuId(String workReceiveStuId) {
		this.workReceiveStuId = workReceiveStuId;
	}
	public String getWorkRecStuQueAnswerId() {
		return workRecStuQueAnswerId;
	}
	public void setWorkRecStuQueAnswerId(String workRecStuQueAnswerId) {
		this.workRecStuQueAnswerId = workRecStuQueAnswerId;
	}
	public String getCorrectFlag() {
		return correctFlag;
	}
	public void setCorrectFlag(String correctFlag) {
		this.correctFlag = correctFlag;
	}
	public String getMyAnswer() {
		return myAnswer;
	}
	public void setMyAnswer(String myAnswer) {
		this.setAnswers(myAnswer.split(CommonsConstant.QUESTION_SEPERATE));
		this.myAnswer = myAnswer;
	}
//	public String getComment() {
//		return comment;
//	}
//	public void setComment(String comment) {
//		this.comment = comment;
//	}

	public String getHomeWorkId() {
		return homeWorkId;
	}
	public void setHomeWorkId(String homeWorkId) {
		this.homeWorkId = homeWorkId;
	}

	public String getFillInAnswerType() {
		return fillInAnswerType;
	}
	public void setFillInAnswerType(String fillInAnswerType) {
		this.fillInAnswerType = fillInAnswerType;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getWorkQuestionId() {
		return workQuestionId;
	}
	public void setWorkQuestionId(String workQuestionId) {
		this.workQuestionId = workQuestionId;
	}
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
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResolve() {
		return resolve;
	}
	public void setResolve(String resolve) {
		this.resolve = resolve;
	}
	public String getResolveVideo() {
		return resolveVideo;
	}
	public void setResolveVideo(String resolveVideo) {
		this.resolveVideo = resolveVideo;
	}

	public List<QuestionKnowLedge> getQuestionKnowLedge() {
		return questionKnowLedge;
	}
	public void setQuestionKnowLedge(List<QuestionKnowLedge> questionKnowLedge) {
		this.questionKnowLedge = questionKnowLedge;
	}
	public List<FillInQuesAnswer> getFillInQuesAnswer() {
		return fillInQuesAnswer;
	}
	public void setFillInQuesAnswer(List<FillInQuesAnswer> fillInQuesAnswer) {
		this.fillInQuesAnswer = fillInQuesAnswer;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
