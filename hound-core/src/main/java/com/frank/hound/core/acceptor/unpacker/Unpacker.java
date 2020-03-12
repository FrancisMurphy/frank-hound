package com.frank.hound.core.acceptor.unpacker;

import com.frank.hound.core.codec.protocol.ProtocolDescribable;

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
    void unpack(ProtocolDescribable unpackParam);
}
