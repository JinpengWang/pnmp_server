package com.pnmp.common.util;

import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;

import com.pnmp.common.exception.*;

/**
 * 时间辅助类
 * 
 * @author WangJinpeng
 * 
 */
public class TimeHelper {

	/** 常量相关 */
	/** 日期模式 (yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 月份 (yyyy-MM) */
	public final static String MONTH_PATTERN = "yyyy-MM";
	/** 月份 (yyyyMM) */
	public final static String MONTH_NOSPACE_PATTERN = "yyyyMM";
	/** 年份 (yyyy) */
	public final static String YEAR_PATTERN = "yyyy";
	/** 时间模式 (HH:mm:ss) */
	private final static String TIME_PATTERN = "HH:mm:ss";
	/** 时间戳模式 (yyyy-MM-dd HH:mm:ss) */
	public final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 时间模式 (hh) */
	public final static String HOUR_PATTERN = "cc";

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期字符串
	 */
	public static String getDate() {
		return new SimpleDateFormat(DATE_PATTERN).format(new java.util.Date());
	}

	/**
	 * 获取指定日期
	 * 
	 * @param date 指定日期
	 * @return 指定日期字符串
	 */
	public static String getDate(Date date) {
		// 检查空值
		if (date == null) {
			return null;
		}

		// 转化日期
		return new SimpleDateFormat(DATE_PATTERN).format(date);
	}

	/**
	 * 获取指定月份
	 * 
	 * @param date 指定日期
	 * @return 指定月份字符串
	 */
	public static String getMonth(Date date) {
		// 检查空值
		if (date == null) {
			return null;
		}

		// 转化日期
		return new SimpleDateFormat(MONTH_PATTERN).format(date);
	}

	/**
	 * 将指定格式的时间转换并获取指定格式月份
	 * 
	 * @param date 指定时间
	 * @return 返回指定格式时间
	 * @throws PnmpException 爱驴游异常
	 */
	public static String getMonthBySpace(String date) throws PnmpException {
		// 检查空值
		if (date == null) {
			return null;
		}

		// 转化日期
		try {
			return new SimpleDateFormat(MONTH_PATTERN).format(getDate(getDate(new java.sql.Date(new SimpleDateFormat(
					MONTH_NOSPACE_PATTERN).parse(date).getTime()))));
		}
		catch (Exception e) {
			throw new PnmpException("日期(" + date + ")格式异常");
		}

	}

	/**
	 * 获取指定年份
	 * 
	 * @param date 指定日期
	 * @return 指定年份字符串
	 */
	public static String getYear(Date date) {
		// 检查空值
		if (date == null) {
			return null;
		}

		// 转化日期
		return new SimpleDateFormat(YEAR_PATTERN).format(date);
	}

	/**
	 * 获取指定日期
	 * 
	 * @param date 指定日期字符串
	 * @return 指定日期
	 * @throws PnmpException 爱驴游异常
	 */
	public static Date getDate(String date) throws PnmpException {
		// 检查空值
		if (date == null) {
			return null;
		}

		// 转化日期
		try {
			return new Date(new SimpleDateFormat(DATE_PATTERN).parse(date).getTime());
		}
		catch (ParseException e) {
			throw new PnmpException("日期(" + date + ")格式异常");
		}
	}

