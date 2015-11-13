package com.codyy.oc.homework.entity;

public class correctCount {
private String workQuestionId;
private String type;
private int rn;
private int rightStuCount;//每道题答对的学生总数
private int checkedStuCount;//批阅的学生总数
private String quePercent;


public String getQuePercent() {
	return quePercent;
}
public void setQuePercent(String quePercent) {
	this.quePercent = quePercent;
}
public String getWorkQuestionId() {
	return workQuestionId;
}
public void setWorkQuestionId(String workQuestionId) {
	this.workQuestionId = workQuestionId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public int getRn() {
	return rn;
}
public void setRn(int rn) {
	this.rn = rn;
}
public int getRightStuCount() {
	return rightStuCount;
}
public void setRightStuCount(int rightStuCount) {
	this.rightStuCount = rightStuCount;
}
public int getCheckedStuCount() {
	return checkedStuCount;
}
public void setCheckedStuCount(int checkedStuCount) {
	this.checkedStuCount = checkedStuCount;
}
}
