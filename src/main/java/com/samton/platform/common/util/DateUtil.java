package com.samton.platform.common.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:日期工具类
 * @author: 李建洲
 * @date: 2018-08-24 15:08:00
 * Copyright (c) 2018, Samton. All rights reserved
 */
public class DateUtil {

	private static ThreadLocal<Map<String, SimpleDateFormat>> dfThreadLocal = new ThreadLocal<Map<String, SimpleDateFormat>>(){
		protected synchronized Map<String, SimpleDateFormat> initialValue() {
			SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat datetimeNumFormat=new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			Map<String, SimpleDateFormat> map=new HashMap<String,SimpleDateFormat>();
			map.put("datetime", datetimeFormat);
			map.put("date", dateFormat);
			map.put("time", timeFormat);
			map.put("datetimeNum", datetimeNumFormat);
			return map;
		}
	};
	
	private static SimpleDateFormat getDatetimeDF() {
		  return dfThreadLocal.get().get("datetime");
	}
	
	private static SimpleDateFormat getDatetimeNumDF(){
		return dfThreadLocal.get().get("datetimeNum");
	}
	
	private static SimpleDateFormat getDateDF() {
		  return dfThreadLocal.get().get("date");
	}
	
	private static SimpleDateFormat getTimeDF() {
		  return dfThreadLocal.get().get("time");
	}
			
	public static Date now() {
		return new Date();
	}
	
	public static String formatDate(Date date) {
		return getDateDF().format(date);
	}

	public static String formatDatetime(Date date) {
		return getDatetimeDF().format(date);
	}
	
	public static String formatDatetimeNum(Date date) {
		return getDatetimeNumDF().format(date);
	}
	
	public static String formatTime(Date date) {
		return getTimeDF().format(date);
	}

	public static String currentDate() {
		return formatDate(now());
	}
	
	public static String currentDatetime() {
		return formatDatetime(now());
	}
	
	public static String currentDatetimeNum() {
		return formatDatetimeNum(now());
	}
	
