package com.codyy.oc.onlinetest.view;

import com.codyy.oc.onlinetest.entity.ExamTask;



public class ExamTaskView extends ExamTask{

	private String examId;             //试卷ID
	
	private String subjectName;       //学科名
	
	private String classlevelName;    //年级名
	 
	private String examTypeName;      //试卷类型   期中   期末  月考  统考
	
	private String examCategoryType;  //试卷种类类型
	
	private String status;           //状态 :未开始      进行中    已结束
	
	private String classlevelId;     //年级ID
	
	private String delFlag;          //删除标识
	
	private String practiceStatus;   //巩固测试状态
	
	private String examResultId;     //测试结果ID
	
	private String classId;          //班级ID
	
	private String className;        //班级名 
	
	private String myScore;          //得分
	
	private String examPracticeId;   //巩固测试ID
	
	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExamCategoryType() {
		return examCategoryType;
	}

	public void setExamCategoryType(String examCategoryType) {
		this.examCategoryType = examCategoryType;
	}

	public String getClasslevelId() {
		return classlevelId;
	}

	public void setClasslevelId(String classlevelId) {
		this.classlevelId = classlevelId;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getPracticeStatus() {
		return practiceStatus;
	}

	public void setPracticeStatus(String practiceStatus) {
		this.practiceStatus = practiceStatus;
	}

	public String getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(String examResultId) {
		this.examResultId = examResultId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMyScore() {
		return myScore;
	}

	public void setMyScore(String myScore) {
		this.myScore = myScore;
	}

	public String getExamPracticeId() {
		return examPracticeId;
	}

	public void setExamPracticeId(String examPracticeId) {
		this.examPracticeId = examPracticeId;
	}
}
