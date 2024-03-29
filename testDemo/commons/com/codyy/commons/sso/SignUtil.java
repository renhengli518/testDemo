package com.codyy.commons.sso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.codyy.commons.utils.SecurityUtil;

/**
 * 签名工具类
 */
public class SignUtil {

	/**
	 * 用于OAuth2中计算参数的签名 <br>
	 * 将参数名按照字母顺序排列, 用&连接参数, 用=连接参数名和参数值, 最后连接签名用的key <br>
	 * 将连接后的字符串取md5为签名后的值
	 * 参数值为null或空字符串的键值不参与计算
	 * @param map
	 * @param key
	 * @return
	 */
	public static String buildSign(TreeMap<String, String> map, String key) {
		StringBuffer buildString = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (StringUtils.isNotBlank(entry.getValue())){
				if (buildString.length() > 0) {
					buildString.append('&');
				}
				buildString.append(entry.getKey());
				buildString.append('=');
				buildString.append(entry.getValue());
			}
		}
		buildString.append(key);
		String md5 = SecurityUtil.MD5String(buildString.toString());
		return md5;
	}
	
	/**
	 * 将map中的参数构建为url
	 * @param map
	 * @param sign
	 * @return
	 */
	public static String buildUrl(TreeMap<String, String> map, String sign) {
		StringBuffer buildString = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (buildString.length() > 0) {
				buildString.append('&');
			}
			buildString.append(entry.getKey());
			buildString.append('=');
			try {
				buildString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		buildString.append("&sign=" + sign);
		return buildString.toString();
	}

	public static SessionUser pareseSessionUser(String str) {
		SessionUser user = JSON.parseObject(str, SessionUser.class);
		return user;
	}

	public static String buildJSONString(SessionUser user) {
		String r = JSON.toJSONString(user);
		return r;
	}

}
