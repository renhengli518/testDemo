package com.codyy.oc.homework.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class WorkHomework {
    private String workHomeworkId;

    private String workTitle;

    private String baseSubjectId;

    private String baseUserId;

    private String assignType;

    private String readOverType;

    private Date createTime;

    private String status;

    private Date assignTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date finishTime;

    private Integer questionCount;

    private String textQueContent;

    public String getWorkHomeworkId() {
        return workHomeworkId;
    }

    public void setWorkHomeworkId(String workHomeworkId) {
        this.workHomeworkId = workHomeworkId;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getBaseSubjectId() {
        return baseSubjectId;
    }

    public void setBaseSubjectId(String baseSubjectId) {
        this.baseSubjectId = baseSubjectId;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getAssignType() {
        return assignType;
    }

    public void setAssignType(String assignType) {
        this.assignType = assignType;
    }

    public String getReadOverType() {
        return readOverType;
    }

    public void setReadOverType(String readOverType) {
        this.readOverType = readOverType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public String getTextQueContent() {
        return textQueContent;
    }

    public void setTextQueContent(String textQueContent) {
        this.textQueContent = textQueContent;
    }
}