package com.frank.hound.core.event;

import com.frank.hound.core.support.Hound;

/**
 * @author frank
 */
public class UnpackedEvent extends BaseHoundEvent
{
    /**
     * Used to listen for processed events for {@link com.frank.hound.core.acceptor.unpacker.Unpacker}
     * @param source Hound instance that publish event
     */
    public UnpackedEvent(
            Hound source)
    {
        super(source);
    }
}
