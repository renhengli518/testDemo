package com.codyy.oc.homework.view;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.utils.LongShortSerializer;

public class ReadOverClassStudentView {

	private String name;

	private String userId;

	private String receiveStuId;

	@JsonSerialize(using = LongShortSerializer.class)
	private Date submitTime;

	private int answerCount;

	private Date finishTime;

	private int questionCount;

	@SuppressWarnings("unused")
	private String isSubmitOnTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiveStuId() {
		return receiveStuId;
	}

	public void setReceiveStuId(String receiveStuId) {
		this.receiveStuId = receiveStuId;
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

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public String getIsSubmitOnTime() {
		return this.submitTime.getTime() > this.finishTime.getTime() ? CommonsConstant.FLAG_NO : CommonsConstant.FLAG_YES;
	}

	public void setIsSubmitOnTime(String isSubmitOnTime) {
		this.isSubmitOnTime = isSubmitOnTime;
	}

}
