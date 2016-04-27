package com.pnmp.common.util;

import javax.servlet.http.*;

/**
 * 请求辅助类
 * 
 * @author WangJinpeng
 *
 */
public class RequestHelper {

	/**
	 * 获取URI
	 * 
	 * @param request HTTP请求
	 * @return URI
	 */
	public static String getURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath() + "/";
		if (uri != null && uri.startsWith("/")) {
			return uri.replace(contextPath, "");
		}
		return uri;
	}

}
