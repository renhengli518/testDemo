package com.codyy.oc.questionlib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.questionlib.dao.QueFillInAnswerMapper;
import com.codyy.oc.questionlib.dao.QueQuestionMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRChapterMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRKnowledgeMapper;
import com.codyy.oc.questionlib.entity.QueFillInAnswer;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.entity.QueQuestionRChapter;
import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;
@Service
public class CreateQuestionLibService {
	
	@Autowired
	private QueQuestionMapper queQuestionMapper;
	@Autowired
	private QueFillInAnswerMapper queFillInAnswerMapper;
	@Autowired
	private QueQuestionRChapterMapper queQuestionRChapterMapper;
	@Autowired
	private QueQuestionRKnowledgeMapper queQuestionRKnowledgeMapper;

  /**
   * @author lichen
  * @Title: createQuestion
  * @Description: TODO(新建习题)
  * @param @param question    设定文件
  * @return void    返回类型
  * @throws
   */
  public void createQuestion(List<QueQuestion> queList,List<QueQuestionRChapter> queQuestList,List<QueQuestionRKnowledge> knowList,String flag){
		
	  //1.插入习题的基本内容到题库
	  if(null!=queList && queList.size()>0){
		for(QueQuestion question : queList){
			//将组卷次数进行初始化操作
			question.setUseCount(0);
			queQuestionMapper.addQuestionLib(question);
	
			//进行习题的导入
			if(!"create".equals(flag)){
				List<QueQuestionRChapter> chapterList=question.getrChapters();
				List<QueQuestionRKnowledge> knowLists=question.getrKnowledges();
				if(null!=chapterList && chapterList.size()>0){
					for(QueQuestionRChapter queChapter : chapterList){
						if("0".equals(queChapter.getBaseVersionId())){
							queChapter.setBaseVersionId("");
						}
						if("0".equals(queChapter.getBaseVolumeId())){
							queChapter.setBaseVolumeId("");
						}
						if("0".equals(queChapter.getBaseChapterId())){
							queChapter.setBaseChapterId("");
						}
						if("0".equals(queChapter.getBaseSectionId())){
							queChapter.setBaseSectionId("");
						}
					}
					  queQuestionRChapterMapper.addQuestionRChapterBatch(chapterList);
				  }
				if(null!=knowLists && knowLists.size()>0){
					 for(QueQuestionRKnowledge queQuestionRKnow: knowLists){
						 
						 if("0".equals(queQuestionRKnow.getBaseKnowledgeId())){
							 queQuestionRKnow.setBaseKnowledgeId("");
						 }
						 if("0".equals(queQuestionRKnow.getBaseSubKnowledge1Id())){
							 queQuestionRKnow.setBaseSubKnowledge1Id("");
						 }
						 if("0".equals(queQuestionRKnow.getBaseSubKnowledge2Id())){
							 queQuestionRKnow.setBaseSubKnowledge2Id("");
						 }
						 if("0".equals(queQuestionRKnow.getBaseSubKnowledge3Id())){
							 queQuestionRKnow.setBaseSubKnowledge3Id("");
						 }
						 if("0".equals(queQuestionRKnow.getBaseSubKnowledge4Id())){
							 queQuestionRKnow.setBaseSubKnowledge4Id("");
						 }
						 if("0".equals(queQuestionRKnow.getBaseSubKnowledge5Id())){
							 queQuestionRKnow.setBaseSubKnowledge5Id("");
						 }
					 }
					  queQuestionRKnowledgeMapper.addQuestionRKnowledgeBatch(knowLists);   
				  }
			}
			
		}  
	  } 
	  //2.插入习题与章节的关系
	  if(null!=queQuestList && queQuestList.size()>0){
		  queQuestionRChapterMapper.addQuestionRChapterBatch(queQuestList);
	  }
	  //3.插入习题与知识点的关系
	  if(null!=knowList && knowList.size()>0){
		  queQuestionRKnowledgeMapper.addQuestionRKnowledgeBatch(knowList);   
	  }
	  
	  
//4.循环插入填空题
	   if(null!=queList && queList.size()>0){
		   
		   for(QueQuestion quest : queList){
			   
			   List<QueFillInAnswer> queFiList =quest.getFillInAnswers();
			    if(null!=queFiList && queFiList.size()>0){
			        for(QueFillInAnswer queFill : queFiList){
						queFillInAnswerMapper.addQuestionFillInAnswer(queFill);
					}
			    }
			 
		   }
	   }
	    
	}
  
  
  /**
   * @author lichen
  * @Title: updateQuestion
  * @Description: TODO(对单个习题的修改操作)
  * @param @param question
  * @param @param queQuestList
  * @param @param knowList    设定文件
  * @return void    返回类型
  * @throws
   */
  public void updateQuestion(QueQuestion question,List<QueQuestionRChapter> queQuestList,List<QueQuestionRKnowledge> knowList,String questionMotherId){
	  
	  //1.修改习题的基本内容
	  queQuestionMapper.updateQuestionLib(question);
	  //2.删除本习题母题(母题或自己)的所有章节关系
	  queQuestionRChapterMapper.deleteQuesChapterByMotherId(questionMotherId);
	  queQuestionRKnowledgeMapper.deleteKnowListByMotherId(questionMotherId);
	  
	  //3.将新添加的章节和知识点与母题进行关联
	  //2.插入习题与章节的关系
	  if(null!=queQuestList && queQuestList.size()>0){
		  queQuestionRChapterMapper.addQuestionRChapterBatch(queQuestList);
	  }
	  //3.插入习题与知识点的关系
	  if(null!=knowList && knowList.size()>0){
		  queQuestionRKnowledgeMapper.addQuestionRKnowledgeBatch(knowList);   
	  }
	  
	 //修改填空题
	  List<QueFillInAnswer> queFillList = question.getFillInAnswers();
         if(null!=queFillList && queFillList.size()>0){
        	//1.清空对应题目的所有填空题答案
    	     queFillInAnswerMapper.deleFillInAnswerById(question.getQueQuestionId());
    	     //2.将修改后的填空题答案进行添加
    	     for(QueFillInAnswer queFill : queFillList){
    	    	 queFill.setQueQuestionId(question.getQueQuestionId());
				 queFillInAnswerMapper.addQuestionFillInAnswer(queFill);
				}
         }     
  }
}
