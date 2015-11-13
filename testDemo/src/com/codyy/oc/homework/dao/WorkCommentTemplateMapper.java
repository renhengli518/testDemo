package com.codyy.oc.homework.dao;

import java.util.List;

import com.codyy.oc.homework.entity.WorkCommentTemplate;

public interface WorkCommentTemplateMapper {
    int deleteByPrimaryKey(String workCommentTemplateId);

    int insert(WorkCommentTemplate record);

    int insertSelective(WorkCommentTemplate record);

    WorkCommentTemplate selectByPrimaryKey(String workCommentTemplateId);

    int updateByPrimaryKeySelective(WorkCommentTemplate record);

    int updateByPrimaryKey(WorkCommentTemplate record);
    
    List<WorkCommentTemplate> selectByUserId(String userId);
}