package com.pnmp.desktop.webapp.interceptor;

import javax.servlet.http.*;

import org.springframework.web.servlet.handler.*;

import com.pnmp.common.exception.*;

/**
 * 时间戳拦截器类
 * 
 * @author WangJinpeng
 * 
 */
public class TimestampInterceptor extends HandlerInterceptorAdapter {

	/** 常量相关 */
	/** 时间戳名称 */
	private static final String TIMESTAMP = "timestamp";
	/** 时间间隔(秒) */
	private static final long INTERVAL = 600L;

	/**
	 * 处理前调用
	 * 
	 * @param request HTTP请求
	 * @param response HTTP应答
	 * @param handler 处理器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取时间戳
		String timestamp = request.getParameter(TIMESTAMP);
		if (timestamp == null) {
			throw new PnmpException(ExceptionCode.TIMESTAMP_ERROR, "时间戳不存在");
		}

		// 转化时间戳
		long $timestamp = 0L;
		try {
			$timestamp = Long.parseLong(timestamp);
		}
		catch (NumberFormatException e) {
			throw new PnmpException(ExceptionCode.TIMESTAMP_ERROR, "时间戳格式错误", e);
		}

		// 验证时间戳
		if (Math.abs(System.currentTimeMillis() / 1000 - $timestamp) > INTERVAL) {
			throw new PnmpException(ExceptionCode.TIMESTAMP_ERROR, "时间戳已过期");
		}

		// 回调函数
		return super.preHandle(request, response, handler);
	}

}
