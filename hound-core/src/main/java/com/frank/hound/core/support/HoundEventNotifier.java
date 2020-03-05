package com.frank.hound.core.support;

import com.frank.hound.core.event.BaseHoundEvent;
import lombok.NonNull;

/**
 * Hound internal event notifier,that will notify hound event listener synchronously.
 * @author frank
 */
public class HoundEventNotifier
{
    private HoundEventListenerRegistry houndEventListenerRegistry;

    public HoundEventNotifier(HoundEventListenerRegistry houndEventListenerRegistry)
    {
        this.houndEventListenerRegistry = houndEventListenerRegistry;
    }

    public void notify(@NonNull final EventObject event)
    {
        if(event instanceof BaseHoundEvent)
        {
            houndEventListenerRegistry.getShepherdEventListeners().forEachRemaining(
                    houndShepherdEventListener -> houndShepherdEventListener
                            .onEvent((BaseHoundEvent)event));
        }
    }
}
