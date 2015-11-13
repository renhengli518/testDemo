package com.codyy.oc.base.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.base.entity.BaseCatalog;

public interface BaseCatalogDao {
   
	 List<BaseCatalog> getAllBaseVersionByClassLevelAndDiscipline(Map<String, String> map);
	 
	 List<BaseCatalog> getAllBaseCatalogByClassLevelAndDiscipline(Map<String, String> map);
	 
	 List<BaseCatalog> getAllBaseVersion();
	 
	 List<BaseCatalog> getAllBaseVolumeByVersion(String baseVersionId);
	 
	 List<BaseCatalog> getAllBaseChapterByVolume(String baseVolumeId);
	 
	 List<BaseCatalog> getAllBaseSectionByChapter(String baseChapterId);
	 
	 List<BaseCatalog> getAllChapterTree(String baseChapterId);
	 
	 List<BaseCatalog> getByIntelligence(Map<String, String> map);
	
}