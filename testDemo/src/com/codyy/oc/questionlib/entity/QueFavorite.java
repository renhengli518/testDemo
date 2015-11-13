package com.codyy.oc.questionlib.entity;

import java.util.Date;

public class QueFavorite {
    private String queFavoriteId;

    private String queQuestionId;

    private String baseUserId;

    private Date createTime;

    public String getQueFavoriteId() {
        return queFavoriteId;
    }

    public void setQueFavoriteId(String queFavoriteId) {
        this.queFavoriteId = queFavoriteId;
    }

    public String getQueQuestionId() {
        return queQuestionId;
    }

    public void setQueQuestionId(String queQuestionId) {
        this.queQuestionId = queQuestionId;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}