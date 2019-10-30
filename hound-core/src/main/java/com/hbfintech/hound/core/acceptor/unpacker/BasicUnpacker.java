package com.hbfintech.hound.core.acceptor.unpacker;

import com.hbfintech.hound.core.support.HoundContext;

/**
 * @author frank
 */
public abstract class BasicUnpacker implements Unpacker
{
    public BasicUnpacker()
    {
    }

    protected abstract void unpacking(Object... unpackParams);

    @Override
    public void unpack(Object... unpackParams)
    {
        unpacking(unpackParams);
        HoundContext.getContext().sort();
    }

}
