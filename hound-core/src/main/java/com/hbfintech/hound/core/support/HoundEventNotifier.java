package com.hbfintech.hound.core.support;

import com.hbfintech.hound.core.event.HoundEvent;
import lombok.NonNull;

/**
 * Hound internal event notifier,that will notify hound event listener synchronously.
 * @author frank
 */
public class HoundEventNotifier
{
    private HoundEventListenerRegistry houndEventListenerRegistry;

    public HoundEventNotifier()
    {
        houndEventListenerRegistry = new HoundEventListenerRegistry();
    }

    public void init()
    {
        houndEventListenerRegistry.init();
    }

    public void notify(@NonNull final EventObject event)
    {
        if(event instanceof HoundEvent)
        {
            houndEventListenerRegistry.getShepherdEventListeners().forEachRemaining(
                    houndShepherdEventListener -> houndShepherdEventListener
                            .onEvent((HoundEvent)event));
        }
    }
}
