package com.codyy.oc.base.entity;
//节
public class BaseSection {
    private String baseSectionId;

    private String baseChapterId;

    private String sectionName;

    private Integer sort;

    public String getBaseSectionId() {
        return baseSectionId;
    }

    public void setBaseSectionId(String baseSectionId) {
        this.baseSectionId = baseSectionId;
    }

    public String getBaseChapterId() {
        return baseChapterId;
    }

    public void setBaseChapterId(String baseChapterId) {
        this.baseChapterId = baseChapterId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}