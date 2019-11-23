package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.common.SimpleFunction;
import com.hbfintech.hound.core.support.Hound;
import lombok.NonNull;

/**
 * @author frank
 */
public abstract class SimpleFuncEvent extends HoundEvent
{
    private SimpleFunction simpleFunc;

    public SimpleFuncEvent(
            @NonNull Hound source,
            SimpleFunction simpleFunc)
    {
        super(source, null);
        this.simpleFunc = simpleFunc;
    }

    protected void invokeFunc()
    {
        simpleFunc.func();
    }
}
