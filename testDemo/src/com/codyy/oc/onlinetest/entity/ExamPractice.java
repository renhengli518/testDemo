package com.codyy.oc.onlinetest.entity;

import java.util.Date;
/**
 * 巩固测试
 * @author zhangshuangquan
 *
 */
public class ExamPractice {
	
	public static final String INIT ="INIT";  //未开始
	
    public static final String END ="END";  //已结束
	
    private String examPracticeId;

    private Integer answerTime;

    private Integer score;      //总分数

    private String status;      //状态: 未开始/INIT, 进行中/PROGRESS, 已结束/END

    private Integer myScore;    //我的得分

    private Integer questionCount;     //题目数

    private Integer answerCount;       //回答数

    private Integer correctCount;      //答对数

    private Date createTime;

    private String examResultId;

    private String baseSemesterId;

    private String baseClasslevelId;

    private String baseSubjectId;
    
    private String rightRate;   //正确率  （客观题）
    
    private String mistakeCnt;  //错题数  （客观题）
    

    public String getExamPracticeId() {
        return examPracticeId;
    }

    public void setExamPracticeId(String examPracticeId) {
        this.examPracticeId = examPracticeId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExamResultId() {
        return examResultId;
    }

    public void setExamResultId(String examResultId) {
        this.examResultId = examResultId;
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

	public String getRightRate() {
		return rightRate;
	}

	public void setRightRate(String rightRate) {
		this.rightRate = rightRate;
	}

	public String getMistakeCnt() {
		return mistakeCnt;
	}

	public void setMistakeCnt(String mistakeCnt) {
		this.mistakeCnt = mistakeCnt;
	}
	
}