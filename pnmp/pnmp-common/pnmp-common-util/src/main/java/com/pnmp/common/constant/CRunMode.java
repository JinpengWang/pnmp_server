package com.pnmp.common.constant;

/**
 * 运行模式枚举
 * 
 * @author WangJinpeng
 *
 */
public enum CRunMode {

	/** 字段相关 */
	/** 开发 */
	DEVELOP("develop", "开发"),
	/** 测试 */
	TEST("test", "测试"),
	/** 预发布 */
	STAGE("stage", "预发布"),
	/** 线上 */
	ONLINE("online", "线上");

	/** 属性相关 */
	/** 运行模式值 */
	private String value = null;
	/** 运行模式描述 */
	private String description = null;

	/**
	 * 构造函数
	 * 
	 * @param value 运行模式值
	 * @param description 运行模式描述
	 */
	private CRunMode(String value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取运行模式值
	 * 
	 * @return 运行模式值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获取运行模式描述
	 * 
	 * @return 运行模式描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取运行模式描述
	 * 
	 * @param value 运行模式值
	 * @return 运行模式描述
	 */
	public static String getDescription(String value) {
		if (value != null) {
			for (CRunMode field : values()) {
				if (value.equals(field.value)) {
					return field.description;
				}
			}
		}
		return null;
	}

	/**
	 * 根据值获取运行模式
	 * 
	 * @param value 运行模式值
	 * @return 运行模式
	 */
	public static CRunMode fromValue(String value) {
		if (value != null) {
			for (CRunMode field : values()) {
				if (value.equals(field.value)) {
					return field;
				}
			}
		}
		return null;
	}

	/**
	 * 包含运行模式值
	 * 
	 * @param value 运行模式值
	 * @return 是否包含
	 */
	public static boolean contains(String value) {
		if (value != null) {
			for (CRunMode field : values()) {
				if (value.equals(field.value)) {
					return true;
				}
			}
		}
		return false;
	}

}
