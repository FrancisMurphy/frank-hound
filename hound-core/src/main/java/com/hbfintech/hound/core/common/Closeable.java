package com.hbfintech.hound.core.common;

/**
 * Automatic recovery Mechanism for Hound sheep.
 * With this interface implemented, Hound will invokes the interface at the right time.
 * @author frank
 */
public interface Closeable
{
    void close();
}
