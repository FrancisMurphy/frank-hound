package com.frank.hound.core.acceptor.unpacker;

/**
 * Unpacker
 * @author frank
 */
public interface Unpacker
{
    /**
     * unpack interface
     * @param fhtc The pram of frank hound trace context
     */
    void unpack(String fhtc);
}
