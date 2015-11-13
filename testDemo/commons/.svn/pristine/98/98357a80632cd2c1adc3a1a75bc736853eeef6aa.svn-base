package com.codyy.commons.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.codyy.commons.context.SpringContext;

public class RedisStreamingServerUtils {

	private static StringRedisTemplate template = SpringContext.getBean(StringRedisTemplate.class);

	private static final String AREA_KEY = "area:";
	private static final String PMS_KEY = "pmsServer:";

	public static void setAreaCache(String areaId, String streamingServerType, String basePmsServerId) {
		BoundHashOperations<String, String, String> opera = template.boundHashOps(AREA_KEY + areaId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("streamingServerType", streamingServerType);
		map.put("basePmsServerId", basePmsServerId);
		opera.putAll(map);
	}

	public static Map<String, String> getAreaCache(String areaId) {
		if(StringUtils.isNotBlank(areaId)){
			BoundHashOperations<String, String, String> opera = template.boundHashOps(AREA_KEY + areaId);
			Map<String, String> map = opera.entries();
			if(map != null && map.size()>0){
				map.put("serverAddress", getPmsServer(map.get("basePmsServerId")));
				return map;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public static void deleteAreaCache(String id) {
		template.delete(AREA_KEY + id);
	}

	public static void setPmsServer(String id, String address) {
		ValueOperations<String, String> opera = template.opsForValue();
		opera.set(PMS_KEY + id, address);
	}

	public static void deletePmsServer(String id) {
		template.delete(PMS_KEY + id);
	}

	public static String getPmsServer(String id) {
		ValueOperations<String, String> opera = template.opsForValue();
		return opera.get(PMS_KEY + id);
	}

}
