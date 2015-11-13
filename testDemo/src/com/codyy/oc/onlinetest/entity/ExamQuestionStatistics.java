package com.codyy.oc.onlinetest.entity;

/**
 * 问题统计
 * @author zhangshuangquan
 *
 */
public class ExamQuestionStatistics {
	
	private String examQuestionId;   //题目Id
	
	private String totalCnt;        //总体数
	
	private String correctCnt;      //正确题数
	
	private String passRate;        //通过率
	
	private String sort;            //排序

	public String getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(String examQuestionId) {
		this.examQuestionId = examQuestionId;
	}

	public String getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getCorrectCnt() {
		return correctCnt;
	}

	public void setCorrectCnt(String correctCnt) {
		this.correctCnt = correctCnt;
	}

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
