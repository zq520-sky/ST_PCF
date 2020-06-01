package com.samton.platform.framework.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.OutputStream;
import java.util.Collection;

/**
 * 
 * @Description:JSON工具类
 * @author:     Alex
 * @date:        2017年2月23日 上午10:31:03
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class JsonUtil {

	private static Log log = LogFactory.getLog(JsonUtil.class);

	private static ObjectMapper objectMapper = null;

	static {
		objectMapper = new ObjectMapper();
		// objectMapper.setDateFormat(new
		// SimpleDateFormat(FormatUtil.DATE_FORMAT_LONG));
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

	public static String stringify(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static String stringify(Object object, String... properties) {
		try {
			return objectMapper.writer(
					new SimpleFilterProvider().addFilter(
							AnnotationUtils.getValue(AnnotationUtils.findAnnotation(
													object.getClass(), JsonFilter.class))
											.toString(), SimpleBeanPropertyFilter
											.filterOutAllExcept(properties)))
					.writeValueAsString(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static void stringify(OutputStream out, Object object) {
		try {
			objectMapper.writeValue(out, object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void stringify(OutputStream out, Object object, String... properties) {
		try {
			objectMapper.writer(new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(
																				AnnotationUtils.findAnnotation(
																						object.getClass(), JsonFilter.class))
																					.toString(), SimpleBeanPropertyFilter
																					.filterOutAllExcept(properties)))
						.writeValue(out, object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static <T> T parse(String json, Class<T> clazz) {
		if (json == null || json.length() == 0) {
			return null;
		}

		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * 把数据对象转换成json字符串
	 * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
	 * 数组对象形如：[{}, {}, {}, ...]
	 * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}
	 * @param object
	 * @return
	 */
	public static String getJSONString(Object object){
		String jsonString = null;
		//日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		if(object != null){
			if(object instanceof Collection || object instanceof Object[]){
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			}else{
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}

}