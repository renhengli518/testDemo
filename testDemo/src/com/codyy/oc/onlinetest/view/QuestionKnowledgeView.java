package com.codyy.oc.onlinetest.view;

public class QuestionKnowledgeView {
	
	
	private String queQuestionId;
	
	private String examQuestionId;
	
	private String baseEndKnowledgeId;
	
	private String relationalIndicator; // 关系指示：母题/MOTHER, 孪生体/TWINS, 衍生题/EXTEND

	private String motherId; // 母体Id
	
	
	public String getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(String examQuestionId) {
		this.examQuestionId = examQuestionId;
	}

	public String getBaseEndKnowledgeId() {
		return baseEndKnowledgeId;
	}

	public void setBaseEndKnowledgeId(String baseEndKnowledgeId) {
		this.baseEndKnowledgeId = baseEndKnowledgeId;
	}

	public String getQueQuestionId() {
		return queQuestionId;
	}

	public void setQueQuestionId(String queQuestionId) {
		this.queQuestionId = queQuestionId;
	}

	public String getRelationalIndicator() {
		return relationalIndicator;
	}

	public void setRelationalIndicator(String relationalIndicator) {
		this.relationalIndicator = relationalIndicator;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}	
}
