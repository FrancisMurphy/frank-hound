package com.frank.hound.core.support;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundCodec {

    /**
     * 选择的序列化方式
     * protobuf、json、xml 等
     * @return
     */
    String value();

}
