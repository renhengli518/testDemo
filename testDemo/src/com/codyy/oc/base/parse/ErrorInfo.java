package com.codyy.oc.base.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author xierongbing
 * @date 2015年8月21日 上午10:17:20 
 * @description 
 */

public class ErrorInfo {
	private boolean flag = true;
	
	private int index; //第几道题
	
	private String prefix;
	
	public ErrorInfo(int index){
		this.index = index;
		this.prefix = "第"+index+"题";
	}

	private List<String> msgList = new ArrayList<String>();

	public void addErrorInfo(String str){
		flag = false;
		msgList.add(str);
	}
	
	
	public String addDocTypeErrorInfo(){
		return "文档类型错误";
	}
	
	public void addQuestionTotalRowErrorInfo(int count){
		String str = this.prefix+"的行数不对,应该是"+count+"行";
		this.addErrorInfo(str);
	}
	
	
	public void addQuestionTotalColumnErrorInfo(int row){
		String str = this.prefix+"第"+row+"行的列数不对,应该是"+ReadWordUtil.COL+"列";
		this.addErrorInfo(str);
	}
	
	public void addQuestionAttrEmptyErrorInfo(String attr){
		String str = this.prefix+attr+"不能为空";
		this.addErrorInfo(str);
	}
	
	public void addDiffcultyErrorInfo(){
		String str = this.prefix+"的难度填写不对,应该是"+StringUtils.join(QuestionConstant.DIFFERCULTY_LIST, "、")+"之一";
		this.addErrorInfo(str);
	}
	
	public void addQuestionTypeErrorInfo(){
		String str = this.prefix+"的题目类型填写不对,应该是"+StringUtils.join(QuestionConstant.QUESTIONTYPE_LIST, "、")+"之一";
		this.addErrorInfo(str);
	}
	
	public void addOptionErrorInfo() {
		String str = this.prefix+"选项最多为8个";
		this.addErrorInfo(str);
	}
	
	public void addAnswerErrorInfo() {
		String str = this.prefix+"的正确答案必须在选项中";
		this.addErrorInfo(str);
	}
	
	public void addScoreTypeErrorInfo(){
		String str = this.prefix+"的分数的数据类型不对";
		this.addErrorInfo(str);
	}
	
	public void addQeustionTotalRowEqual0ErrorInfo(){
		String str = "题目数为0";
		this.addErrorInfo(str);
	}
	
	/**
	 * @author LICHEN
	* @Title: addCheckJudgementOption
	* @Description: TODO(判斷題的選項必須是一個字符)
	* @param     设定文件
	* @return void    返回类型
	* @throws
	 */
	public void addCheckJudgementOption(){
		
		String str="判断题的选项內容只能是一个字符!";
		this.addErrorInfo(str);
	}
	
	public void addCheckJudOptCount(){
		
		String str="判断题只能有两个选项!";
		this.addErrorInfo(str);
	}
	
	public void addMulityOption(){
		
		String str="多选题至少有两个选项!";
		this.addErrorInfo(str);
	}
	
   public void addMulityAnswer(){
		
		String str="多选题至少有两个选项答案!";
		this.addErrorInfo(str);
	}
   
   public void addJudgeAnswer(){
	   
	   String str="判断题只能有一个答案!";
		this.addErrorInfo(str);
   }
	
   public void addSignChoiceAnswer(){
	   
	   String str="单选题只能有一个答案!";
		this.addErrorInfo(str);
   }
   
   public void addSignChoiceOpt(){
	   String str="单选题有的选项没填写或者填写的选项顺序有误!";
		this.addErrorInfo(str);   
   }
   
   public void addMulityChoiceOpt(){
	   String str="多选题有的选项没填写或者填写的选项顺序有误!";
		this.addErrorInfo(str);   
   }
   public void addJudgeChoiceOpt(){
	   String str="判断题有的选项没填写或者填写的选项顺序有误!";
		this.addErrorInfo(str);   
   }
	
	
	
	public void addExamTotalRowErrorInfo(){
		String str = "试卷信息表的行数不对,应该是"+ReadWordUtil.EXAM_ROW+"行";
		this.addErrorInfo(str);
	}
	
	public void addExamTotalColumnErrorInfo(int row){
		String str = "试卷信息表的第"+row+"行的列数不对,应该是"+ReadWordUtil.COL+"列";
		this.addErrorInfo(str);
	}
	
	public void addExamAttrEmptyErrorInfo(String attr){
		String str = "试卷信息中"+attr+"不能为空";
		this.addErrorInfo(str);
	}
	
	public void addExamScoreTypeErrorInfo(){
		String str = "试卷信息中"+"试卷总分的数据类型不对";
		this.addErrorInfo(str);
	}
	
	public void addExamResponseTimeTypeErrorInfo() {
		String str = "试卷信息中"+"答题时间的数据类型不对";
		this.addErrorInfo(str);
	}
	
	public void addExamTotalScoreMoreThan999ErrorInfo(){
		String str = "试卷总分大于999分";
		this.addErrorInfo(str);
	}
	
	public void addExamResponseTimeMoreThan999ErrorInfo(){
		String str = "试卷信息中"+"答题时间大于999分";
		this.addErrorInfo(str);
	}
	
	public void addExamTotalScoreErrorInfo(){
		String str = "试卷总分跟题目总分不同";
		this.addErrorInfo(str);
	}
	
	public void addExamInfoEmptyErrorInfo(){
		String str = "试卷信息为空";
		this.addErrorInfo(str);
	}
	
	public void addTotalRowMoreThan100ErrorInfo() {
		String str = "题目数超过上限（100）";
		this.addErrorInfo(str);
	}

	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public List<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
