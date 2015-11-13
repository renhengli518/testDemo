package com.codyy.commons.utils;

import org.apache.log4j.Logger;

import com.codyy.commons.servlet.ImageServlet;

/**
 * @author xierongbing
 * @date 2015年10月15日 下午5:10:09 
 * @description 
 */

public class TimeCount {
	private static Logger logger = Logger.getLogger(ImageServlet.class);
	/**
	 * 每个线程持有一个时间
	 */
	public static ThreadLocal<Long> count = new ThreadLocal<Long>();
	
	public static void startCountTime(){
		count.set(System.currentTimeMillis());
	}
	
	public static void endCountTime(String action){
		long start = count.get();
		long end = System.currentTimeMillis();
		logger.debug("======================================================"+action+" cost time: "+(end-start)*1.0/1000+"s");
		
		startCountTime();
	}
}
