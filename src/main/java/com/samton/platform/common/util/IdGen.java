package com.samton.platform.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 */
public class IdGen {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	/**
	 * 获取年月日随机数字
	 */
	public static String getYmdRandom(){
		DecimalFormat df = new DecimalFormat("0000");
		return DateUtil.getYear()+ DateUtil.getMonth()+ DateUtil.getDay()+df.format(RandomUtils.nextInt(1000));
	}

	/**
	 * 获取编号，如：ORD20190509160131817
	 * @param headStr
	 * @return
	 */
	public static String getBusinessCode(String headStr){
		if(null == headStr) headStr = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return headStr+""+sdf.format(new Date())+ new DecimalFormat("0000").format(RandomUtils.nextInt(1000));
	}

	/**
	 * 使用SecureRandom随机生成Int.
	 */
	public static long randomInt() {
		return Math.abs(random.nextInt());
	}

	/**
	 * 获取唯一的序列号
	 * @return
	 */
	public static String getUniqueCode(){
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sDateFormat.format(new Date()) + StringUtils.leftPad(String.valueOf(Math.abs(random.nextInt() / 10000)), 6, "0");
	}

	public static void main(String[] args) {
		String cz = IdGen.getBusinessCode("CZ");
		System.out.println(cz);
	}
	
}
