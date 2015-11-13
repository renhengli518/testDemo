package com.codyy.oc.onlinetest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.codyy.oc.base.entity.BaseUser;
import com.codyy.oc.onlinetest.entity.ExamResult;

public interface ExamResultMapper {
	int deleteByPrimaryKey(String examResultId);

	int insert(ExamResult record);

	int insertSelective(ExamResult record);

	ExamResult selectByPrimaryKey(String examResultId);

	int updateByPrimaryKeySelective(ExamResult record);

	int updateByPrimaryKey(ExamResult record);

	void insertBatch(@Param("stuIds") List<String> stuIds, @Param("examId") String examId, @Param("examTaskId") String examTaskId);

	void updateExamResultById(ExamResult examResult);

	List<BaseUser> getReviewStudentList(Map<String, String> map);

	ExamResult getExamResultStatus(String examTaskId);
}