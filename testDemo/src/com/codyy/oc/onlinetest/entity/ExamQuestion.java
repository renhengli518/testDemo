package com.codyy.oc.onlinetest.entity;

import java.util.Date;
import java.util.List;

/**
 * 测试 - 题库
 * 
 * @author zhangshuangquan
 * 
 */
public class ExamQuestion {
	private String examQuestionId;

	private String baseSemesterId;

	private String baseClasslevelId;

	private String baseSubjectId;

	private String baseUserId; // 习题创建者

	private String examId;

	private String contentVideo;

	private String answer; // 选择题正确答案（多个直接连接，无分隔符）

	private String difficulty; // 难易度：容易/EASY, 较容易/LITTLE_EASY, 一般/NORMAL,
								// 较难/LITTLE_DIFFICULT, 难/DIFFICULT

	private String type; /*
						 * 习题类型： 单选题/SINGLE_CHOICE, 多选题/MULTI_CHOICE,
						 * 判断题/JUDEMENT, 填空题/FILL_IN_BLANK, 问答题/ASK_ANSWER,
						 * 计算题/COMPUTING
						 */

	private String resolveVideo; // 习题解析

	private String fillInAnswerType; /*
									 * 填空题答案类型，独立答案/INDEPENDENT,
									 * 组合答案/COMBINATION
									 */

	private String fillInScoreType; // 填空题给分类型，全对给分/ALL_CORRECT,
									// 按空给分/SCORE_BY_FILL

	private String relationalIndicator; // 关系指示：母题/MOTHER, 孪生体/TWINS, 衍生题/EXTEND

	private String motherId; // 母体Id

	private Integer score;

	private Integer sort;

	private String storeServer; // 视频存储服务器

	private String content; // 习题题干内容

	private String options; // 习题选项（多个以两个冒号分隔）

	private String resolve;

	private String optionsTxt; // 纯文本选项（多个以逗号分隔）

	private List<ExamQuestionRKnowledge> rKnowledges; /* 获取关联知识点ID */

	private List<ExamQuestionRChapter> rChapters;/* 获取关联章节列表 */

	private Date updateTime; // 更新时间

	private String num; // 某一试题类型数量

	List<ExamQueFillInAnswer> fillInAnswers; // 关联的填空题答案

	private String tempoparyFlag; // 临时习题标志：Y/是，N/否

	private String endKonwledges;// 冗余end知识点以逗号分隔

	private String fillAnstwerStr;// 填空答案字符串 传到前台

	private String queQuestionId;

	public String getQueQuestionId() {
		return queQuestionId;
	}

	public void setQueQuestionId(String queQuestionId) {
		this.queQuestionId = queQuestionId;
	}

	public String getFillAnstwerStr() {
		return fillAnstwerStr;
	}

	public void setFillAnstwerStr(String fillAnstwerStr) {
		this.fillAnstwerStr = fillAnstwerStr;
	}

	public String getEndKonwledges() {
		return endKonwledges;
	}

	public void setEndKonwledges(String endKonwledges) {
		this.endKonwledges = endKonwledges;
	}

	public String getTempoparyFlag() {
		return tempoparyFlag;
	}

	public void setTempoparyFlag(String tempoparyFlag) {
		this.tempoparyFlag = tempoparyFlag;
	}

	public String getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(String examQuestionId) {
		this.examQuestionId = examQuestionId;
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

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public List<ExamQuestionRKnowledge> getrKnowledges() {
		return rKnowledges;
	}

	public void setrKnowledges(List<ExamQuestionRKnowledge> rKnowledges) {
		this.rKnowledges = rKnowledges;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<ExamQueFillInAnswer> getFillInAnswers() {
		return fillInAnswers;
	}

	public void setFillInAnswers(List<ExamQueFillInAnswer> fillInAnswers) {
		this.fillInAnswers = fillInAnswers;
	}

	public List<ExamQuestionRChapter> getrChapters() {
		return rChapters;
	}

	public void setrChapters(List<ExamQuestionRChapter> rChapters) {
		this.rChapters = rChapters;
	}

}