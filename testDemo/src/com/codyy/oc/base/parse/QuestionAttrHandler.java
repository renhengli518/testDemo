package com.codyy.oc.base.parse;

import java.util.List;

import com.codyy.commons.utils.StringUtils;



/**
 * @author xierongbing
 * @date 2015年8月21日 下午3:38:12 
 * @description
 * 题目属性的索引,数据的注入和检测
 */
public class QuestionAttrHandler{
	public static final int CHAPERNAME_INDEX = 0;
	public static final int KNOWLEDGE_INDEX = 1;
	public static final int DIFFICULTY_INDEX = 2;
	public static final int QUESTIONTYPE_INDEX = 3;
	public static final int QUESTIONTITLE_INDEX = 4;
	public static final int OPTION_INDEX = 5;
	public static final int ANSWER_INDEX = 6;
	public static final int ANALYSIS_INDEX = 7;
	public static final int SCORE_INDEX = 8;
	
	
	
	/**
	 * @author xierongbing
	 * @date 2015年8月21日 下午3:35:15
	 * @param question
	 * @param index
	 * @param value
	 * @return void
	 * @description
	 * 题目相关的数据注入
	 */
	public static void setQuestionAttribute(QuestionInfo question, int index, String value){
		if(index == QuestionAttrHandler.CHAPERNAME_INDEX){
			question.setChapterName(value);
		}else if(index == QuestionAttrHandler.KNOWLEDGE_INDEX){
			question.setKnowledge(value);
		}else if(index == QuestionAttrHandler.DIFFICULTY_INDEX){
			question.setDifficulty(value);
		}else if(index == QuestionAttrHandler.QUESTIONTITLE_INDEX){
			question.setQuestionTitle(value);;
		}else if(index == QuestionAttrHandler.QUESTIONTYPE_INDEX){
			question.setQuestionType(value);
		}else if(index == QuestionAttrHandler.OPTION_INDEX){
			question.setOption(value);
		}else if(index == QuestionAttrHandler.ANSWER_INDEX){
			question.setAnswer(value);
		}else if(index == QuestionAttrHandler.ANALYSIS_INDEX){
			question.setAnalysis(value);
		}else if(index == QuestionAttrHandler.SCORE_INDEX){
			question.setScore(value);
		}
	}

	

