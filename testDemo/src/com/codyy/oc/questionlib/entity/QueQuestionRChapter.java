package com.codyy.oc.questionlib.entity;

public class QueQuestionRChapter {
    private String queQuestionRChapterId;

    private String queQuestionId;

    private String baseVersionId;

    private String baseSectionId;

    private String baseChapterId;

    private String baseVolumeId;

    public String getQueQuestionRChapterId() {
        return queQuestionRChapterId;
    }

    public void setQueQuestionRChapterId(String queQuestionRChapterId) {
        this.queQuestionRChapterId = queQuestionRChapterId;
    }

    public String getQueQuestionId() {
        return queQuestionId;
    }

    public void setQueQuestionId(String queQuestionId) {
        this.queQuestionId = queQuestionId;
    }

    public String getBaseVersionId() {
        return baseVersionId;
    }

    public void setBaseVersionId(String baseVersionId) {
        this.baseVersionId = baseVersionId;
    }

    public String getBaseSectionId() {
        return baseSectionId;
    }

    public void setBaseSectionId(String baseSectionId) {
        this.baseSectionId = baseSectionId;
    }

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

	@Override
	public String toString() {
		return "QueQuestionRChapter [queQuestionRChapterId="
				+ queQuestionRChapterId + ", queQuestionId=" + queQuestionId
				+ ", baseVersionId=" + baseVersionId + ", baseSectionId="
				+ baseSectionId + ", baseChapterId=" + baseChapterId
				+ ", baseVolumeId=" + baseVolumeId + ", toString()="
				+ super.toString() + "]";
	}
    
    
}