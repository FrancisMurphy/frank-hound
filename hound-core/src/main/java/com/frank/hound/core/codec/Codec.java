package com.frank.hound.core.codec;

public interface Codec {

    String build(Object targetObj);

    Object parseFrom(String source);

}
