package com.codyy.oc.homework.view;

import java.util.List;

import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkReceiveDoc;

public class ReadByStuResultView {
	
	private String homeworkId;
	private String workReceiveStuId;
	private String answerUser;
	private String workTitle;
	private String textQueContent;
	private List<WorkDoc> docs;
	private List<WorkReceiveDoc> receiveDocs;
	private List<ReadByStuQuestionView> questions;
	private String textQueAnswer;
	private String textQueReadOverFlag;
	private String docQueReadOverFlag;
	
	public String getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getWorkReceiveStuId() {
		return workReceiveStuId;
	}
	public void setWorkReceiveStuId(String workReceiveStuId) {
		this.workReceiveStuId = workReceiveStuId;
	}
	public String getAnswerUser() {
		return answerUser;
	}
	public void setAnswerUser(String answerUser) {
		this.answerUser = answerUser;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getTextQueContent() {
		return textQueContent;
	}
	public void setTextQueContent(String textQueContent) {
		this.textQueContent = textQueContent;
	}
	public List<WorkDoc> getDocs() {
		return docs;
	}
	public void setDocs(List<WorkDoc> docs) {
		this.docs = docs;
	}
	public List<WorkReceiveDoc> getReceiveDocs() {
		return receiveDocs;
	}
	public void setReceiveDocs(List<WorkReceiveDoc> receiveDocs) {
		this.receiveDocs = receiveDocs;
	}
	public List<ReadByStuQuestionView> getQuestions() {
		return questions;
	}
	public void setQuestions(List<ReadByStuQuestionView> questions) {
		this.questions = questions;
	}
	public String getTextQueAnswer() {
		return textQueAnswer;
	}
	public void setTextQueAnswer(String textQueAnswer) {
		this.textQueAnswer = textQueAnswer;
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

}
