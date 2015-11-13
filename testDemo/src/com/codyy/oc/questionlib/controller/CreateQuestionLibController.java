package com.codyy.oc.questionlib.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.form.QuestionListCriteria;
import com.codyy.oc.questionlib.entity.QueFillInAnswer;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.entity.QueQuestionRChapter;
import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;
import com.codyy.oc.questionlib.form.QuestionFormFields;
import com.codyy.oc.questionlib.service.CreateQuestionLibService;
import com.codyy.oc.questionlib.service.QueQuestionService;
import com.codyy.oc.questionlib.view.UpDateMothTimeView;

@Controller
@RequestMapping("createquestionlib")
public class CreateQuestionLibController extends BaseController {
	
	@Autowired
	private CreateQuestionLibService createQuestionLibService;
	
	@Autowired
	private QueQuestionService queQuestionService;

	
	private String filterSpecChar(String oldChar){
		return (oldChar == null ? "":oldChar.replaceAll("<br>", "").replaceAll("\r\n", " "));
	}

	
	/**
	 * @author lichen
	* @Title: createQuestion
	* @Description: TODO(新建习题)
	* @param @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	@RequestMapping("createquestion")
	public void createQuestion(@ModelAttribute QuestionFormFields qstForm,HttpServletRequest request, HttpServletResponse response){
		//设置所有题型公共属性
				SessionUser sessionUser = getSessionUser(request);
	         	QueQuestion question =  new QueQuestion();
			    String questionId = UUIDUtils.getUUID();//母题的id

				String baseSemesterId=qstForm.getSemesterOpts();//学段
				String baseClasslevelId=qstForm.getClasslevelOpts();//年级
				String baseSubjectId=qstForm.getDisciplineOpts();//学科
				String source=qstForm.getSource();//习题的来源(手动输入)
				String baseUserId=sessionUser==null?"":sessionUser.getUserId();//习题创建者
				String schoolId=sessionUser==null?"":sessionUser.getSchoolId();
				String areaId=sessionUser==null?"":sessionUser.getAreaId();
				String userType=sessionUser==null?"":sessionUser.getUserType();
				String questionDifficult=qstForm.getDifficultyOpts();//习题的难易程度
				String questionType=qstForm.getQuestionTypeOpts();//题型
				String useType =qstForm.getUsableType();//权限(习题共享)
				
				String auditStatus="";
				if("SCHOOL_USR".equals(userType) || "AREA_USR".equals(useType)){//学校用户 和省市县 不需审核
					 auditStatus="PASSED";//默认创建的题为未审核
				}else{//非学校用户创建的习题需要进行审核操作
					auditStatus="INIT";
				}
				List<QueQuestion> queList = new ArrayList<QueQuestion>();   //将所有添加的习题逐个进行封装操作   第一个一定是母题
				
				//将获得的已删除的题目的下标的字符串进行处理
				String[] deleIndexArr=null;
				if(null!=qstForm.getDeleteFlagStr() && !"".equals(qstForm.getDeleteFlagStr())){//表示有删除子项
					deleIndexArr=qstForm.getDeleteFlagStr().split(",");
					
					//将当前题目的下标传过去
					for(int i=0; i<qstForm.getQuestionSubArra().length; i++){
						    boolean flag=true;
							QueQuestion ques =  new QueQuestion();
							for(int j=0; j<deleIndexArr.length; j++){
			                    if(null !=deleIndexArr[j] && !"".equals(deleIndexArr[j])){
			                    	Integer jIndex=Integer.parseInt(deleIndexArr[j]);
			                    	if(i==jIndex){
			                    		flag=false;//只要是刪除的子項則不予添加
			                    		
									}
			                    }
								
							}
							if(flag){//当不含有删除下标的题目进行添加操作
								
								ques=this.getQuestion(qstForm.getQuestionSubArra()[i],questionId,useType, questionType, baseUserId,schoolId,areaId,auditStatus,questionDifficult,source, request,baseSemesterId,baseClasslevelId,baseSubjectId,i);
								queList.add(ques);	
							}		
					}
				}else{//表示无删除子项
					
					for(int i=0; i<qstForm.getQuestionSubArra().length; i++){
						
						QueQuestion ques =  new QueQuestion();
						ques=this.getQuestion(qstForm.getQuestionSubArra()[i],questionId,useType, questionType, baseUserId, schoolId,areaId, auditStatus,questionDifficult,source, request,baseSemesterId,baseClasslevelId,baseSubjectId,i);
						queList.add(ques);	
					}
				}
				
				
			
				//System.out.println("queList="+queList);
				
				//添加章节
				List<QueQuestionRChapter> queQuestList = new ArrayList<QueQuestionRChapter>();
				
				if(null !=qstForm.getCh_input() && qstForm.getCh_input().length>0 ){
					for(int i=0; i<qstForm.getCh_input().length; i++){
						String[] VeVoChSec =qstForm.getCh_input()[i].split(":");//将每一个逗号分隔的字符串元素分为一个数组
						QueQuestionRChapter queQuestionRChapter = new QueQuestionRChapter();
						queQuestionRChapter.setQueQuestionRChapterId(UUIDUtils.getUUID());
						queQuestionRChapter.setQueQuestionId(questionId);//章节关联本习题的母题
						if(!"0".equals(VeVoChSec[0])){
							queQuestionRChapter.setBaseVersionId(VeVoChSec[0]);//一共四个  没有的0代替
						}else{
							queQuestionRChapter.setBaseVersionId("");//如果是0则存空串
						}
						if(!"0".equals(VeVoChSec[1])){
							queQuestionRChapter.setBaseVolumeId(VeVoChSec[1]);
						}else{
							queQuestionRChapter.setBaseVolumeId("");
						}
                       if(!"0".equals(VeVoChSec[2])){
                    	   queQuestionRChapter.setBaseChapterId(VeVoChSec[2]);
						}else{
							queQuestionRChapter.setBaseChapterId("");
						}
                     if(!"0".equals(VeVoChSec[3])){
                    	    queQuestionRChapter.setBaseSectionId(VeVoChSec[3]);
                       }else{
                    	   queQuestionRChapter.setBaseSectionId("");
                       }

						queQuestList.add(queQuestionRChapter);
					}
				}
				
				
                //添加相关的知识点信息
			    List<QueQuestionRKnowledge> knowList = new ArrayList<QueQuestionRKnowledge>();
			    if(null !=qstForm.getK_input() && qstForm.getK_input().length>0){
			    	
			    	for(int i=0; i<qstForm.getK_input().length; i++){
			    		
			    		String[] knowObj =qstForm.getK_input()[i].split(":");
			    		QueQuestionRKnowledge queKnow = new QueQuestionRKnowledge();
			    		queKnow.setQueQuestionRKnowledgeId(UUIDUtils.getUUID());
			    		queKnow.setQueQuestionId(questionId);//与母题进行关联
			    		
			    		//添加最后一节知识点  //倒序判断找出最后一节知识点
			    	    if(!"0".equals(knowObj[5])){
			    	    	queKnow.setBaseEndKnowledgeId(knowObj[5]);
			    	    }
			    	    if(!"0".equals(knowObj[4]) && "0".equals(knowObj[5])){
			    	    	queKnow.setBaseEndKnowledgeId(knowObj[4]);
			    	    }
			    	    if(!"0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
			    	    	queKnow.setBaseEndKnowledgeId(knowObj[3]);
			    	    }
			    	    if(!"0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
			    	    	queKnow.setBaseEndKnowledgeId(knowObj[2]);
			    	    }
			    	    if( !"0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
			    	    	
			    	    	queKnow.setBaseEndKnowledgeId(knowObj[1]);
			    	    }
                        if( !"0".equals(knowObj[0]) && "0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
			    	    	
			    	    	queKnow.setBaseEndKnowledgeId(knowObj[0]);
			    	    }
			    		
			    		if(!"0".equals(knowObj[0])){//添加基础知识点
			    			queKnow.setBaseKnowledgeId(knowObj[0]);
			    		}else{
			    			queKnow.setBaseKnowledgeId("");
			    		}
			    		if(!"0".equals(knowObj[1])){
			    			queKnow.setBaseSubKnowledge1Id(knowObj[1]);
			    		}else{
			    			queKnow.setBaseSubKnowledge1Id("");
			    		}
			    		if(!"0".equals(knowObj[2])){
			    			queKnow.setBaseSubKnowledge2Id(knowObj[2]);
			    		}else{
			    			queKnow.setBaseSubKnowledge2Id("");
			    		}
			    		if(!"0".equals(knowObj[3])){
			    			queKnow.setBaseSubKnowledge3Id(knowObj[3]);
			    		}else{
			    			queKnow.setBaseSubKnowledge3Id("");
			    		}
			    		if(!"0".equals(knowObj[4])){
			    			queKnow.setBaseSubKnowledge4Id(knowObj[4]);
			    		}else{
			    			queKnow.setBaseSubKnowledge4Id("");
			    		}
			    		if(!"0".equals(knowObj[5])){
			    			queKnow.setBaseSubKnowledge5Id(knowObj[5]);
			    		}else{
			    			queKnow.setBaseSubKnowledge5Id("");
			    		}
                        
			    		//将封装好的知识点加入集合
			    		knowList.add(queKnow);
			    	}
			    }
				
			    //System.out.println("封装好的知识点="+knowList);

				try {
					//将封装好的问题的集合传过去   
					createQuestionLibService.createQuestion(queList,queQuestList,knowList,"create");
					
				} catch (Exception e) {
					e.printStackTrace();
					callBack(response, "no");
					return;
				}		
				callBack(response, "yes");
	}
	
	/**
	 * @author lichen
	* @Title: getQuestion
	* @Description: TODO(将传来的QuestionFormFields封装成一个完整的Question对象
	* 每个传来的数组对象就是一个QuestionFormFields对象)
	* @param @param qstForm
	* @param @return    设定文件
	* @return Question    返回类型
	* @throws
	* questionNum 当前操作的题的下标号
	 */
	private QueQuestion getQuestion(QuestionFormFields qstForm,String questionId,String useType, String questionType, String userId,String schoolId, String areaId,String auditStatus, String questionDifficulty,String source, HttpServletRequest request,String baseSemesterId, String baseClasslevelId,String baseSubjectId,Integer questionNum){
		
		if("0".equals(baseSemesterId)){//如果是0将其置空   防止主外键约束异常
			
			baseSemesterId="";
		}
		
		if("0".equals(baseClasslevelId)){
			
			baseClasslevelId="";
		}
		
		if("0".equals(baseSubjectId)){
			
			baseSubjectId="";
		}
		
		QueQuestion question = new QueQuestion();
		question.setBaseUserId(userId);//获得创建者信息
		question.setClsSchoolId(schoolId);
		question.setAreaId(areaId);
		question.setContent(filterSpecChar(qstForm.getEdt_content()));//习题题干内容
		question.setResolve(filterSpecChar(qstForm.getEdt_resolve()));//题干解析内容
		question.setResolveVideo(qstForm.getResolveVideo());//音频路径
		question.setCreateTime( new Date());//创建时间
		question.setUpdateTime(new Date());//修改时间
		question.setContentVideo(qstForm.getContentVideo());//存放音频内容路径
		question.setResolveVideo(qstForm.getResolveVideo());//存放音频解析路径
		question.setUsableType(useType);//设置习题的权限
		question.setSource(source);//题的来源
		question.setDifficulty(questionDifficulty);//题目的难易程度
		question.setType(questionType);//加入题型
		question.setBaseSemesterId(baseSemesterId);//设置学段
		question.setBaseClasslevelId(baseClasslevelId);//年级
		question.setBaseSubjectId(baseSubjectId);//学科
		
		if(null!=auditStatus){
			question.setAuditStatus(auditStatus);//表示只有新建的时候初始化审核状态
		}
		
		//将单选和复选封装到一个字段  存放单选和多选题的答案
		if(QuestionListCriteria.SIGNCHOICE.equals(questionType) || QuestionListCriteria.JUDGEMENT.equals(questionType)){//单选或判断题
			if(null!=qstForm.getRdo_singleOpt() && !"".equals(qstForm.getRdo_singleOpt())){
				question.setAnswer(qstForm.getRdo_singleOpt());
			} 
		}else if(QuestionListCriteria.MULITYCHOICE.equals(questionType)){
			if(qstForm.getChk_multOpt().length>1 && null!=qstForm.getChk_multOpt()){
				String answer="";
				for(int i=0; i<qstForm.getChk_multOpt().length; i++){
					answer+=qstForm.getChk_multOpt()[i];
				}
				question.setAnswer(answer);
			}
		}
		
		
		//封装创建的选择题选项
		String options="";
		if(QuestionListCriteria.SIGNCHOICE.equals(questionType) || QuestionListCriteria.MULITYCHOICE.equals(questionType) || QuestionListCriteria.JUDGEMENT.equals(questionType)){
			
			if(qstForm.getContent_title().length>0 && null!=qstForm.getContent_title() && qstForm.getOpt_title().length>0 && null!=qstForm.getOpt_title()){
				
				for(int i=0; i<qstForm.getContent_title().length; i++){
					
					options+=qstForm.getOpt_title()[i]+qstForm.getContent_title()[i]+QuestionListCriteria.OPT_SEPERATE;
				}
				options=options.substring(0, options.length()-1);
				//System.out.println(options);
			}
		}else{}
		
		question.setOptions(options);
		question.setOptionsTxt(options);//纯文本选项
        if(QuestionListCriteria.EXTEND.equals(qstForm.getQuestionSubTypeOpts()) || QuestionListCriteria.TWINS.equals(qstForm.getQuestionSubTypeOpts())){
        	question.setRelationalIndicator(qstForm.getQuestionSubTypeOpts());
        	//如果是孪生题或衍生题    设置与母题的关联关系
        	question.setMotherId(questionId);
        	//设置孪生题或孪生题的习题id
        	question.setQueQuestionId(UUIDUtils.getUUID());
        }else{
        	question.setRelationalIndicator(QuestionListCriteria.MOTHER);//默认创建的习题为母题
        	question.setQueQuestionId(questionId);//如果是母题则添加其id
        }	
        
		if(null!=qstForm.getEdt_options()){
			question.setOptions(qstForm.getEdt_options());
		}
		
		
		//如果创建的是填空题  进行操作
		if(QuestionListCriteria.FILL_IN_BLANK.equals(questionType)){
			
			String fillAnswerType=qstForm.getRdo_answerType();//答案类型
			Integer fillCount=Integer.parseInt(qstForm.getFillInAnswerCnt());
			if(1==fillCount){//当本填空题只有一个空的时候则默认是单独答案且全选给你分
				question.setFillInScoreType("ALL_CORRECT");//全对给分
				question.setFillInAnswerType(QuestionListCriteria.INDEPENDENT);//独立答案
			}else{//当题目空的个数大于1的时候
				question.setFillInScoreType(qstForm.getRdo_scoreType());//设置填空题按空给分还是全对给分
				if("2".equals(fillAnswerType)){
					question.setFillInAnswerType(QuestionListCriteria.COMBINATION);
				}else{
					question.setFillInAnswerType(QuestionListCriteria.INDEPENDENT);
				}
			}
			
			String questionsId=question.getQueQuestionId();
			List<QueFillInAnswer> queFillList =this.obtainFillInAnswer(request, qstForm,questionNum,questionsId);
			//将封装好的集合赋值给Question对象
			question.setFillInAnswers(queFillList);//封装每个填空题
			//System.out.println("question="+question);
		}
		
		
		//如果创建的习题是非填空题
		if(!QuestionListCriteria.FILL_IN_BLANK.equals(questionType)){

			question.setFillInAnswerType("");//将填空题的答案类型置空
			question.setFillInScoreType("");//将填空题的给分类型置空
		}
		
		return question;
	}
	
	
	
