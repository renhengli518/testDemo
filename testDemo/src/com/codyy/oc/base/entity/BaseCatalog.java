package com.codyy.oc.base.entity;

import java.math.BigDecimal;

public class BaseCatalog {
    private String baseCatalogId;

    private String baseVerClasslevelDisciplineId;

    private String catalogName;

    private BigDecimal sort;

    private BigDecimal catalogLevel;

    private String parentId;

    private String classlevelName; //年级
    
    private String level;
    
    
    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

	public String getBaseCatalogId() {
        return baseCatalogId;
    }

    public void setBaseCatalogId(String baseCatalogId) {
        this.baseCatalogId = baseCatalogId;
    }

    public String getBaseVerClasslevelDisciplineId() {
        return baseVerClasslevelDisciplineId;
    }

    public void setBaseVerClasslevelDisciplineId(String baseVerClasslevelDisciplineId) {
        this.baseVerClasslevelDisciplineId = baseVerClasslevelDisciplineId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public BigDecimal getCatalogLevel() {
		return catalogLevel;
	}

	public void setCatalogLevel(BigDecimal catalogLevel) {
		this.catalogLevel = catalogLevel;
	}

	public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}