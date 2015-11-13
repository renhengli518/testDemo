package com.codyy.oc.questionlib.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.page.Page;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.base.form.QuestionListResult;
import com.codyy.oc.questionlib.dao.QueFavoriteMapper;
import com.codyy.oc.questionlib.dao.QueQuestionMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRKnowledgeMapper;
import com.codyy.oc.questionlib.entity.QueFavorite;

@Service
public class QueFavoriteService {
	@Autowired
	private QueFavoriteMapper queFavoriteMapper;
	
	@Autowired
	private QueQuestionMapper queQuestionMapper;
	
	@Autowired
	private QueQuestionRKnowledgeMapper queQuestionRKnowledgeMapper;

	/**
	 * 取消收藏
	 * 
	 * @param queFavoriteId
	 * @return
	 */
	public void deleteByPrimaryKey(String queFavoriteId) {
		queFavoriteMapper.deleteByPrimaryKey(queFavoriteId);
	}

	/**
	 * 添加收藏
	 * 
	 * @param record
	 * @return
	 * @throws Exception 
	 */
	public QueFavorite insert(String queQuestionId, String baseUserId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queQuestionId", queQuestionId);
		map.put("baseUserId", baseUserId);
		QueFavorite dto = queFavoriteMapper.selectByQesIdAndUserId(map);
		if(dto!=null){
			throw new Exception("已经收藏该题");
		}
		QueFavorite record = new QueFavorite();
		record.setBaseUserId(baseUserId);
		record.setCreateTime(new Date());
		record.setQueFavoriteId(UUIDUtils.getUUID());
		record.setQueQuestionId(queQuestionId);
		queFavoriteMapper.insert(record);
		return record;
	}
	
	/**
	 * 查询收藏列表
	 * @param page
	 * @return
	 */
	public Page getFavoriteQuePageList(Page page){
		List<QuestionListResult> data = queQuestionMapper.getFavoriteQuePageList(page);
		if (data.size() > 0) {
			for (QuestionListResult dto : data) {
				List<String> endKonwledgeNames = queQuestionRKnowledgeMapper.getEndKnowledgeNames(dto.getQueQuestionId());
				String str = "";
				if (endKonwledgeNames.size() > 0) {
					for (int i = 0; i < endKonwledgeNames.size(); i++) {
						if (i != endKonwledgeNames.size() - 1) {
							str += endKonwledgeNames.get(i) + ",";
						} else {
							str += endKonwledgeNames.get(i);
						}

					}
				}
				dto.setEndKonwledgeNames(str);
			}
		}
		page.setData(data);
		return page;
	}
}
