package com.pnmp.desktop.webapp.interceptor;

import javax.servlet.http.*;

import org.springframework.web.servlet.handler.*;

import com.pnmp.common.exception.*;

/**
 * 权限拦截器类
 * 
 * @author WangJinpeng
 * 
 */
public class RightInterceptor extends HandlerInterceptorAdapter {

	/** 登录令牌 */
	private static final String TOKEN = "token";

	/**
	 * 处理前调用
	 * 
	 * @param request HTTP请求
	 * @param response HTTP应答
	 * @param handler 处理器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取令牌
		String token = request.getParameter(TOKEN);
		if (token == null) {
			throw new PnmpException(ExceptionCode.TOKEN_INVALID, "令牌不存在");
		}

		// TODO:

		// 回调函数
		return super.preHandle(request, response, handler);
	}

}
