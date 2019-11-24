package com.hbfintech.hound.core.event;

import com.hbfintech.hound.core.support.Hound;

/**
 * @author frank
 */
public class UnpackedEvent extends BaseHoundEvent
{
    /**
     * Used to listen for processed events for {@link com.hbfintech.hound.core.acceptor.unpacker.Unpacker}
     * @param source Hound instance that publish event
     */
    public UnpackedEvent(
            Hound source)
    {
        super(source);
    }
}
