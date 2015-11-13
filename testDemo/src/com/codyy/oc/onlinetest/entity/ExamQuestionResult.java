package com.codyy.oc.onlinetest.entity;

public class ExamQuestionResult {

	public final static String RESULT_RIGHT = "1"; // 答案正确

	public final static String RESULT_WRONG = "0"; // 答案错误

	private String examQuestionResultId;

	private String examResultId;

	private String examQuestionId;

	private String result;

	private Integer sortNo;

	private String score;

	private String teacherComment;

	private String answer;

	private String answerVideo; // 回答音视频路径

	private String answerCount; // 回答数量

	private String optionAnswer; // 回答选项

	private String scoreCount; // 得分人数

	private Integer noReviewCount;// 统计当前剩余没批阅学生

	public Integer getNoReviewCount() {
		return noReviewCount;
	}

	public void setNoReviewCount(Integer noReviewCount) {
		this.noReviewCount = noReviewCount;
	}

	public String getExamQuestionResultId() {
		return examQuestionResultId;
	}

	public void setExamQuestionResultId(String examQuestionResultId) {
		this.examQuestionResultId = examQuestionResultId;
	}

	public String getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(String examResultId) {
		this.examResultId = examResultId;
	}

	public String getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(String examQuestionId) {
		this.examQuestionId = examQuestionId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTeacherComment() {
		return teacherComment;
	}

	public void setTeacherComment(String teacherComment) {
		this.teacherComment = teacherComment;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(String answerCount) {
		this.answerCount = answerCount;
	}

	public String getOptionAnswer() {
		return optionAnswer;
	}

	public void setOptionAnswer(String optionAnswer) {
		this.optionAnswer = optionAnswer;
	}

	public String getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(String scoreCount) {
		this.scoreCount = scoreCount;
	}

	public String getAnswerVideo() {
		return answerVideo;
	}

	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}

}