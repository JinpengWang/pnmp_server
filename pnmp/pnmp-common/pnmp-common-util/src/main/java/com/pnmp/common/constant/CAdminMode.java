package com.pnmp.common.constant;

/**
 * 系统管理员常量
 * 
 * @author WangJinpeng
 *
 */
public enum CAdminMode {

	/** 字段相关 */
	/** 系统管理员 */
	ADMIN((long) 1, "系统管理员");

	/** 属性相关 */
	/** 系统管理员类型值 */
	private long value = 0;
	/** 系统管理员类型描述 */
	private String description = null;

	/**
	 * 构造函数
	 * 
	 * @param value 系统管理员类型值
	 * @param description 系统管理员类型描述
	 */
	private CAdminMode(long value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取系统管理员类型值
	 * 
	 * @return 系统管理员类型值
	 */
	public long getValue() {
		return value;
	}

	/**
	 * 获取系统管理员类型描述
	 * 
	 * @return 系统管理员类型描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取系统管理员类型描述
	 * 
	 * @param value 系统管理员类型值
	 * @return 系统管理员类型描述
	 */
	public static String getDescription(Long value) {
		if (value != null) {
			for (CAdminMode field : values()) {
				if (value.equals(field.value)) {
					return field.description;
				}
			}
		}
		return null;
	}

	/**
	 * 根据值获取系统管理员类型
	 * 
	 * @param value 系统管理员类型值
	 * @return 系统管理员类型
	 */
	public static CAdminMode fromValue(Long value) {
		if (value != null) {
			for (CAdminMode field : values()) {
				if (value.equals(field.value)) {
					return field;
				}
			}
		}
		return null;
	}

	/**
	 * 包含系统管理员类型值
	 * 
	 * @param value 系统管理员类型值
	 * @return 是否包含
	 */
	public static boolean contains(Long value) {
		if (value != null) {
			for (CAdminMode field : values()) {
				if (value.equals(field.value)) {
					return true;
				}
			}
		}
		return false;
	}

}
