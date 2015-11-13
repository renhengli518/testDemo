package com.codyy.commons.utils;

import java.io.File;

/**
 * 文件路径工具类
 */
public class PathUtils {
	
	/**
	 * 构建文件所在详细路径
	 * @param uploadPath 上传server路径
	 * @param fileName 文件名
	 * @return
	 */
	public static String buildFilePath(String uploadPath, String fileName) {
		StringBuffer buffer = new StringBuffer();
		String md5 = SecurityUtil.MD5String(fileName);
		buffer.append(uploadPath);
		buffer.append(File.separatorChar);
		buffer.append(md5.substring(0, 2));
		buffer.append(File.separatorChar);
		buffer.append(md5.substring(2, 4));
		buffer.append(File.separatorChar);
		buffer.append(md5.substring(4, 6));
		buffer.append(File.separatorChar);
		buffer.append(fileName);
		return buffer.toString();
	}
}
