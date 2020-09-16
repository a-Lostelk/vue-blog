package com.sunny.sys.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static String DEFAULT_PATTERN = "yyyy-MM-dd";
	public static String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static String DATE_PATTERN_YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";

	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
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
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
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

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 *
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 *
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 *
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "天" : "") + hour + "小时" + min + "分" + s + "秒" + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 取得给定日期加上指定月数后的日期字符串
	 *
	 * @param date   给定的日期对象
	 * @param amount 需要添加的月数，如果是向前的月数，使用负数就可以.
	 * @return String 加上一定月数以后的Date字符串
	 */
	public static String getDateAddMonth(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MONTH, amount);
		return DateFormatUtils.format(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 取得给定日期加上一定天数后的日期对象.
	 *
	 * @param date   给定的日期对象
	 * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static Date getDateAdd(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		cal.add(GregorianCalendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定分钟后的日期对象.
	 *
	 * @param date   给定的日期对象
	 * @param amount 需要添加的分钟，如果是向前的分钟，使用负数就可以.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static Date getMinuteAdd(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 取得当前Date对象.
	 *
	 * @return Date 当前Date对象.
	 */
	public static Date getDateObj() {
		Calendar c = new GregorianCalendar();
		return c.getTime();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(getDateAdd(getDateObj(), 4));
	}

	// 获取月初
	public static Date getMonthBeginDate(Date date, String patten) {
		if (date == null) {
			date = new Date();
		}
		if (patten == null) {
			patten = DateUtils.DEFAULT_PATTERN;
		}
		Calendar cale = Calendar.getInstance();
		cale.clear();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);

		return cale.getTime();
	}

	// 获取月末
	public static Date getMonthEndDate(Date date, String patten) {
		if (date == null) {
			date = new Date();
		}
		if (patten == null) {
			patten = DateUtils.DEFAULT_PATTERN;
		}
		Calendar cale = Calendar.getInstance();
		cale.clear();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);

		return cale.getTime();
	}

	/**
	 * 计算两个时间之间相差，参数时间不区分前后顺序
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeBetweenTwoDate(Date startTime, Date endTime) {
		long l = endTime.getTime() - startTime.getTime();
		if (startTime.compareTo(endTime) > 0) {
			l = startTime.getTime() - endTime.getTime();
		}
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String r = "";
		if (day > 0) {
			r += day + "天";
		}
		if (hour > 0) {
			r += hour + "小时";
		}
		if (min > 0) {
			r += min + "分";
		}
		if (s > 0) {
			r += s + "秒";
		}
		return r;
	}

	/**
	 * 返回两个时间的间隔
	 *
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param num
	 * @return
	 */
	public static BigDecimal getTimeBetweenTwoDate(Date startTime, Date endTime, String type, int num) {
		long l = endTime.getTime() - startTime.getTime();
		if (startTime.compareTo(endTime) > 0) {
			l = startTime.getTime() - endTime.getTime();
		}
		if ("day".equals(type)) {
			return new BigDecimal(l).divide(new BigDecimal(1000 * 60 * 60 * 24), num, BigDecimal.ROUND_HALF_UP);
		} else if ("hour".equals(type)) {
			return new BigDecimal(l).divide(new BigDecimal(1000 * 60 * 60), num, BigDecimal.ROUND_HALF_UP);
		} else if ("min".equals(type)) {
			return new BigDecimal(l).divide(new BigDecimal(1000 * 60), num, BigDecimal.ROUND_HALF_UP);
		} else if ("second".equals(type)) {
			return new BigDecimal(l).divide(new BigDecimal(1000), num, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}

	/**
	 * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
	 *
	 * @param dateStr 日期描述 从给定字符串的开始分析文本，以生成一个日期。该方法不使用给定字符串的整个文本。 有关日期分析的更多信息，请参阅
	 *                parse(String, ParsePosition) 方法。一个 String，应从其开始处进行分析
	 * @param pattern 日期模式
	 * @return 给定字符串描述的日期对象。
	 */
	public static Date getDateFromString(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date resDate = null;
		try {
			resDate = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resDate;
	}

	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	public static Date longToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String str = transferLongToDate(dateFormat, millSec);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date parseDateTZ(String dateString)
	{
		dateString = dateString.replace("Z", " UTC");//注意是空格+UTC
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN_YYYY_MM_DD_T_HH_MM_SS_Z);//注意格式化的表达式
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取某月的天数
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定时间范围的日期集合
	 * @param startTime	开始日期
	 * @param endTime	结束日期
	 * @return 返回[2020-01-01, 2020-01-02, 2020-01-03....]的数组
	 */
	public static List<String> getAppointDays(String startTime, String endTime) {
		// 返回的日期集合
		List<String> days = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = dateFormat.parse(startTime);
			Date end = dateFormat.parse(endTime);

			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(start);

			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(end);
			tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
			while (tempStart.before(tempEnd)) {
				days.add(dateFormat.format(tempStart.getTime()));
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 获取指定月份之间的月份集合
	 * @param minDate 字符串开始时间 yyyy-MM
	 * @param maxDate 字符串结束时间 yyyy-MM
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}

	/**
	 * 获取指定日期的前后时间
	 *
	 * @param appoint
	 * @return
	 */
	public static String getAppointDate(Date date, int appoint) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, appoint);
		Date start = calendar.getTime();
		String appointDate = format.format(start);//前一天
		return appointDate;
	}


	/**
	 * 获取本周的开始时间
	 * @return
	 */
	public static Date getBeginDayOfWeek() {
		Date date = new Date();
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek);
		return getDayStartTime(cal.getTime());
	}

	/**
	 * 获取某个日期的开始时间, 本周或本月
	 * @param date
	 * @return
	 */
	public static Date getDayStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date)
			calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();
		return time;
	}

	/**
	 * 获取指定月份开始日期
	 * @param office 0表示当前月，-1表示上个月，1表示下个月
	 * @return String
	 * */
	public static Date getMonthStart(int office){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, office);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date time=calendar.getTime();
		String str = simpleDateFormat.format(time) + " 00:00:00";
		try {
			time = simpleDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 获取指定月份的结束时间
	 * @param office 0表示当前月，-1表示上个月，1表示下个月
	 * @return
	 */
	public static Date getMonthEnd(int office){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, office);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date time = calendar.getTime();
		String str = simpleDateFormat.format(time) + " 23:59:59";
		try {
			time = DateUtils.parseDate(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 获取指定月的前一月（年）或后一月（年）
	 * @param dateStr
	 * @param addYear
	 * @param addMonth
	 * @param addDate
	 * @return 输入的时期格式为yyyy-MM，输出的日期格式为yyyy-MM
	 * @throws Exception
	 */
	public static Date getPreNextMonth(Date dateStr,int addYear, int addMonth, int addDate) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateStr);
			cal.add(Calendar.YEAR,addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);

			SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM");
			String dateTmp = returnSdf.format(cal.getTime());
			Date returnDate = returnSdf.parse(dateTmp);
			return returnDate;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取指定的年份
	 * @param office -1表示去年，0表示当前年，1表示明年
	 * @return
	 */
	public static String getAppointYear(int office) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -1);
		Date y = calendar.getTime();
		String year = format.format(y);
		return year;
	}

	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * 获取指定日期上一周、上一月、上一年的时间 默认为0，不选择上个月或下个月
	 * @param date
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public static Date getAppointDateTime(Date date, int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		if (date == null) {
			calendar.setTime(new Date());
		}else {
			calendar.setTime(date);
		}
		if (day != 0) {
			calendar.add(Calendar.DATE,  day);
		}
		if (month != 0) {
			calendar.add(Calendar.MONTH, month);
		}
		if (year != 0) {
			calendar.add(Calendar.YEAR, year);
		}
		Date d = calendar.getTime();
		return d;
	}
}