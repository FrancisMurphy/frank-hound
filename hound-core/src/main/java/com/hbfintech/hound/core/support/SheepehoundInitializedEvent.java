package com.hbfintech.hound.core.support;

/**
 *
 * @author frank
 */
public class SheepehoundInitializedEvent extends SheepehoundEvent
{
    /**
     * Used to listen for Initialization events for {@link Sheepehound}
     * @param source
     * @param args
     */
    public SheepehoundInitializedEvent(Sheepehound source, String[] args)
    {
        super(source, args);
    }
}