	/**
	 * @author lichen
	* @Title: obtainFillInAnswer
	* @Description: TODO(返回每道填空题的所有答案)
	* @param @return    设定文件
	* @return List<List<QueFillInAnswer>>    返回类型
	* @throws
	* questionIndex  当前操作的题的下标
	* subQstForm 为每道填空题对象
	 */
	private List<QueFillInAnswer> obtainFillInAnswer(HttpServletRequest request,QuestionFormFields subQstForm,Integer questionIndex,String questionId){//questionId填空题与母题的关联id
		
		String prefix = "indep_";
		String txt_prefix = "indep_txt_"; 
				 
	    if("2".equals(subQstForm.getRdo_answerType())){//判断所选填空题的答案类型进行切换答案
					 
					   prefix = "comb_";
			    	   txt_prefix = "comb_txt_";
				 }
	    
	              List<QueFillInAnswer> li = new ArrayList<QueFillInAnswer>();//封装每道填空题的答案

		           for(int j=0; j<Integer.parseInt(subQstForm.getFillInAnswerCnt()); j++){//循环结束一次则封装一道填空题
					 
					 QueFillInAnswer  queFillObj = new QueFillInAnswer();//创建一个填空题
					 
					 queFillObj.setQueFillInAnswerId(UUIDUtils.getUUID());
					 queFillObj.setQueQuestionId(questionId);//设置与对应题库的关联id
					 
					 String answerGrp1= request.getParameter(prefix+questionIndex+"_"+(j+1)+"_1");
					 String answerGrp2= request.getParameter(prefix+questionIndex+"_"+(j+1)+"_2");
					 String answerGrp3= request.getParameter(prefix+questionIndex+"_"+(j+1)+"_3");
					 String answerGrp4= request.getParameter(prefix+questionIndex+"_"+(j+1)+"_4");
					 
					 queFillObj.setAnswerGrp1(filterSpecChar(answerGrp1));//进行字符串处理
					 queFillObj.setAnswerGrp2(filterSpecChar(answerGrp2));
					 queFillObj.setAnswerGrp3(filterSpecChar(answerGrp3));
					 queFillObj.setAnswerGrp4(filterSpecChar(answerGrp4));
					 
					 Integer sort=j+1;
					 queFillObj.setSort(sort.toString());//添加每空的第几个
					 
				    String answerGrp1Txt = (request.getParameter(txt_prefix+questionIndex+"_"+(j+1)+"_1"));//获得备份纯文本答案
					String answerGrp2Txt = (request.getParameter(txt_prefix+questionIndex+"_"+(j+1)+"_2"));
					String answerGrp3Txt = (request.getParameter(txt_prefix+questionIndex+"_"+(j+1)+"_3"));
					String answerGrp4Txt = (request.getParameter(txt_prefix+questionIndex+"_"+(j+1)+"_4"));
					
					queFillObj.setAnswerGrp1Txt(filterSpecChar(answerGrp1Txt));//对填空题的纯文本答案进行处理
					queFillObj.setAnswerGrp2Txt(filterSpecChar(answerGrp2Txt));
					queFillObj.setAnswerGrp3Txt(filterSpecChar(answerGrp3Txt));
					queFillObj.setAnswerGrp4Txt(filterSpecChar(answerGrp4Txt));
	
					li.add(queFillObj);//每次封装一空的所有四种可能性答案
				 } 	
		           
		           return li;
	}

	
	
