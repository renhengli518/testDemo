package com.codyy.oc.questionlib.entity;

public class QueFillInAnswer {
    private String queFillInAnswerId;

    private String queQuestionId;

    private String sort;

    private String answerGrp1;// 答案1或者答案组1

    private String answerGrp2;

    private String answerGrp3;

    private String answerGrp4;

    private String answerGrp1Txt;

    private String answerGrp2Txt;

    private String answerGrp3Txt;

    private String answerGrp4Txt;
    
    private String scoreType;//判断给分类型
    
    private String answerType;//判断答案类型
    
    public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public String getQueFillInAnswerId() {
        return queFillInAnswerId;
    }

    public void setQueFillInAnswerId(String queFillInAnswerId) {
        this.queFillInAnswerId = queFillInAnswerId;
    }

    public String getQueQuestionId() {
        return queQuestionId;
    }

    public void setQueQuestionId(String queQuestionId) {
        this.queQuestionId = queQuestionId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAnswerGrp1() {
        return answerGrp1;
    }

    public void setAnswerGrp1(String answerGrp1) {
        this.answerGrp1 = answerGrp1;
    }

    public String getAnswerGrp2() {
        return answerGrp2;
    }

    public void setAnswerGrp2(String answerGrp2) {
        this.answerGrp2 = answerGrp2;
    }

    public String getAnswerGrp3() {
        return answerGrp3;
    }

    public void setAnswerGrp3(String answerGrp3) {
        this.answerGrp3 = answerGrp3;
    }

    public String getAnswerGrp4() {
        return answerGrp4;
    }

    public void setAnswerGrp4(String answerGrp4) {
        this.answerGrp4 = answerGrp4;
    }

    public String getAnswerGrp1Txt() {
        return answerGrp1Txt;
    }

    public void setAnswerGrp1Txt(String answerGrp1Txt) {
        this.answerGrp1Txt = answerGrp1Txt;
    }

    public String getAnswerGrp2Txt() {
        return answerGrp2Txt;
    }

    public void setAnswerGrp2Txt(String answerGrp2Txt) {
        this.answerGrp2Txt = answerGrp2Txt;
    }

    public String getAnswerGrp3Txt() {
        return answerGrp3Txt;
    }

    public void setAnswerGrp3Txt(String answerGrp3Txt) {
        this.answerGrp3Txt = answerGrp3Txt;
    }

    public String getAnswerGrp4Txt() {
        return answerGrp4Txt;
    }

    public void setAnswerGrp4Txt(String answerGrp4Txt) {
        this.answerGrp4Txt = answerGrp4Txt;
    }

	@Override
	public String toString() {
		return "QueFillInAnswer [queFillInAnswerId=" + queFillInAnswerId
				+ ", queQuestionId=" + queQuestionId + ", sort=" + sort
				+ ", answerGrp1=" + answerGrp1 + ", answerGrp2=" + answerGrp2
				+ ", answerGrp3=" + answerGrp3 + ", answerGrp4=" + answerGrp4
				+ ", answerGrp1Txt=" + answerGrp1Txt + ", answerGrp2Txt="
				+ answerGrp2Txt + ", answerGrp3Txt=" + answerGrp3Txt
				+ ", answerGrp4Txt=" + answerGrp4Txt + ", toString()="
				+ super.toString() + "]";
	}
    
    
}