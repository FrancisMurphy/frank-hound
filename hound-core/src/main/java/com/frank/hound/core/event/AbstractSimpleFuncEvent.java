package com.frank.hound.core.event;

import com.frank.hound.core.common.SimpleFunction;
import com.frank.hound.core.support.Hound;
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
