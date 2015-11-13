package com.codyy.oc.questionlib.form;

public class QuestionFormFields implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private QuestionFormFields questionSubArra[];// 声明本数组对象来依次封装提交的同类型的

	public QuestionFormFields[] getQuestionSubArra() {
		return questionSubArra;
	}

	public void setQuestionSubArra(QuestionFormFields[] questionSubArra) {
		this.questionSubArra = questionSubArra;
	}

	private String currentQuestionId;// 修改时当前的习题id
	private String currentQuestionIdMotherId;// 修改时当前习题的母题id

	private String questionId = "";// 习题id

	private String[] opt_title;// 选项的名字

	private String[] content_title;// 选项的内容

	private String semesterOpts = "";// 学段

	private String classlevelOpts = "";// 年级

	private String disciplineOpts = "";// 学科

	private String difficultyOpts = "";// 难易程度

	private String questionTypeOpts = "";// 习题类型

	private String edt_content = "";// 解析题干内容

	private String edt_resolve = "";// 解析文本内容

	private String resolveVideo = "";// 解析视频路径
	private String contentVideo = "";// 题干音视频路径

	private String videoOrgName = "";

	private String rdo_singleOpt = "";// 单选

	private String[] chk_multOpt;// 复选

	private String edt_options;// 习题选项（多个以特殊字符分隔）

	private String rdo_answerType = "";// 填空题答案类型，独立答案/INDEPENDENT,
										// 组合答案/COMBINATION

	private String rdo_scoreType = "";// 填空题给分类型，全对给分/ALL_CORRECT,
										// 按空给分/SCORE_BY_FILL

	private String[] ch_input;/* 关联的章节对象 每个对象是一个数组的元素 */

	private String ch_inputs;// 导入习题的章节字段

	public String getCh_inputs() {
		return ch_inputs;
	}

	public void setCh_inputs(String ch_inputs) {
		this.ch_inputs = ch_inputs;
	}

	private String[] k_input;// 关联每个知识点的数组

	private String k_inputs;// 导入习题时候进行的知识点拼接的字段

	public String getK_inputs() {
		return k_inputs;
	}

	public void setK_inputs(String k_inputs) {
		this.k_inputs = k_inputs;
	}

	private String optCnt = "0";/* 选项个数 */

	// 填空题的空数(即生成的表格一共有空) 存放所有题目的空的个数 长度表示题目的个数

	private String fillInAnswerCnt;

	private String deleteFlagStr;// 获得已经删除的题目下标组成的字符串(以逗号隔开)

	private String usableType;// 权限类型(分享到平台还是学校)

	private String relationalIndicator;// 习题的关联类型(母题或衍生题或子题)

	private String source;// 手动输入题的来源

	private String motherId;// 母题id

	private String auditStatus;// 审核状态 INIT/未审核, PASSED/已通过, REJECTED/未通过

	private String questionSubTypeOpts;// 子题的类型 如果为空则是母题 孪生题和衍生题

	private String examId;// 试卷Id

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getDeleteFlagStr() {
		return deleteFlagStr;
	}

	public void setDeleteFlagStr(String deleteFlagStr) {
		this.deleteFlagStr = deleteFlagStr;
	}

	public String getQuestionSubTypeOpts() {
		return questionSubTypeOpts;
	}

	public void setQuestionSubTypeOpts(String questionSubTypeOpts) {
		this.questionSubTypeOpts = questionSubTypeOpts;
	}

	public String[] getOpt_title() {
		return opt_title;
	}

	public void setOpt_title(String[] opt_title) {
		this.opt_title = opt_title;
	}

	public String[] getContent_title() {
		return content_title;
	}

	public void setContent_title(String[] content_title) {
		this.content_title = content_title;
	}

	public String getEdt_options() {
		return edt_options;
	}

	public void setEdt_options(String edt_options) {
		this.edt_options = edt_options;
	}

	public String getUsableType() {
		return usableType;
	}

	public void setUsableType(String usableType) {
		this.usableType = usableType;
	}

	public String getRelationalIndicator() {
		return relationalIndicator;
	}

	public void setRelationalIndicator(String relationalIndicator) {
		this.relationalIndicator = relationalIndicator;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRdo_singleOpt() {
		return rdo_singleOpt;
	}

	public void setRdo_singleOpt(String rdo_singleOpt) {
		this.rdo_singleOpt = rdo_singleOpt;
	}

	public String[] getChk_multOpt() {
		return chk_multOpt;
	}

	public void setChk_multOpt(String[] chk_multOpt) {
		this.chk_multOpt = chk_multOpt;
	}

	public String getRdo_answerType() {
		return rdo_answerType;
	}

	public void setRdo_answerType(String rdo_answerType) {
		this.rdo_answerType = rdo_answerType;
	}

	public String getRdo_scoreType() {
		return rdo_scoreType;
	}

	public void setRdo_scoreType(String rdo_scoreType) {
		this.rdo_scoreType = rdo_scoreType;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getOptCnt() {
		return optCnt;
	}

	public void setOptCnt(String optCnt) {
		this.optCnt = optCnt;
	}

	public String getSemesterOpts() {
		return semesterOpts;
	}

	public void setSemesterOpts(String semesterOpts) {
		this.semesterOpts = semesterOpts;
	}

	public String getClasslevelOpts() {
		return classlevelOpts;
	}

	public void setClasslevelOpts(String classlevelOpts) {
		this.classlevelOpts = classlevelOpts;
	}

	public String getDisciplineOpts() {
		return disciplineOpts;
	}

	public void setDisciplineOpts(String disciplineOpts) {
		this.disciplineOpts = disciplineOpts;
	}

	public String getDifficultyOpts() {
		return difficultyOpts;
	}

	public void setDifficultyOpts(String difficultyOpts) {
		this.difficultyOpts = difficultyOpts;
	}

	public String getEdt_resolve() {
		return edt_resolve;
	}

	public void setEdt_resolve(String edt_resolve) {
		this.edt_resolve = edt_resolve;
	}

	public String getResolveVideo() {
		return resolveVideo;
	}

	public String getVideoOrgName() {
		return videoOrgName;
	}

	public void setVideoOrgName(String videoOrgName) {
		this.videoOrgName = videoOrgName;
	}

	public void setResolveVideo(String resolveVideo) {
		this.resolveVideo = resolveVideo;
	}

	public String getQuestionTypeOpts() {
		return questionTypeOpts;
	}

	public String getEdt_content() {
		return edt_content;
	}

	public void setEdt_content(String edt_content) {
		this.edt_content = edt_content;
	}

	public void setQuestionTypeOpts(String questionTypeOpts) {
		this.questionTypeOpts = questionTypeOpts;
	}

	public String[] getCh_input() {
		return ch_input;
	}

	public void setCh_input(String[] ch_input) {
		this.ch_input = ch_input;
	}

	public String[] getK_input() {
		return k_input;
	}

	public void setK_input(String[] k_input) {
		this.k_input = k_input;
	}

	public String getFillInAnswerCnt() {
		return fillInAnswerCnt;
	}

	public void setFillInAnswerCnt(String fillInAnswerCnt) {
		this.fillInAnswerCnt = fillInAnswerCnt;
	}

	public String getCurrentQuestionId() {
		return currentQuestionId;
	}

	public void setCurrentQuestionId(String currentQuestionId) {
		this.currentQuestionId = currentQuestionId;
	}

	public String getCurrentQuestionIdMotherId() {
		return currentQuestionIdMotherId;
	}

	public void setCurrentQuestionIdMotherId(String currentQuestionIdMotherId) {
		this.currentQuestionIdMotherId = currentQuestionIdMotherId;
	}

	public String getContentVideo() {
		return contentVideo;
	}

	public void setContentVideo(String contentVideo) {
		this.contentVideo = contentVideo;
	}

}
