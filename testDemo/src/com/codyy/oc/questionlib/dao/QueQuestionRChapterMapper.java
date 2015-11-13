package com.codyy.oc.questionlib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codyy.oc.questionlib.entity.QueQuestionRChapter;

public interface QueQuestionRChapterMapper {
    int deleteByPrimaryKey(String queQuestionRChapterId);

    int insert(QueQuestionRChapter record);

    int insertSelective(QueQuestionRChapter record);

    QueQuestionRChapter selectByPrimaryKey(String queQuestionRChapterId);

    int updateByPrimaryKeySelective(QueQuestionRChapter record);

    int updateByPrimaryKey(QueQuestionRChapter record);
    
    
    //批量新增习题关联章节内容
    Integer addQuestionRChapterBatch(@Param(value="qstRChapterLi") List<QueQuestionRChapter> qstRChapterLi);
    
    //根据母题的id批量删除所有关联的章节信息
    Integer deleteQuesChapterByMotherId(String motherId);
}