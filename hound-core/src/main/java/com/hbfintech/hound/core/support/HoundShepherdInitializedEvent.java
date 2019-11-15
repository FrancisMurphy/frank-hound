package com.hbfintech.hound.core.support;

/**
 *
 * @author frank
 */
public class HoundShepherdInitializedEvent extends HoundShepherdEvent
{
    /**
     * Used to listen for Initialization events for {@link com.hbfintech.hound.core.support.HoundShepherd}
     * @param source
     * @param args
     */
    public HoundShepherdInitializedEvent(HoundShepherd source, String[] args)
    {
        super(source, args);
    }
}
