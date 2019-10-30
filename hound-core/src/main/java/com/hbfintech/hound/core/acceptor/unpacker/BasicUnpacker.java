package com.hbfintech.hound.core.acceptor.unpacker;

import com.hbfintech.hound.core.context.HoundCoreContext;

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
        HoundCoreContext.getContext().sort();
    }

}
