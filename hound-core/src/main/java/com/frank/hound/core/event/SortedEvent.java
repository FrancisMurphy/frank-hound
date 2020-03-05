package com.frank.hound.core.event;

import com.frank.hound.core.support.Hound;

/**
 * @author frank
 */
public class SortedEvent extends BaseHoundEvent
{
    /**
     * Used to listen for processed events for {@link com.frank.hound.core.acceptor.sorter.Sorter}
     * @param source Hound instance that publish event
     */
    public SortedEvent(
            Hound source)
    {
        super(source);
    }
}
