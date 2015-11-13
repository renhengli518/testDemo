package com.codyy.oc.base.form;

import org.apache.commons.lang.StringUtils;


public class ExamListCriteria {
	private int currentpage;//
	private String examTitle;//试卷标题
	private String examType;//考试类型
	private String semester;//学段
	private String discipline;//学科
	private String startTime;//开始时间
	private String endTime;//结束时间
	
	private String teacherId;//老师ID
	private String studentId;//学生ID
	
	
	//分页起始索引
	 private Integer start;
	 
	 private Integer end;
	 
	 private String status;//状态
	 
	 
	 public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
public Integer getStart() {
		return start;
	}
	public Integer getEnd() {
		return end;
	}
	//	public Integer getStart() {
//		return 10*(currentpage);
//	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
//	public Integer getEnd() {
//		return getStart()+10;
//	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getExamTitle() {
		return examTitle;
	}
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public enum ExamTypeEnum {
		student("-1", "自测组卷"),practice("-2","巩固测试");

		private ExamTypeEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static ExamTypeEnum getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(ExamTypeEnum p : ExamTypeEnum.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}
	
	public enum ExamCategoryTypeEnum {
		classlevel("CLASSLEVEL", "年级统考卷"),claz("CLASS","班级测试"),real("REAL","真题试卷"),consolidation("CONSOLIDATION","班级测试"),self("SELF","自主测试");

		private ExamCategoryTypeEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static ExamTypeEnum getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(ExamTypeEnum p : ExamTypeEnum.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}
	
	
	
	
}
