package com.pnmp.common.util;

import java.util.*;

/**
 * 字符串辅助类
 * 
 * @author WangJinpeng
 *
 */
public class StringHelper {

	/**
	 * 截取字符串
	 * 
	 * @param string 字符串
	 * @param length 截取长度
	 * @return 截取字符串
	 */
	public static String truncate(String string, int length) {
		if (string != null && string.length() > length) {
			return string.substring(0, length);
		}
		return string;
	}

	/**
	 * 转化列表
	 * 
	 * @param list 列表
	 * @return 字符串
	 */
	public static String transList(List<Long> list) {
		// 初始化
		StringBuilder sb = new StringBuilder();

		// 组装数据
		if (list != null && !list.isEmpty()) {
			Long[] array = list.toArray(new Long[0]);
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(array[i]);
			}
		}

		// 返回数据
		return sb.toString();
	}

}
