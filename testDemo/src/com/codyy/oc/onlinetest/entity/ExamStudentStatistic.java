package com.codyy.oc.onlinetest.entity;

/**
 * 学生统计
 * @author zhangshuangquan
 *
 */
public class ExamStudentStatistic {
	
	private String examResultId;
	
	private String baseUserId;    
	
	private String baseUserName;  //姓名
	 
	private String score;     //得分

	private String answerCount;  //答题数
	
	private String totalCount;   //题目总数
	
	private String rightRate;   //正确率

	public String getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(String examResultId) {
		this.examResultId = examResultId;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getBaseUserName() {
		return baseUserName;
	}

	public void setBaseUserName(String baseUserName) {
		this.baseUserName = baseUserName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(String answerCount) {
		this.answerCount = answerCount;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getRightRate() {
		return rightRate;
	}

	public void setRightRate(String rightRate) {
		this.rightRate = rightRate;
	}

}
