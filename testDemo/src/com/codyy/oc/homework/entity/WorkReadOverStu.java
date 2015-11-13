package com.codyy.oc.homework.entity;

public class WorkReadOverStu {
    private String workReadOverStuId;

    private String baseUserId;

    private String workHomeworkId;

    private Integer sort;

    public String getWorkReadOverStuId() {
        return workReadOverStuId;
    }

    public void setWorkReadOverStuId(String workReadOverStuId) {
        this.workReadOverStuId = workReadOverStuId;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getWorkHomeworkId() {
        return workHomeworkId;
    }

    public void setWorkHomeworkId(String workHomeworkId) {
        this.workHomeworkId = workHomeworkId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}