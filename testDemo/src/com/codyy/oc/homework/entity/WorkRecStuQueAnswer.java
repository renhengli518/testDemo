package com.codyy.oc.homework.entity;

public class WorkRecStuQueAnswer {
    private String workRecStuQueAnswerId;

    private String workReceiveStuId;

    private String workQuestionId;

    private String correctFlag;

    private String myAnswer;

    private String comment;
    
    private String readOverFlag;
    
    private String answerVideo;

    public String getWorkRecStuQueAnswerId() {
        return workRecStuQueAnswerId;
    }

    public void setWorkRecStuQueAnswerId(String workRecStuQueAnswerId) {
        this.workRecStuQueAnswerId = workRecStuQueAnswerId;
    }

    public String getWorkReceiveStuId() {
        return workReceiveStuId;
    }

    public void setWorkReceiveStuId(String workReceiveStuId) {
        this.workReceiveStuId = workReceiveStuId;
    }

    public String getWorkQuestionId() {
        return workQuestionId;
    }

    public void setWorkQuestionId(String workQuestionId) {
        this.workQuestionId = workQuestionId;
    }

    public String getCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(String correctFlag) {
        this.correctFlag = correctFlag;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

	public String getReadOverFlag() {
		return readOverFlag;
	}

	public void setReadOverFlag(String readOverFlag) {
		this.readOverFlag = readOverFlag;
	}

	public String getAnswerVideo() {
		return answerVideo;
	}

	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}
}