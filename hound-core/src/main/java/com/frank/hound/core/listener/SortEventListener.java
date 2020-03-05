package com.frank.hound.core.listener;

import com.frank.hound.core.event.BaseHoundEvent;
import com.frank.hound.core.event.SortEvent;

/**
 * @author frank
 */
public class SortEventListener implements HoundEventListener
{
    @Override
    public void onEvent(BaseHoundEvent event)
    {
        if(event instanceof SortEvent)
        {
            SortEvent sortEvent = (SortEvent)event;
            sortEvent.sort();
        }
    }
}