	//对题目进行修改操作
	/**
	 * @author lichen
	* @Title: updateQuestion
	* @Description: TODO(对习题进行修改操作)
	* @param @param qstForm
	* @param @param request
	* @param @param response    设定文件
	* @return void    返回类型
	* @throws
	 */
	  @RequestMapping("updatequestion")
      public void updateQuestion(@ModelAttribute QuestionFormFields qstForm,HttpServletRequest request, HttpServletResponse response){
	     
		//章节与知识点均与母题进行关联
	    	String currentQuestionId=qstForm.getCurrentQuestionId();
	    	String currentQuesMotherId=qstForm.getCurrentQuestionIdMotherId();
	    	//共有属性
	    	String difficulty=qstForm.getDifficultyOpts();
	  		String source=qstForm.getSource();
	  		String questionTypeOpts=qstForm.getQuestionTypeOpts();//习题类型
	  		String useAbleType=qstForm.getUsableType();
	  		
	  		SessionUser sessionUser = getSessionUser(request);
	  		String baseUserId=sessionUser==null?"":sessionUser.getUserId();//习题创建者
			String schoolId=sessionUser==null?"":sessionUser.getSchoolId();
			String areaId=sessionUser==null?"":sessionUser.getAreaId();
	  		
	  		String baseSemesterId=qstForm.getSemesterOpts();//学段
			String baseClasslevelId=qstForm.getClasslevelOpts();//年级
			String baseSubjectId=qstForm.getDisciplineOpts();//学科
  		
  		//封装章节
			List<QueQuestionRChapter> queQuestList = new ArrayList<QueQuestionRChapter>();
			if(null !=qstForm.getCh_input() && qstForm.getCh_input().length>0 ){
				for(int i=0; i<qstForm.getCh_input().length; i++){
					String[] VeVoChSec =qstForm.getCh_input()[i].split(":");//将每一个逗号分隔的字符串元素分为一个数组
					QueQuestionRChapter queQuestionRChapter = new QueQuestionRChapter();
					queQuestionRChapter.setQueQuestionRChapterId(UUIDUtils.getUUID());
					if("".equals(currentQuesMotherId)){
						queQuestionRChapter.setQueQuestionId(currentQuestionId);//若是母题则直接关联
					}else{
						queQuestionRChapter.setQueQuestionId(currentQuesMotherId);//若是子题关联母题
					}
					
					if(!"0".equals(VeVoChSec[0])){
						queQuestionRChapter.setBaseVersionId(VeVoChSec[0]);//一共四个  没有的0代替
					}else{
						queQuestionRChapter.setBaseVersionId("");//如果是0则存空串
					}
					if(!"0".equals(VeVoChSec[1])){
						queQuestionRChapter.setBaseVolumeId(VeVoChSec[1]);
					}else{
						queQuestionRChapter.setBaseVolumeId("");
					}
                 if(!"0".equals(VeVoChSec[2])){
              	   queQuestionRChapter.setBaseChapterId(VeVoChSec[2]);
					}else{
						queQuestionRChapter.setBaseChapterId("");
					}
               if(!"0".equals(VeVoChSec[3])){
              	    queQuestionRChapter.setBaseSectionId(VeVoChSec[3]);
                 }else{
              	   queQuestionRChapter.setBaseSectionId("");
                 }

					queQuestList.add(queQuestionRChapter);
				}
			}
  		
	  //封装知识点
		    List<QueQuestionRKnowledge> knowList = new ArrayList<QueQuestionRKnowledge>();
		    if(null !=qstForm.getK_input() && qstForm.getK_input().length>0){
		    	
		    	for(int i=0; i<qstForm.getK_input().length; i++){
		    		
		    		String[] knowObj =qstForm.getK_input()[i].split(":");
		    		QueQuestionRKnowledge queKnow = new QueQuestionRKnowledge();
		    		queKnow.setQueQuestionRKnowledgeId(UUIDUtils.getUUID());
		    		
		    		if("".equals(currentQuesMotherId)){
		    			queKnow.setQueQuestionId(currentQuestionId);//若是母题则可直接关联
		    		}else{	
		    			queKnow.setQueQuestionId(currentQuesMotherId);//若是子题则直接和其母题进行关联
		    		}	
		    		//添加最后一节知识点  //倒序判断找出最后一节知识点
		    	    if(!"0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[5]);
		    	    }
		    	    if(!"0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[4]);
		    	    }
		    	    if(!"0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[3]);
		    	    }
		    	    if(!"0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[2]);
		    	    }
		    	    if( !"0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[1]);
		    	    }
                    if( !"0".equals(knowObj[0]) && "0".equals(knowObj[1]) && "0".equals(knowObj[2]) && "0".equals(knowObj[3]) && "0".equals(knowObj[4]) && "0".equals(knowObj[5])){
		    	    	
		    	    	queKnow.setBaseEndKnowledgeId(knowObj[0]);
		    	    }
		    		
		    		if(!"0".equals(knowObj[0])){//添加基础知识点
		    			queKnow.setBaseKnowledgeId(knowObj[0]);
		    		}else{
		    			queKnow.setBaseKnowledgeId("");
		    		}
		    		if(!"0".equals(knowObj[1])){
		    			queKnow.setBaseSubKnowledge1Id(knowObj[1]);
		    		}else{
		    			queKnow.setBaseSubKnowledge1Id("");
		    		}
		    		if(!"0".equals(knowObj[2])){
		    			queKnow.setBaseSubKnowledge2Id(knowObj[2]);
		    		}else{
		    			queKnow.setBaseSubKnowledge2Id("");
		    		}
		    		if(!"0".equals(knowObj[3])){
		    			queKnow.setBaseSubKnowledge3Id(knowObj[3]);
		    		}else{
		    			queKnow.setBaseSubKnowledge3Id("");
		    		}
		    		if(!"0".equals(knowObj[4])){
		    			queKnow.setBaseSubKnowledge4Id(knowObj[4]);
		    		}else{
		    			queKnow.setBaseSubKnowledge4Id("");
		    		}
		    		if(!"0".equals(knowObj[5])){
		    			queKnow.setBaseSubKnowledge5Id(knowObj[5]);
		    		}else{
		    			queKnow.setBaseSubKnowledge5Id("");
		    		}
                    
		    		//将封装好的知识点加入集合
		    		knowList.add(queKnow);
		    	}
		    }
		    
		   // System.out.println("queQuestList="+queQuestList+"---knowList="+knowList);

        //1.若只有一道题需要进行修改(即1.母题不增加子题(只修改母题) 2.修改子题(由于页面没有新增子题的链接))
		    if(1==qstForm.getQuestionSubArra().length){
		    	//如果修改的是子题则获得子题的id和其母题的id       如果是修改的是母题则直接获得母题的id即可
	    		QueQuestion question = new QueQuestion();//创建一个习题对象
		    	if("".equals(currentQuesMotherId)){//表示本题是母题
		    		QuestionFormFields qusForm =qstForm.getQuestionSubArra()[0];
		    		qusForm.setQuestionSubTypeOpts(qstForm.getRelationalIndicator());;//将习题的家族属性进行赋值
		    		question=this.getQuestion(qusForm, currentQuestionId, useAbleType, questionTypeOpts, baseUserId,schoolId,areaId,null, difficulty, source, request, baseSemesterId, baseClasslevelId, baseSubjectId, 0);
		    		try {
						//将封装好的问题的集合传过去     实现对习题的更新操作
		    			createQuestionLibService.updateQuestion(question,queQuestList,knowList,currentQuestionId);   
					} catch (Exception e) {
						e.printStackTrace();
						callBack(response, "no");
						return;
					}		
					callBack(response, "yes");
		    	}else{//表示是子题
		    		QuestionFormFields qusForm =qstForm.getQuestionSubArra()[0];
		    		qusForm.setQuestionSubTypeOpts(qstForm.getRelationalIndicator());//将习题的家族属性进行赋值
		    		question=this.getQuestion(qusForm, currentQuesMotherId, useAbleType, questionTypeOpts, baseUserId, schoolId,areaId,null,difficulty, source, request, baseSemesterId, baseClasslevelId, baseSubjectId, 0);
		    		//设置子题的原来id
		    		question.setQueQuestionId(currentQuestionId);
		    		try {
						//将封装好的问题的集合传过去     实现对习题的更新操作   操作章节传过去的是他母题的id
		    			createQuestionLibService.updateQuestion(question,queQuestList,knowList,currentQuesMotherId);
		    			//更新母题的时间
		    			UpDateMothTimeView upDateMothTimeView= new UpDateMothTimeView();
		    			upDateMothTimeView.setQuestionId(currentQuesMotherId);
		    			upDateMothTimeView.setSonDate(question.getUpdateTime());
		    			queQuestionService.updateMotherTime(upDateMothTimeView);
		    			
					} catch (Exception e) {
						e.printStackTrace();
						callBack(response, "no");
						return;
					}		
					callBack(response, "yes");
		    	}
		    	
		    }else{//2.母题修改并且母题添加了许多子题(对第一个母题进行修改操作而对其他子题进行添加操作)
		    	
		    	   //操作多道习题        第一道母题进行修改操作   其余的子题进行添加操作    将第一道题进行修改  其他子题进行添加即可     当前习题一定是母题
		    	QueQuestion question = new QueQuestion();//创建一个习题对象
		    	//将母题进行修改
		    	QuestionFormFields qusForm =qstForm.getQuestionSubArra()[0];
	    		qusForm.setQuestionSubTypeOpts(qstForm.getRelationalIndicator());;//将习题的家族属性进行赋值
	    		List<QueQuestion> queList = new ArrayList<QueQuestion>();   //将所有添加的习题逐个进行封装操作
	    		question=this.getQuestion(qusForm, currentQuestionId, useAbleType, questionTypeOpts, baseUserId,schoolId,areaId,null,difficulty, source, request, baseSemesterId, baseClasslevelId, baseSubjectId, 0);
	    		try {
					//将封装好的问题的集合传过去     实现对习题的更新操作
	    			createQuestionLibService.updateQuestion(question,queQuestList,knowList,currentQuestionId);  
					
	    			
					//将获得的已删除的题目的下标的字符串进行处理
					String[] deleIndexArr=null;
					if(null!=qstForm.getDeleteFlagStr() && !"".equals(qstForm.getDeleteFlagStr())){//表示有删除子项
						deleIndexArr=qstForm.getDeleteFlagStr().split(",");
						
						//将当前题目的下标传过去
						for(int i=1; i<qstForm.getQuestionSubArra().length; i++){
							    boolean flag=true;
								QueQuestion ques =  new QueQuestion();
								for(int j=0; j<deleIndexArr.length; j++){
				                    if(null !=deleIndexArr[j] && !"".equals(deleIndexArr[j])){
				                    	Integer jIndex=Integer.parseInt(deleIndexArr[j]);
				                    	if(i==jIndex){
				                    		flag=false;
										}
				                    }
									
								}
								if(flag){//当不含有删除下标的题目进行添加操作
									ques=this.getQuestion(qstForm.getQuestionSubArra()[i],currentQuestionId,useAbleType, questionTypeOpts, baseUserId,schoolId,areaId,null,difficulty,source, request,baseSemesterId,baseClasslevelId,baseSubjectId,i);
									queList.add(ques);	
								}		
						}
					}else{//表示无删除子项
						
					 	//将添加的子题进行添加并与母题进行关联
		    			for(int i=1; i<qstForm.getQuestionSubArra().length; i++){
		    					QueQuestion ques =  new QueQuestion();
								ques=this.getQuestion(qstForm.getQuestionSubArra()[i],currentQuestionId,useAbleType, questionTypeOpts, baseUserId,schoolId,areaId,null, difficulty,source, request,baseSemesterId,baseClasslevelId,baseSubjectId,i);
								queList.add(ques);
		    				
						}
					}
	    			//将子题进行添加操作 
					createQuestionLibService.createQuestion(queList,null,null,"create");
					
				} catch (Exception e) {
					e.printStackTrace();
					callBack(response, "no");
					return;
				}		
				callBack(response, "yes");

		    }
	   } 
}
