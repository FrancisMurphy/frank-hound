package com.hbfintech.hound.core.annotation;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundComponent
{
    /**
     * Equivalent to component name
     * @return
     */
    String value();
}
