package com.hbfintech.hound.core.support;

/**
 * All managed by hound containers are hound beans, and you can listen for initialization through this interface.
 * @author frank
 */
public interface SheepehoundEventListener
{
    /**
     * Used to listen for events for {@link Sheepehound}
     * @param event HoundShepherd event
     *
     */
    void onEvent(SheepehoundEvent event);
}
