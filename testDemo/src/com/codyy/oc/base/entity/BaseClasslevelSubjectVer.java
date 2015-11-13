package com.codyy.oc.base.entity;

public class BaseClasslevelSubjectVer {
    private String baseClasslevelSubjectVerId;

    private String baseClasslevelSubjectId;

    private String baseVersionId;

    private Integer sort;
    
    /* 以下为冗余字段*/
    private String versionName;

    public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getBaseClasslevelSubjectVerId() {
        return baseClasslevelSubjectVerId;
    }

    public void setBaseClasslevelSubjectVerId(String baseClasslevelSubjectVerId) {
        this.baseClasslevelSubjectVerId = baseClasslevelSubjectVerId;
    }

    public String getBaseClasslevelSubjectId() {
        return baseClasslevelSubjectId;
    }

    public void setBaseClasslevelSubjectId(String baseClasslevelSubjectId) {
        this.baseClasslevelSubjectId = baseClasslevelSubjectId;
    }

    public String getBaseVersionId() {
        return baseVersionId;
    }

    public void setBaseVersionId(String baseVersionId) {
        this.baseVersionId = baseVersionId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}