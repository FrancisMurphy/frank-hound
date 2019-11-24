package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.support.Hound;

/**
 * Used to listen for Initialization events for {@link Hound}
 * @author frank
 */
public class HoundInitializedEvent extends BaseHoundEvent
{
    /**
     * Used to listen for Initialization events for {@link Hound}
     * @param source Hound instance that publish event
     */
    public HoundInitializedEvent(Hound source)
    {
        super(source);
    }
}
