package com.xuanfeiyang.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	/**
	 * 需要生成token的配置参数,服务器生成token并保存至session中
	 * 注意:生成后的token需跟随请求数据一起提交至服务端
	 * <input type="hidden" name="token" value="${token}" />
	 * @return
	 */
	boolean save() default false;
	/**
	 * 需要检查token的配置参数,检查请求重复提交
	 * @return
	 */
	boolean remove() default false;
}
