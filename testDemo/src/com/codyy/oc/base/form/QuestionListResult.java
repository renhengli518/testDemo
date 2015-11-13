package com.codyy.oc.base.form;

import java.util.List;

import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.questionlib.entity.QueQuestion;

public class QuestionListResult extends QueQuestion {

	private String knowledgeEndNames;/* 所有关联末节知识点名称 */

	private String knowledgeEndIds;/* 所有关联末节知识点ID */

	private List<BaseKnowledge> knowledgeEndList;/* 所有关联末节知识点列表 */

	private List<String> knowledgeIdStr;/* 所有关联知识点ID列表（总共六级） */

	private List<String> knowledgeNameStr;/* 所有关联知识点名字列表（总共六级） */

	private List<String> knowledgeIdAndNameStr;/* 所有关联知识点ID和名称组合 用||分开 */

	private List<String> chapterIdStr;/* 所有关联章节ID列表（总共四级） */

	private List<String> chapterNameStr;/* 所有关联章节名字列表（总共四级） */

	private List<String> chapterIdAndNameStr;/* 所有关联章节ID和名称组合 用||分开 */

	private String examTimes;// 组卷次数

	private List<QueQuestion> childrenQuestionList;// 子题列表（孪生题、衍生题）

	public List<QueQuestion> getChildrenQuestionList() {
		return childrenQuestionList;
	}

	public void setChildrenQuestionList(List<QueQuestion> childrenQuestionList) {
		this.childrenQuestionList = childrenQuestionList;
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

	private String examFormedCnt;/* 组卷次数 */

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

}