	/**
	 * @author xierongbing
	 * @date 2015年8月21日 下午3:34:52
	 * @param question
	 * @param info
	 * @return void
	 * @description
	 * 题目相关的数据检测
	 */
	public static void checkQuestionAttribute(QuestionInfo question, ErrorInfo info) {
		
		//对选择题的选项顺序以及是否填写进行验证
		String [] optionArr={"A.","B.","C.","D.","E.","F.","G.","H."};
		
		if(StringUtils.isEmpty(question.getChapterName())){
			info.addQuestionAttrEmptyErrorInfo("章节");
		}
		
		/*if(StringUtil.isEmpty(question.getKnowledge())){
			info.addQuestionAttrEmptyErrorInfo("知识点");
		}*/
		
		if(StringUtils.isEmpty(question.getDifficulty())){
			info.addQuestionAttrEmptyErrorInfo("难度");
		}else{
			if(!QuestionConstant.DIFFERCULTY_LIST.contains(question.getDifficulty())){
				info.addDiffcultyErrorInfo();
			}
		}
		
		if(StringUtils.isEmpty(question.getQuestionType())){
			info.addQuestionAttrEmptyErrorInfo("习题类型");
		}else{
			if(!QuestionConstant.QUESTIONTYPE_LIST.contains(question.getQuestionType())){
				info.addQuestionTypeErrorInfo();
			}
		}
		
		if(StringUtils.isEmpty(question.getQuestionTitle())){
			info.addQuestionAttrEmptyErrorInfo("习题题干");
		}
		
		if(QuestionConstant.QUSTION_TYPE_SIGNLECHOICE.equals(question.getQuestionType()) || QuestionConstant.QUSTION_TYPE_SIGNLECHOICE.equals(question.getQuestionType())){
			if(StringUtils.isEmpty(question.getOption())){
				info.addQuestionAttrEmptyErrorInfo("选项");
			}else{
				String strs[] = question.getOption().split("；");
				if(strs.length == 1){
					strs = question.getOption().split(";");
				}
				if(strs.length > 8){
					info.addOptionErrorInfo();
				}
				question.setOptionSize(strs.length); 
			}
		}
		//如果不是解答题并且也不是计算题则进行验证
		if(!QuestionConstant.QUSTION_TYPE_ANALYSIS.equals(question.getQuestionType()) && !QuestionConstant.QUSTION_TYPE_COMPUTING.equals(question.getQuestionType())){
			if(StringUtils.isEmpty(question.getAnswer())){
				info.addQuestionAttrEmptyErrorInfo("正确答案");
			}else{
				//选择题的答案必须在选项中
				if(QuestionConstant.QUSTION_TYPE_SIGNLECHOICE.equals(question.getQuestionType())){
					String[] strs = ReadWordUtil.splitSemicolon(question.getOption());
		
						boolean flag = false;
						int optFlag=0;
						for( int k=0; k<strs.length; k++){
							if(strs[k].startsWith(question.getAnswer())){
								flag = true;
							}
							//获得单选题选项的前三个字符(即标识)
							if(strs[k].length()>=2){
								String optStr=strs[k].substring(0, 2);
								if(!(optionArr[k].equals(optStr))){
									//有的选项没填写或者填写的选项顺序有误
									optFlag=1;
								}
							}else{
								    optFlag=1;
							}
	
						}
						if(!flag){
							info.addAnswerErrorInfo();
						}
						
						if(1==optFlag){
							info.addSignChoiceOpt();
						}

					if(!(question.getAnswer().length()==1)){//單選題只能有一個答案
						info.addSignChoiceAnswer();
					}
					
	
				}else if(QuestionConstant.QUSTION_TYPE_MULTICHOICE.equals(question.getQuestionType())){
					String strs[] = ReadWordUtil.splitSemicolon(question.getOption());
					String[] answers =  ReadWordUtil.splitSemicolon(question.getAnswer());
					int optFlag=0;//判断选项的有无和
					for(String answer : answers){
						boolean flag = false;
						for(String str : strs){
							if(str.startsWith(answer)){
								flag = true;
							}
						}
						if(!flag){
							info.addAnswerErrorInfo();
							break;
						}
					}
					
					for(int k=0; k<strs.length; k++){//有的选项没填写或者填写的选项顺序有误
						
						if(strs[k].length()>=2){
							String optStr=strs[k].substring(0, 2);
							if(!(optionArr[k].equals(optStr))){
								//有的选项没填写或者填写的选项顺序有误
								optFlag=1;
							}
						}else{
							    optFlag=1;
						}
					}
					
					if(1==optFlag){
						info.addMulityChoiceOpt();
					}
					
					if(strs.length<2){
						info.addMulityOption();//至少兩個選項
					}
					if(answers.length<2){
						info.addMulityAnswer();//至少兩個選項答案
					}
					
				}else if(QuestionConstant.QUSTION_TYPE_JUDGEMENT.equals(question.getQuestionType())){
					
					String[] strs = ReadWordUtil.splitSemicolon(question.getOption());
					String[] answers =  ReadWordUtil.splitSemicolon(question.getAnswer());
					int optFlag=0;//判断选项的有无和
					boolean flag = false;
					int checkFlag=0;
					for(int k=0; k<strs.length; k++){
						if(strs[k].startsWith(question.getAnswer())){
							flag = true;
						}
						
						if(strs[k].length()>3){
							checkFlag=1;//只要含有一個選項內容不合法則進行提示
						}
						
						if(strs[k].length()>=2){
							String optStr=strs[k].substring(0, 2);
							if(!(optionArr[k].equals(optStr))){
								//有的选项没填写或者填写的选项顺序有误
								optFlag=1;
							}
						}else{
							    optFlag=1;
						}
					}
					
					if(!flag){
						info.addAnswerErrorInfo();
					}
					
					if(1==optFlag){
						info.addJudgeChoiceOpt();//有的选项没填写或者填写的选项顺序有误
					}
					
					if(1==checkFlag){
						
						info.addCheckJudgementOption();
					}
					
					if(strs.length>2 || strs.length<2){
						info.addCheckJudOptCount();
					}
					
					if(!(answers.length==1)){//判斷題的答案只能一個
						info.addJudgeAnswer();
					}
					
					
				}
			}
		}
		
		if(StringUtils.isEmpty(question.getAnalysis())){
			info.addQuestionAttrEmptyErrorInfo("习题解析");
		}
		
		//检测分数
		if(StringUtils.isEmpty(question.getScore())){
			info.addQuestionAttrEmptyErrorInfo("分数");
		}else{
			try{
				Integer.parseInt(question.getScore());
			}catch(Exception e){
				info.addScoreTypeErrorInfo();
			}
		}
	}
	
	public static void checkQuestionEmpty(List<QuestionInfo> questionList, List<ErrorInfo> infoList){
		if(questionList.isEmpty() && infoList.isEmpty()){
			ErrorInfo errorInfo = new ErrorInfo(0);
			errorInfo.addQeustionTotalRowEqual0ErrorInfo();
			infoList.add(errorInfo);
		}
	}
}