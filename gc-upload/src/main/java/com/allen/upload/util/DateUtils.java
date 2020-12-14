package com.allen.upload.util;

import com.allen.upload.enums.DateStyle;
import com.allen.upload.enums.Week;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.*;

@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{


	private static final String FORMAT_DAY = "yyyy-MM-dd";
	private static final String FORMAT_TIME = "yyyy-MM-dd hh:mm:ss";
	private static final String FORMAT_UT = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String FORMAT_MM = "yyyy-MM";

	/**
	 * 检验日期格式是否正确
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static boolean isValidDate(String dateStr, DateStyle pattern) {
		if (dateStr == null || dateStr.length() == 0) {
			return false;
		}
		return formatDate(parseDate(dateStr, pattern), pattern).equals(dateStr);
	}


	/**
	 * 日期转换格式
	 * <p>thread-safe</p>
	 * @param date    日期
	 * @param pattern 日期格式 DateStyle
	 * @return
	 */
	public static String formatDate(final Date date, final DateStyle pattern) {
		return DateFormatUtils.format(date, pattern.getValue());
	}

	/**
	 * 日期转换格式
	 * 格式化时间-默认yyyy-MM-dd HH:mm:ss格式
	 * @param date 日期
	 * @return
	 */
	public static String formatDate(final Date date) {
		return formatDate(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
	}


	/**
	 * 日期转换格式
	 * <p>thread-safe</p>
	 * @param str    日期
	 * @param pattern 日期格式 DateStyle
	 * @return
	 */
	public static Date parseDate(final String str, final DateStyle pattern) {
		try {
			return parseDate(str, pattern.getValue());
		} catch (ParseException e) {
			log.error("日期转化失败{}", e);
		}
		return null;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param newDateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String changStyle(String date, DateStyle newDateStyle) {
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle == null) { // 匹配失败
			return null;
		}
		return changStyle(date, dateStyle, newDateStyle);
	}

	/**
	 * 获取日期格式
	 * @param date
	 * @return
	 */
	private static DateStyle getDateStyle(String date) {
		DateStyle[] values = DateStyle.values();
		int j = -1;
		for (int i = 0; i < values.length; i++) {
			Date tDate = parseDate(date, values[i]);
			if (tDate != null) {
				j = i;
				break;
			}
		}
		if (j >= 0) {
			return values[j];
		}
		return null;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date        旧日期字符串
	 * @param olddStyle 旧日期格式
	 * @param newStyle  新日期格式
	 * @return 新日期字符串
	 */
	private static String changStyle(String date, DateStyle olddStyle, DateStyle newStyle) {
		String s = null;
		if (olddStyle != null && newStyle != null) {
			s = formatDate(parseDate(date, olddStyle), newStyle);
		}
		return s;
	}


	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		default:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}

	/**
	 * 获取当前时间处在一年中的哪个季度
	 *
	 * @param date
	 * @return 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 */
	public static String getQualityOfYear(Date date) {
		String season = null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
				season = "第一季度";
				break;
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
				season = "第二季度";
				break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
				season = "第三季度";
				break;
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
				season = "第四季度";
				break;
			default:
				break;
		}
		return season;
	}

	/**
	 * 获取当前时间处在一年中的上半年还是下半年
	 *
	 * @param date
	 * @return 1 上半年 2 下半年
	 */
	public static String getHalfOfYear(Date date) {
		String halfYear = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
				halfYear = "上半年";
				break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
				halfYear = "下半年";
				break;
			default:
				break;
		}
		return halfYear;

	}

	/**
	 * 获取两个日期相差的天数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差天数。如果失败则返回-1
	 */
	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(parseDate(date, DateStyle.YYYY_MM_DD), parseDate(otherDate, DateStyle.YYYY_MM_DD));
	}

	/**
	 * @param date      日期
	 * @param otherDate 另一个日期
	 * @return 相差天数。如果失败则返回-1
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		int num = -1;
		if (date != null && otherDate != null) {
			long time = Math.abs(date.getTime() - otherDate.getTime());
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}

    /**
     * @param date yyyy-MM-dd
     * @return
	 */
	public static boolean compareDateToNow(Date date) {
		String nowDate = formatDate(new Date(), DateStyle.YYYY_MM_DD);
		String strDate;
		try {
			strDate = formatDate(date, DateStyle.YYYY_MM_DD);
			if (strDate.compareTo(nowDate) < 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
    	return true;
    }

	/**
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public static boolean compareDateToNow(String date){
		String nowDate = formatDate(new Date(), DateStyle.YYYY_MM_DD);
		return !BooleanUtils.isTrue(date.compareTo(nowDate)<0);
	}


	public static boolean compare(Date date, Date compareDate) {
		return BooleanUtils.isTrue(date.getTime() >= compareDate.getTime());
	}


	/**
	 * 获取当月所有的天
	 * @return
	 */
	public static List<String> getDayListOfMonth() {
		List list = new ArrayList();
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int year = aCalendar.get(Calendar.YEAR);//年份
		int month = aCalendar.get(Calendar.MONTH) + 1;//月份
		int day = aCalendar.getActualMaximum(Calendar.DATE);
		String monthStr = null;
		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = String.valueOf(month);
		}
		for (int i = 1; i <= day; i++) {
			String days = null;
			if (i < 10) {
				days="0"+i;
			}else {
				days=String.valueOf(i);
			}
			String aDate = String.valueOf(year)+"-"+monthStr+"-"+days;
			list.add(aDate);
		}
		return list;
	}

	/**
	 * 获取这个月的第一天
	 *
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		String str = formatDate(cal.getTime(), DateStyle.YYYY_MM_DD);
		return str + " 00:00:00";
	}

	/**
	 * 获取这个月的最后一天
	 *
	 * @return
	 */
	public static String getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		// 获取某月最大天数
		int lastDay = 0;
		//2月的平年瑞年天数
		if (month == 2) {
			lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
		} else {
			lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期

		String str = formatDate(cal.getTime(), DateStyle.YYYY_MM_DD);
		return str + " 23:59:59";

	}

	/**
	 * 获取一天的开始时间 12:00:00
	 * @return
	 */
	public static String getStartOfDay() {
		Calendar cal = Calendar.getInstance();
		String str = formatDate(cal.getTime(), DateStyle.YYYY_MM_DD);
		return str + " 00:00:00";
	}

	/**
	 * 获取一天的结束时间 11:59:59
	 *
	 * @return
	 */
	public static String getEndOfDay() {
		Calendar cal = Calendar.getInstance();
		String str = formatDate(cal.getTime(), DateStyle.YYYY_MM_DD);
		return str + " 23:59:59";
	}

	/**
	 * 获取指定月份的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		return a.get(Calendar.DATE);
	}

	public static List<String> dayReport(Date month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(month);//month 为指定月份任意日期
		int year = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int dayNumOfMonth = getDaysByYearMonth(year, m);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始

		List<String> list = new ArrayList<>();
		for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
			Date d = cal.getTime();
			String df = formatDate(d, DateStyle.YYYY_MM_DD);
			list.add(df);
		}
		return list;
	}

	/**
	 * 毫秒转 天时分秒
	 * @param mss
	 * @return
	 */
	public static String formatTimeBySecond(Long mss) {
		if (mss == null) {
			return null;
		}
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / (1000);
		StringBuilder stringBuilder = new StringBuilder();
		if (days != 0) {
			stringBuilder.append(days).append("天");
		}
		if (hours != 0) {
			stringBuilder.append(hours).append("小时");
		}
		if (minutes != 0) {
			stringBuilder.append(minutes).append("分钟");
		}
		if (seconds != 0) {
			stringBuilder.append(seconds).append("秒");
		}
		return stringBuilder.toString();
	}

	/** 获取相隔N天的起始日期和结束日期
	 * @param intervals 时间间隔(正数/负数)
	 * @return 相隔N天的起始日期和结束日期
	 */
	public static Map<String, Object> initializeDate(int intervals) {
		Map<String, Object> param = new HashMap<>();
		try {
			Calendar cd = Calendar.getInstance();
			Date fromDate = null;
			Date toDate = null;
			cd.setTime(new Date());
			toDate = cd.getTime();
			cd.add(Calendar.DATE, intervals);//设置N天之前或之后的日期
			fromDate = cd.getTime();
			String startDate = formatDate(fromDate, DateStyle.YYYY_MM_DD);
			String endDate = formatDate(toDate, DateStyle.YYYY_MM_DD);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
		} catch (Exception e) {
			log.error("初始化时间失败{}", e);
		}
		return param;
	}


    public static String formatTime(Long mss) {
        if (mss == null) {
            return null;
        }
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        StringBuilder stringBuilder = new StringBuilder();
        if (days != 0) {
            stringBuilder.append(days).append("天");
        }
        if (hours != 0) {
            stringBuilder.append(hours).append("小时");
        }
        if (minutes != 0) {
            stringBuilder.append(minutes).append("分钟");
        }
        if (minutes == 0) {
            stringBuilder.append(0).append("分钟");
        }

		return stringBuilder.toString();
	}

	/**
	 * 两个时间比较 日期格式 yyyy-MM-dd HH:mm:ss
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean compareDate(String time1, String time2) {
		Date dt1 = DateUtils.parseDate(time1, DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date dt2 = DateUtils.parseDate(time2, DateStyle.YYYY_MM_DD_HH_MM_SS);
		return dt1.getTime() >= dt2.getTime();
	}


	/**
	 * 获取某段时间内容的日期数组 日期格式 yyyy-MM-dd
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static List<String> getStrChangeDate(String startTime,String endTime){
		return getDayByRangeTime(parseDate(startTime, DateStyle.YYYY_MM_DD),parseDate(endTime, DateStyle.YYYY_MM_DD));
	}

	/**
	 * 获取某段时间内容的日期
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<String> getDayByRangeTime(Date dBegin, Date dEnd) {
		List<String> dateList = new ArrayList();

		dateList.add(formatDate(dBegin, DateStyle.YYYY_MM_DD));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			dateList.add(formatDate(calBegin.getTime(), DateStyle.YYYY_MM_DD));
		}
		return dateList;
	}

	/**
	 * 判断开始时间是否大于结束时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return true:大于
	 */

	public static boolean checkDate(String startTime, String endTime) {
		Date dt1 = parseDate(startTime, DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date dt2 = parseDate(endTime, DateStyle.YYYY_MM_DD_HH_MM_SS);
		if (!dt1.before(dt2)) {
			return true;
		}
		return false;
	}

	/**
	* 获取上一个时间差
	 * 示例，如获取30分钟前的时间：DateUtils.getDiffDate(Calendar.MINUTE,-30);
	* @param type 参考Calendar类，如Calendar.HOUR_OF_DAY是小时，Calendar.MINUTE是分钟
	* @param offset
	* @return
	*/
	public static Date getDiffDate(int type , int offset){
		Calendar calendar = Calendar.getInstance();
		calendar.set(type, calendar.get(type) + offset);
		return calendar.getTime();
	}



}
