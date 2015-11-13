package com.codyy.oc.homework.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.codyy.commons.utils.LongShortSerializer;

public class HomeWork {
private String homeWorkId;//作业ID
private String workTitle;//作业标题
private String createTime;
@JsonSerialize(using=LongShortSerializer.class)
private Date assignTime;//作业布置时间
@JsonSerialize(using=LongShortSerializer.class)
private Date finishTime;//作业完成时间
private String status;//作业的状态
private String subjectName;//作业的学科
private String readOverType;//作业的批阅方式
private int subWorkStuCount;//提交作业的人数
private int stuCount;//学生的总人数
private int queCount;//习题的总数
private int answerCount;//已经回答的题数
private String summary;//作业总评
@JsonSerialize(using=LongShortSerializer.class)
private Date submitTime;//作业提交时间
private int countAll;
private int countSubmit;
private int countReadOver;
private String base_user_id;
private String baseSubjectId;
private String studentUserId;
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getBaseSubjectId() {
	return baseSubjectId;
}
public void setBaseSubjectId(String baseSubjectId) {
	this.baseSubjectId = baseSubjectId;
}
public String getStudentUserId() {
	return studentUserId;
}
public void setStudentUserId(String studentUserId) {
	this.studentUserId = studentUserId;
}

public int getCountAll() {
	return countAll;
}
public void setCountAll(int countAll) {
	this.countAll = countAll;
}
public int getCountSubmit() {
	return countSubmit;
}
public void setCountSubmit(int countSubmit) {
	this.countSubmit = countSubmit;
}
public int getCountReadOver() {
	return countReadOver;
}
public void setCountReadOver(int countReadOver) {
	this.countReadOver = countReadOver;
}
public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}

public Date getSubmitTime() {
	return submitTime;
}
public void setSubmitTime(Date submitTime) {
	this.submitTime = submitTime;
}
public int getQueCount() {
	return queCount;
}
public void setQueCount(int queCount) {
	this.queCount = queCount;
}
public int getAnswerCount() {
	return answerCount;
}
public void setAnswerCount(int answerCount) {
	this.answerCount = answerCount;
}
public String getReadOverType() {
	return readOverType;
}
public void setReadOverType(String readOverType) {
	this.readOverType = readOverType;
}

public int getStuCount() {
	return stuCount;
}
public void setStuCount(int stuCount) {
	this.stuCount = stuCount;
}

public String getHomeWorkId() {
	return homeWorkId;
}
public void setHomeWorkId(String homeWorkId) {
	this.homeWorkId = homeWorkId;
}
public String getWorkTitle() {
	return workTitle;
}
public void setWorkTitle(String workTitle) {
	this.workTitle = workTitle;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getSubjectName() {
	return subjectName;
}
public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
}
public int getSubWorkStuCount() {
	return subWorkStuCount;
}
public void setSubWorkStuCount(int subWorkStuCount) {
	this.subWorkStuCount = subWorkStuCount;
}
public String getBase_user_id() {
	return base_user_id;
}
public void setBase_user_id(String base_user_id) {
	this.base_user_id = base_user_id;
}
}
