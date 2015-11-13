package com.codyy.oc.onlinetest.entity;

import java.util.ArrayList;
import java.util.List;

import com.codyy.oc.base.entity.BaseKnowledge;

/**
 * 测试-题库 知识点
 * @author zhangshuangquan
 *
 */
public class ExamQuestionListResult extends ExamQuestion {
	
	
    private String knowledgeEndNames;/*所有关联末节知识点名称*/
    
    private String knowledgeEndIds;/*所有关联末节知识点ID*/
    
    private List<BaseKnowledge> knowledgeEndList;/*所有关联末节知识点列表*/
    
    private List<String> knowledgeIdStr;/*所有关联知识点ID列表（总共六级）*/
    
    private List<String> knowledgeNameStr;/*所有关联知识点名字列表（总共六级）*/
    
    private List<String> knowledgeIdAndNameStr;/*所有关联知识点ID和名称组合 用||分开*/
    
    private List<String> chapterIdStr;/*所有关联章节ID列表（总共四级）*/
    
    private List<String> chapterNameStr;/*所有关联章节名字列表（总共四级）*/
    
    private List<String> chapterIdAndNameStr;/*所有关联章节ID和名称组合 用||分开*/
    
	private String examTimes;//组卷次数
	
	private String myAnswer; //我的答案
	
	private String myScore; //我的得分
	
	private String avgScore; //平均得分
	
	private String rightRate; //正确率
	
	private String teacherComment; //教师点评
	
	private String answerVideo; //音视频

	private String examTaskId;//测试id

	public String getExamTaskId() {
		return examTaskId;
	}

	public void setExamTaskId(String examTaskId) {
		this.examTaskId = examTaskId;
	}

	private List<ExamQueFillInAnswer> fillList= new ArrayList<ExamQueFillInAnswer>();//封装题目是填空题的答案
	private Integer result;//答题的状态(正确1  错误是0)
	
	private String examQuestionResultId;//指定学生的测试

	public String getExamQuestionResultId() {
		return examQuestionResultId;
	}

	public void setExamQuestionResultId(String examQuestionResultId) {
		this.examQuestionResultId = examQuestionResultId;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public List<ExamQueFillInAnswer> getFillList() {
		return fillList;
	}

	public void setFillList(List<ExamQueFillInAnswer> fillList) {
		this.fillList = fillList;
	}

	public String getExamTimes() {
		return examTimes;
	}

	public void setExamTimes(String examTimes) {
		this.examTimes = examTimes;
	}
	
	public List<String> getKnowledgeIdAndNameStr() {
		return knowledgeIdAndNameStr;
	}

	public List<String> getChapterIdStr() {
		return chapterIdStr;
	}

	public void setChapterIdStr(List<String> chapterIdStr) {
		this.chapterIdStr = chapterIdStr;
	}

	public List<String> getChapterNameStr() {
		return chapterNameStr;
	}

	public void setChapterNameStr(List<String> chapterNameStr) {
		this.chapterNameStr = chapterNameStr;
	}

	public List<String> getChapterIdAndNameStr() {
		return chapterIdAndNameStr;
	}

	public void setChapterIdAndNameStr(List<String> chapterIdAndNameStr) {
		this.chapterIdAndNameStr = chapterIdAndNameStr;
	}

	public void setKnowledgeIdAndNameStr(List<String> knowledgeIdAndNameStr) {
		this.knowledgeIdAndNameStr = knowledgeIdAndNameStr;
	}

	private String examFormedCnt;/*组卷次数*/

	public List<String> getKnowledgeIdStr() {
		return knowledgeIdStr;
	}

	public void setKnowledgeIdStr(List<String> knowledgeIdStr) {
		this.knowledgeIdStr = knowledgeIdStr;
	}

	public List<String> getKnowledgeNameStr() {
		return knowledgeNameStr;
	}

	public void setKnowledgeNameStr(List<String> knowledgeNameStr) {
		this.knowledgeNameStr = knowledgeNameStr;
	}

	public String getExamFormedCnt() {
		return examFormedCnt == null ? "0" : examFormedCnt;
	}

	public void setExamFormedCnt(String examFormedCnt) {
		this.examFormedCnt = examFormedCnt;
	}

	public List<BaseKnowledge> getKnowledgeEndList() {
		return knowledgeEndList;
	}

	public void setKnowledgeEndList(List<BaseKnowledge> knowledgeEndList) {
		this.knowledgeEndList = knowledgeEndList;
	}

	public String getKnowledgeEndNames() {
		return knowledgeEndNames == null ? "" : knowledgeEndNames;
	}

	public void setKnowledgeEndNames(String knowledgeEndNames) {
		this.knowledgeEndNames = knowledgeEndNames;
	}

	public String getKnowledgeEndIds() {
		return knowledgeEndIds;
	}

	public void setKnowledgeEndIds(String knowledgeEndIds) {
		this.knowledgeEndIds = knowledgeEndIds;
	}

	public String getMyAnswer() {
		return myAnswer;
	}

	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
	}
	
	public String getMyScore() {
		return myScore;
	}

	public void setMyScore(String myScore) {
		this.myScore = myScore;
	}

	public String getTeacherComment() {
		return teacherComment;
	}

	public void setTeacherComment(String teacherComment) {
		this.teacherComment = teacherComment;
	}

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

	public String getRightRate() {
		return rightRate;
	}

	public void setRightRate(String rightRate) {
		this.rightRate = rightRate;
	}

	public String getAnswerVideo() {
		return answerVideo;
	}

	public void setAnswerVideo(String answerVideo) {
		this.answerVideo = answerVideo;
	}
}
