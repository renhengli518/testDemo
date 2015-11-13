package com.codyy.oc.onlinetest.view;

public class TeachCommentView {

	private String examResultQuestionId;//指定每个用户的测试结果
	private Float score;
	private String teacheComment;
	private Integer result;//主观题的测试结果

	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getExamResultQuestionId() {
		return examResultQuestionId;
	}
	public void setExamResultQuestionId(String examResultQuestionId) {
		this.examResultQuestionId = examResultQuestionId;
	}

	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getTeacheComment() {
		return teacheComment;
	}
	public void setTeacheComment(String teacheComment) {
		this.teacheComment = teacheComment;
	}
	
	
}
