package com.samton.platform.framework.util;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description:公用类
 * @author: shenchu
 * @date: 2017年1月18日 下午4:29:19
 * Copyright (c) 2016, Samton. All rights reserved
 */
@SuppressWarnings({"rawtypes"})
public class Public {
	
	/**
	 * 
	 * @Title:        getParamValue 
	 * @Description:  得到系统参数表中对应的值
	 * @param:        @param param_name
	 * @param:        @param servletContext
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:38:22
	 */
	public static String getParamValue(String param_name, ServletContext servletContext) {
		JdbcTemplate jdbc = (JdbcTemplate) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("jdbcTemplate");
		List list = jdbc.queryForList("SELECT param_value FROM t_sys_param WHERE state = 1 AND param_name = ? ", param_name);
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			return map.get("param_value").toString();
		}
		return null;
	}

	/**
	 * 对传入字符串进行安全保护
	 *
	 * @param content
	 *            传入的字符串
	 * @return content
	 */
	public static String filterContent(String content) {

		// "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		if ((content == null) || ("".equals(content)))
			return "";
		String flt = "\"|'|\\|%|;|or|and|exec|script|--|+";
		String filter[] = flt.split("\\|");
		for (int i = 0; i < filter.length; i++) {
			content = content.replace(filter[i], "");
		}
		return content;
	}

	/*
	 * 将全角字符转换成半角字符
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * 判断字符串中是否为数字
	 *
	 * @param s
	 * @return true:串为数字 false:
	 */
	public static boolean IsInteger(String s) {
		if(!Public.isNotNull(s))return false;
		s = s.trim();
		int vl = s.length();
		for (int i = 0; i < vl; i++) {
			char c = s.charAt(i);
			if (!(c >= '0' && c <= '9')) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @Title:        isPositiveInteger
	 * @Description:  正整数判断
	 * @param:        @param s
	 * @param:        @return
	 * @return:       boolean
	 * @author        yhb
	 * @Date          2018年3月6日 上午10:07:41
	 */
	public static boolean isPositiveInteger(String s) {
		if(!Public.isNotNull(s))return false;
		return Pattern.matches("^[1-9]\\d*$", s.trim());
	}

	/**
	 * 判断字符串是否是号码格式
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s){
		return Pattern.matches("^[0-9]{3,4}\\d{6,9}$", s);
	}

	/**
	 * 判断字符串中是否为小数
	 *
	 * @param s
	 * @return true:串为小数 false:
	 */
	public static boolean IsNumeric(String s) {
		s = s.trim();
		int vl = s.length();
		for (int i = 0; i < vl; i++) {
			char c = s.charAt(i);
			if (!((c >= '0' && c <= '9') || c == '.')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否为网址格式
	 * @param s
	 * @return true : 为网址格式 false;
	 */
	public static boolean IsUrl(String s){
		return Pattern.matches("^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$", s);
	}

	/**
	 * 判断是否为400号
	 *
	 * @param s
	 * @return true: 为400号码 false:
	 */
	public static boolean IsEps400(String s) {
		return Pattern.matches("^400\\d{7}$", s) || Pattern.matches("^1010\\d{4}$", s) ;
	}

	/**
	 *
	 * @Title:        IsMoney
	 * @Description:  钱数，必须是非负整数或小数，小数点后最多2位
	 * @param:        @param s
	 * @param:        @return
	 * @return:       boolean
	 * @author        yhb
	 * @Date          2017年4月12日 下午2:54:14
	 */
	public static boolean IsMoney(String s) {
		return Pattern.matches("^[0-9]{1,9}([.]{1}[0-9]{1,2})?$", s);
	}


	/**
	 * 判断是否是手机号码
	 * @param s
	 * @return
	 */
	public static boolean IsMobile(String s) {
		return Pattern.matches("^1[3456789]\\d{9}$", s);
	}

	/**
	 * 判断是否是联系号码
	 * @param s
	 * @return
	 */
	public static boolean IsContactNum(String s) {
		return Pattern.matches("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)", s)
				|| Pattern.matches("((\\d{12})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)", s.replace("-",""));
	}

	/**
	 * 判断是否电话号码（包括手机和固话）
	 * @param s
	 * @return
	 */
	public static boolean IsPhoneNum(String s){
		return IsMobile(s) || IsContactNum(s);
	}


	/**
	 * 判断是不是时间格式23：59
	 * @param s
	 * @return
	 */
	public static boolean IsTime(String s){
		return Pattern.matches("^([01][0-9]|2[0-3]|[0-9])\\:[0-5][0-9]$", s);
	}

	/**
	 * 判断是不是时间格式23:59:59
	 * @param s
	 * @return
	 */
	public static boolean IsTimeFull(String s){
		return Pattern.matches("^(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$",s);
	}

	/**
	 * 判断是不是日期格式2012-09-23
	 */
	public static boolean IsDate(String s){
		return Pattern.matches("^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2})", s);
	}

	/**
	 * 判断是不是时间戳格式2012-09-23 23:59:59
	 */
	public static boolean IsTimestamp(String s){
		return Pattern.matches("^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2}) ([01][0-9]|2[0-3]|[0-9])\\:[0-5][0-9]:[0-5][0-9]", s);
	}
	/**
	 * 判断是不是日期时间格式2012-09-23 23:59
	 */
	public static boolean IsDateTime(String s){
		return Pattern.matches("^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2}) ([01][0-9]|2[0-3]|[0-9])\\:[0-5][0-9]", s);
	}

	/**
	 * 判断是否为此号段号码
	 * @param segmentCode 号段类型
	 * @param s
	 * @return true: 为此号段号码 false:
	 */
	public static boolean IsSegmentCode(String segmentCode,String s) {
		return Pattern.matches("^"+segmentCode.trim()+"\\d{6}$", s) || Pattern.matches("^1010\\d{4}$", s) ;
	}
	/**
	 * 得到此号段类型的id
	 * @param segmentCode 号段类型
	 * @return true: 为此号段号码 false:
	 */
	public static int getSegmentId(String segmentCode) {
		if (segmentCode.trim().startsWith("4006")) {
			return 1;
		}
		if (segmentCode.trim().startsWith("4000")) {
			return 2;
		}
		if (segmentCode.trim().startsWith("4008")) {
			return 3;
		}
		if (segmentCode.trim().startsWith("4009")) {
			return 4;
		}
		if (segmentCode.trim().startsWith("4001")) {
			return 5;
		}
		if (segmentCode.trim().startsWith("4007")) {
			return 6;
		}
		if (segmentCode.trim().startsWith("1010")) {
			return 7;
		}
		return 0;
	}


	/**
	 * 判断是否为防伪码
	 *
	 * @param s
	 * @return true :防伪码 false:
	 */
	public static boolean IsAntiCode(String s) {
		if ((s == null) || ("".equals(s)))
			return true;
		return Pattern.matches("^\\d{0,30}$", s);
	}

	/**
	 * 加下划线模糊搜索400号码
	 * @param @param s
	 * @param @return
	 * @return boolean
	 * @author wangjinyong
	 */
	public static boolean IsLikeEps400(String s) {
		if ((s == null) || ("".equals(s)))
			return true;
		return Pattern.matches("^400[0-9_]{7}$", s);
	}

	/**
	 * 判断是否为目的码
	 *
	 * @param s
	 * @return
	 */
	public static boolean IsTarget(String s) {
		if (s.equals(""))
			return true;
		if ((s.length() >= 7) && (s.length() < 16))
			return IsInteger(s);
		return false;
	}


	/**
	 * 验证上传文件是否符合格式
	 * @return true符合
	 */
	public static boolean checkFileType(String upLoadFile,String fileType) {
		if (upLoadFile == null || "".equals(upLoadFile.trim())) {
			return false;
		} else {
			if(upLoadFile.toUpperCase().equals(fileType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得指定日期的后num天 的日期
	 *
	 * @param specifiedDay
	 * @return
	 */
	public static Date getAfterDay(String specifiedDay, int num) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + num);
		return c.getTime();
	}

	/**
	 * 获取指定时间的后几分钟时间，如果指定时间为空，则为当前系统时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getAfterMinute(Date date, int minute){
		if(date == null) date = new Date();
		long dateTime = date.getTime();
		dateTime = dateTime + minute*60*1000;
		date.setTime(dateTime);
		return date;
	}

	/**
	 * 得到所有的搜索条件
	 * @param request
	 * @return  返回搜索url
	 */
	public static String getSearchURL(HttpServletRequest request) {
		StringBuffer searchCondition = new StringBuffer();
		Map map = request.getParameterMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String)iterator.next();
			String[] values = (String[])map.get(key);
			for (int j=0;j<values.length;j++) {
				if(values[j] != null && !"".equals(values[j].trim()) && key.startsWith("search")){
					searchCondition.append(key).append("=").append(values[j]).append("&");
				}
			}
		}
		if (searchCondition != null && !"".equals(searchCondition.toString().trim())) {
			return searchCondition.toString();
		}
		return "";
	}
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	/**
	 * 得到对应时间的字符串
	 * @param pattern  时间格式
	 * @param date
	 * @return
	 */
	public static String getDateString(String pattern,Date date) {
		try {
			simpleDateFormat.applyPattern(pattern);
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 根据标准格式的时间字符串得到对应的Date对象,标准格式如下:"2005-03-23 13:34:56".
	 *
	 * @return 时间字符串对应的Date对象.
	 * @throws ParseException
	 */
	public static Date getDateFromStandardTime(String standardTime){
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(standardTime);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * 根据标准格式的时间字符串得到对应的Date对象,标准格式如下:"2005-03-23 13:34:56".
	 *
	 * @return 时间字符串对应的Date对象.
	 * @throws ParseException
	 */
	public static Date getDateFromStandardTime2(String standardTime){
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(standardTime);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 *
	 * @Title:        isNotNull
	 * @Description:  是否为空判断
	 * @param:        @param object
	 * @param:        @return
	 * @return:       boolean
	 * @author        yhb
	 * @Date          2017年11月9日 上午11:03:26
	 */
	public static boolean isNotNull(Object object) {
		try {
			if(object != null && !"".equals(object.toString().trim()))return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 *
	 * @Title:        isNull
	 * @Description:  是否为空判断
	 * @param:        @param object
	 * @param:        @return
	 * @return:       boolean
	 * @author        yhb
	 * @Date          2017年11月9日 上午11:03:47
	 */
	public static boolean isNull(Object object) {
		return !isNotNull(object);
	}

	/**
	 * 根据400号码计算其号段
	 * @param eps400
	 * @return
	 */
	public static int getSegmentByEps400(String eps400){
		if(eps400==null){
			throw new RuntimeException("400号码为空！");
		}
		if(eps400.startsWith("4006")){
			return 1;
		}
		else if(eps400.startsWith("4000")){
			return 2;
		}
		else if(eps400.startsWith("4008")){
			return 3;
		}
		else if(eps400.startsWith("4009")){
			return 4;
		}
		else if(eps400.startsWith("4001")){
			return 5;
		}
		else if(eps400.startsWith("4007")){
			return 6;
		}else if(eps400.startsWith("1010")){
			return 7;
		}else if(eps400.startsWith("6000")){
			return 0;
		}
		else{
			throw new RuntimeException("错误的400号码！");
		}
	}

	/**
	 * 根据400号码计算其运营商
	 * @param eps400
	 * @return
	 */
	public static int getIspByEps400(String eps400){
		if(eps400==null){
			throw new RuntimeException("400号码为空！");
		}
		if(eps400.startsWith("4006")){
			return 1;
		}
		else if(eps400.startsWith("4000")){
			return 1;
		}
		else if(eps400.startsWith("4008")){
			return 3;
		}
		else if(eps400.startsWith("4009")){
			return 3;
		}
		else if(eps400.startsWith("4001")){
			return 2;
		}
		else if(eps400.startsWith("4007")){
			return 2;
		}
		else if(eps400.startsWith("1010")){
			return 1;
		}
		else{
			throw new RuntimeException("错误的400号码！");
		}
	}


	/**
	 * 统计字符串字数
	 * @return
	 */
	public static int dataLength(String data){
		int length=0;
		if(data.length() >= 1){
			length = 1;
		}
		for (int i=1;i<data.length();i++){
			if(isChinese(data.charAt(i)+"")){
				length=length+1;
			}else if(isChar(data.charAt(i)+"")){
				if(!isChar(data.charAt(i-1)+"")){
					length=length+1;
				}
			}else if(isNum(data.charAt(i)+"")){
				if(!isNum(data.charAt(i-1)+"")){
					length=length+1;
				}
			}
		}
		return length;
	}

	/**
	 * 判断是否为汉字
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str){
		Pattern p = null; //正则表达式
		Matcher m = null; //操作的字符串
		p = Pattern.compile("[\u4E00-\u9FA5]");
		m = p.matcher(str);
		return m.matches();

	}

	/**
	 * 判断是否为字母
	 * @param str
	 * @return
	 */
	public static boolean isChar(String str){
		Pattern p = null; //正则表达式
		Matcher m = null; //操作的字符串
		p = Pattern.compile("[a-zA-Z]");
		m = p.matcher(str);
		return m.matches();

	}

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		Pattern p = null; //正则表达式
		Matcher m = null; //操作的字符串
		p = Pattern.compile("[0-9]");
		m = p.matcher(str);
		return m.matches();

	}

	/**
	 *
	 * @Title:        isNumeric
	 * @Description:  用JAVA自带的函数
	 * @param:        @param str
	 * @param:        @return
	 * @return:       boolean
	 * @author        唐伟春
	 * @Date          2018年4月9日 下午2:20:09
	 */
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 通过key得到map里的值是否为double类型,如果不是则让对应key的vaule为null
	 */
	public static void getDoubel(Map searchMap,String key) {
		if(searchMap.get(key) != null && !"".equals(searchMap.get(key))) {
			try {
				Double.valueOf(searchMap.get(key).toString());
			}catch(Exception e){
				searchMap.put(key,null);
			}
		} else {
			searchMap.put(key,null);
		}
	}

	/**
	 * 通过key得到map里的值是否为Date类型,如果不是则让对应key的vaule为null
	 */
	public static void getDate(Map searchMap,String key) {
		if(searchMap.get(key) != null && !"".equals(searchMap.get(key))) {
			try {
				Public.simpleDateFormat.applyPattern("yyyy-MM-dd");
				Public.simpleDateFormat.parse(searchMap.get(key).toString());
			}catch(Exception e){
				searchMap.put(key,null);
			}
		} else {
			searchMap.put(key,null);
		}
	}

	/**
	 * 得到查询时间条件
	 * @param _dateStr
	 * @return
	 */
	public static String getBetweenSql(String _dateStr){
		String _sql = "";
		try {
			SimpleDateFormat _sdf = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat s_sdf = s_sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat e_sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			Date s_date = _sdf.parse(_dateStr);
			Calendar ca = Calendar.getInstance();
			ca.setTime(s_date);
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			_sql = " between '" + s_sdf.format(s_date) + "' and '" + e_sdf.format(ca.getTime())+ "' ";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return _sql;
	}

	/**
	 * 如果是统一查询400号码的格式
	 * @param eps400
	 * @param args
	 */
	public static void addEpsCriteria(String eps400,List<Object> args){
		if(eps400.trim().indexOf("*") > 0){
			args.add("%"+eps400.trim().replace("*", "%"));
		} else{
			args.add("%"+eps400.trim()+"%");
		}
	}


	/**
	 * 获取当月的第一天
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	public static String getFirstDayOfMonth(String yearMonth) {
		try {
			Date date = new SimpleDateFormat("yyyyMM").parse(yearMonth.replace("-", ""));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMinimum(Calendar.DAY_OF_MONTH));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
		}
		return null;
	}

	public static Timestamp getOffsetThirtyDay(Timestamp end) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -30);
		return new Timestamp(calendar.getTimeInMillis());
	}



	/**
	 * 替换换行
	 * @param str
	 * @return
	 */
	public static String getStringNoBlank(String str) {
		if(str!=null && !"".equals(str)) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			String strNoBlank = m.replaceAll("");
			return strNoBlank;
		}else {
			return str;
		}
	}

	/**
	 * 短信过滤关键字对象数据对象
	 */
	public static List<Map<String,Object>> sms_keys = null;
	/**
	 * app接口权限
	 */
	public static Map<String,String> appAuths = null;
	/**
	 * 证件类型数据对象
	 */
	public static List<Map<String,Object>> papers = null;

	/**
	 * 一级证件类型数据对象
	 */
	public static List<Map<String,Object>> supPapers = null;

	/**
	 * 下级证件类型数据对象
	 */
	public static List<Map<String,Object>> nextPapers = null;

	/**
	 * 得到所有一级证件数据
	 * @return
	 */
	public static List<Map<String,Object>> getSupPapers(){
		if(supPapers != null && supPapers.size() > 0){
			return supPapers;
		}
		if(papers != null){
			supPapers = new ArrayList<Map<String,Object>>();
			nextPapers = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < papers.size(); i++) {
				Map paper = papers.get(i);
				if(paper.get("sup_papers_id").equals(paper.get("papers_id"))){
					supPapers.add(paper);
				}else{
					nextPapers.add(paper);
				}
			}
			return supPapers;
		}
		return null;
	}

	/**
	 * 通过一级id得到对应下级
	 * @param sup_paper_id
	 * @return
	 */
	public static List<Map<String,Object>> getNextPapers(int sup_paper_id){
		if(nextPapers != null){
			List<Map<String,Object>> papers = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < nextPapers.size(); i++) {
				Map paper = nextPapers.get(i);
				if(!paper.get("sup_papers_id").equals(paper.get("papers_id")) && paper.get("sup_papers_id").equals(sup_paper_id)){
					papers.add(paper);
				}
			}
			return papers;
		}
		return null;
	}


	/**
	 * 是否有可用的条件
	 * @return
	 */
	public static boolean hasCondition(Object... conds) {
		for ( Object cond : conds ) {
			if( cond instanceof Integer ) {
				if ( cond!=null && (Integer)cond>-1 ) {
					return true;
				}
			} else if ( cond instanceof String ) {
				if ( Public.isNotNull(cond) ) {
					return true;
				}
			} else if ( cond instanceof Timestamp ) {
				if ( Public.isNotNull(cond) ) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 去掉字符串中的HTML代码
	 * @return
	 */
	public static String delHTMLTag(String inputString){
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
//			java.util.regex.Pattern p_html;
//			java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			// String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			// p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
			// m_html = p_html.matcher(htmlStr);

			// htmlStr = m_html.replaceAll(""); //过滤html标签
			// 过滤html标签
			htmlStr = htmlStr.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
					.replaceAll("</[a-zA-Z]+[1-9]?>", "");
			htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");// 去除字符串中的空格,回车,换行符,制表符
			htmlStr = htmlStr.replaceAll("&nbsp;", " ");// 去除字符串中的空格
			htmlStr = replaceHtmlNote(htmlStr);
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return getContent(textStr);// 返回文本字符串
	}

	/**
	 * 取出html中的注释标签
	 * @author 		:	GaoYang
	 * @param htmlString
	 * @return
	 */
	public static  String replaceHtmlNote(String htmlString){
		if(htmlString.indexOf("<!--")>=0&&htmlString.indexOf("-->")>0){
			return replaceHtmlNote(htmlString.substring(0,htmlString.indexOf("<!--"))+htmlString.substring(htmlString.indexOf("-->")+3));
		}else{
			return htmlString;
		}
	}

	/**
	 * html转义源符号
	 * @param s
	 * @return
	 */
	public static String getContent(String s) {
		s = s.replaceAll("&ensp;", " ");
		s = s.replaceAll("&nbsp;", " ");
		s = s.replaceAll("&emsp;", "　");
		s = s.replaceAll("&reg;", "®");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&ldquo;", "“");
		s = s.replaceAll("&rdquo;", "”");
		s = s.replaceAll("&quot;", "“");
		s = s.replaceAll("&rsquo;", "’");
		s = s.replaceAll("&lsquo;", "‘");
		s = s.replaceAll("&mdash;", "—");
		s = s.replaceAll("&ndash;", "–");
		s = s.replaceAll("&middot;", "·");
		s = s.replaceAll("&trade;", "™");
		s = s.replaceAll("&copy;", "©");
		s = s.replaceAll("&hellip;", "…");
		s = s.replaceAll("<br>","\r\n");
		s = s.replaceAll("<br/>","\r\n");
		s = s.replaceAll("<br />","\r\n");
		s = s.replaceAll("  ","　");
		s = s.replaceAll("&amp;", "&");
		return s;
	}

	/**
	 * MD5加密算法
	 * @param inStr
	 * @return
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++){
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16){
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}


	/**
	 * 验证时间是否有效 格式为：yyyy-MM-dd HH:mm:ss / yyyy/MM/dd HH:mm:ss
	 * @param times
	 * @return
	 */
	public static boolean IsRealTimestamp(String times){
		if(!IsTimestamp(times)){
			return false;
		}
		String dateStr = times.replaceAll("/", "-");

		String[] itemDate = dateStr.split("\\s+")[0].split("\\-");   //日期
		String[] itemTime = dateStr.split("\\s+")[1].split(":");     //时间
		//年月日判断
		if(Integer.parseInt(itemDate[1]) == 1 || Integer.parseInt(itemDate[1]) == 3
				|| Integer.parseInt(itemDate[1]) == 5 || Integer.parseInt(itemDate[1]) == 7
				|| Integer.parseInt(itemDate[1]) == 8 || Integer.parseInt(itemDate[1]) == 10
				|| Integer.parseInt(itemDate[1]) == 12){
			if(Integer.parseInt(itemDate[2]) > 31){
				return false;
			}
		} else if(Integer.parseInt(itemDate[1]) == 2){
			if(IsLeapYear(Integer.parseInt(itemDate[0]))){ //闰年,2月有29号
				if(Integer.parseInt(itemDate[2]) > 29){
					return false;
				}
			} else {
				if(Integer.parseInt(itemDate[2]) > 28){
					return false;
				}
			}
		} else if(Integer.parseInt(itemDate[1]) > 12){
			return false;
		} else if(Integer.parseInt(itemDate[1]) <= 0){
			return false;
		} else {
			if(Integer.parseInt(itemDate[2]) > 30){
				return false;
			}
		}

		//时分秒判断
		if(Integer.parseInt(itemTime[0]) > 23){
			return false;
		}
		if(Integer.parseInt(itemTime[1]) > 59 || Integer.parseInt(itemTime[2]) > 59){
			return false;
		}

		return true;
	}

	/**
	 * 验证时间是否有效 格式为：yyyy-MM-dd / yyyy/MM/dd
	 * @param times
	 * @return
	 */
	public static boolean IsRealDate(String times){
		if(!IsDate(times)){
			return false;
		}
		String dateStr = times.replaceAll("/", "-");

		String[] itemDate = dateStr.split("\\s+")[0].split("\\-");   //日期
		//年月日判断
		if(Integer.parseInt(itemDate[1]) == 1 || Integer.parseInt(itemDate[1]) == 3
				|| Integer.parseInt(itemDate[1]) == 5 || Integer.parseInt(itemDate[1]) == 7
				|| Integer.parseInt(itemDate[1]) == 8 || Integer.parseInt(itemDate[1]) == 10
				|| Integer.parseInt(itemDate[1]) == 12){
			if(Integer.parseInt(itemDate[2]) > 31){
				return false;
			}
		} else if(Integer.parseInt(itemDate[1]) == 2){
			if(IsLeapYear(Integer.parseInt(itemDate[0]))){ //闰年,2月有29号
				if(Integer.parseInt(itemDate[2]) > 29){
					return false;
				}
			} else {
				if(Integer.parseInt(itemDate[2]) > 28){
					return false;
				}
			}
		} else if(Integer.parseInt(itemDate[1]) > 12){
			return false;
		} else if(Integer.parseInt(itemDate[1]) <= 0){
			return false;
		} else {
			if(Integer.parseInt(itemDate[2]) > 30){
				return false;
			}
		}

		return true;
	}

	/**
	 * 验证时间是否有效 格式为：yyyy-MM-dd HH:mm / yyyy/MM/dd HH:mm
	 * @param times
	 * @return
	 */
	public static boolean IsRealHoursTime(String times){
		if(!Pattern.matches("^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2}) ([01][0-9]|2[0-3]|[0-9])\\:[0-5][0-9]", times)){
			return false;
		}

		String dateStr = times.replaceAll("/", "-");

		String[] itemDate = dateStr.split("\\s+")[0].split("\\-");   //日期
		String[] itemTime = dateStr.split("\\s+")[1].split(":");     //时间
		//年月日判断
		if(Integer.parseInt(itemDate[1]) == 1 || Integer.parseInt(itemDate[1]) == 3
				|| Integer.parseInt(itemDate[1]) == 5 || Integer.parseInt(itemDate[1]) == 7
				|| Integer.parseInt(itemDate[1]) == 8 || Integer.parseInt(itemDate[1]) == 10
				|| Integer.parseInt(itemDate[1]) == 12){
			if(Integer.parseInt(itemDate[2]) > 31){
				return false;
			}
		} else if(Integer.parseInt(itemDate[1]) == 2){
			if(IsLeapYear(Integer.parseInt(itemDate[0]))){ //闰年,2月有29号
				if(Integer.parseInt(itemDate[2]) > 29){
					return false;
				}
			} else {
				if(Integer.parseInt(itemDate[2]) > 28){
					return false;
				}
			}
		} else if(Integer.parseInt(itemDate[1]) > 12){
			return false;
		} else if(Integer.parseInt(itemDate[1]) <= 0){
			return false;
		} else {
			if(Integer.parseInt(itemDate[2]) > 30){
				return false;
			}
		}

		//时分秒判断
		if(Integer.parseInt(itemTime[0]) > 23){
			return false;
		}
		if(Integer.parseInt(itemTime[1]) > 59){
			return false;
		}

		return true;
	}

	//是否闰年
	public static boolean IsLeapYear(int i)
	{
		return ((i % 4 == 0) && (((i % 100 != 0) || (i % 400 == 0))));
	}

	/**
	 *
	 * @Title:        isLoginName
	 * @Description:  登录名是否合法判断
	 * @param:        @param loginName
	 * @param:        @return
	 * @return:       boolean
	 * @author        yhb
	 * @Date          2018年3月14日 下午3:24:58
	 */
	public static boolean isLoginName(String loginName){
		return Pattern.matches("^[\u4e00-\u9fa5@a-zA-Z0-9\\-\\一]+$", loginName)  ;
	}

	/**
	 *
	 * @Title:        isEmail
	 * @Description:  邮箱判断
	 * @param:        @param email
	 * @param:        @return
	 * @return:       boolean
	 * @author        yhb
	 * @Date          2018年3月14日 下午3:25:09
	 */
	public static boolean isEmail(String email){
		return Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
	}


	/**
	 * 获取指定长度的随机密码
	 * @Title:        getPassWD
	 * @Description:  TODO(这里用一句话描述这个方法的作用)
	 * @param:        @param len 密码长度
	 * @param:        @return
	 * @return:       String
	 * @throws
	 * @author        YangYangen
	 * @Date          2016-07-15
	 */
	public static String getPassWD(int len) {
		int num = 0;
		int count = len;
		int numCount = 0;//是否包含数字标记
		StringBuffer result = new StringBuffer();

		do{
			num = 0;
			count = len;
			result.delete(0, len);
			while(count > 0){
				num = (int)(Math.random()*100)+1+23;
				if(num > 49 && num <= 57){
					numCount++;
				}
				//a~z,A~Z 去除大O,小l
				if(((num > 49 && num <= 57) || (num >= 65 && num < 91) || (num >= 97 && num <123)) && num != 79 && num != 108){
					result.append((char)num);
					count--;
				}
			}
		}while(numCount == 0);

		return result.toString();
	}

	/**
	 *
	 * @Title: isNumBigSmall
	 * @Description: TODO(判断字符串中同时包含数字和大小写字母方法)
	 * @param @param str
	 * @param @return    设定文件
	 * @return boolean    返回类型  true： 都存在 false： 不存在
	 * @throws
	 */
	public static boolean isNumBigSmall(String str){
		boolean flag = false ;
		if(str==null || "".equals(str)){
			flag = false ;
		}
		Pattern numPatt = Pattern.compile("[A-Z]+");
		Pattern smallPatt = Pattern.compile("[a-z]+");
		Pattern bigPatt = Pattern.compile("[0-9]+");
		//Pattern s = Pattern.compile("\\p{Punct}+");
		Matcher numMach = numPatt.matcher(str);  //判断是否含有大写字符
		Matcher smallMatch= smallPatt.matcher(str); //判断是否含有小写字符
		Matcher bigMatch = bigPatt.matcher(str);//判断是否含有数字
		//Matcher m4 = s.matcher(password);//判断是否含有特殊字符
		flag = numMach.find(0)&&smallMatch.find(0)&&bigMatch.find(0);
		return flag ;
	}


	/**
	 *
	 * @Title:        checkFileValid
	 * @Description:  校验非法文件
	 * @param:        @param postfix
	 * @param:        @param response
	 * @param:        @throws Exception
	 * @return:       void
	 * @author        shenchu
	 * @Date          2017年7月13日 下午6:27:33
	 */
	public static  void checkFileValid (String postfix , String fileName , HttpServletResponse response) {

		//非法文件处理
		List<String> illegalSuffix = new ArrayList<String>();
		illegalSuffix.add(".js");
		illegalSuffix.add(".jsp");
		illegalSuffix.add(".exe");
		illegalSuffix.add(".php");
		illegalSuffix.add(".html");
		illegalSuffix.add(".asp");
		illegalSuffix.add(".net");
		illegalSuffix.add(".sql");
		illegalSuffix.add(".xml");
		illegalSuffix.add(".class");
		illegalSuffix.add(".java");
		illegalSuffix.add(".jspx");
		illegalSuffix.add(".aspx");
		illegalSuffix.add(".bat");
		illegalSuffix.add(".db");
		illegalSuffix.add(".sh");
		illegalSuffix.add(".so");

		if(!StringUtils.isEmpty(postfix) && illegalSuffix.contains(postfix.toLowerCase())){
			//ActionUtils.ajaxResultFail("您提交的附件不符合规范，请上传规范文件！", response);
			throw new RuntimeException("文件类型异常！文件名："+fileName);
		}

	}

	/**
	 * 字符串去空格
	 * @param object
	 * @return
	 */
	public static String trim(String object) {
		try {
			if(object != null){
				return object.trim();
			}
			else{
				return null ;
			}
		} catch (Exception e) {
			return null ;
		}
	}

	public static String replaceBlank(Object str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str.toString());
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static boolean isInclude(String arrayStr,String str,String separator){
		arrayStr = separator + arrayStr +separator;
		str = separator + str +separator;
		if(arrayStr.toLowerCase().indexOf(str.toLowerCase())>-1){
			return true;
		}
		return false;
	}

	/**
	 * EncoderByMd5
	 * @param buf
	 * @return
	 */
	public static String EncoderByMd5(String buf) {
		try {
			MessageDigest digist = MessageDigest.getInstance("MD5");
			byte[] rs = digist.digest(buf.getBytes("UTF-8"));
			StringBuffer digestHexStr = new StringBuffer();
			for (int i = 0; i < 16; i++) {
				digestHexStr.append(byteHEX(rs[i]));
			}
			return digestHexStr.toString();
		} catch (Exception e) {

		}
		return null;

	}

	public static String byteHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/**
	 * 获取len位数字编码
	 * @param len
	 * @return
	 */
	public static String getDigitCode(int len) {
		//生成随机6位数的短信验证码
		StringBuffer randomCode = new StringBuffer();
		for(int i=0;i<len;i++){
			randomCode.append((int)(Math.random()*9));
		}
		return randomCode.toString();
	}

	/**
	 *
	 * @Description: 将xml文件属性转成Map对象
	 * @param @param xml
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author huangqiuping
	 * @date 2019年4月29日 下午3:59:34
	 */
	public static Map<String, Object> xmlToMap(String xml) {
		Document doc = null;
		try {
			doc = (Document) DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null) return map;
		Element rootElement = doc.getRootElement();
		elementTomap(rootElement,map);
		return map;
	}

	public static void elementTomap(Element elmt, Map map) {
		if (null == elmt) {
			return;
		}
		String name = elmt.getName();
		if (elmt.isTextOnly()) {
			map.put(name, elmt.getText());
		} else {
			Map<Object, Object> mapSub = new HashMap<Object, Object>();
			List<Element> elements = (List<Element>) elmt.elements();
			for (Element elmtSub : elements) {
				elementTomap(elmtSub, mapSub);
			}
			Object first = map.get(name);
			if (null == first) {
				map.put(name, mapSub);
			} else {
				if (first instanceof List<?>) {
					((List) first).add(mapSub);
				} else {
					List<Object> listSub = new ArrayList<Object>();
					listSub.add(first);
					listSub.add(mapSub);
					map.put(name, listSub);
				}
			}
		}
	}

	/**
	 * 按字典排序进行参数拼接
	 * @param tm
	 * @return
	 */
	public static String getURLStrBySortDictKey(TreeMap<String, String> tm) {
		StringBuffer buf = new StringBuffer();
		for (Map.Entry<String, String> en : tm.entrySet()) {
			String name = en.getKey();
			String value = en.getValue();
			if (value != null && value.length() > 0 && !"null".equals(value)) {
				buf.append(name).append('=').append(value).append('&');
			}
		}
		String urlStr = buf.substring(0, buf.length() - 1);
		return urlStr;
	}


	/**
	 * 获取请求的Str
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public static String getReqStr(HttpServletRequest req) throws Exception{
		//输入流
		BufferedInputStream bi = null;
		//输出流
		InputStreamReader br = null;
		//获取的请求的Str字符串
		StringBuffer xmlSb = new StringBuffer();
		try {
			bi = new BufferedInputStream(req.getInputStream());
			br = new InputStreamReader(bi,"UTF-8");
			int size ;
			char c;
			while((size=br.read())!=-1){
				c = (char)size;
				xmlSb.append(c);
			}
			return xmlSb.toString();
		} catch (Exception e) {
			return "" ;
		} finally{
			if(bi!=null) {
				bi.close();
			}
			if(br!=null){
				br.close();
			}
		}
	}
}
