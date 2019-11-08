package com.hbfintech.hound.core.acceptor.unpacker;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 拆包员
 */
public interface Unpacker
{
    /**
     * @param unpackFunc
     */
    void unpack(Map<String,String> unpackFunc);
}
