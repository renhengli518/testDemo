package com.codyy.oc.homework.entity;

import java.util.List;
import java.util.Map;


public class StuComment {
	private String homeWorkId;
	private String homeworkReceiverId;
	private List<Map<String,String>> ExercisesCommonets;
	private String homeworkTextCommonet;
	private String homeworkAnnexCommonet;
	private String homeworkGeneralCommonet;
	public String getHomeWorkId() {
		return homeWorkId;
	}
	public void setHomeWorkId(String homeWorkId) {
		this.homeWorkId = homeWorkId;
	}
	public List<Map<String, String>> getExercisesCommonets() {
		return ExercisesCommonets;
	}
	public void setExercisesCommonets(List<Map<String, String>> exercisesCommonets) {
		ExercisesCommonets = exercisesCommonets;
	}
	public String getHomeworkReceiverId() {
		return homeworkReceiverId;
	}
	public void setHomeworkReceiverId(String homeworkReceiverId) {
		this.homeworkReceiverId = homeworkReceiverId;
	}
	public String getHomeworkTextCommonet() {
		return homeworkTextCommonet;
	}
	public void setHomeworkTextCommonet(String homeworkTextCommonet) {
		this.homeworkTextCommonet = homeworkTextCommonet;
	}
	public String getHomeworkAnnexCommonet() {
		return homeworkAnnexCommonet;
	}
	public void setHomeworkAnnexCommonet(String homeworkAnnexCommonet) {
		this.homeworkAnnexCommonet = homeworkAnnexCommonet;
	}
	public String getHomeworkGeneralCommonet() {
		return homeworkGeneralCommonet;
	}
	public void setHomeworkGeneralCommonet(String homeworkGeneralCommonet) {
		this.homeworkGeneralCommonet = homeworkGeneralCommonet;
	}
	
	
}
