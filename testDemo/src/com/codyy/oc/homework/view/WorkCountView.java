package com.codyy.oc.homework.view;

import java.util.List;

import com.codyy.oc.homework.entity.ReceiveStu;
import com.codyy.oc.homework.entity.correctCount;

public class WorkCountView {
	private int stuAllCount;
	private int submitCount;//提交学生人数
	private int notSubmitCount;//未提交人数
	private int readOverCount;//已经批阅人数
	private String status;//状态
	private int allCount;//习题总数
	private int queCount;//题库中的题数
	private int objectQueCount;//客观题数
	private List<ReceiveStu> recStuList;
	private List<correctCount> correctCountList;
	private String averageCorrect;
	
	public String getAverageCorrect() {
		return averageCorrect;
	}
	public void setAverageCorrect(String averageCorrect) {
		this.averageCorrect = averageCorrect;
	}
	public int getReadOverCount() {
		return readOverCount;
	}
	public void setReadOverCount(int readOverCount) {
		this.readOverCount = readOverCount;
	}
	public List<correctCount> getCorrectCountList() {
		return correctCountList;
	}
	public void setCorrectCountList(List<correctCount> correctCountList) {
		this.correctCountList = correctCountList;
	}
	public int getSubmitCount() {
		return submitCount;
	}
	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
	}
	public int getNotSubmitCount() {
		return notSubmitCount;
	}
	public void setNotSubmitCount(int notSubmitCount) {
		this.notSubmitCount = notSubmitCount;
	}
	public int getStuAllCount() {
		return stuAllCount;
	}
	public void setStuAllCount(int stuAllCount) {
		this.stuAllCount = stuAllCount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<ReceiveStu> getRecStuList() {
		return recStuList;
	}
	public void setRecStuList(List<ReceiveStu> recStuList) {
		this.recStuList = recStuList;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getQueCount() {
		return queCount;
	}
	public void setQueCount(int queCount) {
		this.queCount = queCount;
	}
	public int getObjectQueCount() {
		return objectQueCount;
	}
	public void setObjectQueCount(int objectQueCount) {
		this.objectQueCount = objectQueCount;
	}
}
