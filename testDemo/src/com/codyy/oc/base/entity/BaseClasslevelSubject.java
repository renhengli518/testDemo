package com.codyy.oc.base.entity;

public class BaseClasslevelSubject {
    private String baseClasslevelSubjectId;

    private String baseClasslevelId;

    private String baseSubjectId;

    private Integer sort;

    public String getBaseClasslevelSubjectId() {
        return baseClasslevelSubjectId;
    }

    public void setBaseClasslevelSubjectId(String baseClasslevelSubjectId) {
        this.baseClasslevelSubjectId = baseClasslevelSubjectId;
    }

    public String getBaseClasslevelId() {
        return baseClasslevelId;
    }

    public void setBaseClasslevelId(String baseClasslevelId) {
        this.baseClasslevelId = baseClasslevelId;
    }

    public String getBaseSubjectId() {
        return baseSubjectId;
    }

    public void setBaseSubjectId(String baseSubjectId) {
        this.baseSubjectId = baseSubjectId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}