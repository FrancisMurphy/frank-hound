package com.hbfintech.hound.core.support;

import lombok.NonNull;

public class HoundEventNotifier
{
    private HoundEventListenerRegistry houndEventListenerRegistry;

    public HoundEventNotifier()
    {
    }

    public void init()
    {
        houndEventListenerRegistry.init();
    }

    public void notify(@NonNull final EventObject event)
    {
        if(event instanceof HoundShepherdEvent)
        {
            houndEventListenerRegistry.getShepherdEventListeners().forEachRemaining(
                    houndShepherdEventListener -> houndShepherdEventListener
                            .onEvent((HoundShepherdEvent)event));
        }
    }
}
