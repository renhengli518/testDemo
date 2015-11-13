package com.codyy.oc.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codyy.oc.base.entity.BaseSemester;


public interface BaseSemesterDao {

	BaseSemester selectByPrimaryKey(String baseSemesterId);
	
	List<BaseSemester> getAllBaseSemesterExam(String teacherId);
	
	List<BaseSemester> getAllBaseSemester();

	//根据id获取名称
	String selectNameById(String baseSemesterId);
	
	 //根据文字获取年级学段学科id信息
    List<String> getscdIds(@Param("semester") String semester,@Param("classlevel") String classlevel,@Param("discipline") String discipline);
    
    
    
    //获取章节ID信息
    List<String> getchapterIds(@Param("classlevelId") String classlevelId,@Param("disciplineId") String disciplineId,@Param("version") String version,@Param("one") String one,@Param("two") String two,@Param("three") String three);
    
    
    
    //获取知识点信息
    String getKnowledgeid(@Param("semesterId") String semesterId,@Param("disciplineId") String disciplineId,@Param("name") String name,@Param("pid") String pid);
    
}