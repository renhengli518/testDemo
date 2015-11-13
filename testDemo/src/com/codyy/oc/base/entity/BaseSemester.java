package com.codyy.oc.base.entity;

import java.math.BigDecimal;

public class BaseSemester {
   
	private String baseSemesterId;

    private String semesterName;

    private BigDecimal sort;

    public String getBaseSemesterId() {
        return baseSemesterId;
    }

    public void setBaseSemesterId(String baseSemesterId) {
        this.baseSemesterId = baseSemesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }
}