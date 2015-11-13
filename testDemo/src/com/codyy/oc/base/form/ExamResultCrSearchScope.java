package com.codyy.oc.base.form;

import java.util.List;


public class ExamResultCrSearchScope  {
	private String classRoomName;
	private String examTaskId;
//	private String studentIds;
	private String studentName;
	private List<String> studentIds;

	//分页起始索引
	 private Integer start;
	 
	 private Integer end;
	
	public String getClassRoomName() {
		return classRoomName;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public String getExamTaskId() {
		return examTaskId;
	}

	public void setExamTaskId(String examTaskId) {
		this.examTaskId = examTaskId;
	}

	public List<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}
