package com.hbfintech.hound.core.support;

import lombok.NonNull;

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
        if(event instanceof SheepehoundEvent)
        {
            houndEventListenerRegistry.getShepherdEventListeners().forEachRemaining(
                    houndShepherdEventListener -> houndShepherdEventListener
                            .onEvent((SheepehoundEvent)event));
        }
    }
}
