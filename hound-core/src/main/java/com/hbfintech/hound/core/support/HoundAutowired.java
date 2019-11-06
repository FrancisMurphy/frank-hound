package com.hbfintech.hound.core.support;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundAutowired
{
    String value();
}
