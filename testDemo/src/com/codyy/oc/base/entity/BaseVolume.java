package com.codyy.oc.base.entity;

public class BaseVolume {
    private String baseVolumeId;

    private String baseClasslevelSubjectVerId;

    private String volumeName;

    private Integer sort;

    public String getBaseVolumeId() {
        return baseVolumeId;
    }

    public void setBaseVolumeId(String baseVolumeId) {
        this.baseVolumeId = baseVolumeId;
    }

    public String getBaseClasslevelSubjectVerId() {
        return baseClasslevelSubjectVerId;
    }

    public void setBaseClasslevelSubjectVerId(String baseClasslevelSubjectVerId) {
        this.baseClasslevelSubjectVerId = baseClasslevelSubjectVerId;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}