package com.codyy.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;



/**
 * @author xierongbing
 * @date 2015年7月30日 下午4:23:30 
 * @description 
 * 用于封装关于Map的操作
 */

public final class MapUtil {
	
	public static <K,V> Map<K,V> newHashMap(){
		return new HashMap<K, V>();
	}
	
	public static <K,V> Map<K,V> newTreeMap(){
		return new TreeMap<K, V>();
	}
	
	public static <K,V> Map<K,V> newConcurrentHashMap(){
		return new ConcurrentHashMap<K, V>();
	}
	
	public static <K,V> Map<K,V> newLinkedHashMap(){
		return new LinkedHashMap<K, V>();
	}
	
	/**
	 * @author xierongbing
	 * @date 2015年7月30日 下午4:28:31
	 * @param map
	 * @param key
	 * @param value
	 * @description
	 * 对数据根据key进行分组
	 */
	public static <K,V> void groupValue(Map<K,List<V>> map, K key, V value){
		List<V> list = map.get(key);
		if(list == null){
			list = new ArrayList<V>();
			map.put(key, list);
		}
		list.add(value);
	}
	
	/**
	 * @author xierongbing
	 * @date 2015年9月22日 上午9:14:38
	 * @param map
	 * @param names
	 * @return Map<String,String>
	 * @description
	 * HttpServletRequest 中的Map<String,String[]> request.getParameterMap() 可以获取所有的参数数据
	 * 获取其中部分数据,转化成Map<String,Object> 
	 */
	public static Map<String,Object> generateParamMap(Map<String,String[]> map, String...names){
		Map<String,Object> paramMap = MapUtil.newHashMap();
		String[] vals = null;
		for(String name : names){
			vals = map.get(name);
			if(vals == null){
				paramMap.put(name, null);
			}else{
				paramMap.put(name, vals[0]);
			}
		}
		return paramMap;
	}
}
