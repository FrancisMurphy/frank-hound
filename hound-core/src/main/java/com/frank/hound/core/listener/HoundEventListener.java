package com.frank.hound.core.listener;

import com.frank.hound.core.event.BaseHoundEvent;

/**
 * All managed by hound containers are hound beans, and you can listen for initialization through this interface.
 * @author frank
 */
public interface HoundEventListener
{
    /**
     * Used to listen for events for {@link com.frank.hound.core.support.Hound}
     * @param event HoundShepherd event
     */
    void onEvent(BaseHoundEvent event);
}
