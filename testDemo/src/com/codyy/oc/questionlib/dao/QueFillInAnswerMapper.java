package com.codyy.oc.questionlib.dao;

import java.util.List;

import com.codyy.oc.questionlib.entity.QueFillInAnswer;

public interface QueFillInAnswerMapper {
    int deleteByPrimaryKey(String queFillInAnswerId);

    int insert(QueFillInAnswer record);

    int insertSelective(QueFillInAnswer record);

    QueFillInAnswer selectByPrimaryKey(String queFillInAnswerId);

    int updateByPrimaryKeySelective(QueFillInAnswer record);

    int updateByPrimaryKeyWithBLOBs(QueFillInAnswer record);

    int updateByPrimaryKey(QueFillInAnswer record);
    
    
    /**
     * @author lichen
    * @Title: addQuestionFillInAnswer
    * @Description: TODO(插入单个填空题答案)
    * @param @param qstFillInAnswer
    * @param @return    设定文件
    * @return Integer    返回类型
    * @throws
     */
    Integer addQuestionFillInAnswer(QueFillInAnswer qstFillInAnswer);
    
    /**
     * @author lichen
    * @Title: updateQuestionFillInAnswer
    * @Description: TODO(修改单个填空题答案)
    * @param @param questionId
    * @param @return    设定文件
    * @return Integer    返回类型
    * @throws
     */
 /*   Integer updateQuestionFillInAnswer(String questionId);*/
    
    /**
     * @author lichen
    * @Title: deleFillInAnswerById
    * @Description: TODO(删除对应习题的所有填空题内容)
    * @param @param questionId
    * @param @return    设定文件
    * @return Integer    返回类型
    * @throws
     */
    Integer deleFillInAnswerById(String questionId);
    
    List<QueFillInAnswer> selectByQuestion(String queQuestionId);
}