package com.codyy.oc.homework.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.codyy.oc.questionlib.entity.QueFillInAnswer;

public class WorkSearchQuestionView {

	private String queQuestionId;
	private String difficulty;
	private Date updateTime;
	private String useCount;
	private String relationalIndicator;
	private String content;
	private String options;
	private String type;
	private String resolveVideo;
	private String answer;
	private String resolve;
	private String fillInAnswerType;
	private String fillInScoreType;
	
	@SuppressWarnings("unused")
	private String endKonwledgeNames;
	List<String> knowledges = new ArrayList<String>();
	List<WorkSearchQuestionView> childrenQuestionList = new ArrayList<WorkSearchQuestionView>();
	List<QueFillInAnswer> fillInAnswers = new ArrayList<QueFillInAnswer>();

	public String getQueQuestionId() {
		return queQuestionId;
	}

	public void setQueQuestionId(String queQuestionId) {
		this.queQuestionId = queQuestionId;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUseCount() {
		return useCount;
	}

	public void setUseCount(String useCount) {
		this.useCount = useCount;
	}

	public String getRelationalIndicator() {
		return relationalIndicator;
	}

	public void setRelationalIndicator(String relationalIndicator) {
		this.relationalIndicator = relationalIndicator;
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

	public List<String> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<String> knowledges) {
		this.knowledges = knowledges;
	}

	public List<WorkSearchQuestionView> getChildrenQuestionList() {
		return childrenQuestionList;
	}

	public void setChildrenQuestionList(List<WorkSearchQuestionView> childrenQuestionList) {
		this.childrenQuestionList = childrenQuestionList;
	}

	public String getEndKonwledgeNames() {
		if (CollectionUtils.isNotEmpty(knowledges)) {
			StringBuilder builder = new StringBuilder();
			for (String knowledgeName : knowledges) {
				builder.append("," + knowledgeName);
			}
			if (builder.length() > 0) {
				return builder.deleteCharAt(0).toString();
			}
		}
		return "";
	}

	public void setEndKonwledgeNames(String endKonwledgeNames) {
		this.endKonwledgeNames = endKonwledgeNames;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getResolve() {
		return resolve;
	}

	public void setResolve(String resolve) {
		this.resolve = resolve;
	}

	public List<QueFillInAnswer> getFillInAnswers() {
		return fillInAnswers;
	}

	public void setFillInAnswers(List<QueFillInAnswer> fillInAnswers) {
		this.fillInAnswers = fillInAnswers;
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

}
