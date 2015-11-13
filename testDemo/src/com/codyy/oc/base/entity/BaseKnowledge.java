package com.codyy.oc.base.entity;

import java.math.BigDecimal;

public class BaseKnowledge {
    private String baseKnowledgeId;

    private String baseSemesterId;

    private String baseSubjectId;

    private String knowledgeName;

    private String parentId;

    private BigDecimal sort;

    public String getBaseKnowledgeId() {
        return baseKnowledgeId;
    }

    public void setBaseKnowledgeId(String baseKnowledgeId) {
        this.baseKnowledgeId = baseKnowledgeId;
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

	public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }
}