package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.common.SimpleFunction;
import com.hbfintech.hound.core.support.Hound;
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
