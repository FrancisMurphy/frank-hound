package com.frank.hound.core.support;

import java.lang.annotation.*;

/**
 * hound桥接器，用于建立Hound与第三方框架(e.g:spring web MVC <—> {@link javax.servlet.Filter} )的关系
 *
 * @author frank
 */
@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundBridge
{
    String value();
}
