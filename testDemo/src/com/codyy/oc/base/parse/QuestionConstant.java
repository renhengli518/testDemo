package com.codyy.oc.base.parse;

import java.util.Vector;

/**
 * @author xierongbing
 * @date 2015年8月21日 下午1:42:05 
 * @description 
 */

public class QuestionConstant {
	//容易/较易/一般/较难/困难
	public static final String DIFFERCULTY_LEVEL1 = "容易";
	public static final String DIFFERCULTY_LEVEL2 = "较易";
	public static final String DIFFERCULTY_LEVEL3 = "一般";
	public static final String DIFFERCULTY_LEVEL4 = "较难";
	public static final String DIFFERCULTY_LEVEL5 = "困难";
	
	public static final Vector<String> DIFFERCULTY_LIST = new Vector<String>();  //必须使用线程 安全类
	static{
		DIFFERCULTY_LIST.add(DIFFERCULTY_LEVEL1);
		DIFFERCULTY_LIST.add(DIFFERCULTY_LEVEL2);
		DIFFERCULTY_LIST.add(DIFFERCULTY_LEVEL3);
		DIFFERCULTY_LIST.add(DIFFERCULTY_LEVEL4);
		DIFFERCULTY_LIST.add(DIFFERCULTY_LEVEL5);
	}
	
	
	//单选题/多选题/填空题/解答题
	public static final String QUSTION_TYPE_SIGNLECHOICE = "单选题";
	public static final String QUSTION_TYPE_MULTICHOICE = "多选题";
	//public static final String QUSTION_TYPE_FILL = "填空题";
	public static final String QUSTION_TYPE_JUDGEMENT="判断题";
	public static final String QUSTION_TYPE_ANALYSIS = "问答题";
	public static final String QUSTION_TYPE_COMPUTING = "计算题";
	//单选题/多选题/填空题/解答题 类型对应的ID
	public static final String QUSTION_TYPE_SIGNLECHOICE_ID = "1";
	public static final String QUSTION_TYPE_MULTICHOICE_ID = "2";
	
	//public static final String QUSTION_TYPE_FILL_ID = "3";
	
	public static final String QUSTION_TYPE_JUDGEMENT_ID="3";
	public static final String QUSTION_TYPE_ANALYSIS_ID = "4";
	public static final String QUSTION_TYPE_COMPUTING_ID="5";//计算题和解答题一致
	
	public static final Vector<String> QUESTIONTYPE_LIST = new Vector<String>();
	static{
		QUESTIONTYPE_LIST.add(QUSTION_TYPE_SIGNLECHOICE);
		QUESTIONTYPE_LIST.add(QUSTION_TYPE_MULTICHOICE);
//		QUESTIONTYPE_LIST.add(QUSTION_TYPE_FILL);
		QUESTIONTYPE_LIST.add(QUSTION_TYPE_ANALYSIS);
		QUESTIONTYPE_LIST.add(QUSTION_TYPE_JUDGEMENT);
		QUESTIONTYPE_LIST.add(QUSTION_TYPE_COMPUTING);
	}
}
