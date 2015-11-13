package com.codyy.oc.homework.entity;

import java.util.Date;

public class WorkReceiveDoc {
    private String workDocId;

    private String workReceiveStuId;

    private String docName;

    private String docPath;

    private Integer docSize;

    private Date createTime;

    public String getWorkDocId() {
        return workDocId;
    }

    public void setWorkDocId(String workDocId) {
        this.workDocId = workDocId;
    }

    public String getWorkReceiveStuId() {
        return workReceiveStuId;
    }

    public void setWorkReceiveStuId(String workReceiveStuId) {
        this.workReceiveStuId = workReceiveStuId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Integer getDocSize() {
        return docSize;
    }

    public void setDocSize(Integer docSize) {
        this.docSize = docSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}