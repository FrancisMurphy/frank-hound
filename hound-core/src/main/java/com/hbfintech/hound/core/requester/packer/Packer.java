package com.hbfintech.hound.core.requester.packer;

import java.util.function.BiConsumer;

/**
 * 封包员
 */
public interface Packer
{
    void pack(BiConsumer<String,String>  unpackFunc);
}
