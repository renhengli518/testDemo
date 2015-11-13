package com.codyy.oc.homework.entity;

import java.util.List;

public class WorkQuestion {
    private String workQuestionId;

    private String workHomeworkId;

    private String baseSemesterId;

    private String baseClasslevelId;

    private String baseSubjectId;

    private String baseUserId;

    private String contentVideo;

    private String answer;
    
    private String answerVideo;

	private String difficulty;

    private String type;

    private String resolveVideo;

    private String fillInAnswerType;

    private String fillInScoreType;

    private String relationalIndicator;

    private String motherId;

    private Integer sort;

    private String storeServer;
    
    private Object updateTime;

    private Integer useCount;

    private String queQuestionId;

    private String content;

    private String options;

    private String resolve;

    private String optionsTxt;
    
    private List<WorkQueFillInAnswer> fillInAnswers;

    public String getWorkQuestionId() {
        return workQuestionId;
    }

    public void setWorkQuestionId(String workQuestionId) {
        this.workQuestionId = workQuestionId;
    }

    public String getWorkHomeworkId() {
        return workHomeworkId;
    }

    public void setWorkHomeworkId(String workHomeworkId) {
        this.workHomeworkId = workHomeworkId;
    }

    public String getBaseSemesterId() {
        return baseSemesterId;
    }

    public void setBaseSemesterId(String baseSemesterId) {
        this.baseSemesterId = baseSemesterId;
    }

    public String getBaseClasslevelId() {
        return baseClasslevelId;
    }

    public void setBaseClasslevelId(String baseClasslevelId) {
        this.baseClasslevelId = baseClasslevelId;
    }

    public String getBaseSubjectId() {
        return baseSubjectId;
    }

    public void setBaseSubjectId(String baseSubjectId) {
        this.baseSubjectId = baseSubjectId;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getContentVideo() {
        return contentVideo;
    }

    public void setContentVideo(String contentVideo) {
        this.contentVideo = contentVideo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerVideo() {
 		return answerVideo;
 	}

 	public void setAnswerVideo(String answerVideo) {
 		this.answerVideo = answerVideo;
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

    public String getResolveVideo() {
        return resolveVideo;
    }

    public void setResolveVideo(String resolveVideo) {
        this.resolveVideo = resolveVideo;
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

    public String getRelationalIndicator() {
        return relationalIndicator;
    }

    public void setRelationalIndicator(String relationalIndicator) {
        this.relationalIndicator = relationalIndicator;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStoreServer() {
        return storeServer;
    }

    public void setStoreServer(String storeServer) {
        this.storeServer = storeServer;
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

    public String getOptionsTxt() {
        return optionsTxt;
    }

    public void setOptionsTxt(String optionsTxt) {
        this.optionsTxt = optionsTxt;
    }

	public Object getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Object updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public String getQueQuestionId() {
		return queQuestionId;
	}

	public void setQueQuestionId(String queQuestionId) {
		this.queQuestionId = queQuestionId;
	}

	public List<WorkQueFillInAnswer> getFillInAnswers() {
		return fillInAnswers;
	}

	public void setFillInAnswers(List<WorkQueFillInAnswer> fillInAnswers) {
		this.fillInAnswers = fillInAnswers;
	}
}