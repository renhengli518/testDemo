package com.codyy.oc.base.form;

public class QuestionFormFields implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String questionId = "";
	
	private String semesterOpts = "";
	
	private String classlevelOpts = "";

	private String disciplineOpts = "";
	
	private String difficultyOpts = "";
	
	private String questionTypeOpts = "";
	
	private String edt_content = "";
	
	private String edt_resolve = "";
	
	private String resolveVideo = "";
	
	private String videoOrgName = "";
	
	private String rdo_singleOpt = "";
	
	private String[] chk_multOpt;

	private String rdo_answerType = "";
	
	private String rdo_scoreType  = "";

	private String ch_input  = "";/*关联章节以下划线分开*/
	
	private String kn_input = "";/*关联知识点以下划线分开*/
	
	private String optCnt = "0";/*选项个数*/
	
	private String fillInAnswerCnt = "0"; /*填空个数*/
	
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
	
	public String getFillInAnswerCnt() {
		return fillInAnswerCnt;
	}

	public void setFillInAnswerCnt(String fillInAnswerCnt) {
		this.fillInAnswerCnt = fillInAnswerCnt;
	}

	public String getOptCnt() {
		return optCnt;
	}

	public void setOptCnt(String optCnt) {
		this.optCnt = optCnt;
	}

	public String getCh_input() {
		return ch_input;
	}

	public void setCh_input(String ch_input) {
		this.ch_input = ch_input;
	}

	public String getKn_input() {
		return kn_input;
	}

	public void setKn_input(String kn_input) {
		this.kn_input = kn_input;
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

}
