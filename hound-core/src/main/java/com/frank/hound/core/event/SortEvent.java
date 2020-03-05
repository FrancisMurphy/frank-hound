package com.frank.hound.core.event;

import com.frank.hound.core.common.SimpleFunction;
import com.frank.hound.core.support.Hound;
import lombok.NonNull;

/**
 *
 *
 * @author frank
 */
public class SortEvent extends AbstractSimpleFuncEvent
{
    public SortEvent(@NonNull Hound source,
            SimpleFunction simpleFunc)
    {
        super(source, simpleFunc);
    }

    public void sort()
    {
        invokeFunc();
    }
}