	/**
	 * 获取昨日日期
	 * 
	 * @param date 指定日期
	 * @return 昨日日期
	 */
	public static Date getYesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.DATE, -1);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定日期0点
	 * 
	 * @param date 指定日期
	 * @return 0点时间戳
	 */
	public static Timestamp getBeginOfDay(Date date) {
		// 初始化
		Calendar calendar = Calendar.getInstance();

		// 处理数据
		if (date != null) {
			calendar.setTime(date);
		}

		// 处理时间
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定日期24点
	 * 
	 * @param date 指定日期
	 * @return 24点时间戳
	 */
	public static Timestamp getEndOfDay(Date date) {
		// 初始化
		Calendar calendar = Calendar.getInstance();

		// 处理时间
		// 处理数据
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH),
				23, 59, 59);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定日期的上周日期
	 * 
	 * @param date 指定日期
	 * @return 上周日期
	 */
	public static Date getLastweekday(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.DATE, -7);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return 当前时间戳字符串
	 */
	public static String getTimestamp() {
		return new SimpleDateFormat(TIMESTAMP_PATTERN).format(new java.util.Date());
	}

	/**
	 * 获取指定时间戳
	 * 
	 * @param timestamp 指定时间戳
	 * @return 指定时间戳字符串
	 */
	public static String getTimestamp(Timestamp timestamp) {
		// 检查空值
		if (timestamp == null) {
			return null;
		}

		// 转化时间
		return new SimpleDateFormat(TIMESTAMP_PATTERN).format(new java.util.Date(timestamp.getTime()));
	}

	/**
	 * 获取指定时间戳
	 * 
	 * @param timestamp 指定时间戳字符串
	 * @return 指定时间戳
	 * @throws PnmpException 爱驴游异常
	 */
	public static Timestamp getTimestamp(String timestamp) throws PnmpException {
		// 检查空值
		if (timestamp == null) {
			return null;
		}

		// 转化时间
		try {
			return new Timestamp(new SimpleDateFormat(TIMESTAMP_PATTERN).parse(timestamp).getTime());
		}
		catch (ParseException e) {
			throw new PnmpException("时间戳(" + timestamp + ")格式异常");
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间字符串
	 */
	public static String getTime() {
		return new SimpleDateFormat(TIME_PATTERN).format(new java.util.Date());
	}

	/**
	 * 获取指定时间
	 * 
	 * @param time 指定时间
	 * @return 指定时间字符串
	 */
	public static String getTime(Time time) {
		// 检查空值
		if (time == null) {
			return null;
		}

		// 转化时间
		return new SimpleDateFormat(TIME_PATTERN).format(time);
	}

	/**
	 * 获取指定时间
	 * 
	 * @param time 指定时间字符串
	 * @return 指定时间
	 * @throws PnmpException 爱驴游异常
	 */
	public static Time getTime(String time) throws PnmpException {
		// 检查空值
		if (time == null) {
			return null;
		}

		// 转化时间
		try {
			return new Time(new SimpleDateFormat(TIME_PATTERN).parse(time).getTime());
		}
		catch (ParseException e) {
			throw new PnmpException("时间(" + time + ")格式异常");
		}
	}

	/**
	 * 获取指定时间范围内的月份
	 * 
	 * @param beginMonth 开始时间(yyyyMM)
	 * @param endMonth 结束时间(yyyyMM)
	 * @return 月份集合(yyyyMM)
	 * @throws PnmpException
	 */
	public static List<String> getMonthBetween(String beginMonth, String endMonth) throws PnmpException {
		// 初始化
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(MONTH_NOSPACE_PATTERN);
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		// 数据处理
		try {
			min.setTime(sdf.parse(beginMonth));
			max.setTime(sdf.parse(endMonth));
		}
		catch (ParseException e) {
			throw new PnmpException("时间(" + beginMonth + "或" + endMonth + ")格式异常");
		}
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		// 返回数据
		return result;
	}

	/**
	 * 获取指定时间所在周的周一
	 * 
	 * @param date 指定时间(yyyy-MM-dd)
	 * @return 周一时间戳
	 */
	public static Timestamp getBeginOfWeek(Date date) {
		// 初始化
		Calendar calendar = Calendar.getInstance();

		// 处理日期
		if (date != null) {
			calendar.setTime(date);
		}
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间所在周的周日
	 * 
	 * @param date 指定时间(yyyy-MM-dd)
	 * @return 周日时间戳
	 */
	public static Timestamp getEndOfWeek(Date date) {
		// 初始化
		Calendar calendar = Calendar.getInstance();

		// 处理日期
		if (date != null) {
			calendar.setTime(date);
		}
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定日期月初时间
	 * 
	 * @param date 指定时间(yyyy-MM-dd)
	 * @return 月初时间戳
	 * @throws PnmpException 爱旅游异常
	 */
	public static Timestamp getBeginOfMonth(String date) throws PnmpException {
		// 初始化
		Calendar calendar = Calendar.getInstance();

		// 处理时间
		if (date != null) {
			try {
				calendar.setTime(new SimpleDateFormat(DATE_PATTERN).parse(date));
			}
			catch (ParseException e) {
				throw new PnmpException("时间(" + date + ")格式异常");
			}
		}
		calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));
		int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), minDay, 00, 00, 00);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定日期月末日期
	 * 
	 * @param date 指定时间(yyyy-MM-dd)
	 * @return 月末日期时间戳
	 * @throws PnmpException 爱旅游异常
	 */
	public static Timestamp getEndOfMonth(String date) throws PnmpException {
		// 初始化
		Calendar calendar = Calendar.getInstance();

		// 处理时间
		if (date != null) {
			try {
				calendar.setTime(new SimpleDateFormat(DATE_PATTERN).parse(date));
			}
			catch (ParseException e) {
				throw new PnmpException("时间(" + date + ")格式异常");
			}
		}
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxDay, 23, 59, 59);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定之间范围内的所有日期
	 * 
	 * @param beginDate 开始日期(yyyy-MM-dd)
	 * @param endDate 结束日期(yyyy-MM-dd)
	 * @return 日期列表(yyyy-MM-dd)
	 * @throws PnmpException 爱旅游异常
	 */
	public static List<String> getDayBetwwen(String beginDate, String endDate) throws PnmpException {
		// 初始化
		List<String> dateList = new ArrayList<String>();
		Calendar calBegin = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		dateList.add(beginDate);

		// 设置时间
		calBegin.setTime(getDate(beginDate));
		calEnd.setTime(getDate(beginDate));

		// 处理时间
		while (calEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			dateList.add(new SimpleDateFormat(TIME_PATTERN).format(calBegin.getTime()));
		}

		// 返回数据
		return dateList;
	}

	/**
	 * 获取指定时间前一小时开始时间
	 * 
	 * @return 开始时间时间戳(yyyy-MM-dd HH:mm:ss)
	 * @throws PnmpException 爱旅游异常
	 */
	public static Timestamp getBeginOfLastHour(String timestamp) throws PnmpException {
		// 初始化
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);

		// 处理时间
		if (timestamp != null) {
			try {
				calendar.setTime(sdf.parse(timestamp));
			}
			catch (ParseException e) {
				throw new PnmpException("时间(" + timestamp + ")格式异常");
			}
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间前一小时结束时间
	 * 
	 * @return 结束时间时间戳(yyyy-MM-dd HH:mm:ss)
	 * @throws PnmpException 爱旅游异常
	 */
	public static Timestamp getEndOfLastHour(String timestamp) throws PnmpException {
		// 初始化
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);

		// 处理时间
		if (timestamp != null) {
			try {
				calendar.setTime(sdf.parse(timestamp));
			}
			catch (ParseException e) {
				throw new PnmpException("时间(" + timestamp + ")格式异常");
			}
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);

		// 返回数据
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间的小时(24小时制)
	 * 
	 * @param timestamp 指定时间
	 * @return 小时值
	 * @throws PnmpException 爱旅游异常
	 */
	public static Short getHourOfDay(Timestamp timestamp) {
		// 初始化
		Calendar calendar = Calendar.getInstance();
		Short hourOfDay = null;

		// 处理数据
		if (timestamp != null) {
			calendar.setTime(timestamp);
		}
		hourOfDay = (short) calendar.get(Calendar.HOUR_OF_DAY);

		// 返回数据
		return hourOfDay;
	}

	/**
	 * 获取指定时间的月份
	 * 
	 * @return 时间戳(yyyy-MM-dd HH:mm:ss)
	 * @throws PnmpException 爱旅游异常
	 */
	public static Short getMonthOfYear(String timestamp) throws PnmpException {
		// 初始化
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
		Short monthOfYear = null;

		// 处理数据
		if (timestamp != null) {
			try {
				calendar.setTime(sdf.parse(timestamp));
			}
			catch (ParseException e) {
				throw new PnmpException("时间(" + timestamp + ")格式异常");
			}
		}
		monthOfYear = (short) (calendar.get(Calendar.MONTH) + 1);

		// 返回数据
		return monthOfYear;
	}

	/**
	 * 获取指定时间上月日期
	 * 
	 * @param timestamp 指定时间
	 * @throws PnmpException 爱旅游异常
	 * @return 上月日期(yyyy-MM-dd)
	 */
	public static String getLastMonth(String timestamp) throws PnmpException {
		// 初始化
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);

		// 处理数据
		if (timestamp != null) {
			try {
				calendar.setTime(sdf.parse(timestamp));
			}
			catch (ParseException e) {
				throw new PnmpException("时间(" + timestamp + ")格式异常");
			}
		}
		calendar.add(Calendar.MONTH, -1);

		// 返回数据
		return new SimpleDateFormat(DATE_PATTERN).format(calendar.getTime());
	}
}
