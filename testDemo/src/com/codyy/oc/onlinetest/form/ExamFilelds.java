package com.codyy.oc.onlinetest.form;

import com.codyy.oc.onlinetest.entity.ExamExam;

public class ExamFilelds extends ExamExam {
	private String examQueList;// 由多个examQuestionId 和 score拼接成的字符串

	private String saveOrEditType;// 用来区分是编辑试卷保存还是新建试卷保存

	public String getSaveOrEditType() {
		return saveOrEditType;
	}

	public void setSaveOrEditType(String saveOrEditType) {
		this.saveOrEditType = saveOrEditType;
	}

	public String getExamQueList() {
		return examQueList;
	}

	public void setExamQueList(String examQueList) {
		this.examQueList = examQueList;
	}

}
