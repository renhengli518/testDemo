package com.codyy.oc.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.oc.base.dao.BaseKnowledgeDao;

@Service
public class BaseKnowledgeService {

	@Autowired
	private BaseKnowledgeDao baseKnowledgeDao;
	
	
	/**
	 * @author lichen
	* @Title: selecKnowLedgeNameById
	* @Description: TODO(根据知识点的id来获得对应的知识点)
	* @param @param baseKnowLId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String selecKnowLedgeNameById(String baseKnowLId){
		
		return baseKnowledgeDao.selecKnowLedgeNameById(baseKnowLId);
	}
	
}
