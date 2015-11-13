package com.codyy.oc.base.parse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xierongbing
 * @date 2015年8月21日 上午8:35:38 
 * @description 
 */

public class QuestionInfo {
	private String chapterName;
	
	private String knowledge;
	
	private String difficulty;
	
	private String questionType;
	
	private String questionTitle;
	
	private String option;
	
	private String answer;
	
	private String analysis;
	
	private String score = "0";
	
	//题目中的图片
	private List<PictureInfo> titlePictureList = new ArrayList<PictureInfo>();
	
	//解析中的图片
	private List<PictureInfo> analysisPictureList = new ArrayList<PictureInfo>();
	
	//额外信息
	private int optionSize;
	
	public int getOptionSize() {
		return optionSize;
	}

	public void setOptionSize(int optionSize) {
		this.optionSize = optionSize;
	}

	public List<PictureInfo> getAnalysisPictureList() {
		return analysisPictureList;
	}

	public void setAnalysisPictureList(List<PictureInfo> analysisPictureList) {
		this.analysisPictureList = analysisPictureList;
	}
	
	public List<PictureInfo> getTitlePictureList() {
		return titlePictureList;
	}

	public void setTitlePictureList(List<PictureInfo> titlePictureList) {
		this.titlePictureList = titlePictureList;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toString() {
		return "试题 [章节=" + chapterName + ", 知识点="
				+ knowledge + ", 难易=" + difficulty + ", 习题类型="
				+ questionType + ", 习题题干=" + questionTitle
				+ ", 选项=" + option + ", 正确答案=" + answer + ", 习题解析="
				+ analysis + ", 分数=" + score + ", 题目中图片数="
				+ getTitlePictureList().size() + ", 解析中图片数="+getAnalysisPictureList().size()+"]";
	}
}
