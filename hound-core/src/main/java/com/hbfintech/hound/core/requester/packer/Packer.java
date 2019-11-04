package com.hbfintech.hound.core.requester.packer;

/**
 * 封包员
 */
public interface Packer
{
    void pack(Object... unpackParams);
}
