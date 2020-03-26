package com.frank.hound.core.codec;

public interface Codec {

    String build(Object targetObj);

    <T> T parseFrom(String source,Class<T> targetClazz);

}
