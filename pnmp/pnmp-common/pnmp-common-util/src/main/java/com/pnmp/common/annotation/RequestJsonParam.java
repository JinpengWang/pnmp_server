package com.pnmp.common.annotation;

import java.lang.annotation.*;

/**
 * 请求JSON参数接口
 * 
 * @author WangJinpeng
 * 
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJsonParam {

	/**
	 * 参数取值
	 * 
	 * @return 参数取值
	 */
	String value() default "";

}
