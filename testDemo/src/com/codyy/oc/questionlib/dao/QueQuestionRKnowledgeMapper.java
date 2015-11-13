package com.codyy.oc.questionlib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;

public interface QueQuestionRKnowledgeMapper {
    int deleteByPrimaryKey(String queQuestionRKnowledgeId);

    int insert(QueQuestionRKnowledge record);

    int insertSelective(QueQuestionRKnowledge record);

    QueQuestionRKnowledge selectByPrimaryKey(String queQuestionRKnowledgeId);

    int updateByPrimaryKeySelective(QueQuestionRKnowledge record);

    int updateByPrimaryKey(QueQuestionRKnowledge record);
    
    /**
     * 批量插入习题与知识点的关系
     * 批量新增习题关联知识点内容
     * @param qstRKnowlegeLi
     * @return
     */
    Integer addQuestionRKnowledgeBatch(@Param(value="qstRKnowlegeLi") List<QueQuestionRKnowledge> qstRKnowlegeLi);
    
    /**
     * 根据questionID 获取 题目关联的知识点
     * @param queQuestionId
     * @return
     */
    List<String> getEndKnowledgeNames(String queQuestionId);
    
    List<QueQuestionRKnowledge> selectByQuestion(String queQuestionId);
    
    /**
     * @author lichen
    * @Title: deleteKnowListByMotherId
    * @Description: TODO(根据母题id来删除所有关联的知识点)
    * @param @param motherId
    * @param @return    设定文件
    * @return Integer    返回类型
    * @throws
     */
    Integer deleteKnowListByMotherId(String motherId);
}