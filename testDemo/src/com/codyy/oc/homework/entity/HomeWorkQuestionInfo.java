package com.codyy.oc.homework.entity;

import java.util.List;

public class HomeWorkQuestionInfo {
private String homeWorkId;
private String workTitle;
private String readOverType;
private String textQueContent;//文本题题干
private String textQueAnswer;//文本题学生回答    
private String textQueComment;//文本题评语
private List<QuestionInfo> questionInfo;
private List<WorkDoc> workDocList;//附件题
private List<WorkReceiveDoc> workReceiveDoc;//学生附件回答
private String docQueComment;//学生附件评语
private String summary;//作业的总评
private String readOverStuId;//批阅学生Id

public String getReadOverStuId() {
	return readOverStuId;
}
public void setReadOverStuId(String readOverStuId) {
	this.readOverStuId = readOverStuId;
}


public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}
public String getReadOverType() {
	return readOverType;
}
public void setReadOverType(String readOverType) {
	this.readOverType = readOverType;
}
public String getDocQueComment() {
	return docQueComment;
}
public void setDocQueComment(String docQueComment) {
	this.docQueComment = docQueComment;
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
public List<WorkReceiveDoc> getWorkReceiveDoc() {
	return workReceiveDoc;
}
public void setWorkReceiveDoc(List<WorkReceiveDoc> workReceiveDoc) {
	this.workReceiveDoc = workReceiveDoc;
}
public List<WorkDoc> getWorkDocList() {
	return workDocList;
}
public void setWorkDocList(List<WorkDoc> workDocList) {
	this.workDocList = workDocList;
}
public String getWorkTitle() {
	return workTitle;
}
public void setWorkTitle(String workTitle) {
	this.workTitle = workTitle;
}

public String getHomeWorkId() {
	return homeWorkId;
}
public void setHomeWorkId(String homeWorkId) {
	this.homeWorkId = homeWorkId;
}
public String getTextQueContent() {
	return textQueContent;
}
public void setTextQueContent(String textQueContent) {
	this.textQueContent = textQueContent;
}
public List<QuestionInfo> getQuestionInfo() {
	return questionInfo;
}
public void setQuestionInfo(List<QuestionInfo> questionInfo) {
	this.questionInfo = questionInfo;
}

}
