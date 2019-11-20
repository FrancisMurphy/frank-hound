package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.support.Hound;

/**
 * @author frank
 */
public class HoundPackedEvent extends HoundEvent
{
    /**
     * Used to listen for processed events for {@link com.hbfintech.hound.core.requester.packer.Packer}
     * @param source Hound instance that publish event
     * @param args Custom parameter
     */
    public HoundPackedEvent(
            Hound source,
            String[] args)
    {
        super(source, args);
    }
}
