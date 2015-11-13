package com.codyy.oc.onlinetest.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.codyy.commons.sso.SessionUser;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.commons.web.CustomMultipartResolver;
import com.codyy.oc.BaseController;
import com.codyy.oc.base.parse.ErrorInfo;
import com.codyy.oc.base.parse.ExamInfo;
import com.codyy.oc.base.parse.ReadWordUtil;
import com.codyy.oc.onlinetest.service.ExamService;

/**
 * 
 * @author xiaokan
 *
 */
public class BaseOnlineTestController extends BaseController{
    
	@Autowired
	private ExamService examService;
	
	public Map<String, Object> uploadExamBase(HttpServletRequest request, HttpServletResponse response,Boolean isreal,String type) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		CustomMultipartResolver resolver = new CustomMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(500*1024*1024); 
		resolver.setServletContext(request.getSession().getServletContext());
		resolver.setMaxInMemorySize(5*1024*1024);
		response.setContentType("text/html;charset=UTF-8");
		MultipartHttpServletRequest multiRequest = null ;
		
		try {
			multiRequest = resolver.resolveMultipart(request);
		} catch (MaxUploadSizeExceededException e) {
			result.put("flag", false);
			result.put("message", "文件过大！");
			return result ;
		}
		
		CommonsMultipartFile file  = (CommonsMultipartFile) multiRequest.getFile("uploadFile");
		String fileName 		   = file.getOriginalFilename() ;
		String suffix			   = StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, "."),fileName.length());
		String userType 		   = StringUtils.substring(fileName, 0, StringUtils.lastIndexOf(fileName, ".")) ;
		InputStream in 			   = file.getInputStream() ;
		
		String tempName 	= UUIDUtils.getUUID() ;
		String tempPath 	= request.getServletContext().getRealPath("/public/common/upload_modal") + File.separator + tempName + suffix ;
		File descFile 		= new File(tempPath) ;
		
		// ==== 拷贝文件流到临时目录
		FileUtils.copyInputStreamToFile(in, descFile);
		
		// === 文件上传完毕，开始解析Excel
		 List<ErrorInfo> infoList = new ArrayList<ErrorInfo>();
		 ExamInfo examinfo = new ExamInfo();
		 InputStream fin = new FileInputStream(descFile);
		 if((suffix.replace(".", "")).equalsIgnoreCase(ReadWordUtil.WORD_TYPE_DOC)){
			  examinfo = ReadWordUtil.readExam(fin, ReadWordUtil.WORD_TYPE_DOC, infoList,isreal);
		 }else{
			 examinfo = ReadWordUtil.readExam(fin, ReadWordUtil.WORD_TYPE_DOCX, infoList,isreal);
		 }
		 	SessionUser sessionUser = getSessionUser(request);
			
			result = examService.uploadExam(examinfo, infoList,sessionUser.getUserId(),sessionUser.getSchoolId(),sessionUser.getAreaId(),sessionUser.getUserType(),type);
		// === 文件导入完毕后，删除临时文件
		FileUtils.deleteQuietly(descFile) ;
        
		return result ;
	}
		
	
}
