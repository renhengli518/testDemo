package com.codyy.oc.homework.entity;

import java.util.Date; 
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.codyy.commons.utils.LongShortSerializer;

public class ReceiveStu {
private String workReceiveStuId;
private String baseUserId;
public String getBaseUserId() {
	return baseUserId;
}
public void setBaseUserId(String baseUserId) {
	this.baseUserId = baseUserId;
}
private String realName;
@JsonSerialize(using=LongShortSerializer.class)
private Date submitTime;
private int answerCount;
@JsonSerialize(using=LongShortSerializer.class)
private Date finishTime;
private String summary;
private int rightCount;
private int objectQueCount;

public int getObjectQueCount() {
	return objectQueCount;
}
public void setObjectQueCount(int objectQueCount) {
	this.objectQueCount = objectQueCount;
}
private String correctPercent;

public String getCorrectPercent() {
	return correctPercent;
}
public void setCorrectPercent(String correctPercent) {
	this.correctPercent = correctPercent;
}
public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}
public int getRightCount() {
	return rightCount;
}
public void setRightCount(int rightCount) {
	this.rightCount = rightCount;
}
public Date getFinishTime() {
	return finishTime;
}
public void setFinishTime(Date finishTime) {
	this.finishTime = finishTime;
}
public String getWorkReceiveStuId() {
	return workReceiveStuId;
}
public void setWorkReceiveStuId(String workReceiveStuId) {
	this.workReceiveStuId = workReceiveStuId;
}

public String getRealName() {
	return realName;
}
public void setRealName(String realName) {
	this.realName = realName;
}
public Date getSubmitTime() {
	return submitTime;
}
public void setSubmitTime(Date submitTime) {
	this.submitTime = submitTime;
}
public int getAnswerCount() {
	return answerCount;
}
public void setAnswerCount(int answerCount) {
	this.answerCount = answerCount;
}
}
