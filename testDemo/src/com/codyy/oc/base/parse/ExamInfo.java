package com.codyy.oc.base.parse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xierongbing
 * @date 2015年8月21日 上午8:35:47 
 * @description 
 * 试卷的相关信息
 */

public class ExamInfo {
	
	private String examName;
	
	private String semesterName;//学段
	
	private String subjectName;//学科
	
	private String classlevelName;//年级ID
	
	private String examType;
	
	private String responseTime;
	
	private String totalScore;
	
	private String areaName;//真题试卷:地区
	
	private String year;//真题试卷:年份
	
	private List<QuestionInfo> questionList = new ArrayList<QuestionInfo>();

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

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public List<QuestionInfo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionInfo> questionList) {
		this.questionList = questionList;
	}

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

	
}
