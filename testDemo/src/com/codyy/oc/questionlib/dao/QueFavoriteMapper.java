package com.codyy.oc.questionlib.dao;

import java.util.Map;

import com.codyy.oc.questionlib.entity.QueFavorite;

public interface QueFavoriteMapper {
    void deleteByPrimaryKey(String queFavoriteId);

    void insert(QueFavorite record);

    int insertSelective(QueFavorite record);

    QueFavorite selectByPrimaryKey(String queFavoriteId);

    int updateByPrimaryKeySelective(QueFavorite record);

    int updateByPrimaryKey(QueFavorite record);
    
    /**
     * 根据queQuestionId 和 baseUserId 获取收藏习题
     * 判断是否被当前用户收藏
     * @param map
     * @return
     */
    QueFavorite selectByQesIdAndUserId(Map<String,Object> map);
}