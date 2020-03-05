package com.frank.hound.core.event;

import com.frank.hound.core.support.Hound;

/**
 * @author frank
 */
public class PackedEvent extends BaseHoundEvent
{
    /**
     * Used to listen for processed events for {@link com.frank.hound.core.requester.packer.Packer}
     * @param source Hound instance that publish event
     */
    public PackedEvent(
            Hound source)
    {
        super(source);
    }
}
