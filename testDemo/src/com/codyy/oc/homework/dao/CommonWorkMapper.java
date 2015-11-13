package com.codyy.oc.homework.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.homework.entity.QuestionKnowLedge;
import com.codyy.oc.homework.entity.ReadOverUser;
import com.codyy.oc.homework.entity.Resolve;


public interface CommonWorkMapper {
	//查询习题解析
	public Resolve findQueResolve(String queId) ;
	//查询习题的知识点
	public List<QuestionKnowLedge> getQuestionKnowLedge(String queId);
	//查询作业的批阅人
	public ReadOverUser findUserType(Map<String,String> map);
	
}
