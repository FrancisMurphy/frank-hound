package com.frank.hound.core.support;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundCodec {

}
