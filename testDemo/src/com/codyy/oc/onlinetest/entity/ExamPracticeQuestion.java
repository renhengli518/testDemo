package com.codyy.oc.onlinetest.entity;

import java.util.List;

public class ExamPracticeQuestion {
    private String examPracticeQuestionId;

    private String examPracticeId;

    private String answer;

    private String difficulty;

    private String type;

    private String result;

    private String resolveVideo;

    private String content;

    private String options;

    private String resolve;

    private String myAnswer;
    
    private String answerVideo;
    
    private String fillInAnswerType;  /*
	                                  * 填空题答案类型，独立答案/INDEPENDENT,
	                                    * 组合答案/COMBINATION
	                                        */

    private String fillInScoreType;  // 填空题给分类型，全对给分/ALL_CORRECT,
	                                // 按空给分/SCORE_BY_FILL
    
    private String contentVideo;  //题干视频
    
	private List<ExamQueFillInAnswer> fillInAnswers; // 关联的填空题答案
		

    public String getExamPracticeQuestionId() {
        return examPracticeQuestionId;
    }

    public void setExamPracticeQuestionId(String examPracticeQuestionId) {
        this.examPracticeQuestionId = examPracticeQuestionId;
    }

    public String getExamPracticeId() {
        return examPracticeId;
    }

    public void setExamPracticeId(String examPracticeId) {
        this.examPracticeId = examPracticeId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResolveVideo() {
        return resolveVideo;
    }

    public void setResolveVideo(String resolveVideo) {
        this.resolveVideo = resolveVideo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

	public String getAnswerVideo() {
		return answerVideo;
	}

	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}

	public String getFillInAnswerType() {
		return fillInAnswerType;
	}

	public void setFillInAnswerType(String fillInAnswerType) {
		this.fillInAnswerType = fillInAnswerType;
	}

	public String getFillInScoreType() {
		return fillInScoreType;
	}

	public void setFillInScoreType(String fillInScoreType) {
		this.fillInScoreType = fillInScoreType;
	}

	public String getContentVideo() {
		return contentVideo;
	}

	public void setContentVideo(String contentVideo) {
		this.contentVideo = contentVideo;
	}

	public List<ExamQueFillInAnswer> getFillInAnswers() {
		return fillInAnswers;
	}

	public void setFillInAnswers(List<ExamQueFillInAnswer> fillInAnswers) {
		this.fillInAnswers = fillInAnswers;
	}
}