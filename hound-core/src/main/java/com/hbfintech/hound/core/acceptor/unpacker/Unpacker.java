package com.hbfintech.hound.core.acceptor.unpacker;

/**
 * 拆包员
 */
public interface Unpacker
{
    void unpack(Object... unpackParams);
}
