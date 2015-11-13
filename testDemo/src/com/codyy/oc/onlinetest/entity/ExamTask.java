package com.codyy.oc.onlinetest.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.codyy.commons.utils.MediumDateSerializer;
/**
 * 测试任务
 * @author zhangshuangquan
 *
 */
public class ExamTask {
    private String examTaskId;        //测试任务ID

    private String examId;

    private String baseUserId;         //布置人

    private String taskType;           //任务类型：SELF/自测，ASSIGN/老师布置
    
    @JsonSerialize(using=MediumDateSerializer.class)
    private Date startTime;            //开始时间
    
    @JsonSerialize(using=MediumDateSerializer.class)
    private Date finishedTime;         //结束时间

    @JsonSerialize(using=MediumDateSerializer.class)
    private Date createTime;

    private Integer finishedCount;      //完成数

    private Integer assignedCount;      //分派数
    
    private Integer readOverCount;      //批阅数
    
    private String examTypeId; // 试卷类型ID

	private String title;        //试卷名称

	private String answerTime; // 回答时间

	private String score;      // 分数
    
    public String getExamTaskId() {
        return examTaskId;
    }

    public void setExamTaskId(String examTaskId) {
        this.examTaskId = examTaskId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
    }

    public Integer getAssignedCount() {
        return assignedCount;
    }

    public void setAssignedCount(Integer assignedCount) {
        this.assignedCount = assignedCount;
    }

	public Integer getReadOverCount() {
		return readOverCount;
	}

	public void setReadOverCount(Integer readOverCount) {
		this.readOverCount = readOverCount;
	}

	public String getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(String examTypeId) {
		this.examTypeId = examTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}