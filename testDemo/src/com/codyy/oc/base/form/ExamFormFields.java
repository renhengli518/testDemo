package com.codyy.oc.base.form;

public class ExamFormFields {
	private String examId;//试卷ID
	private String examTitle;//试卷标题
    private String baseSemesterId;//学段ID
    private String baseSubjectId;//学科ID
    private String baseClasslevelId;//年级ID
	private String examtype;//考试类型
	private Integer answerTime;//考试时间
	private Integer scoreInput;//试卷总分
	private String[] questionIds;//试题id数组
	private Integer[] score;//分数数组
	private String areaName;//地区
	private String year;//年份
	private String[] delquestionIds;//删除试题id数组（编辑试卷）
	private String realExamId;//被复制的真题试卷ID
	
	public String getRealExamId() {
		return realExamId;
	}
	public void setRealExamId(String realExamId) {
		this.realExamId = realExamId;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String[] getDelquestionIds() {
		return delquestionIds;
	}
	public void setDelquestionIds(String[] delquestionIds) {
		this.delquestionIds = delquestionIds;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getExamTitle() {
		return examTitle;
	}
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
	public String getBaseSemesterId() {
		return baseSemesterId;
	}
	public void setBaseSemesterId(String baseSemesterId) {
		this.baseSemesterId = baseSemesterId;
	}
	public String getBaseSubjectId() {
		return baseSubjectId;
	}
	public void setBaseSubjectId(String baseSubjectId) {
		this.baseSubjectId = baseSubjectId;
	}
	public String getBaseClasslevelId() {
		return baseClasslevelId;
	}
	public void setBaseClasslevelId(String baseClasslevelId) {
		this.baseClasslevelId = baseClasslevelId;
	}
	public String getExamtype() {
		return examtype;
	}
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}
	public Integer getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}
	public Integer getScoreInput() {
		return scoreInput;
	}
	public void setScoreInput(Integer scoreInput) {
		this.scoreInput = scoreInput;
	}
	public String[] getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(String[] questionIds) {
		this.questionIds = questionIds;
	}
	public Integer[] getScore() {
		return score;
	}
	public void setScore(Integer[] score) {
		this.score = score;
	}
	
}
