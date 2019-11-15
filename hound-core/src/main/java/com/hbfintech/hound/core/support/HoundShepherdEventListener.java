package com.hbfintech.hound.core.support;

/**
 * All managed by hound containers are hound beans, and you can listen for initialization through this interface.
 * @author frank
 */
public interface HoundShepherdEventListener
{
    /**
     * Used to listen for events for {@link com.hbfintech.hound.core.support.HoundShepherd}
     * @param event HoundShepherd event
     *
     */
    void onEvent(HoundShepherdEvent event);
}
