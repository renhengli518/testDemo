package com.codyy.oc.base.entity;

public class BaseChapter {
    private String baseChapterId;

    private String baseVolumeId;

    private String chapterName;

    private Integer sort;

    public String getBaseChapterId() {
        return baseChapterId;
    }

    public void setBaseChapterId(String baseChapterId) {
        this.baseChapterId = baseChapterId;
    }

    public String getBaseVolumeId() {
        return baseVolumeId;
    }

    public void setBaseVolumeId(String baseVolumeId) {
        this.baseVolumeId = baseVolumeId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}