package com.codyy.oc.homework.entity;

import java.util.Date;

public class WorkCommentTemplate {
    private String workCommentTemplateId;

    private String baseUserId;

    private String commentContent;

    private Date createTime;

    public String getWorkCommentTemplateId() {
        return workCommentTemplateId;
    }

    public void setWorkCommentTemplateId(String workCommentTemplateId) {
        this.workCommentTemplateId = workCommentTemplateId;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}