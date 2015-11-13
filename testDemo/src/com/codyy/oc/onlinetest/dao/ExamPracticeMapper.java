package com.codyy.oc.onlinetest.dao;


import org.apache.ibatis.annotations.Param;

import com.codyy.oc.onlinetest.entity.ExamPractice;

public interface ExamPracticeMapper {
    int deleteByPrimaryKey(String examPracticeId);

    int insert(ExamPractice record);

    int insertSelective(ExamPractice record);

    ExamPractice selectByPrimaryKey(String examPracticeId);

    int updateByPrimaryKeySelective(ExamPractice record);

    int updateByPrimaryKey(ExamPractice record);

	ExamPractice getPracticeByExamResultId(String examResultId);

	int updatePracticeQuestion(@Param("answerCount") int answerCount, 
			@Param("examPracticeId") String examPracticeId,
			@Param("end") String end);

	ExamPractice getPracticeStatistics(String examPracticeId);
}