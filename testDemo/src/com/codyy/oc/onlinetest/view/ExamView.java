package com.codyy.oc.onlinetest.view;

import com.codyy.oc.onlinetest.entity.ExamExam;



public class ExamView extends ExamExam{

	private String semesterName;	  //学段名
	
	private String subjectName;       //学科名
	
	private String classlevelName;    //年级名
	 
	private String examTypeName;      //试卷类型   期中   期末  月考  统考
	
	private String startnum;		 //已开始任务数为0时可以删除
	
	private Integer questionCount;// 题目数量

	private String	realExamId;		//被复制的真题试卷ID
	
	public String getRealExamId() {
		return realExamId;
	}

	public void setRealExamId(String realExamId) {
		this.realExamId = realExamId;
	}

	public String getStartnum() {
		return startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
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

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

	public String getExamTypeName() {
		return examTypeName;
	}

	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
	
	

}
