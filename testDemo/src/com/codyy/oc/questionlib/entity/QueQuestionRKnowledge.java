package com.codyy.oc.questionlib.entity;

public class QueQuestionRKnowledge {
    private String queQuestionRKnowledgeId;

    private String queQuestionId;

    private String baseKnowledgeId;

    private String baseSubKnowledge1Id;

    private String baseSubKnowledge2Id;

    private String baseSubKnowledge3Id;

    private String baseSubKnowledge4Id;

    private String baseSubKnowledge5Id;

    private String baseEndKnowledgeId;
    
    private String konwledgeName;//知识点名称（冗余）

    
    public String getKonwledgeName() {
		return konwledgeName;
	}

	public void setKonwledgeName(String konwledgeName) {
		this.konwledgeName = konwledgeName;
	}

	public String getQueQuestionRKnowledgeId() {
        return queQuestionRKnowledgeId;
    }

    public void setQueQuestionRKnowledgeId(String queQuestionRKnowledgeId) {
        this.queQuestionRKnowledgeId = queQuestionRKnowledgeId;
    }

    public String getQueQuestionId() {
        return queQuestionId;
    }

    public void setQueQuestionId(String queQuestionId) {
        this.queQuestionId = queQuestionId;
    }

    public String getBaseKnowledgeId() {
        return baseKnowledgeId;
    }

    public void setBaseKnowledgeId(String baseKnowledgeId) {
        this.baseKnowledgeId = baseKnowledgeId;
    }

    public String getBaseSubKnowledge1Id() {
        return baseSubKnowledge1Id;
    }

    public void setBaseSubKnowledge1Id(String baseSubKnowledge1Id) {
        this.baseSubKnowledge1Id = baseSubKnowledge1Id;
    }

    public String getBaseSubKnowledge2Id() {
        return baseSubKnowledge2Id;
    }

    public void setBaseSubKnowledge2Id(String baseSubKnowledge2Id) {
        this.baseSubKnowledge2Id = baseSubKnowledge2Id;
    }

    public String getBaseSubKnowledge3Id() {
        return baseSubKnowledge3Id;
    }

    public void setBaseSubKnowledge3Id(String baseSubKnowledge3Id) {
        this.baseSubKnowledge3Id = baseSubKnowledge3Id;
    }

    public String getBaseSubKnowledge4Id() {
        return baseSubKnowledge4Id;
    }

    public void setBaseSubKnowledge4Id(String baseSubKnowledge4Id) {
        this.baseSubKnowledge4Id = baseSubKnowledge4Id;
    }

    public String getBaseSubKnowledge5Id() {
        return baseSubKnowledge5Id;
    }

    public void setBaseSubKnowledge5Id(String baseSubKnowledge5Id) {
        this.baseSubKnowledge5Id = baseSubKnowledge5Id;
    }

    public String getBaseEndKnowledgeId() {
        return baseEndKnowledgeId;
    }

    public void setBaseEndKnowledgeId(String baseEndKnowledgeId) {
        this.baseEndKnowledgeId = baseEndKnowledgeId;
    }

	@Override
	public String toString() {
		return "QueQuestionRKnowledge [queQuestionRKnowledgeId="
				+ queQuestionRKnowledgeId + ", queQuestionId=" + queQuestionId
				+ ", baseKnowledgeId=" + baseKnowledgeId
				+ ", baseSubKnowledge1Id=" + baseSubKnowledge1Id
				+ ", baseSubKnowledge2Id=" + baseSubKnowledge2Id
				+ ", baseSubKnowledge3Id=" + baseSubKnowledge3Id
				+ ", baseSubKnowledge4Id=" + baseSubKnowledge4Id
				+ ", baseSubKnowledge5Id=" + baseSubKnowledge5Id
				+ ", baseEndKnowledgeId=" + baseEndKnowledgeId
				+ ", toString()=" + super.toString() + "]";
	}
    
    
}