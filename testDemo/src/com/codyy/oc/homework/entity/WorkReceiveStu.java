package com.codyy.oc.homework.entity;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class WorkReceiveStu{
    private String workReceiveStuId;

    private String baseUserId;

    private String workHomeworkId;

    private Integer sort;

    private String status;

    private Integer answerCount;

    private Date submitTime;

    private String readOverStuId;

    private String baseClassId;

    private String textQueAnswer;

    private String textQueComment;

    private String docQueComment;

    private String summary;
    
    private String textQueReadOverFlag;

    private String docQueReadOverFlag;
    
    private String baseClassName;

    private String baseClasslevelId;

    private String classlevelName;
    
    public static void randomSort(List<WorkReceiveStu> list){
    	Random rd = new Random();
    	for(int i = 0;i<list.size();i++){
    		int j = rd.nextInt(list.size());
    		WorkReceiveStu receiveStu = list.get(j);
    		list.set(j, list.get(i));
    		list.set(i, receiveStu);
    	}
    }
    
    public String getWorkReceiveStuId() {
        return workReceiveStuId;
    }

    public void setWorkReceiveStuId(String workReceiveStuId) {
        this.workReceiveStuId = workReceiveStuId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getReadOverStuId() {
        return readOverStuId;
    }

    public void setReadOverStuId(String readOverStuId) {
        this.readOverStuId = readOverStuId;
    }

    public String getBaseClassId() {
        return baseClassId;
    }

    public void setBaseClassId(String baseClassId) {
        this.baseClassId = baseClassId;
    }

    public String getTextQueAnswer() {
        return textQueAnswer;
    }

    public void setTextQueAnswer(String textQueAnswer) {
        this.textQueAnswer = textQueAnswer;
    }

    public String getTextQueComment() {
        return textQueComment;
    }

    public void setTextQueComment(String textQueComment) {
        this.textQueComment = textQueComment;
    }

    public String getDocQueComment() {
        return docQueComment;
    }

    public void setDocQueComment(String docQueComment) {
        this.docQueComment = docQueComment;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

	public String getTextQueReadOverFlag() {
		return textQueReadOverFlag;
	}

	public void setTextQueReadOverFlag(String textQueReadOverFlag) {
		this.textQueReadOverFlag = textQueReadOverFlag;
	}

	public String getDocQueReadOverFlag() {
		return docQueReadOverFlag;
	}

	public void setDocQueReadOverFlag(String docQueReadOverFlag) {
		this.docQueReadOverFlag = docQueReadOverFlag;
	}

	public String getBaseClassName() {
		return baseClassName;
	}

	public void setBaseClassName(String baseClassName) {
		this.baseClassName = baseClassName;
	}

	public String getBaseClasslevelId() {
		return baseClasslevelId;
	}

	public void setBaseClasslevelId(String baseClasslevelId) {
		this.baseClasslevelId = baseClasslevelId;
	}

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

}