package com.codyy.oc.base.view;

import com.codyy.oc.base.entity.BaseClasslevelSubject;


public class BaseClslevelSubjectView extends BaseClasslevelSubject {
	
	private String classlevelName;
	
	private String subjectName;

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
