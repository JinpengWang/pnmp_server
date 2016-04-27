package com.pnmp.manage.webapp.interceptor;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.web.servlet.handler.*;

import com.pnmp.common.exception.*;
import com.pnmp.common.util.*;

/**
 * JSON鉴权拦截器类
 * 
 * @author WangJinpeng
 * 
 */
public class SignatureInterceptor extends HandlerInterceptorAdapter {

	/** 常量相关 */
	/** 干扰编码 */
	private static final String NOISE = "EqYKiaWy6qBRvX0EUoUXC6O3xEeRhz";
	/** 签名名称 */
	private static final String SIGNATURE = "signature";

	/**
	 * 处理前调用
	 * 
	 * @param request HTTP请求
	 * @param response HTTP应答
	 * @param handler 处理器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取签名
		String signature = request.getParameter(SIGNATURE);
		if (signature == null) {
			throw new PnmpException(ExceptionCode.SIGNATURE_ERROR, "签名不存在");
		}

		// 验证签名
		String $signature = genSignature(request);
		if (!signature.equalsIgnoreCase($signature)) {
			throw new PnmpException(ExceptionCode.SIGNATURE_ERROR, "签名错误");
		}

		// 回调函数
		return super.preHandle(request, response, handler);
	}

	/**
	 * 生成签名
	 * 
	 * @param request HTTP请求
	 * @return 签名
	 */
	@SuppressWarnings("unchecked")
	private String genSignature(HttpServletRequest request) {
		// 初始化
		StringBuilder sb = new StringBuilder();
		List<String> nameList = new ArrayList<String>();

		// 排序名称
		Enumeration<String> nameEnum = request.getParameterNames();
		while (nameEnum.hasMoreElements()) {
			nameList.add(nameEnum.nextElement());
		}
		Collections.sort(nameList);

		// 组装数据
		for (String name : nameList) {
			if (!SIGNATURE.equals(name)) {
				sb.append(name);
				sb.append(request.getParameter(name));
			}
		}
		sb.append(NOISE);

		// 计算返回
		return EncryptHelper.toMD5(sb.toString());
	}
}
