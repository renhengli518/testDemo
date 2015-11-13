package com.codyy.oc.onlinetest.view;

import java.util.Date;

/**
 * 测试模块通用查询类
 * @author zhangshuangquan
 *
 */
public class TestSearchView {

	private Date beginTime;            //开始时间
	
	private Date endTime;              //结束时间
	
	private String subjectId;          //学科ID
	
	private String classlevelId;       //年级ID
	
	private String classId;            //班级ID
	
	private String status;             //状态
	
	private String examTypeId;         //试卷类型ID
	
	private String examName;           //试卷名称
	
	private String taskType;           //任务类型
	
	private String createUserId;	   //创建人ID
	
	private String userId;             //session中的用户id
	
	private String userType;           //session中的用户类型
	
	private String user_schoolId;      //session中用户的学校id
	
	private String user_classId;       //session中用户的班级id
	
	private Boolean isSelf;				//是否是自测组卷
	

	public Boolean getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(Boolean isSelf) {
		this.isSelf = isSelf;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getClasslevelId() {
		return classlevelId;
	}
	public void setClasslevelId(String classlevelId) {
		this.classlevelId = classlevelId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(String examTypeId) {
		this.examTypeId = examTypeId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUser_schoolId() {
		return user_schoolId;
	}
	public void setUser_schoolId(String user_schoolId) {
		this.user_schoolId = user_schoolId;
	}
	public String getUser_classId() {
		return user_classId;
	}
	public void setUser_classId(String user_classId) {
		this.user_classId = user_classId;
	}
	
	
	
	
	
	
}
