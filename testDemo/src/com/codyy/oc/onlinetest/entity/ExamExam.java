package com.codyy.oc.onlinetest.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.codyy.commons.utils.MediumDateSerializer;

/**
 * 测试
 * 
 * @author zhangshuangquan
 * 
 */
public class ExamExam {
	private String examId; // 试卷ID

	private String examCategoryType; // 试卷种类类型

	private String examTypeId; // 试卷类型ID

	private String baseUserId;

	private String title;

	private Integer answerTime; // 回答时间

	private Integer score; // 分数

	@JsonSerialize(using = MediumDateSerializer.class)
	private Date createTime;

	private String baseSemesterId; // 学段ID

	private String baseSubjectId; // 学科ID

	private String baseClasslevelId; // 年级ID

	private String isDelete;

	private String examResultId; // 测试结果ID

	private String examPracticeId; // 用于巩固测试的ID

	private String year; // 年份

	private String areaName; // 真题试卷用：真题试卷所属地区

	private Integer useCount; // 使用次数
	
	private Date updateTime;//更新时间

	private String publicFlag;// 公开标志：Y/是，N/仅供学校内使用
	
	public String getPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getExamCategoryType() {
		return examCategoryType;
	}

	public void setExamCategoryType(String examCategoryType) {
		this.examCategoryType = examCategoryType;
	}

	public String getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(String examTypeId) {
		this.examTypeId = examTypeId;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBaseSemesterId() {
		return baseSemesterId;
	}

	public void setBaseSemesterId(String baseSemesterId) {
		this.baseSemesterId = baseSemesterId;
	}

	public String getBaseSubjectId() {
		return baseSubjectId;
	}

	public void setBaseSubjectId(String baseSubjectId) {
		this.baseSubjectId = baseSubjectId;
	}

	public String getBaseClasslevelId() {
		return baseClasslevelId;
	}

	public void setBaseClasslevelId(String baseClasslevelId) {
		this.baseClasslevelId = baseClasslevelId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(String examResultId) {
		this.examResultId = examResultId;
	}

	public String getExamPracticeId() {
		return examPracticeId;
	}

	public void setExamPracticeId(String examPracticeId) {
		this.examPracticeId = examPracticeId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

}