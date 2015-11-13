package com.codyy.oc.base.entity;

public class BaseClasslevel {
    private String baseClasslevelId;

    private String baseSemesterId;

    private String classlevelName;

    private Integer sort;

    public String getBaseClasslevelId() {
        return baseClasslevelId;
    }

    public void setBaseClasslevelId(String baseClasslevelId) {
        this.baseClasslevelId = baseClasslevelId;
    }

    public String getBaseSemesterId() {
        return baseSemesterId;
    }

    public void setBaseSemesterId(String baseSemesterId) {
        this.baseSemesterId = baseSemesterId;
    }

    public String getClasslevelName() {
        return classlevelName;
    }

    public void setClasslevelName(String classlevelName) {
        this.classlevelName = classlevelName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}