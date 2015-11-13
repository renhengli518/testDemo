package com.codyy.oc.base.parse;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * Hello world!
 *
 */
public class App {
	
	public static String question_doc = "question2003.doc";
	public static String exam_doc = "exam2003.doc";
	public static String question_docx = "question2007.docx";
	public static String exam_docx = "exam2007.docx";
	
	public static void main(String[] args) throws Exception{
//		initTestEmpty();
//		initTestSuccess();
//		testHeader();
		initTestShijuan();
	}
	
	
	public static void initTestShijuan() throws Exception{
		exam_doc = "shijuan.doc";
		testExam2003();
	}
	
	public static void initTestEmpty() throws Exception{
		question_doc = "empty.doc";
		exam_doc = "empty.doc";
		question_docx = "empty.docx";
		exam_docx = "empty.docx";
		
		testQuestion2003();
		testQuestion2007();
		testExam2003();
		testExam2007();
	}
	
	
	public static void testHeader() throws Exception{
		exam_doc = "header.doc";
		exam_docx = "header.docx";
		testExam2003();
		testExam2007();
	}
	
	public static void initTestSuccess() throws Exception{
		question_doc = "question2003success.doc";
		/*exam_doc = "empty.doc";
		question_docx = "empty.docx";
		exam_docx = "empty.docx";*/
		
		testQuestion2003();
	}
	
	
	public static void test(){
		String prefix = "小学>";
		String prefix2 = ">数学";
		String str = "一年级>人教版>上册>第一章 方程>第一节 一元一次方程";
		
		String[] strs = str.split(">");
		
		strs[0] = prefix+strs[0]+prefix2;
		
		str = StringUtils.join(strs, ">");
		
		System.out.println(str);
	}
	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n|");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	public static void testQuestion2007() throws Exception{
		FileInputStream in = new FileInputStream("c:\\test\\"+question_docx);
		List<ErrorInfo> infoList = new ArrayList<ErrorInfo>();
		List<QuestionInfo> list = ReadWordUtil.readQuestionList(in, ReadWordUtil.WORD_TYPE_DOCX, infoList);
		for(ErrorInfo info : infoList){
			if(info.getMsgList()!=null){
				for(String str: info.getMsgList()){
					System.out.println(str);
				}
			}
		}
		for(QuestionInfo info : list){
			System.out.println(info);
		}
	}
	
	public static void testExam2007() throws Exception{
		FileInputStream in = new FileInputStream("c:\\test\\"+exam_docx);
		List<ErrorInfo> infoList = new ArrayList<ErrorInfo>();
		ExamInfo exam = ReadWordUtil.readExam(in, ReadWordUtil.WORD_TYPE_DOCX, infoList,false);
		for(ErrorInfo info : infoList){
			if(info.getMsgList()!=null){
				for(String str: info.getMsgList()){
					System.out.println(str);
				}
			}
		}
		for(QuestionInfo info : exam.getQuestionList()){
			System.out.println(info);
		}
	}
	
	
	public static void testQuestion2003() throws Exception{
		FileInputStream in = new FileInputStream("c:\\test\\"+question_doc);
		List<ErrorInfo> infoList = new ArrayList<ErrorInfo>();
		List<QuestionInfo> list = ReadWordUtil.readQuestionList(in, ReadWordUtil.WORD_TYPE_DOC, infoList);
		for(ErrorInfo info : infoList){
			if(info.getMsgList()!=null){
				for(String str: info.getMsgList()){
					System.out.println(str);
				}
			}
		}
		for(QuestionInfo info : list){
			System.out.println(info);
		}
	}
	
	public static void testExam2003() throws Exception{
		FileInputStream in = new FileInputStream("c:\\test\\"+exam_doc);
		List<ErrorInfo> infoList = new ArrayList<ErrorInfo>();
		ExamInfo exam = ReadWordUtil.readExam(in, ReadWordUtil.WORD_TYPE_DOC, infoList,false);
		for(ErrorInfo info : infoList){
			if(info.getMsgList()!=null){
				for(String str: info.getMsgList()){
					System.out.println(str);
				}
			}
		}
		for(QuestionInfo info : exam.getQuestionList()){
			System.out.println(info);
		}
	}
}
