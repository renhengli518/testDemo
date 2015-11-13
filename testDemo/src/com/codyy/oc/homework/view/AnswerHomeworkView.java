package com.codyy.oc.homework.view;

import java.util.ArrayList;
import java.util.List;

import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkQuestion;

public class AnswerHomeworkView {
	
	private String workHomeworkId;

    private String workTitle;
    
    private String textQueContent;
    
    List<WorkQuestion> questions = new ArrayList<WorkQuestion>();
    
    List<WorkDoc> docs = new ArrayList<WorkDoc>();

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

	public String getTextQueContent() {
		return textQueContent;
	}

	public void setTextQueContent(String textQueContent) {
		this.textQueContent = textQueContent;
	}

	public List<WorkQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<WorkQuestion> questions) {
		this.questions = questions;
	}

	public List<WorkDoc> getDocs() {
		return docs;
	}

	public void setDocs(List<WorkDoc> docs) {
		this.docs = docs;
	}
    
}
