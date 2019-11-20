package com.hbfintech.hound.core.listener;

import com.hbfintech.hound.core.event.HoundEvent;

/**
 * All managed by hound containers are hound beans, and you can listen for initialization through this interface.
 * @author frank
 */
public interface HoundEventListener
{
    /**
     * Used to listen for events for {@link com.hbfintech.hound.core.support.Hound}
     * @param event HoundShepherd event
     */
    void onEvent(HoundEvent event);
}
