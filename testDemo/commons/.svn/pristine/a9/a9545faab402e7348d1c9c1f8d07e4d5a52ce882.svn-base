/**
 * HttpUtils.java
 * com.codyy.commons.utils
 * Function：  
 *
 * @author   xiongping
 * Copyright (c) 2015, Codyy All Rights Reserved.
*/

package com.codyy.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * ClassName:HttpUtils
 * Function: TODO
 *
 * @author   xiongping
 * @Date	 2015	2015年9月8日		下午2:19:15
 *
 */

public class HttpUtils {
	public MultipartHttpServletRequest createMultipartHttpServletRequest(HttpServletRequest request){
		CommonsMultipartResolver resolver = null;
		MultipartHttpServletRequest multiRequest = null;
		resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(20 * 1024 * 1024);//20M
		resolver.setServletContext(request.getSession().getServletContext());
		resolver.setMaxInMemorySize(1024 * 1024);
		multiRequest = resolver.resolveMultipart(request);
		return multiRequest;
	}
	/**
	 * downLoadExcel:
	 * 下载临时的excel并删除该excel
	 * 或者不下载临时的excel直接删除该excel
	 * @param response
	 * @param basePath
	 * @param fileName
	 * @param downFileFlag
	 * @author   xiongping
	*/
	
	public void downLoadExcel(HttpServletResponse response, String basePath, String fileName, String downFileFlag) {
		ServletOutputStream out = null;
		FileInputStream inputStream = null;
		File file = new File(basePath+fileName);
		if (!file.exists()){
			throw new NullPointerException("指定文件" + basePath+fileName + "不存在");
		}
		try {
			out = response.getOutputStream();
			if(!StringUtils.equals(downFileFlag, "false")){
				response.setContentType("application/x-msdownload");  
				response.setHeader("Content-Disposition", "attachment;fileName="+"errorInfo.xls");  
				inputStream = new FileInputStream(file);
				int b = 0;  
	            byte[] buffer = new byte[2048];  
	            while ((b = inputStream.read(buffer)) != -1){  
	                out.write(buffer,0,b);  
	            }  
			}else{
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("content-type", "application/json;charset=UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				response.setDateHeader("Expires", 0);
				out.write(0);
			}
            out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(inputStream);
			if (file.exists()){
				file.delete();
			}
		}
	}
}
