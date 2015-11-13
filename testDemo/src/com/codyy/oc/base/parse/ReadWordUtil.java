package com.codyy.oc.base.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public final class ReadWordUtil {
	public static final String WORD_TYPE_DOC = "doc";
	
	public static final String WORD_TYPE_DOCX = "docx";
	
	public static final String NEW_LINE = "<br/>";
	
	//上传试题时题目的行数
	public static final int QUESTION_ROW = 8;
	
	//上传试卷时试卷信息的行数
	public static final int EXAM_ROW = 7;
	
	//上传真题试卷时试卷信息的行数
	public static final int REAL_EXAM_ROW = 9;
		
	//上传试卷时题目的行数
	public static final int EXAM_QUESTION_ROW = 9;
	
	//列数
	public static final int COL = 2;
	
	public static List<QuestionInfo> readQuestionList(InputStream in, String docType, List<ErrorInfo> infoList){
		List<QuestionInfo> questionList = null;
		if(WORD_TYPE_DOC.equals(docType)){
			questionList = readQuestionListFromDoc(in, infoList, false);
			QuestionAttrHandler.checkQuestionEmpty(questionList, infoList);
			return questionList;
		}else if(WORD_TYPE_DOCX.equals(docType)){
			questionList = readQuestionListFromDocx(in, infoList, false);
			QuestionAttrHandler.checkQuestionEmpty(questionList, infoList);
			return questionList;
		}
		return null;
	}
	
	public static ExamInfo readExam(InputStream in, String docType, List<ErrorInfo> infoList,boolean isreal){
		ExamInfo examInfo = null;
		if(WORD_TYPE_DOC.equals(docType)){
			examInfo =  handlePrefix(readExamFromDoc(in, infoList,isreal));
			QuestionAttrHandler.checkQuestionEmpty(examInfo.getQuestionList(), infoList);
			return examInfo;
		}else if(WORD_TYPE_DOCX.equals(docType)){
			examInfo =  handlePrefix(readExamFromDocx(in, infoList,isreal));
			QuestionAttrHandler.checkQuestionEmpty(examInfo.getQuestionList(), infoList);
			return examInfo;
		}
		return null;
	}
	
	public static ExamInfo handlePrefix(ExamInfo info){
		String prefix = info.getSemesterName()+">";
		String prefix2 = ">"+info.getSubjectName();
		List<QuestionInfo> list = info.getQuestionList();
		
		for(QuestionInfo question : list){
			String chapterStr="";
			String str = question.getChapterName();
			if(str == null || str.equals("")){
				question.setChapterName("");
			}else{
				
				String[] chapterStrArr =str.split(";");
				if(null!=chapterStrArr && chapterStrArr.length>0){
					for(int i=0; i<chapterStrArr.length; i++){
						String[] strs = chapterStrArr[i].split(">");
						strs[0]=prefix+strs[0]+prefix2;//将头部更换加上学段和学科
						chapterStr+= StringUtils.join(strs, ">")+";";
					}
					//截取最后一个分号
					chapterStr=chapterStr.substring(0,chapterStr.length()-1);
				}
            	question.setChapterName(chapterStr);
			}
			
		}
		return info;
	}

	private static List<QuestionInfo> readQuestionListFromDoc(InputStream in, List<ErrorInfo> infoList, boolean hasHeader) {
		List<QuestionInfo> list = new ArrayList<QuestionInfo>();
		try {
			// 载入文档
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			HWPFDocument hwpf = new HWPFDocument(pfs);
			PicturesTable pTable = hwpf.getPicturesTable();
			Range range = hwpf.getRange();// 得到文档的读取范围
			TableIterator it = new TableIterator(range);
			
			int totalRow = hasHeader ? EXAM_QUESTION_ROW : QUESTION_ROW;
			if(hasHeader){
				if(it.hasNext())
					it.next();
			}
			
			QuestionInfo question = null;
			ErrorInfo info = null;
			int count = 1;
			// 迭代文档中的表格
			while (it.hasNext()) {
				Table tb = (Table) it.next();
				
				question = new QuestionInfo();
				info = new ErrorInfo(count);
				int rows = tb.numRows();
				if(rows != totalRow){
					info.addQuestionTotalRowErrorInfo(totalRow);
					list.add(question);
					infoList.add(info);
					count++;
					continue;
				}
				
				// 迭代行，默认从0开始
				boolean flag = true;
				for (int i = 0; i < rows; i++) {
					TableRow tr = tb.getRow(i);
					int cols = tr.numCells();
					if(cols != COL){
						flag = false;
						info.addQuestionTotalColumnErrorInfo(i);
						break;
					}
					
					// 迭代列，默认从1开始
					StringBuilder sb = new StringBuilder();
					for (int j = 1; j < COL; j++) {
						TableCell td = tr.getCell(j);// 取得单元格
						for(int m=0;m<td.numCharacterRuns();m++){
							if(pTable.hasPicture(td.getCharacterRun(m))){
								Picture picture= pTable.extractPicture(td.getCharacterRun(m), true);
								String fileName = picture.suggestFullFileName();
								byte[] content = picture.getContent();
								
								PictureInfo pic = new PictureInfo();
								pic.setData(Arrays.copyOf(content, content.length));
								pic.setFileName(fileName);
								
								if(i == QuestionAttrHandler.QUESTIONTITLE_INDEX)
									question.getTitlePictureList().add(pic);
								if(i == QuestionAttrHandler.ANALYSIS_INDEX)
									question.getAnalysisPictureList().add(pic);
							}
						}
						for (int k = 0; k < td.numParagraphs(); k++) {
							Paragraph para = td.getParagraph(k);
							if(k != 0){
								sb.append(NEW_LINE);
							}
							String s = para.text().trim();
							sb.append(s);
						} 
					} 
					QuestionAttrHandler.setQuestionAttribute(question, i, replaceStrangeChar(sb.toString()));
				}
				if(flag)
					QuestionAttrHandler.checkQuestionAttribute(question, info);
				
				list.add(question);
				infoList.add(info);
				count++;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static ExamInfo readExamFromDoc(InputStream in, List<ErrorInfo> infoList,boolean isreal) {
		byte[] bytes= null;
		byte[] copy = null;
		try {
			bytes = ByteToInputStream.input2byte(in);
			copy = Arrays.copyOf(bytes, bytes.length);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ExamInfo examInfo = new ExamInfo();
		try {
			// 载入文档
			POIFSFileSystem pfs = new POIFSFileSystem(ByteToInputStream.byte2Input(bytes));
			HWPFDocument hwpf = new HWPFDocument(pfs);
			Range range = hwpf.getRange();// 得到文档的读取范围
			TableIterator it = new TableIterator(range);
			
			ErrorInfo info = null;
			Table tb = null;
			if(it.hasNext()){
				tb = (Table) it.next();
			}else{
				//试卷信息为空error
				info = new ErrorInfo(0);
				info.addExamInfoEmptyErrorInfo();
				infoList.add(info);
				return examInfo;
			}
			
			// 迭代文档中的表格
			info = new ErrorInfo(0);
			int rows = tb.numRows();
			if(rows != (isreal?REAL_EXAM_ROW:EXAM_ROW)){
				info.addExamTotalRowErrorInfo();
			}else{
				// 迭代行，默认从0开始
				boolean flag = true;
				for (int i = 0; i < tb.numRows(); i++) {
					TableRow tr = tb.getRow(i);
					// 迭代列，默认从0开始
					StringBuilder sb = new StringBuilder();
					
					int cols = tr.numCells();
					if(cols != COL){
						flag = false;
						info.addExamTotalColumnErrorInfo(i);
						break;
					}
					
					for (int j = 1; j < COL; j++) {
						TableCell td = tr.getCell(j);// 取得单元格
						
						// 取得单元格的内容
						for (int k = 0; k < td.numParagraphs(); k++) {
							Paragraph para = td.getParagraph(k);
							if(k != 0){
								sb.append(NEW_LINE);
							}
							String s = para.text().trim();
							sb.append(s);
						} 
					} 
					ExamAttrHandler.setExamAttribute(examInfo, i, replaceStrangeChar(sb.toString()));
				}
				if(flag)
					ExamAttrHandler.checkExamAttribute(examInfo, info);
			}
			List<QuestionInfo> questionList = readQuestionListFromDoc(ByteToInputStream.byte2Input(copy), infoList, true);
			if(!info.isFlag())
				infoList.add(info);
			examInfo.setQuestionList(questionList);
			
			ExamAttrHandler.checkExamOtherAttribute(examInfo, info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examInfo;
	}
	
	
	private static List<QuestionInfo> readQuestionListFromDocx(InputStream in, List<ErrorInfo> infoList, boolean hasHeader) {
		List<QuestionInfo> list = new ArrayList<QuestionInfo>();
		try {
			// 载入文档
			XWPFDocument xwpf = new XWPFDocument(in);
			Iterator<XWPFTable> it = xwpf.getTablesIterator();
			
			int totalRow = hasHeader ? EXAM_QUESTION_ROW : QUESTION_ROW;
			if(hasHeader){
				if(it.hasNext())
					it.next();
			}
			
			QuestionInfo question = null;
			ErrorInfo info = null;
			int count = 1;
			
			// 迭代文档中的表格
			while (it.hasNext()) {
				XWPFTable tb = (XWPFTable) it.next();
				
				question = new QuestionInfo();
				info = new ErrorInfo(count);
				int rows = tb.getNumberOfRows();
				if(rows != totalRow){
					info.addQuestionTotalRowErrorInfo(totalRow);
					list.add(question);
					infoList.add(info);
					count++;
					continue;
				}
				
				// 迭代行，默认从0开始
				boolean flag = true;
				for (int i = 0; i < tb.getNumberOfRows(); i++) {
					XWPFTableRow tr = tb.getRow(i);
					
					int cols = tr.getTableCells().size();
					if(cols != COL){
						flag = false;
						info.addQuestionTotalColumnErrorInfo(i);
						break;
					}
					
					StringBuilder sb = new StringBuilder();
					// 迭代列，默认从0开始
					for (int j = 1; j < COL; j++) {
						XWPFTableCell td = tr.getCell(j);// 取得单元格
						
						// 取得单元格的内容
						for (int k = 0; k < td.getParagraphs().size(); k++) {
							XWPFParagraph para = td.getParagraphs().get(k);
							String s = para.getText();
							
							if(k != 0){
								sb.append(NEW_LINE);
							}
							sb.append(s);
							
							for(XWPFRun run : para.getRuns()){
								if(run.getEmbeddedPictures().size()>0){
									XWPFPicture picture = run.getEmbeddedPictures().get(0);
									byte[] content = picture.getPictureData().getData();
									
									PictureInfo pic = new PictureInfo();
									pic.setData(Arrays.copyOf(content, content.length));
									pic.setFileName(picture.getPictureData().getFileName());
									
									if(i == QuestionAttrHandler.QUESTIONTITLE_INDEX)
										question.getTitlePictureList().add(pic);
									if(i == QuestionAttrHandler.ANALYSIS_INDEX)
										question.getAnalysisPictureList().add(pic);
								}
							}
						} 
					} 
					QuestionAttrHandler.setQuestionAttribute(question, i, replaceStrangeChar(sb.toString()));
				}
				if(flag)
					QuestionAttrHandler.checkQuestionAttribute(question, info);
				
				list.add(question);
				infoList.add(info);
				count++;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static ExamInfo readExamFromDocx(InputStream in, List<ErrorInfo> infoList,boolean isreal) {
		byte[] bytes= null;
		byte[] copy = null;
		try {
			bytes = ByteToInputStream.input2byte(in);
			copy = Arrays.copyOf(bytes, bytes.length);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ExamInfo examInfo = new ExamInfo();
		try {
			// 载入文档
			XWPFDocument xwpf = new XWPFDocument(ByteToInputStream.byte2Input(bytes));
			Iterator<XWPFTable> it = xwpf.getTablesIterator();
			
			if(it.hasNext()){
				XWPFTable tb = (XWPFTable) it.next();
				
				ErrorInfo info = new ErrorInfo(0);
				int rows = tb.getNumberOfRows();
				if(rows != (isreal?REAL_EXAM_ROW:EXAM_ROW)){
					info.addExamTotalRowErrorInfo();
				}else{
					// 迭代行，默认从0开始
					boolean flag = true;
					for (int i = 0; i < tb.getNumberOfRows(); i++) {
						XWPFTableRow tr = tb.getRow(i);
						
						int cols = tr.getTableCells().size();
						if(cols != COL){
							flag = false;
							info.addQuestionTotalColumnErrorInfo(i);
							break;
						}
						
						StringBuilder sb = new StringBuilder();
						// 迭代列，默认从1开始
						for (int j = 1; j < COL; j++) {
							XWPFTableCell td = tr.getCell(j);// 取得单元格
							// 取得单元格的内容
							for (int k = 0; k < td.getParagraphs().size(); k++) {
								XWPFParagraph para = td.getParagraphs().get(k);
								String s = para.getText();
								if(k != 0){
									sb.append(NEW_LINE);
								}
								sb.append(s);
							} 
						} 
						ExamAttrHandler.setExamAttribute(examInfo, i, replaceStrangeChar(sb.toString()));
					}
					if(flag)
						ExamAttrHandler.checkExamAttribute(examInfo, info);
				}
				List<QuestionInfo> questionList = readQuestionListFromDocx(ByteToInputStream.byte2Input(copy), infoList, true);
				if(!info.isFlag())
					infoList.add(info);
				examInfo.setQuestionList(questionList);
				
				ExamAttrHandler.checkExamOtherAttribute(examInfo, info);
			}else{
				//试卷信息为空error
				ErrorInfo info = new ErrorInfo(0);
				info.addExamInfoEmptyErrorInfo();
				infoList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examInfo;
	}
	
	public static String replaceStrangeChar(String str){
		return str.replaceAll("", "");
	}
	
	public static String[] splitSemicolon(String str){
		if(str == null){
			return null;
		}
		String strs[] = str.split("；");
		if(strs.length == 1){
			strs = str.split(";");
		}
		return strs;
	}

}
