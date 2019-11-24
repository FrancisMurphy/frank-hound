package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.common.SimpleFunction;
import com.hbfintech.hound.core.support.Hound;
import lombok.NonNull;

/**
 * @author frank
 */
public abstract class AbstractSimpleFuncEvent extends BaseHoundEvent
{
    private SimpleFunction simpleFunc;

    public AbstractSimpleFuncEvent(
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
