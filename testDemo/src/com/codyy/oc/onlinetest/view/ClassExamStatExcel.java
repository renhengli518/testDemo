package com.codyy.oc.onlinetest.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.codyy.oc.onlinetest.entity.ExamQuestionStatistics;
import com.codyy.oc.onlinetest.entity.ExamStudentStatistic;
import com.codyy.oc.onlinetest.entity.ExamTaskStatistics;

/**
 * 定义导出模板
 * @author zhangshuangquan
 *
 */
public class ClassExamStatExcel extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String className = (String) model.get("className");
		ExamTaskView examTaskView = (ExamTaskView) model.get("examTaskView");
		List<ExamTaskStatistics> examTaskStatistics = (List<ExamTaskStatistics>) model.get("examTaskStatistics");
		List<ExamQuestionStatistics> examQuestionStatistics = (List<ExamQuestionStatistics>) model.get("examQuestionStatistics");
		List<ExamStudentStatistic> examStudentStatistics = (List<ExamStudentStatistic>) model.get("examStudentStatistics");
	    
		HSSFSheet sheet = workbook.createSheet("班级测试统计");
        sheet.setDefaultColumnWidth(12);
		
        HSSFCell cell = getCell(sheet, 0, 0);
        cell.setCellValue(examTaskView.getTitle());
        
        getCell(sheet, 1, 0).setCellValue("年级：");
        getCell(sheet, 1, 1).setCellValue(examTaskView.getClasslevelName());
        getCell(sheet, 1, 2).setCellValue("学科：");
        getCell(sheet, 1, 3).setCellValue(examTaskView.getSubjectName());
        getCell(sheet, 1, 4).setCellValue("试卷类型：");
        getCell(sheet, 1, 5).setCellValue(examTaskView.getExamTypeName());
        getCell(sheet, 1, 6).setCellValue("测试人数：");
        getCell(sheet, 1, 7).setCellValue(examTaskView.getFinishedCount()+" / "+ examTaskView.getAssignedCount());
        getCell(sheet, 1, 8).setCellValue("答题时长：");
        getCell(sheet, 1, 9).setCellValue(examTaskView.getAnswerTime()+"分钟");
        getCell(sheet, 1, 10).setCellValue("试卷总分：");
        getCell(sheet, 1, 11).setCellValue(examTaskView.getScore()+"分");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        getCell(sheet, 2, 0).setCellValue("开始时间：");
        getCell(sheet, 2, 1).setCellValue(sdf.format(examTaskView.getStartTime()));
        getCell(sheet, 2, 2).setCellValue("结束时间：");
        getCell(sheet, 2, 3).setCellValue(sdf.format(examTaskView.getFinishedTime()));

        
        //班级统计
        classExamStatistics(sheet, examTaskStatistics);
	    
      
        getCell(sheet, 7+examTaskStatistics.size(), 0).setCellValue("班级：");
        getCell(sheet, 7+examTaskStatistics.size(), 1).setCellValue(className);
        
        
        //正确率统计
        classExamRightStatistics(sheet, examQuestionStatistics, examTaskStatistics.size());
	
        int count = examTaskStatistics.size() + examQuestionStatistics.size();
        //学生统计
        studentExamStatistics(sheet, examStudentStatistics, count);
	
	}
    
	//学生统计
	private void studentExamStatistics(HSSFSheet sheet, List<ExamStudentStatistic> examStudentStatistics, int count) {
		HSSFCell cell = getCell(sheet, 12+count, 0);
        cell.setCellValue("学生统计");
        getCell(sheet, 13+count, 0).setCellValue("姓名");
        getCell(sheet, 13+count, 1).setCellValue("得分");
        getCell(sheet, 13+count, 2).setCellValue("答题数");
        getCell(sheet, 13+count, 3).setCellValue("正确率");
        
       for (int i = 0; i < examStudentStatistics.size(); i++) {
    	   ExamStudentStatistic stuStatistic = examStudentStatistics.get(i);
    	   HSSFRow sheetRow = sheet.createRow(i + 14+count);
    	   sheetRow.createCell(0).setCellValue(stuStatistic.getBaseUserName());
    	   sheetRow.createCell(1).setCellValue(stuStatistic.getScore());
    	   sheetRow.createCell(2).setCellValue(stuStatistic.getAnswerCount()+" / "+stuStatistic.getTotalCount());
    	   sheetRow.createCell(3).setCellValue(stuStatistic.getRightRate()+"%");	   
	   }
       	
	}

	//正确率统计
	private void classExamRightStatistics(HSSFSheet sheet, List<ExamQuestionStatistics> examQuestionStatistics,
			int classCount) {
		HSSFCell cell = getCell(sheet, 9+classCount, 0);
        cell.setCellValue("正确率统计");
        getCell(sheet, 10+classCount, 0).setCellValue("题号");
        getCell(sheet, 10+classCount, 1).setCellValue("正确率");
        
        for (int i = 0; i < examQuestionStatistics.size(); i++) {
        	ExamQuestionStatistics quesStatis = examQuestionStatistics.get(i);
        	HSSFRow sheetRow = sheet.createRow(i + 11+classCount);
        	sheetRow.createCell(0).setCellValue(quesStatis.getSort());
        	sheetRow.createCell(1).setCellValue(quesStatis.getRightRate()+"%");
		}
		
	}

	//班级统计
	private void classExamStatistics(HSSFSheet sheet, List<ExamTaskStatistics> examTaskStatistics) {
		HSSFCell cell = getCell(sheet, 4, 0);
        cell.setCellValue("班级统计");
        getCell(sheet, 5, 0).setCellValue("来源");
        getCell(sheet, 5, 1).setCellValue("答题人数");
        getCell(sheet, 5, 2).setCellValue("最高得分");
        getCell(sheet, 5, 3).setCellValue("最低得分");
        getCell(sheet, 5, 4).setCellValue("平均得分");
        getCell(sheet, 5, 5).setCellValue("通过率");
        
        //每个班级
        for (int i = 0; i < examTaskStatistics.size(); i++) {
			ExamTaskStatistics classStatis = examTaskStatistics.get(i);
			HSSFRow sheetRow = sheet.createRow(i + 6);
			sheetRow.createCell(0).setCellValue(classStatis.getClasslevelName()+classStatis.getClassName());
			sheetRow.createCell(1).setCellValue(classStatis.getCommitCnt());
			sheetRow.createCell(2).setCellValue(classStatis.getHighestScore());
			sheetRow.createCell(3).setCellValue(classStatis.getLowestScore());
			sheetRow.createCell(4).setCellValue(classStatis.getAvgScore());
			sheetRow.createCell(5).setCellValue(classStatis.getPassRate()+"%");	
		}
        
	}

}
