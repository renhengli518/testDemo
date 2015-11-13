package com.codyy.oc.onlinetest.entity;

import java.util.Date;

public class ExamResult{
	
    public static final String INIT ="INIT";//未提交
	
	public static final String SUBMITTED ="SUBMITTED";//已提交
	
	public static final String CHECKED ="CHECKED";//已批阅
	
	
    private String examResultId;

    private String examTaskId;

    private String baseUserId;

    private String status;

    private String score;

    private String questionCount;//题目数量

    private String answerCount;//答题数量

    private Date commitTime;//提交时间
    
    private String isDelay;
    
    private String createTime;
    
    private String baseUserName;
    
    private String baseClasslevelId; //年级Id
    
    private String classlevelName;   //年级名称
    
    private String baseClassId;    //班级id
    
    private String className;     //班级名称
    
    private String rightCount;   //答对正确个数
    
    private String total;     //总题数
    
    private String submitCnt;  //已提交的人数
    
    private String readOverCnt;  //已批阅的人数

	public String getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(String examResultId) {
		this.examResultId = examResultId;
	}

	public String getExamTaskId() {
		return examTaskId;
	}

	public void setExamTaskId(String examTaskId) {
		this.examTaskId = examTaskId;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}

	public String getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(String answerCount) {
		this.answerCount = answerCount;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBaseUserName() {
		return baseUserName;
	}

	public void setBaseUserName(String baseUserName) {
		this.baseUserName = baseUserName;
	}

	public String getBaseClasslevelId() {
		return baseClasslevelId;
	}

	public void setBaseClasslevelId(String baseClasslevelId) {
		this.baseClasslevelId = baseClasslevelId;
	}

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

	public String getBaseClassId() {
		return baseClassId;
	}

	public void setBaseClassId(String baseClassId) {
		this.baseClassId = baseClassId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRightCount() {
		return rightCount;
	}

	public void setRightCount(String rightCount) {
		this.rightCount = rightCount;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSubmitCnt() {
		return submitCnt;
	}

	public void setSubmitCnt(String submitCnt) {
		this.submitCnt = submitCnt;
	}

	public String getReadOverCnt() {
		return readOverCnt;
	}

	public void setReadOverCnt(String readOverCnt) {
		this.readOverCnt = readOverCnt;
	}
}