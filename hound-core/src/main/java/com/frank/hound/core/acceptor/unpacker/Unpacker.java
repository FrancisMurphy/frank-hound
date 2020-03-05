package com.frank.hound.core.acceptor.unpacker;

import java.util.Map;

/**
 * Unpacker
 * @author frank
 */
public interface Unpacker
{
    /**
     * unpack interface
     * @param unpackParam params which wait deal
     */
    void unpack(Map<String,String> unpackParam);
}
