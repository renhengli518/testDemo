package com.codyy.oc.base.dao;

import java.util.List;
import java.util.Map;

import com.codyy.oc.base.entity.BaseKnowledge;

public interface BaseKnowledgeDao {
	
    List<BaseKnowledge> findKnowledgeListByIds(List<String> ids);
    
	List<BaseKnowledge> getAllBaseKnowledgeBySemesterAndDiscipline(Map<String, String> map); 
	
	List<BaseKnowledge> getAllBaseKnowledgeTreeBySemesterAndDiscipline(Map<String, String> map); 
	
	List<BaseKnowledge> getSubKnowledgeByParent(String parentId);
	
	/**
	 * @author lichen
	* @Title: selecKnowLedgeNameById
	* @Description: TODO(根据知识点的id来获取对应知识点的名字)
	* @param @param baseKnowLId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecKnowLedgeNameById(String baseKnowLId);
    
}