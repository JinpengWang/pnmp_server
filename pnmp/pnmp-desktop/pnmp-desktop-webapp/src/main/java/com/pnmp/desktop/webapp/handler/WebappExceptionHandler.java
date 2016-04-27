package com.pnmp.desktop.webapp.handler;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.pnmp.common.constant.*;
import com.pnmp.common.exception.*;
import com.pnmp.common.response.*;

/**
 * WEB应用异常拦截器类
 * 
 * @author WangJinpeng
 * 
 */
@ControllerAdvice
public class WebappExceptionHandler {

	/** 属性相关 */
	/** 运行模式 */
	@Value("${mode}")
	private String mode = null;

	/** 日志相关 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WebappExceptionHandler.class);

	/**
	 * 处理未期望的服务异常
	 * 
	 * @param throwable 抛出异常
	 * @return PNMP应答
	 */
	@ResponseBody
	@ExceptionHandler(Throwable.class)
	public PnmpResponse handleUnexpectedServerError(Throwable throwable) {
		// 打印日志
		if (throwable instanceof PnmpException) {
			if (CRunMode.ONLINE.getValue().equals(mode)) {
				LOGGER.warn("处理未期望的服务异常:" + throwable.getMessage());
			}
			else {
				LOGGER.warn("处理未期望的服务异常:", throwable);
			}
		}
		else {
			if (CRunMode.ONLINE.getValue().equals(mode)) {
				LOGGER.error("处理未期望的服务异常:" + throwable.getMessage());
			}
			else {
				LOGGER.error("处理未期望的服务异常:", throwable);
			}
		}

		// 处理异常
		PnmpResponse response = new PnmpResponse();
		if (throwable instanceof PnmpException) {
			PnmpException exception = (PnmpException) throwable;
			response.setCode(exception.getCode().getValue());
			response.setMessage(exception.getMessage());
		}
		else {
			response.setCode(ExceptionCode.UNKNOWN_ERROR.getValue());
			response.setMessage(throwable.getMessage());
		}

		// 默认消息
		if (response.getMessage() == null) {
			response.setMessage(ExceptionCode.UNKNOWN_ERROR.getDescription());
		}

		// 返回数据
		return response;
	}

}
