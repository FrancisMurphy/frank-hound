package com.frank.hound.core.requester.packer;

import java.util.function.BiConsumer;

/**
 * 封包员
 * @author frank
 */
public interface Packer
{
    /**
     * @param unpackFunc Packet function
     */
    void pack(BiConsumer<String,String> unpackFunc);
}
