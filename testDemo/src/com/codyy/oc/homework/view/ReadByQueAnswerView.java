package com.codyy.oc.homework.view;

import java.util.List;

import com.codyy.oc.homework.entity.WorkReceiveDoc;

public class ReadByQueAnswerView {
	
	private String answer;
	
	private String readFlag;
	
	private String comment;
	
	private int unReadNum;
	
	private String workRecStuQueAnswerId;
	
	private String answerVideo;
	
	private String workReceiveStuId;
	
	private  List<WorkReceiveDoc> docs;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(int unReadNum) {
		this.unReadNum = unReadNum;
	}

	public String getWorkRecStuQueAnswerId() {
		return workRecStuQueAnswerId;
	}

	public void setWorkRecStuQueAnswerId(String workRecStuQueAnswerId) {
		this.workRecStuQueAnswerId = workRecStuQueAnswerId;
	}

	public String getAnswerVideo() {
		return answerVideo;
	}

	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}

	public String getWorkReceiveStuId() {
		return workReceiveStuId;
	}

	public void setWorkReceiveStuId(String workReceiveStuId) {
		this.workReceiveStuId = workReceiveStuId;
	}

	public List<WorkReceiveDoc> getDocs() {
		return docs;
	}

	public void setDocs(List<WorkReceiveDoc> docs) {
		this.docs = docs;
	}
}
