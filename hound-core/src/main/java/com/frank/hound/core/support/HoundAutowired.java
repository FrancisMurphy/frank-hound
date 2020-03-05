package com.frank.hound.core.support;

import java.lang.annotation.*;

/**
 * 自动注入注解，Hound会自动扫描声明了此注解的成员，通过反射的方式将实例注入
 * 如需正常使用需要在当前class上声明{@link com.frank.hound.core.support.HoundAutowired}，以供hound进行IOC
 *
 * @author frank
 */
@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundAutowired
{
    String value();
}
