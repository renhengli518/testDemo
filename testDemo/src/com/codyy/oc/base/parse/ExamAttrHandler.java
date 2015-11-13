package com.codyy.oc.base.parse;

import com.codyy.commons.utils.StringUtils;


/**
 * @author xierongbing
 * @date 2015年8月21日 下午3:41:01 
 * @description 
 * 试卷信息的属性索引
 */

public class ExamAttrHandler {
	public static final int EXAMNAME_INDEX = 0;
	
	public static final int SEMESTERNAME_INDEX = 1;
	
	public static final int CLASSLEVEL_INDEX = 2;
	
	public static final int SUBJECTNAME_INDEX = 3;
	
	public static final int EXAMTYPE_INDEX = 4;
	
	public static final int RESPONSETIME_INDEX = 5;
	
	public static final int TOTALSCORE_INDEX = 6;
	
	public static final int AREANAME_INDEX = 7;
	
	public static final int YEAR_INDEX = 8;
	

	/**
	 * @author xierongbing
	 * @date 2015年8月21日 下午3:35:15
	 * @param question
	 * @param index
	 * @param value
	 * @return void
	 * @description
	 * 试卷相关的数据注入
	 */
	public static void setExamAttribute(ExamInfo exam, int index, String value){
		if(index == EXAMNAME_INDEX){
			exam.setExamName(value);
		}else if(index == SEMESTERNAME_INDEX){
			exam.setSemesterName(value);
		}else if(index == CLASSLEVEL_INDEX){
			exam.setClasslevelName(value);
		}else if(index == SUBJECTNAME_INDEX){
			exam.setSubjectName(value);
		}else if(index == EXAMTYPE_INDEX){
			exam.setExamType(value);
		}else if(index == RESPONSETIME_INDEX){
			exam.setResponseTime(value);
		}else if(index == TOTALSCORE_INDEX){
			exam.setTotalScore(value);
		}else if(index == AREANAME_INDEX){
			exam.setAreaName(value);
		}else if(index == YEAR_INDEX){
			exam.setYear(value);
		}
	}
	
	/**
	 * @author xierongbing
	 * @date 2015年8月21日 下午3:34:52
	 * @param question
	 * @param info
	 * @return void
	 * @description
	 * 试卷相关的数据检测
	 */
	public static void checkExamAttribute(ExamInfo exam, ErrorInfo info) {
		if(StringUtils.isEmpty(exam.getExamName())){
			info.addExamAttrEmptyErrorInfo("试卷名称");
		}
		
		if(StringUtils.isEmpty(exam.getSemesterName())){
			info.addExamAttrEmptyErrorInfo("学段");
		}
		
		if(StringUtils.isEmpty(exam.getSubjectName())){
			info.addExamAttrEmptyErrorInfo("学科");
		}
		
		if(StringUtils.isEmpty(exam.getExamType())){
			info.addExamAttrEmptyErrorInfo("试卷类型");
		}
		
		if(StringUtils.isEmpty(exam.getResponseTime())){
			info.addExamAttrEmptyErrorInfo("答题时间");
		}else{
			try{
				int responseTime = Integer.parseInt(exam.getResponseTime());
				if(responseTime > 999){
					info.addExamResponseTimeMoreThan999ErrorInfo();
				}
			}catch(Exception e){
				info.addExamResponseTimeTypeErrorInfo();
			}
		}
		
		if(StringUtils.isEmpty(exam.getTotalScore())){
			info.addExamAttrEmptyErrorInfo("试卷总分");
		}else{
			try{
				Integer.parseInt(exam.getTotalScore());
			}catch(Exception e){
				info.addExamScoreTypeErrorInfo();
			}
		}
	}

	public static void checkExamOtherAttribute(ExamInfo exam, ErrorInfo info) {
		//分数验证
		if(!StringUtils.isEmpty(exam.getTotalScore())){
			try{
				int total = Integer.parseInt(exam.getTotalScore());
				int sum = 0;
				for(QuestionInfo question : exam.getQuestionList()){
					sum += Integer.parseInt(question.getScore());
				}
				if(total != sum){
					info.addExamTotalScoreErrorInfo();
				}else{
					if(sum > 999){
						info.addExamTotalScoreMoreThan999ErrorInfo();
					}
				}
			}catch(Exception e){
			}
		}
	}
}