	public static String currentTime() {
		return formatTime(now());
	}
	
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat patternDF=new SimpleDateFormat(pattern);
		return patternDF.format(date);
	}

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	public static long millis() {
		return System.currentTimeMillis();
	}

	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	public static int dayOfWeekChina() {
		Calendar c = calendar();
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范围
	 * 
	 * @param beginDate
	 *            日期范围开始
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            需要判断的日期
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 获得当前月的最后一天
	 * <p>
	 * HH:mm:ss为0，毫秒为999
	 * 
	 * @return
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}

	/**
	 * 获得当前月的第一天
	 * <p>
	 * HH:mm:ss SS为零
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 获得周五日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 获得周六日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 获得周日日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}

	/**
	 * 将字符串日期时间转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
			return getDatetimeDF().parse(datetime);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		return getDateDF().parse(date);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 时间格式 HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time) throws ParseException {
		return getTimeDF().parse(time);
	}

	/**
	 * 根据自定义pattern将字符串日期转换成java.util.Date类型
	 * 
	 * @param datetime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public Date parseDatetime(String datetime, String pattern)throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(datetime);
	}

	/**
	 * 
	 * @Title: minusDay
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param: @param day
	 * @param: @param format
	 * @param: @return
	 * @param: @throws ParseException
	 * @return: Date
	 * @throws
	 * @author yokaihei
	 * @Date 2015年8月17日 上午11:12:31
	 */
	public static Date minusDay(int day) throws ParseException {
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
		Date endDate = getDateDF().parse(getDateDF().format(date.getTime()));
		return endDate;
	}

	/**
	 * 计算当前周的第num周的第一天日期和最后一天日期
	 * 
	 * @param num
	 *            当前周的第几周，可以是负数，负数即为前几周
	 * @return
	 */
	public static Map<String, Date> getWeekStartEndDate(Integer num) {
		Map<String, Date> result = new HashMap<String, Date>();
		Date nowTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowTime);
		ca.add(Calendar.DATE, 6 * num);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 当前周第一天
		Date startDate = ca.getTime();
		result.put("startDate", startDate);
		// 当前周最后一天
		ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		ca.add(Calendar.WEEK_OF_YEAR, 1);
		Date endDate = ca.getTime();
		result.put("endDate", endDate);
		return result;
	}
	
	public static String firstDayOfYear(){
		Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, 1);//本年第一天
        return DateUtil.transferLongToDate("yyyyMMdd", c.getTimeInMillis());
	}
	
	public static String lastDayOfYear(){
		Calendar currCal=Calendar.getInstance();  
		int currentYear = currCal.get(Calendar.YEAR);  
		currCal.set(Calendar.YEAR, currentYear);  
		currCal.roll(Calendar.DAY_OF_YEAR, 1);  
		return DateUtil.transferLongToDate("yyyyMMdd", currCal.getTimeInMillis() );
	}

	/**
	 * 得到当前系统时间的millis前或后的时间点，单位毫秒
	 * @param millis
	 * @return
	 */
	public static Date getDateByMillis(Long millis){
		Long sysMillis = System.currentTimeMillis();
		sysMillis += millis;
		Date date = new Date();
		date.setTime(sysMillis);
		return date;
	}

	public static void main(String[] args) {
		System.out.println(getDateByMillis(30*60*1000L));
	}
	/**
	 * 计算当前月的第num月的第一天日期和最后一天日期
	 * 
	 * @param num
	 *            当前月的第几个月，可以是负数，负数即为前几个月
	 * @return
	 */
	public static Map<String, Date> getMonthStartEndDate(Integer num) {
		Map<String, Date> result = new HashMap<String, Date>();
		Date nowTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowTime);
		ca.add(Calendar.MONTH, num);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = ca.getTime();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = ca.getTime();
		result.put("startDate", startDate);
		result.put("endDate", endDate);
		return result;
	}

	/**
	 * 
	 * @Title: minusDay
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param: @param date
	 * @param: @param day
	 * @param: @param format
	 * @param: @return
	 * @param: @throws ParseException
	 * @return: Date
	 * @throws
	 * @author yokaihei
	 * @Date 2015年8月17日 上午11:12:58
	 */
	public static Date minusDay(Date date, int day) throws ParseException {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
		Date endDate = dft.parse(dft.format(calendar.getTime()));
		return endDate;
	}

	/**
	 * 把毫秒转化成日期
	 * 
	 * @param dateFormat
	 *            (日期格式，例如：MM/ dd/yyyy HH:mm:ss)
	 * @param millSec
	 *            (毫秒数)
	 * @return
	 */
	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
	
	/**
	 * 把日期转化成字符串日期
	 * 
	 * @param dateFormat
	 *            (日期格式，例如：MM/ dd/yyyy HH:mm:ss)
	 * @param date
	 *            日期
	 * @return
	 */
	public static String transferDateToString(String dateFormat, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 功能：当前时间增加天数。
	 * 
	 * @param days
	 *            正值时时间延后，负值时时间提前。
	 * @return Date
	 */
	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
		return new Date(c.getTimeInMillis());
	}

	/**
	 * 
	 * @Title: differDays
	 * @Description: (计算两个日期相差天数)
	 * @param: @param start
	 * @param: @param end
	 * @param: @return
	 * @return: int
	 * @throws
	 * @author Joshua_Cheng@qq.com
	 * @Date 2015年9月23日 下午8:16:25
	 */
	public static int differDays(Date start, Date end) {
		long diff = end.getTime() - start.getTime();
		return (int) (diff / (24 * 60 * 60 * 1000));
	}
	
	//本年
	public static String getNowYear(){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_YEAR, 1);//本年第一天
		result = result + sdf.format(c.getTime());
		int year = c.get(Calendar.YEAR);
		c = Calendar.getInstance();  
		c.clear();
        c.set(Calendar.YEAR, year);  
        c.roll(Calendar.DAY_OF_YEAR, -1);
		result = result + sdf.format(c.getTime());
		return result;
	}
	
	//本月
	public static String getNowMonth(){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);//本月第一天
		result = result + sdf.format(c.getTime());
		c.add(Calendar.MONTH, 1);//本月最后一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		result = result + sdf.format(c.getTime());
		return result;
	}
	
	//本周
	public static String getNowWeek(){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		result = result + sdf.format(c.getTime());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		result = result + sdf.format(c.getTime());
		return result;
	}
	
	/**
     * 判断两个日期是否是同一天
     * 
     * @param date1
     *            date1
     * @param date2
     *            date2
     * @return
     */
    @SuppressWarnings("unused")
	public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                        .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}
}