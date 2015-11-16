package com.codyy.oc.questionlib.dao;

import java.util.List;
import java.util.Map;

import com.codyy.commons.page.Page;
import com.codyy.oc.base.form.QuestionListResult;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.view.UpDateMothTimeView;

public interface QueQuestionMapper {
	int deleteByPrimaryKey(String queQuestionId);

	int insert(QueQuestion record);

	int insertSelective(QueQuestion record);

	QueQuestion selectByPrimaryKey(String queQuestionId);

	int updateByPrimaryKeySelective(QueQuestion record);

	int updateByPrimaryKeyWithBLOBs(QueQuestion record);

	int updateByPrimaryKey(QueQuestion record);

	/**
	 * 行习题的插入(添加习题的基本内容)
	 * @author lichen
	 * @Title: addQuestionLib
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param questLib
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @throws
	 */
	Integer addQuestionLib(QueQuestion questLib);
	
	/**
	 * @author lichen
	* @Title: updateQuestionLib
	* @Description: TODO(修改习题的基本内容)
	* @param @param questLib
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer updateQuestionLib(QueQuestion questLib);

	/**
	 * 根据习题的id来查询习题的相关所有信息
	 *@author lichen
	 * @Title: queryQuestionById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param questionId
	 * @param @return 设定文件
	 * @return QueQuestion 返回类型
	 * @throws
	 */
	QueQuestion queryQuestionById(String questionId);
	
	
	
	
	

	/**
	 * 查询共享题库
	 * 
	 * @Title: getShareQuePageList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @return 设定文件
	 * @return List<QueQuestion> 返回类型
	 * @throws
	 */
	List<QuestionListResult> getShareQuePageList(Page page);
	
	/**
	 * 查询教师组卷习题列表
	 * 
	 * @Title: getShareQuePageList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @return 设定文件
	 * @return List<QueQuestion> 返回类型
	 * @throws
	 */
	List<QuestionListResult> getTeaQuePageList(Page page);

	/**
	 * 
	 * @Title: searchIntelligenceList
	 * @Description: TODO(智能组卷获取习题)
	 * @param @param map
	 * @param @return
	 * @return List<QuestionListResult>
	 * @throws
	 */
	List<QuestionListResult> searchIntelligenceList(Map<String, Object> map);

	/**
	 * 更新审核状态
	 * @param map
	 * @return
	 */
	int updateAuditStatusByKey(Map<String, Object> map);
	
	/**
	 * 查询我的收藏习题列表
	 * @param page
	 * @return
	 */
	List<QuestionListResult> getFavoriteQuePageList(Page page);

	/**
	 * 查询我的习题列表
	 * @param page
	 * @return
	 */
	List<QuestionListResult> getOwnQuePageList(Page page);
	
	List<QueQuestion> selectByIds(String ids[]);
	
	/**
	 * @author lichen
	* @Title: countMotherSon
	* @Description: TODO(编辑母题的时候获得当前母题的子题个数)
	* @param @param questionId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	Integer countMotherSon(String questionId);
	
	/**
	 * 根据motherId 删除习题
	 * @param motherId
	 * @return
	 */
	int deleteByMotherId(String motherId);
	
	/**
	 * @author lichen
	* @Title: updateMaotherTime
	* @Description: TODO(编辑子题的时候顺带更新母题的时间)
	* @param @return    设定文件
	* @return int    返回类型
	* @throws
	 */
	int updateMotherTime(UpDateMothTimeView updateTime);
}