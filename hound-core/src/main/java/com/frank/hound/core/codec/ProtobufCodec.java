package com.frank.hound.core.codec;

import com.frank.hound.core.support.HoundSheep;
import lombok.extern.slf4j.Slf4j;

/**
 * protobuf 编码器
 * @author frank
 */
@Slf4j
@HoundSheep("protobuf")
public class ProtobufCodec implements Codec{



    @Override
    public String build(Object targetObj) {




        return null;
    }

    @Override
    public <T> T parseFrom(String source,Class<T> targetClazz) {





        return null;
    }
}
