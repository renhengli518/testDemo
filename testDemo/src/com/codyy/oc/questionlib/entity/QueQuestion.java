package com.codyy.oc.questionlib.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueQuestion {
	private String queQuestionId;

	private List<QueQuestionRChapter> rChapters = new ArrayList<QueQuestionRChapter>();/* 获取关联章节列表 */

	private List<QueQuestionRKnowledge> rKnowledges = new ArrayList<QueQuestionRKnowledge>();/* 获取关联知识点ID */

	private List<QueFillInAnswer> fillInAnswers = new ArrayList<QueFillInAnswer>();/* 对应关联的填空题答案 */

	private String baseSemesterId;
	
	private String fillAnstwerStr;//填空答案字符串  传到前台

	private String baseClasslevelId;

	private String baseSubjectId;

	private String baseUserId;

	private String contentVideo;

	private String answer;

	private String difficulty;

	private String type;

	private String resolveVideo;

	private Date createTime;

	private Date updateTime;

	private String fillInAnswerType;

	private String fillInScoreType;

	private Integer useCount;

	private String relationalIndicator;

	private String motherId;

	private String usableType;

	private String source;

	private String auditStatus;//审核状态

	private String storeServer;

	private String content;

	private String options;

	private String resolve;

	private String optionsTxt;

	private String endKonwledgeNames;// 冗余一个题库关联的知识点（多个用逗号分隔）

	private String queFavoriteId;// 冗余 收藏ID
	
	private String clsSchoolId;
	
	private String areaId;
	
	private Integer score;//分数，导入试卷用
	
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getClsSchoolId() {
		return clsSchoolId;
	}

	public void setClsSchoolId(String clsSchoolId) {
		this.clsSchoolId = clsSchoolId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public List<QueFillInAnswer> getFillInAnswers() {
		return fillInAnswers;
	}

	public void setFillInAnswers(List<QueFillInAnswer> fillInAnswers) {
		this.fillInAnswers = fillInAnswers;
	}

	public String getQueFavoriteId() {
		return queFavoriteId;
	}

	public void setQueFavoriteId(String queFavoriteId) {
		this.queFavoriteId = queFavoriteId;
	}

	public String getEndKonwledgeNames() {
		return endKonwledgeNames;
	}

	public void setEndKonwledgeNames(String endKonwledgeNames) {
		this.endKonwledgeNames = endKonwledgeNames;
	}

	public String getQueQuestionId() {
		return queQuestionId;
	}

	public void setQueQuestionId(String queQuestionId) {
		this.queQuestionId = queQuestionId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
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

	public String getUsableType() {
		return usableType;
	}

	public void setUsableType(String usableType) {
		this.usableType = usableType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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

	public List<QueQuestionRChapter> getrChapters() {
		return rChapters;
	}

	public void setrChapters(List<QueQuestionRChapter> rChapters) {
		this.rChapters = rChapters;
	}

	public List<QueQuestionRKnowledge> getrKnowledges() {
		return rKnowledges;
	}

	public void setrKnowledges(List<QueQuestionRKnowledge> rKnowledges) {
		this.rKnowledges = rKnowledges;
	}

	

	public String getFillAnstwerStr() {
		return fillAnstwerStr;
	}

	public void setFillAnstwerStr(String fillAnstwerStr) {
		this.fillAnstwerStr = fillAnstwerStr;
	}

	@Override
	public String toString() {
		return "QueQuestion [queQuestionId=" + queQuestionId + ", rChapters=" + rChapters + ", rKnowledges=" + rKnowledges
				+ ", fillInAnswers=" + fillInAnswers + ", baseSemesterId=" + baseSemesterId + ", baseClasslevelId=" + baseClasslevelId
				+ ", baseSubjectId=" + baseSubjectId + ", baseUserId=" + baseUserId + ", contentVideo=" + contentVideo + ", answer="
				+ answer + ", difficulty=" + difficulty + ", type=" + type + ", resolveVideo=" + resolveVideo + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", fillInAnswerType=" + fillInAnswerType + ", fillInScoreType="
				+ fillInScoreType + ", useCount=" + useCount + ", relationalIndicator=" + relationalIndicator + ", motherId=" + motherId
				+ ", usableType=" + usableType + ", source=" + source + ", auditStatus=" + auditStatus + ", storeServer=" + storeServer
				+ ", content=" + content + ", options=" + options + ", resolve=" + resolve + ", optionsTxt=" + optionsTxt + ", toString()="
				+ super.toString() + "]";
	}

}