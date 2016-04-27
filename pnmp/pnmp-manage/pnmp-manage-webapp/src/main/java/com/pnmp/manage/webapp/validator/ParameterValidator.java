package com.pnmp.manage.webapp.validator;

import java.text.*;

import com.pnmp.common.exception.*;

/**
 * 参数验证器类
 * 
 * @author WangJinpeng
 * 
 */
public class ParameterValidator {

	/**
	 * 不能为空
	 * 
	 * @param value 参数取值
	 * @param description 参数描述
	 * @throws PnmpException 爱驴游异常
	 */
	public static void notNull(Object value, String description) throws PnmpException {
		if (value == null || "".equals(value)) {
			throw new PnmpException(ExceptionCode.PARAMETER_ERROR, MessageFormat.format("{0}不能为空", description));
		}
	}

	/**
	 * 字符串限制长度
	 * 
	 * @param value 参数取值
	 * @param description 参数描述
	 * @param limit 限制长度
	 * @throws PnmpException 爱驴游异常
	 */
	public static void stringLengthLimit(String value, String description, int limit) throws PnmpException {
		if (value != null && value.getBytes().length > limit) {
			throw new PnmpException(ExceptionCode.PARAMETER_ERROR, MessageFormat.format("{0}超过长度限制{1}", description,
					limit));
		}
	}

	/**
	 * 手机号码格式
	 * 
	 * @param value 参数取值
	 * @param description 参数描述
	 * @throws PnmpException 爱驴游异常
	 */
	public static void phoneFormat(String value, String description) throws PnmpException {
		if (value != null && !value.matches("1\\d{10}")) {
			throw new PnmpException(ExceptionCode.PARAMETER_ERROR, "请输入正确的手机号码");
		}
	}

	/**
	 * 时间戳格式
	 * 
	 * @param value 参数取值
	 * @param description 参数描述
	 * @throws PnmpException 爱驴游异常
	 */
	public static void timestampFormat(String value, String description) throws PnmpException {
		if (value != null && !value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
			throw new PnmpException(ExceptionCode.PARAMETER_ERROR, MessageFormat.format("{0}不是有效的时间戳格式", description));
		}
	}

	/**
	 * 时间格式
	 * 
	 * @param value 参数取值
	 * @param description 参数描述
	 * @throws PnmpException 爱驴游异常
	 */
	public static void timeFormat(String value, String description) throws PnmpException {
		if (value != null && !value.matches("\\d{2}:\\d{2}:\\d{2}")) {
			throw new PnmpException(ExceptionCode.PARAMETER_ERROR, MessageFormat.format("{0}不是有效的时间格式", description));
		}
	}

	/**
	 * 日期格式
	 * 
	 * @param value 参数取值
	 * @param description 参数描述
	 * @throws PnmpException 爱驴游异常
	 */
	public static void dateFormat(String value, String description) throws PnmpException {
		if (value != null && !value.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new PnmpException(ExceptionCode.PARAMETER_ERROR, MessageFormat.format("{0}不是有效的日期格式", description));
		}
	}

}
