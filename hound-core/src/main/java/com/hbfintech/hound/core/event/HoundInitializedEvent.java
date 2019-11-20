package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.support.Hound;

/**
 * Used to listen for Initialization events for {@link Hound}
 * @author frank
 */
public class HoundInitializedEvent extends HoundEvent
{
    /**
     * Used to listen for Initialization events for {@link Hound}
     * @param source Hound instance that publish event
     * @param args Custom parameter
     */
    public HoundInitializedEvent(Hound source, String[] args)
    {
        super(source, args);
    }
}
