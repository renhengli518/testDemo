package com.codyy.oc.onlinetest.view;

public class ExamTaskSortView {
	
	private String examTaskId;
	
	private String classId;
	
	private String nameSort;  //按姓名排序
	
	private String scoreSort;  //按成绩排序
	
	private String numSort;    //按答题数排序
	
	private String rightSort;  //按正确率排序

	public String getExamTaskId() {
		return examTaskId;
	}

	public void setExamTaskId(String examTaskId) {
		this.examTaskId = examTaskId;
	}
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getNameSort() {
		return nameSort;
	}

	public void setNameSort(String nameSort) {
		this.nameSort = nameSort;
	}

	public String getScoreSort() {
		return scoreSort;
	}

	public void setScoreSort(String scoreSort) {
		this.scoreSort = scoreSort;
	}

	public String getNumSort() {
		return numSort;
	}

	public void setNumSort(String numSort) {
		this.numSort = numSort;
	}

	public String getRightSort() {
		return rightSort;
	}

	public void setRightSort(String rightSort) {
		this.rightSort = rightSort;
	}
}
